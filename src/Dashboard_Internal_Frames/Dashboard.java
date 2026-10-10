/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Dashboard_Internal_Frames;

import sarisaristoreinventoryandsalesmanagementsytem.InternalFrameUtils;

import Database.DBConnection;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Dashboard extends javax.swing.JInternalFrame {

    private String userRole;
    
    public Dashboard(String userRole) {
        initComponents();
        
        this.userRole = userRole;

        InternalFrameUtils.setupInternalFrame(this);
        
        loadDashboardSummary();
    }
    
    private void loadDashboardSummary() {

    String sql =
            "SELECT "
            + "COUNT(*) AS totalProducts, "
            + "COALESCE(SUM(CASE "
            + "WHEN stock <= lowStockReminder THEN 1 "
            + "ELSE 0 END), 0) AS lowStock "
            + "FROM tbl_products "
            + "WHERE status = 'Active'";

    try (Connection conn = DBConnection.connect();
         PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        if (rs.next()) {

            int totalProducts = rs.getInt("totalProducts");
            int lowStock = rs.getInt("lowStock");

            lblTotalProduct.setText(String.valueOf(totalProducts));
            lblLowStock.setText(String.valueOf(lowStock));
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Failed to load dashboard summary: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDashboard = new javax.swing.JPanel();
        lblDashboardTitle = new javax.swing.JLabel();
        lblDashboardDescription = new javax.swing.JLabel();
        lblDashboardTitle2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblLowStock = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblTotalProduct = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        pnlDashboard1 = new javax.swing.JPanel();
        lblDashboardTitle1 = new javax.swing.JLabel();
        lblDashboardDescription1 = new javax.swing.JLabel();
        btnDashboardAddProduct1 = new javax.swing.JButton();
        btnDashboardRestock1 = new javax.swing.JButton();
        btnDashboardDailyGain1 = new javax.swing.JButton();
        btnDashboardPuhunan1 = new javax.swing.JButton();
        btnDashboardInventory1 = new javax.swing.JButton();
        btnDashboardCashier1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Dashboard");
        setPreferredSize(new java.awt.Dimension(1570, 960));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard.setForeground(new java.awt.Color(0, 51, 255));
        pnlDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDashboardTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle.setText("Dashboard");
        pnlDashboard.add(lblDashboardTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 266, 40));

        lblDashboardDescription.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription.setText("Manage your store efficiently.");
        pnlDashboard.add(lblDashboardDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        lblDashboardTitle2.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle2.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle2.setText("\"Small Store, Big Dreams\"");
        pnlDashboard.add(lblDashboardTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 317, 55));

        jPanel4.setBackground(new java.awt.Color(0, 102, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(360, 400));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\low-download-speed-black-28612_64 (1).png")); // NOI18N
        jLabel4.setText("LOW STOCK");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblLowStock.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
        lblLowStock.setForeground(new java.awt.Color(255, 255, 255));
        lblLowStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLowStock.setText("127");

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Check Inventory for details.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLowStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(292, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblLowStock, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlDashboard.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 510, 230));

        jPanel5.setBackground(new java.awt.Color(0, 102, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(360, 400));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\gift-box-5792_64 (2).png")); // NOI18N
        jLabel5.setText("TOTAL PRODUCT");
        jLabel5.setToolTipText("");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setVerifyInputWhenFocusTarget(false);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblTotalProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
        lblTotalProduct.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalProduct.setText("127");

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Active Products");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(lblTotalProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pnlDashboard.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 510, 230));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setForeground(new java.awt.Color(0, 51, 255));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel2.setText("Verify product details, cart quantities, totals, cash, and change.");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("IMPORTANT REMINDERS:");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel6.setText("Search Inventory before adding a product to avoid duplicates.");

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel7.setText("The system cannot verify physical cash or detect incorrect change.");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel8.setText("Check low-stock products regularly and use Restock Product for additional stock.");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel9.setText("Record utang in the physical notebook.");

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel10.setText("Check product expiration dates manually.");

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel11.setText("Search Inventory before adding a product to avoid duplicates.");

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel12.setText("Damaged/lost stock adjustments, supplier records, customer utang histories, receipts, and individual transaction histories are not supported.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1537, 1537, 1537))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlDashboard.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 1070, 340));

        getContentPane().add(pnlDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 710));

        jInternalFrame1.setBackground(new java.awt.Color(255, 255, 255));
        jInternalFrame1.setTitle("Dashboard");
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(1570, 960));
        jInternalFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDashboard1.setBackground(new java.awt.Color(255, 255, 255));

        lblDashboardTitle1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle1.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle1.setText("Dashboard");

        lblDashboardDescription1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription1.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription1.setText("Manage your store efficiently.");

        btnDashboardAddProduct1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnDashboardAddProduct1.setForeground(new java.awt.Color(0, 51, 255));
        btnDashboardAddProduct1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\add-plus-5834_64 (1).png")); // NOI18N
        btnDashboardAddProduct1.setText("Add Product");
        btnDashboardAddProduct1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDashboardAddProduct1.setIconTextGap(10);
        btnDashboardAddProduct1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnDashboardRestock1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnDashboardRestock1.setForeground(new java.awt.Color(0, 51, 255));
        btnDashboardRestock1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\add-to-basket-5854_64 (1).png")); // NOI18N
        btnDashboardRestock1.setText("Restock Product");
        btnDashboardRestock1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDashboardRestock1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDashboardRestock1.addActionListener(this::btnDashboardRestock1ActionPerformed);

        btnDashboardDailyGain1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnDashboardDailyGain1.setForeground(new java.awt.Color(0, 51, 255));
        btnDashboardDailyGain1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\medical-history-healthcare-report-green-26326_64 (1).png")); // NOI18N
        btnDashboardDailyGain1.setText("Daily Gain / History");
        btnDashboardDailyGain1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDashboardDailyGain1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDashboardDailyGain1.addActionListener(this::btnDashboardDailyGain1ActionPerformed);

        btnDashboardPuhunan1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnDashboardPuhunan1.setForeground(new java.awt.Color(0, 51, 255));
        btnDashboardPuhunan1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\return-on-investment-dollar-28163_64 (1).png")); // NOI18N
        btnDashboardPuhunan1.setText("Puhunan");
        btnDashboardPuhunan1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDashboardPuhunan1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnDashboardInventory1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnDashboardInventory1.setForeground(new java.awt.Color(0, 51, 255));
        btnDashboardInventory1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\gift-box-5792_64 (1).png")); // NOI18N
        btnDashboardInventory1.setText("Inventory");
        btnDashboardInventory1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDashboardInventory1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnDashboardCashier1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnDashboardCashier1.setForeground(new java.awt.Color(0, 51, 255));
        btnDashboardCashier1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\wallet-5880_64.png")); // NOI18N
        btnDashboardCashier1.setText("Cashier");
        btnDashboardCashier1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDashboardCashier1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnlDashboard1Layout = new javax.swing.GroupLayout(pnlDashboard1);
        pnlDashboard1.setLayout(pnlDashboard1Layout);
        pnlDashboard1Layout.setHorizontalGroup(
            pnlDashboard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboard1Layout.createSequentialGroup()
                .addGroup(pnlDashboard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDashboard1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(pnlDashboard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDashboardDescription1)
                            .addComponent(lblDashboardTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDashboard1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(pnlDashboard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDashboardPuhunan1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDashboardAddProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(155, 155, 155)
                .addGroup(pnlDashboard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDashboardRestock1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDashboardInventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addGroup(pnlDashboard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDashboardDailyGain1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDashboardCashier1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109))
        );
        pnlDashboard1Layout.setVerticalGroup(
            pnlDashboard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDashboard1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblDashboardTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDashboardDescription1)
                .addGap(77, 77, 77)
                .addGroup(pnlDashboard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDashboard1Layout.createSequentialGroup()
                        .addComponent(btnDashboardDailyGain1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDashboardCashier1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDashboard1Layout.createSequentialGroup()
                        .addGroup(pnlDashboard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDashboardRestock1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDashboardAddProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addGroup(pnlDashboard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDashboardInventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDashboardPuhunan1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64))
        );

        jInternalFrame1.getContentPane().add(pnlDashboard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardRestock1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardRestock1ActionPerformed

    }//GEN-LAST:event_btnDashboardRestock1ActionPerformed

    private void btnDashboardDailyGain1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardDailyGain1ActionPerformed

    }//GEN-LAST:event_btnDashboardDailyGain1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDashboardAddProduct1;
    private javax.swing.JButton btnDashboardCashier1;
    private javax.swing.JButton btnDashboardDailyGain1;
    private javax.swing.JButton btnDashboardInventory1;
    private javax.swing.JButton btnDashboardPuhunan1;
    private javax.swing.JButton btnDashboardRestock1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblDashboardDescription;
    private javax.swing.JLabel lblDashboardDescription1;
    private javax.swing.JLabel lblDashboardTitle;
    private javax.swing.JLabel lblDashboardTitle1;
    private javax.swing.JLabel lblDashboardTitle2;
    private javax.swing.JLabel lblLowStock;
    private javax.swing.JLabel lblTotalProduct;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlDashboard1;
    // End of variables declaration//GEN-END:variables
}
