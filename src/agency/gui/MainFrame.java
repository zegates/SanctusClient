/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.gui;

import connn.ReadObject;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import agency.other.OtherController;
import agency.persistance.controller.remote.ItemController;
import agency.persistance.controller.remote.LogSessionController;
import agency.persistance.controller.remote.ManufacturerController;
import agency.persistance.controller.remote.OrdersController;
import agency.persistance.controller.remote.SupplierController;
import agency.persistance.controller.remote.SupplyOrderController;
import agency.persistance.controller.remote.SupplyOrderDetailController;
import agency.persistance.factory.ControllerFactory;
import agency.remote.DBBackup;
import com.zegates.sanctus.services.remote.Item;
import com.zegates.sanctus.services.remote.LogSession;
import com.zegates.sanctus.services.remote.LogUser;
import com.zegates.sanctus.services.remote.Orders;
import com.zegates.sanctus.services.remote.SupplyOrder;
import com.zegates.sanctus.services.remote.SupplyOrderDetail;

/**
 *
 * @author Sandaruwan
 */
public class MainFrame extends javax.swing.JFrame implements Observer {
    
    private int openFrameCount = 0;
    private int xOffset = 20;
    private int yOffset = 20;
    private ArrayList<JInternalFrame> frameList;
    private LogSession logSession;
    
    private ItemController ijc;
//    private ConstructionController cjc;
    private ManufacturerController mjc;
    private OrdersController ojc;
    private SupplyOrderController sojc;
    private SupplyOrderDetailController sodjc;
    private SupplierController sjc;
    private LogSessionController lsjc;
    
    private DefaultListModel dlmCritical;
    private TrayIcon ico;

    /**
     * Creates new form MainFrame
     */
    public MainFrame(LogUser logUser, LogSession logSession) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage("./src/agency/img/tire.ico"));
        Toolkit.getDefaultToolkit().getImage("./src/agency/img/tire.ico");
        setLocation(0, 0);
        setExtendedState(MAXIMIZED_BOTH);
        ijc = ControllerFactory.getItemController();
        ojc = ControllerFactory.getOrdersController();
        lsjc = ControllerFactory.getSessionController();
        mjc = ControllerFactory.getManufacturerController();
