/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ImportForm {
   private String importFormID;
    private String supplierID;
    private java.sql.Date importDate;
    private double totalAmount;

    // Constructor
    public ImportForm(String importFormID, String supplierID, java.sql.Date importDate, double totalAmount) {
        this.importFormID = importFormID;
        this.supplierID = supplierID;
        this.importDate = importDate;
        this.totalAmount = totalAmount;
    }

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

    public java.sql.Date getImportDate() {
        return importDate;
    }

    public void setImportDate(java.sql.Date importDate) {
        this.importDate = importDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
    
}
