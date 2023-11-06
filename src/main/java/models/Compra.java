/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author arthu
 */
public class Compra {

    private Integer idCompra;
    private static Integer cont = 0;
    private Double precoTotal;
    private Cliente cliente;
    private ArrayList<Produto> lProdutos;
    private ArrayList<Servico> lServicos;

    public Compra(Cliente cliente, ArrayList<Produto> lProdutos, ArrayList<Servico> lServicos) {
        this.idCompra = cont;
        cont++;
        this.cliente = cliente;
        this.lProdutos = lProdutos;
        this.lServicos = lServicos;
        
        alteraPreco();
    }

    public void alteraPreco() {
        precoTotal = Double.valueOf(0);
        for (Produto p : lProdutos) {
            precoTotal += p.getPreco();
        }
        for (Servico s : lServicos) {
            precoTotal += s.getPreco();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public ArrayList<Produto> getlProdutos() {
        return lProdutos;
    }

    public ArrayList<Servico> getlServicos() {
        return lServicos;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setlProdutos(ArrayList<Produto> lProdutos) {
        this.lProdutos = lProdutos;
    }

    public void setlServicos(ArrayList<Servico> lServicos) {
        this.lServicos = lServicos;
    }

    public Object[] dadosTabela() {
        String idProdutos, idServicos;

        idProdutos = new String("");
        for (Produto p : lProdutos) {
            idProdutos += String.format("%d ", p.getIdProduto());
        }

        idServicos = new String("");
        for (Servico s : lServicos) {
            idServicos += String.format("%d ", s.getIdServico());
        }
        return new Object[]{idCompra, precoTotal, cliente.getIdCliente(), cliente.getNome(), idProdutos, idServicos};
    }
}
