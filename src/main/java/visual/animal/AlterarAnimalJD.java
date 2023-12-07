/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package visual.animal;

import DAOImplementation.AnimalDAOImpl;
import java.util.HashMap;

import java.util.Objects;
import javax.swing.JOptionPane;
import models.Animal;
import models.Cliente;

/**
 *
 * @author arthu
 */
public class AlterarAnimalJD extends javax.swing.JDialog {

    private AnimalDAOImpl animalDAOImpl;
    private HashMap<Integer, Cliente> lDonos;

    public AlterarAnimalJD(java.awt.Frame parent, boolean modal, AnimalDAOImpl animalDAOImpl, HashMap<Integer, Cliente> lDonos) {
        super(parent, modal);
        this.animalDAOImpl = animalDAOImpl;
        this.lDonos = lDonos;
        initComponents();
        setTitle("Alterar Animal");

        Integer[] donosKeys = lDonos.keySet().toArray(new Integer[0]);
        String[] listData = new String[donosKeys.length];
        for (int i = 0; i < donosKeys.length; i++) {
            listData[i] = String.valueOf(donosKeys[i]);
        }
        donoJL.setListData(listData);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomeTF = new javax.swing.JTextField();
        alterarAnimalBTN = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        idTF = new javax.swing.JTextField();
        sair = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        donoJL = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        especieTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar Funcionário");

        alterarAnimalBTN.setText("Alterar");
        alterarAnimalBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarAnimalBTNActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome");

        jLabel5.setText("ID");

        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        jLabel6.setText("Digite o ID do Animal e as informações e aperte em alterar");

        donoJL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(donoJL);

        jLabel2.setText("Dono");

        jLabel3.setText("Espécie");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(especieTF, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(alterarAnimalBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addComponent(idTF, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(nomeTF))
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
                .addComponent(idTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nomeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(especieTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alterarAnimalBTN)
                    .addComponent(sair))
                .addGap(66, 66, 66))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void alterarAnimalBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarAnimalBTNActionPerformed
        Cliente dono = null;
        Animal animal = animalDAOImpl.consultaPorId(Integer.valueOf(idTF.getText()));

        if (!Objects.isNull(animal.getDono())) {
            animal.getDono().removeAnimal(animal);
        }

        if (!donoJL.isSelectionEmpty()) {
            dono = lDonos.get(Integer.valueOf(donoJL.getSelectedValue()));
            if (!Objects.isNull(dono)) {
                dono.addAnimal(animal);
            }
        }

        Object[] args = new Object[]{nomeTF.getText(), especieTF.getText(), Integer.valueOf(donoJL.getSelectedValue())};

        if (!animalDAOImpl.altera(Integer.valueOf(idTF.getText()), args)) {
            JOptionPane.showMessageDialog(null, "Animal não alterado");
        } else {
            JOptionPane.showMessageDialog(null, "Animal alterado");
        }

        idTF.setText(null);
        nomeTF.setText(null);
        especieTF.setText(null);
        donoJL.setSelectedIndex(-1);
    }//GEN-LAST:event_alterarAnimalBTNActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        dispose();
    }//GEN-LAST:event_sairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterarAnimalBTN;
    private javax.swing.JList<String> donoJL;
    private javax.swing.JTextField especieTF;
    private javax.swing.JTextField idTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nomeTF;
    private javax.swing.JButton sair;
    // End of variables declaration//GEN-END:variables
}
