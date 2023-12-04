/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.Compra;
import DAOImplementation.ProdutoDAOImpl;
import DAOImplementation.ServicoDAOImpl;
/**
 *
 * @author arthu
 */
public class CompraConnection {
    
    private ProdutoDAOImpl produtoDAOImpl;
    private ServicoDAOImpl servicoDAOImpl;

    public CompraConnection(ProdutoDAOImpl produtoDAOImpl, ServicoDAOImpl servicoDAOImpl) {
        this.produtoDAOImpl = produtoDAOImpl;
        this.servicoDAOImpl = servicoDAOImpl;
    }
    
    public void alterarProdutos(Compra compra) {
        compra.limparProdutos();
        for(Integer idProduto : compra.getKeysProdutos()) {
            compra.addProduto(produtoDAOImpl.consultaPorId(idProduto));
        }
    }
    
    public void alterarServicos(Compra compra) {
        compra.limparServicos();
        for(Integer idServico : compra.getKeysServicos()) {
            compra.addServico(servicoDAOImpl.consultaPorId(idServico));
        }
    }
}
