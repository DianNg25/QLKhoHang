/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ImportForm {
    private String importFormID;
    private String supplierID; // Nếu bạn không cần cột này, bạn có thể bỏ qua
    private String supplierName; // Thêm trường này
    private Date importDate;
    private BigDecimal totalAmount;

    public String getImportFormID() {
        return importFormID;
    }

    public void setImportFormID(String importFormID) {
        this.importFormID = importFormID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() { // Getter cho SupplierName
        return supplierName;
    }

    public void setSupplierName(String supplierName) { // Setter cho SupplierName
        this.supplierName = supplierName;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
}
