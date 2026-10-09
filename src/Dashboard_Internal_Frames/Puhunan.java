/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Dashboard_Internal_Frames;

import sarisaristoreinventoryandsalesmanagementsytem.InternalFrameUtils;
import Database.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Puhunan extends javax.swing.JInternalFrame {

    private String userRole;
    
    public Puhunan(String userRole) {
        initComponents();
        System.out.println("Puhunan constructor called");

        this.userRole = userRole;
        
        InternalFrameUtils.setupInternalFrame(this);

        loadSummary();
        loadCategoryValues();
    }
    
    // private bc only this class needs to call it.
    private void loadSummary() {

        String sql =
                "SELECT "
                + "COALESCE(SUM(unitCost * stock), 0) AS inventoryValue, "
                + "COUNT(*) AS totalProducts, "
                + "COALESCE(SUM(stock), 0) AS totalStock "
                + "FROM tbl_products "
                + "WHERE status = 'Active'";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {

                BigDecimal inventoryValue =
                        rs.getBigDecimal("inventoryValue");

                int totalProducts =
                        rs.getInt("totalProducts");

                int totalStock =
                        rs.getInt("totalStock");

                lblTotalInventoryValue.setText(
                        "₱" + inventoryValue.setScale(2).toPlainString()
                );

                lblTotalProducts.setText(
                        String.valueOf(totalProducts)
                );

                lblTotalStock.setText(
                        String.valueOf(totalStock)
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load Puhunan summary: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void loadCategoryValues() {

        String sql =
                "SELECT category, "
                + "COALESCE(SUM(unitCost * stock), 0) AS categoryValue "
                + "FROM tbl_products "
                + "WHERE status = 'Active' "
                + "GROUP BY category "
                + "ORDER BY category ASC";

        DefaultTableModel model =
                (DefaultTableModel) tblCategoryValue.getModel();

        model.setRowCount(0);

        try (Connection conn = DBConnection.connect();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                String category = rs.getString("category");

                BigDecimal categoryValue =
                        rs.getBigDecimal("categoryValue");

                model.addRow(new Object[]{
                    category,
                    "₱" + categoryValue.setScale(2).toPlainString()
                });
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load category values: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDashboard = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblDashboardTitle5 = new javax.swing.JLabel();
        lblTotalInventoryValue = new javax.swing.JLabel();
        pnlDashboard1 = new javax.swing.JPanel();
        lblDashboardDescription1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblDashboardTitle3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblDashboardTitle6 = new javax.swing.JLabel();
        lblTotalStock = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblDashboardTitle4 = new javax.swing.JLabel();
        lblTotalProducts = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategoryValue = new javax.swing.JTable();
        lblDashboardTitle7 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Dashboard");
        setPreferredSize(new java.awt.Dimension(1570, 960));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDashboardTitle5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblDashboardTitle5.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle5.setText("Total Inventory Value");

        lblTotalInventoryValue.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        lblTotalInventoryValue.setForeground(new java.awt.Color(0, 51, 255));
        lblTotalInventoryValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalInventoryValue.setText("0.00");
        lblTotalInventoryValue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lblDashboardTitle5)
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalInventoryValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblDashboardTitle5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalInventoryValue, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pnlDashboard.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 290, 130));

        pnlDashboard1.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDashboardDescription1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription1.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription1.setText("View your current capital and inventory value.");
        pnlDashboard1.add(lblDashboardDescription1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        pnlDashboard1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 290, 130));

        lblDashboardTitle3.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle3.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle3.setText("Puhunan");
        pnlDashboard1.add(lblDashboardTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 266, 55));

        lblDashboardTitle6.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblDashboardTitle6.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle6.setText("Total Stock (Available Pieces)");

        lblTotalStock.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        lblTotalStock.setForeground(new java.awt.Color(0, 51, 255));
        lblTotalStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalStock.setText("0.00 pcs");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblDashboardTitle6)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblDashboardTitle6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalStock, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pnlDashboard1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 290, -1));

        lblDashboardTitle4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblDashboardTitle4.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle4.setText("Total Products");

        lblTotalProducts.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        lblTotalProducts.setForeground(new java.awt.Color(0, 51, 255));
        lblTotalProducts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalProducts.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(lblDashboardTitle4)
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblDashboardTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pnlDashboard1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 290, 130));

        jPanel1.setBackground(new java.awt.Color(0, 51, 204));

        tblCategoryValue.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        tblCategoryValue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Category", "Values"
            }
        ));
        jScrollPane1.setViewportView(tblCategoryValue);

        lblDashboardTitle7.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblDashboardTitle7.setForeground(new java.awt.Color(255, 255, 255));
        lblDashboardTitle7.setText("Inventory Value by Category");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblDashboardTitle7, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(lblDashboardTitle7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlDashboard1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 680, 350));

        btnRefresh.setBackground(new java.awt.Color(0, 51, 255));
        btnRefresh.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);
        pnlDashboard1.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, 170, 50));

        pnlDashboard.add(pnlDashboard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        getContentPane().add(pnlDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed

        loadSummary();
        loadCategoryValues();
    }//GEN-LAST:event_btnRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDashboardDescription1;
    private javax.swing.JLabel lblDashboardTitle3;
    private javax.swing.JLabel lblDashboardTitle4;
    private javax.swing.JLabel lblDashboardTitle5;
    private javax.swing.JLabel lblDashboardTitle6;
    private javax.swing.JLabel lblDashboardTitle7;
    private javax.swing.JLabel lblTotalInventoryValue;
    private javax.swing.JLabel lblTotalProducts;
    private javax.swing.JLabel lblTotalStock;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlDashboard1;
    private javax.swing.JTable tblCategoryValue;
    // End of variables declaration//GEN-END:variables
}
