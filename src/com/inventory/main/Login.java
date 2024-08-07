package com.inventory.main;

import com.inventory.dao.EmployeesDAO;
import com.inventory.entity.Employees;
import com.inventory.form.DoiMatKhau;
import java.sql.PreparedStatement;
import com.inventory.utils.XJdbc;
import com.inventory.message.*;
import com.inventory.swing.glasspanepopup.GlassPanePopup;
import com.inventory.utils.Auth;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class Login extends javax.swing.JFrame {

    public static String loggedInUsername;
    public static String employeeId;
    private Preferences prefs;
    

    public Login() {
        initComponents();
        GlassPanePopup.install(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        userTextField.addKeyListener(enterKeyAdapter);
        passwordField.addKeyListener(enterKeyAdapter);
        getGlassPane().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    GlassPanePopup.closePopupLast();
                    requestFocusInWindow();
                }
            }
        });
        prefs = Preferences.userNodeForPackage(Login.class);
        loadCredentials();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.inventory.swing.PanelBorder();
        panelBorder2 = new com.inventory.swing.PanelBorder();
        userTextField = new com.inventory.swing.TextFieldLogin();
        passwordField = new com.inventory.swing.PasswordField();
        cboGhiNho = new javax.swing.JCheckBox();
        btnLogin = new com.inventory.swing.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelBorder3 = new com.inventory.swing.PanelBorder();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        userTextField.setDisabledTextColor(new java.awt.Color(0, 255, 85));
        userTextField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        userTextField.setLabelText("Tên đăng nhập");

        passwordField.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        passwordField.setLabelText("Mật khẩu");

        cboGhiNho.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        cboGhiNho.setText("Ghi nhớ tôi");
        cboGhiNho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboGhiNhoMouseClicked(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(83, 131, 164));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Đăng Nhập");
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(83, 131, 164));
        jLabel1.setText("ĐĂNG NHẬP VÀO TÀI KHOẢN");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel3.setText("Quên mật khẩu?");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(cboGhiNho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))))
                .addGap(0, 57, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboGhiNho)
                    .addComponent(jLabel3))
                .addGap(30, 30, 30)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventory/icon/KhoHang-400x400.png"))); // NOI18N

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            InputError obj = new InputError();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
            return;
        }

        try {
            Employees loggedInEmployee = checkLogin(username, password);
            if (loggedInEmployee != null) {
                Auth.user = loggedInEmployee; // Lưu thông tin đăng nhập vào Auth
                System.out.println("Position của tài khoản đang đăng nhập: " + Auth.user.getPosition());

                if (cboGhiNho.isSelected()) {
                    saveCredentials(username, password);
                } else {
                    clearCredentials();
                }

                openMainPage();
            } else {
                LoginError obj = new LoginError();
                obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                GlassPanePopup.showPopup(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void cboGhiNhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboGhiNhoMouseClicked
        // TODO add your handling code here:
        if (cboGhiNho.isSelected()) {
            saveCredentials(userTextField.getText(), new String(passwordField.getPassword()));
        } else {
            clearCredentials();
        }
    }//GEN-LAST:event_cboGhiNhoMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
                DoiMatKhau obj = new DoiMatKhau();
                obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_jLabel3MouseClicked

    private final KeyAdapter enterKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                btnLoginActionPerformed(null);
            }
        }
    };

    private Employees checkLogin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Employees WHERE Username = ? AND Password = ?";
        try (PreparedStatement stmt = XJdbc.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Employees emp = new Employees();
                    emp.setEmployeeID(rs.getString("EmployeeID"));
                    emp.setUsername(rs.getString("Username"));
                    emp.setFullName(rs.getString("FullName"));
                    emp.setPhone(rs.getInt("Phone"));
                    emp.setEmail(rs.getString("Email"));
                    emp.setPassword(rs.getString("Password"));
                    emp.setPosition(rs.getByte("Position"));
                    emp.setImage(rs.getString("Image"));
                    loggedInUsername = username;
                    employeeId = emp.getEmployeeID();
                    return emp;
                }
            }
        }
        return null; // Không tìm thấy nhân viên
    }

    private String getEmployeeId(String username) {
        String empId = "";
        try (PreparedStatement stmt = XJdbc.prepareStatement("SELECT EmployeeID FROM Employees WHERE Username = ?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empId = rs.getString("EmployeeID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empId;
    }

    // Phương thức mở trang chính
    private void openMainPage() {
        try {
            if (Auth.isLogin()) {
                System.out.println("Position của tài khoản đang đăng nhập: " + Auth.user.getPosition());

                if (Auth.user.getPosition() == 1) {
                    Main main = new Main();
                    main.setVisible(true);
                } else {
                    MainEmployee mainEmployee = new MainEmployee();
                    mainEmployee.setVisible(true);
                }
            } else {
                System.out.println("Chưa có tài khoản đăng nhập");
            }

            dispose(); // Đóng cửa sổ đăng nhập
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể mở trang chính: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadCredentials() {
        String username = prefs.get("username", "");
        String password = prefs.get("password", "");

        userTextField.setText(username);
        passwordField.setText(password);
        cboGhiNho.setSelected(!username.isEmpty() && !password.isEmpty());
    }

    private void saveCredentials(String username, String password) {
        prefs.put("username", username);
        prefs.put("password", password);
    }

    private void clearCredentials() {
        prefs.remove("username");
        prefs.remove("password");
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnLogin;
    private javax.swing.JCheckBox cboGhiNho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private com.inventory.swing.PanelBorder panelBorder1;
    private com.inventory.swing.PanelBorder panelBorder2;
    private com.inventory.swing.PanelBorder panelBorder3;
    private com.inventory.swing.PasswordField passwordField;
    private com.inventory.swing.TextFieldLogin userTextField;
    // End of variables declaration//GEN-END:variables
}
