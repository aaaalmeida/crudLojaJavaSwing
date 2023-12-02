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
    private String url;

    public AnimalDAOImpl(String url) {
        this.animais = new HashMap<>();
        this.url = url;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM animais;");

            while (rs.next()) {
                Animal animal = new Animal(
                        rs.getInt("idanimal"),
                        rs.getInt("idcliente"),
                        rs.getString("nome"),
                        rs.getString("especie"));
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
        if (!animais.containsKey(obj.getIdAnimal())) {
            animais.put(obj.getIdAnimal(), obj);

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, "postgres", "postgres");
                PreparedStatement ps = connection.prepareStatement("INSERT INTO animais (nome, especie, idcliente) VALUES (?, ?, ?);");
                ps.setString(1, obj.getNome());
                ps.setString(2, obj.getEspecie());
                ps.setInt(3, obj.getIdAnimal());
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

        System.err.println("ERRO: CADASTRO DE ANIMAL");
        return false;
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
                a.setIdDono((Integer) args[2]);

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, "postgres", "postgres");
                    PreparedStatement ps = connection.prepareStatement("UPDATE animais SET nome = ?, especie = ?, idcliente WHERE idanimal = ?;");
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
