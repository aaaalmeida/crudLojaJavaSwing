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

    public Integer getIdCompra() {
        return idCompra;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public Integer getIdCliente() {
        return idCliente;
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

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setKeysProdutos(ArrayList<Integer> keysProdutos) {
        this.keysProdutos = keysProdutos;
    }

    public void setKeysServicos(ArrayList<Integer> keysServicos) {
        this.keysServicos = keysServicos;
    }

    public void addProduto(Produto produto) {
        lProdutos.add(produto);
    }
    
    public void addServico(Servico servico) {
        lServicos.add(servico);
    }
    
    public void limparProdutos() {
        lProdutos = new ArrayList();
    }
    
    public void limparServicos() {
        lServicos = new ArrayList();
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
