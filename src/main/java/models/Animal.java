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
    private static Integer cont = 0;
    private Cliente dono;
    private String nome;
    private String especie;

    public Animal(Cliente dono, String nome, String especie) {
        this.idAnimal = cont;
        cont++;
        this.dono = dono;
        this.nome = nome;
        this.especie = especie;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
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
