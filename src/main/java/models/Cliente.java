/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author arthu
 */
public class Cliente extends Pessoa {

    private Integer idCliente;
    private ArrayList<Animal> lAnimais;
    private ArrayList<Compra> lCompra;
    private ArrayList<Servico> lServico;

    public Cliente(Integer idCliente, String nome, String cpf) {
        super(nome, cpf);
        this.idCliente = idCliente;
        this.lAnimais = new ArrayList();
        this.lCompra = new ArrayList();
        this.lServico = new ArrayList();
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String[] infoAnimal() {
        String[] retorno = new String[lAnimais.size()];
        for (int i = 0; i < lAnimais.size(); i++) {
            retorno[i] = String.valueOf(lAnimais.get(i).getIdAnimal());
        }
        return retorno;
    }

    public String[] infoCompras() {
        String[] retorno = new String[lCompra.size()];
        for (int i = 0; i < lCompra.size(); i++) {
            retorno[i] = String.valueOf(lCompra.get(i).getIdCompra());
        }
        return retorno;
    }

    public String[] infoServicos() {
        String[] retorno = new String[lServico.size()];
        for (int i = 0; i < lServico.size(); i++) {
            retorno[i] = String.valueOf(lServico.get(i).getIdServico());
        }
        return retorno;
    }

    public void addAnimal(Animal animal) {
        if (!lAnimais.contains(animal)) {
            lAnimais.add(animal);
        }
    }

    public void removeAnimal(Animal animal) {
        lAnimais.remove(animal);
    }

    public ArrayList<Animal> getlAnimais() {
        return lAnimais;
    }

    public void addCompra(Compra compra) {
        if (!lCompra.contains(compra)) {
            lCompra.add(compra);
        }
    }

    public void removeCompra(Compra compra) {
        lCompra.remove(compra);
    }

    public void addServico(Servico servico) {
        if (!lServico.contains(servico)) {
            lServico.add(servico);
        }
    }

    public void removeServico(Servico servico) {
        lServico.remove(servico);
    }

    public Object[] dadosTabela() {
        String idAnimais, idCompras, idServicos;

        idAnimais = new String("");
        for (Animal a : lAnimais) {
            idAnimais += String.format("%d ", a.getIdAnimal());
        }

        idCompras = new String("");
        for (Compra c : lCompra) {
            idCompras += String.format("%d ", c.getIdCompra());
        }

        idServicos = new String("");
        for (Servico s : lServico) {
            idServicos += String.format("%d ", s.getIdServico());
        }

        return new Object[]{idCliente, getNome(), getCpf(), idAnimais, idCompras, idServicos};
    }
}
