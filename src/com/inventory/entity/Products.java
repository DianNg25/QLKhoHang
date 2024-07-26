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

    public Products() {
    }

    
    
    private String ProductID;
    private String ProductName;
    private String Color;
    private String Weight;
    private int Quantity;
    private Double price;

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Products(String ProductID, String ProductName, String Color, String Weight, int Quantity, Double price, String Status) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Color = Color;
        this.Weight = Weight;
        this.Quantity = Quantity;
        this.price = price;
        this.Status = Status;
    }
    private String Status;
   
    
}
