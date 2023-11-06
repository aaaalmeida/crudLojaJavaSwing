/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import interfaces.Controller;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Objects;
import models.Promocao;

/**
 *
 * @author arthu
 */
public class PromocaoController implements Controller<Promocao> {

    private HashMap promocoes;

    public PromocaoController() {
        this.promocoes = new HashMap<Integer, Promocao>();
    }

    @Override
    public Boolean adicionar(Promocao obj) {
        if (!promocoes.containsKey(obj.getIdPromocao())) {
            promocoes.put(obj.getIdPromocao(), obj);
            return true;
        }

        System.err.println("ERRO: PROMOCAO JA CADASTRADA");
        return false;
    }

    @Override
    public Promocao consulta(Integer id) {
        if (promocoes.containsKey(id)) {
            return (Promocao) promocoes.get(id);
        }

        System.err.println("ERRO: PROMOCAO NAO ENCONTRADA");
        return null;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (promocoes.containsKey(id)) {
                Promocao p = (Promocao) promocoes.get(id);
                p.setValorDesconto(Double.valueOf((String) args[0]));
                p.setPorcDesconto(Double.valueOf((String) args[1]));
                p.setData((LocalDate) args[2]);
                p.setHora((LocalTime) args[3]);
                return true;
            }
        }
        System.err.println(String.format("ERRO: PROMOCAO %d NAO ALTERADA", id));
        return false;
    }

    @Override
    public Promocao remove(Integer id) {
        return (Promocao) promocoes.remove(id);
    }

    @Override
    public HashMap<Integer, Promocao> relatorio() {
        return this.promocoes;
    }
}
