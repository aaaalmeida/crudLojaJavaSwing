/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Objects;

/**
 *
 * @author arthu
 */
public class Produto extends Item {

    private Integer idProduto;
    private String descricao;
    private Promocao promocao;

    public Produto(Integer idProduto, String nome, Double preco, Promocao promocao, String descricao) {
        super(nome, preco, promocao);
        this.idProduto = idProduto;
        this.descricao = descricao;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Object[] dadosTabela() {
        if (Objects.isNull(promocao)) {
            return new Object[]{idProduto, getNome(), getPreco(), descricao, ""};
        }

        return new Object[]{idProduto, getNome(), getPreco(), descricao, promocao.getIdPromocao()};
    }
}
