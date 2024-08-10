/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.swing;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class StatisticsTableExample {
    private JTable table;
    private DefaultTableModel model;

    public StatisticsTableExample() {
        // Tạo bảng và mô hình bảng cơ bản
        JFrame frame = new JFrame("Thống kê");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Tạo mô hình bảng và bảng
        String[] columns = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Doanh thu"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        // Tạo thanh cuộn cho bảng
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Tạo các nút và thêm chúng vào khung
        JPanel buttonPanel = new JPanel();
        JButton btnDoanhThu = new JButton("Doanh thu");
        JButton btnHangTonKho = new JButton("Hàng tồn kho");
        JButton btnXuatKho = new JButton("Xuất kho");
        JButton btnNhapKho = new JButton("Nhập kho");

        // Thêm hành động cho các nút
        btnDoanhThu.addActionListener(e -> updateTable("Doanh thu"));
        btnHangTonKho.addActionListener(e -> updateTable("Hàng tồn kho"));
        btnXuatKho.addActionListener(e -> updateTable("Xuất kho"));
        btnNhapKho.addActionListener(e -> updateTable("Nhập kho"));

        buttonPanel.add(btnDoanhThu);
        buttonPanel.add(btnHangTonKho);
        buttonPanel.add(btnXuatKho);
        buttonPanel.add(btnNhapKho);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Hiển thị khung
        frame.setVisible(true);
    }

    public void updateTable(String type) {
        // Dữ liệu mẫu để hiển thị trong bảng
        Object[][] data = {
            {"P001", "Sản phẩm A", 10, 100},
            {"P002", "Sản phẩm B", 5, 50},
            {"P003", "Sản phẩm C", 8, 80},
            {"P004", "Sản phẩm D", 20, 200}
        };

        // Xóa dữ liệu cũ trong mô hình bảng
        model.setRowCount(0);

        // Lọc và thêm dữ liệu mới dựa trên loại thống kê
        for (Object[] row : data) {
            boolean includeRow = false;
            switch (type) {
                case "Doanh thu":
                    includeRow = (Double) row[3] > 0; // Ví dụ điều kiện lọc doanh thu
                    break;
                case "Hàng tồn kho":
                    includeRow = (Integer) row[2] > 0; // Ví dụ điều kiện lọc hàng tồn kho
                    break;
                case "Xuất kho":
                    includeRow = (Integer) row[2] > 5; // Ví dụ điều kiện lọc xuất kho
                    break;
                case "Nhập kho":
                    includeRow = (Integer) row[2] < 10; // Ví dụ điều kiện lọc nhập kho
                    break;
            }
            if (includeRow) {
                model.addRow(row);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StatisticsTableExample::new);
    }
}
