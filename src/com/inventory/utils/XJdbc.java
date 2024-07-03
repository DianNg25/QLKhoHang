package com.inventory.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XJdbc {
    private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    private static String dburl="jdbc:sqlserver://localhost;database=Polypro";
    private static String dburl = "jdbc:sqlserver://localhost:1433;encrypt=false;trustServerCertificate=true;databaseName=QuanLyKhoHang";
    private static String username="sa";
    private static String password="123";
    
    /*
     * Nạp driver
     */
    static{
        try {            
            Class.forName(driver);
        } 
        catch (ClassNotFoundException ex) {
            System.out.println("Register CLass ERROR: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    /**
     * Xây dựng PreparedStatement
     * @param sql là câu lệnh SQL chứa có thể chứa tham số. Nó có thể là một lời gọi thủ tục lưu
     * @param args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql
     * @return PreparedStatement tạo được
     * @throws java.sql.SQLException lỗi sai cú pháp
     */
    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(dburl, username, password);
        }catch(Exception e){
            System.out.println(e.getMessage() + "ERROR CONNECTION");
        }
        if(connection == null)
            System.err.print("Cannot connection to the Database Server MSSQL!");
        PreparedStatement pstmt = null;
        if(sql.trim().startsWith("{")){
            pstmt = connection.prepareCall(sql);
        }
        else{
            pstmt = connection.prepareStatement(sql);
        }
        for(int i=0;i<args.length;i++){
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }
    /**
     * Thực hiện câu lệnh SQL thao tác (INSERT, UPDATE, DELETE) hoặc thủ tục lưu thao tác dữ liệu
     * @param sql là câu lệnh SQL chứa có thể chứa tham số. Nó có thể là một lời gọi thủ tục lưu
     * @param args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql     * 
     */
    public static void update(String sql, Object...args) {
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            try {
                stmt.executeUpdate();
            } 
            finally{
                stmt.getConnection().close();
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Thực hiện câu lệnh SQL truy vấn (SELECT) hoặc thủ tục lưu truy vấn dữ liệu
     * @param sql là câu lệnh SQL chứa có thể chứa tham số. Nó có thể là một lời gọi thủ tục lưu
     * @param args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql
     */    
    public static ResultSet query(String sql, Object...args) {
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            return stmt.executeQuery();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static Object value(String sql, Object...args) {
        try {
            ResultSet rs = XJdbc.query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
