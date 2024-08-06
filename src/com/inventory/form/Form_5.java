package com.inventory.form;

import com.inventory.entity.ModelData;
import com.inventory.swing.chart.ModelChart;
import com.inventory.utils.XJdbc;
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Form_5 extends javax.swing.JPanel {

    public Form_5() {
        initComponents();
        chart.setTitle("Thống Kê");
        chart.addLegend("Nhập", Color.decode("#0cebeb"), Color.decode("#20e3b2"));
        chart.addLegend("Xuất", Color.decode("#7b4397"), Color.decode("#dc2430"));
        chart.addLegend("Doanh thu", Color.decode("#e65c00"), Color.decode("#F9D423"));
        chart.addLegend("Tồn kho", Color.decode("#0099F7"), Color.decode("#F11712"));
        setData();
    }

    private void setData() {
        List<ModelData> listData = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            listData.add(new ModelData(getMonthName(month), 0, 0, 0, 0)); // Khởi tạo với giá trị 0 cho tất cả các tháng
        }

        try (Connection con = XJdbc.getConnection(); CallableStatement stmt = con.prepareCall("{CALL GetMonthlyStats()}"); // Thêm các CallableStatement cho các SP khác
                 CallableStatement cstmtImported = con.prepareCall("{CALL GetTotalImportedQuantity()}"); CallableStatement cstmtExported = con.prepareCall("{CALL GetTotalExportedQuantity()}"); CallableStatement cstmtInventory = con.prepareCall("{CALL GetCurrentInventory()}"); CallableStatement cstmtRevenue = con.prepareCall("{CALL GetTotalRevenue()}")) {

            // Lấy dữ liệu từ GetMonthlyStats và cập nhật listData
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int monthNumber = rs.getInt("Month");
                    String month = getMonthName(monthNumber);
                    double imported = rs.getDouble("totalImported");
                    double exported = rs.getDouble("totalExported");
                    double revenue = rs.getDouble("totalRevenue");
                    double endingInventory = rs.getDouble("endingInventory");

                    System.out.println("Month: " + month
                            + ", Imported: " + imported
                            + ", Exported: " + exported
                            + ", Revenue: " + revenue
                            + ", Ending Inventory: " + endingInventory);

                    for (ModelData data : listData) {
                        if (data.getMonth().equals(month)) {
                            data.setImported(imported);
                            data.setExported(exported);
                            data.setRevenue(revenue);
                            data.setEndingInventory(endingInventory);
                            break;
                        }
                    }
                }
            }

            System.out.println("listData: " + listData);

            try (ResultSet rsRevenue = cstmtRevenue.executeQuery()) {
                if (rsRevenue.next()) {
                    DecimalFormat df = new DecimalFormat("#,###.##");
                    BigDecimal totalRevenueBigDecimal = rsRevenue.getBigDecimal("totalRevenue");
                    double totalRevenue = totalRevenueBigDecimal != null ? totalRevenueBigDecimal.doubleValue() : 0.0;
                    lblDoanhThu.setText(df.format(totalRevenue));
                }
            }

            try (ResultSet rsImported = cstmtImported.executeQuery()) {
                if (rsImported.next()) {
                    int totalImported = rsImported.getInt("totalImportedQuantity");
                    lblNhap.setText(String.valueOf(totalImported));
                }
            }

            try (ResultSet rsExported = cstmtExported.executeQuery()) {
                if (rsExported.next()) {
                    int totalExported = rsExported.getInt("totalExportedQuantity");
                    lblXuat.setText(String.valueOf(totalExported));
                }
            }

            int totalInventory = 0;
            try (ResultSet rsInventory = cstmtInventory.executeQuery()) {
                while (rsInventory.next()) {
                    totalInventory += rsInventory.getInt("currentQuantity");
                }
                lblTonKho.setText(String.valueOf(totalInventory));
            }

            // Add data to chart (giữ nguyên)
            chart.clear();
            for (ModelData data : listData) {
                chart.addData(new ModelChart(data.getMonth(),
                        new double[]{data.getExported(), data.getImported(),
                            data.getRevenue(), data.getEndingInventory()}));
            }
            chart.start();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error executing query: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getMonthName(int monthNumber) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if (monthNumber >= 1 && monthNumber <= 12) {
            return monthNames[monthNumber - 1];
        } else {
            return "Invalid Month";
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        textField3 = new com.inventory.swing.TextField();
        jLabel4 = new javax.swing.JLabel();
        textField1 = new com.inventory.swing.TextField();
        button1 = new com.inventory.swing.Button();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblNhap = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblXuat = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblDoanhThu = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblTonKho = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panelShadow1 = new com.inventory.swing.chart.PanelShadow();
        chart = new com.inventory.swing.chart.CurveLineChart();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(910, 607));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setText("Từ ngày");

        textField3.setBackground(new java.awt.Color(72, 142, 174));
        textField3.setForeground(new java.awt.Color(255, 255, 255));
        textField3.setCaretColor(new java.awt.Color(255, 255, 255));
        textField3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setText("Đến ngày");

        textField1.setBackground(new java.awt.Color(72, 142, 174));
        textField1.setForeground(new java.awt.Color(255, 255, 255));
        textField1.setCaretColor(new java.awt.Color(255, 255, 255));
        textField1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        button1.setBackground(new java.awt.Color(102, 102, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Thống kê");
        button1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setText("Tổng số hàng nhập");

        lblNhap.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblNhap.setForeground(new java.awt.Color(0, 204, 51));
        lblNhap.setText("jLabel1");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setText("Tổng số hàng xuất");

        lblXuat.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblXuat.setForeground(new java.awt.Color(0, 204, 51));
        lblXuat.setText("jLabel1");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel11.setText("Doanh thu");

        lblDoanhThu.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDoanhThu.setForeground(new java.awt.Color(0, 204, 51));
        lblDoanhThu.setText("jLabel1");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel13.setText("Tổng số hàng tồn kho");

        lblTonKho.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTonKho.setForeground(new java.awt.Color(0, 204, 51));
        lblTonKho.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNhap)
                    .addComponent(lblXuat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDoanhThu)
                    .addComponent(lblTonKho))
                .addGap(140, 140, 140))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lblDoanhThu))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lblTonKho)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblNhap))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblXuat))))
                .addGap(10, 10, 10))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(860, 383));
        jPanel3.setLayout(new java.awt.BorderLayout());

        panelShadow1.setBackground(new java.awt.Color(34, 59, 69));
        panelShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow1.setColorGradient(new java.awt.Color(17, 38, 47));

        chart.setForeground(new java.awt.Color(255, 255, 255));
        chart.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
        );

        jPanel3.add(panelShadow1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button button1;
    private com.inventory.swing.chart.CurveLineChart chart;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblNhap;
    private javax.swing.JLabel lblTonKho;
    private javax.swing.JLabel lblXuat;
    private com.inventory.swing.chart.PanelShadow panelShadow1;
    private com.inventory.swing.TextField textField1;
    private com.inventory.swing.TextField textField3;
    // End of variables declaration//GEN-END:variables
}
