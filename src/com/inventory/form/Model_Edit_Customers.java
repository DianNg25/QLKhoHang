package com.inventory.form;

import com.inventory.entity.CustomersTable;
import com.inventory.swing.glasspanepopup.GlassPanePopup;
import com.inventory.utils.XJdbc;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nguyen
 */
public class Model_Edit_Customers extends javax.swing.JPanel {

    private JDialog errorDialog;

    /**
     * Creates new form Model_Add_Product
     */
    public Model_Edit_Customers() {
        initComponents();
        setOpaque(false);
    }

    private void updateCustomersData() {
        java.sql.Connection connection = null;
        java.sql.PreparedStatement statement = null;
        java.sql.ResultSet resultSet = null;

        try {
            // Lấy dữ liệu từ các trường nhập liệu
            String id = txtMaKH.getText() != null ? txtMaKH.getText().trim() : "";
            String name = txtTenKH.getText() != null ? txtTenKH.getText().trim() : "";
            String address = txtDiaChi.getText() != null ? txtDiaChi.getText().trim() : "";
            String phone = txtSDT.getText() != null ? txtSDT.getText().trim() : "";

            // Kiểm tra dữ liệu trống
            if (id.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                return;
            }

            // Kiểm tra định dạng số điện thoại (ví dụ: phải bắt đầu bằng số 0 và có 10 chữ số)
            if (!phone.matches("^(0)\\d{9}$")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ. Nó phải bắt đầu bằng số 0 có 10 chữ số.");
                return;
            }

            // Kết nối đến cơ sở dữ liệu
            connection = XJdbc.getConnection();

            // Kiểm tra xem ID đã tồn tại hay chưa
            String checkIdQuery = "SELECT COUNT(*) FROM Customers WHERE CustomerID = ?";
            statement = connection.prepareStatement(checkIdQuery);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (!resultSet.next() || resultSet.getInt(1) == 0) {
                JOptionPane.showMessageDialog(null, "ID không tồn tại. Vui lòng kiểm tra lại.");
                return;
            }

            // Chuẩn bị câu lệnh SQL để cập nhật dữ liệu
            String query = "UPDATE Customers SET CustomerName = ?, Address = ?, Phone = ? WHERE CustomerID = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phone);
            statement.setString(4, id);

            // Thực hiện lệnh SQL
            int rowsAffected = statement.executeUpdate();

            // Kiểm tra kết quả và hiển thị thông báo
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật Thành Công!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật Thất Bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpLoai = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnOK = new com.inventory.swing.Button();
        button2 = new com.inventory.swing.Button();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaKH = new com.inventory.swing.TextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenKH = new com.inventory.swing.TextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new com.inventory.swing.TextField();
        txtSDT = new com.inventory.swing.TextField();

        setBackground(new java.awt.Color(39, 74, 89));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(32, 137, 173));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        jPanel1.setPreferredSize(new java.awt.Dimension(166, 50));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Sửa Khách Hàng");
        jLabel9.setPreferredSize(new java.awt.Dimension(166, 30));
        jPanel1.add(jLabel9, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(32, 137, 173));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        btnOK.setBackground(new java.awt.Color(27, 66, 139));
        btnOK.setForeground(new java.awt.Color(255, 255, 255));
        btnOK.setText("Sửa");
        btnOK.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(255, 0, 0));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Hủy");
        button2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(370, Short.MAX_VALUE)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(32, 137, 173));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã Khách Hàng");

        txtMaKH.setPreferredSize(new java.awt.Dimension(25, 40));
        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên Khách Hàng");

        txtTenKH.setPreferredSize(new java.awt.Dimension(25, 40));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Địa chỉ:");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Số điện thoại:");

        txtDiaChi.setPreferredSize(new java.awt.Dimension(25, 40));

        txtSDT.setPreferredSize(new java.awt.Dimension(25, 40));
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_button2ActionPerformed

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        updateCustomersData();

    }//GEN-LAST:event_btnOKActionPerformed

    public void setCustomersData(CustomersTable customer) {
        if (customer == null) {
            return;
        } else {
            txtMaKH.setText(customer.getCustomerID());
            txtTenKH.setText(customer.getCustomerName());
            txtDiaChi.setText(customer.getAddress());
            txtSDT.setText(customer.getPhone());

            // Nếu có thêm trường khác cho khách hàng, hãy thiết lập ở đây
            // Ví dụ: Nếu có trường email cho khách hàng
            // txtEmail.setText(customer.getEmail());
        }
    }

    private void clearFields() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnOK;
    private com.inventory.swing.Button button2;
    private javax.swing.ButtonGroup grpLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.inventory.swing.TextField txtDiaChi;
    private com.inventory.swing.TextField txtMaKH;
    private com.inventory.swing.TextField txtSDT;
    private com.inventory.swing.TextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
