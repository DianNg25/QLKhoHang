/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.entity;

/**
 *
 * @author ADMIN
 */
public class ProductsTable {

    private String productID;
    private String productName;
    private String color;
    private String weight;
    private int quantity;
    private Double price;
    private String status;
    private String supplierID;  // Nếu cần lưu SupplierID

    // Constructor không tham số
    public ProductsTable() {
    }

    // Constructor với tất cả các tham số
    public ProductsTable(String productID, String productName, String color, String weight, int quantity, Double price, String status, String supplierID) {
        this.productID = productID;
        this.productName = productName;
        this.color = color;
        this.weight = weight;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.supplierID = supplierID;
    }

    // Getter và Setter cho tất cả các thuộc tính
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return "ProductsTable{"
                + "productID='" + productID + '\''
                + ", productName='" + productName + '\''
                + ", color='" + color + '\''
                + ", weight='" + weight + '\''
                + ", quantity=" + quantity
                + ", price=" + price
                + ", status='" + status + '\''
                + ", supplierID='" + supplierID + '\''
                + '}';
    }
}
