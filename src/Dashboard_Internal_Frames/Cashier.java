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
                    + "FROM tbl_products";
        } else {
            sql = "SELECT productID, productName, category, sellingPrice, stock "
                    + "FROM tbl_products "
                    + "WHERE productName LIKE ?";
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

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Dashboard");
        setPreferredSize(new java.awt.Dimension(1570, 960));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard.setBorder(new javax.swing.border.MatteBorder(null));

        lblDashboardDescription.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription.setText("Process customer transactions. ");

        btnSearch.setBackground(new java.awt.Color(0, 51, 255));
        btnSearch.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.setFocusable(false);
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        txtSearchProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtSearchProduct.setText("Search product by name...");
        txtSearchProduct.addActionListener(this::txtSearchProductActionPerformed);

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
        jPanel1.add(txtCashReceived, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 150, 40));

        lblChange.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblChange.setForeground(new java.awt.Color(255, 255, 255));
        lblChange.setText("Change: ");
        jPanel1.add(lblChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 280, -1));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cash Received: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        lblDashboardTitle1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle1.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle1.setText("Cashier");

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
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, 50));

        btnAddToCart.setBackground(new java.awt.Color(255, 153, 0));
        btnAddToCart.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnAddToCart.setForeground(new java.awt.Color(255, 255, 255));
        btnAddToCart.setText("ADD TO CART");
        btnAddToCart.addActionListener(this::btnAddToCartActionPerformed);
        jPanel4.add(btnAddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 240, 50));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setText("SELECTED PRODUCT: ");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        txtQuantity.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtQuantity.addActionListener(this::txtQuantityActionPerformed);
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });
        jPanel4.add(txtQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 150, 40));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 0));
        jLabel4.setText("Quantity: ");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnUtang.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnUtang.setText("UTANG");
        btnUtang.addActionListener(this::btnUtangActionPerformed);

        btnPayCash.setBackground(new java.awt.Color(0, 51, 255));
        btnPayCash.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnPayCash.setForeground(new java.awt.Color(255, 255, 255));
        btnPayCash.setText("PAY CASH");
        btnPayCash.addActionListener(this::btnPayCashActionPerformed);

        javax.swing.GroupLayout pnlDashboardLayout = new javax.swing.GroupLayout(pnlDashboard);
        pnlDashboard.setLayout(pnlDashboardLayout);
        pnlDashboardLayout.setHorizontalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDashboardLayout.createSequentialGroup()
                        .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDashboardDescription)
                            .addComponent(lblDashboardTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(pnlDashboardLayout.createSequentialGroup()
                        .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlDashboardLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlDashboardLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(btnUtang, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnPayCash, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(26, Short.MAX_VALUE))))
        );
        pnlDashboardLayout.setVerticalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDashboardLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDashboardLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lblDashboardTitle1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDashboardDescription)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDashboardLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUtang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPayCash, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(pnlDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchProductActionPerformed
        // TODO add your handling code here:
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
        System.out.println("PAY CASH BUTTON CLICKED");
        
        DefaultTableModel cartModel =
            (DefaultTableModel) tblCart.getModel();

    if (cartModel.getRowCount() == 0) {

        JOptionPane.showMessageDialog(
            this,
            "Cart is empty. Please add a product first.",
            "Empty Cart",
            JOptionPane.WARNING_MESSAGE
        );

        return;
    }
    
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

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid cash amount.",
                "Invalid Cash",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }
        
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
                + "Total: ₱" + total.setScale(2)
                + "\nCash Received: ₱" + cashReceived.setScale(2),
                "Insufficient Cash",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }
        
        BigDecimal change =
            cashReceived.subtract(total);
        
        for (int i = 0; i < cartModel.getRowCount(); i++) {

        String productName =
                cartModel.getValueAt(i, 0).toString();

        int cartQuantity =
                (int) cartModel.getValueAt(i, 1);

        String sql =
                "SELECT stock FROM tbl_products "
                + "WHERE productName = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, productName);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    int currentStock =
                            rs.getInt("stock");

                    if (cartQuantity > currentStock) {

                        JOptionPane.showMessageDialog(
                            this,
                            "Not enough stock for "
                            + productName + ".\n"
                            + "Available stock: "
                            + currentStock
                            + "\nQuantity in cart: "
                            + cartQuantity,
                            "Insufficient Stock",
                            JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                } else {

                    JOptionPane.showMessageDialog(
                        this,
                        "Product not found: "
                        + productName,
                        "Product Not Found",
                        JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "Failed to check product stock:\n"
                + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );

            return;
        }
    }
        for (int i = 0; i < cartModel.getRowCount(); i++) {

            String productName =
                    cartModel.getValueAt(i, 0).toString();

            int cartQuantity =
                    (int) cartModel.getValueAt(i, 1);

            String sql =
                    "UPDATE tbl_products "
                    + "SET stock = stock - ? "
                    + "WHERE productName = ?";

            try (Connection conn = DBConnection.connect();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setInt(1, cartQuantity);
                pst.setString(2, productName);

                int rowsUpdated = pst.executeUpdate();

                System.out.println(
                    "Rows updated for "
                    + productName
                    + ": "
                    + rowsUpdated
                );

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(
                    this,
                    "Failed to update product stock:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }
        //RESET
        cartModel.setRowCount(0);
        txtCashReceived.setText("");
        lblChange.setText("Change: 0.00");
        lblTotal.setText("TOTAL: 0.00");
        txtQuantity.setText("");
        lblSelectedProduct.setText("");
        
        searchProduct();
        
        JOptionPane.showMessageDialog(
            this,
            "Payment successful!\n"
            + "Total: ₱" + total.setScale(2)
            + "\nCash Received: ₱" + cashReceived.setScale(2)
            + "\nChange: ₱" + change.setScale(2),
            "Payment Successful",
            JOptionPane.INFORMATION_MESSAGE
        );
    }//GEN-LAST:event_btnPayCashActionPerformed

    private void btnUtangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUtangActionPerformed
        String cashText = txtCashReceived.getText().trim();

    if (!cashText.isEmpty()) {

        int answer = JOptionPane.showConfirmDialog(
            this,
            "Cash received has been entered.\n\n"
            + "Are you sure you want to record this "
            + "as an UTANG transaction?",
            "UTANG Transaction",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (answer != JOptionPane.YES_OPTION) {
            return;
        }

        txtCashReceived.setText("");
        lblChange.setText("Change: 0.00");
    }
        
        DefaultTableModel cartModel =
                (DefaultTableModel) tblCart.getModel();

        // Check if cart is empty
        if (cartModel.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                this,
                "The cart is empty.",
                "Empty Cart",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Build the utang list
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
                    (int) cartModel.getValueAt(i, 1);

            BigDecimal subtotal =
                    (BigDecimal) cartModel.getValueAt(i, 3);

            utangList.append(
                "x" + quantity
                + "   " + productName
                + "   ₱" + subtotal.setScale(2)
                + "\n"
            );
        }

        utangList.append(
            "\nPlease list them to help manage your store properly."
            + "\n\nHave you written the utang list?"
        );

        // YES button
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

        // If the dialog is closed using X, stop the process
        if (answer != 0) {
            return;
        }
        
                // Re-check stock before recording utang
        for (int i = 0; i < cartModel.getRowCount(); i++) {

            String productName =
                    cartModel.getValueAt(i, 0).toString();

            int cartQuantity =
                    (int) cartModel.getValueAt(i, 1);

            String sql =
                    "SELECT stock FROM tbl_products "
                    + "WHERE productName = ?";

            try (Connection conn = DBConnection.connect();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, productName);

                try (ResultSet rs = pst.executeQuery()) {

                    if (rs.next()) {

                        int currentStock =
                                rs.getInt("stock");

                        if (cartQuantity > currentStock) {

                            JOptionPane.showMessageDialog(
                                this,
                                "Not enough stock for "
                                + productName
                                + ".\nAvailable stock: "
                                + currentStock
                                + "\nQuantity in cart: "
                                + cartQuantity,
                                "Insufficient Stock",
                                JOptionPane.WARNING_MESSAGE
                            );

                            return;
                        }

                    } else {

                        JOptionPane.showMessageDialog(
                            this,
                            "Product not found: "
                            + productName,
                            "Product Not Found",
                            JOptionPane.ERROR_MESSAGE
                        );

                        return;
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

                return;
            }
        }
        
                // Deduct utang quantities from inventory
        for (int i = 0; i < cartModel.getRowCount(); i++) {

            String productName =
                    cartModel.getValueAt(i, 0).toString();

            int cartQuantity =
                    (int) cartModel.getValueAt(i, 1);

            String sql =
                    "UPDATE tbl_products "
                    + "SET stock = stock - ? "
                    + "WHERE productName = ?";

            try (Connection conn = DBConnection.connect();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setInt(1, cartQuantity);
                pst.setString(2, productName);

                int rowsUpdated =
                        pst.executeUpdate();

                System.out.println(
                    "Rows updated for "
                    + productName
                    + ": "
                    + rowsUpdated
                );

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(
                    this,
                    "Failed to update product stock:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );

                return;
            }
        }
        //RESET
        cartModel.setRowCount(0);
        txtCashReceived.setText("");
        lblChange.setText("Change: 0.00");
        lblTotal.setText("TOTAL: 0.00");
        txtQuantity.setText("");
        lblSelectedProduct.setText("");
        
        searchProduct();
        
        JOptionPane.showMessageDialog(
            this,
            "Utang recorded successfully!\n"
            + "The items have been deducted from inventory.",
            "Utang Recorded",
            JOptionPane.INFORMATION_MESSAGE
        );
    }//GEN-LAST:event_btnUtangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnPayCash;
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
