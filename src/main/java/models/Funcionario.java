/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author arthu
 */
public class Funcionario extends Pessoa {

    private String usuario;
    private String senha;
    private Integer idFuncionario;

    public Funcionario(Integer idFuncionario, String usuario, String senha, String nome, String cpf) {
        super(nome, cpf);
        this.idFuncionario = idFuncionario;
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public Object[] dadosTabela() {
        return new Object[]{idFuncionario, getNome(), getCpf(), usuario, senha};
    }

    public String toString() {
        return String.format("id %d, nome %s, cpf %s, usuario %s, senha %s \n", idFuncionario, getNome(), getCpf(), usuario, senha);
    }
}
