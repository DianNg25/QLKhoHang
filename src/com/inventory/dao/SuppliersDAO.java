/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;

import com.inventory.entity.Suppliers;
import com.inventory.utils.XJdbc;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Nguyen
 */
public class SuppliersDAO extends InvenDAO<Suppliers, String> {
     public void insert(Suppliers model) {
        String sql = "INSERT INTO Suppliers (SupplierID, SupplierName, Address, Phone, Email) VALUES (?, ?, ?, ?, ?)";
        XJdbc.update(sql, 
                model.getSupplierID(), 
                model.getSupplierName(), 
                model.getAddress(), 
                model.getPhone(), 
                model.getEmail()); 
            
    }
     
      public void update(Suppliers model) {
        String sql = "UPDATE Suppliers SET  SupplierName=?, Address=?, Phone=?, Email=?, WHERE SupplierID=?";
        XJdbc.update(sql,            
                model.getSupplierName(), 
                model.getAddress(), 
                model.getPhone(),
                model.getEmail(),
                model.getSupplierID());
    }
      public void delete(String SupplierID) {
        String sql = "DELETE FROM Suppliers WHERE SupplierID=?";
        XJdbc.update(sql, SupplierID);
    }
       public List<Suppliers> selectAll() {
        String sql = "SELECT * FROM Suppliers";
        return this.selectBySql(sql);
    }
       
       public Suppliers selectById(String SupplierID) {
        String sql = "SELECT * FROM Suppliers WHERE SupplierID=?";
        List<Suppliers> list = this.selectBySql(sql, SupplierID);
        return list.size() > 0 ? list.get(0) : null;
    }
       
        protected List<Suppliers> selectBySql(String sql, Object... args) {
        List<Suppliers> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    Suppliers entity = new Suppliers();
                    entity.setSupplierID(rs.getString("SupplierID"));
                    entity.setSupplierName(rs.getString("SupplierName"));
                    entity.setAddress(rs.getString("Address"));
                    entity.setPhone(rs.getInt("Phone")); 
                    entity.setEmail(rs.getString("Email"));      
                   
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
}
