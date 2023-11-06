package controllers;

import interfaces.Controller;
import java.util.HashMap;
import java.util.Objects;
import models.Animal;
import models.Cliente;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author arthu
 */
public class AnimalController implements Controller<Animal> {

    private HashMap animais;

    public AnimalController() {
        this.animais = new HashMap<Integer, Animal>();
    }

    @Override
    public Boolean adicionar(Animal obj) {
        if (!animais.containsKey(obj.getIdAnimal())) {
            animais.put(obj.getIdAnimal(), obj);
            return true;
        }

        System.err.println("ANIMAL JA CADASTRADO");
        return false;
    }

    @Override
    public Animal consulta(Integer id) {
        if (animais.containsKey(id)) {
            return (Animal) animais.get(id);
        }

        System.err.println("ANIMAL NAO ENCONTRADO");
        return null;
    }

    @Override
    public Animal remove(Integer id) {
        if (animais.containsKey(id)) {
            Animal a = (Animal) animais.get(id);
            a.getDono().removeAnimal(a);
        }

        return (Animal) animais.remove(id);
    }

    @Override
    public HashMap<Integer, Animal> relatorio() {
        return this.animais;
    }

    @Override
    public Boolean altera(Integer id, Object[] args) {
        if (!Objects.isNull(id)) {
            if (animais.containsKey(id)) {
                Animal a = (Animal) animais.get(id);
                a.setNome(String.valueOf(args[0]));
                a.setEspecie(String.valueOf(args[1]));
                if (!Objects.isNull(a.getDono())) {
                    a.getDono().removeAnimal(a);
                }
                a.setDono((Cliente) args[2]);
                return true;
            }
        }

        System.err.println(String.format("ERRO: ANIMAL %d NAO ALTERADO", id));
        return false;
    }

}
