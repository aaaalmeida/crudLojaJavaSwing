/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package visual.compra;

import DAOImplementation.CompraDAOImpl;

import java.util.ArrayList;
import java.util.HashMap;
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

    private CompraDAOImpl compraDAOImpl;
    private HashMap<Integer, Servico> lServicos;
    private HashMap<Integer, Cliente> lClientes;
    private HashMap<Integer, Produto> lProdutos;

    public AdicionarCompraJD(java.awt.Frame parent, boolean modal, CompraDAOImpl compraDAOImpl, HashMap<Integer, Servico> lServicos, HashMap<Integer, Cliente> lClientes, HashMap<Integer, Produto> lProdutos) {
        super(parent, modal);
        this.compraDAOImpl = compraDAOImpl;
        this.lServicos = lServicos;
        this.lClientes = lClientes;
        this.lProdutos = lProdutos;
        initComponents();
        setTitle("Adicionar Compra");

        Integer[] clientesKeys = lClientes.keySet().toArray(new Integer[0]);
        String[] clienteList = new String[clientesKeys.length];
        for (int i = 0; i < clientesKeys.length; i++) {
            clienteList[i] = String.valueOf(clientesKeys[i]);
        }
        clienteJL.setListData(clienteList);
        clienteJL.setSelectedIndex(-1);

        Integer[] produtosKeys = lProdutos.keySet().toArray(new Integer[0]);
        String[] produtosList = new String[produtosKeys.length];
        for (int i = 0; i < produtosKeys.length; i++) {
            produtosList[i] = String.valueOf(produtosKeys[i]);
        }
        produtosJL.setListData(produtosList);
        produtosJL.setSelectedIndex(-1);
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
        clienteJL.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                clienteJLValueChanged(evt);
            }
        });
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
        if (clienteJL.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Cliente não pode ser nulo");
            return;
        }
        Cliente cliente = lClientes.get(Integer.valueOf(clienteJL.getSelectedValue()));

        ArrayList<Produto> produtos = new ArrayList();
        for (String id : produtosJL.getSelectedValuesList()) {
            produtos.add(lProdutos.get(Integer.valueOf(id)));
        }

        ArrayList<Servico> servicos = new ArrayList();
        for (String id : servicosJL.getSelectedValuesList()) {
            servicos.add(lServicos.get(Integer.valueOf(id)));
        }

        Compra compra = new Compra(null, cliente.getIdCliente(), cliente, produtos, servicos);

        if (compraDAOImpl.adicionar(compra)) {
            resposta.setText(String.format("Compra cadastrada com ID: %d", compra.getIdCompra()));
            JOptionPane.showMessageDialog(null, "Compra cadastrado");
        } else {
            resposta.setText(null);
            JOptionPane.showMessageDialog(null, "Compra não cadastrado");
        }

        clienteJL.setSelectedIndex(-1);
        produtosJL.setSelectedIndex(-1);
        servicosJL.setSelectedIndex(-1);
    }//GEN-LAST:event_adicionarCompraBTNActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        dispose();
    }//GEN-LAST:event_sairActionPerformed

    private void clienteJLValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_clienteJLValueChanged
        Cliente c = lClientes.get(Integer.valueOf(clienteJL.getSelectedValue()));
        servicosJL.setListData(c.infoServicos());
        servicosJL.setSelectedIndex(-1);
    }//GEN-LAST:event_clienteJLValueChanged

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