//        cjc = ControllerFactory.getConstructionController();
        sojc = ControllerFactory.getSupplyOrderController();
        sodjc = ControllerFactory.getSupplyOrderDetailController();
        sjc = ControllerFactory.getSupplierController();
        
        ico = new TrayIcon(new ImageIcon(getClass().getResource("/agency/img/warning.png")).getImage());
        ico.setToolTip("Tire System");
        
        if (logUser != null) {
            if (logSession == null) {
                try {
                    logSession = new LogSession();
                    
                    Date dateSQL = new Date(Calendar.getInstance().getTimeInMillis());
                    Time timeSQL = new Time(Calendar.getInstance().getTimeInMillis());
                    com.zegates.sanctus.services.remote.Date date = new com.zegates.sanctus.services.remote.Date();
                    date.setDate(dateSQL);
                    com.zegates.sanctus.services.remote.Time time = new com.zegates.sanctus.services.remote.Time();
                    time.setTime(timeSQL);
                    
                    logSession.setSeid(lsjc.getLogSessionCount() + 1L);
                    logSession.setDateStarted(date);
                    logSession.setTimeStarted(time);
                    logSession.setLogUser(logUser);
                    lsjc.create(logSession);
                    this.logSession = logSession;
//                    setItem();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.logSession = logSession;
            }
        }
        lblRState.setVerticalTextPosition(JLabel.BOTTOM);
        lblRState.setHorizontalTextPosition(JLabel.CENTER);
        
        dlmCritical = new DefaultListModel();
        lstCritical.setModel(dlmCritical);
        setStat();
        ijc.addObserver(this);
        
        frameList = new ArrayList<>();
        frameList.add(new ItemsFrame(this));
        frameList.add(new SuppliersFrame(this));
        frameList.add(new StockFrame(this));
        
////        System.gc();
    }
    
    public LogSession getLogSession() {
        return logSession;
    }
    
    public void setLogSession(LogSession logSession) {
        this.logSession = logSession;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        btnOrders = new javax.swing.JButton();
        btnItems = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        btnSession = new javax.swing.JButton();
        btnFinalize = new javax.swing.JButton();
        btnSuppliers = new javax.swing.JButton();
        btnDue = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblLastOrder = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCritical = new javax.swing.JList();
        btnPeep = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtSessionSale = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txtStockSale = new javax.swing.JTextField();
        lblRState = new javax.swing.JLabel();
        dskMain = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniUpdateRemoteDB = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnAddCutOrders = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mniListItems = new javax.swing.JMenuItem();
        mniAddItem = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        mniAddSuppliers = new javax.swing.JMenuItem();
        mniUpdateSuppliers = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        mniViewStock = new javax.swing.JMenuItem();
        mniCritical = new javax.swing.JMenuItem();
        mniSession = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        mniPayedOrders = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        mniFinalize = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        backupDB = new javax.swing.JMenuItem();
        restoreDB = new javax.swing.JMenuItem();
        itemupuser = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mniAbout = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);

        btnOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agency/img/orders.png"))); // NOI18N
        btnOrders.setText("Orders");
        btnOrders.setFocusable(false);
        btnOrders.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOrders.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdersActionPerformed(evt);
            }
        });
        jToolBar1.add(btnOrders);

        btnItems.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agency/img/Items.png"))); // NOI18N
        btnItems.setText("Items");
        btnItems.setFocusable(false);
        btnItems.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnItems.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemsActionPerformed(evt);
            }
        });
        jToolBar1.add(btnItems);

        btnStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agency/img/stock.png"))); // NOI18N
        btnStock.setText("Stock");
        btnStock.setFocusable(false);
        btnStock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockActionPerformed(evt);
            }
        });
        jToolBar1.add(btnStock);

        btnSession.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agency/img/session.png"))); // NOI18N
        btnSession.setText("Session");
        btnSession.setToolTipText("S");
        btnSession.setFocusable(false);
        btnSession.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSession.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSessionActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSession);

        btnFinalize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agency/img/finalize.png"))); // NOI18N
        btnFinalize.setText("Finalize");
        btnFinalize.setFocusable(false);
        btnFinalize.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFinalize.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFinalize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizeActionPerformed(evt);
            }
        });
        jToolBar1.add(btnFinalize);

        btnSuppliers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agency/img/supply.png"))); // NOI18N
        btnSuppliers.setText("Suppliers");
        btnSuppliers.setFocusable(false);
        btnSuppliers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSuppliers.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSuppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuppliersActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSuppliers);

        btnDue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agency/img/due.png"))); // NOI18N
        btnDue.setText("Due");
        btnDue.setFocusable(false);
        btnDue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDue.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDueActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDue);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.EAST);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(130, 464));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agency/gui/dunlop.jpg"))); // NOI18N

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 2, 11)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("LAST ORDER");

        lblLastOrder.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblLastOrder.setForeground(new java.awt.Color(255, 255, 255));
        lblLastOrder.setText("No Orders");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CRITICAL STOCKS");

        lstCritical.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lstCritical.setForeground(new java.awt.Color(255, 51, 51));
        lstCritical.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCriticalMouseClicked(evt);
            }
        });
        lstCritical.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstCriticalValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstCritical);

        btnPeep.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btnPeep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/agency/img/peep1.png"))); // NOI18N
        btnPeep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeepActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("TOTAL SESSION SALE");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("REMOTE STATE");

        txtSessionSale.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("TOTAL STOCK WAGES");

        txtStockSale.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lblRState.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        lblRState.setForeground(new java.awt.Color(255, 255, 255));
        lblRState.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRState.setToolTipText("");
        lblRState.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblRState.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblRState.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(lblLastOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtSessionSale)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator5)
                    .addComponent(txtStockSale)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPeep, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRState, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLastOrder)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPeep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSessionSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStockSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRState, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("STAT", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        dskMain.setBackground(new java.awt.Color(255, 255, 255));
        dskMain.setPreferredSize(new java.awt.Dimension(800, 500));
        jSplitPane1.setRightComponent(dskMain);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");

        mniUpdateRemoteDB.setText("Update Remote Database");
        mniUpdateRemoteDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUpdateRemoteDBActionPerformed(evt);
            }
        });
        jMenu1.add(mniUpdateRemoteDB);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("View");

        btnAddCutOrders.setText("Add Cust Orders");
        btnAddCutOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCutOrdersActionPerformed(evt);
            }
        });
        jMenu3.add(btnAddCutOrders);

        jMenu5.setText("Items");

        mniListItems.setText("Find | Update | Delete | Item");
        mniListItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniListItemsActionPerformed(evt);
            }
        });
        jMenu5.add(mniListItems);

        mniAddItem.setText("Add Item");
        mniAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAddItemActionPerformed(evt);
            }
        });
        jMenu5.add(mniAddItem);

        jMenu3.add(jMenu5);

        jMenu6.setText("Suppliers");

        mniAddSuppliers.setText("Add Suppliers");
        mniAddSuppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAddSuppliersActionPerformed(evt);
            }
        });
        jMenu6.add(mniAddSuppliers);

        mniUpdateSuppliers.setText("Edit | Find | Update Suppliers");
        mniUpdateSuppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUpdateSuppliersActionPerformed(evt);
            }
        });
        jMenu6.add(mniUpdateSuppliers);

        jMenu3.add(jMenu6);

        jMenu7.setText("Stock");

        mniViewStock.setText("View Stock");
        mniViewStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniViewStockActionPerformed(evt);
            }
        });
        jMenu7.add(mniViewStock);

        mniCritical.setText("Critical Stock");
        mniCritical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCriticalActionPerformed(evt);
            }
        });
        jMenu7.add(mniCritical);

        jMenu3.add(jMenu7);

        mniSession.setText("Sessions");
        mniSession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSessionActionPerformed(evt);
            }
        });
        jMenu3.add(mniSession);

        jMenu8.setText("View Orders");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("View Sup Orders");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem2);

        mniPayedOrders.setText("View Due Orders");
        mniPayedOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPayedOrdersActionPerformed(evt);
            }
        });
        jMenu8.add(mniPayedOrders);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("View Cust Orders");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem3);

        jMenu3.add(jMenu8);

        mniFinalize.setText("Finalize");
        mniFinalize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniFinalizeActionPerformed(evt);
            }
        });
        jMenu3.add(mniFinalize);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Tools");

        backupDB.setText("Back Up DB");
        backupDB.setToolTipText("Don't Use Foler Name contains '#'");
        backupDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupDBActionPerformed(evt);
            }
        });
        jMenu2.add(backupDB);

        restoreDB.setText("Restore DB");
        restoreDB.setToolTipText("Don't Use Foler Name contains '#'");
        restoreDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreDBActionPerformed(evt);
            }
        });
        jMenu2.add(restoreDB);

        itemupuser.setText("Update User");
        itemupuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemupuserActionPerformed(evt);
            }
        });
        jMenu2.add(itemupuser);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Help");

        mniAbout.setText("About Us");
        mniAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAboutActionPerformed(evt);
            }
        });
        jMenu4.add(mniAbout);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdersActionPerformed
        
        
        JInternalFrame[] allFrames = dskMain.getAllFrames();
        boolean b = false;
        int frmid = 0;
        for (int i = 0; i < allFrames.length; i++) {
            if (allFrames[i] instanceof AddOrdersFrame) {
                b = true;
                frmid = i;
            }
        }
        if (!b) {
            addIntFrame(new AddOrdersFrame(this));
        } else {
            dskMain.setSelectedFrame(allFrames[frmid]);
            allFrames[frmid].moveToFront();
        }
