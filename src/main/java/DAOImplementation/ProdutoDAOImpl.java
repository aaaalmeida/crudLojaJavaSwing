/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImplementation;

import models.Produto;
import interfaces.DAOInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Objects;
import models.Promocao;

/**
 *
 * @author arthu
 */
public class ProdutoDAOImpl implements DAOInterface<Produto> {

    private HashMap<Integer, Produto> produtos;
    private HashMap<Integer, Promocao> lPromocoes;
    private String url;
    private Integer codUltimoProduto;

    public ProdutoDAOImpl(String url, HashMap<Integer, Produto> produtos, HashMap<Integer, Promocao> lPromocoes) {
        this.produtos = produtos;
        this.lPromocoes = lPromocoes;
        this.url = url;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT MAX(idproduto) from produtos;");
            if (rs.next()) {
                if (!rs.wasNull()) {
                    codUltimoProduto = rs.getInt("MAX") + 1;
                } else {
                    rs = statement.executeQuery("SELECT nextval('produtos_idproduto_seq'), currval('produtos_idproduto_seq') AS valor;");
                    codUltimoProduto = rs.getInt("valor");
                }
            }

            rs = statement.executeQuery("SELECT * FROM produtos;");

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("idproduto"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("idpromocao"),
                        lPromocoes.get(rs.getInt("idpromocao")),
                        rs.getString("descricao"));
                produtos.put(produto.getIdProduto(), produto);
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
    public Boolean adicionar(Produto obj) {
        obj.setIdProduto(codUltimoProduto);
        codUltimoProduto++;
        produtos.put(obj.getIdProduto(), obj);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO produtos(idproduto, nome, preco, descricao, idpromocao) VALUES (?, ?, ?, ?, ?);");
            ps.setInt(1, obj.getIdProduto());
            ps.setString(2, obj.getNome());
            ps.setDouble(3, obj.getPreco());
            ps.setString(4, obj.getDescricao());

            if (!Objects.isNull(obj.getIdPromocao())) {
                ps.setInt(5, obj.getIdPromocao());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
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

        return true;
    }

    @Override
    public Produto consultaPorId(Integer id) {
        if (produtos.containsKey(id)) {
            return (Produto) produtos.get(id);
        }

        System.err.println("ERRO: CONSULTA DE PRODUTO");
        return null;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (produtos.containsKey(id)) {
                Produto p = (Produto) produtos.get(id);
                p.setNome(String.valueOf(args[0]));
                p.setDescricao(String.valueOf(args[1]));

                if (!Objects.isNull(args[2])) {
                    p.setPromocao((Integer) args[2], lPromocoes.get((Integer) args[2]));
                } else {
                    p.setPromocao(null, null);
                }
                
                p.setPreco(Double.valueOf((String) args[3]));

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, "postgres", "postgres");
                    PreparedStatement ps = connection.prepareStatement("UPDATE produtos SET nome = ?, preco = ?, descricao = ?, idpromocao = ? WHERE idproduto = ?;");
                    ps.setString(1, p.getNome());
                    ps.setDouble(2, p.getPreco());
                    ps.setString(3, p.getDescricao());

                    if (!Objects.isNull(p.getIdPromocao())) {
                        ps.setInt(4, p.getIdPromocao());
                    } else {
                        ps.setNull(4, java.sql.Types.INTEGER);
                    }
                    ps.setInt(5, p.getIdProduto());
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
                return true;
            }
        }

        System.err.println("ERRO: ALTERACAO DE PRODUTO");
        return false;
    }

    @Override
    public Produto remove(Integer id) {
        Produto p = (Produto) produtos.remove(id);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM produtos WHERE idproduto = ?;");
            ps.setInt(1, p.getIdProduto());
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

        return p;
    }

    @Override
    public HashMap<Integer, Produto> relatorio() {
        return this.produtos;
    }
}
