/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.form;

import javax.swing.Icon;

/**
 *
 * @author WINDOWS
 */
public class Model_NvCard {

    private Icon icon;
    private String title;
    private String desctription;

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesctription() {
        return desctription;
    }

    public void setDesctription(String desctription) {
        this.desctription = desctription;
    }

    public Model_NvCard(Icon icon, String title, String desctription) {
        this.icon = icon;
        this.title = title;
        this.desctription = desctription;
    }

    public Model_NvCard() {
    }
}
