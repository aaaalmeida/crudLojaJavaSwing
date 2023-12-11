/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImplementation;

import models.Funcionario;
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
public class FuncionarioDAOImpl implements DAOInterface<Funcionario> {

    private HashMap<Integer, Funcionario> funcionarios;
    private String url;
    private Integer codUltimoFuncionario;
    
    public FuncionarioDAOImpl(String url, HashMap<Integer, Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
        this.url = url;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT MAX(idfuncionario) from funcionarios;");
            if (rs.next()) {
                if(!rs.wasNull()) {
                    codUltimoFuncionario = rs.getInt("MAX") + 1;
                } else {
                    rs = statement.executeQuery("SELECT nextval('funcionarios_idfuncionario_seq'), currval('funcionarios_idfuncionario_seq') AS valor;");
                    codUltimoFuncionario = rs.getInt("valor");
                }
            }
            
            rs = statement.executeQuery("SELECT * FROM funcionarios;");

            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getInt("idfuncionario"),
                        rs.getString("usuario"),
                        rs.getString("senha"),
                        rs.getString("nome"),
                        rs.getString("cpf"));
                funcionarios.put(funcionario.getIdFuncionario(), funcionario);
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
    public Boolean adicionar(Funcionario obj) {
            obj.setIdFuncionario(codUltimoFuncionario);
            codUltimoFuncionario++;
            funcionarios.put(obj.getIdFuncionario(), obj);

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, "postgres", "postgres");
                PreparedStatement ps = connection.prepareStatement("INSERT INTO funcionarios (idfuncionario, nome, cpf, usuario, senha) VALUES (?, ?, ?, ?, ?);");
                ps.setInt(1, obj.getIdFuncionario());
                ps.setString(2, obj.getNome());
                ps.setString(3, obj.getCpf());
                ps.setString(4, obj.getUsuario());
                ps.setString(5, obj.getSenha());
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
    public Funcionario consultaPorId(Integer id) {
        if (!Objects.isNull(id)) {
            if (funcionarios.containsKey(id)) {
                return (Funcionario) funcionarios.get(id);
            }
        }

        System.err.println("ERRO: CONSULTA DE FUNCIONARIO VIA ID");
        return null;
    }

    public Funcionario consultaPorCpf(String cpf) {
        for (Integer i : funcionarios.keySet()) {
            if (funcionarios.get(i).getCpf().equals(cpf)) {
                return funcionarios.get(i);
            }
        }
        System.err.println("ERRO: CONSULTA DE FUNCIONARIO VIA CPF");
        return null;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (funcionarios.containsKey(id)) {
                Funcionario f = (Funcionario) funcionarios.get(id);
                f.setUsuario(String.valueOf(args[0]));
                f.setSenha(String.valueOf(args[1]));
                f.setNome(String.valueOf(args[2]));
                f.setCpf(String.valueOf(args[3]));

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, "postgres", "postgres");
                    PreparedStatement ps = connection.prepareStatement("UPDATE funcionarios SET nome = ?, cpf = ?, usuario = ?, senha = ? WHERE idfuncionario = ?;");
                    ps.setString(1, f.getNome());
                    ps.setString(2, f.getCpf());
                    ps.setString(3, f.getUsuario());
                    ps.setString(4, f.getSenha());
                    ps.setInt(5, f.getIdFuncionario());
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

        System.err.println("ERRO: ALTERACAO DE FUNCIONARIO");
        return false;
    }

    @Override
    public Funcionario remove(Integer id) {
        Funcionario f = (Funcionario) funcionarios.remove(id);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM funcionarios WHERE idfuncionario = ?;");
            ps.setInt(1, f.getIdFuncionario());
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

        return f;
    }

    @Override
    public HashMap<Integer, Funcionario> relatorio() {
        return this.funcionarios;
    }
}
