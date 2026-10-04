/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package sarisaristoreinventoryandsalesmanagementsytem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAddProduct extends javax.swing.JInternalFrame {

    /**
     * Creates new form DashboardAddProduct
     */
    public DAddProduct() {
        initComponents();
    }
    
    private void calculateProductValues() {
        
        String purchaseCostText = txtPurchaseCost.getText().trim();
        String piecesText = txtNumberOfPieces.getText().trim();
        String sellingPriceText = txtSellingPrice.getText().trim();
        
        try {
            BigDecimal purchaseCost =
                    new BigDecimal(purchaseCostText);

            BigDecimal numberOfPieces =
                    new BigDecimal(piecesText);

            BigDecimal sellingPrice =
                    new BigDecimal(sellingPriceText);
            
            if (numberOfPieces.compareTo(BigDecimal.ZERO) <= 0) {
                lblUnitCost.setText("₱ 0.00");
                lblPotentialProfit.setText("₱ 0.00");
                return;
            }

            
            BigDecimal unitCost =
                purchaseCost.divide(
                    numberOfPieces,
                    2,
                    RoundingMode.HALF_UP);
            
            BigDecimal potentialProfit =
                sellingPrice.subtract(unitCost);
            
            if (potentialProfit.compareTo(BigDecimal.ZERO) < 0) {
                lblPotentialProfit.setText("Invalid Selling Price.");
                JOptionPane.showMessageDialog(this,"The selling price must be greater than original price", "Warning",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (sellingPrice.compareTo(unitCost) <= 0) {
                lblPotentialProfit.setText("Invalid Selling Price");
                return;
            }
            
            lblUnitCost.setText("Unit Cost / Original Piece per Price: " + unitCost + " pesos");
            lblPotentialProfit.setText("Potential Profit per Item: " + potentialProfit + " pesos");

        } catch (NumberFormatException e) {

        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAddProduct = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblProductName = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        txtPurchaseCost = new javax.swing.JTextField();
        txtNumberOfPieces = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblProductInformation = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblUnitCost = new javax.swing.JLabel();
        lblPotentialProfit = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSellingPrice = new javax.swing.JTextField();
        cmbLowStockReminder = new javax.swing.JComboBox<>();
        btnReset = new javax.swing.JButton();
        btnSaveProduct = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        lblAddProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblAddProduct.setForeground(new java.awt.Color(0, 51, 255));
        lblAddProduct.setText("Add Product");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Total amount paid for the bundle/pack or individual purchase");

        lblProductName.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductName.setForeground(new java.awt.Color(0, 51, 255));
        lblProductName.setText("Product Name");

        txtProductName.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtProductName.setText("Enter Product Name ");

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\gold-philippine-peso-coin-18576_32.png")); // NOI18N
        jLabel4.setText(" Purchase Cost");

        cmbCategory.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Food & Snacks", "Drinks & Beverages", "Personal & Beauty Care", "Household Products", "School & Office Supplies", "Baby Products", "Grocery & Cooking", "Medicines", "Others" }));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setText("Category");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 255));
        jLabel6.setText("Number of Pieces (Initial Stock)");

        lblDescription.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDescription.setForeground(new java.awt.Color(102, 102, 102));
        lblDescription.setText("Enter product details to add to inventory.");

        txtPurchaseCost.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtPurchaseCost.setText("Enter total purchase cost");
        txtPurchaseCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPurchaseCostKeyReleased(evt);
            }
        });

        txtNumberOfPieces.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtNumberOfPieces.setText("Enter number of pieces");
        txtNumberOfPieces.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumberOfPiecesKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 255));
        jLabel10.setText("PURCHASE INFORMATION");

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 255));
        jLabel11.setText("CALCULATED INFORMATION");

        lblProductInformation.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductInformation.setForeground(new java.awt.Color(0, 153, 255));
        lblProductInformation.setText("PRODUCT INFORMATION");

        lblUnitCost.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblUnitCost.setForeground(new java.awt.Color(0, 51, 255));
        lblUnitCost.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\gold-philippine-peso-coin-18576_32.png")); // NOI18N
        lblUnitCost.setText(" Unit Cost / Original Price per Piece: ");

        lblPotentialProfit.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblPotentialProfit.setForeground(new java.awt.Color(0, 51, 255));
        lblPotentialProfit.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\gold-philippine-peso-coin-18576_32.png")); // NOI18N
        lblPotentialProfit.setText(" Potential Profit per Item: ");

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 255));
        jLabel15.setText("SELLING INFORMATION");

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 51, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\gold-philippine-peso-coin-18576_32.png")); // NOI18N
        jLabel16.setText(" Selling Price");

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 255));
        jLabel17.setText("Low Stock Reminder ");

        txtSellingPrice.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtSellingPrice.setText("Enter selling price per item");
        txtSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSellingPriceKeyReleased(evt);
            }
        });

        cmbLowStockReminder.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        cmbLowStockReminder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20" }));

        btnReset.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(this::btnResetActionPerformed);

        btnSaveProduct.setBackground(new java.awt.Color(0, 51, 255));
        btnSaveProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnSaveProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveProduct.setText("Save Product");
        btnSaveProduct.addActionListener(this::btnSaveProductActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnSaveProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPurchaseCost, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(cmbLowStockReminder, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(lblProductInformation))
                                            .addComponent(jLabel2)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(56, 56, 56)
                                                .addComponent(jLabel10)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel9)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(92, 92, 92)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(25, 25, 25)
                                                    .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jLabel15)
                                                    .addGap(116, 116, 116))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jLabel11)
                                                    .addGap(104, 104, 104)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addComponent(lblUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblPotentialProfit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(86, 86, 86))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumberOfPieces, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddProduct)
                    .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductName)
                    .addComponent(lblUnitCost))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(251, 251, 251))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(lblPotentialProfit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPurchaseCost, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbLowStockReminder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(txtNumberOfPieces, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(189, 189, 189))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtProductName.setText("");
        cmbCategory.setSelectedIndex(0);
        txtPurchaseCost.setText("");
        txtNumberOfPieces.setText("");
        txtSellingPrice.setText("");
        cmbLowStockReminder.setSelectedIndex(0);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSaveProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveProductActionPerformed
                               
        // Check required fields
        if (txtProductName.getText().trim().isEmpty()
                || txtPurchaseCost.getText().trim().isEmpty()
                || txtNumberOfPieces.getText().trim().isEmpty()
                || txtSellingPrice.getText().trim().isEmpty()
                || cmbLowStockReminder.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all required fields.",
                    "Incomplete Information",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            String productName = txtProductName.getText().trim();
            String category = cmbCategory.getSelectedItem().toString();

            BigDecimal purchaseCost =
                    new BigDecimal(txtPurchaseCost.getText().trim());

            int numberOfPieces =
                    Integer.parseInt(txtNumberOfPieces.getText().trim());

            BigDecimal sellingPrice =
                    new BigDecimal(txtSellingPrice.getText().trim());

            int lowStockReminder =
                    Integer.parseInt(
                            cmbLowStockReminder.getSelectedItem().toString()
                    );

            // Number of pieces must be greater than zero
            if (numberOfPieces <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Number of pieces must be greater than zero.",
                        "Invalid Quantity",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Calculate unit cost
            BigDecimal unitCost =
                    purchaseCost.divide(
                            BigDecimal.valueOf(numberOfPieces),
                            2,
                            RoundingMode.HALF_UP
                    );

            // Selling price must be higher than unit cost
            if (sellingPrice.compareTo(unitCost) <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Selling price must be higher than the unit cost of ₱"
                                + unitCost.setScale(2, RoundingMode.HALF_UP),
                        "Invalid Selling Price",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Calculate potential profit
            BigDecimal potentialProfit =
                    sellingPrice.subtract(unitCost);

            // Initial stock = number of pieces purchased
            int stock = numberOfPieces;

            String sql = "INSERT INTO tbl_products "
                    + "(productName, category, purchaseCost, numberOfPieces, "
                    + "unitCost, potentialProfit, sellingPrice, stock, lowStockReminder) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DBConnection.connect();
                 PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, productName);
                pst.setString(2, category);
                pst.setBigDecimal(3, purchaseCost);
                pst.setInt(4, numberOfPieces);
                pst.setBigDecimal(5, unitCost);
                pst.setBigDecimal(6, sellingPrice);
                pst.setBigDecimal(7, potentialProfit);
                pst.setInt(8, stock);
                pst.setInt(9, lowStockReminder);

                int rowsInserted = pst.executeUpdate();

                if (rowsInserted > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Product successfully added!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    // Reset fields
                    txtProductName.setText("");
                    cmbCategory.setSelectedIndex(0);
                    txtPurchaseCost.setText("");
                    txtNumberOfPieces.setText("");
                    txtSellingPrice.setText("");
                    cmbLowStockReminder.setSelectedIndex(0);

                    lblUnitCost.setText("Unit Cost / Original Price per Piece: ");
                    lblPotentialProfit.setText("Potential Profit per Item: ");
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers for purchase cost, number of pieces, and selling price.",
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to save product.\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnSaveProductActionPerformed

    private void txtPurchaseCostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPurchaseCostKeyReleased
        calculateProductValues();
    }//GEN-LAST:event_txtPurchaseCostKeyReleased

    private void txtNumberOfPiecesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumberOfPiecesKeyReleased
        calculateProductValues();
    }//GEN-LAST:event_txtNumberOfPiecesKeyReleased

    private void txtSellingPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSellingPriceKeyReleased
        calculateProductValues();
    }//GEN-LAST:event_txtSellingPriceKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSaveProduct;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbLowStockReminder;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblAddProduct;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblPotentialProfit;
    private javax.swing.JLabel lblProductInformation;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblUnitCost;
    private javax.swing.JTextField txtNumberOfPieces;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtPurchaseCost;
    private javax.swing.JTextField txtSellingPrice;
    // End of variables declaration//GEN-END:variables
}
