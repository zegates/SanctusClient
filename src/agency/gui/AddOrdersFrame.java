/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import agency.other.ComboSearch;
import agency.other.IntegerOnly;
import agency.other.OtherController;
import agency.other.SwitchComboSearch;
import agency.persistance.controller.remote.ItemController;
import agency.persistance.controller.remote.ManufacturerController;
import agency.persistance.controller.remote.OrdersController;
import agency.persistance.controller.remote.SupplyOrderDetailController;
import agency.persistance.factory.ControllerFactory;
import com.zegates.sanctus.services.remote.Item;
import com.zegates.sanctus.services.remote.LogSession;
import com.zegates.sanctus.services.remote.Manufacturer;
import com.zegates.sanctus.services.remote.OrderDetail;
import com.zegates.sanctus.services.remote.Orders;
import com.zegates.sanctus.services.remote.SupplyOrderDetail;

/**
 *
 * @author Sandaruwan
 */
public class AddOrdersFrame extends javax.swing.JInternalFrame implements Observer {

    public DefaultTableModel getDtmOrder() {
        return dtmOrder;
    }
    private ItemController ijc;
    private ManufacturerController mjc;
    private SupplyOrderDetailController sodjc;
    private OrdersController ojc;
    private boolean comboSwitch = false;
    /**
     * False this to stop Console proof printing
     */
    private boolean showProofs = false;
    private ImageIcon imgRight;
    private ImageIcon imgWrong;
    // Tablemodel of the Order Table
    private DefaultTableModel dtmOrder;
    // Total of the order
    private double total = 0;
    private Color clrRed = Color.RED;
    private Color clrBlack = Color.BLACK;
    private MainFrame mainFrame;
    private ComboSearch search;
    private SwitchComboSearch search1;

    /**
     * Creates new form AddOrdersFrame initialize components
     */
    public AddOrdersFrame(MainFrame mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        search1 = new SwitchComboSearch();
        search = new ComboSearch();
        imgRight = new ImageIcon(getClass().getResource("/tireshop/img/right.png"));
        imgWrong = new ImageIcon(getClass().getResource("/tireshop/img/wrong.png"));
        ijc = ControllerFactory.getItemController();
        mjc = ControllerFactory.getManufacturerController();
        sodjc = ControllerFactory.getSupplyOrderDetailController();
        ojc = ControllerFactory.getOrdersController();
        dtmOrder = (DefaultTableModel) tblOrders.getModel();
        dtmOrder.setRowCount(0);
        refreshFields();
        refreshOrderID();
        refreshSessionID();

        ojc.addObserver(this);

        Document docQty = txtQuantity.getDocument();
        if (docQty instanceof AbstractDocument) {
            AbstractDocument abDoc = (AbstractDocument) docQty;
            abDoc.setDocumentFilter(new IntegerOnly());
        }
        setMap();

        cmbManufacturer.requestFocus();
        //System.gc();
    }

