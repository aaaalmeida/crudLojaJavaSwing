package DAOImplementation;

import models.Animal;
import interfaces.DAOInterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Objects;
import models.Cliente;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author arthu
 */
public class AnimalDAOImpl implements DAOInterface<Animal> {
    
    private HashMap<Integer, Animal> animais;
    private HashMap<Integer, Cliente> lClientes;
    private String url;
    private Integer codUltimoAnimal;
    
    public AnimalDAOImpl(String url, HashMap<Integer, Animal> animais, HashMap<Integer, Cliente> lClientes) {
        this.animais = animais;
        this.lClientes = lClientes;
        this.url = url;
        
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT MAX(idanimal) from animais;");
            if (rs.next()) {
                if (!rs.wasNull()) {
                    codUltimoAnimal = rs.getInt("MAX") + 1;
                } else {
                    rs = statement.executeQuery("SELECT nextval('animais_idanimal_seq'), currval('animais_idanimal_seq') AS valor;");
                    codUltimoAnimal = rs.getInt("valor");
                }
            }
            
            rs = statement.executeQuery("SELECT * FROM animais;");
            
            while (rs.next()) {
                Animal animal = new Animal(
                        rs.getInt("idanimal"),
                        rs.getInt("idcliente"),
                        lClientes.get(rs.getInt("idcliente")),
                        rs.getString("nome"),
                        rs.getString("especie"));
                if (lClientes.containsKey(animal.getIdDono())) {
                    lClientes.get(animal.getDono().getIdCliente()).addAnimal(animal);
                }
                animais.put(animal.getIdAnimal(), animal);
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
    public Boolean adicionar(Animal obj) {
        obj.setIdAnimal(codUltimoAnimal);
        codUltimoAnimal++;
        
        obj.getDono().addAnimal(obj);
        animais.put(obj.getIdAnimal(), obj);
        
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO animais (idanimal, nome, especie, idcliente) VALUES (?, ?, ?, ?);");
            ps.setInt(1, obj.getIdAnimal());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getEspecie());
            ps.setInt(4, obj.getIdDono());
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
    public Animal consultaPorId(Integer id) {
        if (animais.containsKey(id)) {
            return (Animal) animais.get(id);
        }
        
        System.err.println("ERRO: CONSULTA DE ANIMAL");
        return null;
    }
    
    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (animais.containsKey(id)) {
                Animal a = (Animal) animais.get(id);
                a.setNome(String.valueOf(args[0]));
                a.setEspecie(String.valueOf(args[1]));
                
                a.getDono().removeAnimal(a);
                a.setDono((Integer) args[2], lClientes.get((Integer) args[2]));
                a.getDono().addAnimal(a);
                
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, "postgres", "postgres");
                    PreparedStatement ps = connection.prepareStatement("UPDATE animais SET nome = ?, especie = ?, idcliente = ? WHERE idanimal = ?;");
                    ps.setString(1, a.getNome());
                    ps.setString(2, a.getEspecie());
                    ps.setInt(3, a.getIdDono());
                    ps.setInt(4, a.getIdAnimal());
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
        
        System.err.println("ERRO: ALTERACAO DE ANIMAL");
        return false;
    }
    
    @Override
    public Animal remove(Integer id) {
        Animal a = (Animal) animais.remove(id);
        a.getDono().removeAnimal(a);
        
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM animais WHERE idanimal = ?;");
            ps.setInt(1, a.getIdAnimal());
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
        
        return a;
    }
    
    @Override
    public HashMap<Integer, Animal> relatorio() {
        return this.animais;
    }
}
