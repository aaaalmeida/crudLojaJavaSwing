/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import interfaces.Controller;
import java.util.HashMap;
import java.util.Objects;
import models.Cliente;

/**
 *
 * @author arthu
 */
public class ClienteController implements Controller<Cliente> {

    private HashMap clientes;

    public ClienteController() {
        this.clientes = new HashMap<Integer, Cliente>();
    }

    @Override
    public Boolean adicionar(Cliente obj) {
        if (!clientes.containsKey(obj.getIdCliente())) {
            clientes.put(obj.getIdCliente(), obj);
            return true;
        }

        System.err.println("ERRO: CLIENTE JA CADASTRADO");
        return false;
    }

    @Override
    public Cliente consulta(Integer id) {
        if (clientes.containsKey(id)) {
            return (Cliente) clientes.get(id);
        }

        System.err.println("ERRO: CLIENTE NAO ENCONTRADO");
        return null;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (clientes.containsKey(id)) {
                Cliente c = (Cliente) clientes.get(id);
                c.setNome(String.valueOf(args[0]));
                c.setCpf(String.valueOf(args[1]));
                return true;
            }
        }

        System.err.println(String.format("ERRO: CLIENTE %d NAO ALTERADO", id));
        return false;
    }

    @Override
    public Cliente remove(Integer id) {
        return (Cliente) clientes.remove(id);
    }

    @Override
    public HashMap<Integer, Cliente> relatorio() {
        return this.clientes;
    }
}
