package Dashboard_Internal_Frames;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
import Database.DBConnection;
import sarisaristoreinventoryandsalesmanagementsytem.InternalFrameUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Cashier extends javax.swing.JInternalFrame {
    
    private String userRole;
    private int selectedProductID;
    
    public Cashier(String userRole) {
        initComponents();

        this.userRole = userRole;

        InternalFrameUtils.setupInternalFrame(this);

        DefaultTableModel cartModel =
                (DefaultTableModel) tblCart.getModel();

        cartModel.setRowCount(0);
        
        setupCartActionColumn();

        searchProduct();
    }

    private void searchProduct() {

        String searchText = txtSearchProduct.getText().trim();

        if (searchText.equals("Search product by name...")) {
            searchText = "";
        }

        String sql;


        if (searchText.isEmpty()) {
            sql = "SELECT productID, productName, category, sellingPrice, stock "
                    + "FROM tbl_products "
                    + "WHERE status = 'Active'";
        } else {
            sql = "SELECT productID, productName, category, sellingPrice, stock "
                    + "FROM tbl_products "
                    + "WHERE productName LIKE ? AND status = 'Active'";
        }


        try (Connection conn = DBConnection.connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            if (!searchText.isEmpty()) {
                pst.setString(1, "%" + searchText + "%");
            }

            ResultSet rs = pst.executeQuery();

            DefaultTableModel model =
                    (DefaultTableModel) tblProductList.getModel();

            model.setRowCount(0);

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("productID"),
                    rs.getString("productName"),
                    rs.getString("category"),
                    rs.getBigDecimal("sellingPrice"),
                    rs.getInt("stock")
                });
            
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to search product.\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
private void calculateCartTotal() {

    BigDecimal total = BigDecimal.ZERO;

    DefaultTableModel cartModel =
            (DefaultTableModel) tblCart.getModel();

    for (int i = 0; i < cartModel.getRowCount(); i++) {

        BigDecimal subtotal =
                (BigDecimal) cartModel.getValueAt(i, 3);

        total = total.add(subtotal);
    }

    lblTotal.setText(
            "Total: "
            + total.setScale(
                    2,
                    java.math.RoundingMode.HALF_UP
            )
    );
}
    
    
    private void calculateChange() {

    String cashText =
            txtCashReceived.getText().trim();

    if (cashText.isEmpty()) {
        lblChange.setText("Change: 0.00");
        return;
    }

    try {

        BigDecimal cashReceived =
                new BigDecimal(cashText);

        BigDecimal total = BigDecimal.ZERO;

        DefaultTableModel cartModel =
                (DefaultTableModel) tblCart.getModel();

        for (int i = 0; i < cartModel.getRowCount(); i++) {

            BigDecimal subtotal =
                    (BigDecimal) cartModel.getValueAt(i, 3);

            total = total.add(subtotal);
        }

        BigDecimal change =
                cashReceived.subtract(total);

        if (change.compareTo(BigDecimal.ZERO) < 0) {

            lblChange.setText(
                    "Change: 0.00"
            );

            return;
        }

        lblChange.setText(
                "Change: "
                + change.setScale(
                        2,
                        java.math.RoundingMode.HALF_UP
                )
        );

    } catch (NumberFormatException e) {

        lblChange.setText(
                "Change: 0.00"
        );
    }
}
    
    
private void updateDailyGain(
        Connection conn,
        BigDecimal totalSales,
        BigDecimal totalCost
) throws SQLException {

    BigDecimal totalGain = totalSales.subtract(totalCost);

    String sql =
            "INSERT INTO tbl_daily_gain "
            + "(gainDate, totalSales, totalCost, totalGain) "
            + "VALUES (CURDATE(), ?, ?, ?) "
            + "ON DUPLICATE KEY UPDATE "
            + "totalSales = totalSales + VALUES(totalSales), "
            + "totalCost = totalCost + VALUES(totalCost), "
            + "totalGain = totalGain + VALUES(totalGain)";

    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setBigDecimal(1, totalSales);
        pst.setBigDecimal(2, totalCost);
        pst.setBigDecimal(3, totalGain);
        pst.executeUpdate();
    }
}
    
