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
    private ArrayList<Integer> keysProdutos;
    private ArrayList<Integer> keysServicos;
    private ArrayList<Produto> lProdutos;
    private ArrayList<Servico> lServicos;

    public Compra(Integer idCompra, Integer idCliente) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;

        this.keysProdutos = new ArrayList<>();
        this.keysServicos = new ArrayList<>();

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

    public ArrayList<Integer> getKeysProdutos() {
        return keysProdutos;
    }

    public ArrayList<Integer> getKeysServicos() {
        return keysServicos;
    }

    public ArrayList<Produto> getlProdutos() {
        return lProdutos;
    }

    public ArrayList<Servico> getlServicos() {
        return lServicos;
    }

    public void addProduto(Integer idProduto, Produto produto) {
        keysProdutos.add(idProduto);
        lProdutos.add(produto);
        alteraPreco();
    }

    public void addServico(Integer idServico, Servico servico) {
        keysServicos.add(idServico);
        lServicos.add(servico);
        alteraPreco();
    }

    public void limparProdutos() {
        keysProdutos = new ArrayList();
        lProdutos = new ArrayList();
        alteraPreco();
    }

    public void limparServicos() {
        keysServicos = new ArrayList();
        lServicos = new ArrayList();
        alteraPreco();
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
