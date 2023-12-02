package DAOImplementation;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import models.Cliente;
import interfaces.DAOInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author arthu
 */
public class ClienteDAOImpl implements DAOInterface<Cliente> {

    private HashMap<Integer, Cliente> clientes;
    private String url;

    public ClienteDAOImpl(String url) {
        this.clientes = new HashMap<>();
        this.url = url;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM clientes;");

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("idcliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"));
                clientes.put(cliente.getIdCliente(), cliente);
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
    public Boolean adicionar(Cliente obj) {
        if (!clientes.containsKey(obj.getIdCliente())) {
            clientes.put(obj.getIdCliente(), obj);

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, "postgres", "postgres");
                PreparedStatement ps = connection.prepareStatement("INSERT INTO clientes (nome, cpf) VALUES (?, ?);");
                ps.setString(1, obj.getNome());
                ps.setString(2, obj.getCpf());
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

        System.err.println("ERRO: CADASTRO DE CLIENTE");
        return false;
    }

    @Override
    public Cliente consultaPorId(Integer id) {
        if (clientes.containsKey(id)) {
            return (Cliente) clientes.get(id);
        }

        System.err.println("ERRO: CONSULTA DE CLIENTE VIA ID");
        return null;
    }

    public Cliente consultaPorCpf(String cpf) {
        for (Integer i : clientes.keySet()) {
            if (clientes.get(i).getCpf().equals(cpf)) {
                return clientes.get(i);
            }
        }
        System.err.println("ERRO: CONSULTA DE CLIENTE VIA CPF");
        return null;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (clientes.containsKey(id)) {
                Cliente c = (Cliente) clientes.get(id);
                c.setNome(String.valueOf(args[0]));
                c.setCpf(String.valueOf(args[1]));

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, "postgres", "postgres");
                    PreparedStatement ps = connection.prepareStatement("UPDATE clientes SET nome = ?, cpf = ? WHERE idcliente = ?;");
                    ps.setString(1, c.getNome());
                    ps.setString(2, c.getCpf());
                    ps.setInt(3, c.getIdCliente());
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

        System.err.println("ERRO: ALTERAÇÃO DE CLIENTE");
        return false;
    }

    @Override
    public Cliente remove(Integer id) {
        Cliente c = (Cliente) clientes.remove(id);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM clientes WHERE idcliente = ?;");
            ps.setInt(1, c.getIdCliente());
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
    public HashMap<Integer, Cliente> relatorio() {
        return this.clientes;
    }
}