////        System.gc();
    }//GEN-LAST:event_btnOrdersActionPerformed
    
    private void btnItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemsActionPerformed
        
        JInternalFrame[] allFrames = dskMain.getAllFrames();
        boolean b = false;
        int frmid = 0;
        for (int i = 0; i < allFrames.length; i++) {
            if (allFrames[i] instanceof ItemsFrame) {
                b = true;
                frmid = i;
            }
        }
        if (!b) {
            addIntFrame(frameList.get(0));
        } else {
            dskMain.setSelectedFrame(allFrames[frmid]);
            allFrames[frmid].moveToFront();
        }
////        System.gc();
    }//GEN-LAST:event_btnItemsActionPerformed
    
    private void btnStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockActionPerformed
        JInternalFrame[] allFrames = dskMain.getAllFrames();
        boolean b = false;
        int frmid = 0;
        for (int i = 0; i < allFrames.length; i++) {
            if (allFrames[i] instanceof StockFrame) {
                b = true;
                frmid = i;
            }
        }
        if (!b) {
            addIntFrame(frameList.get(2));
        } else {
            dskMain.setSelectedFrame(allFrames[frmid]);
            allFrames[frmid].moveToFront();
        }
////        System.gc();
    }//GEN-LAST:event_btnStockActionPerformed
    
    private void btnFinalizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizeActionPerformed
        JInternalFrame[] allFrames = dskMain.getAllFrames();
        boolean b = false;
        int frmid = 0;
        for (int i = 0; i < allFrames.length; i++) {
            if (allFrames[i] instanceof FinalizeFrame) {
                b = true;
                frmid = i;
            }
        }
        if (!b) {
            addIntFrame(new FinalizeFrame(this));
        } else {
            dskMain.setSelectedFrame(allFrames[frmid]);
            allFrames[frmid].moveToFront();
        }
