/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.inventory.main;

import com.inventory.utils.XJdbc;
import com.inventory.form.Model_Menu;
import com.inventory.message.*;
import com.inventory.swing.PanelBorder;
import com.inventory.swing.glasspanepopup.GlassPanePopup;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends javax.swing.JFrame {

    private JTextField userTextField;
    private JPasswordField passwordField;

    public Login() {
        GlassPanePopup.install(this);
        // Thiết lập JFrame
        setTitle("Đăng Nhập");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Bỏ nút Java góc trái
        setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình

        // Sử dụng PanelBorder
        PanelBorder panelBorder = new PanelBorder();
        panelBorder.setBackground(new Color(0, 0, 0, 0)); // Màu nền cho panel với độ trong suốt
        setContentPane(panelBorder);
        panelBorder.setLayout(new BorderLayout());

        // Tạo JPanel cho hình ảnh
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBackground(Color.WHITE); // Đặt màu nền cho panel là màu trắng

        JLabel imageLabel = new JLabel();

        // Sử dụng Model_Menu để lấy icon
        Model_Menu menu = new Model_Menu("KhoHang-400x400", "Tổng quan", Model_Menu.MenuType.MENU);
        ImageIcon imageIcon = (ImageIcon) menu.toIcon();
        if (imageIcon.getIconWidth() == -1) {
            System.out.println("Hình ảnh không tồn tại hoặc đường dẫn không đúng: " + "/com/inventory/icon/Login_bg.png");
        } else {
            imageLabel.setIcon(imageIcon);
        }
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Tạo JPanel cho form đăng nhập
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null); // Sử dụng null layout để đặt vị trí các thành phần
        loginPanel.setBackground(Color.WHITE);

        // Tiêu đề
        JLabel titleLabel = new JLabel("ĐĂNG NHẬP VÀO TÀI KHOẢN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(50, 20, 300, 30);
        titleLabel.setForeground(new Color(0x2e536d)); // Đặt màu cho tiêu đề
        loginPanel.add(titleLabel);

        // Tên đăng nhập
        JLabel userLabel = new JLabel("Tên đăng nhập");
        userLabel.setBounds(50, 70, 150, 30);
        loginPanel.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(50, 100, 300, 30);
        userTextField.setForeground(new Color(0x2e536d)); // Đặt màu chữ
        loginPanel.add(userTextField);

        // Mật khẩu
        JLabel passwordLabel = new JLabel("Mật khẩu");
        passwordLabel.setBounds(50, 150, 150, 30);
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 180, 300, 30);
        passwordField.setForeground(new Color(0x2e536d)); // Đặt màu chữ
        loginPanel.add(passwordField);

        // Ghi nhớ tôi
        JCheckBox rememberMeCheckBox = new JCheckBox("Ghi nhớ tôi");
        rememberMeCheckBox.setBounds(50, 220, 150, 30);
        loginPanel.add(rememberMeCheckBox);

        // Quên mật khẩu
        JLabel forgotPasswordLabel = new JLabel("Quên mật khẩu?");
        forgotPasswordLabel.setBounds(220, 220, 150, 30);
        forgotPasswordLabel.setForeground(new Color(0x2e536d));
        loginPanel.add(forgotPasswordLabel);

        // Nút Đăng Nhập
        JButton loginButton = new JButton("ĐĂNG NHẬP");
        loginButton.setBounds(50, 270, 300, 40);
        loginButton.setBackground(new Color(0x2e536d)); // Đặt màu nền cho nút
        loginButton.setForeground(Color.WHITE); // Đặt màu chữ cho nút
        loginPanel.add(loginButton);

        // Thêm sự kiện cho nút đăng nhập
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());

                // Kiểm tra nếu tên đăng nhập hoặc mật khẩu bị bỏ trống
                if (username.isEmpty() || password.isEmpty()) {
//                    JOptionPane.showMessageDialog(Login.this, "Vui lòng điền đầy đủ tên đăng nhập và mật khẩu", "Lỗi nhập liệu", JOptionPane.WARNING_MESSAGE);
                    InputError obj = new InputError();
                    obj.eventOK(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            GlassPanePopup.closePopupLast();
                        }
                    });
                    GlassPanePopup.showPopup(obj);
                    return;
                }

                // Kiểm tra đăng nhập từ SQL
                boolean loginSuccess = checkLogin(username, password);
                if (loginSuccess) {
                    // Chuyển trang hoặc hiển thị progress bar
                    openMainPage();
                } else {
                    // Hiển thị thông báo lỗi
//                    JOptionPane.showMessageDialog(Login.this, "Tên đăng nhập hoặc mật khẩu không đúng", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
                    LoginError obj = new LoginError();
                    obj.eventOK(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            GlassPanePopup.closePopupLast();
                        }
                    });
                    GlassPanePopup.showPopup(obj);
                }
            }
        });

        // Thêm sự kiện khi nhấn Enter trong các trường nhập liệu
        KeyAdapter enterKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        };
        userTextField.addKeyListener(enterKeyAdapter);
        passwordField.addKeyListener(enterKeyAdapter);

        // Thêm các panel vào PanelBorder
        panelBorder.add(imagePanel, BorderLayout.WEST);
        panelBorder.add(loginPanel, BorderLayout.CENTER);
    }

    // Phương thức kiểm tra đăng nhập từ SQL
    private boolean checkLogin(String username, String password) {
        boolean isValidUser = false;

        try {
            String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
            ResultSet rs = XJdbc.query(sql, username, password);

            if (rs.next()) {
                isValidUser = true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValidUser;
    }

    // Phương thức mở trang chính
    private void openMainPage() {
        try {
            LoadingScreen loadingScreen = new LoadingScreen();
            loadingScreen.setVisible(true);
            dispose(); // Đóng cửa sổ đăng nhập
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể mở trang chính: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.inventory.swing.PanelBorder();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 876, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
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

    /**
     * @param args the command line arguments
     */
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
