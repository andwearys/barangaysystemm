/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangaysystem;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class MySqlConnect {
    Connection conn = null;
    
    public static Connection ConnectDB() {
        try {
            // Updated to new MySQL driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Updated connection URL with required parameters for MySQL 8.0+
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/barangaysystem?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                "root", 
                ""
            );
            
            //JOptionPane.showMessageDialog(null, "Connected to Database");
            return conn;
        }
        catch(Exception e) {
            // Uncomment for debugging if needed
            //JOptionPane.showMessageDialog(null, "Database Connection Error: " + e.getMessage());
            //e.printStackTrace();
            return null;
        }
    }
}