////        System.gc();
    }//GEN-LAST:event_btnFinalizeActionPerformed
    
    private void btnSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSessionActionPerformed
        JInternalFrame[] allFrames = dskMain.getAllFrames();
        boolean b = false;
        int frmid = 0;
        for (int i = 0; i < allFrames.length; i++) {
            if (allFrames[i] instanceof SessionFrame) {
                b = true;
                frmid = i;
            }
        }
        if (!b) {
            addIntFrame(new SessionFrame(this));
        } else {
            dskMain.setSelectedFrame(allFrames[frmid]);
            allFrames[frmid].moveToFront();
        }
////        System.gc();
    }//GEN-LAST:event_btnSessionActionPerformed
    
    private void btnSuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuppliersActionPerformed
        JInternalFrame[] allFrames = dskMain.getAllFrames();
        boolean b = false;
        int frmid = 0;
        for (int i = 0; i < allFrames.length; i++) {
            if (allFrames[i] instanceof SuppliersFrame) {
                b = true;
                frmid = i;
            }
        }
        if (!b) {
            addIntFrame(frameList.get(1));
        } else {
            dskMain.setSelectedFrame(allFrames[frmid]);
            allFrames[frmid].moveToFront();
        }
////        System.gc();
    }//GEN-LAST:event_btnSuppliersActionPerformed
    
    private void lstCriticalValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstCriticalValueChanged
        String itemCode = (String) lstCritical.getSelectedValue();
        if (itemCode != null) {
            Long criticalItemID = OtherController.stripOutItemCode(itemCode);
            ItemsFrame itemsFrame = (ItemsFrame) frameList.get(0);
            itemsFrame.setCriticalStock(criticalItemID);
        }
////        System.gc();
    }//GEN-LAST:event_lstCriticalValueChanged
    
    private void lstCriticalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCriticalMouseClicked
        String itemCode = (String) lstCritical.getSelectedValue();
        if (itemCode != null) {
            Long criticalItemID = OtherController.stripOutItemCode(itemCode);
            ItemsFrame itemsFrame = (ItemsFrame) frameList.get(0);
            itemsFrame.setCriticalStock(criticalItemID);
            itemsFrame.moveToFront();
        }
////        System.gc();
    }//GEN-LAST:event_lstCriticalMouseClicked
    
    private void btnPeepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeepActionPerformed
        Long[] ids = new Long[dlmCritical.size()];
        for (int i = 0; i < dlmCritical.size(); i++) {
            String itemCode = (String) dlmCritical.getElementAt(i);
            Long itemID = OtherController.stripOutItemCode(itemCode);
            ids[i] = itemID;
        }
        if (ids != null && ids.length > 0) {
            ItemsFrame itemsFrame = (ItemsFrame) frameList.get(0);
            itemsFrame.moveToFront();
            btnItems.doClick();
            itemsFrame.setCriticalStock(ids);
        }
////        System.gc();
    }//GEN-LAST:event_btnPeepActionPerformed
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int conf = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (conf == 0) {
            System.exit(0);
        }
