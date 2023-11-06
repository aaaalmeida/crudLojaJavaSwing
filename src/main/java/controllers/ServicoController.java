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
import models.Animal;
import models.Cliente;
import models.Promocao;
import models.Servico;

/**
 *
 * @author arthu
 */
public class ServicoController implements Controller<Servico> {

    private HashMap servicos;

    public ServicoController() {
        this.servicos = new HashMap<Integer, Servico>();
    }

    @Override
    public Boolean adicionar(Servico obj) {
        if (!servicos.containsKey(obj.getIdServico())) {
            servicos.put(obj.getIdServico(), obj);
            return true;
        }

        System.err.println("ERRO: SERVICO JA CADASTRADO");
        return false;
    }

    @Override
    public Servico consulta(Integer id) {
        if (servicos.containsKey(id)) {
            return (Servico) servicos.get(id);
        }

        System.err.println("ERRO: PROMOCAO NAO ENCONTRADO");
        return null;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (servicos.containsKey(id)) {
                Servico s = (Servico) servicos.get(id);
                s.setNomeServico((String) args[0]);
                s.setPreco(Double.valueOf((String) args[1]));
                s.setData((LocalDate) args[2]);
                s.setHora((LocalTime) args[3]);
                s.setAnimal((Animal) args[4]);
                s.setCliente((Cliente) args[5]);
                s.setPromocao((Promocao) args[6]);
                return true;
            }
        }

        System.err.println(String.format("ERRO: SERVICO %d NAO ALTERADO", id));
        return false;
    }

    @Override
    public Servico remove(Integer id) {
        return (Servico) servicos.remove(id);
    }

    @Override
    public HashMap<Integer, Servico> relatorio() {
        return this.servicos;
    }
}
