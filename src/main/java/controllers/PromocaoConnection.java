/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import DAOImplementation.PromocaoDAOImpl;
import models.Item;

/**
 *
 * @author arthu
 */
public class PromocaoConnection {

    private PromocaoDAOImpl promocaoDAOImpl;

    public PromocaoConnection(PromocaoDAOImpl promocaoDAOImpl) {
        this.promocaoDAOImpl = promocaoDAOImpl;
    }

    public void alterarPromocao(Item item, Integer idProm) {
        item.setPromocao(promocaoDAOImpl.consultaPorId(idProm));
        item.descontoPreco();
    }
}
