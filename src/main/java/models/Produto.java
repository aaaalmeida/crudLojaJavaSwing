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
public class Produto {

    private Integer idProduto;
    private static Integer cont = 0;
    private String nomeProduto;
    private String descricao;
    private Double preco;
    private Promocao promocao;

    public Produto(String nomeProduto, String descricao, Double preco, Promocao promocao) {
        this.idProduto = cont;
        cont++;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.promocao = promocao;
        this.preco = preco;

        descontoPreco();
    }

    private void descontoPreco() {
        if (!Objects.isNull(promocao)) {
            if (!Objects.isNull(promocao.getValorDesconto())) {
                if (promocao.getValorDesconto() > preco) {
                    System.err.println("ERRO: DESCONTO FIXO INVALIDO");
                } else {
                    preco -= promocao.getValorDesconto();
                }
            }
            if (!Objects.isNull(promocao.getPorcDesconto())) {
                if (promocao.getPorcDesconto() > 100 || promocao.getPorcDesconto() < 0) {
                    System.err.println("ERRO: DESCONTO PORCENTAGEM INVALIDO");
                } else {
                    preco = preco - (preco * promocao.getPorcDesconto()/100);
                }
            }
        }
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
        descontoPreco();
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public Object[] dadosTabela() {
        if (Objects.isNull(promocao)) {
            return new Object[]{idProduto, nomeProduto, preco, descricao, ""};
        }

        return new Object[]{idProduto, nomeProduto, preco, descricao, promocao.getIdPromocao()};
    }
}
