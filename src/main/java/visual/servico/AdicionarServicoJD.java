/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package visual.servico;

import controllers.AnimalController;
import controllers.ClienteController;
import controllers.PromocaoController;
import controllers.ServicoController;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import models.Animal;
import models.Cliente;
import models.Promocao;
import models.Servico;

/**
 *
 * @author arthu
 */
public class AdicionarServicoJD extends javax.swing.JDialog {

    private ServicoController sc;
    private AnimalController ac;
    private PromocaoController promc;
    private ClienteController clientec;

    public AdicionarServicoJD(java.awt.Frame parent, boolean modal, ServicoController sc, AnimalController ac, PromocaoController promc, ClienteController clientec) {
        super(parent, modal);
        this.sc = sc;
        this.ac = ac;
        this.promc = promc;
        this.clientec = clientec;
        initComponents();
        setTitle("Adicionar Serviço");

        Integer[] clientesKeys = clientec.relatorio().keySet().toArray(new Integer[0]);
        String[] clienteList = new String[clientesKeys.length];
        for (int i = 0; i < clientesKeys.length; i++) {
            clienteList[i] = String.valueOf(clientesKeys[i]);
        }
        clienteJL.setListData(clienteList);

        Integer[] promocoesKeys = promc.relatorio().keySet().toArray(new Integer[0]);
        String[] promocaoList = new String[promocoesKeys.length];
        for (int i = 0; i < promocoesKeys.length; i++) {
            promocaoList[i] = String.valueOf(promocoesKeys[i]);
        }
        promocoesJL.setListData(promocaoList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adicionarServicoBTN = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        precoTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        dataTF = new javax.swing.JTextField();
        horaTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clienteJL = new javax.swing.JList<>();
        nomeServicoTF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        sair = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        promocoesJL = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        animalJL = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        resposta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar Funcionário");

        adicionarServicoBTN.setText("Adicionar");
        adicionarServicoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarServicoBTNActionPerformed(evt);
            }
        });

        jLabel5.setText("Serviço");

        jLabel6.setText("Digite todos os campos e aperte em Adicionar");

        jLabel2.setText("Cliente");

        jLabel7.setText("Data (DD/MM/AAAA)");

        jLabel8.setText("Hora (HH:MM)");

        clienteJL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        clienteJL.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                clienteJLValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(clienteJL);

        jLabel10.setText("Preço");

        sair.setText("Sair");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        promocoesJL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(promocoesJL);

        animalJL.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(animalJL);

        jLabel3.setText("Promoção");

        jLabel12.setText("Animal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(adicionarServicoBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sair, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(nomeServicoTF)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(precoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataTF, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(horaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(resposta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(nomeServicoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(precoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addComponent(resposta)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adicionarServicoBTN)
                    .addComponent(sair))
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarServicoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarServicoBTNActionPerformed
        DateTimeFormatter formatterDT = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate data = LocalDate.parse(dataTF.getText(), formatterDT);

        LocalTime hora = LocalTime.parse(horaTF.getText());

        Animal animal = ac.consulta(Integer.valueOf(animalJL.getSelectedValue()));
        Cliente cliente = clientec.consulta(Integer.valueOf(clienteJL.getSelectedValue()));
        Promocao promocao = null;
        if (!promocoesJL.isSelectionEmpty()) {
            promocao = promc.consulta(Integer.valueOf(promocoesJL.getSelectedValue()));
        }

        Servico s = new Servico(nomeServicoTF.getText(), Double.valueOf(precoTF.getText()), data, hora, animal, cliente, promocao);

        if (!sc.adicionar(s)) {
            JOptionPane.showMessageDialog(null, "Serviço não cadastrado");
            resposta.setText(null);
        } else {
            JOptionPane.showMessageDialog(null, "Serviço cadastrado");
            resposta.setText(String.format("Serviço cadastrado com ID: %d", s.getIdServico()));
            cliente.addServico(s);
        }

        nomeServicoTF.setText(null);
        precoTF.setText(null);
        dataTF.setText(null);
        horaTF.setText(null);
        clienteJL.setSelectedIndex(-1);
        promocoesJL.setSelectedIndex(-1);
        animalJL.setSelectedIndex(-1);
    }//GEN-LAST:event_adicionarServicoBTNActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        dispose();
    }//GEN-LAST:event_sairActionPerformed

    private void clienteJLValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_clienteJLValueChanged
        Cliente c = clientec.consulta(Integer.parseInt(clienteJL.getSelectedValue()));
        animalJL.setListData(c.infoAnimal());
    }//GEN-LAST:event_clienteJLValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarServicoBTN;
    private javax.swing.JList<String> animalJL;
    private javax.swing.JList<String> clienteJL;
    private javax.swing.JTextField dataTF;
    private javax.swing.JTextField horaTF;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField nomeServicoTF;
    private javax.swing.JTextField precoTF;
    private javax.swing.JList<String> promocoesJL;
    private javax.swing.JLabel resposta;
    private javax.swing.JButton sair;
    // End of variables declaration//GEN-END:variables
}
