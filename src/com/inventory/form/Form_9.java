/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.dao.EmployeesDAO;
import com.inventory.entity.Employees;
import java.util.Random;
import javax.swing.JOptionPane;
import com.inventory.message.*;
import com.inventory.swing.glasspanepopup.GlassPanePopup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class Form_9 extends javax.swing.JPanel {

    private String currentCaptchaCode; // Biến lưu trữ mã CAPTCHA hiện tại

    public Form_9() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaCapcha = new com.inventory.swing.TextField();
        lblMaCapcha = new javax.swing.JLabel();
        btnCapcha = new com.inventory.swing.Button();
        btnThayDoiMatKhau = new com.inventory.swing.Button();
        txtTenDangNhap = new com.inventory.swing.TextFieldLogin();
        txtMatKhauMoi = new com.inventory.swing.PasswordField();
        txtXacNhanMatKhau = new com.inventory.swing.PasswordField();
        txtMatKhauCu = new com.inventory.swing.PasswordField();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 607));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventory/icon/baomat1.jpg"))); // NOI18N
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.LINE_END);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setText("Nhập mã capcha");

        txtMaCapcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaCapchaActionPerformed(evt);
            }
        });

        lblMaCapcha.setBackground(new java.awt.Color(255, 255, 255));
        lblMaCapcha.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        lblMaCapcha.setForeground(new java.awt.Color(204, 0, 0));

        btnCapcha.setBackground(new java.awt.Color(102, 102, 255));
        btnCapcha.setForeground(new java.awt.Color(255, 255, 255));
        btnCapcha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventory/icon/Reset.png"))); // NOI18N
        btnCapcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapchaActionPerformed(evt);
            }
        });

        btnThayDoiMatKhau.setBackground(new java.awt.Color(102, 102, 255));
        btnThayDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnThayDoiMatKhau.setText("Thay đổi");
        btnThayDoiMatKhau.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnThayDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayDoiMatKhauActionPerformed(evt);
            }
        });

        txtTenDangNhap.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTenDangNhap.setLabelText("Tên đăng nhập");
        txtTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDangNhapActionPerformed(evt);
            }
        });

        txtMatKhauMoi.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMatKhauMoi.setLabelText("Mật khẩu mới");

        txtXacNhanMatKhau.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtXacNhanMatKhau.setLabelText("Xác nhận lại mật khẩu");

        txtMatKhauCu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMatKhauCu.setLabelText("Mật khẩu củ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtMaCapcha, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMaCapcha, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapcha, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMatKhauCu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThayDoiMatKhau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaCapcha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(lblMaCapcha, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(btnThayDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btnCapcha, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThayDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayDoiMatKhauActionPerformed
        String username = txtTenDangNhap.getText();
        String oldPassword = new String(txtMatKhauCu.getPassword());
        String newPassword = new String(txtMatKhauMoi.getPassword());
        String confirmPassword = new String(txtXacNhanMatKhau.getPassword());
        EmployeesDAO employeesDAO = new EmployeesDAO();

        // Kiểm tra các trường bỏ trống
        if (username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            ErrorBoTrong obj = new ErrorBoTrong();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
            return;
        }

        // Kiểm tra mật khẩu cũ có khớp không
        Employees employee = employeesDAO.selectById(username);
        System.out.println(employee);
        if (employee == null || !employee.getPassword().equals(oldPassword)) {
            oldPassword obj = new oldPassword();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
            return;
        }

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu mới
        if (!newPassword.equals(confirmPassword)) {
            khongTrungKhop obj = new khongTrungKhop();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
            return;
        }

        // Kiểm tra mã CAPTCHA
         if (!currentCaptchaCode.equals(txtMaCapcha.getText())) {
            CaptchaNull obj = new CaptchaNull();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
            return;
        }
        
        // Đổi mật khẩu
        try {
            employeesDAO.updatePassword(username, newPassword);
            changePassword obj = new changePassword();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi đổi mật khẩu: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnThayDoiMatKhauActionPerformed


    private void txtMaCapchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaCapchaActionPerformed
        // Lấy mã CAPTCHA người dùng nhập vào
    String enteredCaptcha = txtMaCapcha.getText();

    // Lấy mã CAPTCHA đã lưu
    String displayedCaptcha = currentCaptchaCode;

    // Kiểm tra sự trùng khớp
    if (!displayedCaptcha.equals(enteredCaptcha)) {
        CaptchaNull obj = new CaptchaNull();
        obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
        GlassPanePopup.showPopup(obj);
        return;
    }

    // Nếu mã CAPTCHA chính xác
    System.out.println("Mã CAPTCHA chính xác: " + displayedCaptcha);
    }//GEN-LAST:event_txtMaCapchaActionPerformed

    private void btnCapchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapchaActionPerformed
         currentCaptchaCode = generateCaptchaCode();
        BufferedImage captchaImage = createCaptchaImage(currentCaptchaCode);
        lblMaCapcha.setIcon(new ImageIcon(captchaImage));
    }//GEN-LAST:event_btnCapchaActionPerformed

    private void txtTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDangNhapActionPerformed

     private BufferedImage createCaptchaImage(String captchaText) {
        int width = 190;
        int height = 50;
        BufferedImage captchaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = captchaImage.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        Random random = new Random();
        g2d.setColor(Color.GRAY);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g2d.drawLine(x1, y1, x2, y2);
        }

        String[] fonts = {"Arial", "Courier New", "Times New Roman"};
        Random fontRandom = new Random();
        for (int i = 0; i < captchaText.length(); i++) {
            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            g2d.setFont(new Font(fonts[fontRandom.nextInt(fonts.length)], Font.BOLD, 30));
            g2d.drawString(String.valueOf(captchaText.charAt(i)), 20 + i * 25, 35 + random.nextInt(10));
        }

        g2d.dispose();
        return captchaImage;
    }

    private String generateCaptchaCode() {
        int length = 6;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }
        return captcha.toString();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnCapcha;
    private com.inventory.swing.Button btnThayDoiMatKhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblMaCapcha;
    private com.inventory.swing.TextField txtMaCapcha;
    private com.inventory.swing.PasswordField txtMatKhauCu;
    private com.inventory.swing.PasswordField txtMatKhauMoi;
    private com.inventory.swing.TextFieldLogin txtTenDangNhap;
    private com.inventory.swing.PasswordField txtXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables
}
