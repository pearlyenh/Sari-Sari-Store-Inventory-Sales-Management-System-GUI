/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Dashboard_Internal_Frames;

import Database.DBConnection;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.List;

import sarisaristoreinventoryandsalesmanagementsytem.InternalFrameUtils;

public class RestockProduct extends javax.swing.JInternalFrame {
    //list to remember all product names
    private final List<String> allProductNames = new ArrayList<>();
    private boolean updatingProductDropdown = false;
    

    private String userRole;
    
    private int currentStock;
    private BigDecimal currentUnitCost;
    private BigDecimal currentSellingPrice;
    
    public RestockProduct(String userRole) {
        initComponents();
        
        this.userRole = userRole;
        
        loadProducts();
    }
    
private void loadProducts() {
    
    String sql =
            "SELECT productID, productName "
            + "FROM tbl_products "
            + "WHERE status = 'Active' "
            + "ORDER BY productName";

    allProductNames.clear();
    cmbProduct.removeAllItems();

    try (Connection conn = DBConnection.connect();
         PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        
        cmbProduct.addItem("Select a product...");

        while (rs.next()) {
            String productName = rs.getString("productName");

            allProductNames.add(productName);
            cmbProduct.addItem(productName);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
                this,
                "Failed to load products:\n" + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
private void loadProductInformation() {

    if (cmbProduct.getSelectedItem() == null) {
        return;
    }

    String productName =
            cmbProduct.getSelectedItem().toString();

    if (productName.equals("Select a product...")) {

        currentStock = 0;
        currentUnitCost = null;
        currentSellingPrice = null;

        lblCurrentStock.setText(
                "Current Stock: "
        );

        lblCurrentUnitCost.setText(
                "Current Unit Cost: "
        );

        lblCurrentSellingPrice.setText(
                "Current Selling Price: "
        );

        lblNewUnitCost.setText(
                "New Unit Cost: "
        );

        lblNewStock.setText(
                "New Stock: "
        );

        lblPotentialProfit.setText(
                "Potential Profit per Item: "
        );

        return;
    }

    String sql =
            "SELECT stock, unitCost, sellingPrice "
            + "FROM tbl_products "
            + "WHERE productName = ?";

    try (Connection conn = DBConnection.connect();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, productName);

        try (ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {

                // Store database values
                currentStock =
                        rs.getInt("stock");

                currentUnitCost =
                        rs.getBigDecimal("unitCost");

                currentSellingPrice =
                        rs.getBigDecimal("sellingPrice");

                // Display current information
                lblCurrentStock.setText(
                        "Current Stock: "
                        + currentStock
                        + " pieces"
                );

                lblCurrentUnitCost.setText(
                        "Current Unit Cost: "
                        + currentUnitCost
                                .setScale(2, RoundingMode.HALF_UP)
                );

                lblCurrentSellingPrice.setText(
                        "Current Selling Price: "
                        + currentSellingPrice
                                .setScale(2, RoundingMode.HALF_UP)
                );

                // Clear previous calculations
                lblNewUnitCost.setText(
                        "New Unit Cost: "
                );

                lblNewStock.setText(
                        "New Stock: "
                );

                lblPotentialProfit.setText(
                        "Potential Profit per Item: "
                );
            }
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Failed to load product information.\n"
                        + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
private void calculateRestockValues() {

    if (cmbProduct.getSelectedItem() == null) {
        return;
    }

    String productName =
            cmbProduct.getSelectedItem().toString();

    if (productName.equals("Select a product...")) {
        return;
    }

    String additionalCostText =
            txtAdditionalPurchaseCost.getText().trim();

    String piecesText =
            txtNumberOfPieces.getText().trim();

    // Do not calculate while information is incomplete
    if (additionalCostText.isEmpty()
            || piecesText.isEmpty()) {

        lblNewUnitCost.setText("New Unit Cost: ");
        lblNewStock.setText("New Stock: ");
        lblPotentialProfit.setText(
                "Potential Profit per Item: "
        );

        return;
    }

    try {

        BigDecimal additionalPurchaseCost =
                new BigDecimal(additionalCostText);

        int numberOfPieces =
                Integer.parseInt(piecesText);

        // Do not calculate invalid values
        if (additionalPurchaseCost.compareTo(
                BigDecimal.ZERO) <= 0
                || numberOfPieces <= 0) {

            lblNewUnitCost.setText("New Unit Cost: ");
            lblNewStock.setText("New Stock: ");
            lblPotentialProfit.setText(
                    "Potential Profit per Item: "
            );

            return;
        }

        if (currentUnitCost == null
                || currentSellingPrice == null) {
            return;
        }

        // 1. Get the current total cost of existing stock
        BigDecimal currentInventoryCost =
                currentUnitCost.multiply(
                        BigDecimal.valueOf(currentStock)
                );

        // 2. Add the cost of the new stock
        BigDecimal newTotalCost =
                currentInventoryCost.add(
                        additionalPurchaseCost
                );

        // 3. Calculate the new total stock
        int newStock =
                currentStock + numberOfPieces;

        // 4. Calculate the new average unit cost
        BigDecimal newUnitCost =
                newTotalCost.divide(
                        BigDecimal.valueOf(newStock),
                        2,
                        RoundingMode.HALF_UP
                );

        // 5. SAME PROFIT LOGIC AS ADD PRODUCT
        BigDecimal potentialProfit =
                currentSellingPrice.subtract(
                        newUnitCost
                );

        // 6. Display calculated values
        lblNewUnitCost.setText(
                "New Unit Cost: "
                + newUnitCost.setScale(
                        2,
                        RoundingMode.HALF_UP
                )
        );

        lblNewStock.setText(
                "New Stock: "
                + newStock
                + " pieces"
        );

        lblPotentialProfit.setText(
                "Potential Profit per Item: "
                + potentialProfit.setScale(
                        2,
                        RoundingMode.HALF_UP
                )
        );

    } catch (NumberFormatException e) {

        // User is still typing.
        lblNewUnitCost.setText("New Unit Cost: ");
        lblNewStock.setText("New Stock: ");
        lblPotentialProfit.setText(
                "Potential Profit per Item: "
        );
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDashboard = new javax.swing.JPanel();
        lblDashboardTitle = new javax.swing.JLabel();
        lblDashboardDescription = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnRestock = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblProductInformation = new javax.swing.JLabel();
        lblCurrentStock = new javax.swing.JLabel();
        lblCurrentUnitCost = new javax.swing.JLabel();
        lblCurrentSellingPrice = new javax.swing.JLabel();
        cmbProduct = new javax.swing.JComboBox<>();
        lblProductName4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblProductInformation1 = new javax.swing.JLabel();
        txtNumberOfPieces = new javax.swing.JTextField();
        lblProductName8 = new javax.swing.JLabel();
        txtAdditionalPurchaseCost = new javax.swing.JTextField();
        lblProductName7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblProductInformation2 = new javax.swing.JLabel();
        lblNewUnitCost = new javax.swing.JLabel();
        lblNewStock = new javax.swing.JLabel();
        lblPotentialProfit = new javax.swing.JLabel();
        txtSearchProduct = new javax.swing.JTextField();
        lblProductName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Dashboard");
        setPreferredSize(new java.awt.Dimension(1570, 960));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDashboardTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle.setText("Restock Product");
        pnlDashboard.add(lblDashboardTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 6, 510, 55));

        lblDashboardDescription.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription.setText("Search for a product by name to restock.");
        pnlDashboard.add(lblDashboardDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 67, 510, -1));

        btnReset.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(0, 51, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(this::btnResetActionPerformed);
        pnlDashboard.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(659, 598, 214, 51));

        btnRestock.setBackground(new java.awt.Color(0, 51, 255));
        btnRestock.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnRestock.setForeground(new java.awt.Color(255, 255, 255));
        btnRestock.setText("Restock Product");
        btnRestock.addActionListener(this::btnRestockActionPerformed);
        pnlDashboard.add(btnRestock, new org.netbeans.lib.awtextra.AbsoluteConstraints(891, 598, 214, 51));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));

        lblProductInformation.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductInformation.setForeground(new java.awt.Color(255, 255, 255));
        lblProductInformation.setText("PRODUCT INFORMATION");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblProductInformation)
                .addContainerGap(731, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblProductInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 990, 40));

        lblCurrentStock.setBackground(new java.awt.Color(255, 255, 255));
        lblCurrentStock.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblCurrentStock.setForeground(new java.awt.Color(0, 51, 255));
        lblCurrentStock.setText("Current Stock: ");
        jPanel3.add(lblCurrentStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 300, -1));

