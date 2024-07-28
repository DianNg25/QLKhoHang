/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.entity;

/**
 *
 * @author ADMIN
 */


public class Products {

    private String ProductID;
    private String ProductName;
    private String Color;
    private String Weight;
    private int Quantity;
    private Double Price;
    private String Status;
    private String SupplierID;  // Nếu cần lưu SupplierID

    // Constructor không tham số
    public Products() {
    }
     // Constructor với tất cả các tham số
    public Products(String productID, String productName, String color, String weight, int quantity, Double price, String status, String supplierID) {
        this.ProductID = productID;
        this.ProductName = productName;
        this.Color = color;
        this.Weight = weight;
        this.Quantity = quantity;
        this.Price = price;
        this.Status = status;
        this.SupplierID = supplierID;
    }

    // Getter và Setter cho tất cả các thuộc tính
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        this.ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        this.Color = color;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        this.Weight = weight;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        this.Price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String supplierID) {
        this.SupplierID = supplierID;
    }
}