package com.inventory.form;

import com.inventory.swing.*;
import com.inventory.utils.XJdbc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class Form_6 extends javax.swing.JPanel {

    public Form_6() {
        initComponents();
        tbl.getColumnModel().getColumn(4).setCellRenderer(new TableCellRender());
        tbl.getColumnModel().getColumn(4).setCellEditor(new TableCellEditor());
        DefaultTableModel subTable = new DefaultTableModel();
        subTable.addColumn("Mã SP");
        subTable.addColumn("Tên SP");
        subTable.addColumn("Số lượng");
        subTable.addColumn("Giá");

        comboBoxSuggestion2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy mã phiếu xuất được chọn
                String selectedMaPhieuXuat = (String) comboBoxSuggestion2.getSelectedItem();

                // Xóa dữ liệu cũ trên bảng
                DefaultTableModel model = (DefaultTableModel) tbl.getModel();
                model.setRowCount(0);

                // Truy vấn dữ liệu từ cơ sở dữ liệu dựa trên mã phiếu xuất
                loadDataToTable(selectedMaPhieuXuat);
            }
        });
    }

    private void loadDataToTable(String maPhieuXuat) {
    try {
        // 1. Truy vấn dữ liệu từ cơ sở dữ liệu, bao gồm cả việc nối với các bảng khác
        String query = "SELECT kh.CustomerName, px.ExportDate, px.TotalAmount, nv.FullName, ct.ProductID, sp.ProductName, ct.Quantity, ct.Price " +
                       "FROM ExportForm px " +
                       "JOIN ImportFormDetails ct ON px.ExportFormID = ct.ExportFormID " +
                       "JOIN Employees nv ON px.EmployeeID = nv.EmployeeID " +
                       "JOIN Products sp ON ct.ProductID = sp.ProductID " +
                       "JOIN Customers kh ON px.CustomerID = kh.CustomerID " +
                       "WHERE px.ExportFormID = ?";
        ResultSet resultSet = XJdbc.query(query, maPhieuXuat);

        DefaultTableModel model = (DefaultTableModel) tbl.getModel();

        while (resultSet.next()) {
            // 2. Tạo DefaultTableModel cho bảng con
            DefaultTableModel subTable = new DefaultTableModel();
            subTable.addColumn("Mã SP");
            subTable.addColumn("Tên SP");
            subTable.addColumn("Số lượng");
            subTable.addColumn("Giá");

            // Thêm dữ liệu vào bảng con
            subTable.addRow(new Object[]{
                resultSet.getString("ProductID"),
                resultSet.getString("ProductName"), // Lấy tên sản phẩm từ bảng SanPham
                resultSet.getInt("Quantity"),
                resultSet.getDouble("Price")
            });

            // 3. Thêm dữ liệu vào bảng chính
            model.addRow(new Object[]{
                resultSet.getString("CustomerName"),
                resultSet.getDate("ExportDate"),
                resultSet.getString("FullName"), // Lấy tên nhân viên từ bảng NhanVien
                resultSet.getDouble("TotalAmount"),
                subTable
            });
        }

        // 4. Cập nhật giao diện bảng
        model.fireTableDataChanged();

        // Đóng ResultSet
        resultSet.close();

    } catch (Exception e) {
        e.printStackTrace(); // Xử lý ngoại lệ phù hợp
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button button1;
    private com.inventory.swing.ComboBoxSuggestion comboBoxSuggestion2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.inventory.swing.Table tbl;
    // End of variables declaration//GEN-END:variables
}
