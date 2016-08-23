/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.gui;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import agency.other.OtherController;
import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.LogSessionController;
import agency.persistance.controller.remote.OrdersController;
import agency.persistance.factory.ControllerFactory;
import com.zegates.sanctus.services.remote.LogSession;
import com.zegates.sanctus.services.remote.OrderDetail;
import com.zegates.sanctus.services.remote.Orders;
import com.zegates.sanctus.services.remote.SupplyOrder;

/**
 *
 * @author Sandaruwan
 */
public class FinalizeFrame extends javax.swing.JInternalFrame {

//    class TmpItem {
//
//        public TmpItem(Item item, boolean lf) {
//            this.item = item;
//            this.lf = lf;
//        }
//        Item item;
//        boolean lf;
//
////            void addSoldQty(int qty){
////                soldQty+=qty;
////            }
//        @Override
//        public boolean equals(Object obj) {
//            if (obj == null) {
//                return false;
//            }
//            if (getClass() != obj.getClass()) {
//                return false;
//            }
//            final hotelbar.gui.FinalizeFrame.TmpItem other = (hotelbar.gui.FinalizeFrame.TmpItem) obj;
//            if (!Objects.equals(this.item, other.item)) {
//                return false;
//            }
//            if (this.lf != other.lf) {
//                return false;
//            }
//            return true;
//        }
//
//        @Override
//        public int hashCode() {
//            int hash = 7;
//            hash = 67 * hash + Objects.hashCode(this.item);
//            hash = 67 * hash + (this.lf ? 1 : 0);
//            return hash;
//        }
//    }
    LogSessionController lsjc;
    OrdersController ojp;
    DefaultTableModel dtm;
    LogSession session = null;
    MainFrame mainFrame;

    /**
     * Creates new form FinalizeFrame
     */
    public FinalizeFrame(MainFrame mf) {
        initComponents();
        this.mainFrame = mf;
        lsjc = ControllerFactory.getSessionController();
        this.session = lsjc.findLogSession(mf.getLogSession().getSeid());//OtherController.getLogSession(mf.session);  

        ojp = ControllerFactory.getOrdersController();
        txtSeid.setText(OtherController.formatCode("SE", session.getSeid(), 8));

        dtm = (DefaultTableModel) tbl.getModel();
        tbl.setModel(dtm);
        refreshFields();
//        System.gc();

    }

