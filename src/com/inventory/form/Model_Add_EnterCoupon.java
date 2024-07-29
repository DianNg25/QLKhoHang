/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.dao.ImportFormsDAO;
import com.inventory.entity.ImportForm;
import com.inventory.utils.XJdbc;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WINDOWS
 */
public class Model_Add_EnterCoupon extends javax.swing.JPanel {

    /**
     * Creates new form Model_Add_EnterCoupon
     */
    public Model_Add_EnterCoupon() {
        initComponents();
        loadSuppliersIntoComboBox();
        setCurrentDate();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboTenNhaCungCap = new com.inventory.swing.ComboBoxSuggestion();
        txtImportFormID = new com.inventory.swing.TextField();
        txtNgayHienTai = new com.inventory.swing.TextField();
        txtTotalAmount = new com.inventory.swing.TextField();
        jPanel2 = new javax.swing.JPanel();
        button1 = new com.inventory.swing.Button();
        btnOK = new com.inventory.swing.Button();
        txtTongTien = new com.inventory.swing.TextField();
        jLabel10 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(785, 545));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(32, 137, 173));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        jPanel1.setPreferredSize(new java.awt.Dimension(680, 50));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("QUẢN LÝ NHẬP HÀNG");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 10, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 789, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 54, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 55, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 1, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 1, Short.MAX_VALUE)))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(32, 137, 173));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setPreferredSize(new java.awt.Dimension(785, 545));
        jPanel3.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã phiếu nhập");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên nhà cung cấp");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ngày");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Số lượng");

        cboTenNhaCungCap.setPreferredSize(new java.awt.Dimension(151, 38));
        cboTenNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenNhaCungCapActionPerformed(evt);
            }
        });

        txtNgayHienTai.setFocusable(false);
        txtNgayHienTai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayHienTaiActionPerformed(evt);
            }
        });

        txtTotalAmount.setFocusable(false);

        jPanel2.setBackground(new java.awt.Color(32, 137, 173));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 45));

        button1.setBackground(new java.awt.Color(255, 0, 0));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Hủy");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        btnOK.setBackground(new java.awt.Color(27, 66, 139));
        btnOK.setForeground(new java.awt.Color(255, 255, 255));
        btnOK.setText("Nhập");
        btnOK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(567, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        txtTongTien.setFocusable(false);
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tổng tiền");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTenNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayHienTai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtImportFormID, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(61, 61, 61))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImportFormID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboTenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void cboTenNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenNhaCungCapActionPerformed
        // TODO add your handling code here:
        String selectedSupplier = (String) cboTenNhaCungCap.getSelectedItem();

        if (selectedSupplier != null) {
            try {
                // Tạo câu lệnh SQL để tính tổng số lượng sản phẩm
                String sql = "SELECT SUM(Quantity) AS TotalQuantity FROM Products WHERE SupplierID = (SELECT SupplierID FROM Suppliers WHERE SupplierName = ?)";

                // Sử dụng XJdbc để thực hiện câu lệnh với tham số
                java.sql.ResultSet rs = XJdbc.query(sql, selectedSupplier);

                int totalQuantity = 0;
                if (rs.next()) {
                    totalQuantity = rs.getInt("TotalQuantity");
                }

                // Cập nhật trường txtSoLuongSP với tổng số lượng
                txtTotalAmount.setText(String.valueOf(totalQuantity));

                rs.close();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi tải tổng số lượng sản phẩm.");
            }

        }
        if (selectedSupplier != null) {
            double totalAmount = getTotalAmountBySupplier(selectedSupplier);
            txtTongTien.setText(String.format("%.2f", totalAmount)); // Hiển thị tổng tiền
        }
    }//GEN-LAST:event_cboTenNhaCungCapActionPerformed

    private double getTotalAmountBySupplier(String supplierName) {
        double totalAmount = 0.0;
        String sql = "SELECT SUM(Price * Quantity) AS TotalAmount FROM Products WHERE SupplierID = (SELECT SupplierID FROM Suppliers WHERE SupplierName = ?)";

        try (java.sql.Connection conn = XJdbc.getConnection(); java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, supplierName);

            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    totalAmount = rs.getDouble("TotalAmount");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalAmount;
    }


    private void txtNgayHienTaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayHienTaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayHienTaiActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        addImportForm();
    }//GEN-LAST:event_btnOKActionPerformed

    private void addImportForm() {
        // Lấy tên nhà cung cấp từ cboTenNhaCungCap
        String selectedSupplierName = (String) cboTenNhaCungCap.getSelectedItem();

        if (selectedSupplierName == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp.");
            return;
        }

        // Lấy các thông tin khác từ form
        String importFormID = txtImportFormID.getText().trim();
        Date importDate = new java.util.Date(); // Hoặc lấy từ một công cụ chọn ngày nếu có
        String totalAmountStr = txtTotalAmount.getText().trim();
        BigDecimal totalAmount = new BigDecimal(totalAmountStr);

        String supplierID = null;

        try (java.sql.Connection conn = XJdbc.getConnection()) {
            // Lấy SupplierID từ SupplierName
            String sqlSupplierID = "SELECT SupplierID FROM Suppliers WHERE SupplierName = ?";
            try (java.sql.PreparedStatement pstmtSupplierID = conn.prepareStatement(sqlSupplierID)) {
                pstmtSupplierID.setString(1, selectedSupplierName);
                try (java.sql.ResultSet rsSupplierID = pstmtSupplierID.executeQuery()) {
                    if (rsSupplierID.next()) {
                        supplierID = rsSupplierID.getString("SupplierID");
                    }
                }
            }

            if (supplierID == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhà cung cấp.");
                return;
            }

            // Tạo đối tượng ImportForm
            ImportForm importForm = new ImportForm();
            importForm.setImportFormID(importFormID);
            importForm.setSupplierID(supplierID);
            importForm.setImportDate(importDate);
            importForm.setTotalAmount(totalAmount);

            // Tạo đối tượng ImportFormsDAO và thêm phiếu nhập
            ImportFormsDAO dao = new ImportFormsDAO(conn);
            dao.insert(importForm);

            // Thông báo thành công
            JOptionPane.showMessageDialog(this, "Đã thêm phiếu nhập thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm phiếu nhập: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setCurrentDate() {
        // Định dạng ngày tháng
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());

        // Cập nhật trường txtNgayHienTai
        txtNgayHienTai.setText(currentDate);
    }

    private void loadSuppliersIntoComboBox() {
        // Tạo kết nối đến cơ sở dữ liệu thông qua XJdbc
        try {
            // Thực hiện truy vấn
            String sql = "SELECT SupplierName FROM Suppliers";
            java.sql.ResultSet rs = XJdbc.query(sql);

            cboTenNhaCungCap.removeAllItems(); // Xóa tất cả các mục hiện tại

            // Thêm từng nhà cung cấp vào JComboBox
            while (rs.next()) {
                cboTenNhaCungCap.addItem(rs.getString("SupplierName"));
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu nhà cung cấp.");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnOK;
    private com.inventory.swing.Button button1;
    private com.inventory.swing.ComboBoxSuggestion cboTenNhaCungCap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.inventory.swing.TextField txtImportFormID;
    private com.inventory.swing.TextField txtNgayHienTai;
    private com.inventory.swing.TextField txtTongTien;
    private com.inventory.swing.TextField txtTotalAmount;
    // End of variables declaration//GEN-END:variables
}
