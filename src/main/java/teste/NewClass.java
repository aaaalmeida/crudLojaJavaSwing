/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import DAOImplementation.FuncionarioDAOImpl;

import models.Funcionario;

/**
 *
 * @author arthu
 */
public class NewClass {

    public static void main(String[] args) {

        String nomeBanco = "teste1";
        String url = "jdbc:postgresql://localhost:5432/" + nomeBanco;
        /*
        Connection connection;

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");

            System.out.println("ta indo");
            fTeste.listarRelatorio();

        } catch (SQLException e) {
            e.printStackTrace();
        }
         */
        FuncionarioDAOImpl fTeste = new FuncionarioDAOImpl(url);
        fTeste.listarRelatorio();
        System.out.println("--------------------");
        System.out.println(fTeste.remove(Integer.valueOf(4)));
        System.out.println("--------------------");
        fTeste.listarRelatorio();
    }
}
