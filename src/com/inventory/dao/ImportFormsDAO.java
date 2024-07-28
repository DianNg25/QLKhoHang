package com.inventory.dao;

import com.inventory.entity.ImportForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ImportFormsDAO {

    private Connection conn;

    public ImportFormsDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(ImportForm importForm) throws SQLException {
        String sql = "INSERT INTO ImportForms (ImportFormID, SupplierID, ImportDate, TotalAmount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, importForm.getImportFormID());
            stmt.setString(2, importForm.getSupplierID());
            stmt.setDate(3, new java.sql.Date(importForm.getImportDate().getTime())); // Chuyển đổi java.util.Date sang java.sql.Date
            stmt.setBigDecimal(4, importForm.getTotalAmount()); // Sử dụng BigDecimal cho giá trị tiền
            stmt.executeUpdate();
        }
    }
  
  
  
  


}
