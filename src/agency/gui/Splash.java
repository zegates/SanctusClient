/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import agency.other.LReader;
import agency.persistance.controller.remote.OrdersController;
import agency.persistance.factory.ControllerFactory;
import agency.remote.DBBackup;

/**
 *
 * @author Sandaruwan
 */
public class Splash extends javax.swing.JFrame {

    private Timer t;
    private Splash splash;
    private ArrayList<JFrame> frames;
    private static Point mouseDownCompCoords;

    /**
     * Creates new form Splash
     */
    public Splash() {
        initComponents();
        this.setBackground(new Color(0, 0, 0, 0));
//        this.setBackground(new Color(0, 0, 0));
        lblStat.setOpaque(true);
        lblStat.setBackground(new Color(255, 255, 255));
        
        setLocationRelativeTo(null);
        splash = this;
        prgsStat.setValue(0);
        frames = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int value = prgsStat.getValue();
                    while (value <= 99) {
                     lblStat.setOpaque(true);
//                       lblStat.setText("");
//                    System.out.println("value"+value);
                        prgsStat.setValue(prgsStat.getValue() + 1);
                        value = prgsStat.getValue();
                        if (value < 5) {
                            
                            lblStat.setText("Loading...");
                        }
                        if (value > 5 && value < 10) {
                            lblStat.setText("Reading Databases...");
                        }

                        if (value == 10) {
                            lblStat.setText("Loading Modules...");
                            frames.add(new LoginFrame());
                            prgsStat.setValue(25);
                        }
                        if (value == 26) {
                            lblStat.setText("Loading Sessions...");
                            frames.add(new StartSession());
                            prgsStat.setValue(55);
                        }
                        if (value == 60) {
                            lblStat.setText("Starting...");
//                            frames.add(new MainFrame(null, null));
                            prgsStat.setValue(80);
                        }
                        if (value == 82) {
                            while(!check()){
                            }
                    //        System.gc();
                            prgsStat.setValue(90);
                            

                        }
                        if (value == 99) {

                            LReader lr = new LReader();
                            if (lr.read()) {
                                if (!lr.isRemoteExist()) {
                                    JOptionPane.showMessageDialog(null, "Files are missing\n"
                                            + "Please contact the operator.", "Files are missing", JOptionPane.INFORMATION_MESSAGE);
                                    System.exit(0);
                                }
                                new LoginFrame().setVisible(true);
                                frames = null;
                        //        System.gc();
                                splash.dispose();
                        //        System.gc();
                            } else {
//                                JOptionPane.showMessageDialog(null, "Your product is not licensed\n"
//                                        + "Please contact the operator.", "Version Invalid", JOptionPane.INFORMATION_MESSAGE);
                                OrdersController ojc = ControllerFactory.getOrdersController();
                                int oc = ojc.getOrdersCount();
                                if (oc >= 50) {

                                    JOptionPane.showMessageDialog(null, "This version has expired.\n"
                                            + "Please contact the operator.", "Version Expired", JOptionPane.INFORMATION_MESSAGE);
                                    System.exit(0);
                                }

                                new LoginFrame().setVisible(true);
                                frames = null;
                        //        System.gc();
                                splash.dispose();
                        //        System.gc();
                            }
                        }
                        try {
                            Thread.currentThread().sleep(50);
                    //        System.gc();
                        } catch (InterruptedException ex) {
                            JOptionPane.showMessageDialog(null, "Error occured in the thread.\n"
                                    + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            System.exit(0);
                        }
                        lblStat.setOpaque(false);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error occured in initializing.\n"
                            + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                    System.exit(0);
                }

            }

            private boolean check() {

                lblStat.setText("Updating Remote Database...");
                try {
                    int backUpDB = DBBackup.backUpDB();
                    if (backUpDB == 0) {
                        int restoreBackUp = DBBackup.restoreBackUp();
                        if (restoreBackUp == 0) {
                            JOptionPane.showMessageDialog(null, "Remote Server"
                                    + " Was Successfully Restored", "Successful", JOptionPane.INFORMATION_MESSAGE);
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Check Connection with remote Server",
                                    "Internet Error", JOptionPane.INFORMATION_MESSAGE);
                            return true;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error in Connection with remote Server",
                                "Internet Error", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    }

                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error Attempt \n" + ex.getMessage(),
                            "ClassNotFound ", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Select correct mysql bin directory \n" + ex.getMessage(),
                            "IO Exception Occured", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
//
                    File f = new File("sqlconfig.ini");
                    if (f.exists()) {
                        f.delete();
                    }
                    
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, "Error Attempt \n" + ex.getMessage(),
                            "Interrupted Exception Occured", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
                return false;
            }
        }).start();
//        System.gc();

//        t = new Timer(150, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int value = prgsStat.getValue();
//                if (value < 5) {
//                    lblStat.setText("Loading...");
//                }
//                if (value > 5 && value < 10) {
//                    lblStat.setText("Reading Databases...");
//                }
//                prgsStat.setValue(prgsStat.getValue() + 1);
//                if (value == 10) {
//                    lblStat.setText("Loading Modules...");
//                    frames.add(new LoginFrame());
//                    prgsStat.setValue(25);
//                }
//                if (value == 26) {
//                    lblStat.setText("Loading Sessions...");
//                    frames.add(new StartSession());
//                    prgsStat.setValue(55);
//                }
//                if (value == 60) {
//                    lblStat.setText("Starting...");
//                    frames.add(new MainFrame(null, null));
//                    prgsStat.setValue(80);
//                }
//                if (value == 100) {
//
//                    OrdersController ojc = ControllerFactory.getOrdersController();
//                    int oc = ojc.getOrdersCount();
//                    if (oc > 50) {
//
//                        JOptionPane.showMessageDialog(null, "This Beta version has expired.\n"
//                                + "Please contact the operator.", "Version Expired", JOptionPane.INFORMATION_MESSAGE);
//                        System.exit(0);
//                    }
//
//                    t.stop();
//                    new LoginFrame().setVisible(true);
//                    frames = null;
//            //        System.gc();
//                    splash.dispose();
//                }
//            }
//        });
//        t.start();

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        prgsStat = new javax.swing.JProgressBar();
        lblStat = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agency/img/emblem8.png"))); // NOI18N

        lblStat.setFont(new java.awt.Font("Dosis", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dosis", 0, 14)); // NOI18N
        jLabel3.setText("<html>Distribution Management System for Gamlath Tire Service<br>Version 2.0<br>Build 12/10/2012<br>www.zegates.com");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(prgsStat, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                        .addComponent(lblStat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(155, 155, 155))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prgsStat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStat, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        mouseDownCompCoords = evt.getPoint();
    }//GEN-LAST:event_formMousePressed

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
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Splash().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblStat;
    private javax.swing.JProgressBar prgsStat;
    // End of variables declaration//GEN-END:variables
}
