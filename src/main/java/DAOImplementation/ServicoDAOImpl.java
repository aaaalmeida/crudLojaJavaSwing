/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImplementation;

import models.Servico;
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
import models.Promocao;

/**
 *
 * @author arthu
 */
public class ServicoDAOImpl implements DAOInterface<Servico> {

    private HashMap<Integer, Servico> servicos;
    private HashMap<Integer, Promocao> lPromocoes;
    private String url;
    private Integer codUltimoServico;

    public ServicoDAOImpl(String url, HashMap<Integer, Servico> servicos, HashMap<Integer, Promocao> lPromocoes) {
        this.servicos = servicos;
        this.lPromocoes = lPromocoes;
        this.url = url;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT nextval('servicos_idservico_seq'), currval('servicos_idservico_seq') AS valor;");
            while (rs.next()) {
                codUltimoServico = rs.getInt("valor");
            }

            rs = statement.executeQuery("SELECT * FROM servicos;");

            while (rs.next()) {
                Servico servico = new Servico(
                        rs.getInt("idservico"),
                        rs.getDate("data").toLocalDate(),
                        rs.getTime("hora").toLocalTime(),
                        rs.getInt("idanimal"),
                        rs.getInt("idcliente"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("idpromocao"));
                Integer idPromocao = servico.getIdPromocao();
                servico.setPromocao(idPromocao, lPromocoes.get(idPromocao));
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
    public Boolean adicionar(Servico obj) {
        obj.setIdServico(codUltimoServico);
        servicos.put(obj.getIdServico(), obj);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO servico(nome, data, hora, preco, idcliente, idanimal, idpromocao) VALUES (?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, obj.getNome());
            ps.setDate(2, Date.valueOf(obj.getData()));
            ps.setDouble(3, obj.getPreco());
            ps.setInt(4, obj.getIdCliente());
            ps.setInt(5, obj.getIdAnimal());
            ps.setInt(6, obj.getIdPromocao());
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
    public Servico consultaPorId(Integer id) {
        if (servicos.containsKey(id)) {
            return (Servico) servicos.get(id);
        }

        System.err.println("ERRO: CONSULTA DE PRODUTO");
        return null;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (servicos.containsKey(id)) {
                Servico s = (Servico) servicos.get(id);
                s.setNome((String) args[0]);
                s.setPreco(Double.valueOf((String) args[1]));
                s.setPromocao((Integer) args[2], lPromocoes.get((Integer) args[2]));
                s.setData((LocalDate) args[3]);
                s.setHora((LocalTime) args[4]);
                s.setIdAnimal((Integer) args[5]);
                s.setIdCliente((Integer) args[6]);

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
    public Servico remove(Integer id) {
        Servico s = (Servico) servicos.remove(id);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM servicos WHERE idservico = ?;");
            ps.setInt(1, s.getIdServico());
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

        return s;
    }

    @Override
    public HashMap<Integer, Servico> relatorio() {
        return this.servicos;
    }
}
