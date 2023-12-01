/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visual;

import controllers.*;

import DAOImplementation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Base64;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import visual.animal.*;
import visual.cliente.*;
import visual.funcionario.*;
import visual.compra.*;
import visual.servico.*;
import visual.promocao.*;
import visual.produto.*;

/**
 *
 * @author arthu
 */
public class Main extends javax.swing.JFrame {

    private FuncionarioController funcCont;
    private ClienteController clieCont;
    private AnimalController animCont;
    private CompraController compCont;
    private ProdutoController prodCont;
    private PromocaoController promCont;
    private ServicoController servCont;

    private Connection connection;

    private String nomeBanco;
    private String url;

    public Main() {
        initComponents();

        nomeBanco = "teste1";
        url = "jdbc:postgresql://localhost:5432/";

        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");

            if (!checkDatabase(connection)) {
                createDatabase(connection);
                url += nomeBanco;
                connection = DriverManager.getConnection(url, "postgres", "postgres");
                createTables(connection);
            } else {
                url += nomeBanco;
                connection = DriverManager.getConnection(url, "postgres", "postgres");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        this.funcCont = new FuncionarioController();
        this.clieCont = new ClienteController();
        this.animCont = new AnimalController();
        this.compCont = new CompraController();
        this.prodCont = new ProdutoController();
        this.promCont = new PromocaoController();
        this.servCont = new ServicoController();
    }

    private boolean checkDatabase(Connection conection) throws SQLException {
        String query = "SELECT 1 FROM pg_database WHERE datname = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nomeBanco);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private void createDatabase(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("CREATE DATABASE " + nomeBanco + ";")) {
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
    }

    private static void createTables(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String funcionarioQuery = "CREATE TABLE IF NOT EXISTS funcionarios "
                + "(idFuncionario SERIAL PRIMARY KEY, nome VARCHAR(50) NOT NULL, "
                + "cpf VARCHAR(11) NOT NULL, usuario VARCHAR(50) NOT NULL, senha VARCHAR(50) NOT NULL);";
        String clienteQuery = "CREATE TABLE IF NOT EXISTS clientes "
                + "(idCliente SERIAL PRIMARY KEY, nome VARCHAR(50) NOT NULL, cpf VARCHAR(11) NOT NULL);";
        String animalQuery = "CREATE TABLE IF NOT EXISTS animais "
                + "(idAnimal SERIAL PRIMARY KEY, nome VARCHAR(50) NOT NULL, especie VARCHAR(50), "
                + "idCliente INT NOT NULL, FOREIGN KEY (idCliente) REFERENCES clientes (idCliente));";
        String promocaoQuery = "CREATE TABLE IF NOT EXISTS promocoes "
                + "(idPromocao SERIAL PRIMARY KEY, data DATE, hora TIME, "
                + "valorFixo NUMERIC(10,2) NOT NULL, valorPorcentagem NUMERIC(10,2) NOT NULL);";
        String servicoQuery = "CREATE TABLE servicos "
                + "(idServico SERIAL PRIMARY KEY, nome VARCHAR(50), "
                + "data DATE, hora TIME, preco NUMERIC(10,2) NOT NULL,"
                + "idCliente INT NOT NULL, idAnimal INT NOT NULL, idPromocao INT, "
                + "FOREIGN KEY (idCliente) REFERENCES clientes (idCliente), "
                + "FOREIGN KEY (idAnimal) REFERENCES animais (idAnimal), "
                + "FOREIGN KEY (idPromocao) REFERENCES promocoes (idPromocao));";
        String produtoQuery = "CREATE TABLE IF NOT EXISTS produtos "
                + "(idProduto SERIAL PRIMARY KEY, nome VARCHAR(50) NOT NULL, "
                + "preco NUMERIC(10,2) NOT NULL, descricao VARCHAR(50), idPromocao INT, "
                + "FOREIGN KEY (idPromocao) REFERENCES promocoes (idPromocao));";
        String compraQuery = "CREATE TABLE IF NOT EXISTS compras "
                + "(idCompra SERIAL PRIMARY KEY, precoTotal NUMERIC(10,2) NOT NULL, idCliente INT NOT NULL, "
                + "FOREIGN KEY (idCliente) REFERENCES clientes (idCliente));";
        String comprasProdutosQuery = "CREATE TABLE IF NOT EXISTS compras_produtos "
                + "(idCompraProduto SERIAL PRIMARY KEY, idCompra INT NOT NULL, idProduto INT NOT NULL, "
                + "FOREIGN KEY (idCompra) REFERENCES compras (idCompra), "
                + "FOREIGN KEY (idProduto) REFERENCES produtos (idProduto));";
        String comprasServicosQuery = "CREATE TABLE IF NOT EXISTS compras_servicos "
                + "(idCompraServico SERIAL PRIMARY KEY, idCompra INT NOT NULL, idServico INT NOT NULL, "
                + "FOREIGN KEY (idCompra) REFERENCES compras (idCompra), "
                + "FOREIGN KEY (idServico) REFERENCES servicos (idServico));";

        statement.executeUpdate(funcionarioQuery);
        statement.executeUpdate(clienteQuery);
        statement.executeUpdate(animalQuery);
        statement.executeUpdate(promocaoQuery);
        statement.executeUpdate(servicoQuery);
        statement.executeUpdate(produtoQuery);
        statement.executeUpdate(compraQuery);
        statement.executeUpdate(comprasProdutosQuery);
        statement.executeUpdate(comprasServicosQuery);

        statement.close();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usuarioTF = new javax.swing.JTextField();
        senhaPF = new javax.swing.JPasswordField();
        acessarBTN = new javax.swing.JButton();
        cadastrarFuncionarioBTN = new javax.swing.JButton();
        menuPrincipal = new javax.swing.JMenuBar();
        funcionarioMenu = new javax.swing.JMenu();
        adicionarFuncionario = new javax.swing.JMenuItem();
        consultarFuncionario = new javax.swing.JMenuItem();
        alterarFuncionario = new javax.swing.JMenuItem();
        removerFuncionario = new javax.swing.JMenuItem();
        relatorioFuncionario = new javax.swing.JMenuItem();
        clienteMenu = new javax.swing.JMenu();
        adicionarCliente = new javax.swing.JMenuItem();
        consultarCliente = new javax.swing.JMenuItem();
        alterarCliente = new javax.swing.JMenuItem();
        removerCliente = new javax.swing.JMenuItem();
        relatorioCliente = new javax.swing.JMenuItem();
        animalMenu = new javax.swing.JMenu();
        adicionarAnimal = new javax.swing.JMenuItem();
        consultarAnimal = new javax.swing.JMenuItem();
        alterarAnimal = new javax.swing.JMenuItem();
        removerAnimal = new javax.swing.JMenuItem();
        relatorioAnimal = new javax.swing.JMenuItem();
        produtoMenu = new javax.swing.JMenu();
        adicionarProduto = new javax.swing.JMenuItem();
        consultarProduto = new javax.swing.JMenuItem();
        alterarProduto = new javax.swing.JMenuItem();
        removerProduto = new javax.swing.JMenuItem();
        relatorioProduto = new javax.swing.JMenuItem();
        compraMenu = new javax.swing.JMenu();
        adicionarCompra = new javax.swing.JMenuItem();
        consultarCompra = new javax.swing.JMenuItem();
        alterarCompra = new javax.swing.JMenuItem();
        removerCompra = new javax.swing.JMenuItem();
        relatorioCompra = new javax.swing.JMenuItem();
        servicoMenu = new javax.swing.JMenu();
        adicionarServico = new javax.swing.JMenuItem();
        consultarServico = new javax.swing.JMenuItem();
        alterarServico = new javax.swing.JMenuItem();
        removerServico = new javax.swing.JMenuItem();
        relatorioServico = new javax.swing.JMenuItem();
        promocaoMenu = new javax.swing.JMenu();
        adicionarPromocao = new javax.swing.JMenuItem();
        consultarPromocao = new javax.swing.JMenuItem();
        alterarPromocao = new javax.swing.JMenuItem();
        removerPromocao = new javax.swing.JMenuItem();
        relatorioPromocao = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuário");

        jLabel3.setText("Senha");

        acessarBTN.setText("Acessar");
        acessarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acessarBTNActionPerformed(evt);
            }
        });

        cadastrarFuncionarioBTN.setText("Cadastro Funcionário");
        cadastrarFuncionarioBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarFuncionarioBTNActionPerformed(evt);
            }
        });

        funcionarioMenu.setText("Funcionário");
        funcionarioMenu.setEnabled(false);

        adicionarFuncionario.setText("Adicionar");
        adicionarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarFuncionarioActionPerformed(evt);
            }
        });
        funcionarioMenu.add(adicionarFuncionario);

        consultarFuncionario.setText("Consultar");
        consultarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarFuncionarioActionPerformed(evt);
            }
        });
        funcionarioMenu.add(consultarFuncionario);

        alterarFuncionario.setText("Alterar");
        alterarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarFuncionarioActionPerformed(evt);
            }
        });
        funcionarioMenu.add(alterarFuncionario);

        removerFuncionario.setText("Remover");
        removerFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerFuncionarioActionPerformed(evt);
            }
        });
        funcionarioMenu.add(removerFuncionario);

        relatorioFuncionario.setText("Relatório");
        relatorioFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioFuncionarioActionPerformed(evt);
            }
        });
        funcionarioMenu.add(relatorioFuncionario);

        menuPrincipal.add(funcionarioMenu);

        clienteMenu.setText("Cliente");
        clienteMenu.setEnabled(false);

        adicionarCliente.setText("Adicionar");
        adicionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarClienteActionPerformed(evt);
            }
        });
        clienteMenu.add(adicionarCliente);

        consultarCliente.setText("Consultar");
        consultarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarClienteActionPerformed(evt);
            }
        });
        clienteMenu.add(consultarCliente);

        alterarCliente.setText("Alterar");
        alterarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarClienteActionPerformed(evt);
            }
        });
        clienteMenu.add(alterarCliente);

        removerCliente.setText("Remover");
        removerCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerClienteActionPerformed(evt);
            }
        });
        clienteMenu.add(removerCliente);

        relatorioCliente.setText("Relatório");
        relatorioCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioClienteActionPerformed(evt);
            }
        });
        clienteMenu.add(relatorioCliente);

        menuPrincipal.add(clienteMenu);

        animalMenu.setText("Animal");
        animalMenu.setEnabled(false);

        adicionarAnimal.setText("Adicionar");
        adicionarAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarAnimalActionPerformed(evt);
            }
        });
        animalMenu.add(adicionarAnimal);

        consultarAnimal.setText("Consultar");
        consultarAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarAnimalActionPerformed(evt);
            }
        });
        animalMenu.add(consultarAnimal);

        alterarAnimal.setText("Alterar");
        alterarAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarAnimalActionPerformed(evt);
            }
        });
        animalMenu.add(alterarAnimal);

        removerAnimal.setText("Remover");
        removerAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerAnimalActionPerformed(evt);
            }
        });
        animalMenu.add(removerAnimal);

        relatorioAnimal.setText("Relatório");
        relatorioAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioAnimalActionPerformed(evt);
            }
        });
        animalMenu.add(relatorioAnimal);

        menuPrincipal.add(animalMenu);

        produtoMenu.setText("Produto");
        produtoMenu.setEnabled(false);

        adicionarProduto.setText("Adicionar");
        adicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarProdutoActionPerformed(evt);
            }
        });
        produtoMenu.add(adicionarProduto);

        consultarProduto.setText("Consultar");
        consultarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarProdutoActionPerformed(evt);
            }
        });
        produtoMenu.add(consultarProduto);

        alterarProduto.setText("Alterar");
        alterarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarProdutoActionPerformed(evt);
            }
        });
        produtoMenu.add(alterarProduto);

        removerProduto.setText("Remover");
        removerProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerProdutoActionPerformed(evt);
            }
        });
        produtoMenu.add(removerProduto);

        relatorioProduto.setText("Relatório");
        relatorioProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioProdutoActionPerformed(evt);
            }
        });
        produtoMenu.add(relatorioProduto);

        menuPrincipal.add(produtoMenu);

        compraMenu.setText("Compra");
        compraMenu.setEnabled(false);

        adicionarCompra.setText("Adicionar");
        adicionarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarCompraActionPerformed(evt);
            }
        });
        compraMenu.add(adicionarCompra);

        consultarCompra.setText("Consultar");
        consultarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarCompraActionPerformed(evt);
            }
        });
        compraMenu.add(consultarCompra);

        alterarCompra.setText("Alterar");
        alterarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarCompraActionPerformed(evt);
            }
        });
        compraMenu.add(alterarCompra);

        removerCompra.setText("Remover");
        removerCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerCompraActionPerformed(evt);
            }
        });
        compraMenu.add(removerCompra);

        relatorioCompra.setText("Relatório");
        relatorioCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioCompraActionPerformed(evt);
            }
        });
        compraMenu.add(relatorioCompra);

        menuPrincipal.add(compraMenu);

        servicoMenu.setText("Serviço");
        servicoMenu.setEnabled(false);

        adicionarServico.setText("Adicionar");
        adicionarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarServicoActionPerformed(evt);
            }
        });
        servicoMenu.add(adicionarServico);

        consultarServico.setText("Consulta");
        consultarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarServicoActionPerformed(evt);
            }
        });
        servicoMenu.add(consultarServico);

        alterarServico.setText("Alterar");
        alterarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarServicoActionPerformed(evt);
            }
        });
        servicoMenu.add(alterarServico);

        removerServico.setText("Remover");
        removerServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerServicoActionPerformed(evt);
            }
        });
        servicoMenu.add(removerServico);

        relatorioServico.setText("Relatório");
        relatorioServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioServicoActionPerformed(evt);
            }
        });
        servicoMenu.add(relatorioServico);

        menuPrincipal.add(servicoMenu);

        promocaoMenu.setText("Promoção");
        promocaoMenu.setEnabled(false);

        adicionarPromocao.setText("Adicionar");
        adicionarPromocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarPromocaoActionPerformed(evt);
            }
        });
        promocaoMenu.add(adicionarPromocao);

        consultarPromocao.setText("Consultar");
        consultarPromocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarPromocaoActionPerformed(evt);
            }
        });
        promocaoMenu.add(consultarPromocao);

        alterarPromocao.setText("Alterar");
        alterarPromocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarPromocaoActionPerformed(evt);
            }
        });
        promocaoMenu.add(alterarPromocao);

        removerPromocao.setText("Remover");
        removerPromocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerPromocaoActionPerformed(evt);
            }
        });
        promocaoMenu.add(removerPromocao);

        relatorioPromocao.setText("Relatório");
        relatorioPromocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioPromocaoActionPerformed(evt);
            }
        });
        promocaoMenu.add(relatorioPromocao);

        menuPrincipal.add(promocaoMenu);

        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(usuarioTF)
                    .addComponent(senhaPF)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(acessarBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cadastrarFuncionarioBTN)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(senhaPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acessarBTN)
                    .addComponent(cadastrarFuncionarioBTN))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarFuncionarioActionPerformed
        AdicionarFuncionarioJD dialog = new AdicionarFuncionarioJD(this, true, funcCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_adicionarFuncionarioActionPerformed

    private void consultarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarFuncionarioActionPerformed
        ConsultarFuncionarioJD dialog = new ConsultarFuncionarioJD(this, true, funcCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_consultarFuncionarioActionPerformed

    private void alterarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarFuncionarioActionPerformed
        AlterarFuncionarioJD dialog = new AlterarFuncionarioJD(this, true, funcCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_alterarFuncionarioActionPerformed

    private void removerFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerFuncionarioActionPerformed
        RemoverFuncionarioJD dialog = new RemoverFuncionarioJD(this, true, funcCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_removerFuncionarioActionPerformed

    private void adicionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarClienteActionPerformed
        AdicionarClienteJD dialog = new AdicionarClienteJD(this, true, clieCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_adicionarClienteActionPerformed

    private void consultarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarClienteActionPerformed
        ConsultarClienteJD dialog = new ConsultarClienteJD(this, true, clieCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_consultarClienteActionPerformed

    private void alterarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarClienteActionPerformed
        AlterarClienteJD dialog = new AlterarClienteJD(this, true, clieCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_alterarClienteActionPerformed

    private void removerClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerClienteActionPerformed
        RemoverClienteJD dialog = new RemoverClienteJD(this, true, clieCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_removerClienteActionPerformed

    private void adicionarAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarAnimalActionPerformed
        AdicionarAnimalJD dialog = new AdicionarAnimalJD(this, true, animCont, clieCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_adicionarAnimalActionPerformed

    private void consultarAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarAnimalActionPerformed
        ConsultarAnimalJD dialog = new ConsultarAnimalJD(this, true, animCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_consultarAnimalActionPerformed

    private void alterarAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarAnimalActionPerformed
        AlterarAnimalJD dialog = new AlterarAnimalJD(this, true, animCont, clieCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_alterarAnimalActionPerformed

    private void removerAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerAnimalActionPerformed
        RemoverAnimalJD dialog = new RemoverAnimalJD(this, true, animCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_removerAnimalActionPerformed

    private void adicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarProdutoActionPerformed
        AdicionarProdutoJD dialog = new AdicionarProdutoJD(this, true, prodCont, promCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_adicionarProdutoActionPerformed

    private void consultarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarProdutoActionPerformed
        ConsultarProdutoJD dialog = new ConsultarProdutoJD(this, true, prodCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_consultarProdutoActionPerformed

    private void alterarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarProdutoActionPerformed
        AlterarProdutoJD dialog = new AlterarProdutoJD(this, true, prodCont, promCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_alterarProdutoActionPerformed

    private void removerProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerProdutoActionPerformed
        RemoverProdutoJD dialog = new RemoverProdutoJD(this, true, prodCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_removerProdutoActionPerformed

    private void adicionarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarCompraActionPerformed
        AdicionarCompraJD dialog = new AdicionarCompraJD(this, true, compCont, servCont, clieCont, prodCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_adicionarCompraActionPerformed

    private void consultarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarCompraActionPerformed
        ConsultarCompraJD dialog = new ConsultarCompraJD(this, true, compCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_consultarCompraActionPerformed

    private void alterarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarCompraActionPerformed
        AlterarCompraJD dialog = new AlterarCompraJD(this, true, compCont, servCont, clieCont, prodCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_alterarCompraActionPerformed

    private void removerCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerCompraActionPerformed
        RemoverCompraJD dialog = new RemoverCompraJD(this, true, compCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_removerCompraActionPerformed

    private void adicionarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarServicoActionPerformed
        AdicionarServicoJD dialog = new AdicionarServicoJD(this, rootPaneCheckingEnabled, servCont, animCont, promCont, clieCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_adicionarServicoActionPerformed

    private void consultarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarServicoActionPerformed
        ConsultarServicoJD dialog = new ConsultarServicoJD(this, true, servCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_consultarServicoActionPerformed

    private void alterarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarServicoActionPerformed
        AlterarServicoJD dialog = new AlterarServicoJD(this, true, servCont, clieCont, promCont, animCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_alterarServicoActionPerformed

    private void removerServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerServicoActionPerformed
        RemoverServicoJD dialog = new RemoverServicoJD(this, true, servCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_removerServicoActionPerformed

    private void adicionarPromocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarPromocaoActionPerformed
        AdicionarPromocaoJD dialog = new AdicionarPromocaoJD(this, true, promCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_adicionarPromocaoActionPerformed

    private void consultarPromocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarPromocaoActionPerformed
        ConsultarPromocaoJD dialog = new ConsultarPromocaoJD(this, true, promCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_consultarPromocaoActionPerformed

    private void alterarPromocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarPromocaoActionPerformed
        AlterarPromocaoJD dialog = new AlterarPromocaoJD(this, true, promCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_alterarPromocaoActionPerformed

    private void removerPromocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerPromocaoActionPerformed
        RemoverPromocaoJD dialog = new RemoverPromocaoJD(this, true, promCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_removerPromocaoActionPerformed

    private void relatorioFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioFuncionarioActionPerformed
        RelatorioFuncionarioJD dialog = new RelatorioFuncionarioJD(this, true, funcCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_relatorioFuncionarioActionPerformed

    private void relatorioClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioClienteActionPerformed
        RelatorioClienteJD dialog = new RelatorioClienteJD(this, true, clieCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_relatorioClienteActionPerformed

    private void relatorioAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioAnimalActionPerformed
        RelatorioAnimalJD dialog = new RelatorioAnimalJD(this, true, animCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_relatorioAnimalActionPerformed

    private void relatorioProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioProdutoActionPerformed
        RelatorioProdutoJD dialog = new RelatorioProdutoJD(this, true, prodCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_relatorioProdutoActionPerformed

    private void relatorioCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioCompraActionPerformed
        RelatorioCompraJD dialog = new RelatorioCompraJD(this, true, compCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_relatorioCompraActionPerformed

    private void relatorioServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioServicoActionPerformed
        RelatorioServicoJD dialog = new RelatorioServicoJD(this, true, servCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_relatorioServicoActionPerformed

    private void relatorioPromocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioPromocaoActionPerformed
        RelatorioPromocaoJD dialog = new RelatorioPromocaoJD(this, true, promCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_relatorioPromocaoActionPerformed

    private void acessarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acessarBTNActionPerformed
        File arquivo = new File("src/main/java/cadastros/cadastros.txt");
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            BufferedReader leitura = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = leitura.readLine()) != null) {
                String[] partes = linha.split(" ");

                if (partes[0].equals(Base64.getEncoder().encodeToString(usuarioTF.getText().getBytes()))
                        && partes[1].equals(Base64.getEncoder().encodeToString(new String(senhaPF.getPassword()).getBytes()))) {
                    liberaMenu();
                    usuarioTF.setEnabled(false);
                    senhaPF.setEnabled(false);
                    acessarBTN.setEnabled(false);
                    cadastrarFuncionarioBTN.setEnabled(false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_acessarBTNActionPerformed

    private void cadastrarFuncionarioBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarFuncionarioBTNActionPerformed
        AdicionarFuncionarioJD dialog = new AdicionarFuncionarioJD(this, true, funcCont);
        dialog.setVisible(true);
    }//GEN-LAST:event_cadastrarFuncionarioBTNActionPerformed

    private void liberaMenu() {
        funcionarioMenu.setEnabled(true);
        clienteMenu.setEnabled(true);
        animalMenu.setEnabled(true);
        produtoMenu.setEnabled(true);
        compraMenu.setEnabled(true);
        servicoMenu.setEnabled(true);
        promocaoMenu.setEnabled(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acessarBTN;
    private javax.swing.JMenuItem adicionarAnimal;
    private javax.swing.JMenuItem adicionarCliente;
    private javax.swing.JMenuItem adicionarCompra;
    private javax.swing.JMenuItem adicionarFuncionario;
    private javax.swing.JMenuItem adicionarProduto;
    private javax.swing.JMenuItem adicionarPromocao;
    private javax.swing.JMenuItem adicionarServico;
    private javax.swing.JMenuItem alterarAnimal;
    private javax.swing.JMenuItem alterarCliente;
    private javax.swing.JMenuItem alterarCompra;
    private javax.swing.JMenuItem alterarFuncionario;
    private javax.swing.JMenuItem alterarProduto;
    private javax.swing.JMenuItem alterarPromocao;
    private javax.swing.JMenuItem alterarServico;
    private javax.swing.JMenu animalMenu;
    private javax.swing.JButton cadastrarFuncionarioBTN;
    private javax.swing.JMenu clienteMenu;
    private javax.swing.JMenu compraMenu;
    private javax.swing.JMenuItem consultarAnimal;
    private javax.swing.JMenuItem consultarCliente;
    private javax.swing.JMenuItem consultarCompra;
    private javax.swing.JMenuItem consultarFuncionario;
    private javax.swing.JMenuItem consultarProduto;
    private javax.swing.JMenuItem consultarPromocao;
    private javax.swing.JMenuItem consultarServico;
    private javax.swing.JMenu funcionarioMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JMenu produtoMenu;
    private javax.swing.JMenu promocaoMenu;
    private javax.swing.JMenuItem relatorioAnimal;
    private javax.swing.JMenuItem relatorioCliente;
    private javax.swing.JMenuItem relatorioCompra;
    private javax.swing.JMenuItem relatorioFuncionario;
    private javax.swing.JMenuItem relatorioProduto;
    private javax.swing.JMenuItem relatorioPromocao;
    private javax.swing.JMenuItem relatorioServico;
    private javax.swing.JMenuItem removerAnimal;
    private javax.swing.JMenuItem removerCliente;
    private javax.swing.JMenuItem removerCompra;
    private javax.swing.JMenuItem removerFuncionario;
    private javax.swing.JMenuItem removerProduto;
    private javax.swing.JMenuItem removerPromocao;
    private javax.swing.JMenuItem removerServico;
    private javax.swing.JPasswordField senhaPF;
    private javax.swing.JMenu servicoMenu;
    private javax.swing.JTextField usuarioTF;
    // End of variables declaration//GEN-END:variables
}
