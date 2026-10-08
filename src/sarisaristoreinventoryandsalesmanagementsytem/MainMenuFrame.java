/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sarisaristoreinventoryandsalesmanagementsytem;

/**
 *
 * @author Helia Pearl Charish
 */

import Login.LoginFrame;
import Dashboard_Internal_Frames.Update;
import Dashboard_Internal_Frames.RestockProduct;
import Dashboard_Internal_Frames.Puhunan;
import Dashboard_Internal_Frames.Dashboard;
import Dashboard_Internal_Frames.DailyGain;
import Dashboard_Internal_Frames.AddProduct;
import Dashboard_Internal_Frames.Inventory;
import Dashboard_Internal_Frames.Cashier;


import javax.swing.JInternalFrame;



public class MainMenuFrame extends javax.swing.JFrame {
    
    private String userRole;
    private Inventory inventoryFrame;

    public MainMenuFrame(String userRole) {
        this.userRole = userRole; 

        initComponents();
        
        lblWelcome.setText("Welcome, " + userRole + "!");
        
        
        InternalFrameUtils.setupDesktopPane(desktopPane);
    
        //Screen Size Code
        setBounds(FrameUtils.getScreenBounds());
        
        openDashboard();
    }
    
    //disposing the internal frames
    private void showFrame(JInternalFrame frame) {
        desktopPane.removeAll();
        
        InternalFrameUtils.setupInternalFrame(frame);
        
        desktopPane.add(frame);
        frame.setBounds(0,0, desktopPane.getWidth(), desktopPane.getHeight());
        frame.setVisible(true);
        desktopPane.revalidate();
        desktopPane.repaint();
    }
    
    public void openDashboard() {
        Dashboard dashboard = new Dashboard(userRole);
        showFrame(dashboard);
    }
    
    public void openAddProduct() {
        AddProduct addProduct = new AddProduct(userRole);
        showFrame(addProduct);
    }
    public void openRestockProduct(){
        RestockProduct restockProduct = new RestockProduct(userRole);
        showFrame(restockProduct);
    }
    public void openDailyGain(){
        DailyGain dailyGain = new DailyGain(userRole);
        showFrame(dailyGain);
    }
    public void openPuhunan(){
        Puhunan puhunan = new Puhunan(userRole);
        showFrame(puhunan);
    }
    public void openInventory(){
        inventoryFrame =  new Inventory(userRole);
        showFrame(inventoryFrame);
    }
    public void openUpdateProduct(int productID){
        Update updateProduct = new Update(productID, userRole);
        showFrame(updateProduct);
    }
    public void openCashier(){
        Cashier cashier = new Cashier(userRole);
        showFrame(cashier);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSidebar = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnAddProduct = new javax.swing.JButton();
        btnRestockProduct = new javax.swing.JButton();
        btnDailyGain = new javax.swing.JButton();
        btnPuhunan = new javax.swing.JButton();
        btnInventory = new javax.swing.JButton();
        btnCashier = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        pnlHeader = new javax.swing.JPanel();
        lblStoreName = new javax.swing.JLabel();
        lblSystemName = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        desktopPane = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sari-Sari Store Management System");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlSidebar.setBackground(new java.awt.Color(0, 51, 153));

        btnDashboard.setBackground(new java.awt.Color(0, 51, 153));
        btnDashboard.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\dashboard-5481_32.png")); // NOI18N
        btnDashboard.setText("   Dashboard");
        btnDashboard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDashboard.setFocusPainted(false);
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);

        btnLogout.setBackground(new java.awt.Color(255, 51, 51));
        btnLogout.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        btnAddProduct.setBackground(new java.awt.Color(0, 51, 153));
        btnAddProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnAddProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnAddProduct.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\add-plus-5834_32 (2).png")); // NOI18N
        btnAddProduct.setText("   Add Product");
        btnAddProduct.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAddProduct.setFocusPainted(false);
        btnAddProduct.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAddProduct.addActionListener(this::btnAddProductActionPerformed);

        btnRestockProduct.setBackground(new java.awt.Color(0, 51, 153));
        btnRestockProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnRestockProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnRestockProduct.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\add-to-basket-5854_32 (2).png")); // NOI18N
        btnRestockProduct.setText("   Restock Product");
        btnRestockProduct.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRestockProduct.setFocusPainted(false);
        btnRestockProduct.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRestockProduct.addActionListener(this::btnRestockProductActionPerformed);

        btnDailyGain.setBackground(new java.awt.Color(0, 51, 153));
        btnDailyGain.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnDailyGain.setForeground(new java.awt.Color(255, 255, 255));
        btnDailyGain.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\medical-history-healthcare-report-green-26326_32 (5).png")); // NOI18N
        btnDailyGain.setText("   Daily Gain / History");
        btnDailyGain.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDailyGain.setFocusPainted(false);
        btnDailyGain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDailyGain.addActionListener(this::btnDailyGainActionPerformed);

