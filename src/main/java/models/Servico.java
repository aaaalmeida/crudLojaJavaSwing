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
public class Servico extends Item {

    private Integer idServico;
    private LocalDate data;
    private LocalTime hora;
    private Integer idAnimal;
    private Integer idCliente;
    private Animal animal;
    private Cliente cliente;

    public Servico(Integer idServico, LocalDate data, LocalTime hora, Integer idAnimal, Animal animal, Integer idCliente, Cliente cliente, String nome, Double preco, Integer idPromocao, Promocao promocao) {
        super(nome, preco, idPromocao, promocao);
        this.idServico = idServico;
        this.data = data;
        this.hora = hora;
        this.cliente = cliente;
        this.animal = animal;
        this.idAnimal = idAnimal;
        this.idCliente = idCliente;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdServico() {
        return idServico;
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

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Integer idAnimal, Animal animal) {
        this.idAnimal = idAnimal;
        this.animal = animal;
    }

    public void setCliente(Integer idCliente, Cliente cliente) {
        this.idCliente = idCliente;
        this.cliente = cliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Object[] dadosTabela() {
        String cId = "", cNome = "";
        if (!Objects.isNull(cliente)) {
            cId = String.valueOf(cliente.getIdCliente());
            cNome = cliente.getNome();
        }

        String pId = "";
        if (!Objects.isNull(getPromocao())) {
            pId = String.valueOf(getPromocao().getIdPromocao());
        }

        String aId = "", aNome = "";
        if (!Objects.isNull(animal)) {
            aId = String.valueOf(animal.getIdAnimal());
            aNome = animal.getNome();
        }

        return new Object[]{idServico, getNome(), getPreco(), cId, cNome, data.toString(),
            hora.toString(), pId, aId, aNome};
    }
}
