/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Helia Pearl Charish
 */
public class DBConnection {
    
    public static Connection connect(){
        try {
            String url = "jdbc:MySQL://localhost:3306/bebing_store"; //inventory_s. is the name sa database // localhost sa computer gi gamit 3306- port of my sql 
            String user = "root"; //before database, undergo with security check
            String pass = "";
            
            Connection conn = DriverManager.getConnection(url,user,pass);
            
            return conn;
            
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
}
