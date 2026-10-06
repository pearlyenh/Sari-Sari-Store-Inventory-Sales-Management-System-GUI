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

import sarisaristoreinventoryandsalesmanagementsytem.InternalFrameUtils;

/**
 *
 * @author Helia Pearl Charish
 */
public class RestockProduct extends javax.swing.JInternalFrame {

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

    String sql = "SELECT productID, productName "
            + "FROM tbl_products "
            + "ORDER BY productName";

    try (Connection conn = DBConnection.connect();
         PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        cmbProduct.removeAllItems();

        cmbProduct.addItem("Select a product...");

        while (rs.next()) {

            cmbProduct.addItem(
                    rs.getString("productName")
            );
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Failed to load products.\n"
                        + e.getMessage(),
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

    // Wait until both fields have values
    if (additionalCostText.isEmpty()
            || piecesText.isEmpty()) {

        return;
    }

    try {

        BigDecimal additionalPurchaseCost =
                new BigDecimal(additionalCostText);

        int numberOfPieces =
                Integer.parseInt(piecesText);

        // Validate pieces
        if (numberOfPieces <= 0) {
            return;
        }

        // Validate purchase cost
        if (additionalPurchaseCost.compareTo(
                BigDecimal.ZERO) <= 0) {

            return;
        }

        // Make sure product information exists
        if (currentUnitCost == null
                || currentSellingPrice == null) {

            return;
        }

        /*
         * STEP 1
         * Calculate the current inventory cost
         */

        BigDecimal currentInventoryCost =
                currentUnitCost.multiply(
                        BigDecimal.valueOf(currentStock)
                );


        /*
         * STEP 2
         * Add the new purchase cost
         */

        BigDecimal newTotalCost =
                currentInventoryCost.add(
                        additionalPurchaseCost
                );


        /*
         * STEP 3
         * Calculate new stock
         */

        int newStock =
                currentStock + numberOfPieces;


        /*
         * STEP 4
         * Calculate weighted average unit cost
         */

        BigDecimal newUnitCost =
                newTotalCost.divide(
                        BigDecimal.valueOf(newStock),
                        2,
                        RoundingMode.HALF_UP
                );


        /*
         * STEP 5
         * Calculate potential profit per item
         */

        BigDecimal potentialProfit =
                currentSellingPrice.subtract(
                        newUnitCost
                );


        /*
         * STEP 6
         * Display results
         */

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
        // Do not show an error yet.
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDashboard = new javax.swing.JPanel();
        lblDashboardTitle = new javax.swing.JLabel();
        lblDashboardDescription = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblProductInformation = new javax.swing.JLabel();
        lblProductName2 = new javax.swing.JLabel();
        lblCurrentStock = new javax.swing.JLabel();
        lblCurrentUnitCost = new javax.swing.JLabel();
        lblCurrentSellingPrice = new javax.swing.JLabel();
        cmbProduct = new javax.swing.JComboBox<>();
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

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Dashboard");
        setPreferredSize(new java.awt.Dimension(1570, 960));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));

        lblDashboardTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle.setText("Restock Product");

        lblDashboardDescription.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription.setText("Enter product barcode to restock.");

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 255));
        jButton1.setText("Reset");

        jButton2.setBackground(new java.awt.Color(0, 51, 255));
        jButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Save Product");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 51, 255));
        jButton3.setText("Back");

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

        lblProductName2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductName2.setForeground(new java.awt.Color(0, 51, 255));
        lblProductName2.setText("Product Name");
        jPanel3.add(lblProductName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 140, -1));

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
                .addContainerGap(733, Short.MAX_VALUE))
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

        javax.swing.GroupLayout pnlDashboardLayout = new javax.swing.GroupLayout(pnlDashboard);
        pnlDashboard.setLayout(pnlDashboardLayout);
        pnlDashboardLayout.setHorizontalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDashboardLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 412, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65))
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDashboardTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDashboardDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDashboardLayout.setVerticalGroup(
            pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDashboardTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDashboardDescription)
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(48, 48, 48)
                .addGroup(pnlDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        getContentPane().add(pnlDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductActionPerformed
        loadProductInformation();
    }//GEN-LAST:event_cmbProductActionPerformed

    private void txtAdditionalPurchaseCostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdditionalPurchaseCostKeyReleased
        calculateRestockValues();
    }//GEN-LAST:event_txtAdditionalPurchaseCostKeyReleased

    private void txtNumberOfPiecesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumberOfPiecesKeyReleased
        calculateRestockValues();
    }//GEN-LAST:event_txtNumberOfPiecesKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbProduct;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel lblProductName2;
    private javax.swing.JLabel lblProductName7;
    private javax.swing.JLabel lblProductName8;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JTextField txtAdditionalPurchaseCost;
    private javax.swing.JTextField txtNumberOfPieces;
    // End of variables declaration//GEN-END:variables
}
