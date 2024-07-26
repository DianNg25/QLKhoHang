/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.log.Province;
import com.inventory.log.ShippingFee;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
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

        // Khởi tạo các thành phần giao diện
        addProvinces();

        cbo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbo2ActionPerformed(e);
            }
        });

        txtNgayNhap.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                txtNgayNhapFocusLost(e);
            }
        });

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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textField1 = new com.inventory.swing.TextField();
        txtNgayNhap = new com.inventory.swing.TextField();
        textField5 = new com.inventory.swing.TextField();
        comboBoxSuggestion1 = new com.inventory.swing.ComboBoxSuggestion();
        cbo2 = new com.inventory.swing.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        lblHoaHong = new javax.swing.JLabel();
        lblPhiVanChuyen1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textField2 = new com.inventory.swing.TextField();
        jLabel10 = new javax.swing.JLabel();
        textField3 = new com.inventory.swing.TextField();
        jLabel11 = new javax.swing.JLabel();
        comboBoxSuggestion2 = new com.inventory.swing.ComboBoxSuggestion();
        jLabel12 = new javax.swing.JLabel();
        textField4 = new com.inventory.swing.TextField();
        jLabel13 = new javax.swing.JLabel();
        textField6 = new com.inventory.swing.TextField();
        jLabel14 = new javax.swing.JLabel();
        textField7 = new com.inventory.swing.TextField();

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(638, Short.MAX_VALUE)
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
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
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
        jLabel6.setText("Hoa hồng");

        textField1.setPreferredSize(new java.awt.Dimension(25, 40));

        txtNgayNhap.setPreferredSize(new java.awt.Dimension(25, 40));
        txtNgayNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayNhapActionPerformed(evt);
            }
        });

        textField5.setPreferredSize(new java.awt.Dimension(25, 40));

        comboBoxSuggestion1.setPreferredSize(new java.awt.Dimension(151, 38));

        cbo2.setPreferredSize(new java.awt.Dimension(151, 38));
        cbo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tổng tiền:");

        lblHoaHong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHoaHong.setForeground(new java.awt.Color(255, 255, 255));

        lblPhiVanChuyen1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPhiVanChuyen1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Phí vận chuyển");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tên NCC");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Số lượng");

        comboBoxSuggestion2.setPreferredSize(new java.awt.Dimension(151, 38));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tên khách hàng");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Số điện thoại");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Phường/Xã");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textField1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                .addComponent(textField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPhiVanChuyen1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblHoaHong, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textField6, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbo2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(textField7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 150, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))))
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(comboBoxSuggestion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxSuggestion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel10)))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12)
                    .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(lblPhiVanChuyen1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHoaHong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(76, 76, 76))
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
            lblPhiVanChuyen1.setText(String.valueOf(fee) + " VND");
        }

    }//GEN-LAST:event_cbo2ActionPerformed

    private void txtNgayNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayNhapActionPerformed

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
        String ngayNhapStr = txtNgayNhap.getText();
        if (!ngayNhapStr.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date ngayNhap = sdf.parse(ngayNhapStr);
                Date ngayHienTai = new Date();

                long diffInMillies = Math.abs(ngayHienTai.getTime() - ngayNhap.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                double hoaHong = calculateCommission(diff);
                lblHoaHong.setText("Hoa Hồng: " + hoaHong + "%");
            } catch (Exception ex) {
                lblHoaHong.setText("Định dạng ngày không hợp lệ!");
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
    private com.inventory.swing.ComboBoxSuggestion comboBoxSuggestion1;
    private com.inventory.swing.ComboBoxSuggestion comboBoxSuggestion2;
    private com.inventory.swing.ComboSuggestionUI comboSuggestionUI1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel lblPhiVanChuyen1;
    private com.inventory.swing.TextField textField1;
    private com.inventory.swing.TextField textField2;
    private com.inventory.swing.TextField textField3;
    private com.inventory.swing.TextField textField4;
    private com.inventory.swing.TextField textField5;
    private com.inventory.swing.TextField textField6;
    private com.inventory.swing.TextField textField7;
    private com.inventory.swing.TextField txtNgayNhap;
    // End of variables declaration//GEN-END:variables
}
