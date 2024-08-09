/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.entity.ModelData;

import com.inventory.swing.chart.ModelChart;
import com.inventory.utils.XJdbc;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author ADMIN
 */
public class Form_Home extends javax.swing.JPanel {

    
    
    

        // Sử dụng TableHeaderRenderer cho tiêu đề bảng
        
    /**
     * Creates new form Form_Home
     */
    public Form_Home() {
        initComponents();
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/inventory/icon/profit.png")), "Tổng doanh thu", "361.000.000VND", "Increased by 60%"));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/inventory/icon/stock.png")), "Tổng số hàng tồn kho", "267 Sản phẩm", "Increased by 25%"));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/inventory/icon/flag.png")), "Tổng số hàng xuất kho", "167 Sản Phẩm", "Increased by 70%"));
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
//                    lblDoanhThu.setText(df.format(totalRevenue));
                }
            }

            try (ResultSet rsImported = cstmtImported.executeQuery()) {
                if (rsImported.next()) {
                    int totalImported = rsImported.getInt("totalImportedQuantity");
//                    lblNhap.setText(String.valueOf(totalImported));
                }
            }

            try (ResultSet rsExported = cstmtExported.executeQuery()) {
                if (rsExported.next()) {
                    int totalExported = rsExported.getInt("totalExportedQuantity");
//                    lblXuat.setText(String.valueOf(totalExported));
                }
            }

            int totalInventory = 0;
            try (ResultSet rsInventory = cstmtInventory.executeQuery()) {
                while (rsInventory.next()) {
                    totalInventory += rsInventory.getInt("currentQuantity");
                }
//                lblTonKho.setText(String.valueOf(totalInventory));
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
       String[] monthNames = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
        if (monthNumber >= 1 && monthNumber <= 12) {
            return monthNames[monthNumber - 1];
        } else {
            return "Invalid Month";
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.inventory.swing.PanelBorder();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        card1 = new com.inventory.component.Card_DT();
        card2 = new com.inventory.component.Card_TK();
        card3 = new com.inventory.component.Card_XK();
        jPanel1 = new javax.swing.JPanel();
        panelShadow1 = new com.inventory.swing.chart.PanelShadow();
        chart = new com.inventory.swing.chart.CurveLineChart();

        setBackground(new java.awt.Color(255, 255, 255));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLayeredPane1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(26, 42, 128));
        card1.setColor2(new java.awt.Color(36, 183, 194));
        jLayeredPane1.add(card1);

        card2.setColor1(new java.awt.Color(26, 42, 128));
        card2.setColor2(new java.awt.Color(36, 183, 194));
        jLayeredPane1.add(card2);

        card3.setColor1(new java.awt.Color(26, 42, 128));
        card3.setColor2(new java.awt.Color(36, 183, 194));
        jLayeredPane1.add(card3);

        jPanel1.setLayout(new java.awt.BorderLayout());

        panelShadow1.setBackground(new java.awt.Color(30, 99, 154));
        panelShadow1.setColorGradient(new java.awt.Color(0, 99, 154));

        chart.setBackground(new java.awt.Color(34, 59, 69));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelShadow1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, 25, getHeight());
        g2.fillRect(getWidth() - 25, getHeight() - 25, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.component.Card_DT card1;
    private com.inventory.component.Card_TK card2;
    private com.inventory.component.Card_XK card3;
    private com.inventory.swing.chart.CurveLineChart chart;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private com.inventory.swing.PanelBorder panelBorder1;
    private com.inventory.swing.chart.PanelShadow panelShadow1;
    // End of variables declaration//GEN-END:variables
}