    /**
     * This maps the order process for relevant key actions
     */
    private void setMap() {
        //Manufacturer
        cmbManufacturer.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "focusItemName");
        cmbManufacturer.getActionMap().put("focusItemName", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmbItemName.requestFocus();
            }
        });
        cmbManufacturer.getInputMap().put(KeyStroke.getKeyStroke("F1"), "clearOrder");
        cmbManufacturer.getActionMap().put("clearOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnClearOrder.doClick();
            }
        });
        cmbManufacturer.getInputMap().put(KeyStroke.getKeyStroke("F9"), "deleteRow");
        cmbManufacturer.getActionMap().put("deleteRow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeleteRow.doClick();
            }
        });
        cmbManufacturer.getInputMap().put(KeyStroke.getKeyStroke("F12"), "addOrder");
        cmbManufacturer.getActionMap().put("addOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddOrder.doClick();
            }
        });
        cmbManufacturer.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "goBack");
        cmbManufacturer.getActionMap().put("goBack", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtQuantity.isEnabled()) {
                    txtQuantity.requestFocus();
                }

            }
        });
        cmbManufacturer.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "focusItemName");

        // Item Name
        cmbItemName.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "focusItemCode");
        cmbItemName.getActionMap().put("focusItemCode", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmbItemCode.requestFocus();
            }
        });
        cmbItemName.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "goBack");
        cmbItemName.getActionMap().put("goBack", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmbManufacturer.requestFocus();
            }
        });
        cmbItemName.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "focusItemCode");
        cmbItemName.getInputMap().put(KeyStroke.getKeyStroke("F1"), "clearOrder");
        cmbItemName.getActionMap().put("clearOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnClearOrder.doClick();
            }
        });
        cmbItemName.getInputMap().put(KeyStroke.getKeyStroke("F9"), "deleteRow");
        cmbItemName.getActionMap().put("deleteRow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeleteRow.doClick();
            }
        });
        cmbItemName.getInputMap().put(KeyStroke.getKeyStroke("F12"), "addOrder");
        cmbItemName.getActionMap().put("addOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddOrder.doClick();
            }
        });

        // Item Code
        cmbItemCode.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "focusSupply");
        cmbItemCode.getActionMap().put("focusSupply", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmbBatchID.requestFocus();
            }
        });
        cmbItemCode.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "goBack");
        cmbItemCode.getActionMap().put("goBack", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmbItemName.requestFocus();
            }
        });
        cmbItemCode.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "focusSupply");
        cmbItemCode.getInputMap().put(KeyStroke.getKeyStroke("F1"), "clearOrder");
        cmbItemCode.getActionMap().put("clearOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnClearOrder.doClick();
            }
        });
        cmbItemCode.getInputMap().put(KeyStroke.getKeyStroke("F9"), "deleteRow");
        cmbItemCode.getActionMap().put("deleteRow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeleteRow.doClick();
            }
        });
        cmbItemCode.getInputMap().put(KeyStroke.getKeyStroke("F12"), "addOrder");
        cmbItemCode.getActionMap().put("addOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddOrder.doClick();
            }
        });

        // Batch ID
        cmbBatchID.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "focusQty");
        cmbBatchID.getActionMap().put("focusQty", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtQuantity.isEnabled()) {
                    txtQuantity.requestFocus();
                } else {
                    cmbManufacturer.requestFocus();
                }
            }
        });
        cmbBatchID.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "goBack");
        cmbBatchID.getActionMap().put("goBack", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmbItemCode.requestFocus();
            }
        });
        cmbBatchID.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "focusQty");
        cmbBatchID.getInputMap().put(KeyStroke.getKeyStroke("F1"), "clearOrder");
        cmbBatchID.getActionMap().put("clearOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnClearOrder.doClick();
            }
        });
        cmbBatchID.getInputMap().put(KeyStroke.getKeyStroke("F9"), "deleteRow");
        cmbBatchID.getActionMap().put("deleteRow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeleteRow.doClick();
            }
        });
        cmbBatchID.getInputMap().put(KeyStroke.getKeyStroke("F12"), "addOrder");
        cmbBatchID.getActionMap().put("addOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddOrder.doClick();
            }
        });

        //Quantity
        txtQuantity.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "focusAdd");
        txtQuantity.getActionMap().put("focusAdd", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnAddItem.isEnabled()) {
                    btnAddItem.requestFocus();
                } else {
                    cmbManufacturer.requestFocus();
                }
            }
        });
        txtQuantity.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "goBack");
        txtQuantity.getActionMap().put("goBack", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmbBatchID.requestFocus();
            }
        });
        txtQuantity.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "focusAdd");
        txtQuantity.getInputMap().put(KeyStroke.getKeyStroke("F1"), "clearOrder");
        txtQuantity.getActionMap().put("clearOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnClearOrder.doClick();
            }
        });
        txtQuantity.getInputMap().put(KeyStroke.getKeyStroke("F9"), "deleteRow");
        txtQuantity.getActionMap().put("deleteRow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeleteRow.doClick();
            }
        });
        txtQuantity.getInputMap().put(KeyStroke.getKeyStroke("F12"), "addOrder");
        txtQuantity.getActionMap().put("addOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddOrder.doClick();
            }
        });

        // Add Item
        btnAddItem.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "addItem");
        btnAddItem.getActionMap().put("addItem", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddItem.doClick();
                cmbManufacturer.requestFocus();
            }
        });
        btnAddItem.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "goBack");
        btnAddItem.getActionMap().put("goBack", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtQuantity.requestFocus();
            }
        });
        btnAddItem.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "focusQty");
        btnAddItem.getActionMap().put("focusQty", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cmbManufacturer.requestFocus();
            }
        });
        btnAddItem.getInputMap().put(KeyStroke.getKeyStroke("F1"), "clearOrder");
        btnAddItem.getActionMap().put("clearOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnClearOrder.doClick();
            }
        });
        btnAddItem.getInputMap().put(KeyStroke.getKeyStroke("F9"), "deleteRow");
        btnAddItem.getActionMap().put("deleteRow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeleteRow.doClick();
            }
        });
        btnAddItem.getInputMap().put(KeyStroke.getKeyStroke("F12"), "addOrder");
        btnAddItem.getActionMap().put("addOrder", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddOrder.doClick();
            }
        });

        //   System.gc();
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
        txtOrderID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSessionID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbManufacturer = new javax.swing.JComboBox();
        cmbItemName = new javax.swing.JComboBox();
        cmbItemCode = new javax.swing.JComboBox();
        cmbBatchID = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtSellingPrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        txtSubTotal = new javax.swing.JTextField();
        btnAddItem = new javax.swing.JButton();
        btnDeleteRow = new javax.swing.JButton();
        btnClearOrder = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lblStat = new javax.swing.JLabel();
        lblRemainder = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnAddOrder = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        spinnerDiscount = new javax.swing.JSpinner();
        btnAddItemRt = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Add Orders for Items ");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(169, 217, 224));
        jPanel1.setPreferredSize(new java.awt.Dimension(928, 548));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel1KeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ORDER ID");

        txtOrderID.setEditable(false);
        txtOrderID.setBackground(new java.awt.Color(99, 144, 177));
        txtOrderID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtOrderID.setForeground(new java.awt.Color(255, 255, 255));
        txtOrderID.setText("OD00000001");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("DATE");

        txtDate.setEditable(false);
        txtDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("SESSION ID");

        txtSessionID.setEditable(false);
        txtSessionID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        tblOrders.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Code", "Item Name", "Supply ID", "Quantity", "Selling Price", "Sub Total", "Discount(%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrders.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblOrders.getTableHeader().setReorderingAllowed(false);
        tblOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrdersMouseClicked(evt);
            }
        });
        tblOrders.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblOrdersKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrders);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("Manufacturer");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setText("Item Name");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Item Code");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel7.setText("Supply ID");

        cmbManufacturer.setEditable(true);
        cmbManufacturer.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbManufacturer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbManufacturerItemStateChanged(evt);
            }
        });
        cmbManufacturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbManufacturerActionPerformed(evt);
            }
        });
        cmbManufacturer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbManufacturerFocusGained(evt);
            }
        });
        cmbManufacturer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbManufacturerKeyReleased(evt);
            }
        });

        cmbItemName.setEditable(true);
        cmbItemName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbItemName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbItemNameItemStateChanged(evt);
            }
        });
        cmbItemName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbItemNameFocusGained(evt);
            }
        });
        cmbItemName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbItemNameKeyReleased(evt);
            }
        });

        cmbItemCode.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbItemCode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbItemCodeItemStateChanged(evt);
            }
        });
        cmbItemCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbItemCodeKeyReleased(evt);
            }
        });

        cmbBatchID.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbBatchID.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBatchIDItemStateChanged(evt);
            }
        });
        cmbBatchID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBatchIDActionPerformed(evt);
            }
        });
        cmbBatchID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbBatchIDKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel8.setText("Selling Price");

        txtSellingPrice.setEditable(false);
        txtSellingPrice.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel9.setText("Quantity");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel10.setText("Sub Total");

        txtQuantity.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtQuantityFocusGained(evt);
            }
        });
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        btnAddItem.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnAddItem.setText("ADD ITEM");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });

        btnDeleteRow.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnDeleteRow.setText("DELETE ROW [F9]");
        btnDeleteRow.setEnabled(false);
        btnDeleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRowActionPerformed(evt);
            }
        });

        btnClearOrder.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnClearOrder.setText("CLEAR ORDER [F1]");
        btnClearOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearOrderActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("TOTAL");

        lblStat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        lblRemainder.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblRemainder.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRemainder.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Remainder", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 10))); // NOI18N

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnAddOrder.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnAddOrder.setText("ADD ORDER [F12]");
        btnAddOrder.setEnabled(false);
        btnAddOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddOrderActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel11.setText("Discount (%)");

        spinnerDiscount.setModel(new javax.swing.SpinnerNumberModel(10.0d, 0.0d, 100.0d, 10.0d));

        btnAddItemRt.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnAddItemRt.setText("RETAIL ITEM");
        btnAddItemRt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemRtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSessionID, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(cmbManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(cmbItemName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtQuantity))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblRemainder, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spinnerDiscount))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblStat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAddItem))
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnClearOrder)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnDeleteRow)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAddItemRt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(cmbItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbBatchID, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnAddOrder, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDate, txtOrderID, txtSessionID});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtSessionID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBatchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddItem)
                                .addComponent(btnDeleteRow)
                                .addComponent(lblStat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddItemRt))
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblRemainder, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddOrder)
                    .addComponent(btnClearOrder))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {spinnerDiscount, txtSubTotal});

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbManufacturerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbManufacturerItemStateChanged
        String manuName = (String) cmbManufacturer.getSelectedItem();
        Manufacturer manufacturer = mjc.findManufacturerForName(manuName);
        cmbItemName.removeAllItems();
        cmbItemCode.removeAllItems();
        if (manufacturer != null) {
            List<Item> items = manufacturer.getItems();
            if (items != null) {
                for (Item item : items) {
                    cmbItemCode.addItem(OtherController.formatCode("I", item.getIid(), 8));
                    cmbItemName.addItem(item.getName() + " - "
                            + item.getMetric().getName());
                }
            }
        }
        search1.refreshList(cmbItemName, cmbItemCode);
        refreshBatchID();
    }//GEN-LAST:event_cmbManufacturerItemStateChanged
    /**
     * When State changes indexes are switched in the cmbItemeCode Checks
     * itemName for null instances of the cmbItemName
     *
     * @param evt for event
     */
    private void cmbItemNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbItemNameItemStateChanged
        String itemName = (String) cmbItemName.getSelectedItem();
        String manufacturer = (String) cmbManufacturer.getSelectedItem();
        comboSwitch = true;
        if (itemName != null && manufacturer != null) {
//            itemName = itemName.split(" -")[0];
//            Item item = ijc.findItemForNameAndManu(itemName, manufacturer);
//            if(showTest) System.out.println("found item ItemName "+item.getName());
            if (showProofs) {
                System.out.println("Item Name " + cmbItemName.getSelectedIndex());
            }
            Integer itemIndex = cmbItemName.getSelectedIndex();

            if (itemIndex != null && itemIndex != -1) {
                //if (cmbItemCode.getSelectedIndex() >= itemIndex) {
                try {
                    cmbItemCode.setSelectedIndex(cmbItemName.getSelectedIndex());
                } catch (IllegalArgumentException ex) {
                }
                //  }
            }
        }
    }//GEN-LAST:event_cmbItemNameItemStateChanged

    private void cmbItemCodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbItemCodeItemStateChanged
        String itemCode = (String) cmbItemCode.getSelectedItem();

        if (!comboSwitch) {
            if (itemCode != null) {
                if (showProofs) {
                    System.out.println("Item Code " + cmbItemCode.getSelectedIndex());
                    System.out.println("Item Name " + cmbItemName.getSelectedIndex());
                    System.out.println("itemcode " + stripOutItemCode(itemCode));
                }
                Integer itemIndex = cmbItemName.getSelectedIndex();

                if (itemIndex != -1) {
                    cmbItemName.setSelectedIndex(
                            cmbItemCode.getSelectedIndex());
                    refreshBatchID();
                }
            }
        } else {
            comboSwitch = false;
        }
    }//GEN-LAST:event_cmbItemCodeItemStateChanged

    private void cmbItemNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbItemNameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            cmbManufacturer.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbItemCode.requestFocus();
        }
    }//GEN-LAST:event_cmbItemNameKeyReleased

    private void cmbItemCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbItemCodeKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            cmbItemName.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbBatchID.requestFocus();
        }
    }//GEN-LAST:event_cmbItemCodeKeyReleased

    private void cmbBatchIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBatchIDItemStateChanged
        Long supplyOrderDetailID = (Long) cmbBatchID.getSelectedItem();
        if (supplyOrderDetailID != null) {
            SupplyOrderDetail sod = sodjc.findSupplyOrderDetail(
                    supplyOrderDetailID);
            txtSellingPrice.setText(sod.getSellingPrice() + "");
            lblRemainder.setText(sod.getRemainingQty() + "");
            txtQuantity.setEnabled(true);
            calculateSubTotal();
            manageQty();
        } else {
            txtSellingPrice.setText("");
            txtQuantity.setEnabled(false);
            btnAddItem.setEnabled(false);
            lblStat.setIcon(imgWrong);
            lblRemainder.setText("");
            txtQuantity.setText("");
        }
        // System.gc();
    }//GEN-LAST:event_cmbBatchIDItemStateChanged

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        manageQty();

    }//GEN-LAST:event_txtQuantityKeyReleased

    private void manageQty() {
        calculateSubTotal();
        SupplyOrderDetail sod = sodjc.findSupplyOrderDetail(
                (Long) cmbBatchID.getSelectedItem());
        int remainingQty = sod.getRemainingQty();

        try {
            Long addingItemCode = (Long) cmbBatchID.getSelectedItem();
            int qty = Integer.parseInt(txtQuantity.getText());
            for (int i = 0; i < dtmOrder.getRowCount(); i++) {
                if (dtmOrder.getValueAt(i, 2).equals(addingItemCode)) {
                    double qtyEnterd = Double.parseDouble((String) dtmOrder.getValueAt(i, 3));
                    remainingQty -= qtyEnterd;
                }
            }
            if (remainingQty - qty < 0) {
                btnAddItem.setEnabled(false);
                lblRemainder.setForeground(clrRed);
                lblStat.setIcon(imgWrong);
            } else {
                btnAddItem.setEnabled(true);
                lblRemainder.setForeground(clrBlack);
                lblStat.setIcon(imgRight);
            }
            lblRemainder.setText((remainingQty - qty) + "");
        } catch (NumberFormatException e) {
            btnAddItem.setEnabled(false);
            lblRemainder.setText(remainingQty + "");
            lblStat.setIcon(imgWrong);
        }
        // System.gc();
    }

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        String addingItemCode = (String) cmbItemCode.getSelectedItem();
        String price = txtSellingPrice.getText();
        boolean exist = false;
        for (int i = 0; i < dtmOrder.getRowCount(); i++) {
            if (dtmOrder.getValueAt(i, 0).equals(addingItemCode)) {

                if (dtmOrder.getValueAt(i, 4).equals(price)) {

                    int qty = Integer.parseInt((String) dtmOrder.getValueAt(i, 3));
                    double sellingPrice = Double.parseDouble((String) dtmOrder.getValueAt(i, 4));
                    total -= qty * sellingPrice;
                    qty += Integer.parseInt(txtQuantity.getText());
                    total += qty * sellingPrice;
                    dtmOrder.setValueAt(qty + "", i, 3);
                    dtmOrder.setValueAt((qty * sellingPrice) + "", i, 5);
                    exist = true;
                    setTotal();
                } else {
                    exist = false;
                }
            }
        }
        if (!exist) {
            dtmOrder.addRow(new Object[]{addingItemCode,
                cmbManufacturer.getSelectedItem() + " "
                + cmbItemName.getSelectedItem(),
                cmbBatchID.getSelectedItem(),
                txtQuantity.getText(), txtSellingPrice.getText(),
                txtSubTotal.getText(), spinnerDiscount.getValue()});
            int qty = Integer.parseInt(txtQuantity.getText());
            double sellingPrice = Double.parseDouble(txtSellingPrice.getText());
            total += (qty * sellingPrice);

            setTotal();
        }
        manageQty();
        //refreshFields();
        clearFields();
        cmbManufacturer.requestFocus();
        if (dtmOrder.getRowCount() > 0) {
            btnAddOrder.setEnabled(true);
        }
        //  System.gc();
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void tblOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrdersMouseClicked
        if (tblOrders.getRowCount() > 0) {
            int row = tblOrders.getSelectedRow();
            //System.out.println(" " + row);
            if (row >= 0) {
                btnDeleteRow.setEnabled(true);
            } else {
                btnDeleteRow.setEnabled(false);
            }
        }
        //   System.gc();
    }//GEN-LAST:event_tblOrdersMouseClicked

    private void btnDeleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRowActionPerformed
        if (tblOrders.getRowCount() > 0) {
            int row = tblOrders.getSelectedRow();
            dtmOrder.removeRow(row);
            txtQuantityKeyReleased(null);
            btnDeleteRow.setEnabled(false);
            // System.gc();
        }
    }//GEN-LAST:event_btnDeleteRowActionPerformed

    private void txtQuantityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQuantityFocusGained
        txtQuantity.selectAll();
        //  System.gc();
    }//GEN-LAST:event_txtQuantityFocusGained

    private void btnAddOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddOrderActionPerformed
        int rowCount = dtmOrder.getRowCount();
        if (rowCount > 0) {
            setTotal();
            Orders order = new Orders();
            List<OrderDetail> orderDetails = new ArrayList<>();
            double discount = 0;
            for (int i = 0; i < rowCount; i++) {
                OrderDetail od = new OrderDetail();
                Integer qty = Integer.parseInt((String) dtmOrder.getValueAt(i, 3));
                SupplyOrderDetail sod = sodjc.findSupplyOrderDetail((Long) dtmOrder.getValueAt(i, 2));
                od.setOrder(order);
                od.setQty(qty);
                double unitpice = Double.parseDouble((String) dtmOrder.getValueAt(i, 4));
                od.setUnitPrice(unitpice);
                od.setSupplyOrderDetail(sod);
                orderDetails.add(od);
                double discountrate;
                try {
                    discountrate = (double) dtmOrder.getValueAt(i, 6);
                } catch (Exception e) {
                    discountrate = Double.parseDouble((String) dtmOrder.getValueAt(i, 6));
                }
                discount += (qty * unitpice * discountrate) / 100;
                /**
                 * Updates the remain quantity.
                 */
                sod.setRemainingQty(sod.getRemainingQty() - qty);

                orderDetails.add(od);
            }
            Date date = new Date(Calendar.getInstance().getTimeInMillis());
            Time time = new Time(Calendar.getInstance().getTimeInMillis());

            com.zegates.sanctus.services.remote.Date dateJax = new com.zegates.sanctus.services.remote.Date();
            com.zegates.sanctus.services.remote.Time timeJax = new com.zegates.sanctus.services.remote.Time();
            dateJax.setDate(date);
            timeJax.setTime(time);
            order.setOrderDetails(orderDetails);
            order.setDateAdded(dateJax);
            order.setTimeAdded(timeJax);
            order.setOid(ojc.getLatesOrdersID() + 1L);
            order.setDiscount(discount);
            order.setTotal(total);
            order.setLogSession(mainFrame.getLogSession());

            new CustomerDetails(mainFrame, true, order, this).setVisible(true);

            refreshOrderID();
            //  System.gc();
        }
    }//GEN-LAST:event_btnAddOrderActionPerformed

    private void btnClearOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearOrderActionPerformed
        clearFields();
        clearOrder();
        reArrangeTotal();
        refreshFields();
        cmbManufacturer.requestFocus();
        //   System.gc();
    }//GEN-LAST:event_btnClearOrderActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_F9 || evt.getKeyCode() == KeyEvent.VK_DELETE) {
            btnDeleteRow.doClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F12) {
            btnAddOrder.doClick();
        }
        //   System.gc();
    }//GEN-LAST:event_formKeyReleased

    private void tblOrdersKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblOrdersKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_F9 || evt.getKeyCode() == KeyEvent.VK_DELETE) {
            btnDeleteRow.doClick();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F12) {
            btnAddOrder.doClick();
        }
        // System.gc();
    }//GEN-LAST:event_tblOrdersKeyReleased

    private void cmbManufacturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbManufacturerActionPerformed
    }//GEN-LAST:event_cmbManufacturerActionPerformed

    private void cmbManufacturerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbManufacturerKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbItemName.requestFocus();
        }
        //    System.gc();
    }//GEN-LAST:event_cmbManufacturerKeyReleased

    private void jPanel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_F12) {
            btnAddOrder.doClick();
        }
        //  System.gc();
    }//GEN-LAST:event_jPanel1KeyReleased

    private void cmbBatchIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbBatchIDKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            cmbItemCode.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtQuantity.requestFocus();
        }
        //   System.gc();
    }//GEN-LAST:event_cmbBatchIDKeyReleased

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        cmbManufacturer.requestFocus();
    }//GEN-LAST:event_formFocusGained

    private void cmbManufacturerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbManufacturerFocusGained
    }//GEN-LAST:event_cmbManufacturerFocusGained

    private void cmbItemNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbItemNameFocusGained
    }//GEN-LAST:event_cmbItemNameFocusGained

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        refreshFields();
        refreshOrderID();
        refreshSessionID();
        refreshBatchID();
    }//GEN-LAST:event_formInternalFrameActivated

    private void btnAddItemRtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemRtActionPerformed
        new RetailOrderToOrder(mainFrame, true, dtmOrder, this).setVisible(true);
    }//GEN-LAST:event_btnAddItemRtActionPerformed

    private void cmbBatchIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBatchIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBatchIDActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnAddItemRt;
    private javax.swing.JButton btnAddOrder;
    private javax.swing.JButton btnClearOrder;
    private javax.swing.JButton btnDeleteRow;
    private javax.swing.JComboBox cmbBatchID;
    private javax.swing.JComboBox cmbItemCode;
    private javax.swing.JComboBox cmbItemName;
    private javax.swing.JComboBox cmbManufacturer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRemainder;
    private javax.swing.JLabel lblStat;
    private javax.swing.JSpinner spinnerDiscount;
    private javax.swing.JTable tblOrders;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtOrderID;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSellingPrice;
    private javax.swing.JTextField txtSessionID;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    private void refreshFields() {
        //Set Manufacturers
        cmbItemName.removeAllItems();
        cmbManufacturer.removeAllItems();

        List<Manufacturer> manufacturers = mjc.findManufacturerEntities();
        Manufacturer m = new Manufacturer();
        m.setManuid(-1L);
        manufacturers.remove(m);
        Collections.sort(manufacturers, new Comparator<Manufacturer>() {
            @Override
            public int compare(Manufacturer o1, Manufacturer o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (int i = 0; i < manufacturers.size(); i++) {
            Manufacturer manufacturer = manufacturers.get(i);
            if (i == 0) {
                List<Item> itemsFirstList = manufacturer.getItems();
                for (Item item : itemsFirstList) {
                    cmbItemName.addItem(item.getName());
                }
            }
            cmbManufacturer.addItem(manufacturer.getName());
        }

        search.setSearchableCombo(cmbManufacturer, true, "No Such Manufacture");

        search1.setSearchableCombo(cmbItemName, cmbItemCode, false, "No Such Item");
        //  System.gc();

    }

    private Long stripOutItemCode(String itemCode) {
        try {
            return Long.parseLong(itemCode.substring(1));
        } catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    private void refreshBatchID() {
        cmbBatchID.removeAllItems();
        String itemCode = (String) cmbItemCode.getSelectedItem();
        if (itemCode != null) {
            Item item = ijc.findItem(stripOutItemCode(itemCode));
            if (item != null) {
                List<SupplyOrderDetail> supplyOrderDetails = item.getSupplyOrderDetails();
                cmbBatchID.removeAllItems();
                for (SupplyOrderDetail supplyOrderDetail : supplyOrderDetails) {
                    int remainder = supplyOrderDetail.getRemainingQty();
                    if (remainder > 0) {
                        cmbBatchID.addItem(supplyOrderDetail.getSodid());
                    }
                }
                cmbBatchIDItemStateChanged(null);
            }
        } else {
            cmbBatchID.removeAllItems();
        }
    }

    public void clearFields() {
        lblRemainder.setText("");
        txtSubTotal.setText("");
        txtQuantity.setText("0");
        //   System.gc();
    }

    private void calculateSubTotal() {
        try {
            double qty = Double.parseDouble(txtQuantity.getText());
            double sellingPrice = Double.parseDouble(txtSellingPrice.getText());
            txtSubTotal.setText((qty * sellingPrice) + "");

            lblStat.setIcon(imgRight);
            btnAddItem.setEnabled(true);
        } catch (NumberFormatException e) {
            txtSubTotal.setText("");
            lblStat.setIcon(imgWrong);
            btnAddItem.setEnabled(false);
        }
    }

    private String formatPrice(double total) {
        return String.format("%,.2f", total);
    }

    public void setTotal() {
        reArrangeTotal();
        txtTotal.setText(formatPrice(total));
        if (dtmOrder.getRowCount() > 0) {
            btnAddOrder.setEnabled(true);
        }
    }

    private void reArrangeTotal() {
        total = 0;
        for (int i = 0; i < dtmOrder.getRowCount(); i++) {
            double qty = Double.parseDouble((String) dtmOrder.getValueAt(i, 3));
            double price = Double.parseDouble((String) dtmOrder.getValueAt(i, 4));
            total += qty * price;
        }
    }

    public void clearOrder() {
        dtmOrder.setRowCount(0);
        txtTotal.setText("");
        //  System.gc();
    }

    public void refreshOrderID() {
        txtOrderID.setText(OtherController.formatCode("OD",
                ojc.getLatesOrdersID() + 1L, 8));
        txtDate.setText(OtherController.getDate());
    }

    private void refreshSessionID() {
        LogSession logSession = mainFrame.getLogSession();
        txtSessionID.setText(OtherController.formatCode("SE", logSession.getSeid(), 8));
    }

    @Override
    public void update(Observable o, Object arg) {
        mainFrame.update(o, arg);
        //   System.gc();
    }
}
