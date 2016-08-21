/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import agency.other.OtherController;
import agency.persistance.controller.LogSessionJpaController;
import agency.persistance.entity.LogSession;
import agency.persistance.entity.Orders;
import agency.persistance.entity.SupplyOrder;
import agency.persistance.factory.ControllerFactory;

/**
 *
 * @author Sandaruwan
 */
public class SessionFrame extends javax.swing.JInternalFrame {

    private MainFrame mainFrame;
    private LogSessionJpaController lsjc;
    DefaultTableModel dtmSession;
    DefaultTableModel dtmSessionDetails;

    /**
     * Creates new form SessionFrame
     */
    public SessionFrame(MainFrame mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        lsjc = ControllerFactory.getSessionJpaController();

        dtmSession = (DefaultTableModel) tblSessions.getModel();
        dtmSessionDetails = (DefaultTableModel) tblSessionDetails.getModel();

        refreshFields();
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
        jLabel1 = new javax.swing.JLabel();
        txtUid = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSessions = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSessionDetails = new javax.swing.JTable();
        txtUserName = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Session Frame");
        setToolTipText("View details about finalized sessions");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        jLabel1.setText("User ID");

        txtUid.setEditable(false);
        txtUid.setBackground(new java.awt.Color(99, 144, 177));
        txtUid.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "List of Sessions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Droid Sans", 1, 10))); // NOI18N

        tblSessions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Session ID", "Date Started", "Time Started", "Date Ended", "Time Ended", "Total Orders", "Total Supplies"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSessions.setColumnSelectionAllowed(true);
        tblSessions.getTableHeader().setReorderingAllowed(false);
        tblSessions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSessionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSessions);
        tblSessions.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        jLabel2.setText("Name");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Session Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Droid Sans", 1, 10))); // NOI18N

        tblSessionDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Transaction Type", "ID", "Name", "Tel", "Date", "Time", "Discounts", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSessionDetails);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        );

        txtUserName.setEditable(false);
        txtUserName.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUid, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 435, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSessionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSessionsMouseClicked
        int row = tblSessions.getSelectedRow();
        if (row != -1) {
            Long sessID = stripOutSessionCode((String) dtmSession.getValueAt(row, 0));
            LogSession logSession = lsjc.findLogSession(sessID);

            List<Orders> orderss = logSession.getOrderss();
            List<SupplyOrder> supplyOrders = logSession.getSupplyOrders();

            List<Object> list = new ArrayList<>();

            list.addAll(orderss);
            list.addAll(supplyOrders);

            Collections.sort(list, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    if (o1 instanceof Orders && o2 instanceof Orders) {
                        Orders ob1 = (Orders) o1;
                        Orders ob2 = (Orders) o2;

                        if (ob1.getDateAdded().after(ob2.getDateAdded())) {
                            return 1;
                        } else if (ob1.getDateAdded().before(ob2.getDateAdded())) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                    if (o1 instanceof Orders && o2 instanceof SupplyOrder) {
                        Orders ob1 = (Orders) o1;
                        SupplyOrder ob2 = (SupplyOrder) o2;

                        if (ob1.getDateAdded().after(ob2.getDateAdded())) {
                            return 1;
                        } else if (ob1.getDateAdded().before(ob2.getDateAdded())) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                    if (o1 instanceof SupplyOrder && o2 instanceof Orders) {
                        SupplyOrder ob1 = (SupplyOrder) o1;
                        Orders ob2 = (Orders) o2;

                        if (ob1.getDateAdded().after(ob2.getDateAdded())) {
                            return 1;
                        } else if (ob1.getDateAdded().before(ob2.getDateAdded())) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                    if (o1 instanceof SupplyOrder && o2 instanceof SupplyOrder) {
                        SupplyOrder ob1 = (SupplyOrder) o1;
                        SupplyOrder ob2 = (SupplyOrder) o2;

                        if (ob1.getDateAdded().after(ob2.getDateAdded())) {
                            return 1;
                        } else if (ob1.getDateAdded().before(ob2.getDateAdded())) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                    return 0;
                }
            });
            dtmSessionDetails.setRowCount(0);
            for (Object object : list) {
                if (object instanceof Orders) {
                    Orders o = (Orders) object;
                    dtmSessionDetails.addRow(new Object[]{
                                "Cust Order",
                                OtherController.formatCode("OD", o.getOid(), 8),
                                o.getCustName(),
                                o.getTpNo(),
                                o.getDateAdded(),
                                o.getTimeAdded(),
                                OtherController.formatPrice(o.getDiscount()),
                                OtherController.formatPrice(o.getTotal())
                            });
                } else if (object instanceof SupplyOrder) {
                    SupplyOrder so = (SupplyOrder) object;
                    if (so.getSoid() != -1) {
                        dtmSessionDetails.addRow(new Object[]{
                                    "Supply Order",
                                    OtherController.formatCode("SU", so.getSoid(), 8),
                                    so.getSupplier().getCompName(),
                                    so.getSupplier().getTpno(),
                                    so.getDateAdded(),
                                    so.getTimeAdded(),
                                    OtherController.formatPrice(so.getDiscount()),
                                    OtherController.formatPrice(so.getTotal())
                                });
                    }
                }
            }

        }
    }//GEN-LAST:event_tblSessionsMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblSessionDetails;
    private javax.swing.JTable tblSessions;
    private javax.swing.JTextField txtUid;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables

    private void refreshFields() {

        dtmSession.setRowCount(0);
        List<LogSession> logSessions = lsjc.findLogSessionEntities();
        txtUid.setText(OtherController.formatCode("U", mainFrame.getLogSession().getSeid(), 5));
        txtUserName.setText(mainFrame.getLogSession().getLogUser().getName());
        for (LogSession logSession : logSessions) {
            List<Orders> orderss = logSession.getOrderss();
            double ordersTotal = 0;
            for (Orders orders : orderss) {
                ordersTotal += orders.getTotal() - orders.getDiscount();
            }
            List<SupplyOrder> supplyOrders = logSession.getSupplyOrders();
            double supplyTotal = 0;
            for (SupplyOrder supplyOrder : supplyOrders) {
                supplyTotal += supplyOrder.getTotal() - supplyOrder.getDiscount();
            }
            dtmSession.addRow(new Object[]{
                        OtherController.formatCode("SE", logSession.getSeid(), 8),
                        logSession.getDateStarted(),
                        logSession.getTimeStarted(),
                        logSession.getDateEnded(),
                        logSession.getTimeStarted(),
                        OtherController.formatPrice(ordersTotal),
                        OtherController.formatPrice(supplyTotal)
                    });
        }
    }

    private Long stripOutSessionCode(String string) {
        return Long.parseLong(string.substring(2));
    }
}