        lblCurrentUnitCost.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblCurrentUnitCost.setForeground(new java.awt.Color(0, 51, 255));
        lblCurrentUnitCost.setText("Current Unit Cost: ");
        jPanel3.add(lblCurrentUnitCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 320, -1));

        lblCurrentSellingPrice.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblCurrentSellingPrice.setForeground(new java.awt.Color(0, 51, 255));
        lblCurrentSellingPrice.setText("Current Selling Price: ");
        jPanel3.add(lblCurrentSellingPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 330, -1));

        cmbProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        cmbProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a product..." }));
        cmbProduct.addActionListener(this::cmbProductActionPerformed);
        jPanel3.add(cmbProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 820, 40));

        lblProductName4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductName4.setForeground(new java.awt.Color(0, 51, 255));
        lblProductName4.setText("Product Name");
        jPanel3.add(lblProductName4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 140, -1));

        pnlDashboard.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 108, 1035, 166));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(0, 102, 255));

        lblProductInformation1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductInformation1.setForeground(new java.awt.Color(255, 255, 255));
        lblProductInformation1.setText("RESTOCK INFORMATION");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblProductInformation1)
                .addContainerGap(737, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lblProductInformation1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 990, 40));

        txtNumberOfPieces.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtNumberOfPieces.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumberOfPiecesKeyReleased(evt);
            }
        });
        jPanel5.add(txtNumberOfPieces, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 240, 40));

        lblProductName8.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductName8.setForeground(new java.awt.Color(0, 51, 255));
        lblProductName8.setText("Additional Purchase Cost: ");
        jPanel5.add(lblProductName8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 250, -1));

        txtAdditionalPurchaseCost.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtAdditionalPurchaseCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAdditionalPurchaseCostKeyReleased(evt);
            }
        });
        jPanel5.add(txtAdditionalPurchaseCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 240, 40));

        lblProductName7.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductName7.setForeground(new java.awt.Color(0, 51, 255));
        lblProductName7.setText("Number of Pieces: ");
        jPanel5.add(lblProductName7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, -1, -1));

        pnlDashboard.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 292, 1035, 118));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(0, 51, 255));

        lblProductInformation2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductInformation2.setForeground(new java.awt.Color(255, 255, 255));
        lblProductInformation2.setText("UPDATE INFORMATION");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblProductInformation2)
                .addContainerGap(744, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(lblProductInformation2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 990, 40));

        lblNewUnitCost.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblNewUnitCost.setForeground(new java.awt.Color(0, 51, 255));
        lblNewUnitCost.setText("New Unit Cost:");
        jPanel7.add(lblNewUnitCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 290, -1));

        lblNewStock.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblNewStock.setForeground(new java.awt.Color(0, 51, 255));
        lblNewStock.setText("New Stock: ");
        jPanel7.add(lblNewStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 290, -1));

        lblPotentialProfit.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblPotentialProfit.setForeground(new java.awt.Color(0, 51, 255));
        lblPotentialProfit.setText("Potential Profit per Item: ");
        jPanel7.add(lblPotentialProfit, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 380, -1));

        pnlDashboard.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 428, 1035, 122));

        txtSearchProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtSearchProduct.setText("Type a product name...");
        txtSearchProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchProductKeyReleased(evt);
            }
        });
        pnlDashboard.add(txtSearchProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 310, 40));

        lblProductName.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductName.setForeground(new java.awt.Color(0, 51, 255));
        lblProductName.setText("Search Product: ");
        pnlDashboard.add(lblProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, 170, -1));

        getContentPane().add(pnlDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductActionPerformed

        if (updatingProductDropdown) {
            return;
        }
        
    Object selected = cmbProduct.getSelectedItem();

    if (selected == null
            || selected.toString().equals("Select a product")) {
        return;
    }
        loadProductInformation();
        
    }//GEN-LAST:event_cmbProductActionPerformed

    private void txtAdditionalPurchaseCostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdditionalPurchaseCostKeyReleased
        calculateRestockValues();
    }//GEN-LAST:event_txtAdditionalPurchaseCostKeyReleased

    private void txtNumberOfPiecesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumberOfPiecesKeyReleased
        calculateRestockValues();
    }//GEN-LAST:event_txtNumberOfPiecesKeyReleased

    private void btnRestockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockActionPerformed

    // Check if a product is selected
    if (cmbProduct.getSelectedItem() == null
            || cmbProduct.getSelectedItem()
                    .toString()
                    .equals("Select a product...")) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a product.",
                "Product Required",
                JOptionPane.WARNING_MESSAGE
        );
        
        if(cmbProduct.equals("Select a product...")){
            lblCurrentStock.setText("");
            lblCurrentUnitCost.setText("");
            lblCurrentSellingPrice.setText("");
            txtAdditionalPurchaseCost.setText("");
            txtNumberOfPieces.setText("");
            lblNewUnitCost.setText("");
            lblNewStock.setText("");
            lblPotentialProfit.setText("");
        }


        return;
    }

    // Get additional purchase cost
    String additionalCostText =
            txtAdditionalPurchaseCost.getText().trim();

    if (additionalCostText.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the additional purchase cost.",
                "Purchase Cost Required",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    // Get number of additional pieces
    String piecesText =
            txtNumberOfPieces.getText().trim();

    if (piecesText.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the number of pieces.",
                "Number of Pieces Required",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    // Convert input values
    BigDecimal additionalPurchaseCost;
    int numberOfPieces;

    try {

        additionalPurchaseCost =
                new BigDecimal(additionalCostText);

        numberOfPieces =
                Integer.parseInt(piecesText);

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter valid numbers.",
                "Invalid Input",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    // Validate purchase cost
    if (additionalPurchaseCost.compareTo(
            BigDecimal.ZERO) <= 0) {

        JOptionPane.showMessageDialog(
                this,
                "Additional purchase cost must be greater than 0.",
                "Invalid Purchase Cost",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    // Validate number of pieces
    if (numberOfPieces <= 0) {

        JOptionPane.showMessageDialog(
                this,
                "Number of pieces must be greater than 0.",
                "Invalid Number of Pieces",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    // Make sure product information is loaded
    if (currentUnitCost == null
            || currentSellingPrice == null) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a valid product.",
                "Product Error",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    // Calculate current inventory cost
    BigDecimal currentInventoryCost =
            currentUnitCost.multiply(
                    BigDecimal.valueOf(currentStock)
            );

    // Add the new purchase cost
    BigDecimal newTotalCost =
            currentInventoryCost.add(
                    additionalPurchaseCost
            );

    // Calculate new total stock
    int newStock =
            currentStock + numberOfPieces;

    // Calculate new weighted-average unit cost
    BigDecimal newUnitCost =
            newTotalCost.divide(
                    BigDecimal.valueOf(newStock),
                    2,
                    RoundingMode.HALF_UP
            );

    // Calculate potential profit
    // SAME LOGIC AS ADD PRODUCT:
    // Selling Price - Unit Cost
    BigDecimal newPotentialProfit =
            currentSellingPrice.subtract(
                    newUnitCost
            );

    // Prevent negative or zero profit
    if (newPotentialProfit.compareTo(
            BigDecimal.ZERO) <= 0) {

        JOptionPane.showMessageDialog(
                this,
                "Restock cannot proceed.\n\n"
                + "The new unit cost of ₱"
                + newUnitCost.setScale(
                        2,
                        RoundingMode.HALF_UP
                )
                + " is equal to or higher than the selling price of ₱"
                + currentSellingPrice.setScale(
                        2,
                        RoundingMode.HALF_UP
                )
                + ".\n\n"
                + "The selling price must be higher than the unit cost.",
                "Invalid Restock",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    // Get selected product name
    String productName =
            cmbProduct.getSelectedItem().toString();

    // Update product information
    String sql =
            "UPDATE tbl_products SET "
            + "stock = ?, "
            + "unitCost = ?, "
            + "potentialProfit = ? "
            + "WHERE productName = ?";

    try (Connection conn = DBConnection.connect();
         PreparedStatement pst =
                 conn.prepareStatement(sql)) {

        pst.setInt(1, newStock);
        pst.setBigDecimal(2, newUnitCost);
        pst.setBigDecimal(3, newPotentialProfit);
        pst.setString(4, productName);

        int rowsUpdated =
                pst.executeUpdate();

        if (rowsUpdated > 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Product restocked successfully!",
                    "Restock Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Product was not found.",
                    "Restock Failed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Failed to restock product.\n"
                + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );

        return;
    }

    // Clear input fields
    cmbProduct.setSelectedIndex(0);
    txtAdditionalPurchaseCost.setText("");
    txtNumberOfPieces.setText("");

    // Clear calculated values
    lblNewUnitCost.setText(
            "New Unit Cost: "
    );

    lblNewStock.setText(
            "New Stock: "
    );

    lblPotentialProfit.setText(
            "Potential Profit per Item: "
    );

    // Reload current product information
    loadProductInformation();
    }//GEN-LAST:event_btnRestockActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        cmbProduct.setSelectedIndex(0);
        txtAdditionalPurchaseCost.setText("");
        txtNumberOfPieces.setText("");


        lblNewUnitCost.setText(
                "New Unit Cost: "
        );

        lblNewStock.setText(
                "New Stock: "
        );

        lblPotentialProfit.setText(
                "Potential Profit per Item: "
        );
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtSearchProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchProductKeyReleased

        String searchText =
                txtSearchProduct.getText().trim().toLowerCase();

        updatingProductDropdown = true;

        try {
            cmbProduct.removeAllItems();

            for (String productName : allProductNames) {

                if (productName.toLowerCase().contains(searchText)) {
                    cmbProduct.addItem(productName);
                }
            }

        } finally {
            updatingProductDropdown = false;
        }

        if (cmbProduct.getItemCount() > 0) {
            cmbProduct.setSelectedIndex(0);
            loadProductInformation();
        } else {
            // Clear details if no product matches
            lblCurrentStock.setText("Current Stock: -");
            lblCurrentUnitCost.setText("Current Unit Cost: -");
            lblCurrentSellingPrice.setText("Current Selling Price: -");

            currentStock = 0;
            currentUnitCost = BigDecimal.ZERO;
            currentSellingPrice = BigDecimal.ZERO;
        }
    }//GEN-LAST:event_txtSearchProductKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnRestock;
    private javax.swing.JComboBox<String> cmbProduct;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblCurrentSellingPrice;
    private javax.swing.JLabel lblCurrentStock;
    private javax.swing.JLabel lblCurrentUnitCost;
    private javax.swing.JLabel lblDashboardDescription;
    private javax.swing.JLabel lblDashboardTitle;
    private javax.swing.JLabel lblNewStock;
    private javax.swing.JLabel lblNewUnitCost;
    private javax.swing.JLabel lblPotentialProfit;
    private javax.swing.JLabel lblProductInformation;
    private javax.swing.JLabel lblProductInformation1;
    private javax.swing.JLabel lblProductInformation2;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblProductName4;
    private javax.swing.JLabel lblProductName7;
    private javax.swing.JLabel lblProductName8;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JTextField txtAdditionalPurchaseCost;
    private javax.swing.JTextField txtNumberOfPieces;
    private javax.swing.JTextField txtSearchProduct;
    // End of variables declaration//GEN-END:variables
}
