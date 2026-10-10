/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Dashboard_Internal_Frames;

import sarisaristoreinventoryandsalesmanagementsytem.InternalFrameUtils;
import sarisaristoreinventoryandsalesmanagementsytem.MainMenuFrame;


import Database.DBConnection;

import java.math.BigDecimal;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Inventory extends javax.swing.JInternalFrame {
    
    private String userRole;
    private TableRowSorter<DefaultTableModel> sorter;

    public Inventory(String userRole) {
        initComponents();

        this.userRole = userRole;

        sorter = new TableRowSorter<>(
            (DefaultTableModel) tblInventory.getModel()
        );

        tblInventory.setRowSorter(sorter);

        setupStockStatusColors();

        loadProducts();
    }

    private void loadProducts() {
        String sql =
                "SELECT * FROM tbl_products "
                + "WHERE status = 'Active'";

        try (Connection conn = DBConnection.connect();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            DefaultTableModel model =
                    (DefaultTableModel) tblInventory.getModel();

            model.setRowCount(0);

            while (rs.next()) {

                int stock = rs.getInt("stock");
                int lowStockReminder = rs.getInt("lowStockReminder");

                String stockStatus;

                if (stock == 0) {
                    stockStatus = "NO STOCK";
                } else if (stock <= lowStockReminder) {
                    stockStatus = "LOW STOCK";
                } else if (stock <= lowStockReminder + 2) {
                    stockStatus = "ALMOST LOW";
                } else {
                    stockStatus = "MANY STOCKS";
                }

                model.addRow(new Object[]{
                    rs.getInt("productID"),
                    rs.getString("productName"),
                    rs.getString("category"),
                    rs.getBigDecimal("purchaseCost"),
                    rs.getBigDecimal("unitCost"),
                    rs.getBigDecimal("sellingPrice"),
                    rs.getBigDecimal("potentialProfit"),
                    stock,
                    lowStockReminder,
                    stockStatus
                });
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load products.\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void setupStockStatusColors() {

    tblInventory.getColumnModel()
            .getColumn(9)
            .setCellRenderer(new DefaultTableCellRenderer() {

                @Override
                public Component getTableCellRendererComponent(
                        JTable table,
                        Object value,
                        boolean isSelected,
                        boolean hasFocus,
                        int row,
                        int column) {

                    Component component =
                            super.getTableCellRendererComponent(
                                    table,
                                    value,
                                    isSelected,
                                    hasFocus,
                                    row,
                                    column
                            );

                    String status =
                            value == null
                            ? ""
                            : value.toString();

                    if (status.equals("NO STOCK")) {

                        component.setBackground(Color.GRAY);
                        component.setForeground(Color.WHITE);

                    } else if (status.equals("LOW STOCK")) {

                        component.setBackground(Color.RED);
                        component.setForeground(Color.WHITE);

                    } else if (status.equals("ALMOST LOW")) {

                        component.setBackground(Color.ORANGE);
                        component.setForeground(Color.BLACK);

                    } else if (status.equals("MANY STOCKS")) {

                        component.setBackground(Color.GREEN);
                        component.setForeground(Color.BLACK);

                    } else {

                        component.setBackground(Color.WHITE);
                        component.setForeground(Color.BLACK);
                    }

                    return component;
                }
            });
}  

    private void applyStockFilter() {
        String selectedFilter =
                cmbStockFilter.getSelectedItem().toString();

        String searchText = txtSearch.getText().trim();

        if (searchText.equalsIgnoreCase("Search product")) {
            searchText = "";
        }

        java.util.List<javax.swing.RowFilter<Object, Object>> filters =
                new java.util.ArrayList<>();

        // Filter by product name (column 1)
        if (!searchText.isEmpty()) {
            filters.add(
                javax.swing.RowFilter.regexFilter(
                    "(?i)" + java.util.regex.Pattern.quote(searchText),
                    1
                )
            );
        }

        // Filter by stock status (column 9)
        if (!selectedFilter.equals("ALL PRODUCTS")) {
            filters.add(
                javax.swing.RowFilter.regexFilter(
                    "^" + java.util.regex.Pattern.quote(selectedFilter) + "$",
                    9
                )
            );
        }

        if (filters.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(
                javax.swing.RowFilter.andFilter(filters)
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
        tblInventory = new javax.swing.JTable();
        btnAddProduct = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRestock = new javax.swing.JButton();
        pnlSearch = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbStockFilter = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Dashboard");
        setPreferredSize(new java.awt.Dimension(1570, 960));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDashboard.setBackground(new java.awt.Color(255, 255, 255));
        pnlDashboard.setBorder(new javax.swing.border.MatteBorder(null));
        pnlDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDashboardTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblDashboardTitle.setForeground(new java.awt.Color(0, 51, 255));
        lblDashboardTitle.setText("Inventory");
        pnlDashboard.add(lblDashboardTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 266, 55));

        lblDashboardDescription.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblDashboardDescription.setForeground(new java.awt.Color(102, 102, 102));
        lblDashboardDescription.setText("View and manage your product inventory.");
        pnlDashboard.add(lblDashboardDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        tblInventory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblInventory.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Product Name", "Caterory", "Purchase Cost", "Unit Cost", "Selling Price", "Potential Profit", "Stock", "Low Stock Reminder", "Stock Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInventory.setGridColor(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(tblInventory);
        if (tblInventory.getColumnModel().getColumnCount() > 0) {
            tblInventory.getColumnModel().getColumn(0).setResizable(false);
            tblInventory.getColumnModel().getColumn(1).setResizable(false);
            tblInventory.getColumnModel().getColumn(2).setResizable(false);
            tblInventory.getColumnModel().getColumn(3).setResizable(false);
            tblInventory.getColumnModel().getColumn(4).setResizable(false);
            tblInventory.getColumnModel().getColumn(5).setResizable(false);
            tblInventory.getColumnModel().getColumn(6).setResizable(false);
            tblInventory.getColumnModel().getColumn(7).setResizable(false);
            tblInventory.getColumnModel().getColumn(8).setResizable(false);
            tblInventory.getColumnModel().getColumn(9).setResizable(false);
        }

        pnlDashboard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 146, 1040, 440));

        btnAddProduct.setBackground(new java.awt.Color(0, 153, 255));
        btnAddProduct.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnAddProduct.setForeground(new java.awt.Color(255, 255, 255));
        btnAddProduct.setText("Add Product");
        btnAddProduct.addActionListener(this::btnAddProductActionPerformed);
        pnlDashboard.add(btnAddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 598, 177, 47));

        btnUpdate.setBackground(new java.awt.Color(0, 51, 255));
        btnUpdate.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);
        pnlDashboard.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 598, 177, 47));

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);
        pnlDashboard.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 598, 177, 47));

        btnRestock.setBackground(new java.awt.Color(102, 204, 0));
        btnRestock.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnRestock.setForeground(new java.awt.Color(255, 255, 255));
        btnRestock.setText("Restock");
        btnRestock.addActionListener(this::btnRestockActionPerformed);
        pnlDashboard.add(btnRestock, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 598, 177, 47));

        txtSearch.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtSearch.setText("Search product ");
        txtSearch.addActionListener(this::txtSearchActionPerformed);
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(0, 51, 255));
        btnSearch.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Search");
        btnSearch.setFocusable(false);
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(txtSearch))
                .addContainerGap())
        );

        pnlDashboard.add(pnlSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 44, -1, -1));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel1.setText("Filter Stock:");
        pnlDashboard.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 80, -1));

        cmbStockFilter.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        cmbStockFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL PRODUCTS", "NO STOCK", "LOW STOCK", "ALMOST LOW", "MANY STOCKS" }));
        cmbStockFilter.addActionListener(this::cmbStockFilterActionPerformed);
        pnlDashboard.add(cmbStockFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 102, 260, 30));

        getContentPane().add(pnlDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        MainMenuFrame mainMenu =
                (MainMenuFrame) javax.swing.SwingUtilities
                        .getWindowAncestor(this);

        if (mainMenu != null) {
            mainMenu.openAddProduct();
        }
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tblInventory.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a product to update.",
                    "No Product Selected",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int modelRow = tblInventory.convertRowIndexToModel(selectedRow);

        int productID = (int) tblInventory.getModel()
                .getValueAt(modelRow, 0);
        
        MainMenuFrame mainMenu = 
                (MainMenuFrame) javax.swing.SwingUtilities
                        .getWindowAncestor(this);
        
        mainMenu.openUpdateProduct(productID);

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRestockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockActionPerformed
        MainMenuFrame mainMenu =
            (MainMenuFrame) javax.swing.SwingUtilities
                        .getWindowAncestor(this);

        if (mainMenu != null) {
            mainMenu.openRestockProduct();
        }
    }//GEN-LAST:event_btnRestockActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tblInventory.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a product to delete.",
                "No Product Selected",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        //Get the Product ID
        int modelRow = tblInventory.convertRowIndexToModel(selectedRow);

        int productID =
                (int) tblInventory.getModel().getValueAt(modelRow, 0);
        
        int answer = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this product?",
            "Delete Product",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (answer != JOptionPane.YES_OPTION) {
            return;
        }
        
        String sql =
        "UPDATE tbl_products "
        + "SET status = 'Archived' "
        + "WHERE productID = ?";
        
        try (Connection conn = DBConnection.connect();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, productID);

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Product deleted successfully! \nThis cannot be undone.",
                    "Delete Product",
                    JOptionPane.INFORMATION_MESSAGE
                );

                loadProducts();
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                this,
                "Failed to delete product:\n"
                + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        applyStockFilter();
        
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cmbStockFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStockFilterActionPerformed
        applyStockFilter();
    }//GEN-LAST:event_cmbStockFilterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRestock;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbStockFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDashboardDescription;
    private javax.swing.JLabel lblDashboardTitle;
    private javax.swing.JPanel pnlDashboard;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JTable tblInventory;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
