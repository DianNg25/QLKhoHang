/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inevntory.entity;

/**
 *
 * @author ADMIN
 */
public class Employees {

    private String Name;
    private int Phone;
    private String Email;
    private String Position;
    private String Image;

    public Employees() {
    }

    public Employees(String Name, int Phone, String Email, String Position, String Image) {
        this.Name = Name;
        this.Phone = Phone;
        this.Email = Email;
        this.Position = Position;
        this.Image = Image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    

}
