/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Dashboard_Internal_Frames;

import sarisaristoreinventoryandsalesmanagementsytem.InternalFrameUtils;

/**
 *
 * @author Helia Pearl Charish
 */
public class Dashboard extends javax.swing.JInternalFrame {

    private String userRole;
    
    public Dashboard(String userRole) {
        initComponents();
        
        this.userRole = userRole;

        InternalFrameUtils.setupInternalFrame(this);
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
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        pnlDashboard.add(lblDashboardTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 266, 55));

        lblDashboardDescription.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription.setText("Manage your store efficiently.");
        pnlDashboard.add(lblDashboardDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 81, -1, -1));

        lblDashboardTitle2.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle2.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle2.setText("\"Small Store, Big Dreams\"");
        pnlDashboard.add(lblDashboardTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 317, 55));

        jPanel4.setBackground(new java.awt.Color(0, 102, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(360, 400));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("LOW STOCK");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel4)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        pnlDashboard.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 370, 340));

        jPanel5.setBackground(new java.awt.Color(0, 102, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(360, 400));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("TOTAL PRODUCT");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel5)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
        );

        pnlDashboard.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 400, 340));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setForeground(new java.awt.Color(0, 51, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("TIPS ON HOW TO USE THIS SYSTEM:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 15, 430, 30));

        pnlDashboard.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 1070, 140));

        getContentPane().add(pnlDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 680));

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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDashboardRestock1ActionPerformed

    private void btnDashboardDailyGain1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardDailyGain1ActionPerformed
        // TODO add your handling code here:
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblDashboardDescription;
    private javax.swing.JLabel lblDashboardDescription1;
    private javax.swing.JLabel lblDashboardTitle;
    private javax.swing.JLabel lblDashboardTitle1;
    private javax.swing.JLabel lblDashboardTitle2;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlDashboard1;
    // End of variables declaration//GEN-END:variables
}
