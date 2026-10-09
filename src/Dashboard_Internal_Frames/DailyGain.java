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

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

public class DailyGain extends javax.swing.JInternalFrame {

    
    private String userRole;
    
    public DailyGain(String userRole) {
        initComponents();
        
        this.userRole = userRole;

        InternalFrameUtils.setupInternalFrame(this);
        
        // Set default dates to today
        java.util.Date today = new java.util.Date();

        dateFrom.setDate(today);
        dateTo.setDate(today);

        // Set date display format
        dateFrom.setDateFormatString("MM/dd/yyyy");
        dateTo.setDateFormatString("MM/dd/yyyy");
        
        loadDailyGain();
        loadTodaySummary();
    }

    private void loadDailyGain() {

        DefaultTableModel model =
                (DefaultTableModel) tblDailyGain.getModel();

        model.setRowCount(0);

        java.util.Date fromDate = dateFrom.getDate();
        java.util.Date toDate = dateTo.getDate();

        if (fromDate == null || toDate == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select both dates.",
                    "Missing Date",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (fromDate.after(toDate)) {
            JOptionPane.showMessageDialog(
                    this,
                    "The From date cannot be later than the To date.",
                    "Invalid Date Range",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String sql =
                "SELECT gainDate, totalSales, totalCost, totalGain "
                + "FROM tbl_daily_gain "
                + "WHERE gainDate BETWEEN ? AND ? "
                + "ORDER BY gainDate DESC";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            java.sql.Date sqlFromDate =
                    new java.sql.Date(fromDate.getTime());

            java.sql.Date sqlToDate =
                    new java.sql.Date(toDate.getTime());

            pst.setDate(1, sqlFromDate);
            pst.setDate(2, sqlToDate);

            try (ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getDate("gainDate"),
                        rs.getBigDecimal("totalSales"),
                        rs.getBigDecimal("totalCost"),
                        rs.getBigDecimal("totalGain")
                    });
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load daily gain history:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void loadTodaySummary() {

    String sql =
            "SELECT totalSales, totalCost, totalGain "
            + "FROM tbl_daily_gain "
            + "WHERE gainDate = CURDATE()";

    // Default values if today's record does not exist
    java.math.BigDecimal totalSales =
            java.math.BigDecimal.ZERO;

    java.math.BigDecimal totalCost =
            java.math.BigDecimal.ZERO;

    java.math.BigDecimal totalGain =
            java.math.BigDecimal.ZERO;

    try (Connection conn = DBConnection.connect();
         PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        if (rs.next()) {
            totalSales = rs.getBigDecimal("totalSales");
            totalCost = rs.getBigDecimal("totalCost");
            totalGain = rs.getBigDecimal("totalGain");
        }

        lblTotalSales.setText(
                totalSales.setScale(
                        2,
                        java.math.RoundingMode.HALF_UP
                ).toPlainString()
        );

        lblTotalCost.setText(
                totalCost.setScale(
                        2,
                        java.math.RoundingMode.HALF_UP
                ).toPlainString()
        );

        lblTotalGain.setText(
                totalGain.setScale(
                        2,
                        java.math.RoundingMode.HALF_UP
                ).toPlainString()
        );

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Failed to load today's summary:\n"
                + e.getMessage(),
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDailyGain = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        lblTotalSales3 = new javax.swing.JLabel();
        lblTotalSales5 = new javax.swing.JLabel();
        dateFrom = new com.toedter.calendar.JDateChooser();
        dateTo = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        lblSummaryTitle = new javax.swing.JLabel();
        lblTotalCostTitle = new javax.swing.JLabel();
        lblTotalSalesTitle = new javax.swing.JLabel();
        lblTotalGainTitle = new javax.swing.JLabel();
        lblTotalCost = new javax.swing.JLabel();
        lblTotalGain = new javax.swing.JLabel();
        lblTotalSales = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Dashboard");
        setPreferredSize(new java.awt.Dimension(1570, 960));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard.setBorder(new javax.swing.border.MatteBorder(null));
        pnlDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDashboardTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle.setText("Daily Gain / History");
        pnlDashboard.add(lblDashboardTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 266, 55));

        lblDashboardDescription.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription.setText("View and manage your daily sales and gain.  ");
        pnlDashboard.add(lblDashboardDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 423, -1));

        tblDailyGain.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblDailyGain.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        tblDailyGain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Total Sales", "Total Cost", "Total Gain"
            }
        ));
        tblDailyGain.setGridColor(new java.awt.Color(0, 51, 255));
        jScrollPane1.setViewportView(tblDailyGain);

