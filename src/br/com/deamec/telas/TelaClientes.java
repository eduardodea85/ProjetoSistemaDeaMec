/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.deamec.telas;

import java.sql.*;
import br.com.deamec.dal.ModuloConexao;
import javax.swing.JOptionPane;
//A linha abaixo importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;

public class TelaClientes extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaClientes
     */
    public TelaClientes() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void adicionar() {
        String sql = "insert tbclientes(nomecli,cpfcbo,cpfcnpjcli,endclirua,endclinum,endclibairro,endclicid,endcliest,cepcli,emailcli,dddfixo,foneclifixo,dddcel,foneclicel) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, cboCliCnpj.getSelectedItem().toString());
            pst.setString(3, txtCliCnpj.getText());
            pst.setString(4, txtCliRua.getText());
            pst.setString(5, txtCliNumero.getText());
            pst.setString(6, txtCliBairro.getText());
            pst.setString(7, txtCliCidade.getText());
            pst.setString(8, cboCliEstado.getSelectedItem().toString());
            pst.setString(9, txtCliCep.getText());
            pst.setString(10, txtCliEmail.getText());
            pst.setString(11, txtCliDddFixo.getText());
            pst.setString(12, txtCliFoneFixo.getText());
            pst.setString(13, txtCliDddCel.getText());
            pst.setString(14, txtCliFoneCel.getText());

            if ((((((((((((txtCliNome.getText().isEmpty()) || txtCliCnpj.getText().isEmpty()) || txtCliRua.getText().isEmpty()) || txtCliNumero.getText().isEmpty()) || txtCliBairro.getText().isEmpty()) || txtCliCidade.getText().isEmpty()) || txtCliCep.getText().isEmpty()) || txtCliEmail.getText().isEmpty()) || txtCliDddCel.getText().isEmpty()) || txtCliFoneCel.getText().isEmpty()) || cboCliEstado.getSelectedItem().toString().isEmpty()) || cboCliCnpj.getSelectedItem().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
                    txtCliNome.setText(null);
                    txtCliCnpj.setText(null);
                    txtCliRua.setText(null);
                    txtCliNumero.setText(null);
                    txtCliBairro.setText(null);
                    txtCliCidade.setText(null);
                    txtCliCep.setText(null);
                    txtCliEmail.setText(null);
                    txtCliDddFixo.setText(null);
                    txtCliFoneFixo.setText(null);
                    txtCliDddCel.setText(null);
                    txtCliFoneCel.setText(null);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Método para pesquisar clientes pelo nomde com filtro.
    private void pesquisar_cliente() {
        String sql = "select idcli as ID,nomecli as Nome,cpfcbo as Documento,cpfcnpjcli as CpfCnpj,endclirua as Rua,endclinum as Número,endclibairro as Bairro,endclicid as Cidade,endcliest as Estado,cepcli as CEP,emailcli as EMail,dddfixo as DDD,foneclifixo as FoneFixo,dddcel as DDD,foneclicel as FoneCel from tbclientes where nomecli like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //Passando o conteudo da caixa de pesquisa(nome da variavel) para o ?
            //Atenção ao % que é a continuação da String sql do comando acima.
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //A linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela.
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//Metodo para seta os campos do formulario com o conteudo da tabela
    public void setar_campos() {
        int setar = tblClientes.getSelectedRow();
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        cboCliCnpj.setSelectedItem(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtCliCnpj.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtCliRua.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
        txtCliNumero.setText(tblClientes.getModel().getValueAt(setar, 5).toString());
        txtCliBairro.setText(tblClientes.getModel().getValueAt(setar, 6).toString());
        txtCliCidade.setText(tblClientes.getModel().getValueAt(setar, 7).toString());
        cboCliEstado.setSelectedItem(tblClientes.getModel().getValueAt(setar, 8));
        txtCliCep.setText(tblClientes.getModel().getValueAt(setar, 9).toString());
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 10).toString());
        txtCliDddFixo.setText(tblClientes.getModel().getValueAt(setar, 11).toString());
        txtCliFoneFixo.setText(tblClientes.getModel().getValueAt(setar, 12).toString());
        txtCliDddCel.setText(tblClientes.getModel().getValueAt(setar, 13).toString());
        txtCliFoneCel.setText(tblClientes.getModel().getValueAt(setar, 14).toString());
        //A linha abaixo desabilita o botão adicionar
        btnCliAdicionar.setEnabled(false);

    }

    private void alterar() {
        String sql = "update tbclientes set nomecli=?,cpfcbo=?,cpfcnpjcli=?,endclirua=?,endclinum=?,endclibairro=?,endclicid=?,endcliest=?,cepcli=?,emailcli=?,dddfixo=?,foneclifixo=?,dddcel=?,foneclicel=? where idcli=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, cboCliCnpj.getSelectedItem().toString());
            pst.setString(3, txtCliCnpj.getText());
            pst.setString(4, txtCliRua.getText());
            pst.setString(5, txtCliNumero.getText());
            pst.setString(6, txtCliBairro.getText());
            pst.setString(7, txtCliCidade.getText());
            pst.setString(8, cboCliEstado.getSelectedItem().toString());
            pst.setString(9, txtCliCep.getText());
            pst.setString(10, txtCliEmail.getText());
            pst.setString(11, txtCliDddFixo.getText());
            pst.setString(12, txtCliFoneFixo.getText());
            pst.setString(13, txtCliDddCel.getText());
            pst.setString(14, txtCliFoneCel.getText());
            pst.setString(15, txtCliId.getText());

            if ((((((((((((txtCliNome.getText().isEmpty()) || cboCliCnpj.getSelectedItem().toString().isEmpty()) || txtCliCnpj.getText().isEmpty()) || txtCliRua.getText().isEmpty()) || txtCliNumero.getText().isEmpty()) || txtCliBairro.getText().isEmpty()) || txtCliCidade.getText().isEmpty()) || cboCliEstado.getSelectedItem().toString().isEmpty()) || txtCliCep.getText().isEmpty()) || txtCliEmail.getText().isEmpty()) || txtCliDddCel.getText().isEmpty()) || txtCliFoneCel.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int alterado = pst.executeUpdate();
                if (alterado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso");
                    txtCliNome.setText(null);
                    txtCliCnpj.setText(null);
                    txtCliRua.setText(null);
                    txtCliNumero.setText(null);
                    txtCliBairro.setText(null);
                    txtCliCidade.setText(null);
                    txtCliCep.setText(null);
                    txtCliEmail.setText(null);
                    txtCliDddFixo.setText(null);
                    txtCliFoneFixo.setText(null);
                    txtCliDddCel.setText(null);
                    txtCliFoneCel.setText(null);
                    btnCliAdicionar.setEnabled(true);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse cliente", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbclientes where idcli=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCliId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso");
                    txtCliNome.setText(null);
                    txtCliCnpj.setText(null);
                    txtCliRua.setText(null);
                    txtCliNumero.setText(null);
                    txtCliBairro.setText(null);
                    txtCliCidade.setText(null);
                    txtCliCep.setText(null);
                    txtCliEmail.setText(null);
                    txtCliDddFixo.setText(null);
                    txtCliFoneFixo.setText(null);
                    txtCliDddCel.setText(null);
                    txtCliFoneCel.setText(null);
                    btnCliAdicionar.setEnabled(true);

                }

            } catch (Exception e) {
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        txtCliPesquisar = new javax.swing.JTextField();
        lblCliPesquisar = new javax.swing.JLabel();
        cboCliCnpj = new javax.swing.JComboBox<>();
        lblCliNome = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        lblCliRua = new javax.swing.JLabel();
        txtCliRua = new javax.swing.JTextField();
        lblCliNumero = new javax.swing.JLabel();
        txtCliNumero = new javax.swing.JTextField();
        lblCliBairro = new javax.swing.JLabel();
        txtCliBairro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblCliCidade = new javax.swing.JLabel();
        txtCliCidade = new javax.swing.JTextField();
        lblCliEstado = new javax.swing.JLabel();
        cboCliEstado = new javax.swing.JComboBox<>();
        lblCliCep = new javax.swing.JLabel();
        lblCliEmail = new javax.swing.JLabel();
        txtCliEmail = new javax.swing.JTextField();
        lblCliFoneFixo = new javax.swing.JLabel();
        lblCliFoneCel = new javax.swing.JLabel();
        btnCliAlterar = new javax.swing.JButton();
        btnCliRemover = new javax.swing.JButton();
        btnCliAdicionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblCliId = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        lblCliDoc = new javax.swing.JLabel();
        txtCliCnpj = new javax.swing.JFormattedTextField();
        txtCliCep = new javax.swing.JFormattedTextField();
        txtCliDddFixo = new javax.swing.JFormattedTextField();
        txtCliFoneFixo = new javax.swing.JFormattedTextField();
        txtCliDddCel = new javax.swing.JFormattedTextField();
        txtCliFoneCel = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(640, 480));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        txtCliPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCliPesquisarMouseClicked(evt);
            }
        });
        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        lblCliPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/deamec/icones/search.png"))); // NOI18N

        cboCliCnpj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----", "CNPJ", "CPF" }));
        cboCliCnpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboCliCnpjKeyPressed(evt);
            }
        });

        lblCliNome.setText("Nome / Razão Social");

        lblCliRua.setText("Rua");

        lblCliNumero.setText("Número");

        lblCliBairro.setText("Bairro");

        lblCliCidade.setText("Cidade");

        lblCliEstado.setText("Estado");

        cboCliEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " " }));

        lblCliCep.setText("CEP");

        lblCliEmail.setText("E-Mail");

        lblCliFoneFixo.setText("Fone Fixo");

        lblCliFoneCel.setText("Fone Cel");

        btnCliAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/deamec/icones/updateedit.png"))); // NOI18N
        btnCliAlterar.setToolTipText("Editar");
        btnCliAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAlterarActionPerformed(evt);
            }
        });

        btnCliRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/deamec/icones/delete.png"))); // NOI18N
        btnCliRemover.setToolTipText("Remover");
        btnCliRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliRemoverActionPerformed(evt);
            }
        });

        btnCliAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/deamec/icones/create.png"))); // NOI18N
        btnCliAdicionar.setToolTipText("Adicionar");
        btnCliAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAdicionarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("*");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("*");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("*");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("*");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("* Campos Obrigatórios");

        lblCliId.setText("ID");

        txtCliId.setEnabled(false);

        lblCliDoc.setText("Documento");

        txtCliCnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliCnpjActionPerformed(evt);
            }
        });

        try {
            txtCliCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCliDddFixo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCliFoneFixo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCliDddCel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCliFoneCel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCliId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCliRua)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCliRua))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCliCidade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCliCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCliEstado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboCliEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCliCep)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCliCep, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCliEmail))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCliNumero)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCliNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCliBairro)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliBairro)
                                    .addComponent(txtCliEmail)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCliFoneFixo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliDddFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliFoneFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCliFoneCel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliDddCel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliFoneCel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCliNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCliDoc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboCliCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliCnpj))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCliAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnCliAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnCliRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(308, 308, 308)))
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addComponent(jLabel6)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(lblCliId)
                                .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliNome)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12)
                    .addComponent(lblCliDoc)
                    .addComponent(cboCliCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblCliRua)
                    .addComponent(txtCliRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(lblCliBairro)
                    .addComponent(txtCliBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(lblCliNumero))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliCidade)
                    .addComponent(jLabel7)
                    .addComponent(txtCliCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliEstado)
                    .addComponent(jLabel8)
                    .addComponent(cboCliEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliEmail)
                    .addComponent(jLabel5)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(lblCliCep))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliDddFixo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliFoneFixo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(lblCliFoneCel)
                    .addComponent(txtCliFoneCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliDddCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliFoneFixo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCliAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCliRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        setBounds(0, 0, 1001, 643);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAdicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btnCliAdicionarActionPerformed
//O evento abaixo é do tipo enquanto for digitando mostre em tempo real
    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased
//Evento que sera usado para setar os campos da tabela (clicando com o mouse)
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // chamando o metodo para setar os campos
        setar_campos();

    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnCliAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAlterarActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnCliAlterarActionPerformed

    private void btnCliRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliRemoverActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnCliRemoverActionPerformed

    private void txtCliPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCliPesquisarMouseClicked
        // TODO add your handling code here:
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarMouseClicked

    private void txtCliCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliCnpjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliCnpjActionPerformed

    private void cboCliCnpjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboCliCnpjKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCliCnpjKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliAdicionar;
    private javax.swing.JButton btnCliAlterar;
    private javax.swing.JButton btnCliRemover;
    private javax.swing.JComboBox<String> cboCliCnpj;
    private javax.swing.JComboBox<String> cboCliEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliBairro;
    private javax.swing.JLabel lblCliCep;
    private javax.swing.JLabel lblCliCidade;
    private javax.swing.JLabel lblCliDoc;
    private javax.swing.JLabel lblCliEmail;
    private javax.swing.JLabel lblCliEstado;
    private javax.swing.JLabel lblCliFoneCel;
    private javax.swing.JLabel lblCliFoneFixo;
    private javax.swing.JLabel lblCliId;
    private javax.swing.JLabel lblCliNome;
    private javax.swing.JLabel lblCliNumero;
    private javax.swing.JLabel lblCliPesquisar;
    private javax.swing.JLabel lblCliRua;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliBairro;
    private javax.swing.JFormattedTextField txtCliCep;
    private javax.swing.JTextField txtCliCidade;
    private javax.swing.JFormattedTextField txtCliCnpj;
    private javax.swing.JFormattedTextField txtCliDddCel;
    private javax.swing.JFormattedTextField txtCliDddFixo;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JFormattedTextField txtCliFoneCel;
    private javax.swing.JFormattedTextField txtCliFoneFixo;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliNumero;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCliRua;
    // End of variables declaration//GEN-END:variables
}
