/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImplementation;

import models.Compra;
import interfaces.DAOInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Objects;
import models.Cliente;
import models.Produto;
import models.Servico;

/**
 *
 * @author arthu
 */
public class CompraDAOImpl implements DAOInterface<Compra> {

    private HashMap<Integer, Compra> compras;
    private HashMap<Integer, Servico> lServicos;
    private HashMap<Integer, Produto> lProdutos;
    private HashMap<Integer, Cliente> lClientes;
    private Integer codUltimaCompra;
    private String url;

    public CompraDAOImpl(String url, HashMap<Integer, Compra> compras, HashMap<Integer, Produto> lProdutos, HashMap<Integer, Servico> lServicos, HashMap<Integer, Cliente> lClientes) {
        this.compras = compras;
        this.lServicos = lServicos;
        this.lProdutos = lProdutos;
        this.lClientes = lClientes;
        this.url = url;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT nextval('compras_idcompras_seq'), currval('compras_idcompras_seq') AS valor;");
            while (rs.next()) {
                codUltimaCompra = rs.getInt("valor");
            }
            rs = statement.executeQuery("SELECT * FROM compras;");

            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getInt("idCompra"),
                        rs.getInt("idCliente"));
                Integer idCliente = compra.getIdCliente();
                compra.setCliente(idCliente, lClientes.get(idCliente));
                compras.put(compra.getIdCompra(), compra);
            }

            for (Integer idCompra : compras.keySet()) {
                rs = statement.executeQuery("SELECT * FROM compras_produtos WHERE idcompra = " + idCompra + ";");
                while (rs.next()) {
                    Compra compra = compras.get(idCompra);
                    Integer idProduto = rs.getInt("idproduto");
                    compra.addProduto(idProduto, lProdutos.get(idProduto));
                }
            }

            for (Integer idCompra : compras.keySet()) {
                rs = statement.executeQuery("SELECT * FROM compras_servicos WHERE idcompra = " + idCompra + ";");
                while (rs.next()) {
                    Compra compra = compras.get(idCompra);
                    Integer idServico = rs.getInt("idservico");
                    compra.addServico(idServico, lServicos.get(idServico));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Boolean adicionar(Compra obj) {
            obj.setIdCompra(codUltimaCompra);
            compras.put(obj.getIdCompra(), obj);

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, "postgres", "postgres");
                PreparedStatement ps = connection.prepareStatement("INSERT INTO compra(precototal, idcliente) VALUES (?, ?);");
                ps.setDouble(1, obj.getPrecoTotal());
                ps.setInt(2, obj.getIdCliente());
                ps.executeUpdate();

                for (Integer idProduto : obj.getKeysProdutos()) {
                    ps = connection.prepareStatement("INSERT INTO compras_produtos(idcompra, idproduto) VALUES (?, ?);");
                    ps.setInt(1, obj.getIdCompra());
                    ps.setInt(2, idProduto);
                }

                for (Integer idServico : obj.getKeysServicos()) {
                    ps = connection.prepareStatement("INSERT INTO compras_servicos(idcompra, idservico) VALUES (?, ?);");
                    ps.setInt(1, obj.getIdCompra());
                    ps.setInt(2, idServico);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return true;
    }

    @Override
    public Compra consultaPorId(Integer id) {
        if (compras.containsKey(id)) {
            return (Compra) compras.get(id);
        }

        System.err.println("ERRO: CONSULTA DE COMPRA");
        return null;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (compras.containsKey(id)) {
                Compra c = (Compra) compras.get(id);
                c.setCliente((Integer) args[0], lClientes.get(c.getIdCliente()));

                c.limparProdutos();
                for (Integer idProduto : (ArrayList<Integer>) args[1]) {
                    c.addProduto(idProduto, lProdutos.get(idProduto));
                }

                c.limparServicos();
                for (Integer idServico : (ArrayList<Integer>) args[2]) {
                    c.addServico(idServico, lServicos.get(idServico));
                }

                c.alteraPreco();

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, "postgres", "postgres");
                    PreparedStatement ps = connection.prepareStatement("UPDATE compras SET precototal = ?, idcliente = ? WHERE idcompra = ?;");
                    ps.setDouble(1, c.getPrecoTotal());
                    ps.setInt(2, c.getIdCliente());
                    ps.setInt(3, c.getIdCompra());
                    ps.executeUpdate();

                    ps = connection.prepareStatement("DELETE FROM compras_produtos WHERE idcompra = ?;");
                    ps.setInt(1, c.getIdCompra());
                    ps.executeUpdate();
                    for (Integer idProduto : c.getKeysProdutos()) {
                        ps = connection.prepareStatement("INSERT INTO compras_produtos (idcompra, idproduto) VALUES (?, ?);");
                        ps.setInt(1, c.getIdCompra());
                        ps.setInt(2, idProduto);
                        ps.executeUpdate();
                    }

                    ps = connection.prepareStatement("DELETE FROM compras_servicos WHERE idcompra = ?;");
                    ps.setInt(1, c.getIdCompra());
                    ps.executeUpdate();
                    for (Integer idServico : c.getKeysServicos()) {
                        ps = connection.prepareStatement("INSERT INTO compras_servicos (idcompra, idservico) VALUES (?, ?);");
                        ps.setInt(1, c.getIdCompra());
                        ps.setInt(2, idServico);
                        ps.executeUpdate();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                return true;
            }
        }

        System.err.println("ERRO: ALTERACAO DE SERVICO");
        return false;
    }

    @Override
    public Compra remove(Integer id) {
        Compra c = (Compra) compras.remove(id);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM compras WHERE idcompra = ?;");
            ps.setInt(1, c.getIdCompra());
            ps.executeUpdate();

            ps = connection.prepareStatement("DELETE FROM compras_produtos WHERE idcompra = ?;");
            ps.setInt(1, c.getIdCompra());
            ps.executeUpdate();

            ps = connection.prepareStatement("DELETE FROM compras_servicos WHERE idcompra = ?;");
            ps.setInt(1, c.getIdCompra());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return c;
    }

    @Override
    public HashMap<Integer, Compra> relatorio() {
        return this.compras;
    }
}
