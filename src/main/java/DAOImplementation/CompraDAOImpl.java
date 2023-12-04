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

/**
 *
 * @author arthu
 */
public class CompraDAOImpl implements DAOInterface<Compra> {

    private HashMap<Integer, Compra> compras;
    private String url;

    public CompraDAOImpl(String url) {
        this.compras = new HashMap<>();
        this.url = url;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM compras;");

            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getInt("idCompra"),
                        rs.getInt("idCliente"));
                compras.put(compra.getIdCompra(), compra);
            }
            
            for(Integer idCompra : compras.keySet()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM compras_produtos WHERE idcompra = ?;");
                preparedStatement.setInt(1, idCompra);
                
                rs = preparedStatement.executeQuery();
                while(rs.next()) {
                    compras.get(idCompra).addProduto(rs.getInt("idproduto"));
                }
            }
            
            for(Integer idCompra : compras.keySet()) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM compras_servicos WHERE idcompra = ?;");
                preparedStatement.setInt(1, idCompra);
                
                rs = preparedStatement.executeQuery();
                while(rs.next()) {
                    compras.get(idCompra).addServico(rs.getInt("idservico"));
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
        if (!compras.containsKey(obj.getIdCompra())) {
            compras.put(obj.getIdCompra(), obj);

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, "postgres", "postgres");
                PreparedStatement ps = connection.prepareStatement("INSERT INTO compra(precototal, idcliente) VALUES (?, ?);");
                ps.setDouble(1, obj.getPrecoTotal());
                ps.setInt(2, obj.getIdCliente());
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

        System.err.println("ERRO: CADASTRO DE COMPRA");
        return false;
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
                c.setIdCliente((Integer) args[0]);
                c.setKeysProdutos((ArrayList<Integer>) args[1]);
                
                c.setKeysServicos((ArrayList<Integer>) args[2]);
                        
                        
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, "postgres", "postgres");
                    PreparedStatement ps = connection.prepareStatement("UPDATE servicos SET nome = ?, preco = ?, idpromocao = ?, "
                            + "data = ?, hora = ?, idcliente = ?, idanimal = ? WHERE idservico = ?;");
                    ps.setString(1, s.getNome());
                    ps.setDouble(2, s.getPreco());
                    ps.setInt(3, s.getIdPromocao());
                    ps.setDate(4, Date.valueOf(s.getData()));
                    ps.setTime(5, Time.valueOf(s.getHora()));
                    ps.setInt(6, s.getIdCliente());
                    ps.setInt(7, s.getIdAnimal());
                    ps.setInt(8, s.getIdServico());
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

        System.err.println("ERRO: ALTERACAO DE SERVICO");
        return false;
    }

    @Override
    public Compra remove(Integer id) {
        return (Compra) compras.remove(id);
    }

    @Override
    public HashMap<Integer, Compra> relatorio() {
        return this.compras;
    }
}
