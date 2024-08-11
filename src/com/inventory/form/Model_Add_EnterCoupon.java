/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.main.Login;
import com.inventory.message.AddThanhCong;
import com.inventory.message.AddThatBai;
import com.inventory.message.DeleteEmployees2_Er;
import com.inventory.message.DeleteNhapHang;
import com.inventory.message.DeleteSuppliers2;
import com.inventory.message.ErrorrNhapHang;
import com.inventory.message.ErrorrNhapHang1;
import com.inventory.message.ErrorrNhapHang11;
import com.inventory.swing.glasspanepopup.ModalErrorGlassPanePopup;
import com.inventory.swing.glasspanepopup.ModalErrorOption;
import com.inventory.utils.XJdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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
        jLabel14 = new javax.swing.JLabel();
        txtName = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaNCC = new com.inventory.swing.TextField();
        txtTong = new javax.swing.JLabel();

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
            .addGap(0, 807, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 63, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 64, Short.MAX_VALUE)))
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

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tên nhân viên :");

        txtName.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtName.setForeground(new java.awt.Color(255, 255, 255));
        txtName.setText("Bảo");
        txtName.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtNameAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel14)
                .addGap(39, 39, 39)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
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
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtName))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mã nhà cung cấp");

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tổng tiền");

        txtMaNCC.setFocusable(false);
        txtMaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNCCActionPerformed(evt);
            }
        });

        txtTong.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTong.setForeground(new java.awt.Color(255, 255, 255));
        txtTong.setText("Tổng tiền");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel3))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtTong)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayHienTai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaNCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTenNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtImportFormID, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(61, 61, 61))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        add(jPanel3, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void cboTenNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenNhaCungCapActionPerformed
        // TODO add your handling code here:
        String selectedSupplier = (String) cboTenNhaCungCap.getSelectedItem();

        if (selectedSupplier != null) {
            try (java.sql.Connection conn = XJdbc.getConnection()) {
                // Câu lệnh SQL để đếm số lượng sản phẩm
                String countSql = "SELECT COUNT(*) AS TotalProducts FROM Products WHERE SupplierID = (SELECT SupplierID FROM Suppliers WHERE SupplierName = ?) AND Status <> N'Đã xóa'";

                try (java.sql.PreparedStatement pstmtCount = conn.prepareStatement(countSql)) {
                    pstmtCount.setString(1, selectedSupplier);
                    try (ResultSet rsCount = pstmtCount.executeQuery()) {
                        int totalProducts = 0;
                        if (rsCount.next()) {
                            totalProducts = rsCount.getInt("TotalProducts");
                        }
                        txtTotalAmount.setText(String.valueOf(totalProducts));
                    }
                }

                // Câu lệnh SQL để lấy SupplierID
                String supplierIDSql = "SELECT SupplierID FROM Suppliers WHERE SupplierName = ?";
                try (java.sql.PreparedStatement pstmtSupplierID = conn.prepareStatement(supplierIDSql)) {
                    pstmtSupplierID.setString(1, selectedSupplier);
                    try (ResultSet rsSupplierID = pstmtSupplierID.executeQuery()) {
                        if (rsSupplierID.next()) {
                            String supplierID = rsSupplierID.getString("SupplierID");
                            txtMaNCC.setText(supplierID);
                        }
                    }
                }

                // Tính tổng tiền và cập nhật txtTong
                double totalAmount = getTotalAmountBySupplier(selectedSupplier);
                txtTong.setText(String.format("%.2f", totalAmount));

            } catch (SQLException e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    DeleteNhapHang errorPanel = new DeleteNhapHang();
                    errorPanel.eventOK((ae) -> {
                        ModalErrorGlassPanePopup.closePopupLast();
                    });
                    ModalErrorGlassPanePopup.showPopup((JDialog) SwingUtilities.getWindowAncestor(this), errorPanel, new ModalErrorOption());
                });

            }
        }

    }//GEN-LAST:event_cboTenNhaCungCapActionPerformed

    private double getTotalAmountBySupplier(String supplierName) {
        double totalAmount = 0.0;
        // Câu lệnh SQL đã được cập nhật để tính tổng tiền của các sản phẩm với trạng thái khác "Đã xóa"
        String sql = "SELECT SUM(Price * Quantity) AS TotalAmount FROM Products WHERE SupplierID = (SELECT SupplierID FROM Suppliers WHERE SupplierName = ?) AND Status <> N'Đã xóa'";

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

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        addImportForm();
    }//GEN-LAST:event_btnOKActionPerformed

    private void txtMaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNCCActionPerformed

    private void txtNameAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtNameAncestorAdded
        // TODO add your handling code here:
        String empId = Login.employeeId;
        String fullName = getFullNameByEmployeeId(empId);
        if (!fullName.isEmpty()) {
            txtName.setText(fullName);
        } else {
            txtName.setText("Unknown User");
        }
    }//GEN-LAST:event_txtNameAncestorAdded

    private String getFullNameByEmployeeId(String employeeId) {
        String fullName = "";

        try (ResultSet rs = XJdbc.query("SELECT FullName FROM Employees WHERE EmployeeID = ?", employeeId)) {
            if (rs.next()) {
                fullName = rs.getString("FullName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi ở đây (ví dụ: hiển thị thông báo lỗi)
        }
        return fullName;
    }

    private void addImportForm() {
        // Lấy tên nhà cung cấp từ cboTenNhaCungCap
        String selectedSupplierName = (String) cboTenNhaCungCap.getSelectedItem();

        if (selectedSupplierName == null) {
            SwingUtilities.invokeLater(() -> {
                DeleteSuppliers2 errorPanel = new DeleteSuppliers2();
                errorPanel.eventOK((ae) -> {
                    ModalErrorGlassPanePopup.closePopupLast();
                });
                ModalErrorGlassPanePopup.showPopup((JDialog) SwingUtilities.getWindowAncestor(this), errorPanel, new ModalErrorOption());
            });

            return;
        }

        // Lấy các thông tin khác từ form
        String importFormID = txtImportFormID.getText().trim();
        String importFormDetailID = txtImportFormID.getText().trim(); // ID phiếu nhập chi tiết
        String importDateStr = txtNgayHienTai.getText().trim(); // Ngày nhập từ form
        String totalAmountStr = txtTong.getText().trim(); // Tổng số tiền từ form
        String quantityStr = txtTotalAmount.getText().trim(); // Số lượng từ form
        String priceStr = txtTong.getText().trim(); // Giá từ form

        if (importFormID.isEmpty() || importFormDetailID.isEmpty()) {
            SwingUtilities.invokeLater(() -> {
                ErrorrNhapHang errorPanel = new ErrorrNhapHang();
                errorPanel.eventOK((ae) -> {
                    ModalErrorGlassPanePopup.closePopupLast();
                });
                ModalErrorGlassPanePopup.showPopup((JDialog) SwingUtilities.getWindowAncestor(this), errorPanel, new ModalErrorOption());
            });
            return;
        }

        // Chuyển đổi dữ liệu sang kiểu phù hợp
        BigDecimal totalAmount = parseBigDecimalOrZero(totalAmountStr);
        BigDecimal quantity = parseBigDecimalOrZero(quantityStr);
        BigDecimal price = parseBigDecimalOrZero(priceStr);
        java.sql.Date importDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(importDateStr);
            importDate = new java.sql.Date(date.getTime());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String supplierID = null;
        String employeeID = Login.employeeId; // Lấy EmployeeID từ thông tin đăng nhập

        try (java.sql.Connection conn = XJdbc.getConnection()) {
            // Kiểm tra xem ImportFormID đã tồn tại chưa
            String sqlCheckImportFormID = "SELECT COUNT(*) FROM ImportForms WHERE ImportFormID = ?";
            try (java.sql.PreparedStatement pstmtCheckID = conn.prepareStatement(sqlCheckImportFormID)) {
                pstmtCheckID.setString(1, importFormID);
                try (java.sql.ResultSet rsCheckID = pstmtCheckID.executeQuery()) {
                    if (rsCheckID.next() && rsCheckID.getInt(1) > 0) {
                        SwingUtilities.invokeLater(() -> {
                            ErrorrNhapHang1 errorPanel = new ErrorrNhapHang1();
                            errorPanel.eventOK((ae) -> {
                                ModalErrorGlassPanePopup.closePopupLast();
                            });
                            ModalErrorGlassPanePopup.showPopup((JDialog) SwingUtilities.getWindowAncestor(this), errorPanel, new ModalErrorOption());
                        });
                        return;
                    }
                }
            }

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
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhà cung cấp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Chèn dữ liệu vào bảng ImportForms bao gồm EmployeeID
            String queryImportForms = "INSERT INTO ImportForms (ImportFormID, SupplierID, ImportDate, TotalAmount, EmployeeID) VALUES (?, ?, ?, ?, ?)";
            try (java.sql.PreparedStatement pstmtImportForms = conn.prepareStatement(queryImportForms)) {
                pstmtImportForms.setString(1, importFormID);
                pstmtImportForms.setString(2, supplierID);
                pstmtImportForms.setDate(3, importDate);
                pstmtImportForms.setBigDecimal(4, totalAmount);
                pstmtImportForms.setString(5, employeeID); // Chèn EmployeeID vào

                int rowsInsertedImportForms = pstmtImportForms.executeUpdate();

                if (rowsInsertedImportForms <= 0) {
                     SwingUtilities.invokeLater(() -> {
                            ErrorrNhapHang11 errorPanel = new ErrorrNhapHang11();
                            errorPanel.eventOK((ae) -> {
                                ModalErrorGlassPanePopup.closePopupLast();
                            });
                            ModalErrorGlassPanePopup.showPopup((JDialog) SwingUtilities.getWindowAncestor(this), errorPanel, new ModalErrorOption());
                        });
                   
                    return;
                }
            }

            // Chèn dữ liệu vào bảng ImportFormDetails
            String queryImportFormDetails = "INSERT INTO ImportFormDetails (ImportFormDetailID, ImportFormID, Quantity, Price) VALUES (?, ?, ?, ? )";
            try (java.sql.PreparedStatement pstmtImportFormDetails = conn.prepareStatement(queryImportFormDetails)) {
                pstmtImportFormDetails.setString(1, importFormDetailID);
                pstmtImportFormDetails.setString(2, importFormID);
                pstmtImportFormDetails.setBigDecimal(3, quantity);
                pstmtImportFormDetails.setBigDecimal(4, price);
                int rowsInsertedImportFormDetails = pstmtImportFormDetails.executeUpdate();

                if (rowsInsertedImportFormDetails > 0) {
                    SwingUtilities.invokeLater(() -> {
                            AddThanhCong errorPanel = new AddThanhCong();
                            errorPanel.eventOK((ae) -> {
                                ModalErrorGlassPanePopup.closePopupLast();
                            });
                            ModalErrorGlassPanePopup.showPopup((JDialog) SwingUtilities.getWindowAncestor(this), errorPanel, new ModalErrorOption());
                        });
                    
                } else {
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
             SwingUtilities.invokeLater(() -> {
                            AddThatBai errorPanel = new AddThatBai();
                            errorPanel.eventOK((ae) -> {
                                ModalErrorGlassPanePopup.closePopupLast();
                            });
                            ModalErrorGlassPanePopup.showPopup((JDialog) SwingUtilities.getWindowAncestor(this), errorPanel, new ModalErrorOption());
                        });
         
        }
    }

// Phương thức tiện ích để chuyển đổi chuỗi thành BigDecimal, mặc định là 0 nếu lỗi
    private BigDecimal parseBigDecimalOrZero(String value) {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    private void setCurrentDate() {
        // Định dạng ngày tháng
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.inventory.swing.TextField txtImportFormID;
    private com.inventory.swing.TextField txtMaNCC;
    private javax.swing.JLabel txtName;
    private com.inventory.swing.TextField txtNgayHienTai;
    private javax.swing.JLabel txtTong;
    private com.inventory.swing.TextField txtTotalAmount;
    // End of variables declaration//GEN-END:variables
}
