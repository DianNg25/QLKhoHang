/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;

import com.inventory.entity.Products;
import com.inventory.utils.XJdbc;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Nguyen
 */
public class ProductsDAO extends InvenDAO<Products, String> {

   

    public void insert(Products product) {
        String sql = "INSERT INTO Products (ProductID, ProductName, Weight, Color, Quantity, Price, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            XJdbc.update(sql, product.getProductID(), product.getProductName(), product.getWeight(), product.getColor(),
                    product.getQuantity(), product.getPrice(), product.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting product", e);
        }
    }

    // Phương thức update sử dụng XJdbc
      public void insertOrUpdateProduct(java.sql.Connection conn, Products product) throws SQLException {
        String sql = "IF EXISTS (SELECT 1 FROM Products WHERE ProductID = ?) "
                   + "BEGIN "
                   + "   UPDATE Products "
                   + "   SET ProductName = ?, Weight = ?, Color = ?, Quantity = ?, Price = ?, Status = ?, SupplierID = ? "
                   + "   WHERE ProductID = ? "
                   + "END "
                   + "ELSE "
                   + "BEGIN "
                   + "   INSERT INTO Products (ProductID, ProductName, Weight, Color, Quantity, Price, Status, SupplierID) "
                   + "   VALUES (?, ?, ?, ?, ?, ?, ?, ?) "
                   + "END";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set values for both UPDATE and INSERT
            stmt.setString(1, product.getProductID()); // Check if exists

            // Update values
            stmt.setString(2, product.getProductName()); 
            stmt.setString(3, product.getWeight()); 
            stmt.setString(4, product.getColor()); 
            stmt.setInt(5, product.getQuantity()); 
            stmt.setDouble(6, product.getPrice()); 
            stmt.setString(7, product.getStatus()); 
            stmt.setString(8, product.getSupplierID()); 
            stmt.setString(9, product.getProductID()); 

            // Insert values
            stmt.setString(10, product.getProductID()); 
            stmt.setString(11, product.getProductName()); 
            stmt.setString(12, product.getWeight()); 
            stmt.setString(13, product.getColor()); 
            stmt.setInt(14, product.getQuantity()); 
            stmt.setDouble(15, product.getPrice()); 
            stmt.setString(16, product.getStatus()); 
            stmt.setString(17, product.getSupplierID()); 

            stmt.executeUpdate();
        }
    }



    // Lấy kết nối đến cơ sở dữ liệu
    private java.sql.Connection  getConnection() throws SQLException {
        // Cần phải trả về kết nối thực tế đến cơ sở dữ liệu
        return XJdbc.getConnection(); // Hoặc DataSource.getConnection() nếu bạn sử dụng DataSource
    }

    // Lấy SupplierID từ tên nhà cung cấp
    public String getSupplierIDByName(String supplierName) {
        String sql = "SELECT SupplierID FROM Suppliers WHERE SupplierName = ?";
        try (java.sql.Connection  conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, supplierName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("SupplierID");
                } else {
                    // Thêm nhà cung cấp mới nếu không có sẵn
                    return addSupplier(supplierName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting SupplierID by name", e);
        }
    }

    // Thêm nhà cung cấp mới và trả về ID
    private String addSupplier(String supplierName) {
        String sql = "INSERT INTO Suppliers (SupplierID, SupplierName) VALUES (?, ?)";
        String newSupplierID = generateNewSupplierID(); // Tạo SupplierID mới
        try (java.sql.Connection  conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newSupplierID);
            pstmt.setString(2, supplierName);
            pstmt.executeUpdate();
            return newSupplierID;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new supplier", e);
        }
    }

    // Tạo ID nhà cung cấp mới (phương pháp này cần được định nghĩa)
    private String generateNewSupplierID() {
        // Implement the logic to generate a new SupplierID
        return "NEW_SUPPLIER_ID";
    }

    public void update(Products model) {
        String sql = "UPDATE Products SET ProductName=?, SupplierID=?, Weight=?, Color=?, Quantity=?, Price=?, Status=? WHERE ProductID=?";
        XJdbc.update(sql,
                model.getProductName(),
                model.getWeight(),
                model.getColor(),
                model.getQuantity(),
                model.getPrice(),
                model.getStatus(),
                model.getProductID());
    }

    public void delete(String productID) {
        String sql = "UPDATE Products SET Status =? WHERE ProductID=?";
        XJdbc.update(sql,
                "Đã xóa",
                productID);
    }

    public List<Products> selectAll() {
        String sql = "SELECT * FROM Products";
        return this.selectBySql(sql);
    }

    public Products selectById(String ProductID) {
        String sql = "SELECT * FROM Products WHERE ProductID=?";
        List<Products> list = this.selectBySql(sql, ProductID);
        return list.size() > 0 ? list.get(0) : null;
    }

    public String getSupplierNameByID(String supplierID) {
        String sql = "SELECT SupplierName FROM Suppliers WHERE SupplierID = ?";
        try (ResultSet rs = XJdbc.query(sql, supplierID)) {
            if (rs.next()) {
                return rs.getString("SupplierName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving supplier name", e);
        }
        return null;
    }

    protected List<Products> selectBySql(String sql, Object... args) {
        List<Products> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    Products entity = new Products();
                    entity.setProductID(rs.getString("ProductID"));
                    entity.setProductName(rs.getString("ProductName"));

                    entity.setColor(rs.getString("Color"));
                    entity.setWeight(rs.getString("Weight"));
                    entity.setQuantity(rs.getInt("Quantity"));
                    entity.setPrice(rs.getDouble("price"));
                    entity.setStatus(rs.getString("Status"));

                    list.add(entity);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    public void updateStatus(String productID, String newStatus) {
        String sql = "UPDATE Products SET Status =? WHERE ProductID=?";
        XJdbc.update(sql,
                newStatus,
                productID);
    }
    
     public String getStatus(String productID) {
        String sql = "SELECT Status FROM Products WHERE ProductID = ?";
        try (java.sql.Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting status of product", e);
        }
        return null; // Trả về null nếu không tìm thấy trạng thái
    }
}
