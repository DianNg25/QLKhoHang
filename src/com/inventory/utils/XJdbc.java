package com.inventory.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.logging.Logger;

public class XJdbc {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost:1433;encrypt=false;trustServerCertificate=true;databaseName=QuanLyKhoHang";
    private static String username = "sa";
    private static String password = "123";
// Nạp driver

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.out.println("Register Class ERROR: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    // Lấy kết nối
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, username, password);
    }

//    // Tạo PreparedStatement
//    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
//        Connection connection = getConnection();
//        PreparedStatement pstmt = null;
//        try {
//            if (sql.trim().startsWith("{")) {
//                pstmt = connection.prepareCall(sql);
//            } else {
//                pstmt = connection.prepareStatement(sql);
//            }
//            for (int i = 0; i < args.length; i++) {
//                pstmt.setObject(i + 1, args[i]);
//            }
//            return pstmt;
//        } catch (SQLException e) {
//            if (connection != null && !connection.isClosed()) {
//                connection.close();
//            }
//            throw e;
//        }
//    }
    // Tạo PreparedStatement
    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement pstmt = null;
        try {
            if (sql.trim().startsWith("{")) {
                pstmt = connection.prepareCall(sql);
            } else {
                pstmt = connection.prepareStatement(sql);
            }
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            return pstmt;
        } catch (SQLException e) {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            throw e;
        }
    }

    // Cập nhật dữ liệu
    public static void update(String sql, Object... args) {
        try (PreparedStatement stmt = XJdbc.getStmt(sql, args)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Truy vấn dữ liệu
//    public static ResultSet query(String sql, Object... args) {
//        try {
//            PreparedStatement stmt = XJdbc.getStmt(sql, args);
//            return stmt.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//    // Truy vấn dữ liệu
//    public static ResultSet query(String sql, Object... args) {
//        try {
//            PreparedStatement stmt = XJdbc.getStmt(sql, args);
//            return stmt.executeQuery();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error executing query: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
    private static final boolean PRINT_QUERIES = false; // Set to true to enable logging
    private static final Logger logger = Logger.getLogger(XJdbc.class.getName());

    public static ResultSet query(String sql, Object... args) {
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            ResultSet rs = stmt.executeQuery();
            logger.fine("Query executed: " + sql);
            if (PRINT_QUERIES) {
                System.out.println("Query executed: " + sql);
            }
            return rs;
        } catch (SQLException e) {
            logger.severe("Error executing query: " + sql + " - " + e.getMessage()); // Log errors
        }
        return null;
    }

    // Lấy giá trị từ truy vấn
    public static Object value(String sql, Object... args) {
        try (ResultSet rs = XJdbc.query(sql, args)) {
            if (rs.next()) {
                return rs.getObject(1); // Sử dụng chỉ số cột bắt đầu từ 1
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isSupplierIDUnique(String supplierID) {
        String sql = "SELECT 1 FROM Suppliers WHERE SupplierID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, supplierID);
            try (ResultSet rs = ps.executeQuery()) {
                return !rs.next(); // Trả về true nếu không có bản ghi (mã duy nhất)
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error checking SupplierID: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

}
