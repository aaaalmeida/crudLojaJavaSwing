/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImplementation;

import models.Servico;
import models.Animal;
import models.Cliente;
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
public class ServicoDAOImpl implements DAOInterface<Servico> {
    
    private HashMap<Integer, Servico> servicos;
    private HashMap<Integer, Promocao> lPromocoes;
    private HashMap<Integer, Cliente> lClientes;
    private HashMap<Integer, Animal> lAnimais;
    private String url;
    private Integer codUltimoServico;
    
    public ServicoDAOImpl(String url, HashMap<Integer, Servico> servicos, HashMap<Integer, Promocao> lPromocoes, HashMap<Integer, Cliente> lClientes, HashMap<Integer, Animal> lAnimais) {
        this.servicos = servicos;
        this.lPromocoes = lPromocoes;
        this.lClientes = lClientes;
        this.lAnimais = lAnimais;
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
                        lAnimais.get(rs.getInt("idanimal")),
                        lClientes.get(rs.getInt("idcliente")),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        lPromocoes.get(rs.getInt("idpromocao")));
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
        codUltimoServico++;
        obj.getCliente().addServico(obj);
        servicos.put(obj.getIdServico(), obj);
        
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO servicos(idservico, nome, data, hora, preco, idcliente, idanimal, idpromocao) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setInt(1, obj.getIdServico());
            ps.setString(2, obj.getNome());
            ps.setDate(3, Date.valueOf(obj.getData()));
            ps.setTime(4, Time.valueOf(obj.getHora()));
            ps.setDouble(5, obj.getPreco());
            ps.setInt(6, obj.getIdCliente());
            ps.setInt(7, obj.getIdAnimal());
            ps.setInt(8, obj.getIdPromocao());
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
                
                if (!Objects.isNull(args[2])) {
                    s.setPromocao((Integer) args[2], lPromocoes.get((Integer) args[2]));
                }
                s.setData((LocalDate) args[3]);
                s.setHora((LocalTime) args[4]);
                s.setAnimal((Integer) args[5], lAnimais.get((Integer) args[5]));
                
                s.getCliente().removeServico(s);
                s.setCliente((Integer) args[6], lClientes.get((Integer) args[6]));
                s.getCliente().addServico(s);
                
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
        s.getCliente().removeServico(s);
        
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
