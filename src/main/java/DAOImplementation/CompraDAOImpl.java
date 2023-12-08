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

            ResultSet rs = statement.executeQuery("SELECT nextval('compras_idcompra_seq'), currval('compras_idcompra_seq') AS valor;");
            while (rs.next()) {
                codUltimaCompra = rs.getInt("valor");
            }
            
            rs = statement.executeQuery("SELECT * FROM compras;");

            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getInt("idcompra"),
                        lClientes.get(rs.getInt("idCliente")),
                        new ArrayList<>(),
                        new ArrayList<>());

                compra.getCliente().addCompra(compra);
                compras.put(compra.getIdCompra(), compra);
            }

            for (Integer idCompra : compras.keySet()) {
                rs = statement.executeQuery("SELECT * FROM compras_produtos WHERE idcompra = " + idCompra + ";");
                while (rs.next()) {
                    Compra compra = compras.get(idCompra);
                    compra.addProduto(lProdutos.get(rs.getInt("idproduto")));
                }
            }

            for (Integer idCompra : compras.keySet()) {
                rs = statement.executeQuery("SELECT * FROM compras_servicos WHERE idcompra = " + idCompra + ";");
                while (rs.next()) {
                    Compra compra = compras.get(idCompra);
                    compra.addServico(lServicos.get(rs.getInt("idservico")));
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
        codUltimaCompra++;
        compras.put(obj.getIdCompra(), obj);
        obj.getCliente().addCompra(obj);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO compra(idcompra, precototal, idcliente) VALUES (?, ?, ?);");
            ps.setInt(1, obj.getIdCompra());
            ps.setDouble(2, obj.getPrecoTotal());
            ps.setInt(3, obj.getIdCliente());
            ps.executeUpdate();

            for (Produto produto : obj.getlProdutos()) {
                ps = connection.prepareStatement("INSERT INTO compras_produtos(idcompra, idproduto) VALUES (?, ?);");
                ps.setInt(1, obj.getIdCompra());
                ps.setInt(2, produto.getIdProduto());
            }

            for (Servico servico : obj.getlServicos()) {
                ps = connection.prepareStatement("INSERT INTO compras_servicos(idcompra, idservico) VALUES (?, ?);");
                ps.setInt(1, obj.getIdCompra());
                ps.setInt(2, servico.getIdServico());
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
                c.getCliente().removeCompra(c);
                c.setCliente((Integer) args[0], lClientes.get((Integer) args[0]));
                c.getCliente().addCompra(c);

                c.limparProdutos();
                for (Produto produto : (ArrayList<Produto>) args[1]) {
                    c.addProduto(produto);
                }

                c.limparServicos();
                for (Servico servico : (ArrayList<Servico>) args[2]) {
                    c.addServico(servico);
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
                    for (Produto produto : c.getlProdutos()) {
                        ps = connection.prepareStatement("INSERT INTO compras_produtos (idcompra, idproduto) VALUES (?, ?);");
                        ps.setInt(1, c.getIdCompra());
                        ps.setInt(2, produto.getIdProduto());
                        ps.executeUpdate();
                    }

                    ps = connection.prepareStatement("DELETE FROM compras_servicos WHERE idcompra = ?;");
                    ps.setInt(1, c.getIdCompra());
                    ps.executeUpdate();
                    for (Servico servico : c.getlServicos()) {
                        ps = connection.prepareStatement("INSERT INTO compras_servicos (idcompra, idservico) VALUES (?, ?);");
                        ps.setInt(1, c.getIdCompra());
                        ps.setInt(2, servico.getIdServico());
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