////        System.gc();
    }//GEN-LAST:event_formWindowClosing
    
    private void mniAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAboutActionPerformed
        new AboutUs(this, true).setVisible(true);
////        System.gc();
    }//GEN-LAST:event_mniAboutActionPerformed
    
    private void btnAddCutOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCutOrdersActionPerformed
        btnOrders.doClick();
    }//GEN-LAST:event_btnAddCutOrdersActionPerformed
    
    private void mniAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAddItemActionPerformed
        btnItems.doClick();
        ItemsFrame itemsFrame = (ItemsFrame) frameList.get(0);
        itemsFrame.getTbpMain().setSelectedIndex(0);
////        System.gc();
    }//GEN-LAST:event_mniAddItemActionPerformed
    
    private void mniListItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniListItemsActionPerformed
        btnItems.doClick();
        ItemsFrame itemsFrame = (ItemsFrame) frameList.get(0);
        itemsFrame.getTbpMain().setSelectedIndex(1);
////        System.gc();
    }//GEN-LAST:event_mniListItemsActionPerformed
    
    private void mniUpdateSuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUpdateSuppliersActionPerformed
        btnSuppliers.doClick();
        SuppliersFrame suppliersFrame = (SuppliersFrame) frameList.get(1);
        suppliersFrame.getTbpMain().setSelectedIndex(1);
////        System.gc();
    }//GEN-LAST:event_mniUpdateSuppliersActionPerformed
    
    private void mniAddSuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAddSuppliersActionPerformed
        btnSuppliers.doClick();
        SuppliersFrame suppliersFrame = (SuppliersFrame) frameList.get(1);
        suppliersFrame.getTbpMain().setSelectedIndex(0);
////        System.gc();
    }//GEN-LAST:event_mniAddSuppliersActionPerformed
    
    private void mniViewStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniViewStockActionPerformed
        btnStock.doClick();
    }//GEN-LAST:event_mniViewStockActionPerformed
    
    private void mniCriticalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCriticalActionPerformed
        btnPeep.doClick();
    }//GEN-LAST:event_mniCriticalActionPerformed
    
    private void mniSessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSessionActionPerformed
        btnSession.doClick();
    }//GEN-LAST:event_mniSessionActionPerformed
    
    private void mniFinalizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniFinalizeActionPerformed
        btnFinalize.doClick();
    }//GEN-LAST:event_mniFinalizeActionPerformed
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new ViewOrder(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new ViewSupplyOrder(this, true).setVisible(true);
////        System.gc();
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
    private void mniUpdateRemoteDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUpdateRemoteDBActionPerformed
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ImageIcon imgTcp = new ImageIcon(getClass().getResource("/agency/img/tcp.png"));
                    ImageIcon imgTcpW = new ImageIcon(getClass().getResource("/agency/img/tcp_wrng.png"));
                    lblRState.setIcon(imgTcp);
                    int backUpDB = DBBackup.backUpDB();
                    if (backUpDB == 0) {
                        lblRState.setIcon(new ImageIcon(getClass().getResource("/agency/img/tcp_restore.png")));
                        int restoreBackUp = DBBackup.restoreBackUp();
                        if (restoreBackUp == 0) {
                            lblRState.setIcon(new ImageIcon(getClass().getResource("/agency/img/tcp_cmplt.png")));
                            JOptionPane.showMessageDialog(null, "Remote Server"
                                    + " Was Successfully Restored", "Successful", JOptionPane.INFORMATION_MESSAGE);
                            lblRState.setText(OtherController.getDate() + " " + OtherController.getCurretTime());
                            
                        } else {
                            lblRState.setIcon(imgTcpW);
                            lblRState.setText(OtherController.getDate() + " " + OtherController.getCurretTime());
                            JOptionPane.showMessageDialog(null, "Error in Connection with remote Server",
                                    "Error Occured", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        lblRState.setIcon(imgTcpW);
                        JOptionPane.showMessageDialog(null, "Error in Connection with remote Server",
                                "Error Occured", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error Attempt \n" + ex.getMessage(),
                            "ClassNotFound ", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error Attempt \n" + ex.getMessage(),
                            "IO Exception Occured", JOptionPane.ERROR_MESSAGE);
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, "Error Attempt \n" + ex.getMessage(),
                            "Interrupted Exception Occured", JOptionPane.ERROR_MESSAGE);
                }
//        //        System.gc();
            }
        }).start();
        
