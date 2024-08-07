/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.utils;

import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class RevenueCalculator {
    public double getTotalRevenue() {
        double totalRevenue = 0.0;
        String sql = "{call GetTotalRevenue}";

        try (java.sql.Connection conn = XJdbc.getConnection();
             java.sql.CallableStatement stmt = conn.prepareCall(sql)) {
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalRevenue = rs.getDouble("totalRevenue");
            }
        } catch (java.sql.SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        return totalRevenue;
    }

    public String getFormattedTotalRevenue() {
        double revenue = getTotalRevenue();
        DecimalFormat df = new DecimalFormat("#,###.00"); // Định dạng số với dấu phân cách hàng nghìn và hai chữ số thập phân
        return df.format(revenue);
    }
}
