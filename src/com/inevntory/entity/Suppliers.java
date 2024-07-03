/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inevntory.entity;

/**
 *
 * @author ADMIN
 */
public class Suppliers {

    private String SupplierName;
    private String Address;
    private int Phone;
    private String Email;

    public Suppliers(String SupplierName, String Address, int Phone, String Email) {
        this.SupplierName = SupplierName;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
    }

    public Suppliers() {
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}
