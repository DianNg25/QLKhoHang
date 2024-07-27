/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.entity;

/**
 *
 * @author ADMIN
 */
public class EmployeesTable {
    private String EmployeeID;
    private String Username;
    private String FullName;
    private int Phone;
    private String Email;
    private byte Position = 0;
    private String Image;
    private String Password;

    public EmployeesTable() {
    }

    public EmployeesTable(String EmployeeID, String Username, String FullName, int Phone, String Email, String Image, String Password) {
        this.EmployeeID = EmployeeID;
        this.Username = Username;
        this.FullName = FullName;
        this.Phone = Phone;
        this.Email = Email;
        this.Image = Image;
        this.Password = Password;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public String getUsername() {
        return Username;
    }

    public String getFullName() {
        return FullName;
    }

    public int getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public byte getPosition() {
        return Position;
    }

    public String getImage() {
        return Image;
    }

    public String getPassword() {
        return Password;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPosition(byte Position) {
        this.Position = Position;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    
    
}
