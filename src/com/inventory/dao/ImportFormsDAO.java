package com.inventory.dao;

import com.inventory.entity.ImportForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ImportFormsDAO {

  public void insertImportForm(Connection conn, ImportForm importForm) {
    String sql = "INSERT INTO ImportForms (ImportFormID, SupplierID, ImportDate, TotalAmount) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, importForm.getImportFormID());
        stmt.setString(2, importForm.getSupplierID());
        stmt.setDate(3, importForm.getImportDate());
        stmt.setDouble(4, importForm.getTotalAmount());
        stmt.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Error inserting import form", e);
    }
}


}