        btnPuhunan.setBackground(new java.awt.Color(0, 51, 153));
        btnPuhunan.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnPuhunan.setForeground(new java.awt.Color(255, 255, 255));
        btnPuhunan.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\return-on-investment-dollar-28163_32 (1).png")); // NOI18N
        btnPuhunan.setText("   Puhunan");
        btnPuhunan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPuhunan.setFocusPainted(false);
        btnPuhunan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPuhunan.addActionListener(this::btnPuhunanActionPerformed);

        btnInventory.setBackground(new java.awt.Color(0, 51, 153));
        btnInventory.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnInventory.setForeground(new java.awt.Color(255, 255, 255));
        btnInventory.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\gift-box-5792_32.png")); // NOI18N
        btnInventory.setText("   Inventory");
        btnInventory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnInventory.setFocusPainted(false);
        btnInventory.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInventory.addActionListener(this::btnInventoryActionPerformed);

        btnCashier.setBackground(new java.awt.Color(0, 51, 153));
        btnCashier.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnCashier.setForeground(new java.awt.Color(255, 255, 255));
        btnCashier.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\wallet-5880_32.png")); // NOI18N
        btnCashier.setText("   Cashier");
        btnCashier.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCashier.setFocusPainted(false);
        btnCashier.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCashier.addActionListener(this::btnCashierActionPerformed);

        btnLogOut.setBackground(new java.awt.Color(255, 51, 51));
        btnLogOut.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnLogOut.setForeground(new java.awt.Color(255, 255, 255));
        btnLogOut.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\red-circle-logout-arrow-20586_32 (1).png")); // NOI18N
        btnLogOut.setText("   Logout");
        btnLogOut.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLogOut.setFocusPainted(false);
        btnLogOut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLogOut.addActionListener(this::btnLogOutActionPerformed);

        javax.swing.GroupLayout pnlSidebarLayout = new javax.swing.GroupLayout(pnlSidebar);
        pnlSidebar.setLayout(pnlSidebarLayout);
        pnlSidebarLayout.setHorizontalGroup(
            pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSidebarLayout.createSequentialGroup()
                .addGroup(pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlSidebarLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlSidebarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLogOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnInventory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPuhunan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDailyGain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(btnRestockProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(23, 23, 23))
        );
        pnlSidebarLayout.setVerticalGroup(
            pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSidebarLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRestockProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDailyGain, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPuhunan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(437, 437, 437)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(pnlSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 350, 800));

        pnlHeader.setBackground(new java.awt.Color(51, 51, 255));
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblStoreName.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblStoreName.setForeground(new java.awt.Color(255, 255, 255));
        lblStoreName.setText("SARI-SARI STORE");
        pnlHeader.add(lblStoreName, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 26, 260, -1));

        lblSystemName.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblSystemName.setForeground(new java.awt.Color(255, 255, 255));
        lblSystemName.setText("MANAGEMENT SYSTEM");
        pnlHeader.add(lblSystemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 66, 238, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\Screenshot_2026-09-28_194856-removebg-preview (1).png")); // NOI18N
        pnlHeader.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 70, -1));

        lblWelcome.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(255, 255, 255));
        lblWelcome.setText("Welcome, ");
        pnlHeader.add(lblWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 50, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\Screenshot_2026-09-28_194856-removebg-preview (1).png")); // NOI18N
        pnlHeader.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        getContentPane().add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 120));
        getContentPane().add(desktopPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 1180, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        openDashboard();
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventoryActionPerformed
        openInventory();
    }//GEN-LAST:event_btnInventoryActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        openAddProduct();
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void btnRestockProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockProductActionPerformed
        openRestockProduct();
    }//GEN-LAST:event_btnRestockProductActionPerformed

    private void btnDailyGainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDailyGainActionPerformed
        openDailyGain();
    }//GEN-LAST:event_btnDailyGainActionPerformed

    private void btnPuhunanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPuhunanActionPerformed
        openPuhunan();
    }//GEN-LAST:event_btnPuhunanActionPerformed

    private void btnCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashierActionPerformed
        openCashier();
    }//GEN-LAST:event_btnCashierActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnCashier;
    private javax.swing.JButton btnDailyGain;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnInventory;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPuhunan;
    private javax.swing.JButton btnRestockProduct;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblStoreName;
    private javax.swing.JLabel lblSystemName;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlSidebar;
    // End of variables declaration//GEN-END:variables

}
