package com.inventory.form;

import com.inventory.swing.*;
import com.inventory.utils.XJdbc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.JTable;

public class Form_6 extends javax.swing.JPanel {

    public Form_6() {
        initComponents();
        setupTable();
        updateComboBox();
        comboBoxSuggestion2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMaPhieuXuat = (String) comboBoxSuggestion2.getSelectedItem();
                if (selectedMaPhieuXuat != null) {
                    loadDataToTable(selectedMaPhieuXuat);
                }
            }
        });
        // Gán giá trị mặc định cho selectedMaPhieuXuat hoặc để trống nếu không có giá trị mặc định
        String defaultMaPhieuXuat = "MA_PHIEU_XUAT_CHUNG"; // Thay thế bằng giá trị mặc định thực tế nếu có
        loadDataToTable(defaultMaPhieuXuat);

    }

    private void setupTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tên khách hàng");
        model.addColumn("Ngày xuất");
        model.addColumn("Nhân viên");
        model.addColumn("Tổng cộng");
        model.addColumn("Chi tiết");

        tbl.setModel(model);
        tbl.getColumnModel().getColumn(4).setCellRenderer(new TableCellRender());
        tbl.getColumnModel().getColumn(4).setCellEditor(new TableCellEditor());
       

    }

    
  private void loadDataToTable(String maPhieuXuat) {
    System.out.println("Loading data for ExportFormID: " + maPhieuXuat);
    try {
        // Lấy thông tin cơ bản từ bảng ExportForms
        String query = "SELECT kh.CustomerName, px.ExportDate, px.TotalAmount, nv.FullName AS EmployeeName "
                + "FROM ExportForms px "
                + "JOIN Employees nv ON px.EmployeeID = nv.EmployeeID "
                + "JOIN Customers kh ON px.CustomerID = kh.CustomerID "
                + "WHERE px.ExportFormID = ?";

        ResultSet resultSet = XJdbc.query(query, maPhieuXuat);
        if (resultSet.next()) {
            String customerName = resultSet.getString("CustomerName");
            java.sql.Date exportDate = resultSet.getDate("ExportDate");
            double totalAmount = resultSet.getDouble("TotalAmount");
            String employeeName = resultSet.getString("EmployeeName");

            // Cập nhật dữ liệu chính vào bảng
            DefaultTableModel model = (DefaultTableModel) tbl.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ

            model.addRow(new Object[]{
                customerName,
                exportDate,
                employeeName,
                totalAmount,
                "" // Cột chi tiết sẽ được cập nhật sau
            });
        }

        resultSet.close();

        // Lấy thông tin nhà cung cấp từ bảng ExportForms
        String supplierQuery = "SELECT sp.SupplierName "
                + "FROM ExportForms ef "
                + "JOIN Suppliers sp ON ef.SupplierID = sp.SupplierID "
                + "WHERE ef.ExportFormID = ?";

        ResultSet supplierResultSet = XJdbc.query(supplierQuery, maPhieuXuat);
        String supplierName = "";
        if (supplierResultSet.next()) {
            supplierName = supplierResultSet.getString("SupplierName");
        }
        supplierResultSet.close();

        // Cập nhật dữ liệu nhà cung cấp vào bảng
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        int rowCount = model.getRowCount();
        if (rowCount > 0) {
            Object currentDetail = model.getValueAt(rowCount - 1, 4);
            if (currentDetail != null && !currentDetail.toString().isEmpty()) {
                model.setValueAt(currentDetail.toString() + "\n" + "Supplier: " + supplierName, rowCount - 1, 4);
            } else {
                model.setValueAt("Supplier: " + supplierName, rowCount - 1, 4);
            }
        }
        System.out.println("Data loaded successfully.");

    } catch (Exception e) {
        e.printStackTrace(); // Ghi log lỗi
    }
}



private void updateComboBox() {
    try {
        String query = "SELECT ExportFormID FROM ExportForms";
        ResultSet resultSet = XJdbc.query(query);

        // Clear existing items
        comboBoxSuggestion2.removeAllItems();

        while (resultSet.next()) {
            comboBoxSuggestion2.addItem(resultSet.getString("ExportFormID"));
        }

        resultSet.close();

    } catch (Exception e) {
        e.printStackTrace(); // Handle exceptions appropriately
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        comboBoxSuggestion2 = new com.inventory.swing.ComboBoxSuggestion();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new com.inventory.swing.Table();
        jPanel1 = new javax.swing.JPanel();
        button1 = new com.inventory.swing.Button();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        comboBoxSuggestion2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        comboBoxSuggestion2.setPreferredSize(new java.awt.Dimension(186, 38));
        comboBoxSuggestion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSuggestion2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(300, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(null);

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên khách hàng", "Ngày xuất", "Nhân viên", "Tổng cộng", "Chi tiết"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl.setRowHeight(100);
        jScrollPane1.setViewportView(tbl);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        button1.setBackground(new java.awt.Color(102, 102, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Xuất hóa đơn");
        button1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxSuggestion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSuggestion2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_comboBoxSuggestion2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button button1;
    private com.inventory.swing.ComboBoxSuggestion comboBoxSuggestion2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.inventory.swing.Table tbl;
    // End of variables declaration//GEN-END:variables
}
