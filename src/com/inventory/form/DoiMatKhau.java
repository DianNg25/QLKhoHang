package com.inventory.form;

import com.inventory.swing.glasspanepopup.GlassPanePopup;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.inventory.utils.XJdbc;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DoiMatKhau extends javax.swing.JPanel {

    private String otp;
    private boolean otpSent = false;  // Để kiểm tra xem mã OTP đã được gửi hay chưa
    private boolean otpVerified = false;  // Để kiểm tra xem mã OTP đã được xác nhận hay chưa
    private Instant otpGeneratedTime;
    private final Duration OTP_VALIDITY_DURATION = Duration.ofMinutes(1);

    public DoiMatKhau() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnOK = new com.inventory.swing.Button();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new com.inventory.swing.TextField();
        txtXacNhanMa = new com.inventory.swing.TextField();
        lblCheckPass = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        txtUserName = new com.inventory.swing.TextField();
        txtMatKhauMoi = new com.inventory.swing.TextField();
        btnGuiMa = new com.inventory.swing.Button();
        btnXacNhan = new com.inventory.swing.Button();
        btnChangePassword = new com.inventory.swing.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));
        setPreferredSize(new java.awt.Dimension(630, 310));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("QUÊN MẬT KHẨU");

        btnOK.setBackground(new java.awt.Color(204, 0, 0));
        btnOK.setForeground(new java.awt.Color(255, 255, 255));
        btnOK.setText("Hủy");
        btnOK.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Mã xác nhận");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("Tên đăng nhập");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setText("Mật khẩu mới");

        txtEmail.setBackground(new java.awt.Color(190, 213, 243));
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtXacNhanMa.setBackground(new java.awt.Color(190, 213, 243));
        txtXacNhanMa.setForeground(new java.awt.Color(255, 255, 255));
        txtXacNhanMa.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N

        lblCheckPass.setPreferredSize(new java.awt.Dimension(0, 16));

        lblStatus.setBackground(new java.awt.Color(255, 255, 255));
        lblStatus.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N

        txtUserName.setBackground(new java.awt.Color(190, 213, 243));
        txtUserName.setForeground(new java.awt.Color(255, 255, 255));
        txtUserName.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N

        txtMatKhauMoi.setBackground(new java.awt.Color(190, 213, 243));
        txtMatKhauMoi.setForeground(new java.awt.Color(255, 255, 255));
        txtMatKhauMoi.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtMatKhauMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauMoiActionPerformed(evt);
            }
        });

        btnGuiMa.setBackground(new java.awt.Color(190, 213, 243));
        btnGuiMa.setText("Gửi mã");
        btnGuiMa.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnGuiMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiMaActionPerformed(evt);
            }
        });

        btnXacNhan.setBackground(new java.awt.Color(190, 213, 243));
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnChangePassword.setBackground(new java.awt.Color(0, 153, 0));
        btnChangePassword.setForeground(new java.awt.Color(255, 255, 255));
        btnChangePassword.setText("Lưu");
        btnChangePassword.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(138, 138, 138))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(btnGuiMa, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(txtXacNhanMa, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCheckPass, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuiMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtXacNhanMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCheckPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_btnOKActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed

    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtMatKhauMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauMoiActionPerformed

    }//GEN-LAST:event_txtMatKhauMoiActionPerformed

    private void btnGuiMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiMaActionPerformed
        String email = txtEmail.getText().trim();

        // Kiểm tra email không bị bỏ trống
        if (email.isEmpty()) {
            lblStatus.setText("Email không được để trống!");
            lblStatus.setForeground(Color.RED);
            return;
        }

        // Kiểm tra email hợp lệ
        if (!isValidEmail(email)) {
            lblStatus.setText("Địa chỉ email không hợp lệ!");
            lblStatus.setForeground(Color.RED);
            return;
        }

        // Kiểm tra thời gian hết hạn của OTP trước đó
        if (otp != null && otpGeneratedTime != null) {
            Instant currentTime = Instant.now();
            Duration timeElapsed = Duration.between(otpGeneratedTime, currentTime);
            if (timeElapsed.compareTo(OTP_VALIDITY_DURATION) < 0) {
                lblStatus.setText("Bạn phải chờ mã OTP hiện tại hết hạn trước khi gửi mã mới!");
                lblStatus.setForeground(Color.RED);
                return;
            }
        }

        otp = generateOTP();
        otpGeneratedTime = Instant.now();
        boolean success = sendOTPEmail(email, otp);
        if (success) {
            lblStatus.setText("OTP đã được gửi thành công!");
            lblStatus.setForeground(Color.GREEN);
            otpSent = true;  // Đặt trạng thái là đã gửi OTP
        } else {
            lblStatus.setText("Lỗi khi gửi OTP!");
            lblStatus.setForeground(Color.RED);
        }


    }//GEN-LAST:event_btnGuiMaActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        String enteredOTP = txtXacNhanMa.getText().trim();

        // Kiểm tra mã OTP không bị bỏ trống
        if (enteredOTP.isEmpty()) {
            lblStatus.setText("Mã OTP không được để trống!");
            lblStatus.setForeground(Color.RED);
            return;
        }

        if (enteredOTP.equals(otp)) {
            lblStatus.setText("Xác nhận OTP thành công!");
            lblStatus.setForeground(Color.GREEN);
            otpVerified = true;  // Đặt trạng thái là mã OTP đã được xác nhận
            btnChangePassword.setEnabled(true);  // Cho phép thay đổi mật khẩu
        } else {
            lblStatus.setText("Mã OTP không chính xác!");
            lblStatus.setForeground(Color.RED);
            otpVerified = false;  // Đặt trạng thái là mã OTP không được xác nhận
            btnChangePassword.setEnabled(false);  // Không cho phép thay đổi mật khẩu
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        String username = txtUserName.getText().trim();
        String newPassword = txtMatKhauMoi.getText().trim();

        // Kiểm tra tên người dùng không bị bỏ trống
        if (username.isEmpty()) {
            lblCheckPass.setText("Tên người dùng không được để trống!");
            lblCheckPass.setForeground(Color.RED);
            return;
        }

        // Kiểm tra mật khẩu mới không bị bỏ trống
        if (newPassword.isEmpty()) {
            lblCheckPass.setText("Mật khẩu mới không được để trống!");
            lblCheckPass.setForeground(Color.RED);
            return;
        }

        if (!otpSent) {
            lblCheckPass.setText("Bạn phải gửi mã OTP trước khi thay đổi mật khẩu!");
            lblCheckPass.setForeground(Color.RED);
            return;
        }

        if (!otpVerified) {
            lblCheckPass.setText("Bạn phải xác nhận mã OTP trước khi thay đổi mật khẩu!");
            lblCheckPass.setForeground(Color.RED);
            return;
        }

        if (checkUserExists(username)) {
            updatePassword(username, newPassword);
            lblCheckPass.setText("Mật khẩu đã được thay đổi thành công!");
            lblCheckPass.setForeground(Color.GREEN);
        } else {
            lblCheckPass.setText("Tên người dùng không tồn tại!");
            lblCheckPass.setForeground(Color.RED);
        }
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    private boolean checkUserExists(String username) {
        String sql = "SELECT COUNT(*) FROM Employees WHERE Username=?";
        try (ResultSet rs = XJdbc.query(sql, username)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
///// thay đổi mật khẩu mới

    public void updatePassword(String username, String newPassword) {
        String sql = "UPDATE Employees SET Password=? WHERE Username=?";
        try {
            XJdbc.update(sql, newPassword, username);
        } catch (RuntimeException e) {
            e.printStackTrace();
            lblCheckPass.setText("Có lỗi xảy ra khi thay đổi mật khẩu!");
            lblCheckPass.setForeground(Color.RED);
        }
    }

    private String getEmailByUsername(String username) {
        String sql = "SELECT Email FROM Employees WHERE Username=?";
        try (ResultSet rs = XJdbc.query(sql, username)) {
            if (rs.next()) {
                return rs.getString("Email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void eventOK(ActionListener event) {
        btnOK.addActionListener(event);
    }

    private String generateOTP() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    private boolean sendOTPEmail(String recipient, String otp) {
//        String host = "smtp.gmail.com";
        final String senderEmail = "nguyenghihieuminh2004@gmail.com";
        final String senderPassword = "bxbbblubiehsmtjt";

        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");

        Session session = Session.getInstance(p, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);

            Transport.send(message);
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }

    }

    //// bắt lỗi mail
    private boolean isValidEmail(String email) {
        // Regex for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(graphics);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnChangePassword;
    private com.inventory.swing.Button btnGuiMa;
    private com.inventory.swing.Button btnOK;
    private com.inventory.swing.Button btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblCheckPass;
    private javax.swing.JLabel lblStatus;
    private com.inventory.swing.TextField txtEmail;
    private com.inventory.swing.TextField txtMatKhauMoi;
    private com.inventory.swing.TextField txtUserName;
    private com.inventory.swing.TextField txtXacNhanMa;
    // End of variables declaration//GEN-END:variables

}
