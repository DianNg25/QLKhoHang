/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.swing;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author ADMIN
 */
public class ExcelFontUtil {
       public static Font createTitleFont(Workbook workbook) {
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());
        return titleFont;
    }

    public static Font createHeaderFont(Workbook workbook) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        return headerFont;
    }

    public static Font createDataFont(Workbook workbook) {
        Font dataFont = workbook.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        return dataFont;
    }

    public static CellStyle createTitleStyle(Workbook workbook) {
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFont(createTitleFont(workbook));
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return titleStyle;
    }

    public static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(createHeaderFont(workbook));
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return headerStyle;
    }

    public static CellStyle createDataStyle(Workbook workbook) {
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setFont(createDataFont(workbook));
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setAlignment(HorizontalAlignment.LEFT);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return dataStyle;
    }
    
      // Tạo style cho ngày giờ
    public static CellStyle createDateStyle(Workbook workbook) {
        CellStyle dateStyle = workbook.createCellStyle();
        Font dateFont = workbook.createFont();
        dateFont.setFontName("Arial");
        dateStyle.setFont(dateFont);
        dateStyle.setAlignment(HorizontalAlignment.LEFT);
         dateFont.setColor(IndexedColors.RED.getIndex()); // Đặt màu đỏ cho font
        return dateStyle;
    }
}
