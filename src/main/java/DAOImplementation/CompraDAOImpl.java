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
                        rs.getInt("idCliente"),
                
                
                
                );
                servicos.put(servico.getIdServico(), servico);
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
            return true;
        }

        System.err.println("ERRO: COMPRA JA CADASTRADA");
        return false;
    }

    @Override
    public Compra consulta(Integer id) {
        if (compras.containsKey(id)) {
            return (Compra) compras.get(id);
        }

        System.err.println("ERRO: COMPRA NAO ENCONTRADA");
        return null;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (compras.containsKey(id)) {
                Compra c = (Compra) compras.get(id);
                c.setCliente((Cliente) args[0]);
                c.setlProdutos((ArrayList<Produto>) args[1]);
                c.setlServicos((ArrayList<Servico>) args[2]);
                c.alteraPreco();
                return true;
            }
        }

        System.err.println(String.format("ERRO: COMPRA %d NAO ALTERADO", id));
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
