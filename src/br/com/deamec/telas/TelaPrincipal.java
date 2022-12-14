/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.deamec.telas;

import br.com.deamec.dal.ModuloConexao;
import br.com.deamec.utils.FundoTela;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author danie
 */
public class TelaPrincipal extends javax.swing.JFrame {
    FundoTela tela;
    
    Connection conexao = null;

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setLayout(new GridLayout());
        //tela = new FundoTela("src/br/com/deamec/icones/fundo-telalogin.jpg");
        //getContentPane().add(tela);
        conexao = ModuloConexao.conector();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        lblLogo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menCad = new javax.swing.JMenu();
        menCadCli = new javax.swing.JMenuItem();
        menCadOS = new javax.swing.JMenuItem();
        menCadUsu = new javax.swing.JMenuItem();
        menRel = new javax.swing.JMenu();
        menRelCli = new javax.swing.JMenuItem();
        menRelSer = new javax.swing.JMenuItem();
        menAju = new javax.swing.JMenu();
        menAjuSob = new javax.swing.JMenuItem();
        menOpc = new javax.swing.JMenu();
        menOpcSai = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Gerenciamento de Empresa");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1001, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/deamec/icones/logo.png"))); // NOI18N

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUsuario.setText("Usu??rio");

        lblData.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblData.setText("Data");
        lblData.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblDataPropertyChange(evt);
            }
        });

        menCad.setText("Cadastro");

        menCadCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menCadCli.setText("Cliente");
        menCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadCliActionPerformed(evt);
            }
        });
        menCad.add(menCadCli);

        menCadOS.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        menCadOS.setText("Ordem Sevi??o");
        menCadOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadOSActionPerformed(evt);
            }
        });
        menCad.add(menCadOS);

        menCadUsu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        menCadUsu.setText("Usu??rios");
        menCadUsu.setEnabled(false);
        menCadUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCadUsuActionPerformed(evt);
            }
        });
        menCad.add(menCadUsu);

        Menu.add(menCad);

        menRel.setText("Relat??rio");
        menRel.setEnabled(false);

        menRelCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        menRelCli.setText("Clientes");
        menRelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelCliActionPerformed(evt);
            }
        });
        menRel.add(menRelCli);

        menRelSer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        menRelSer.setText("Servi??os");
        menRelSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRelSerActionPerformed(evt);
            }
        });
        menRel.add(menRelSer);

        Menu.add(menRel);

        menAju.setText("Ajuda");

        menAjuSob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_MASK));
        menAjuSob.setText("Sobre");
        menAjuSob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menAjuSobActionPerformed(evt);
            }
        });
        menAju.add(menAjuSob);

        Menu.add(menAju);

        menOpc.setText("Op????es");

        menOpcSai.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menOpcSai.setText("Sair");
        menOpcSai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOpcSaiActionPerformed(evt);
            }
        });
        menOpc.add(menOpcSai);

        Menu.add(menOpc);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblData))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblUsuario)
                .addGap(50, 50, 50)
                .addComponent(lblData)
                .addGap(136, 136, 136)
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(Desktop)
        );

        setSize(new java.awt.Dimension(1276, 694));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menOpcSaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOpcSaiActionPerformed
        //exibe uma caixa de dialogo
        //JOption ?? a janela, null ?? o tamanho padr??o, mensagem exibida dentro
        //da caixa de dialogo, titulo da caixa de dialogo e op????o de mostrar os 
        //bot??es de sim ou nao.
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Aten????o", JOptionPane.YES_NO_OPTION);
        if (sair == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_menOpcSaiActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // A linha abaixo subtituem a label lblData pela data atual do 
        //sistema ao inicializar o form
        //Date data = new Date();
        //DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        //lblData.setText(formatador.format(data));
        
        
        
        
             
    }//GEN-LAST:event_formWindowActivated

    
    private void menAjuSobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menAjuSobActionPerformed
        // Chamando a tela sobre
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
        
    }//GEN-LAST:event_menAjuSobActionPerformed

    private void menCadUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadUsuActionPerformed
        // As linhas abaixo v??o abrr o Form tela usuatios dentro do DesktopPane
        TelaUsuarios usuario = new TelaUsuarios();
        usuario.setVisible(true);
        Desktop.add(usuario);
    }//GEN-LAST:event_menCadUsuActionPerformed

    private void menCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadCliActionPerformed
        // TODO add your handling code here:
        TelaClientes clientes = new TelaClientes();
        clientes.setVisible(true);
        Desktop.add(clientes);
        
        
        
        
        
    }//GEN-LAST:event_menCadCliActionPerformed

    private void menCadOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCadOSActionPerformed
        // TODO add your handling code here:
        TelaOS tela = new TelaOS();
        tela.setVisible(true);
        Desktop.add(tela);
    }//GEN-LAST:event_menCadOSActionPerformed

    private void menRelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelCliActionPerformed
        // Gerando relat??rio de clientes
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emiss??o deste relat??rio?", "Aten????o", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            try {
                //Usando a classe JasperPrint  para preparar a impressao de um relat??rio
                JasperPrint print = JasperFillManager.fillReport("D:/Programa????o/IReport/clientes.jasper",null,conexao);
                //A linha abaixo exibe o relatorio atraves da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
            }
        }
        
        
    }//GEN-LAST:event_menRelCliActionPerformed

    private void menRelSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRelSerActionPerformed
        // Gerando relat??rio de Servi??os
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a emiss??o deste relat??rio?", "Aten????o", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            //emitindo o relat??rios com framework JasperReports
            try {
                //Usando a classe JasperPrint  para preparar a impressao de um relat??rio
                JasperPrint print = JasperFillManager.fillReport("D:/Programa????o/IReport/servicos.jasper",null,conexao);
                //A linha abaixo exibe o relatorio atraves da classe JasperViewer
                JasperViewer.viewReport(print, false);
            } catch (Exception e) {
            }
        }
        
    }//GEN-LAST:event_menRelSerActionPerformed

    private void lblDataPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblDataPropertyChange
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
    }//GEN-LAST:event_lblDataPropertyChange

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new TelaPrincipal().setVisible(true);
                TelaPrincipal tela = new TelaPrincipal();
                tela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                tela.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt){
                    JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair", "Aten????o", JOptionPane.YES_OPTION);
                    System.exit(0);
                }               
});
          tela.setVisible(true);
    }
       
        });
                }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblLogo;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu menAju;
    private javax.swing.JMenuItem menAjuSob;
    private javax.swing.JMenu menCad;
    private javax.swing.JMenuItem menCadCli;
    private javax.swing.JMenuItem menCadOS;
    public static javax.swing.JMenuItem menCadUsu;
    private javax.swing.JMenu menOpc;
    private javax.swing.JMenuItem menOpcSai;
    public static javax.swing.JMenu menRel;
    private javax.swing.JMenuItem menRelCli;
    private javax.swing.JMenuItem menRelSer;
    // End of variables declaration//GEN-END:variables

            private TelaPrincipal TelaPrincipal() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
