package agency.gui;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.SupplierController;
import agency.persistance.controller.remote.SupplyOrderController;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import agency.persistance.factory.ControllerFactory;
import com.zegates.sanctus.services.remote.Supplier;
import com.zegates.sanctus.services.remote.SupplyOrder;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Thilina
 */
public class UpdateSupplyOrderFrame extends javax.swing.JDialog {

    private final SupplyOrderController sojc;
    private final SupplierController sjc;
    private final double total;
    private final ImageIcon imgRight;
    private final ImageIcon imgWrong;

    /**
     * Creates new form UpdateSupplyOrderFrame
     */
    UpdateSupplyOrderFrame(java.awt.Frame parent, boolean modal, Object[] objects) {
        super(parent, modal);
        initComponents();
        sojc = ControllerFactory.getSupplyOrderController();
        sjc = ControllerFactory.getSupplierController();
        List<Supplier> suppliers = sjc.findSupplierEntities();
        cmbSupplierName.removeAllItems();
        for (Supplier supplier : suppliers) {
            cmbSupplierName.addItem(supplier.getCompName());
        }

        txtOrderID.setText(objects[0] + "");
        txtASDate.setText(objects[1] + "");
        cmbSupplierName.setSelectedItem(objects[2] + "");
        txtDiscount.setText(objects[3] + "");
        txtTotal.setText(objects[4] + "");
        total = (double) objects[4];

        imgRight = new ImageIcon(getClass().getResource("/agency/img/right.png"));
        imgWrong = new ImageIcon(getClass().getResource("/agency/img/wrong.png"));
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
        jLabel28 = new javax.swing.JLabel();
        txtOrderID = new javax.swing.JTextField();
        txtASDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnUpdateSuppyOrder = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        labelError = new javax.swing.JLabel();
        cmbSupplierName = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Supply Order");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Suppy Order ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Droid Sans", 1, 10))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel17.setText("Name Of Supplier");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel28.setText("Order ID");

        txtOrderID.setEditable(false);
        txtOrderID.setBackground(new java.awt.Color(255, 186, 0));
        txtOrderID.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtOrderID.setForeground(new java.awt.Color(255, 255, 255));
        txtOrderID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrderIDActionPerformed(evt);
            }
        });

        txtASDate.setEditable(false);
        txtASDate.setBackground(new java.awt.Color(204, 204, 204));
        txtASDate.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtASDate.setFocusable(false);
        txtASDate.setRequestFocusEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("Date");

        btnUpdateSuppyOrder.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btnUpdateSuppyOrder.setText("UPDATE ORDER");
        btnUpdateSuppyOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSuppyOrderActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel18.setText("Discount");

        txtDiscount.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiscountKeyReleased(evt);
            }
        });

        txtTotal.setBackground(new java.awt.Color(204, 204, 204));
        txtTotal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel19.setText("Total");

        labelError.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtASDate, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnUpdateSuppyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbSupplierName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel17, jLabel28});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtASDate, txtDiscount, txtOrderID, txtTotal});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtASDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cmbSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(labelError, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateSuppyOrder)
                    .addComponent(btnExit)))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, txtASDate, txtOrderID});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel17, jLabel28});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel18, jLabel19, txtDiscount, txtTotal});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtOrderIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrderIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrderIDActionPerformed

    private void btnUpdateSuppyOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSuppyOrderActionPerformed
        try {
            
            Date dateSQL = new Date(Calendar.getInstance().getTimeInMillis());
            Time timeSQL = new Time(Calendar.getInstance().getTimeInMillis());
//            com.zegates.sanctus.services.remote.Date date = new com.zegates.sanctus.services.remote.Date();
//            date.setDateA(dateSQL);
//            com.zegates.sanctus.services.remote.Time time = new com.zegates.sanctus.services.remote.Time();
//            time.setTimeA(timeSQL);
            java.util.Date date = new java.util.Date(System.currentTimeMillis());
              GregorianCalendar c = new GregorianCalendar();
                    c.setTime(date);
                    XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            SupplyOrder order = sojc.findSupplyOrder(Long.parseLong(txtOrderID.getText()));

            order.setDateAdded(date2);
            order.setTimeAdded(date2);
            order.setDiscount(Double.parseDouble(txtDiscount.getText()));
            order.setTotal(total - Double.parseDouble(txtDiscount.getText()));
            order.setSupplier(sjc.findSupplier((String) cmbSupplierName.getSelectedItem()));
//            System.out.println(sjc.findSupplier((String) cmbSupplierName.getSelectedItem()).getSuid());
            sojc.edit(order);
            JOptionPane.showMessageDialog(this, "Supply Order Updated "
                    + "Successfully", "Insert Record", JOptionPane.INFORMATION_MESSAGE);
            setId();
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "Error in updating "
                    + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    //        System.gc();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error in updating "
                    + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            //Logger.getLogger(UpdateSupplyOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
    //        System.gc();
        }
//        System.gc();
    }//GEN-LAST:event_btnUpdateSuppyOrderActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtDiscountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscountKeyReleased
        try {
            txtTotal.setText((total - Double.parseDouble(txtDiscount.getText())) + "");
        } catch (Exception exception) {
            txtDiscount.setText("");
        }
        validation();
    }//GEN-LAST:event_txtDiscountKeyReleased

    private void txtTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnUpdateSuppyOrder;
    private javax.swing.JComboBox cmbSupplierName;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel labelError;
    private javax.swing.JTextField txtASDate;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtOrderID;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private void validation() {
        if ("".equals(labelError.getText()) && !"0".equals(txtDiscount.getText()) && !"".equals(txtDiscount.getText())) {
            btnUpdateSuppyOrder.setEnabled(true);
        } else {
            btnUpdateSuppyOrder.setEnabled(false);

        }
//        System.gc();
    }

    private void setId() {
        DateFormat dfD = new SimpleDateFormat("yyyy-MM-dd hh:mm:aa");
        txtASDate.setText(dfD.format(Calendar.getInstance().getTimeInMillis()));
//        System.gc();
    }
}
