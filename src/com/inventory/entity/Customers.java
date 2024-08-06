/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.entity;

/**
 *
 * @author ACER
 */
public class Customers {

    private String CustomerID;
    private String CustomerName;
    private String Address;
    private String Phone;

    // Constructor không tham số
    public Customers() {
    }

    // Constructor với tất cả các tham số
    public Customers(String customerID, String customerName, String address, String phone) {
        this.CustomerID = customerID;
        this.CustomerName = customerName;
        this.Address = address;
        this.Phone = phone;
    }

    // Getter và Setter cho tất cả các thuộc tính
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        this.CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        this.CustomerName = customerName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }
}
