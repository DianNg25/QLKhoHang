/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.utils;

/**
 *
 * @author ADMIN
 */
public class RevenueCalculator {
     public double getTotalRevenue() {
        double totalRevenue = 0.0;
        String sql = "{call sp_TongDoanhThu}";

        try (java.sql.Connection conn = XJdbc.getConnection();
             java.sql.CallableStatement stmt = conn.prepareCall(sql)) {
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalRevenue = rs.getDouble("DoanhThu");
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        return totalRevenue;
    }
}
