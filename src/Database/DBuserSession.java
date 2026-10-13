/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

public class DBuserSession {
    private static int userID;
    private static String username;
    private static String userRole;
    
    public static void setUser(int id, String user, String role){
        userID = id;
        username = user;
        userRole = role;
    }
    public static int getUserId(){
        return userID;
    }
    public static String getUsername(){
        return username;
    }
    public static String getRole(){
        return userRole;
    }
    public static void clearSection(){
        userID = 0;
        username = null;
        userRole = null;
    }
}
