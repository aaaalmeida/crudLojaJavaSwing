/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package visual.compra;

import controllers.ClienteController;
import controllers.CompraController;
import controllers.ProdutoController;
import controllers.ServicoController;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Cliente;
import models.Compra;
import models.Produto;
import models.Servico;

/**
 *
 * @author arthu
 */
public class AdicionarCompraJD extends javax.swing.JDialog {

    private CompraController cc;
    private ServicoController sc;
    private ProdutoController pc;
    private ClienteController clientec;

    public AdicionarCompraJD(java.awt.Frame parent, boolean modal, CompraController cc, ServicoController sc, ClienteController clientec, ProdutoController pc) {
        super(parent, modal);
        this.cc = cc;
        this.sc = sc;
        this.clientec = clientec;
        this.pc = pc;
        initComponents();
        setTitle("Adicionar Compra");

        Integer[] clientesKeys = clientec.relatorio().keySet().toArray(new Integer[0]);
        String[] clienteList = new String[clientesKeys.length];
        for (int i = 0; i < clientesKeys.length; i++) {
            clienteList[i] = String.valueOf(clientesKeys[i]);
        }
        clienteJL.setListData(clienteList);

        Integer[] produtosKeys = pc.relatorio().keySet().toArray(new Integer[0]);
        String[] produtosList = new String[produtosKeys.length];
        for (int i = 0; i < produtosKeys.length; i++) {
            produtosList[i] = String.valueOf(produtosKeys[i]);
        }
        produtosJL.setListData(produtosList);

        Integer[] servicosKeys = sc.relatorio().keySet().toArray(new Integer[0]);
        String[] servicosList = new String[servicosKeys.length];
        for (int i = 0; i < servicosKeys.length; i++) {
            servicosList[i] = String.valueOf(servicosKeys[i]);
        }
        servicosJL.setListData(servicosList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adicionarCompraBTN = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clienteJL = new javax.swing.JList<>();
        sair = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        produtosJL = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        servicosJL = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        resposta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar Funcionário");

        adicionarCompraBTN.setText("Adicionar");
        adicionarCompraBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarCompraBTNActionPerformed(evt);
            }
        });

        jLabel6.setText("Selecione todos os campos e aperte em Adicionar");

        jLabel2.setText("Cliente");

        clienteJL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(clienteJL);

        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(produtosJL);

        jScrollPane3.setViewportView(servicosJL);

        jLabel3.setText("Produtos");

        jLabel12.setText("Serviços");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(adicionarCompraBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addComponent(resposta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addGap(18, 34, Short.MAX_VALUE)
                .addComponent(resposta)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adicionarCompraBTN)
                    .addComponent(sair))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarCompraBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarCompraBTNActionPerformed
        Cliente cliente = clientec.consulta(Integer.valueOf(clienteJL.getSelectedValue()));

        ArrayList<Produto> produtos = new ArrayList();
        for (String id : produtosJL.getSelectedValuesList()) {
            produtos.add(pc.consulta(Integer.valueOf(id)));
        }

        ArrayList<Servico> servicos = new ArrayList();
        for (String id : servicosJL.getSelectedValuesList()) {
            servicos.add(sc.consulta(Integer.valueOf(id)));
        }

        Compra compra = new Compra(cliente, produtos, servicos);

        if (!cc.adicionar(compra)) {
            resposta.setText(null);
            JOptionPane.showMessageDialog(null, "Compra não cadastrado");
        } else
            resposta.setText(String.format("Compra cadastrada com ID: %d", compra.getIdCompra()));
    }//GEN-LAST:event_adicionarCompraBTNActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        dispose();
    }//GEN-LAST:event_sairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarCompraBTN;
    private javax.swing.JList<String> clienteJL;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> produtosJL;
    private javax.swing.JLabel resposta;
    private javax.swing.JButton sair;
    private javax.swing.JList<String> servicosJL;
    // End of variables declaration//GEN-END:variables
}