////        System.gc();
        
    }//GEN-LAST:event_mniUpdateRemoteDBActionPerformed
    
    private void mniPayedOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPayedOrdersActionPerformed
        btnDueActionPerformed(null);
    }//GEN-LAST:event_mniPayedOrdersActionPerformed
    
    private void btnDueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDueActionPerformed
        JInternalFrame[] allFrames = dskMain.getAllFrames();
        boolean b = false;
        int frmid = 0;
        for (int i = 0; i < allFrames.length; i++) {
            if (allFrames[i] instanceof ViewUncompletedOrders) {
                b = true;
                frmid = i;
            }
        }
        if (!b) {
            addIntFrame(new ViewUncompletedOrders());
        } else {
            dskMain.setSelectedFrame(allFrames[frmid]);
            allFrames[frmid].moveToFront();
        }
////        System.gc();
    }//GEN-LAST:event_btnDueActionPerformed
    
    private void backupDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupDBActionPerformed
        JFileChooser browser = new JFileChooser();
        browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ref = browser.showOpenDialog(null);
        File file;
        File batFile = null;
        if (ref == JFileChooser.APPROVE_OPTION) {
            FileWriter fw;
            ReadObject ro = new ReadObject();
            try {
                file = browser.getSelectedFile();
                fw = new FileWriter("Backup.bat");
                String apath = file.getAbsolutePath();
                String replaceAll = apath.replace('\\', '#');
                String[] split = replaceAll.split("#");
                String path = "";
                int i = 1;
                for (String string : split) {
                    if (i == 1) {
                        path += string + "\\\"";
                    } else if (i == split.length) {
                        path += string + "\"\\";
                    } else {
                        path += string + "\"\\\"";
                    }
                    i++;
                }
                fw.write("mysqldump agency_erp -h localhost -u " + ro.getUser().getUsername() + " -p" + ro.getUser().getPassword() + " >" + path + "\\Backup.sql");
                fw.flush();
                fw.close();
                batFile = new File("Backup.bat");
                Desktop.getDesktop().open(batFile);
            } catch (SAXException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                batFile.deleteOnExit();
            }
        }
    }//GEN-LAST:event_backupDBActionPerformed
    
    private void restoreDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreDBActionPerformed
        JFileChooser browser = new JFileChooser();
        browser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter backupfileFilter = new FileNameExtensionFilter("Back Up Files", "sql");
        browser.setFileFilter(backupfileFilter);
        int ref = browser.showOpenDialog(this);
        File file;
        File batFile = null;
        if (ref == JFileChooser.APPROVE_OPTION) {
            ReadObject ro = new ReadObject();
            FileWriter fw;
            try {
                file = browser.getSelectedFile();
                String apath = file.getAbsolutePath();
                String replaceAll = apath.replace('\\', '#');
                String[] split = replaceAll.split("#");
                String path = "";
                int i = 1;
                for (String string : split) {
                    if (i == 1) {
                        path += string + "\\\"";
                    } else if (i == (split.length - 1)) {
                        path += string + "\"\\";
                    } else if (i == split.length) {
                        path += string;
                    } else {
                        path += string + "\"\\\"";
                    }
                    i++;
                }
                fw = new FileWriter("Restore.bat");
                fw.write("mysql agency_erp -h localhost -u " + ro.getUser().getUsername() + " -p" + ro.getUser().getPassword() + " <" + path);
                fw.flush();
                fw.close();
                batFile = new File("Restore.bat");
                Desktop.getDesktop().open(batFile);
            } catch (SAXException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                batFile.deleteOnExit();
            }
        }
        browser.removeChoosableFileFilter(backupfileFilter);
    }//GEN-LAST:event_restoreDBActionPerformed
    
    private void itemupuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemupuserActionPerformed
        new UpdateUser(this, true, logSession.getLogUser()).setVisible(true);
    }//GEN-LAST:event_itemupuserActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Splash().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem backupDB;
    private javax.swing.JMenuItem btnAddCutOrders;
    private javax.swing.JButton btnDue;
    private javax.swing.JButton btnFinalize;
    private javax.swing.JButton btnItems;
    private javax.swing.JButton btnOrders;
    private javax.swing.JButton btnPeep;
    private javax.swing.JButton btnSession;
    private javax.swing.JButton btnStock;
    private javax.swing.JButton btnSuppliers;
    private javax.swing.JDesktopPane dskMain;
    private javax.swing.JMenuItem itemupuser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblLastOrder;
    private javax.swing.JLabel lblRState;
    private javax.swing.JList lstCritical;
    private javax.swing.JMenuItem mniAbout;
    private javax.swing.JMenuItem mniAddItem;
    private javax.swing.JMenuItem mniAddSuppliers;
    private javax.swing.JMenuItem mniCritical;
    private javax.swing.JMenuItem mniFinalize;
    private javax.swing.JMenuItem mniListItems;
    private javax.swing.JMenuItem mniPayedOrders;
    private javax.swing.JMenuItem mniSession;
    private javax.swing.JMenuItem mniUpdateRemoteDB;
    private javax.swing.JMenuItem mniUpdateSuppliers;
    private javax.swing.JMenuItem mniViewStock;
    private javax.swing.JMenuItem restoreDB;
    private javax.swing.JTextField txtSessionSale;
    private javax.swing.JTextField txtStockSale;
    // End of variables declaration//GEN-END:variables

    public void addIntFrame(JInternalFrame jif) {
        dskMain.add(jif);
        jif.setVisible(true);
        openFrameCount = getOpenedFrameCount();
        jif.setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
        try {
            //addToTaskBasr(tsk);
            jif.setSelected(true);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(null, "Cannot Select Opened Window");
        }
////        System.gc();
    }
    
    private int getOpenedFrameCount() {
        openFrameCount = 0;
        JInternalFrame[] frames = dskMain.getAllFrames();
        for (int i = 0; i < frames.length; i++) {
            openFrameCount = i;
        }
        return openFrameCount;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String argExp = (String) arg;
            if (argExp.equals("addOrder")) {
              //  setStat();
                calculateSessionSale();
            } else if (argExp.equals("addSupply")) {
             //   setStat();
                calculateSessionSale();
                ((ItemsFrame) frameList.get(0)).setTree();
            } else if (argExp.equals("addItem")) {
                calculateSessionSale();
                ((ItemsFrame) frameList.get(0)).setTree();
                ((ItemsFrame) frameList.get(0)).refreshFields();
                ((StockFrame) frameList.get(2)).refreshFields();
                ((StockFrame) frameList.get(2)).refreshFindListTable();
            } else if (argExp.equals("supplierAdded")) {
                ((StockFrame) frameList.get(2)).refreshFields();
                ((StockFrame) frameList.get(2)).refreshFindListTable();
            }
        }
