/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangaysystem;
import java.sql.*;
import javax.swing.*;

public class SqlConnectReports {
    public static Connection ConnectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/barangaysystemreports", "root", "");
            return con;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
            return null;
        }
    }
}
