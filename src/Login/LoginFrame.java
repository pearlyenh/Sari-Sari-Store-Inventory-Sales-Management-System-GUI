/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Login;

import Database.DBuserSession;
import Database.DBConnection;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.sql.*;
import javax.swing.JOptionPane;
import sarisaristoreinventoryandsalesmanagementsytem.MainMenuFrame;

public class LoginFrame extends javax.swing.JFrame {
    
    public LoginFrame() {
        initComponents();
        
        buttonGroup1.add(adminRbutton);
        buttonGroup1.add(cRbutton);
        
        GraphicsEnvironment ge =
            GraphicsEnvironment.getLocalGraphicsEnvironment();

        GraphicsDevice[] screens = ge.getScreenDevices();

        GraphicsDevice screen = screens[0]; // Change to screens[1] for monitor 2

        GraphicsConfiguration gc =
            screen.getDefaultConfiguration();

        Rectangle bounds = gc.getBounds();
        java.awt.Insets insets =
            java.awt.Toolkit.getDefaultToolkit().getScreenInsets(gc);

        int x = bounds.x + insets.left;
        int y = bounds.y + insets.top;

        int width = bounds.width - insets.left - insets.right;
        int height = bounds.height - insets.top - insets.bottom;
    
        setBounds(x, y, width, height);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        showpass = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        adminRbutton = new javax.swing.JRadioButton();
        cRbutton = new javax.swing.JRadioButton();
        loginButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        welcomePanel.setBackground(new java.awt.Color(153, 153, 153));
        welcomePanel.setPreferredSize(new java.awt.Dimension(1920, 1080));
        welcomePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabel1.setText("Sari-Sari Store Inventory & Sales Management System");
        welcomePanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 550, 50));

        jLabel2.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        jLabel2.setText("Welcome!");
        welcomePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 160, 50));

        jLabel3.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabel3.setText("Login As:");
        welcomePanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, 180, 50));

        jButton2.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jButton2.setText("Staff");
        welcomePanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, 120, 40));

        jButton3.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jButton3.setText("Owner");
        welcomePanel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 120, 40));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(0, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("INVENTORY & SALES MANAGEMENT");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, 56));

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("BEBING SARI-SARI STORE ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, 56));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("\"Small Store. Big Dreams\"");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 530, -1, 56));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 245, -1, 56));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 255));
        jLabel7.setText("WELCOME!");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 50, -1, 56));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel6.setText("Please log in to continue.");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 312, 56));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel4.setText("Username:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 180, 194, 56));

        txtUsername.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        txtUsername.addActionListener(this::txtUsernameActionPerformed);
        jPanel2.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 240, 446, 41));

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel13.setText("Password: ");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 300, 194, 56));

        txtPassword.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        txtPassword.addActionListener(this::txtPasswordActionPerformed);
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 360, 446, 42));

        showpass.setFont(new java.awt.Font("Comic Sans MS", 2, 18)); // NOI18N
        showpass.setText("Show Password");
        showpass.addActionListener(this::showpassActionPerformed);
        jPanel2.add(showpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 420, -1, -1));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel5.setText("Log in as:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 500, 114, 56));

        adminRbutton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        adminRbutton.setText("OWNER");
        adminRbutton.addActionListener(this::adminRbuttonActionPerformed);
        jPanel2.add(adminRbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 580, 98, -1));

        cRbutton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        cRbutton.setText("FAMILY MEMBER");
        cRbutton.addActionListener(this::cRbuttonActionPerformed);
        jPanel2.add(cRbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 580, 190, -1));

        loginButton.setBackground(new java.awt.Color(0, 102, 255));
        loginButton.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("LOG IN");
        loginButton.addActionListener(this::loginButtonActionPerformed);
        jPanel2.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 660, 568, 46));

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\Screenshot_2026-09-28_194856-removebg-preview.png")); // NOI18N
        jLabel12.setText(".");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 250, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon("C:\\Users\\Helia Pearl Charish\\Downloads\\3 (1).png")); // NOI18N
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1510, 820));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void showpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassActionPerformed
        if(showpass.isSelected()){
            txtPassword.setEchoChar((char)0); // 0 (null)
        }else{
            txtPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_showpassActionPerformed
    // TODO add your handling code here:

    private void cRbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cRbuttonActionPerformed

    }//GEN-LAST:event_cRbuttonActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed

        String uN = txtUsername.getText();
        String pW = new String(txtPassword.getPassword());

        if (uN.isEmpty() || pW.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Please enter username and password.","Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Connection conn = DBConnection.connect();
        
        try {
            String sql = "SELECT * FROM tbl_users WHERE username= ? AND user_password= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,uN);
            pst.setString(2,pW);
            
            ResultSet rs = pst.executeQuery();//where the database and opening frames happing
            
            if (rs.next()) {
                int userId = rs.getInt("userID");
                String userRole = rs.getString("user_role");//database info
                     
            if (adminRbutton.isSelected() && !userRole.equals("Owner")) {
                JOptionPane.showMessageDialog(this,"This account is not an owner account.","Access Denied",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(!adminRbutton.isSelected() && !cRbutton.isSelected()){
                JOptionPane.showMessageDialog(this,"You must select owner or family member button! ", "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (cRbutton.isSelected() && !userRole.equals("Family_Member")) {
                JOptionPane.showMessageDialog(this,"This account is not a family member account.","Access Denied",JOptionPane.ERROR_MESSAGE);
            return;
            }
            
            DBuserSession.setUser(userId, uN, userRole);

            switch (userRole) {
                case "Owner":
                    JOptionPane.showMessageDialog(this,"You are logging in as Owner.\nPlease verify your identity.","Owner Verification",JOptionPane.INFORMATION_MESSAGE);

                    Authentication adminAuth = new Authentication(uN);
                    adminAuth.setVisible(true);
                    this.dispose();
                    break;
                case "Family_Member":
                    JOptionPane.showMessageDialog(this,"You are logging in as a Family Member.","Login Successful",JOptionPane.INFORMATION_MESSAGE);

                    MainMenuFrame menu = new MainMenuFrame(userRole);
                    menu.setVisible(true);
                    this.dispose();
                    break;
                default:
                    JOptionPane.showMessageDialog(this,"Invalid username or password!","Error",JOptionPane.ERROR_MESSAGE);
                    break;
            }
            
            conn.close();
            } else {
                JOptionPane.showMessageDialog(this,"Invalid username or password. Please try again.","Warning",JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());   
    }//GEN-LAST:event_loginButtonActionPerformed
}
    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void adminRbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminRbuttonActionPerformed

    }//GEN-LAST:event_adminRbuttonActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton adminRbutton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton cRbutton;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loginButton;
    private javax.swing.JCheckBox showpass;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JPanel welcomePanel;
    // End of variables declaration//GEN-END:variables
}
