/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author arthu
 */
public class Promocao {

    private Integer idPromocao;
    private Double porcDesconto;
    private Double valorDesconto;
    private LocalDate data;
    private LocalTime hora;

    public Promocao(Integer idPromocao, Double porcDesconto, Double valorDesconto, LocalDate data, LocalTime hora) {
        this.idPromocao = idPromocao;
        this.porcDesconto = porcDesconto;
        this.valorDesconto = valorDesconto;
        this.data = data;
        this.hora = hora;
    }

    public Integer getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(Integer idPromocao) {
        this.idPromocao = idPromocao;
    }

    public Double getPorcDesconto() {
        return porcDesconto;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public LocalDate getData() {
        return data;
    }

    public void setPorcDesconto(Double porcDesconto) {
        this.porcDesconto = porcDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
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

    public Object[] dadosTabela() {
        return new Object[]{idPromocao, valorDesconto, porcDesconto, data.toString(), hora.toString()};
    }
}
