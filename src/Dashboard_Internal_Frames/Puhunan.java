/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Dashboard_Internal_Frames;

import sarisaristoreinventoryandsalesmanagementsytem.InternalFrameUtils;

public class Puhunan extends javax.swing.JInternalFrame {

    private String userRole;
    
    public Puhunan(String userRole) {
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
        pnlDashboard1 = new javax.swing.JPanel();
        lblDashboardTitle1 = new javax.swing.JLabel();
        lblDashboardDescription1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Dashboard");
        setPreferredSize(new java.awt.Dimension(1570, 960));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDashboardTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle.setText("Puhunan");
        pnlDashboard.add(lblDashboardTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 266, 55));

        lblDashboardDescription.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription.setText("View your current capital and inventory value.");
        pnlDashboard.add(lblDashboardDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        pnlDashboard1.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDashboardTitle1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle1.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle1.setText("Puhunan");
        pnlDashboard1.add(lblDashboardTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 266, 55));

        lblDashboardDescription1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription1.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription1.setText("View your current capital and inventory value.");
        pnlDashboard1.add(lblDashboardDescription1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Total Inventory Value");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 19, -1, -1));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Current Capital (Puhunan)");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 19, -1, -1));

        pnlDashboard1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 1050, 110));

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Category", "Value"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 500, 340));

        pnlDashboard1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 500, 380));

        jPanel4.setBackground(new java.awt.Color(0, 51, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 500, 340));

        pnlDashboard1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 500, 380));

        pnlDashboard.add(pnlDashboard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        getContentPane().add(pnlDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblDashboardDescription;
    private javax.swing.JLabel lblDashboardDescription1;
    private javax.swing.JLabel lblDashboardTitle;
    private javax.swing.JLabel lblDashboardTitle1;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlDashboard1;
    // End of variables declaration//GEN-END:variables
}
