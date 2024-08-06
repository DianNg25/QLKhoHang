/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.utils;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class DatabaseUtils {
    
    
    private static Connection connection;
    private static Connection createConnection(){
        
        try {
            Class.forName(SQLServerDriver.class.getName());
            String url = "jdbc:sqlserver://localhost;database=QuanLyKhoHang;integratedSecurity=false;user=sa;password=123;encrypt=true;trustServerCertificate=true;";
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public  static  Connection getConnection(){
        try{
            if(connection == null || connection.isClosed()){
                connection = createConnection();
            }
            return connection;
            
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
  
    
}