    public void refreshFields() {

        LogSession logSession = lsjc.findLogSession(session.getSeid());

        List<Orders> orderss = logSession.getOrderss();
        List<SupplyOrder> supplyOrders = logSession.getSupplyOrders();

        List<Object> list = new ArrayList<>();

        list.addAll(orderss);
        list.addAll(supplyOrders);

        double ordersTotal = 0;
        double supplyTotal = 0;

        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Orders && o2 instanceof Orders) {
                    Orders ob1 = (Orders) o1;
                    Orders ob2 = (Orders) o2;

                    if (ob1.getDateAdded().getDate().after(ob2.getDateAdded().getDate())) {
                        return 1;
                    } else if (ob1.getDateAdded().getDate().before(ob2.getDateAdded().getDate())) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
                if (o1 instanceof Orders && o2 instanceof SupplyOrder) {
                    Orders ob1 = (Orders) o1;
                    SupplyOrder ob2 = (SupplyOrder) o2;

                    if (ob1.getDateAdded().getDate().after(ob2.getDateAdded().getDate())) {
                        return 1;
                    } else if (ob1.getDateAdded().getDate().before(ob2.getDateAdded().getDate())) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
                if (o1 instanceof SupplyOrder && o2 instanceof Orders) {
                    SupplyOrder ob1 = (SupplyOrder) o1;
                    Orders ob2 = (Orders) o2;

                    if (ob1.getDateAdded().getDate().after(ob2.getDateAdded().getDate())) {
                        return 1;
                    } else if (ob1.getDateAdded().getDate().before(ob2.getDateAdded().getDate())) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
                if (o1 instanceof SupplyOrder && o2 instanceof SupplyOrder) {
                    SupplyOrder ob1 = (SupplyOrder) o1;
                    SupplyOrder ob2 = (SupplyOrder) o2;

                    if (ob1.getDateAdded().getDate().after(ob2.getDateAdded().getDate())) {
                        return 1;
                    } else if (ob1.getDateAdded().getDate().before(ob2.getDateAdded().getDate())) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
                return 0;
            }
        });

        double wages = 0d;
        dtm.setRowCount(0);
        for (Object object : list) {
            if (object instanceof Orders) {
                Orders o = (Orders) object;
                dtm.addRow(new Object[]{
                    "Cust Order",
                    OtherController.formatCode("OD", o.getOid(), 8),
                    o.getCustName(),
                    o.getTpNo(),
                    o.getDateAdded(),
                    o.getTimeAdded(),
                    OtherController.formatPrice(o.getDiscount()),
                    OtherController.formatPrice(o.getTotal())
                });
                ordersTotal += o.getTotal() - o.getDiscount();
                List<OrderDetail> orderDetails = o.getOrderDetails();
                for (int i = 0; i < orderDetails.size(); i++) {
                    OrderDetail orderDetail = orderDetails.get(i);
                    wages += orderDetail.getQty() * orderDetail.getSupplyOrderDetail().getBuyingPrice();
                }

            } else if (object instanceof SupplyOrder) {
                SupplyOrder so = (SupplyOrder) object;
                if (so.getSoid() != -1) {
                    dtm.addRow(new Object[]{
                        "Supply Order",
                        OtherController.formatCode("SU", so.getSoid(), 8),
                        so.getSupplier().getName(),
                        so.getSupplier().getTpno(),
                        so.getDateAdded(),
                        so.getTimeAdded(),
                        OtherController.formatPrice(so.getDiscount()),
                        OtherController.formatPrice(so.getTotal())
                    });
                    supplyTotal += so.getTotal() - so.getDiscount();
                }
            }
        }
        /**
         * Sets Dates and Time
         */
        txtDate.setText(session.getDateStarted().toString());
        txtDateTill.setText(OtherController.getDate());
        txtFromTime.setText(session.getTimeStarted().toString());
        txtTillTime.setText(OtherController.getCurretTime());

        txtStockWages.setText(OtherController.formatPrice(supplyTotal));
        txtTotalSales.setText(OtherController.formatPrice(ordersTotal));
        txtTurnOver.setText(OtherController.formatPrice(ordersTotal - wages));
        txtTotExpenditures.setText(OtherController.formatPrice(ordersTotal - supplyTotal));
        txtWages.setText(OtherController.formatPrice(wages));
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSeid = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        txtFromTime = new javax.swing.JTextField();
        txtTillTime = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtTotalSales = new javax.swing.JTextField();
        txtStockWages = new javax.swing.JTextField();
        txtTurnOver = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnPrintReports = new javax.swing.JButton();
        btnFinalizeSession = new javax.swing.JButton();
        txtDateTill = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtWages = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotExpenditures = new javax.swing.JTextField();
        txtTotTurnover = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Finalize Sessions");

        jPanel1.setBackground(new java.awt.Color(169, 208, 214));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setText("Session ID");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("Date From");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setText("Session From");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("Till");

        txtSeid.setEditable(false);
        txtSeid.setBackground(new java.awt.Color(255, 175, 0));
        txtSeid.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        txtDate.setEditable(false);
        txtDate.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        txtFromTime.setEditable(false);
        txtFromTime.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        txtTillTime.setEditable(false);
        txtTillTime.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(169, 208, 214));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Session Final Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Droid Sans", 1, 10))); // NOI18N

