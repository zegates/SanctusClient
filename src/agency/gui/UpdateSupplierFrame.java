/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.gui;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import agency.persistance.controller.SupplierJpaController;
import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.Supplier;
import agency.persistance.factory.ControllerFactory;

/**
 *
 * @author Thilina
 */
public class UpdateSupplierFrame extends javax.swing.JDialog {

    private final SupplierJpaController sjc;

    /**
     * Creates new form UpdateSupplierFrame
     */
    UpdateSupplierFrame(java.awt.Frame parent, boolean modal, Object[] objects) {
        super(parent, modal);
        initComponents();
        txtSupID.setText(objects[0] + "");
        txtCompTitle.setText(objects[1] + "");
        txtCEO.setText(objects[2] + "");
        txtLocation.setText(objects[3] + "");
        txtCompTel.setText(objects[4] + "");
        txtCompEmail.setText(objects[5] + "");
        sjc = ControllerFactory.getSupplierJpaController();
        validation();
        setId();
        setLocationRelativeTo(null);
//        System.gc();
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
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtCompTitle = new javax.swing.JTextField();
        txtLocation = new javax.swing.JTextField();
        txtCompTel = new javax.swing.JTextField();
        txtCompEmail = new javax.swing.JTextField();
        txtCEO = new javax.swing.JTextField();
        cmbCompCEOTitle = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        txtSupID = new javax.swing.JTextField();
        txtASDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnUpdateSupplier = new javax.swing.JButton();
        btnAddSupplier1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Supplier");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Supplier ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Droid Sans", 1, 10))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel17.setText("Name Of the Company");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel19.setText("Location (Address)");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel20.setText("Telephone");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel21.setText("Email");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel23.setText("Name Of CEO");

        txtCompTitle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCompTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCompTitleKeyReleased(evt);
            }
        });

        txtLocation.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtLocation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLocationKeyReleased(evt);
            }
        });

        txtCompTel.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCompTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCompTelKeyReleased(evt);
            }
        });

        txtCompEmail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCompEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCompEmailKeyReleased(evt);
            }
        });

        txtCEO.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCEO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCEOKeyReleased(evt);
            }
        });

        cmbCompCEOTitle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmbCompCEOTitle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mr.", "Mrs.", "Miss" }));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel28.setText("Supplier ID");

        txtSupID.setEditable(false);
        txtSupID.setBackground(new java.awt.Color(255, 186, 0));
        txtSupID.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtSupID.setForeground(new java.awt.Color(255, 255, 255));
        txtSupID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupIDActionPerformed(evt);
            }
        });

        txtASDate.setBackground(new java.awt.Color(204, 204, 204));
        txtASDate.setEditable(false);
        txtASDate.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtASDate.setFocusable(false);
        txtASDate.setRequestFocusEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("Date");

        btnUpdateSupplier.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnUpdateSupplier.setText("UPDATE SUPPLIER");
        btnUpdateSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSupplierActionPerformed(evt);
            }
        });

        btnAddSupplier1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnAddSupplier1.setText("EXIT");
        btnAddSupplier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSupplier1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnUpdateSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddSupplier1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19)
                                .addComponent(jLabel20)
                                .addComponent(jLabel21)
                                .addComponent(jLabel23))
                            .addGap(62, 62, 62)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtLocation)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCompTel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCompEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(cmbCompCEOTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCEO))))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(jLabel28))
                            .addGap(38, 38, 38)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(txtSupID, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtASDate, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(246, 246, 246))
                                .addComponent(txtCompTitle)))))
                .addGap(57, 57, 57))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtASDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(txtSupID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtCompTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtCompTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtCompEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtCEO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCompCEOTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateSupplier)
                    .addComponent(btnAddSupplier1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCompTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompTitleKeyReleased
        validation();
    }//GEN-LAST:event_txtCompTitleKeyReleased

    private void txtLocationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLocationKeyReleased
        validation();
    }//GEN-LAST:event_txtLocationKeyReleased

    private void txtCompTelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompTelKeyReleased
        validation();
    }//GEN-LAST:event_txtCompTelKeyReleased

    private void txtCompEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCompEmailKeyReleased
        validation();
    }//GEN-LAST:event_txtCompEmailKeyReleased

    private void txtCEOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCEOKeyReleased
        validation();
    }//GEN-LAST:event_txtCEOKeyReleased

    private void txtSupIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSupIDActionPerformed

    private void btnUpdateSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSupplierActionPerformed
        try {
            Supplier supplier =sjc.findSupplier(Long.parseLong(txtSupID.getText()));
            supplier.setAddress(txtLocation.getText());
            supplier.setCompName(txtCompTitle.getText());
            supplier.setEmail(txtCompEmail.getText());
            supplier.setDateAdded(new Date(Calendar.getInstance().getTimeInMillis()));
            supplier.setSuid(Long.parseLong(txtSupID.getText()));
            supplier.setName(txtCEO.getText());
            supplier.setTimeAdded(new Time(Calendar.getInstance().getTimeInMillis()));
            supplier.setTpno(txtCompTel.getText());
            sjc.edit(supplier);
            JOptionPane.showMessageDialog(this, "Supplier Updated Successfully", "Insert Record", JOptionPane.INFORMATION_MESSAGE);
            setId();
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(null, "Error occured in updating .\n"
                                    + ex.getMessage(), "NonexistentEntity Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error occured in updating .\n"
                                    + ex.getMessage(), "NonexistentEntity Error", JOptionPane.ERROR_MESSAGE);
        }
//        System.gc();
    }//GEN-LAST:event_btnUpdateSupplierActionPerformed

    private void btnAddSupplier1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSupplier1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddSupplier1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSupplier1;
    private javax.swing.JButton btnUpdateSupplier;
    private javax.swing.JComboBox cmbCompCEOTitle;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField txtASDate;
    private javax.swing.JTextField txtCEO;
    private javax.swing.JTextField txtCompEmail;
    private javax.swing.JTextField txtCompTel;
    private javax.swing.JTextField txtCompTitle;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtSupID;
    // End of variables declaration//GEN-END:variables

    private void validation() {
        if (!"".equals(txtCEO.getText()) && !"".equals(txtLocation.getText()) && !"".equals(txtCompTel.getText()) && !"".equals(txtCompTitle.getText())) {
            btnUpdateSupplier.setEnabled(true);
        } else {
            btnUpdateSupplier.setEnabled(false);
        }
//        System.gc();
    }

    private void setId() {
        DateFormat dfD = new SimpleDateFormat("yyyy-MM-dd hh:mm:aa");
        txtASDate.setText(dfD.format(Calendar.getInstance().getTimeInMillis()));
//        System.gc();
    }
}