private void setupCartActionColumn() {

    // + Button - Blue
    DefaultTableCellRenderer addRenderer =
            new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            Component component =
                    super.getTableCellRendererComponent(
                            table,
                            value,
                            isSelected,
                            hasFocus,
                            row,
                            column
                    );

            component.setBackground(new Color(0, 123, 255));
            component.setForeground(Color.WHITE);

            setHorizontalAlignment(
                    DefaultTableCellRenderer.CENTER
            );

            return component;
        }
    };

    // − Button - Red
    DefaultTableCellRenderer subtractRenderer =
            new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            Component component =
                    super.getTableCellRendererComponent(
                            table,
                            value,
                            isSelected,
                            hasFocus,
                            row,
                            column
                    );

            component.setBackground(new Color(220, 53, 69));
            component.setForeground(Color.WHITE);

            setHorizontalAlignment(
                    DefaultTableCellRenderer.CENTER
            );

            return component;
        }
    };

    // Remove Button - Gray
    DefaultTableCellRenderer removeRenderer =
            new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            Component component =
                    super.getTableCellRendererComponent(
                            table,
                            value,
                            isSelected,
                            hasFocus,
                            row,
                            column
                    );

            component.setBackground(new Color(108, 117, 125));
            component.setForeground(Color.WHITE);

            setHorizontalAlignment(
                    DefaultTableCellRenderer.CENTER
            );

            return component;
        }
    };

    // +
    tblCart.getColumnModel()
            .getColumn(4)
            .setCellRenderer(addRenderer);

    // −
    tblCart.getColumnModel()
            .getColumn(5)
            .setCellRenderer(subtractRenderer);

    // Remove
    tblCart.getColumnModel()
            .getColumn(6)
            .setCellRenderer(removeRenderer);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        pnlDashboard = new javax.swing.JPanel();
        lblDashboardDescription = new javax.swing.JLabel();
        pnlSearch = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        txtSearchProduct = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        txtCashReceived = new javax.swing.JTextField();
        lblChange = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblDashboardTitle1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductList = new javax.swing.JTable();
        lblDashboardTitle4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblSelectedProduct = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnAddToCart = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        lblDashboardTitle3 = new javax.swing.JLabel();
        btnUtang = new javax.swing.JButton();
        btnPayCash = new javax.swing.JButton();
        btnPersonalUse = new javax.swing.JButton();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Dashboard");
        setPreferredSize(new java.awt.Dimension(1570, 960));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard.setBorder(new javax.swing.border.MatteBorder(null));
        pnlDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDashboardDescription.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription.setText("Process customer transactions. ");
        pnlDashboard.add(lblDashboardDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 54, -1, -1));

        btnSearch.setBackground(new java.awt.Color(0, 51, 255));
        btnSearch.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.setFocusable(false);
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        txtSearchProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtSearchProduct.setText("Search product by name...");
        txtSearchProduct.addActionListener(this::txtSearchProductActionPerformed);
        txtSearchProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchProductKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtSearchProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(txtSearchProduct))
                .addContainerGap())
        );

        pnlDashboard.add(pnlSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(608, 14, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotal.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("TOTAL:");
        jPanel1.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 16, 326, -1));

        txtCashReceived.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtCashReceived.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCashReceivedKeyReleased(evt);
            }
        });
        jPanel1.add(txtCashReceived, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 150, 40));

        lblChange.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblChange.setForeground(new java.awt.Color(255, 255, 255));
        lblChange.setText("Change: ");
        jPanel1.add(lblChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 280, -1));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cash Received: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        pnlDashboard.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 415, -1, 160));

        lblDashboardTitle1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle1.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle1.setText("Cashier");
        pnlDashboard.add(lblDashboardTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 14, 266, -1));

        jPanel2.setBackground(new java.awt.Color(0, 51, 255));

        tblProductList.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        tblProductList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product ID", "Product Name", "Category", "Selling Price", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProductList);
        if (tblProductList.getColumnModel().getColumnCount() > 0) {
            tblProductList.getColumnModel().getColumn(0).setResizable(false);
            tblProductList.getColumnModel().getColumn(1).setResizable(false);
            tblProductList.getColumnModel().getColumn(2).setResizable(false);
            tblProductList.getColumnModel().getColumn(3).setResizable(false);
            tblProductList.getColumnModel().getColumn(4).setResizable(false);
        }

        lblDashboardTitle4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblDashboardTitle4.setForeground(new java.awt.Color(255, 255, 255));
        lblDashboardTitle4.setText("PRODUCT LIST");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 49, -1, -1));

        lblSelectedProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblSelectedProduct.setForeground(new java.awt.Color(0, 51, 255));
        jPanel4.add(lblSelectedProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 260, -1));

        jPanel5.setBackground(new java.awt.Color(0, 51, 255));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Select a row to add to cart..");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, 40));

        btnAddToCart.setBackground(new java.awt.Color(255, 153, 0));
        btnAddToCart.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnAddToCart.setForeground(new java.awt.Color(255, 255, 255));
        btnAddToCart.setText("ADD TO CART");
        btnAddToCart.addActionListener(this::btnAddToCartActionPerformed);
        jPanel4.add(btnAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 240, 50));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setText("SELECTED PRODUCT: ");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        txtQuantity.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtQuantity.addActionListener(this::txtQuantityActionPerformed);
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });
        jPanel4.add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 150, 40));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 0));
        jLabel4.setText("Quantity: ");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("_______________________________________________");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 280, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblDashboardTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDashboardTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlDashboard.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 153, 0));

        tblCart.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Quantity", "Price", "Subtotal", "+ Add Qty", "- Remove Qty", "Remove All"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCartMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCart);
        if (tblCart.getColumnModel().getColumnCount() > 0) {
            tblCart.getColumnModel().getColumn(0).setResizable(false);
            tblCart.getColumnModel().getColumn(1).setResizable(false);
            tblCart.getColumnModel().getColumn(2).setResizable(false);
            tblCart.getColumnModel().getColumn(3).setResizable(false);
            tblCart.getColumnModel().getColumn(4).setResizable(false);
            tblCart.getColumnModel().getColumn(5).setResizable(false);
            tblCart.getColumnModel().getColumn(6).setResizable(false);
        }

        lblDashboardTitle3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblDashboardTitle3.setForeground(new java.awt.Color(255, 255, 255));
        lblDashboardTitle3.setText("CART");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblDashboardTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDashboardTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlDashboard.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 416, 742, 270));

        btnUtang.setBackground(new java.awt.Color(153, 153, 153));
        btnUtang.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnUtang.setText("UTANG");
        btnUtang.addActionListener(this::btnUtangActionPerformed);
        pnlDashboard.add(btnUtang, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 590, 150, 40));

        btnPayCash.setBackground(new java.awt.Color(0, 51, 255));
        btnPayCash.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnPayCash.setForeground(new java.awt.Color(255, 255, 255));
        btnPayCash.setText("PAY CASH");
        btnPayCash.addActionListener(this::btnPayCashActionPerformed);
        pnlDashboard.add(btnPayCash, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 590, 149, 42));

        btnPersonalUse.setBackground(new java.awt.Color(204, 204, 204));
        btnPersonalUse.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnPersonalUse.setText("PERSONAL USE / DAMAGED / LOST");
        btnPersonalUse.addActionListener(this::btnPersonalUseActionPerformed);
        pnlDashboard.add(btnPersonalUse, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 640, 340, 42));

        getContentPane().add(pnlDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchProductActionPerformed

    }//GEN-LAST:event_txtSearchProductActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchProduct();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblProductListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductListMouseClicked
        int selectedRow = tblProductList.getSelectedRow();
        
        if (selectedRow == -1) {
             return;
        }
        
        selectedProductID =
                (int) tblProductList.getValueAt(selectedRow, 0);
        
        String selectedProductName =
                tblProductList.getValueAt(selectedRow, 1).toString();
        
        lblSelectedProduct.setText(
                 selectedProductName
        );
        txtQuantity.setText("");
    }//GEN-LAST:event_tblProductListMouseClicked

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed

        // Get the quantity entered by the cashier
        String quantityText =
                txtQuantity.getText().trim();

        System.out.println(
                "Quantity entered: [" + quantityText + "]"
        );

        // Check if quantity is empty
        if (quantityText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a quantity.",
                    "Quantity Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Convert quantity from String to int
        int quantity;

        try {

            quantity = Integer.parseInt(quantityText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid quantity.",
                    "Invalid Quantity",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Check if quantity is greater than 0
        if (quantity <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be greater than 0.",
                    "Invalid Quantity",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Check if a product was selected
        if (tblProductList.getSelectedRow() == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a product first.",
                    "No Product Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Get the available stock of the selected product
        int availableStock =
                (int) tblProductList.getValueAt(
                        tblProductList.getSelectedRow(), 4
                );
        
        if (quantity > availableStock) {

            JOptionPane.showMessageDialog(
                    this,
                    "Not enough stock. Only "
                            + availableStock
                            + " pieces available.",
                    "Insufficient Stock",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }
        
        String productName =
            tblProductList.getValueAt(
                    tblProductList.getSelectedRow(), 1
            ).toString();
        
        String category =
            tblProductList.getValueAt(
                    tblProductList.getSelectedRow(), 2
            ).toString();
        
        BigDecimal sellingPrice =
            (BigDecimal) tblProductList.getValueAt(
                tblProductList.getSelectedRow(), 3
        );
        
        BigDecimal subtotal =
            sellingPrice.multiply(
                BigDecimal.valueOf(quantity)
        );
        
        DefaultTableModel cartModel =
                (DefaultTableModel) tblCart.getModel();

        // Check if product already exists in the cart
        boolean productAlreadyInCart = false;

        for (int i = 0; i < cartModel.getRowCount(); i++) {

            String cartProductName =
                    cartModel.getValueAt(i, 0).toString();

            if (cartProductName.equals(productName)) {

                int currentQuantity =
                        (int) cartModel.getValueAt(i, 1);

                int newQuantity =
                        currentQuantity + quantity;

                // Check combined quantity against stock
                if (newQuantity > availableStock) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Not enough stock available.\n"
                        + "Available stock: " + availableStock
                        + "\nCurrent quantity in cart: "
                        + currentQuantity,
                        "Insufficient Stock",
                        JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                BigDecimal newSubtotal =
                        sellingPrice.multiply(
                                BigDecimal.valueOf(newQuantity)
                        );

                cartModel.setValueAt(
                        newQuantity,
                        i,
                        1
                );

                cartModel.setValueAt(
                        newSubtotal,
                        i,
                        3
                );

                productAlreadyInCart = true;

                break;
            }
        }

        // If product is NOT already in cart, create a new row
        if (!productAlreadyInCart) {

            cartModel.addRow(new Object[]{
                productName,
                quantity,
                sellingPrice,
                subtotal,
                "+",
                "−",
                "Remove"
            });
        }
        calculateCartTotal();
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void txtCashReceivedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashReceivedKeyReleased

        calculateChange();

    }//GEN-LAST:event_txtCashReceivedKeyReleased

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityKeyReleased

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void tblCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCartMouseClicked
        int selectedRow = tblCart.getSelectedRow();
        int selectedColumn = tblCart.getSelectedColumn();

        if (selectedRow == -1) {
            return;
        }
        
        if (selectedColumn == 4) {

            int currentQuantity =
                    (int) tblCart.getValueAt(selectedRow, 1);

            String productName =
                    tblCart.getValueAt(selectedRow, 0).toString();

            String sql =
                    "SELECT stock FROM tbl_products "
                    + "WHERE productName = ?";

            try (Connection conn = DBConnection.connect();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, productName);

                try (ResultSet rs = pst.executeQuery()) {

                    if (rs.next()) {

                        int availableStock =
                                rs.getInt("stock");

                        if (currentQuantity >= availableStock) {

                            JOptionPane.showMessageDialog(
                                this,
                                "Cannot add more.\n"
                                + "Available stock: "
                                + availableStock,
                                "Stock Limit",
                                JOptionPane.WARNING_MESSAGE
                            );

                            return;
                        }

                        int newQuantity =
                                currentQuantity + 1;

                        BigDecimal price =
                                (BigDecimal) tblCart
                                        .getValueAt(selectedRow, 2);

                        BigDecimal newSubtotal =
                                price.multiply(
                                    BigDecimal.valueOf(newQuantity)
                                );

                        tblCart.setValueAt(
                                newQuantity,
                                selectedRow,
                                1
                        );

                        tblCart.setValueAt(
                                newSubtotal,
                                selectedRow,
                                3
                        );

                        calculateCartTotal();
                    }
                }

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(
                    this,
                    "Failed to check stock:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
        if (selectedColumn == 5) {

            int currentQuantity =
                    (int) tblCart.getValueAt(selectedRow, 1);

            int newQuantity =
                    currentQuantity - 1;

            if (newQuantity <= 0) {

                ((DefaultTableModel) tblCart.getModel())
                        .removeRow(selectedRow);

            } else {

                BigDecimal price =
                        (BigDecimal) tblCart.getValueAt(
                                selectedRow, 2
                        );

                BigDecimal newSubtotal =
                        price.multiply(
                                BigDecimal.valueOf(newQuantity)
                        );

                tblCart.setValueAt(
                        newQuantity,
                        selectedRow,
                        1
                );

                tblCart.setValueAt(
                        newSubtotal,
                        selectedRow,
                        3
                );
            }

            calculateCartTotal();
        }
        
        if (selectedColumn == 6) {

            DefaultTableModel cartModel =
                    (DefaultTableModel) tblCart.getModel();

            cartModel.removeRow(selectedRow);

            calculateCartTotal();
        }
    }//GEN-LAST:event_tblCartMouseClicked

    private void btnPayCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayCashActionPerformed

        DefaultTableModel cartModel =
                (DefaultTableModel) tblCart.getModel();

        // 1. Check if cart is empty
        if (cartModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Cart is empty. Please add a product first.",
                    "Empty Cart",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 2. Validate cash received
        String cashText = txtCashReceived.getText().trim();

        if (cashText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the cash received.",
                    "Cash Required",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        BigDecimal cashReceived;

        try {
            cashReceived = new BigDecimal(cashText);

            if (cashReceived.signum() < 0) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid, non-negative cash amount.",
                    "Invalid Cash",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 3. Calculate total selling price
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < cartModel.getRowCount(); i++) {
            BigDecimal subtotal =
                    (BigDecimal) cartModel.getValueAt(i, 3);

            total = total.add(subtotal);
        }

        if (cashReceived.compareTo(total) < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Insufficient cash.\n"
                    + "Total: " + total.setScale(2,
                            java.math.RoundingMode.HALF_UP)
                    + "\nCash Received: " + cashReceived.setScale(2,
                            java.math.RoundingMode.HALF_UP),
                    "Insufficient Cash",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        BigDecimal change = cashReceived.subtract(total);
        BigDecimal totalCost = BigDecimal.ZERO;

        // 4. Deduct stock and update daily gain together
        try (Connection conn = DBConnection.connect()) {

            if (conn == null) {
                throw new SQLException(
                        "Could not connect to the database."
                );
            }

            conn.setAutoCommit(false);

            try {
                // Check stock and get unit cost
                for (int i = 0; i < cartModel.getRowCount(); i++) {

                    String productName =
                            cartModel.getValueAt(i, 0).toString();

                    int quantity =
                            ((Number) cartModel.getValueAt(i, 1)).intValue();

                    String sql =
                            "SELECT stock, unitCost "
                            + "FROM tbl_products "
                            + "WHERE productName = ? "
                            + "AND status = 'Active' FOR UPDATE";

                    try (PreparedStatement pst =
                                 conn.prepareStatement(sql)) {

                        pst.setString(1, productName);

                        try (ResultSet rs = pst.executeQuery()) {

                            if (!rs.next()) {
                                throw new SQLException(
                                        "Product not found: " + productName
                                );
                            }

                            int stock = rs.getInt("stock");

                            if (quantity > stock) {
                                throw new SQLException(
                                        "Not enough stock for " + productName
                                        + ". Available: " + stock
                                        + ", requested: " + quantity
                                );
                            }

                            BigDecimal unitCost =
                                    rs.getBigDecimal("unitCost");

                            BigDecimal itemCost =
                                    unitCost.multiply(
                                            BigDecimal.valueOf(quantity)
                                    );

                            totalCost = totalCost.add(itemCost);
                        }
                    }
                }

                // Deduct stock
                for (int i = 0; i < cartModel.getRowCount(); i++) {

                    String productName =
                            cartModel.getValueAt(i, 0).toString();

                    int quantity =
                            ((Number) cartModel.getValueAt(i, 1)).intValue();

                    String sql =
                            "UPDATE tbl_products "
                            + "SET stock = stock - ? "
                            + "WHERE productName = ? "
                            + "AND status = 'Active' "
                            + "AND stock >= ?";

                    try (PreparedStatement pst =
                                 conn.prepareStatement(sql)) {

                        pst.setInt(1, quantity);
                        pst.setString(2, productName);
                        pst.setInt(3, quantity);

                        if (pst.executeUpdate() != 1) {
                            throw new SQLException(
                                    "Stock update failed for " + productName
                            );
                        }
                    }
                }

                // Update today's daily summary
                updateDailyGain(conn, total, totalCost);

                // Commit all database changes
                conn.commit();

            } catch (SQLException e) {

                try {
                    conn.rollback();
                } catch (SQLException rollbackError) {
                    e.addSuppressed(rollbackError);
                }

                throw e;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment could not be completed:\n"
                    + e.getMessage(),
                    "Payment Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // 5. Reset the cart after a successful transaction
        cartModel.setRowCount(0);
        txtCashReceived.setText("");
        lblChange.setText("Change: 0.00");
        lblTotal.setText("TOTAL: 0.00");
        txtQuantity.setText("");
        lblSelectedProduct.setText("");

        searchProduct();

        // 6. Show payment receipt summary
        JOptionPane.showMessageDialog(
                this,
                "Payment successful!\n"
                + "Total: " + total.setScale(2,
                        java.math.RoundingMode.HALF_UP)
                + "\nCash Received: " + cashReceived.setScale(2,
                        java.math.RoundingMode.HALF_UP)
                + "\nChange: " + change.setScale(2,
                        java.math.RoundingMode.HALF_UP)
                + "\nGain: " + total.subtract(totalCost).setScale(2,
                        java.math.RoundingMode.HALF_UP),
                "Payment Successful",
                JOptionPane.INFORMATION_MESSAGE
        );
    }//GEN-LAST:event_btnPayCashActionPerformed

    private void btnUtangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUtangActionPerformed

        DefaultTableModel cartModel =
                (DefaultTableModel) tblCart.getModel();

        // 1. Check if cart is empty
        if (cartModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "The cart is empty.",
                    "Empty Cart",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 2. Confirm that this is an UTANG transaction
        String cashText = txtCashReceived.getText().trim();

        if (!cashText.isEmpty()) {

            int confirmation = JOptionPane.showConfirmDialog(
                    this,
                    "Cash received has been entered.\n\n"
                    + "Are you sure you want to record this "
                    + "as an UTANG transaction?",
                    "UTANG Transaction",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                    
            );

            if (confirmation != JOptionPane.YES_OPTION) {
                return;
            }
        }
      

        // 3. Build the physical notebook reminder
        StringBuilder utangList = new StringBuilder();

        utangList.append(
                "UTANG REMINDER\n\n"
                + "Please write the following items from your customers\n"
                + "in the physical utang notebook before proceeding:\n\n"
        );

        for (int i = 0; i < cartModel.getRowCount(); i++) {

            String productName =
                    cartModel.getValueAt(i, 0).toString();

            int quantity =
                    ((Number) cartModel.getValueAt(i, 1)).intValue();

            BigDecimal subtotal =
                    (BigDecimal) cartModel.getValueAt(i, 3);

            utangList.append(
                    "x" + quantity
                    + "   " + productName
                    + "   ₱" + subtotal.setScale(
                            2, java.math.RoundingMode.HALF_UP)
                    + "\n"
            );
        }

        utangList.append(
                "\nPlease list them to help manage your store properly."
                + "\n\nHave you written the utang list?"
        );

        // 4. Confirm that the notebook entry has been written
        int answer = JOptionPane.showOptionDialog(
                this,
                utangList.toString(),
                "UTANG REMINDER",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"YES"},
                "YES"
        );

        // Stop if the dialog is closed without choosing YES
        if (answer != 0) {
            return;
        }

        // 5. Calculate total sales and prepare total cost
        BigDecimal totalSales = BigDecimal.ZERO;
        BigDecimal totalCost = BigDecimal.ZERO;

        for (int i = 0; i < cartModel.getRowCount(); i++) {

            BigDecimal subtotal =
                    (BigDecimal) cartModel.getValueAt(i, 3);

            totalSales = totalSales.add(subtotal);
        }

        // 6. Check stock, deduct inventory, and update Daily Gain
        try (Connection conn = DBConnection.connect()) {

            if (conn == null) {
                throw new SQLException(
                        "Could not connect to the database."
                );
            }

            conn.setAutoCommit(false);

            try {

                // Check available stock and retrieve unit cost
                for (int i = 0; i < cartModel.getRowCount(); i++) {

                    String productName =
                            cartModel.getValueAt(i, 0).toString();

                    int quantity =
                            ((Number) cartModel.getValueAt(i, 1)).intValue();

                    String sql =
                            "SELECT stock, unitCost "
                            + "FROM tbl_products "
                            + "WHERE productName = ? "
                            + "AND status = 'Active' "
                            + "FOR UPDATE";

                    try (PreparedStatement pst =
                                 conn.prepareStatement(sql)) {

                        pst.setString(1, productName);

                        try (ResultSet rs = pst.executeQuery()) {

                            if (!rs.next()) {
                                throw new SQLException(
                                        "Product not found: " + productName
                                );
                            }

                            int stock = rs.getInt("stock");

                            if (quantity > stock) {
                                throw new SQLException(
                                        "Not enough stock for "
                                        + productName
                                        + ".\nAvailable stock: " + stock
                                        + "\nQuantity in cart: " + quantity
                                );
                            }

                            BigDecimal unitCost =
                                    rs.getBigDecimal("unitCost");

                            BigDecimal itemCost =
                                    unitCost.multiply(
                                            BigDecimal.valueOf(quantity)
                                    );

                            totalCost = totalCost.add(itemCost);
                        }
                    }
                }

                // Deduct stock only after all products pass validation
                for (int i = 0; i < cartModel.getRowCount(); i++) {

                    String productName =
                            cartModel.getValueAt(i, 0).toString();

                    int quantity =
                            ((Number) cartModel.getValueAt(i, 1)).intValue();

                    String sql =
                            "UPDATE tbl_products "
                            + "SET stock = stock - ? "
                            + "WHERE productName = ? "
                            + "AND status = 'Active' "
                            + "AND stock >= ?";

                    try (PreparedStatement pst =
                                 conn.prepareStatement(sql)) {

                        pst.setInt(1, quantity);
                        pst.setString(2, productName);
                        pst.setInt(3, quantity);

                        if (pst.executeUpdate() != 1) {
                            throw new SQLException(
                                    "Stock update failed for " + productName
                            );
                        }
                    }
                }

                // Add this UTANG transaction to today's Daily Gain
                updateDailyGain(conn, totalSales, totalCost);

                // Save all database changes together
                conn.commit();

            } catch (SQLException e) {

                try {
                    conn.rollback();
                } catch (SQLException rollbackError) {
                    e.addSuppressed(rollbackError);
                }

                throw e;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Utang could not be recorded:\n"
                    + e.getMessage(),
                    "Utang Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // 7. Reset the cashier interface after success
        cartModel.setRowCount(0);
        
        txtCashReceived.setText("");
        lblChange.setText("Change: 0.00");
        lblTotal.setText("TOTAL: 0.00");
        txtQuantity.setText("");
        lblSelectedProduct.setText("");

        searchProduct();

        // 8. Show confirmation
        JOptionPane.showMessageDialog(
                this,
                "Utang recorded successfully!\n"
                + "The items have been deducted from inventory."
                + "\n\nTotal: ₱"
                + totalSales.setScale(
                        2, java.math.RoundingMode.HALF_UP)
                + "\nGain: ₱"
                + totalSales.subtract(totalCost).setScale(
                        2, java.math.RoundingMode.HALF_UP),
                "Utang Recorded",
                JOptionPane.INFORMATION_MESSAGE
        );
    }//GEN-LAST:event_btnUtangActionPerformed

    private void btnPersonalUseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalUseActionPerformed

        DefaultTableModel cartModel =
                (DefaultTableModel) tblCart.getModel();

        // 1. Check if the cart is empty
        if (cartModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please add a product to the cart first.",
                    "Empty Cart",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 2. Confirm the deduction
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to deduct these products "
                + "from inventory?\n\n"
                + "No sale or utang will be recorded.",
                "Confirm Stock Deduction",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        // 3. Connect to the database
        try (Connection conn = DBConnection.connect()) {

            if (conn == null) {
                throw new SQLException(
                        "Could not connect to the database."
                );
            }

            conn.setAutoCommit(false);

            try {

                // 4. Validate every product before deducting stock
                for (int i = 0; i < cartModel.getRowCount(); i++) {

                    String productName =
                            cartModel.getValueAt(i, 0).toString();

                    int quantity =
                            ((Number) cartModel.getValueAt(i, 1)).intValue();

                    if (quantity <= 0) {
                        throw new SQLException(
                                "Invalid quantity for " + productName
                        );
                    }

                    String sql =
                            "SELECT stock "
                            + "FROM tbl_products "
                            + "WHERE productName = ? "
                            + "AND status = 'Active' "
                            + "FOR UPDATE";

                    try (PreparedStatement pst =
                                 conn.prepareStatement(sql)) {

                        pst.setString(1, productName);

                        try (ResultSet rs = pst.executeQuery()) {

                            if (!rs.next()) {
                                throw new SQLException(
                                        "Product not found: " + productName
                                );
                            }

                            int stock = rs.getInt("stock");

                            if (quantity > stock) {
                                throw new SQLException(
                                        "Not enough stock for " + productName
                                        + ". Available stock: " + stock
                                        + "\nQuantity selected: " + quantity
                                );
                            }
                        }
                    }
                }

                // 5. Deduct stock after all products pass validation
                for (int i = 0; i < cartModel.getRowCount(); i++) {

                    String productName =
                            cartModel.getValueAt(i, 0).toString();

                    int quantity =
                            ((Number) cartModel.getValueAt(i, 1)).intValue();

                    String sql =
                            "UPDATE tbl_products "
                            + "SET stock = stock - ? "
                            + "WHERE productName = ? "
                            + "AND status = 'Active' "
                            + "AND stock >= ?";

                    try (PreparedStatement pst =
                                 conn.prepareStatement(sql)) {

                        pst.setInt(1, quantity);
                        pst.setString(2, productName);
                        pst.setInt(3, quantity);

                        if (pst.executeUpdate() != 1) {
                            throw new SQLException(
                                    "Stock deduction failed for " + productName
                            );
                        }
                    }
                }

                // 6. Save all deductions together
                conn.commit();

            } catch (SQLException e) {

                try {
                    conn.rollback();
                } catch (SQLException rollbackError) {
                    e.addSuppressed(rollbackError);
                }

                throw e;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Stock deduction failed:\n" + e.getMessage(),
                    "Deduction Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // 7. Reset the Cashier interface
        cartModel.setRowCount(0);

        txtCashReceived.setText("");
        lblChange.setText("Change: 0.00");
        lblTotal.setText("TOTAL: 0.00");
        txtQuantity.setText("");
        lblSelectedProduct.setText("");

        searchProduct();

        // 8. Show success message
        JOptionPane.showMessageDialog(
                this,
                "Products deducted successfully!\n"
                + "Inventory stock has been updated.\n"
                + "No sale, utang, or daily gain was recorded.",
                "Stock Deducted",
                JOptionPane.INFORMATION_MESSAGE
        );
    }//GEN-LAST:event_btnPersonalUseActionPerformed

    private void txtSearchProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchProductKeyReleased
        searchProduct();
    }//GEN-LAST:event_txtSearchProductKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnPayCash;
    private javax.swing.JButton btnPersonalUse;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUtang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblChange;
    private javax.swing.JLabel lblDashboardDescription;
    private javax.swing.JLabel lblDashboardTitle1;
    private javax.swing.JLabel lblDashboardTitle3;
    private javax.swing.JLabel lblDashboardTitle4;
    private javax.swing.JLabel lblSelectedProduct;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblProductList;
    private javax.swing.JTextField txtCashReceived;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearchProduct;
    // End of variables declaration//GEN-END:variables
}
