/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.log.Province;
import com.inventory.log.ShippingFee;
import com.inventory.utils.XJdbc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author WINDOWS
 */
public class Model_Add_ProductDelivery extends javax.swing.JPanel {

    /**
     * Creates new form Model_Add_ProductDelivery
     */
    public Model_Add_ProductDelivery() {
        initComponents();
        loadDateNow();
        addProvinces();
        populateImportFormIDs();
        populateCustomerIDs();

        cbo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbo2ActionPerformed(e);
            }
        });

        txtNgayXuat.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                txtNgayNhapFocusLost(e);
            }
        });

    }

    private void populateImportFormIDs() {
        String query = "SELECT ImportFormID FROM ImportForms";
        try (java.sql.Connection connection = XJdbc.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(query); java.sql.ResultSet resultSet = statement.executeQuery()) {

            cboImportFormID.removeAllItems(); // Xóa các mục hiện có để tránh trùng lặp
            while (resultSet.next()) {
                cboImportFormID.addItem(resultSet.getString("ImportFormID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải ID phiếu nhập.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFieldsBasedOnImportFormID(String importFormID) {
        String query = "SELECT ImportFormID, SupplierID, ImportDate, TotalAmount FROM ImportForms WHERE ImportFormID = ?";
        try (java.sql.Connection connection = XJdbc.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, importFormID);
            try (java.sql.ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Lấy thông tin phiếu nhập
                    String supplierID = resultSet.getString("SupplierID");
                    String importDate = resultSet.getDate("ImportDate").toString();
                    double totalAmount = resultSet.getDouble("TotalAmount");

                    // Lấy tên nhà cung cấp và cập nhật vào txtNCC
                    String supplierName = getSupplierName(supplierID);
                    txtTenNCC.setText(supplierName);
                    txtSoLuong.setText(String.valueOf(totalAmount));
                    txtNgayNhap.setText(importDate);

                    // Lấy tổng giá của sản phẩm từ nhà cung cấp
                    double totalPrice = getTotalPriceBySupplierName(supplierName);
                    txtPhiSL.setText(String.valueOf(totalPrice));

                } else {
                    txtMaPX.setText("Không tìm thấy thông tin cho ID phiếu nhập này.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy thông tin phiếu nhập.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double getTotalPriceBySupplierName(String supplierName) {
        String query = "SELECT SUM(p.Quantity * 500) AS TotalPrice "
                + "FROM Suppliers s "
                + "JOIN Products p ON s.SupplierID = p.SupplierID "
                + "WHERE s.SupplierName = ?";
        try (java.sql.Connection connection = XJdbc.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, supplierName);
            try (java.sql.ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("TotalPrice");
                } else {
                    return 0.0; // Trả về 0 nếu không có kết quả
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy tổng giá của sản phẩm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return 0.0;
        }
    }

    private String getSupplierName(String supplierID) {
        String query = "SELECT SupplierName FROM Suppliers WHERE SupplierID = ?";
        try (java.sql.Connection connection = XJdbc.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, supplierID);
            try (java.sql.ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("SupplierName");
                } else {
                    return "Không tìm thấy tên nhà cung cấp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy tên nhà cung cấp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return "Lỗi";
        }
    }

    private void populateCustomerIDs() {
        String query = "SELECT CustomerID FROM Customers";
        try (java.sql.Connection connection = XJdbc.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(query); java.sql.ResultSet resultSet = statement.executeQuery()) {

            cboImportFormID1.removeAllItems(); // Xóa các mục hiện có để tránh trùng lặp
            while (resultSet.next()) {
                cboImportFormID1.addItem(resultSet.getString("CustomerID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải ID khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFieldsBasedOnCustomerID(String customerID) {
        String query = "SELECT CustomerName FROM Customers WHERE CustomerID = ?";
        try (java.sql.Connection connection = XJdbc.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, customerID);
            try (java.sql.ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Lấy tên khách hàng từ bảng Customers và cập nhật vào txtName
                    String customerName = resultSet.getString("CustomerName");
                    txtName.setText(customerName);
                } else {
                    txtMaPX.setText("Không tìm thấy thông tin cho ID khách hàng này.");
                    txtName.setText("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy thông tin khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDateNow() {
        txtNgayXuat.setText(getCurrentDate());
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Định dạng ngày
        return sdf.format(new Date());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboSuggestionUI1 = new com.inventory.swing.ComboSuggestionUI();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        button1 = new com.inventory.swing.Button();
        btnOK = new com.inventory.swing.Button();
        jLabel15 = new javax.swing.JLabel();
        txtTong = new com.inventory.swing.TextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaPX = new com.inventory.swing.TextField();
        txtNgayXuat = new com.inventory.swing.TextField();
        txtPhiSL = new com.inventory.swing.TextField();
        cboImportFormID = new com.inventory.swing.ComboBoxSuggestion();
        cbo2 = new com.inventory.swing.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        lblHoaHong = new javax.swing.JLabel();
        lblPhiVanChuyen = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNgayNhap = new com.inventory.swing.TextField();
        jLabel10 = new javax.swing.JLabel();
        txtSoLuong = new com.inventory.swing.TextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTenNCC = new com.inventory.swing.TextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtPHX = new com.inventory.swing.TextField();
        txtName = new com.inventory.swing.TextField();
        cboImportFormID1 = new com.inventory.swing.ComboBoxSuggestion();

        setPreferredSize(new java.awt.Dimension(850, 500));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(32, 137, 173));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 50));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("QUẢN LÝ XUẤT HÀNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 85, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 85, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 11, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 11, Short.MAX_VALUE)))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(32, 137, 173));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 80));

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
        btnOK.setText("Thêm");
        btnOK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tổng tiền");

        txtTong.setPreferredSize(new java.awt.Dimension(25, 40));
        txtTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 440, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(32, 137, 173));
        jPanel3.setPreferredSize(new java.awt.Dimension(750, 467));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã phiếu xuất");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mã phiếu nhập");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ngày nhập");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Chọn tỉnh");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ngày xuất");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Phí gửi hàng(ngày)");

        txtMaPX.setPreferredSize(new java.awt.Dimension(25, 40));

        txtNgayXuat.setPreferredSize(new java.awt.Dimension(25, 40));
        txtNgayXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayXuatActionPerformed(evt);
            }
        });

        txtPhiSL.setPreferredSize(new java.awt.Dimension(25, 40));

        cboImportFormID.setPreferredSize(new java.awt.Dimension(151, 38));
        cboImportFormID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboImportFormIDActionPerformed(evt);
            }
        });

        cbo2.setPreferredSize(new java.awt.Dimension(151, 38));
        cbo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Phí gửi hàng(số lượng)");

        lblHoaHong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoaHong.setForeground(new java.awt.Color(255, 255, 255));

        lblPhiVanChuyen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPhiVanChuyen.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Phí vận chuyển");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tên NCC");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Số lượng");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tên khách hàng");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Mã khách hàng");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Phường/Xã");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        cboImportFormID1.setPreferredSize(new java.awt.Dimension(151, 38));
        cboImportFormID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboImportFormID1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMaPX, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                .addComponent(txtNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(cboImportFormID, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                        .addGap(9, 9, 9))
                                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboImportFormID1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(txtPhiSL, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblHoaHong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPhiVanChuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(135, 135, 135)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbo2, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addComponent(txtPHX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaPX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cboImportFormID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10))
                        .addGap(12, 12, 12)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel13))
                    .addComponent(cboImportFormID1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(lblPhiVanChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel6))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblHoaHong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtPhiSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtPHX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_button1ActionPerformed


    private void cbo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo2ActionPerformed
        // TODO add your handling code here:
        Province selectedProvince = (Province) cbo2.getSelectedItem();
        if (selectedProvince != null) {
            int fee = ShippingFee.calculateFee(selectedProvince.getDistance());
            lblPhiVanChuyen.setText(String.valueOf(fee) + " VND");
        }

    }//GEN-LAST:event_cbo2ActionPerformed

    private void txtNgayXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayXuatActionPerformed

    private void cboImportFormIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboImportFormIDActionPerformed
        // TODO add your handling code here:
        String selectedID = (String) cboImportFormID.getSelectedItem();
        updateFieldsBasedOnImportFormID(selectedID);
    }//GEN-LAST:event_cboImportFormIDActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        String exportFormID = txtMaPX.getText().trim();
        String customerId = (String) cboImportFormID1.getSelectedItem();
        String exportDateStr = txtNgayXuat.getText().trim();
        String totalAmountStr = txtTong.getText().trim();
        String hoaHongStr = lblHoaHong.getText().trim();
        String phiVanChuyenStr = lblPhiVanChuyen.getText().trim();
        String status = "Đã xuất"; // Giá trị cố định

        // Kiểm tra mã phiếu xuất không được bỏ trống
        if (exportFormID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã phiếu xuất không được bỏ trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra mã phiếu xuất đã tồn tại
        if (isExportFormIDExists(exportFormID)) {
            JOptionPane.showMessageDialog(this, "Mã phiếu xuất đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Chuyển đổi dữ liệu sang kiểu phù hợp
        double totalAmount = parseDoubleOrZero(totalAmountStr);
        double hoaHong = parseDoubleOrZero(hoaHongStr);
        double phiVanChuyen = parseDoubleOrZero(phiVanChuyenStr);

        java.sql.Date exportDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = sdf.parse(exportDateStr);
            exportDate = new java.sql.Date(date.getTime());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Kiểm tra mã khách hàng không bị null
        if (customerId == null || customerId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Câu lệnh SQL để chèn dữ liệu vào bảng ExportForms
        String query = "INSERT INTO ExportForms (ExportFormID, CustomerID, ExportDate, TotalAmount, CommissionFee, ShippingFee, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (java.sql.Connection connection = XJdbc.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(query)) {

            // Đặt giá trị vào câu lệnh PreparedStatement
            statement.setString(1, exportFormID);
            statement.setString(2, customerId);
            statement.setDate(3, exportDate);
            statement.setDouble(4, totalAmount);
            statement.setDouble(5, hoaHong);
            statement.setDouble(6, phiVanChuyen);
            statement.setString(7, status);

            // Thực thi câu lệnh
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Thông tin phiếu xuất đã được lưu thành công.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Không thể lưu thông tin phiếu xuất.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu thông tin phiếu xuất.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void cboImportFormID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboImportFormID1ActionPerformed
        // TODO add your handling code here:
        String selectedID = (String) cboImportFormID1.getSelectedItem();
        updateFieldsBasedOnCustomerID(selectedID);
    }//GEN-LAST:event_cboImportFormID1ActionPerformed

    private void txtTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private double parseDoubleOrZero(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0.0; // Trả về 0 nếu không thể chuyển đổi
        }
    }

    private boolean isExportFormIDExists(String exportFormID) {
        String query = "SELECT COUNT(*) FROM ExportForms WHERE ExportFormID = ?";
        try (java.sql.Connection connection = XJdbc.getConnection(); java.sql.PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, exportFormID);
            try (java.sql.ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra mã phiếu xuất.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    //Tính hoa hồng
    private double calculateCommission(long days) {
        if (days < 30) {
            return 10.0;
        } else if (days < 60) {
            return 15.0;
        } else if (days < 90) {
            return 20.0;
        } else {
            return 25.0;
        }
    }

    private void txtNgayNhapFocusLost(FocusEvent evt) {
        String ngayNhapStr = txtNgayNhap.getText(); // Đảm bảo sử dụng đúng trường văn bản
        if (!ngayNhapStr.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date ngayNhap = sdf.parse(ngayNhapStr);
                Date ngayHienTai = new Date();

                long diffInMillies = Math.abs(ngayHienTai.getTime() - ngayNhap.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                double hoaHong = calculateCommission(diff);
                lblHoaHong.setText(String.format("%.2f", hoaHong)); // Định dạng giá trị hoa hồng

                String phiVanChuyenText = lblPhiVanChuyen.getText();
                double phiVanChuyen = parseDoubleOrZero(phiVanChuyenText);
                System.out.println("VC" + phiVanChuyenText);

                // Lấy giá trị từ lblHoaHong (JLabel)
//        String hoaHongText = lblHoaHong.getText();
//        double hoaHong = parseDoubleOrZero(hoaHongText);
                System.out.println("HH" + hoaHong);

                // Lấy giá trị từ txtPhiSL (JTextField)
                String phiSLText = txtPhiSL.getText();
                double phiSL = parseDoubleOrZero(phiSLText);
                System.out.println("SL" + phiSLText);

                // Tính toán tổng
                double tong = hoaHong * phiSL + phiVanChuyen;

                System.out.println("" + tong);
                // Cập nhật giá trị vào txtTong
                txtTong.setText(String.valueOf(tong));

            } catch (Exception ex) {
                lblHoaHong.setText("Định dạng ngày không hợp lệ!");
                System.out.println("Lỗi phân tích ngày: " + ex.getMessage());
            }
        } else {
            lblHoaHong.setText("Vui lòng nhập ngày!");
        }
    }

//     private void updateLabel() {
//        String ngayNhapStr = txtNgayNhap.getText();
//        String commissionText = "";
//        if (!ngayNhapStr.isEmpty()) {
//            try {
//                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                Date ngayNhap = sdf.parse(ngayNhapStr);
//                Date ngayHienTai = new Date();
//
//                long diffInMillies = Math.abs(ngayHienTai.getTime() - ngayNhap.getTime());
//                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
//
//                double hoaHong = calculateCommission(diff);
//                commissionText = "Hoa Hồng: " + hoaHong + "%";
//            } catch (Exception ex) {
//                commissionText = "Định dạng ngày không hợp lệ!";
//            }
//        }
//
//        Province selectedProvince = (Province) cbo2.getSelectedItem();
//        String shippingFeeText = "";
//        if (selectedProvince != null) {
//            int fee = ShippingFee.calculateFee(selectedProvince.getDistance());
//            shippingFeeText = "Phí Vận Chuyển: " + fee;
//        }
//
//        lblPhiVanChuyen.setText(shippingFeeText + " | " + commissionText);
//    }
    //thêm tỉnh vào cbo
    private void addProvinces() {
        // Danh sách các tỉnh và khoảng cách từ Cần Thơ (km)
        ArrayList<Province> provinces = new ArrayList<>();
        provinces.add(new Province("An Giang", 60));
        provinces.add(new Province("Bà Rịa - Vũng Tàu", 250));
        provinces.add(new Province("Bắc Giang", 1800));
        provinces.add(new Province("Bắc Kạn", 2000));
        provinces.add(new Province("Bạc Liêu", 130));
        provinces.add(new Province("Bắc Ninh", 1800));
        provinces.add(new Province("Bến Tre", 120));
        provinces.add(new Province("Bình Định", 650));
        provinces.add(new Province("Bình Dương", 200));
        provinces.add(new Province("Bình Phước", 250));
        provinces.add(new Province("Bình Thuận", 350));
        provinces.add(new Province("Cà Mau", 180));
        provinces.add(new Province("Cao Bằng", 2000));
        provinces.add(new Province("Đắk Lắk", 350));
        provinces.add(new Province("Đắk Nông", 400));
        provinces.add(new Province("Điện Biên", 2000));
        provinces.add(new Province("Đồng Nai", 220));
        provinces.add(new Province("Đồng Tháp", 130));
        provinces.add(new Province("Gia Lai", 400));
        provinces.add(new Province("Hà Giang", 2100));
        provinces.add(new Province("Hà Nam", 1700));
        provinces.add(new Province("Hà Tĩnh", 800));
        provinces.add(new Province("Hải Dương", 1700));
        provinces.add(new Province("Hải Phòng", 1750));
        provinces.add(new Province("Hậu Giang", 60));
        provinces.add(new Province("Hòa Bình", 1700));
        provinces.add(new Province("Hưng Yên", 1700));
        provinces.add(new Province("Khánh Hòa", 600));
        provinces.add(new Province("Kiên Giang", 90));
        provinces.add(new Province("Kon Tum", 400));
        provinces.add(new Province("Lai Châu", 2000));
        provinces.add(new Province("Lâm Đồng", 350));
        provinces.add(new Province("Lạng Sơn", 1800));
        provinces.add(new Province("Lào Cai", 1900));
        provinces.add(new Province("Long An", 150));
        provinces.add(new Province("Nam Định", 1750));
        provinces.add(new Province("Nghệ An", 800));
        provinces.add(new Province("Ninh Bình", 1750));
        provinces.add(new Province("Ninh Thuận", 350));
        provinces.add(new Province("Phú Thọ", 1800));
        provinces.add(new Province("Phú Yên", 700));
        provinces.add(new Province("Quảng Bình", 850));
        provinces.add(new Province("Quảng Nam", 900));
        provinces.add(new Province("Quảng Ngãi", 850));
        provinces.add(new Province("Quảng Ninh", 1800));
        provinces.add(new Province("Quảng Trị", 850));
        provinces.add(new Province("Sóc Trăng", 60));
        provinces.add(new Province("Sơn La", 2000));
        provinces.add(new Province("Tây Ninh", 250));
        provinces.add(new Province("Thái Bình", 1750));
        provinces.add(new Province("Thái Nguyên", 1800));
        provinces.add(new Province("Thanh Hóa", 800));
        provinces.add(new Province("Thừa Thiên Huế", 850));
        provinces.add(new Province("Tiền Giang", 100));
        provinces.add(new Province("Trà Vinh", 100));
        provinces.add(new Province("Tuyên Quang", 1800));
        provinces.add(new Province("Vĩnh Long", 70));
        provinces.add(new Province("Vĩnh Phúc", 1800));
        provinces.add(new Province("Yên Bái", 1900));

        for (Province province : provinces) {
            cbo2.addItem(province);
        }
    }

    public void eventOK(ActionListener event) {
        btnOK.addActionListener(event);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnOK;
    private com.inventory.swing.Button button1;
    private com.inventory.swing.ComboBoxSuggestion cbo2;
    private com.inventory.swing.ComboBoxSuggestion cboImportFormID;
    private com.inventory.swing.ComboBoxSuggestion cboImportFormID1;
    private com.inventory.swing.ComboSuggestionUI comboSuggestionUI1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblHoaHong;
    private javax.swing.JLabel lblPhiVanChuyen;
    private com.inventory.swing.TextField txtMaPX;
    private com.inventory.swing.TextField txtName;
    private com.inventory.swing.TextField txtNgayNhap;
    private com.inventory.swing.TextField txtNgayXuat;
    private com.inventory.swing.TextField txtPHX;
    private com.inventory.swing.TextField txtPhiSL;
    private com.inventory.swing.TextField txtSoLuong;
    private com.inventory.swing.TextField txtTenNCC;
    private com.inventory.swing.TextField txtTong;
    // End of variables declaration//GEN-END:variables
}
