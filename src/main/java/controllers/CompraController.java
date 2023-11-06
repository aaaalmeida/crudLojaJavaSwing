/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import interfaces.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import models.Cliente;
import models.Compra;
import models.Produto;
import models.Servico;

/**
 *
 * @author arthu
 */
public class CompraController implements Controller<Compra> {

    private HashMap compras;

    public CompraController() {
        this.compras = new HashMap<Integer, Compra>();
    }

    @Override
    public Boolean adicionar(Compra obj) {
        if (!compras.containsKey(obj.getIdCompra())) {
            compras.put(obj.getIdCompra(), obj);
            return true;
        }

        System.err.println("ERRO: COMPRA JA CADASTRADA");
        return false;
    }

    @Override
    public Compra consulta(Integer id) {
        if (compras.containsKey(id)) {
            return (Compra) compras.get(id);
        }

        System.err.println("ERRO: COMPRA NAO ENCONTRADA");
        return null;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (compras.containsKey(id)) {
                Compra c = (Compra) compras.get(id);
                c.setCliente((Cliente) args[0]);
                c.setlProdutos((ArrayList<Produto>) args[1]);
                c.setlServicos((ArrayList<Servico>) args[2]);
                c.alteraPreco();
                return true;
            }
        }

        System.err.println(String.format("ERRO: COMPRA %d NAO ALTERADO", id));
        return false;
    }

    @Override
    public Compra remove(Integer id) {
        return (Compra) compras.remove(id);
    }

    @Override
    public HashMap<Integer, Compra> relatorio() {
        return this.compras;
    }
}
