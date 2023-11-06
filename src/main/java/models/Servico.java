/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author arthu
 */
public class Servico {

    private Integer idServico;
    private static Integer cont = 0;
    private String nomeServico;
    private Double preco;
    private LocalDate data;
    private LocalTime hora;
    private Animal animal;
    private Cliente cliente;
    private Promocao promocao;

    public Servico(String nomeServico, Double _preco, LocalDate data, LocalTime hora, Animal animal, Cliente cliente, Promocao promocao) {
        this.idServico = cont;
        cont++;
        this.nomeServico = nomeServico;
        this.data = data;
        this.hora = hora;
        this.animal = animal;
        this.cliente = cliente;
        this.promocao = promocao;

        this.preco = _preco;
        descontoPreco();
    }

    private void descontoPreco() {
        if (!Objects.isNull(promocao)) {
            if (!Objects.isNull(promocao.getValorDesconto())) {
                if (promocao.getValorDesconto() > preco) {
                    System.err.println("DESCONTO FIXO INVALIDO");
                } else {
                    preco -= promocao.getValorDesconto();
                }
            }
            if (!Objects.isNull(promocao.getPorcDesconto())) {
                if (promocao.getPorcDesconto() > 100 || promocao.getPorcDesconto() < 0) {
                    System.err.println("DESCONTO PORCENTAGEM INVALIDO");
                } else {
                    preco = preco - (preco * promocao.getPorcDesconto()/100);
                }
            }
        }
    }

    public Integer getIdServico() {
        return idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
        descontoPreco();
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public Object[] dadosTabela() {
        String cId = "", cNome = "";
        if (!Objects.isNull(cliente)) {
            cId = String.valueOf(cliente.getIdCliente());
            cNome = cliente.getNome();
        }

        String pId = "";
        if (!Objects.isNull(promocao)) {
            pId = String.valueOf(promocao.getIdPromocao());
        }

        String aId = "", aNome = "";
        if (!Objects.isNull(animal)) {
            aId = String.valueOf(animal.getIdAnimal());
            aNome = animal.getNome();
        }

        return new Object[]{idServico, nomeServico, preco, cId, cNome, data.toString(),
            hora.toString(), pId, aId, aNome};
    }
}