////        System.gc();
    }
    
    private void setStat() {
        List<Item> items = ijc.findItemEntities();
        dlmCritical.removeAllElements();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            List<SupplyOrderDetail> sods = item.getSupplyOrderDetails();
            if (sods.size() > 0) {
                int qty = 0;
                if (sods != null) {
                    for (SupplyOrderDetail sod : sods) {
                        qty += sod.getRemainingQty();
                    }
                }
                //System.out.println("qty " + qty);
                /**
                 * Configuration file should keep maximum values
                 */
                if (qty <= 2) {
                    dlmCritical.addElement(OtherController.formatCode("I", item.getIid(), 6));
                    try {
                        boolean isExist = false;
                        SystemTray systemTray = SystemTray.getSystemTray();
                        TrayIcon[] trayIcons = systemTray.getTrayIcons();
                        for (int j = 0; j < trayIcons.length; j++) {
                            TrayIcon trayIcon = trayIcons[j];
                            if (trayIcon.getToolTip().equals(ico.getToolTip())) {
                                isExist = true;
                                ico = trayIcon;
                            }
                        }
                        if (!isExist) {
                            SystemTray.getSystemTray().add(ico);
                        }
                    } catch (AWTException ex) {
                    }
                    ico.displayMessage("Critical Stock", "You have some items that are running low in stock", TrayIcon.MessageType.INFO);
                }
            }
        }
        if (ojc.getOrdersCount() > 0) {
            List<Orders> orderses = ojc.findOrdersEntities(1, ojc.getOrdersCount() - 1);
            
            lblLastOrder.setText(OtherController.formatCode(
                    "OD", orderses.get(orderses.size() - 1).getOid(), 8));
            calculateSessionSale();
        }
