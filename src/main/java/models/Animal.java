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
public class Animal {

    private Integer idAnimal;
    private Cliente dono;
    private Integer idDono;
    private String nome;
    private String especie;

    public Animal(Integer idAnimal, Integer idDono, Cliente dono, String nome, String especie) {
        this.idAnimal = idAnimal;
        this.dono = dono;
        this.nome = nome;
        this.especie = especie;
        this.idDono = idDono;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Cliente getDono() {
        return dono;
    }

    public Integer getIdDono() {
        return idDono;
    }

    public void setDono(Integer idDono, Cliente dono) {
        this.idDono = idDono;
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Object[] dadosTabela() {
        if (Objects.isNull(dono)) {
            return new Object[]{idAnimal, nome, especie, "", ""};
        }
        return new Object[]{idAnimal, nome, especie, dono.getIdCliente(), dono.getNome()};
    }
}
