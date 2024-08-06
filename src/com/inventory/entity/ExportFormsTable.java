/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.entity;

import java.util.Date;

/**
 *
 * @author ACER
 */
public class ExportFormsTable {

    private String exportFormID;
    private String customerID;
    private Date exportDate;
    private double totalAmount;
    private String status;

    // Constructor không tham số
    public ExportFormsTable() {
    }

    // Getter và Setter cho exportFormID
    public String getExportFormID() {
        return exportFormID;
    }

    public void setExportFormID(String exportFormID) {
        this.exportFormID = exportFormID;
    }

    // Getter và Setter cho customerID
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    // Getter và Setter cho exportDate
    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    // Getter và Setter cho totalAmount
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Getter và Setter cho status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExportFormsTable{"
                + "exportFormID='" + exportFormID + '\''
                + ", customerID='" + customerID + '\''
                + ", exportDate=" + exportDate
                + ", totalAmount=" + totalAmount
                + ", status='" + status + '\''
                + '}';
    }
}