        pnlDashboard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 328, 1090, 310));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSearch.setBackground(new java.awt.Color(0, 51, 255));
        btnSearch.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.addActionListener(this::btnSearchActionPerformed);
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(876, 12, 182, 60));

        lblTotalSales3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblTotalSales3.setForeground(new java.awt.Color(0, 51, 255));
        lblTotalSales3.setText("To:");
        jPanel1.add(lblTotalSales3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, -1, -1));

        lblTotalSales5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblTotalSales5.setForeground(new java.awt.Color(0, 51, 255));
        lblTotalSales5.setText("From: ");
        jPanel1.add(lblTotalSales5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        dateFrom.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jPanel1.add(dateFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 270, 50));

        dateTo.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jPanel1.add(dateTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 280, 50));

        pnlDashboard.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 1090, 90));

        lblSummaryTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblSummaryTitle.setForeground(new java.awt.Color(0, 51, 255));
        lblSummaryTitle.setText("Today's Summary");

        lblTotalCostTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        lblTotalCostTitle.setForeground(new java.awt.Color(0, 51, 255));
        lblTotalCostTitle.setText("Total Cost");

        lblTotalSalesTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        lblTotalSalesTitle.setForeground(new java.awt.Color(0, 51, 255));
        lblTotalSalesTitle.setText("Total Sales");

        lblTotalGainTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        lblTotalGainTitle.setForeground(new java.awt.Color(0, 51, 255));
        lblTotalGainTitle.setText("Total Gain");

        lblTotalCost.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblTotalCost.setForeground(new java.awt.Color(0, 51, 255));
        lblTotalCost.setText("0.00");

        lblTotalGain.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblTotalGain.setForeground(new java.awt.Color(0, 51, 255));
        lblTotalGain.setText("0.00");

        lblTotalSales.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lblTotalSales.setForeground(new java.awt.Color(0, 51, 255));
        lblTotalSales.setText("0.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(422, 422, 422)
                        .addComponent(lblSummaryTitle)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTotalSalesTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalCostTitle)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblTotalCost)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(407, 407, 407)
                                .addComponent(lblTotalGain)
                                .addGap(59, 59, 59))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotalGainTitle)
                                .addGap(51, 51, 51))))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(lblTotalSales)
                    .addContainerGap(981, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblSummaryTitle)
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalSalesTitle)
                    .addComponent(lblTotalCostTitle)
                    .addComponent(lblTotalGainTitle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCost)
                    .addComponent(lblTotalGain))
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(58, Short.MAX_VALUE)
                    .addComponent(lblTotalSales)
                    .addContainerGap()))
        );

        pnlDashboard.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 1090, 90));

        getContentPane().add(pnlDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        loadDailyGain();
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private com.toedter.calendar.JDateChooser dateFrom;
    private com.toedter.calendar.JDateChooser dateTo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDashboardDescription;
    private javax.swing.JLabel lblDashboardTitle;
    private javax.swing.JLabel lblSummaryTitle;
    private javax.swing.JLabel lblTotalCost;
    private javax.swing.JLabel lblTotalCostTitle;
    private javax.swing.JLabel lblTotalGain;
    private javax.swing.JLabel lblTotalGainTitle;
    private javax.swing.JLabel lblTotalSales;
    private javax.swing.JLabel lblTotalSales3;
    private javax.swing.JLabel lblTotalSales5;
    private javax.swing.JLabel lblTotalSalesTitle;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JTable tblDailyGain;
    // End of variables declaration//GEN-END:variables
}
