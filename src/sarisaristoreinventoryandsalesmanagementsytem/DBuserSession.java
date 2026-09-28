/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sarisaristoreinventoryandsalesmanagementsytem;

/**
 *
 * @author Helia Pearl Charish
 */
public class DBuserSession {
    private static int userId;
    private static String userName;
    private static String userRole;
    
    public static void setUser(int id, String user, String role){
        userId = id;
        userName = user;
        userRole = role;
    }
    public static int getUserId(){
        return userId;
    }
    public static String getUsername(){
        return userName;
    }
    public static String getRole(){
        return userRole;
    }
    public static void clearSection(){
        userId = 0;
        userName = null;
    }
}
