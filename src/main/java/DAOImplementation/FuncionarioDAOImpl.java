/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImplementation;

import models.Funcionario;
import interfaces.DAOInteface;

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
public class FuncionarioDAOImpl implements DAOInteface<Funcionario> {

    private HashMap funcionarios;
    private String url;

    public FuncionarioDAOImpl(String url) {
        this.funcionarios = new HashMap<Integer, Funcionario>();
        this.url = url;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM funcionarios;");

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

    public void listarRelatorio() {
        funcionarios.forEach((Integer, Funcionario) -> System.out.println(Funcionario));
    }

    @Override
    public Boolean adicionar(Funcionario obj) {
        if (!funcionarios.containsKey(obj.getIdFuncionario())) {
            funcionarios.put(obj.getIdFuncionario(), obj);

            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, "postgres", "postgres");
                PreparedStatement ps = connection.prepareStatement("INSERT INTO funcionarios (nome, cpf, usuario, senha) VALUES (?, ?, ?, ?);");
                ps.setString(1, obj.getNome());
                ps.setString(2, obj.getCpf());
                ps.setString(3, obj.getUsuario());
                ps.setString(4, obj.getSenha());
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

        System.err.println("ERRO: CADASTRO DE FUNCIONARIO");
        return false;
    }

    @Override
    public Funcionario consultaPorId(Integer id) {
        if (!Objects.isNull(id)) {
            if (funcionarios.containsKey(id)) {
                return (Funcionario) funcionarios.get(id);
            }
        }

        System.err.println("ERRO: CONSULTA DE FUNCIONARIO");
        return null;
    }

    public Funcionario consultaPorCpf(String cpf) {
        // TODO
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
                    PreparedStatement ps = connection.prepareStatement("UPDATE funcionario SET nome = ?, cpf = ?, usuario = ?, senha = ? WHERE idfuncionario = ?;");
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
