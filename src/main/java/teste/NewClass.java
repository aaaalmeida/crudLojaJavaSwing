/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import DAOImplementation.FuncionarioDAOImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author arthu
 */
public class NewClass {

    public static void main(String[] args) {

        FuncionarioDAOImpl fTeste;
        String nomeBanco = "teste1";
        String url = "jdbc:postgresql://localhost:5432/" + nomeBanco;
        Connection connection;

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
            fTeste = new FuncionarioDAOImpl(connection);

            System.out.println("ta indo");
            fTeste.listarRelatorio();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