////        System.gc();
    }
    
    private void calculateSessionSale() {
        if (logSession != null) {
            LogSession ls = lsjc.findLogSession(logSession.getSeid());
            
            List<Orders> orderss = ls.getOrderss();
            double sessionTotal = 0;
            for (Orders orders : orderss) {
                sessionTotal += orders.getTotal() - orders.getDiscount();
            }
            txtSessionSale.setText(OtherController.formatPrice(sessionTotal));
            sessionTotal = 0;
            List<SupplyOrder> supplyOrders = ls.getSupplyOrders();
            for (SupplyOrder supplyOrder : supplyOrders) {
                sessionTotal -= (supplyOrder.getTotal() - supplyOrder.getDiscount());
            }
            txtStockSale.setText(OtherController.formatPrice(sessionTotal));
        }
////        System.gc();
    }
    
    public JDesktopPane getDsk() {
        return dskMain;
    }
    
//    private void setItem() {
//        
//        Long loc = new Long("-1");
//        try {
//            Metric construction = cjc.findConstruction(loc);
//            if (construction == null) {
//                construction = new Metric();
//                construction.setMid(loc);
//                construction.setName("Undifined");
//                cjc.create(construction);
//            }
//            Manufacturer manufacturer = mjc.findManufacturer(loc);
//            if (manufacturer == null) {
//                manufacturer = new Manufacturer();
//                manufacturer.setManuid(loc);
//                manufacturer.setName("Undifined");
//                mjc.create(manufacturer);
//            }
//
//            Item item = ijc.findItem(loc);
//            
//            if (item == null) {
//                item = new Item();
//                item.setIid(loc);
//                item.setCategory(Category.Undifined);
//                item.setConstruction(construction);
//                item.setManufacturer(manufacturer);
//                item.setName("Undifined");
//                item.setTubeType(TubeType.NA);
//                item.setVehicleType(vt);
//                ijc.create(item);
//            }
//            Supplier supplier = sjc.findSupplier(loc);
//            if (supplier == null) {
//                supplier = new Supplier();
//                supplier.setAddress("Undifined");
//                supplier.setCompName("Undifined");
//                supplier.setDateAdded(new Date(Calendar.getInstance().getTimeInMillis()));
//                supplier.setTimeAdded(new Time(Calendar.getInstance().getTimeInMillis()));
//                supplier.setEmail("Undifined");
//                supplier.setName("Undifined");
//                supplier.setSuid(loc);
//                supplier.setTpno("Undifined");
//                
//                sjc.create(supplier);
//            }
//            
//            SupplyOrderDetail detail = sodjc.findSupplyOrderDetail(loc);
//            if (detail == null) {
//                detail = new SupplyOrderDetail();
//                detail.setBuyingPrice(0L);
//                detail.setItem(item);
//                detail.setQty(Integer.MAX_VALUE);
//                detail.setSellingPrice(0L);
//                detail.setRemainingQty(Integer.MAX_VALUE - 1000);
//                detail.setSodid(loc);
//                
//            } else {
//                detail.setRemainingQty(Integer.MAX_VALUE - 1000);
//                sodjc.edit(detail);
//            }
//            
//            SupplyOrder order = sojc.findSupplyOrder(loc);
//            if (order == null) {
//                
//                List<SupplyOrderDetail> details = new ArrayList<>();
//                details.add(detail);
//                order = new SupplyOrder();
//                order.setSoid(loc);
//                order.setDiscount(0);
//                order.setLogSession(logSession);
//                order.setDateAdded(new Date(Calendar.getInstance().getTimeInMillis()));
//                order.setTimeAdded(new Time(Calendar.getInstance().getTimeInMillis()));
//                order.setSupplier(supplier);
//                order.setTotal(0);
//                order.setSupplyOrderDetails(details);
//                
//                sojc.create(order);
//                
//            }
//        } catch (NonexistentEntityException ex) {
//            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException | SQLException e) {
//            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
//        } catch (Exception e) {
//            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
}
