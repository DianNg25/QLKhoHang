/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.entity;

/**
 *
 * @author ADMIN
 */
public class ImportFormDetail {
      private String importFormDetailID;
    private String importFormID;
    private String productID;
    private int quantity;
    private double price;

    // Constructor
    public ImportFormDetail(String importFormDetailID, String importFormID, String productID, int quantity, double price) {
        this.importFormDetailID = importFormDetailID;
        this.importFormID = importFormID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    public String getImportFormDetailID() {
        return importFormDetailID;
    }

    public void setImportFormDetailID(String importFormDetailID) {
        this.importFormDetailID = importFormDetailID;
    }

    public String getImportFormID() {
        return importFormID;
    }

    public void setImportFormID(String importFormID) {
        this.importFormID = importFormID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
}
