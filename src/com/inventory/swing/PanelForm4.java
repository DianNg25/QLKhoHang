/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.swing;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

public class PanelForm4 extends javax.swing.JPanel {

    public JTable tblNCC;

    public PanelForm4() {
        initComponents();
        initTable();

    }

    private void initTable() {
        tblNCC = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"SupplierID", "SupplierName", "Address", "Phone", "Email"}
        ));

    }
    
    private void timKiemNCC(){
         String supplierName = txtNameNCC.getText(); // Updated JTextField name
        if (supplierName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhà cung cấp để tìm kiếm.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblNCC.getModel();
        model.setRowCount(0);

        try {
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=QuanLyKhoHang;user=sa;password=123");
            CallableStatement stmt = con.prepareCall("{call sp_TimKiemNhaCungCap(?)}");
            stmt.setString(1, supplierName);
            ResultSet rs = stmt.executeQuery();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                String id = rs.getString("SupplierID");
                String name = rs.getString("SupplierName");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                model.addRow(new Object[]{id, name, address, phone, email});
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        txtNameNCC = new com.inventory.swing.TextField();
        btnTimKiemNCC = new com.inventory.swing.Button();

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setText("Tên nhà cung cấp");

        txtNameNCC.setBackground(new java.awt.Color(72, 142, 174));
        txtNameNCC.setForeground(new java.awt.Color(255, 255, 255));
        txtNameNCC.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNameNCC.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        btnTimKiemNCC.setBackground(new java.awt.Color(102, 102, 255));
        btnTimKiemNCC.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemNCC.setText("Tìm Kiếm");
        btnTimKiemNCC.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnTimKiemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel5)
                .addGap(40, 40, 40)
                .addComponent(txtNameNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(btnTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNameNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNCCActionPerformed
       timKiemNCC();
    }//GEN-LAST:event_btnTimKiemNCCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnTimKiemNCC;
    private javax.swing.JLabel jLabel5;
    private com.inventory.swing.TextField txtNameNCC;
    // End of variables declaration//GEN-END:variables
}
