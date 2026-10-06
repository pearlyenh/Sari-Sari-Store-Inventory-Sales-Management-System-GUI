/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Dashboard_Internal_Frames;

import sarisaristoreinventoryandsalesmanagementsytem.InternalFrameUtils;
import Database.DBConnection;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddProduct extends javax.swing.JInternalFrame {
    
    private String userRole;
    
    public AddProduct(String userRole) {
        initComponents();
        
        this.userRole = userRole;
        
        InternalFrameUtils.setupInternalFrame(this);
    }
    
private void calculateProductValues() {

    String purchaseCostText =
            txtPurchaseCost.getText().trim();

    String piecesText =
            txtNumberOfPieces.getText().trim();

    String sellingPriceText =
            txtSellingPrice.getText().trim();

    // Calculate Unit Cost first
    if (!purchaseCostText.isEmpty()
            && !piecesText.isEmpty()) {

        try {

            BigDecimal purchaseCost =
                    new BigDecimal(purchaseCostText);

            int numberOfPieces =
                    Integer.parseInt(piecesText);

            if (numberOfPieces > 0) {

                BigDecimal unitCost =
                        purchaseCost.divide(
                                BigDecimal.valueOf(numberOfPieces),
                                2,
                                RoundingMode.HALF_UP
                        );

                lblUnitCost.setText(
                        "Unit Cost / Original Price per Piece: "
                                + unitCost + " pesos"
                );

                // Calculate Potential Profit only
                // when Selling Price is available
                if (!sellingPriceText.isEmpty()) {

                    BigDecimal sellingPrice =
                            new BigDecimal(sellingPriceText);

                    BigDecimal potentialProfit =
                            sellingPrice.subtract(unitCost);

                    lblPotentialProfit.setText(
                            "Potential Profit per Item: "
                                    + potentialProfit + " pesos"
                    );

                } else {

                    lblPotentialProfit.setText(
                            "Potential Profit per Item: "
                    );
                }

            } else {

                lblUnitCost.setText(
                        "Unit Cost / Original Price per Piece: "
                );

                lblPotentialProfit.setText(
                        "Potential Profit per Item: "
                );
            }

        } catch (NumberFormatException e) {

            // User is still typing
        }

    } else {

        lblUnitCost.setText(
                "Unit Cost / Original Price per Piece: "
        );

        lblPotentialProfit.setText(
                "Potential Profit per Item: "
        );
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAddProduct = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtNumberOfPieces = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPurchaseCost = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btnSaveProduct = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblProductInformation = new javax.swing.JLabel();
        lblProductName = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lblUnitCost = new javax.swing.JLabel();
        lblPotentialProfit = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cmbLowStockReminder = new javax.swing.JComboBox<>();
        txtSellingPrice = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        lblAddProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblAddProduct.setForeground(new java.awt.Color(0, 51, 255));
        lblAddProduct.setText("Add Product");

        lblDescription.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDescription.setForeground(new java.awt.Color(102, 102, 102));
        lblDescription.setText("Enter product details to add to inventory.");

        btnReset.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(this::btnResetActionPerformed);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNumberOfPieces.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtNumberOfPieces.setText("Enter number of pieces");
        txtNumberOfPieces.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumberOfPiecesKeyReleased(evt);
            }
        });
        jPanel1.add(txtNumberOfPieces, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 180, 45));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 255));
        jLabel6.setText("Number of Pieces (Initial Stock)");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 311, -1));

        txtPurchaseCost.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtPurchaseCost.setText("Enter total purchase cost");
        txtPurchaseCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPurchaseCostKeyReleased(evt);
            }
        });
        jPanel1.add(txtPurchaseCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 310, 45));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\gold-philippine-peso-coin-18576_32.png")); // NOI18N
        jLabel4.setText(" Purchase Cost");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 177, -1));

        jPanel2.setBackground(new java.awt.Color(0, 102, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("PURCHASE INFORMATION");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(120, 120, 120))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 7, 490, 44));

        btnSaveProduct.setBackground(new java.awt.Color(0, 51, 255));
        btnSaveProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnSaveProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveProduct.setText("Save Product");
        btnSaveProduct.addActionListener(this::btnSaveProductActionPerformed);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 102, 255));

        lblProductInformation.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductInformation.setForeground(new java.awt.Color(255, 255, 255));
        lblProductInformation.setText("PRODUCT INFORMATION");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addComponent(lblProductInformation)
                .addGap(133, 133, 133))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(lblProductInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 490, -1));

        lblProductName.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblProductName.setForeground(new java.awt.Color(0, 51, 255));
        lblProductName.setText("Product Name");
        jPanel3.add(lblProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 140, -1));

        txtProductName.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtProductName.setText("Enter Product Name ");
        txtProductName.addActionListener(this::txtProductNameActionPerformed);
        jPanel3.add(txtProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 340, 45));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setText("Category");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        cmbCategory.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Food & Snacks", "Drinks & Beverages", "Personal & Beauty Care", "Household Products", "School & Office Supplies", "Baby Products", "Grocery & Cooking", "Medicines", "Others" }));
        jPanel3.add(cmbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 340, 44));

        jPanel5.setBackground(new java.awt.Color(0, 102, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("CALCULATED INFORMATION");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel12)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 7, 490, 44));

        lblUnitCost.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblUnitCost.setForeground(new java.awt.Color(255, 255, 255));
        lblUnitCost.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\gold-philippine-peso-coin-18576_32.png")); // NOI18N
        lblUnitCost.setText(" Unit Cost / Original Price per Piece: ");
        jPanel5.add(lblUnitCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 500, -1));

        lblPotentialProfit.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblPotentialProfit.setForeground(new java.awt.Color(255, 255, 255));
        lblPotentialProfit.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\gold-philippine-peso-coin-18576_32.png")); // NOI18N
        lblPotentialProfit.setText(" Potential Profit per Item: ");
        jPanel5.add(lblPotentialProfit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 500, -1));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(0, 102, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("SELLING INFORMATION");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jLabel13)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 7, 490, 44));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 51, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\gold-philippine-peso-coin-18576_32.png")); // NOI18N
        jLabel16.setText(" Selling Price");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 167, -1));

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 255));
        jLabel17.setText("Low Stock Reminder ");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        cmbLowStockReminder.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        cmbLowStockReminder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20" }));
        jPanel7.add(cmbLowStockReminder, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 290, 44));

        txtSellingPrice.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        txtSellingPrice.setText("Enter selling price per item");
        txtSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSellingPriceKeyReleased(evt);
            }
        });
        jPanel7.add(txtSellingPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 290, 45));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(665, 665, 665))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnSaveProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(769, 769, 769))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblAddProduct)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 436, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(593, 593, 593))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSaveProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
        
        lblUnitCost.setText("Unit Cost / Original Price per Piece: ");
        lblPotentialProfit.setText("Potential Profit per Item: ");
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
                            "Product successfully added! \nCheck inventory to see newly added product.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    // Reset fields
                    txtProductName.setText("Enter Product Name");
                    cmbCategory.setSelectedIndex(0);
                    txtPurchaseCost.setText("Enter purchase cost");
                    txtNumberOfPieces.setText("Enter number of piece");
                    txtSellingPrice.setText("Enter selling price");
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

    private void txtProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSaveProduct;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbLowStockReminder;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
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
