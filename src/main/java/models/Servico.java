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

    public Servico(Integer idServico, LocalDate data, LocalTime hora, Integer idAnimal, Integer idCliente, String nome, Double preco, Integer idPromocao) {
        super(nome, preco, idPromocao);
        this.idServico = idServico;
        this.data = data;
        this.hora = hora;
        this.idAnimal = idAnimal;
        this.idCliente = idCliente;
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

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    /*
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
     */
}
