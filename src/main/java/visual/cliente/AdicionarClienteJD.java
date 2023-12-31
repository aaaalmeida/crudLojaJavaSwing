/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package visual.cliente;

import DAOImplementation.ClienteDAOImpl;
import javax.swing.JOptionPane;
import models.Cliente;

/**
 *
 * @author arthu
 */
public class AdicionarClienteJD extends javax.swing.JDialog {

    private ClienteDAOImpl clienteDAOImpl;

    public AdicionarClienteJD(java.awt.Frame parent, boolean modal, ClienteDAOImpl clienteDAOImpl) {
        super(parent, modal);
        initComponents();
        this.clienteDAOImpl = clienteDAOImpl;
        setTitle("Adicionar Cliente");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cpfTF = new javax.swing.JTextField();
        adicionarClienteBTN = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nomeTF = new javax.swing.JTextField();
        sair = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        resposta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar Funcionário");

        adicionarClienteBTN.setText("Adicionar");
        adicionarClienteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarClienteBTNActionPerformed(evt);
            }
        });

        jLabel1.setText("CPF");

        jLabel5.setText("Nome");

        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        jLabel6.setText("Digite todos os campos do cliente e aperte Adicionar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adicionarClienteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(nomeTF, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(cpfTF)
                            .addComponent(resposta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cpfTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(resposta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196)
                .addComponent(adicionarClienteBTN)
                .addGap(12, 12, 12)
                .addComponent(sair)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarClienteBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarClienteBTNActionPerformed
        Cliente c = new Cliente(null, nomeTF.getText(), cpfTF.getText());

        if (c.getCpf().trim().equals("") || c.getNome().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Digite todos os campos");
            return;
        }

        if (clienteDAOImpl.adicionar(c)) {
            resposta.setText(String.format("Cliente cadastrado com ID: %d", c.getIdCliente()));
            nomeTF.setText(null);
            cpfTF.setText(null);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado");
        } else {
            resposta.setText(null);
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
        }
    }//GEN-LAST:event_adicionarClienteBTNActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        dispose();
    }//GEN-LAST:event_sairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarClienteBTN;
    private javax.swing.JTextField cpfTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField nomeTF;
    private javax.swing.JLabel resposta;
    private javax.swing.JButton sair;
    // End of variables declaration//GEN-END:variables
}
