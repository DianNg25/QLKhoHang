/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.swing;

import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author ADMIN
 */
public class FontUtil {
     public static org.apache.poi.ss.usermodel.Font createPoiFont(Workbook workbook) {
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        return font;
    }

    public static java.awt.Font createAwtFont() {
        return new java.awt.Font("Arial", java.awt.Font.PLAIN, 12);
    }
}
