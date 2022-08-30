/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.deamec.telas;


import java.sql.*;
import br.com.deamec.dal.ModuloConexao;
import javax.swing.JOptionPane;

public class TelaUsuarios extends javax.swing.JInternalFrame {
//usando variaveis. Codigo que sempre usa para conectar ao banco 
//conexao = É o modulo de conexao
//pst = prepara a conexão com o banco de dados
// rs = exibe o resultado da conexao feita com o banco

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuarios
     */
    // A linnha abaixo significa que é um método construtor.
    //usamos esse modulo para iniciar o modulo de conexao, precisa criar uma
    //instancia do método conector que esta dentro do modulo de conexao.
    public TelaUsuarios() {
        initComponents();
        //chama o modulo de conexão criado em dal.
        conexao = ModuloConexao.conector();
    }

    //privite = este modificador, vai permitir que só seja executado este metodo
    //dentro dessa classe. void = porque é um metodo sem retorno. criando o 
    //acesso ao banco no botão consultar
    //método para consultar usuarios
    private void consultar() {
        String num_cli = JOptionPane.showInputDialog("Digite a ID do cliente");
        //primeiro passo criar a instrução sql String e criar a variavel sql 
        //do tipo String que vai receber a consulta ao banco de dados.
        //Cria a query(linha abaixo), seta o ?(vai trazer do campo Id) e executa
        // a query tendo ação. sempre essa sequencia.
        //String sql = "select * from tbusuarios where iduser=?";
        String sql = "select * from tbusuarios where iduser= " + num_cli;
        try {
            pst = conexao.prepareStatement(sql);
            //Se tem apenas ?, é necessário setar apenas um campo
            //a função getText() vai obter o valor o valor que fooi digitado no 
            //campo, e substituir pelo ?.
            //pst.setString(1, txtUsuId.getText());
            //Depois que setou ou alimentou o ?, executa a query usando a
            //variavel rs, que é da classe especial resultset que vem dentro
            //do pacote import java.sql.*;
            rs = pst.executeQuery();
            //usar uma estrutura de decisão if caso tenha um usuario correspondente
            //vai pedir para preencher os campos do Form
            //rs.next é caso tenha o usuario correspondente
            if (rs.next()) {
                //caso tenha ele vai setar os campos
                txtUsuId.setText(rs.getString(1));
                txtUsuNome.setText(rs.getString(2));
                //Agora precisa chamar esse metodo private void consultar(); no 
                //botão consultar da tela de Usuarios.(nessa tela)
                txtUsuCpf.setText(rs.getString(3));
                txtUsuTelefone.setText(rs.getString(4));
                txtUsuLogin.setText(rs.getString(5));
                txtUsuSenha.setText(rs.getString(6));
                //A linha abaixo se refere ao combobox
                cboUsuPerfil.setSelectedItem(rs.getString(7));

            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                //As linhas abaixo limpam os campos. null limpa o campo do texto
                txtUsuNome.setText(null);
                txtUsuCpf.setText(null);
                txtUsuTelefone.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
        }
    }

    //metodo para adicionar usuários. private é o modificador de acesso que fará
    //com que esse metodo seja executado apenas dentro dessa classe TelaUsuarios
    //void significa que o metodo é sem retorno e "adicionar" é o nome do metodo
    private void adicionar() {
        //instrução sql responsavel por inserir no banco
        String sql = "insert into tbusuarios(iduser,usuario,cpf,fone,login,senha,perfil) values(?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            //setar o banco de dados com o conteudo do campo a inserir
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsuNome.getText());
            pst.setString(3, txtUsuCpf.getText());
            pst.setString(4, txtUsuTelefone.getText());
            pst.setString(5, txtUsuLogin.getText());
            pst.setString(6, txtUsuSenha.getText());
            pst.setString(7, cboUsuPerfil.getSelectedItem().toString());

            /**
             *
             * Lógica sql try{ //validação if(Campos obrigatótios não
             * preenchidos){ não executa update no banco "Preencha os campos
             * obrifatórios" else{ executa update no banco } }catch }
             *
             */
//validação dos campos obrigatórios. empty diz que está vazio o campo orbrigatório 
//no banco de dados. o "ou" é representado pelo simbolo ||
            if (((((txtUsuId.getText().isEmpty())||txtUsuNome.getText().isEmpty())||txtUsuCpf.getText().isEmpty())||txtUsuLogin.getText().isEmpty())||txtUsuSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {

                //A linha abaixo atualiza no baco a tabela usuarios com os dados 
                //do formulario
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuCpf.setText(null);
                    txtUsuTelefone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                    
                }
            }

            //A linha serve de apoio ao entendimento da logica
            //System.out.println(adicionado);
            //pst.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            //txtUsuId.setText(null);
            //txtUsuNome.setText(null);
            //txtUsuCpf.setText(null);
            //txtUsuTelefone.setText(null);
            //txtUsuLogin.setText(null);
            //txtUsuSenha.setText(null);
            //cboUsuPerfil.setSelectedItem(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
//Criando o metodo para alterar os dados dos usuarios(botão alterar)
    private void alterar(){
        String sql ="update tbusuarios set usuario=?,cpf=?,fone=?,login=?,senha=?,perfil=? where iduser=?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1,txtUsuNome.getText());
                pst.setString(2,txtUsuCpf.getText());
                pst.setString(3,txtUsuTelefone.getText());
                pst.setString(4,txtUsuLogin.getText());
                pst.setString(5,txtUsuSenha.getText());
                pst.setString(6,cboUsuPerfil.getSelectedItem().toString());
                pst.setString(7,txtUsuId.getText());
                
                if (((((txtUsuId.getText().isEmpty())||txtUsuNome.getText().isEmpty())||txtUsuCpf.getText().isEmpty())||txtUsuLogin.getText().isEmpty())||txtUsuSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {

                //A linha abaixo atualiza no baco a tabela usuarios com os dados 
                //do formulario
                //A estrutura abaixo é usada para confirmar a alteração dos dados na tabela 
                int alterado = pst.executeUpdate();
                if (alterado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuCpf.setText(null);
                    txtUsuTelefone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                    
                }
            }
                
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
//metodo responsável pela remoçao de usuarios
    private void remover(){
        //a estrutura abaixo confirma a remoçao do usuario
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse usuário", "Atenção",JOptionPane.YES_NO_OPTION);
        if (confirma==JOptionPane.YES_OPTION) {
            String sql="delete from tbusuarios where iduser=?";
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1,txtUsuId.getText());
                                
                if (((((txtUsuId.getText().isEmpty())||txtUsuNome.getText().isEmpty())||txtUsuCpf.getText().isEmpty())||txtUsuLogin.getText().isEmpty())||txtUsuSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");

            } else {

                //A linha abaixo atualiza no baco a tabela usuarios com os dados 
                //do formulario
                //A estrutura abaixo é usada para confirmar a alteração dos dados na tabela 
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuCpf.setText(null);
                    txtUsuTelefone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                    
                }
            }
                
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
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

        lblUsuId = new javax.swing.JLabel();
        lblUsuNome = new javax.swing.JLabel();
        lblUsuCpf = new javax.swing.JLabel();
        lblUsuLogin = new javax.swing.JLabel();
        lblUsuSenha = new javax.swing.JLabel();
        lblUsuPerfil = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        lblUsuTelefone = new javax.swing.JLabel();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        lblObrigNome = new javax.swing.JLabel();
        lblObrigSenha = new javax.swing.JLabel();
        lblObrigCpf = new javax.swing.JLabel();
        lblObrigLogin = new javax.swing.JLabel();
        lblObrigId = new javax.swing.JLabel();
        lblObrigPerfil = new javax.swing.JLabel();
        lblCamposObrigatorios = new javax.swing.JLabel();
        txtUsuCpf = new javax.swing.JFormattedTextField();
        txtUsuTelefone = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(640, 480));

        lblUsuId.setText("ID");

        lblUsuNome.setText("Nome");

        lblUsuCpf.setText("CPF");

        lblUsuLogin.setText("Login");

        lblUsuSenha.setText("Senha");

        lblUsuPerfil.setText("Perfil");

        txtUsuId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuIdActionPerformed(evt);
            }
        });

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--------", "Administrador", "Assistente" }));
        cboUsuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsuPerfilActionPerformed(evt);
            }
        });

        lblUsuTelefone.setText("Telefone");

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/deamec/icones/add.png"))); // NOI18N
        btnUsuCreate.setToolTipText("Adicionar");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        btnUsuRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/deamec/icones/search2.png"))); // NOI18N
        btnUsuRead.setToolTipText("Consultar");
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });

        btnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/deamec/icones/edit.png"))); // NOI18N
        btnUsuUpdate.setToolTipText("Alterar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });

        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/deamec/icones/remove.png"))); // NOI18N
        btnUsuDelete.setToolTipText("Remover");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });

        lblObrigNome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblObrigNome.setForeground(new java.awt.Color(255, 0, 0));
        lblObrigNome.setText("*");

        lblObrigSenha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblObrigSenha.setForeground(new java.awt.Color(255, 0, 0));
        lblObrigSenha.setText("*");

        lblObrigCpf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblObrigCpf.setForeground(new java.awt.Color(255, 0, 0));
        lblObrigCpf.setText("*");

        lblObrigLogin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblObrigLogin.setForeground(new java.awt.Color(255, 0, 0));
        lblObrigLogin.setText("*");

        lblObrigId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblObrigId.setForeground(new java.awt.Color(255, 0, 0));
        lblObrigId.setText("*");

        lblObrigPerfil.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblObrigPerfil.setForeground(new java.awt.Color(255, 0, 0));
        lblObrigPerfil.setText("*");

        lblCamposObrigatorios.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblCamposObrigatorios.setForeground(new java.awt.Color(255, 0, 0));
        lblCamposObrigatorios.setText("* Campos Obrigatórios");

        try {
            txtUsuCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtUsuTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblObrigId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblUsuId))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblObrigCpf)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblUsuCpf))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblObrigNome)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblUsuNome))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txtUsuCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtUsuTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblObrigPerfil)
                                        .addGap(4, 4, 4)
                                        .addComponent(lblUsuPerfil)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(130, 130, 130)
                                        .addComponent(lblCamposObrigatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblObrigLogin)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblUsuTelefone)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUsuLogin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblObrigSenha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblUsuSenha)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuSenha)))
                .addContainerGap(225, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(291, 291, 291))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnUsuCreate, btnUsuDelete, btnUsuRead, btnUsuUpdate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUsuId)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuPerfil)
                    .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObrigId)
                    .addComponent(lblObrigPerfil)
                    .addComponent(lblCamposObrigatorios))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuNome)
                    .addComponent(lblObrigNome))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUsuCpf)
                    .addComponent(lblUsuTelefone)
                    .addComponent(lblObrigCpf)
                    .addComponent(txtUsuCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblUsuLogin)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuSenha)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObrigSenha)
                    .addComponent(lblObrigLogin))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1001, 643);
    }// </editor-fold>//GEN-END:initComponents
// quando usa a plublic void, significa um método sem retorno
    private void txtUsuIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuIdActionPerformed

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        // Chamando o método consultar
        consultar();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void cboUsuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsuPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUsuPerfilActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        //chamando o metodo (botão) adicionar
        adicionar();
    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        alterar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel lblCamposObrigatorios;
    private javax.swing.JLabel lblObrigCpf;
    private javax.swing.JLabel lblObrigId;
    private javax.swing.JLabel lblObrigLogin;
    private javax.swing.JLabel lblObrigNome;
    private javax.swing.JLabel lblObrigPerfil;
    private javax.swing.JLabel lblObrigSenha;
    private javax.swing.JLabel lblUsuCpf;
    private javax.swing.JLabel lblUsuId;
    private javax.swing.JLabel lblUsuLogin;
    private javax.swing.JLabel lblUsuNome;
    private javax.swing.JLabel lblUsuPerfil;
    private javax.swing.JLabel lblUsuSenha;
    private javax.swing.JLabel lblUsuTelefone;
    private javax.swing.JFormattedTextField txtUsuCpf;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    private javax.swing.JFormattedTextField txtUsuTelefone;
    // End of variables declaration//GEN-END:variables
}
