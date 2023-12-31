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
public abstract class Item {

    private String nome;
    private Double preco;
    private Integer idPromocao;
    private Promocao promocao;

    public Item(String nome, Double preco, Integer idPromocao, Promocao promocao) {
        this.nome = nome;
        this.preco = preco;
        this.promocao = promocao;

        if (!Objects.isNull(promocao)) {
            this.idPromocao = idPromocao;
        } else {
            this.idPromocao = null;
        }

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
                    preco = preco - (preco * promocao.getPorcDesconto() / 100);
                }
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
        descontoPreco();
    }

    public Integer getIdPromocao() {
        return idPromocao;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Integer idPromocao, Promocao promocao) {
        this.idPromocao = idPromocao;
        this.promocao = promocao;
        descontoPreco();
    }

}