        tbl.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction Type", "ID", "Name", "Tel", "Date", "Time", "Discount", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setText("Total Sales");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Total Supply Wages");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel7.setText("Turnover");

        txtTotalSales.setEditable(false);
        txtTotalSales.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTotalSales.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtStockWages.setEditable(false);
        txtStockWages.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtStockWages.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtTurnOver.setEditable(false);
        txtTurnOver.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTurnOver.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButton1.setFont(new java.awt.Font("Droid Sans", 1, 11)); // NOI18N
        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnPrintReports.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnPrintReports.setText("Print Report");
        btnPrintReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintReportsActionPerformed(evt);
            }
        });

        btnFinalizeSession.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnFinalizeSession.setText("Finalize Session");
        btnFinalizeSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizeSessionActionPerformed(evt);
            }
        });

        txtDateTill.setEditable(false);
        txtDateTill.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel8.setText("Till");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel9.setText("Total Wages");

        txtWages.setEditable(false);
        txtWages.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtWages.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel10.setText("Total Expenditures");

        txtTotExpenditures.setEditable(false);
        txtTotExpenditures.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTotExpenditures.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtTotTurnover.setEditable(false);
        txtTotTurnover.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTotTurnover.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel11.setText("Total Turnover");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSeid, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDateTill, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFromTime, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTillTime, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(btnPrintReports, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnFinalizeSession)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalSales, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(txtTurnOver)
                            .addComponent(txtWages))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotTurnover, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotExpenditures, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStockWages, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDate, txtDateTill, txtFromTime, txtTillTime});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFromTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTillTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtDateTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtStockWages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtTotalSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(txtWages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotExpenditures, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtTotTurnover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTurnOver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrintReports)
                    .addComponent(jButton1)
                    .addComponent(btnFinalizeSession))
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

    private void btnPrintReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintReportsActionPerformed
        try {
            JRTableModelDataSource jrt = new JRTableModelDataSource(dtm);
            String repSource = "./src/tireshop/reports/FinalReport1.jrxml";
            Map<String, Object> param = new HashMap<>();

            param.put("SessionID", OtherController.formatCode("SE", session.getSeid(), 8));
            param.put("SessionFrom", session.getDateStarted().toString() + " at "
                    + session.getTimeStarted().toString());
            param.put("SessionTill", OtherController.getDate() + " at " + OtherController.getCurretTime());
            param.put("UserID", OtherController.formatCode("U", session.getLogUser().getUid(), 5));
            param.put("UserName", session.getLogUser().getName());
            param.put("TotalSales", txtTotalSales.getText());
            param.put("TotalSupplyWages", txtStockWages.getText());
            param.put("Turnover", txtTurnOver.getText());

            JasperReport jr = JasperCompileManager.compileReport(repSource);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, jrt);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(FinalizeFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.gc();
    }//GEN-LAST:event_btnPrintReportsActionPerformed

    private void btnFinalizeSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizeSessionActionPerformed
        int conf = JOptionPane.showConfirmDialog(null, "Are sure to finalize this session", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (conf == 0) {
            Date dateSQL = new Date(Calendar.getInstance().getTimeInMillis());
            Time timeSQL = new Time(Calendar.getInstance().getTimeInMillis());
            com.zegates.sanctus.services.remote.Date date = new com.zegates.sanctus.services.remote.Date();
            date.setDate(dateSQL);
            com.zegates.sanctus.services.remote.Time time = new com.zegates.sanctus.services.remote.Time();
            time.setTime(timeSQL);
            session.setFinalised(true);
            session.setDateEnded(date);
            session.setTimeEnded(time);
            try {
                lsjc.edit(session);
                JOptionPane.showMessageDialog(null, "Your session is successfully "
                        + "finalized. Application will be restarted",
                        "Session Finalized", JOptionPane.INFORMATION_MESSAGE);
                new LoginFrame().setVisible(true);
                this.dispose();
                mainFrame.dispose();
                //        System.gc();
            } catch (NonexistentEntityException ex) {
                JOptionPane.showMessageDialog(null, "Entity Error "
                        + ex.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FinalizeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Exception "
                        + ex.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FinalizeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        System.gc();
    }//GEN-LAST:event_btnFinalizeSessionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinalizeSession;
    private javax.swing.JButton btnPrintReports;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDateTill;
    private javax.swing.JTextField txtFromTime;
    private javax.swing.JTextField txtSeid;
    private javax.swing.JTextField txtStockWages;
    private javax.swing.JTextField txtTillTime;
    private javax.swing.JTextField txtTotExpenditures;
    private javax.swing.JTextField txtTotTurnover;
    private javax.swing.JTextField txtTotalSales;
    private javax.swing.JTextField txtTurnOver;
    private javax.swing.JTextField txtWages;
    // End of variables declaration//GEN-END:variables

    private Long stripOutSessionCode(String string) {
        return Long.parseLong(string.substring(2));
    }
}
