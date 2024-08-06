/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dao;

import com.inventory.utils.XJdbc;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class CustomersDAO {

    // Xóa khách hàng dựa vào ID
    public boolean deleteCustomer(String customerId) {
        java.sql.Connection connection = null;
        java.sql.PreparedStatement statement = null;

        try {
            // Kết nối đến cơ sở dữ liệu
            connection = XJdbc.getConnection();

            // Chuẩn bị câu lệnh SQL để xóa khách hàng
            String query = "DELETE FROM Customers WHERE CustomerID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, customerId);

            // Thực hiện lệnh SQL
            int rowsAffected = statement.executeUpdate();

            // Kiểm tra kết quả
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
