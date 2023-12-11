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
    private Double precoTotal;
    private Integer idCliente;
    private Cliente cliente;
    private ArrayList<Produto> lProdutos;
    private ArrayList<Servico> lServicos;

    public Compra(Integer idCompra, Integer idCliente, Cliente cliente, ArrayList<Produto> lProdutos, ArrayList<Servico> lServicos) {
        this.idCompra = idCompra;
        this.cliente = cliente;
        this.idCliente = idCliente;
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

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setCliente(Integer idCliente, Cliente cliente) {
        this.idCliente = idCliente;
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Produto> getlProdutos() {
        return lProdutos;
    }

    public ArrayList<Servico> getlServicos() {
        return lServicos;
    }

    public void addProduto(Produto produto) {
        lProdutos.add(produto);
        alteraPreco();
    }

    public void addServico(Servico servico) {
        lServicos.add(servico);
        alteraPreco();
    }

    public void limparProdutos() {
        lProdutos = new ArrayList();
        alteraPreco();
    }

    public void limparServicos() {
        lServicos = new ArrayList();
        alteraPreco();
    }

    public Object[] dadosTabela() {
        String idProdutos, idServicos;

        idProdutos = "";
        for (Produto p : lProdutos) {
            idProdutos += String.format("%d ", p.getIdProduto());
        }

        idServicos = "";
        for (Servico s : lServicos) {
            idServicos += String.format("%d ", s.getIdServico());
        }
        return new Object[]{idCompra, precoTotal, cliente.getIdCliente(), cliente.getNome(), idProdutos, idServicos};
    }
}
