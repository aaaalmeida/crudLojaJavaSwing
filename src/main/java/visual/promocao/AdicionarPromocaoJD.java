/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package visual.promocao;

import DAOImplementation.PromocaoDAOImpl;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import models.Promocao;

/**
 *
 * @author arthu
 */
public class AdicionarPromocaoJD extends javax.swing.JDialog {

    private PromocaoDAOImpl promocaoDAOImpl;

    public AdicionarPromocaoJD(java.awt.Frame parent, boolean modal, PromocaoDAOImpl promocaoDAOImpl) {
        super(parent, modal);
        this.promocaoDAOImpl = promocaoDAOImpl;
        initComponents();
        setTitle("Adicionar Promoção");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        valPercentTF = new javax.swing.JTextField();
        adicionarPromocaoBTN = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        valFixoTF = new javax.swing.JTextField();
        sair = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dataTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        horaTF = new javax.swing.JTextField();
        resposta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar Funcionário");

        adicionarPromocaoBTN.setText("Adicionar");
        adicionarPromocaoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarPromocaoBTNActionPerformed(evt);
            }
        });

        jLabel1.setText("Valor Percentual");

        jLabel5.setText("Valor Fixo");

        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        jLabel6.setText("Digite todos os campos da promocao e aperte Adicionar");

        jLabel2.setText("Data (DD/MM/AAAA)");

        jLabel3.setText("Hora (HH:MM)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adicionarPromocaoBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(valFixoTF, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(valPercentTF)
                            .addComponent(jLabel2)
                            .addComponent(dataTF)
                            .addComponent(horaTF)
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
                .addComponent(valFixoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valPercentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(horaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(resposta)
                .addGap(81, 81, 81)
                .addComponent(adicionarPromocaoBTN)
                .addGap(12, 12, 12)
                .addComponent(sair)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarPromocaoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarPromocaoBTNActionPerformed
        DateTimeFormatter formatterDT = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate data = LocalDate.parse(dataTF.getText(), formatterDT);
        LocalTime hora = LocalTime.parse(horaTF.getText());

        Promocao p = new Promocao(null, Double.valueOf(valPercentTF.getText()), Double.valueOf(valFixoTF.getText()), data, hora);

        if (promocaoDAOImpl.adicionar(p)) {
            JOptionPane.showMessageDialog(null, "Promoção cadastrada");
            resposta.setText(String.format("Promoção criada com ID: %d", p.getIdPromocao()));
        } else {
            JOptionPane.showMessageDialog(null, "Promoção não cadastrada");
            resposta.setText(null);
        }
        valFixoTF.setText(null);
        valPercentTF.setText(null);
        dataTF.setText(null);
        horaTF.setText(null);
    }//GEN-LAST:event_adicionarPromocaoBTNActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        dispose();
    }//GEN-LAST:event_sairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarPromocaoBTN;
    private javax.swing.JTextField dataTF;
    private javax.swing.JTextField horaTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel resposta;
    private javax.swing.JButton sair;
    private javax.swing.JTextField valFixoTF;
    private javax.swing.JTextField valPercentTF;
    // End of variables declaration//GEN-END:variables
}
