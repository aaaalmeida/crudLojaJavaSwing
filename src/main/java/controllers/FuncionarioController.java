/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import interfaces.Controller;
import java.util.HashMap;
import java.util.Objects;
import models.Funcionario;

/**
 *
 * @author arthu
 */
public class FuncionarioController implements Controller<Funcionario> {

    private HashMap funcionarios;

    public FuncionarioController() {
        this.funcionarios = new HashMap<Integer, Funcionario>();
    }

    @Override
    public Boolean adicionar(Funcionario obj) {
        if (!funcionarios.containsKey(obj.getIdFuncionario())) {
            funcionarios.put(obj.getIdFuncionario(), obj);
            return true;
        }

        System.err.println("ERRO: FUNCIONARIO JA CADASTRADO");
        return false;
    }

    @Override
    public Funcionario consulta(Integer id) {
        if (!Objects.isNull(id)) {
            if (funcionarios.containsKey(id)) {
                return (Funcionario) funcionarios.get(id);
            }
        }

        System.err.println(String.format("ERRO: FUNCIONARIO %d NAO ENCONTRADO", id));
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
                return true;
            }
        }

        System.err.println(String.format("ERRO: FUNCIONARIO %d NAO ALTERADO", id));
        return false;
    }

    @Override
    public Funcionario remove(Integer id) {
        return (Funcionario) funcionarios.remove(id);
    }

    @Override
    public HashMap<Integer, Funcionario> relatorio() {
        return this.funcionarios;
    }
}
