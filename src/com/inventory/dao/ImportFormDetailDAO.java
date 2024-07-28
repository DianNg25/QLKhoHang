/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;

import com.inventory.entity.ImportFormDetail;
import com.inventory.utils.XJdbc;

/**
 *
 * @author ADMIN
 */
public class ImportFormDetailDAO {
     public void insertImportFormDetail(java.sql.Connection conn,ImportFormDetail detail) {
        String sql = "INSERT INTO ImportFormDetails (ImportFormDetailID, ImportFormID, ProductID, Quantity) VALUES (?, ?, ?, ?)";
        try {
            XJdbc.update(sql, detail.getImportFormDetailID(), detail.getImportFormID(), detail.getProductID(), detail.getQuantity());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting import form detail", e);
        }
    }
}
