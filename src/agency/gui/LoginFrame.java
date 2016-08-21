/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.gui;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import agency.persistance.controller.LogUserJpaController;
import agency.persistance.controller.remote.LogUserController;
import agency.persistance.entity.LogUser;
import agency.persistance.factory.ControllerFactory;

/**
 *
 * @author Sandaruwan
 */
public class LoginFrame extends javax.swing.JFrame {

    private LogUserController lujc;

    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        initComponents();
        setLocationRelativeTo(null);
        lujc = ControllerFactory.getLogUserJpaController();
        txtUname.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        txtUname = new javax.swing.JTextField();
        pwd = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Please login to continue");

        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLoginKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnLoginKeyReleased(evt);
            }
        });

        txtUname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUnameKeyReleased(evt);
            }
        });

        pwd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pwd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pwdKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agency/img/zecured.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(btnLogin))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 172, Short.MAX_VALUE)
                                .addComponent(btnExit))
                            .addComponent(txtUname)
                            .addComponent(pwd))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String pass = new String(pwd.getPassword());
        boolean verif = false;
        List<LogUser> users = lujc.findLogUserEntities();

        for (int i = 0; i < users.size(); i++) {
            LogUser logUser = users.get(i);
            if (logUser.getUsername().equals(txtUname.getText())
                    && logUser.getPw().equals(pass)) {
                new StartSession(logUser.getUid()).setVisible(true);
                verif = true;
                break;
            } else {
                verif = false;
            }
        }
        if (!verif) {
            JOptionPane.showMessageDialog(null, "Wrong Password or Username",
                    "Error in logging", JOptionPane.ERROR_MESSAGE);
            txtUname.requestFocus();
        } else {
            this.dispose();
        }
//        System.gc();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtUnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pwd.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            pwd.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtUname.requestFocus();
        }
    }//GEN-LAST:event_txtUnameKeyReleased

    private void pwdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pwdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLogin.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            btnLogin.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtUname.requestFocus();
        }
    }//GEN-LAST:event_pwdKeyReleased

    private void btnLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLoginKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLogin.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            pwd.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            txtUname.requestFocus();
        }
    }//GEN-LAST:event_btnLoginKeyReleased

    private void btnLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLoginKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        try {
            /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//            UIManager.setLookAndFeel(new NimbusLookAndFeel());
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField pwd;
    private javax.swing.JTextField txtUname;
    // End of variables declaration//GEN-END:variables
}
