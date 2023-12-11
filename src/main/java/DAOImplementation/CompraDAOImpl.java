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
    private Integer codUltimaCompraProduto;
    private Integer codUltimaCompraServico;
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

            ResultSet rs = statement.executeQuery("SELECT MAX(idcompra) from compras;");
            if (rs.next()) {
                if (!rs.wasNull()) {
                    codUltimaCompra = rs.getInt("MAX") + 1;
                } else {
                    rs = statement.executeQuery("SELECT nextval('compras_idcompra_seq'), currval('compras_idcompra_seq') AS valor;");
                    codUltimaCompra = rs.getInt("valor");
                }
            }

            rs = statement.executeQuery("SELECT MAX(idcompraproduto) from compras_produtos;");
            if (rs.next()) {
                if (!rs.wasNull()) {
                    codUltimaCompraProduto = rs.getInt("MAX") + 1;
                } else {
                    rs = statement.executeQuery("SELECT nextval('compras_produtos_idcompraproduto_seq'), currval('compras_produtos_idcompraproduto_seq') AS valor;");
                    codUltimaCompraProduto = rs.getInt("valor");
                }
            }

            rs = statement.executeQuery("SELECT MAX(idcompraservico) from compras_servicos;");
            if (rs.next()) {
                if (!rs.wasNull()) {
                    codUltimaCompraServico = rs.getInt("MAX") + 1;
                } else {
                    rs = statement.executeQuery("SELECT nextval('compras_servicos_idcompraservico_seq'), currval('compras_servicos_idcompraservico_seq') AS valor;");
                    codUltimaCompraServico = rs.getInt("valor");
                }
            }

            rs = statement.executeQuery("SELECT * FROM compras;");

            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getInt("idcompra"),
                        rs.getInt("idcliente"),
                        lClientes.get(rs.getInt("idCliente")),
                        new ArrayList<Produto>(),
                        new ArrayList<Servico>());
                if (lClientes.containsKey(compra.getIdCliente())) {
                    compra.getCliente().addCompra(compra);
                }
                compras.put(compra.getIdCompra(), compra);
            }

            rs = statement.executeQuery("SELECT * FROM compras_produtos;");
            while (rs.next()) {
                compras.get(rs.getInt("idcompra")).addProduto(lProdutos.get(rs.getInt("idproduto")));
            }

            rs = statement.executeQuery("SELECT * FROM compras_servicos;");
            while (rs.next()) {
                compras.get(rs.getInt("idcompra")).addServico(lServicos.get(rs.getInt("idservico")));
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

        obj.getCliente().addCompra(obj);
        compras.put(obj.getIdCompra(), obj);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO compras(idcompra, precototal, idcliente) VALUES (?, ?, ?);");
            ps.setInt(1, obj.getIdCompra());
            ps.setDouble(2, obj.getPrecoTotal());
            ps.setInt(3, obj.getIdCliente());
            ps.executeUpdate();

            for (Produto produto : obj.getlProdutos()) {
                ps = connection.prepareStatement("INSERT INTO compras_produtos(idcompraproduto, idcompra, idproduto) VALUES (?, ?, ?);");
                ps.setInt(1, codUltimaCompraProduto);
                codUltimaCompraProduto++;
                ps.setInt(2, obj.getIdCompra());
                ps.setInt(3, produto.getIdProduto());
                ps.executeUpdate();
            }

            for (Servico servico : obj.getlServicos()) {
                ps = connection.prepareStatement("INSERT INTO compras_servicos(idcompraservico, idcompra, idservico) VALUES (?, ?, ?);");
                ps.setInt(1, codUltimaCompraServico);
                codUltimaCompraServico++;
                ps.setInt(2, obj.getIdCompra());
                ps.setInt(3, servico.getIdServico());
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
                        ps = connection.prepareStatement("INSERT INTO compras_produtos (idcompraproduto, idcompra, idproduto) VALUES (?, ?, ?);");
                        ps.setInt(1, codUltimaCompraProduto);
                        codUltimaCompraProduto++;
                        ps.setInt(2, c.getIdCompra());
                        ps.setInt(3, produto.getIdProduto());
                        ps.executeUpdate();
                    }

                    ps = connection.prepareStatement("DELETE FROM compras_servicos WHERE idcompra = ?;");
                    ps.setInt(1, c.getIdCompra());
                    ps.executeUpdate();
                    for (Servico servico : c.getlServicos()) {
                        ps = connection.prepareStatement("INSERT INTO compras_servicos (idcompraservico, idcompra, idservico) VALUES (?, ?, ?);");
                        ps.setInt(1, codUltimaCompraServico);
                        codUltimaCompraServico++;
                        ps.setInt(2, c.getIdCompra());
                        ps.setInt(3, servico.getIdServico());
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

        c.getCliente().removeCompra(c);

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
