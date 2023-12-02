/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImplementation;

import models.Promocao;
import interfaces.DAOInterface;

import java.sql.Date;
import java.sql.Time;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author arthu
 */
public class PromocaoDAOImpl implements DAOInterface<Promocao> {

    private HashMap<Integer, Promocao> promocoes;
    private String url;

    public PromocaoDAOImpl(String url) {
        this.promocoes = new HashMap<>();
        this.url = url;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM promocoes;");

            while (rs.next()) {
                Promocao promocao = new Promocao(
                        rs.getInt("idpromocao"),
                        rs.getDouble("valorporcentagem"),
                        rs.getDouble("valorfixo"),
                        rs.getDate("data").toLocalDate(),
                        rs.getTime("hora").toLocalTime());
                promocoes.put(promocao.getIdPromocao(), promocao);
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
    public Boolean adicionar(Promocao obj) {
        if (!promocoes.containsKey(obj.getIdPromocao())) {
            promocoes.put(obj.getIdPromocao(), obj);

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, "postgres", "postgres");
                PreparedStatement ps = connection.prepareStatement("INSERT INTO promocoes(data, hora, valorfixo, valorporcentagem) VALUES (?, ?, ?, ?);");
                ps.setDate(1, Date.valueOf(obj.getData()));
                ps.setTime(2, Time.valueOf(obj.getHora()));
                ps.setDouble(3, obj.getValorDesconto());
                ps.setDouble(4, obj.getPorcDesconto());
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

        System.err.println("ERRO: CADASTRO DE PROMOCAO");
        return false;
    }

    @Override
    public Promocao consultaPorId(Integer id) {
        if (promocoes.containsKey(id)) {
            return (Promocao) promocoes.get(id);
        }

        System.err.println("ERRO: CONSULTA DE PROMOCAO");
        return null;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (promocoes.containsKey(id)) {
                Promocao p = (Promocao) promocoes.get(id);
                p.setValorDesconto(Double.valueOf((String) args[0]));
                p.setPorcDesconto(Double.valueOf((String) args[1]));
                p.setData((LocalDate) args[2]);
                p.setHora((LocalTime) args[3]);

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, "postgres", "postgres");
                    PreparedStatement ps = connection.prepareStatement("UPDATE promocoes SET data = ?, hora = ?, valorfixo = ?, valorporcentagem = ? WHERE idpromocao= ?;");
                    ps.setDate(1, Date.valueOf(p.getData()));
                    ps.setTime(2, Time.valueOf(p.getHora()));
                    ps.setDouble(3, p.getValorDesconto());
                    ps.setDouble(4, p.getPorcDesconto());
                    ps.setInt(5, p.getIdPromocao());
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
        System.err.println("ERRO: ALTERACAO DE PROMOCAO");
        return false;
    }

    @Override
    public Promocao remove(Integer id) {
        Promocao p = (Promocao) promocoes.remove(id);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM promocoes WHERE idpromocao = ?;");
            ps.setInt(1, p.getIdPromocao());
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
    public HashMap<Integer, Promocao> relatorio() {
        return this.promocoes;
    }
}
