/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import interfaces.Controller;
import java.util.HashMap;
import java.util.Objects;
import models.Produto;
import models.Promocao;

/**
 *
 * @author arthu
 */
public class ProdutoController implements Controller<Produto> {

    private HashMap produtos;

    public ProdutoController() {
        this.produtos = new HashMap<Integer, Produto>();
    }

    @Override
    public Boolean adicionar(Produto obj) {
        if (!produtos.containsKey(obj.getIdProduto())) {
            produtos.put(obj.getIdProduto(), obj);
            return true;
        }

        System.err.println("ERRO: PRODUTO JA CADASTRADO");
        return false;
    }

    @Override
    public Produto consulta(Integer id) {
        if (produtos.containsKey(id)) {
            return (Produto) produtos.get(id);
        }

        System.err.println("ERRO: PRODUTOS NAO ENCONTRADO");
        return null;
    }

    @Override
    public Produto remove(Integer id) {
        return (Produto) produtos.remove(id);
    }

    @Override
    public HashMap<Integer, Produto> relatorio() {
        return this.produtos;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (produtos.containsKey(id)) {
                Produto p = (Produto) produtos.get(id);
                p.setNomeProduto(String.valueOf(args[0]));
                p.setDescricao(String.valueOf(args[1]));
                p.setPromocao((Promocao) args[2]);
                p.setPreco(Double.valueOf((String) args[3]));
                return true;
            }
        }

        System.err.println(String.format("ERRO: ANIMAL %d NAO ALTERADO", id));
        return false;
    }

}
