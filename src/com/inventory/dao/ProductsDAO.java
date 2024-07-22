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
import java.sql.SQLException;

/**
 *
 * @author Nguyen
 */
public class ProductsDAO extends InvenDAO<Products, String> {

    public void insert(Products model) {
        String sql = "INSERT INTO Products (ProductID, ProductName, SupplierID, Weight, Color, Quantity, Price, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getProductID(),
                model.getProductName(),
                model.getSupplierID(),
                model.getWeight(),
                model.getColor(),
                model.getQuantity(),
                model.getPrice(),
                model.getStatus());
    }

    public void update(Products model) {
        String sql = "UPDATE Products SET ProductName=?, SupplierID=?, Weight=?, Color=?, Quantity=?, Price=?, Status=? WHERE ProductID=?";
        XJdbc.update(sql,
                model.getProductName(),
                model.getSupplierID(),
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
                    entity.setSupplierID(rs.getString("SupplierID"));
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
}
