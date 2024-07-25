/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.utils.DatabaseUtils;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.SwingUtilities;

/**
 *
 * @author WINDOWS
 */
public class Model_Add_Employees extends javax.swing.JPanel {

    /**
     * Creates new form Model_Add_Employees
     */
    public Model_Add_Employees() {
        initComponents();
    }

    @SuppressWarnings("empty-statement")
    private void addTable() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // Lấy dữ liệu từ các trường nhập liệu
            String id = txtId.getText().trim();
            String hoten = txtHoTen.getText().trim();
            String email = txtEmail.getText().trim();
            String sodt = txtSdt.getText().trim();
            byte gender = jRadioButton2.isSelected() ? (byte) 1 : (byte) 0; // 1 nếu là Admin, 0 nếu là User
            String tk = txtTaiKhoan.getText().trim();
            String mk = textField7.getText().trim(); // Đảm bảo mật khẩu là String
            String hinh = txtHinh.getText().trim();

            // Kiểm tra dữ liệu trống
            if (id.isEmpty() || hoten.isEmpty() || email.isEmpty() || sodt.isEmpty() || tk.isEmpty() || mk.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                return;
            }

            // Kiểm tra định dạng email
            if (!email.matches("^[\\w-_.+]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
                JOptionPane.showMessageDialog(null, "Địa chỉ email không hợp lệ.");
                return;
            }

            // Kiểm tra định dạng số điện thoại (phải bắt đầu bằng 03 hoặc 09 và có 10 chữ số)
            if (!sodt.matches("^(0)\\d{9}$")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ. Nó phải bắt đầu bằng số 0 có 10 chữ số.");
                return;
            }

            // Kiểm tra mật khẩu (giả định mật khẩu phải dài ít nhất 6 ký tự)
            if (mk.length() < 6) {
                JOptionPane.showMessageDialog(null, "Mật khẩu phải có ít nhất 6 ký tự.");
                return;
            }

            // Kết nối đến cơ sở dữ liệu
            connection = DatabaseUtils.getConnection();

            // Kiểm tra xem ID đã tồn tại hay chưa
            String checkIdQuery = "SELECT COUNT(*) FROM Employees WHERE EmployeeID = ?";
            statement = connection.prepareStatement(checkIdQuery);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "ID đã tồn tại. Vui lòng chọn ID khác.");
                return;
            }

            // Chuẩn bị câu lệnh SQL để chèn dữ liệu
            String query = "INSERT INTO Employees (EmployeeID, Username, FullName, Phone, Email, Password, Position, Image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, tk);
            statement.setString(3, hoten);
            statement.setString(4, sodt);
            statement.setString(5, email);
            statement.setString(6, mk);
            statement.setByte(7, gender);
            statement.setString(8, hinh);

            // Thực hiện lệnh SQL
            int rowsAffected = statement.executeUpdate();

            // Kiểm tra kết quả và hiển thị thông báo
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Thêm Thành Công!");
                Clearform();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm Thất Bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void Clearform() {
        txtId.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtSdt.setText("");
        role.clearSelection(); // Nếu grpGt là ButtonGroup
        textField7.setText(""); // Nếu textField7 là trường để nhập địa chỉ
        txtHinh.setText(""); // Hoặc bạn có thể thiết lập ảnh mặc định nếu cần
        txtTaiKhoan.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        role = new javax.swing.ButtonGroup();
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
        txtId = new com.inventory.swing.TextField();
        txtHoTen = new com.inventory.swing.TextField();
        txtSdt = new com.inventory.swing.TextField();
        txtEmail = new com.inventory.swing.TextField();
        txtTaiKhoan = new com.inventory.swing.TextField();
        panelBorder1 = new com.inventory.swing.PanelBorder();
        txtHinh = new javax.swing.JLabel();
        textField7 = new com.inventory.swing.TextField();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setPreferredSize(new java.awt.Dimension(700, 550));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(32, 137, 173));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        jPanel1.setPreferredSize(new java.awt.Dimension(680, 50));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("THÊM NHÂN VIÊN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(32, 137, 173));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel2.setPreferredSize(new java.awt.Dimension(680, 80));

        button1.setBackground(new java.awt.Color(255, 0, 0));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Hủy");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(36, 33));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        btnOK.setBackground(new java.awt.Color(27, 66, 139));
        btnOK.setForeground(new java.awt.Color(255, 255, 255));
        btnOK.setText("Thêm");
        btnOK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnOK.setPreferredSize(new java.awt.Dimension(36, 33));
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(488, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(32, 137, 173));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(680, 300));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Họ và tên");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Số điện thoại");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Chức vụ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tài khoản ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID nhân viên");

        txtId.setPreferredSize(new java.awt.Dimension(25, 40));

        txtHoTen.setPreferredSize(new java.awt.Dimension(25, 40));

        txtSdt.setPreferredSize(new java.awt.Dimension(25, 40));

        txtEmail.setPreferredSize(new java.awt.Dimension(25, 40));

        txtTaiKhoan.setPreferredSize(new java.awt.Dimension(25, 40));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        txtHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );

        textField7.setPreferredSize(new java.awt.Dimension(25, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Mật khẩu");

        role.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Admin");

        role.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("User");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(19, 19, 19)
                                .addComponent(jRadioButton2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 3, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        addTable();
    }//GEN-LAST:event_btnOKActionPerformed

    private void txtHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHinhMouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Lấy tệp hình ảnh đã chọn
            File selectedFile = fileChooser.getSelectedFile();

            // Tạo ImageIcon từ tệp hình ảnh
            ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());

            // Thay đổi kích thước hình ảnh (tùy chọn)
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(txtHinh.getWidth(), txtHinh.getHeight(), Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);

            // Cập nhật JLabel với hình ảnh
            txtHinh.setIcon(imageIcon);

            // Cập nhật đường dẫn hình ảnh nếu cần
            // txtHinh.setText(selectedFile.getAbsolutePath());
        }
    }//GEN-LAST:event_txtHinhMouseClicked
    public void eventOK(ActionListener event) {
        btnOK.addActionListener(event);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnOK;
    private com.inventory.swing.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private com.inventory.swing.PanelBorder panelBorder1;
    private javax.swing.ButtonGroup role;
    private com.inventory.swing.TextField textField7;
    private com.inventory.swing.TextField txtEmail;
    private javax.swing.JLabel txtHinh;
    private com.inventory.swing.TextField txtHoTen;
    private com.inventory.swing.TextField txtId;
    private com.inventory.swing.TextField txtSdt;
    private com.inventory.swing.TextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
