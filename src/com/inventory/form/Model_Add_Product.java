/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.dao.ProductsDAO;
import com.inventory.dao.SuppliersDAO;
import com.inventory.entity.Products;
import com.inventory.entity.Suppliers;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.inventory.message.*;
import com.inventory.swing.glasspanepopup.GlassPanePopup;
import com.inventory.utils.XJdbc;

/**
 *
 * @author Nguyen
 */
public class Model_Add_Product extends javax.swing.JPanel {

    private SuppliersDAO suppliersDAO;

    /**
     * Creates new form Model_Add_Product
     */
    public Model_Add_Product() {
        initComponents();
        setOpaque(false);
        populateComboBox();
        loadSupplierIDsIntoComboBox();

        this.suppliersDAO = new SuppliersDAO();
//                fillComboBox();

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
        txtMaSanPham = new com.inventory.swing.TextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenSanPham = new com.inventory.swing.TextField();
        jLabel3 = new javax.swing.JLabel();
        txtGia = new com.inventory.swing.TextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rdoMini = new javax.swing.JRadioButton();
        rdoNho = new javax.swing.JRadioButton();
        rdoVua = new javax.swing.JRadioButton();
        rdoLon = new javax.swing.JRadioButton();
        cboMau = new com.inventory.swing.ComboBoxSuggestion();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboBoxProductID = new com.inventory.swing.ComboBoxSuggestion();

        setBackground(new java.awt.Color(39, 74, 89));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(792, 477));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(32, 137, 173));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        jPanel1.setPreferredSize(new java.awt.Dimension(166, 50));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("THÊM SẢN PHẨM");
        jLabel9.setPreferredSize(new java.awt.Dimension(166, 30));
        jPanel1.add(jLabel9, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(32, 137, 173));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        btnOK.setBackground(new java.awt.Color(27, 66, 139));
        btnOK.setForeground(new java.awt.Color(255, 255, 255));
        btnOK.setText("Thêm");
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
                .addContainerGap(549, Short.MAX_VALUE)
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
        jPanel3.setPreferredSize(new java.awt.Dimension(700, 300));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã sản phẩm");

        txtMaSanPham.setPreferredSize(new java.awt.Dimension(25, 40));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên sản phẩm");

        txtTenSanPham.setPreferredSize(new java.awt.Dimension(25, 40));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Giá");

        txtGia.setPreferredSize(new java.awt.Dimension(25, 40));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Màu");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Loại");

        grpLoai.add(rdoMini);
        rdoMini.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rdoMini.setForeground(new java.awt.Color(255, 255, 255));
        rdoMini.setText("Mini (0,3 Kg)");

        grpLoai.add(rdoNho);
        rdoNho.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rdoNho.setForeground(new java.awt.Color(255, 255, 255));
        rdoNho.setText("Nhỏ (6 Kg)");

        grpLoai.add(rdoVua);
        rdoVua.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rdoVua.setForeground(new java.awt.Color(255, 255, 255));
        rdoVua.setText("Vừa (12 Kg)");

        grpLoai.add(rdoLon);
        rdoLon.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        rdoLon.setForeground(new java.awt.Color(255, 255, 255));
        rdoLon.setText("Lớn (45 Kg)");

        cboMau.setPreferredSize(new java.awt.Dimension(151, 38));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mã NCC");

        comboBoxProductID.setPreferredSize(new java.awt.Dimension(151, 30));
        comboBoxProductID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxProductIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(5, 5, 5))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxProductID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboMau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(rdoMini)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoVua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoLon)
                        .addGap(0, 94, Short.MAX_VALUE)))
                .addGap(63, 63, 63))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(rdoMini)
                    .addComponent(rdoNho)
                    .addComponent(rdoVua)
                    .addComponent(rdoLon))
                .addGap(15, 15, 15))
        );

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_button2ActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        addProducts();
        clearForm();
    }//GEN-LAST:event_btnOKActionPerformed

    private void comboBoxProductIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxProductIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxProductIDActionPerformed

    private void loadSupplierIDsIntoComboBox() {
        String query = "SELECT SupplierID FROM Suppliers";
        try (java.sql.Connection conn = XJdbc.getConnection(); java.sql.PreparedStatement pstmt = conn.prepareStatement(query); java.sql.ResultSet rs = pstmt.executeQuery()) {

            // Xóa tất cả các phần tử hiện có trong combobox trước khi thêm mới
            comboBoxProductID.removeAllItems();

            // Thêm các SupplierID vào JComboBox
            while (rs.next()) {
                String supplierID = rs.getString("SupplierID");
                comboBoxProductID.addItem(supplierID);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu nhà cung cấp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addProducts() {
        // Lấy dữ liệu từ các trường nhập liệu
        String maSP = txtMaSanPham.getText().trim();
        String tenSP = txtTenSanPham.getText().trim();
        String giaSP = txtGia.getText().trim();
        String mau = cboMau.getSelectedItem().toString().trim(); // Sử dụng getSelectedItem() thay vì getText()

        // Lấy SupplierID từ comboBoxProductID
        String supplierID = (String) comboBoxProductID.getSelectedItem();

        // Kiểm tra nếu các trường nhập liệu không được để trống
        if (maSP.isEmpty() || tenSP.isEmpty() || giaSP.isEmpty() || supplierID == null) {
            ErrorBoTrong obj = new ErrorBoTrong();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
            return;
        }

        // Kiểm tra xem mã sản phẩm có bị trùng không
        try (java.sql.Connection conn = XJdbc.getConnection()) {
            String checkProductQuery = "SELECT COUNT(*) FROM Products WHERE ProductID = ?";
            try (java.sql.PreparedStatement pstmtCheck = conn.prepareStatement(checkProductQuery)) {
                pstmtCheck.setString(1, maSP);
                try (java.sql.ResultSet rs = pstmtCheck.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        IDError obj = new IDError();
                        obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                        GlassPanePopup.showPopup(obj);
                        return;
                    }
                }
            }

            // Kiểm tra nếu giá sản phẩm phải là số và không được nhỏ hơn 0
            double gia;
            try {
                gia = Double.parseDouble(giaSP);
                if (gia < 0) {
                    EditProducts_ErrorPrice obj = new EditProducts_ErrorPrice();
                    obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                    GlassPanePopup.showPopup(obj);
                    return;
                }
            } catch (NumberFormatException e) {
                EditProducts_PriceNumber obj = new EditProducts_PriceNumber();
                obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                GlassPanePopup.showPopup(obj);
                return;
            }

            // Xác định loại sản phẩm từ radio buttons
            String loai = "";
            if (rdoMini.isSelected()) {
                loai = "Mini";
            } else if (rdoNho.isSelected()) {
                loai = "Nhỏ";
            } else if (rdoVua.isSelected()) {
                loai = "Vừa";
            } else if (rdoLon.isSelected()) {
                loai = "Lớn";
            }

            // Đặt trạng thái sản phẩm mặc định là "Hoạt động"
            String trangThai = "Hoạt động";

            // Câu lệnh SQL để chèn dữ liệu vào bảng Products, với Quantity mặc định là 1
            String insertProductQuery = "INSERT INTO Products (ProductID, ProductName, Price, Color, Weight, Status, SupplierID, Quantity) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (java.sql.PreparedStatement pstmtInsert = conn.prepareStatement(insertProductQuery)) {
                pstmtInsert.setString(1, maSP);
                pstmtInsert.setString(2, tenSP);
                pstmtInsert.setDouble(3, gia);
                pstmtInsert.setString(4, mau);
                pstmtInsert.setString(5, loai);
                pstmtInsert.setString(6, trangThai);
                pstmtInsert.setString(7, supplierID);
                pstmtInsert.setInt(8, 1); // Đặt Quantity mặc định là 1

                // Thực thi câu lệnh chèn dữ liệu
                int rowsInserted = pstmtInsert.executeUpdate();

                // Thông báo thành công
                if (rowsInserted > 0) {
                    AddThanhCong obj = new AddThanhCong();
                    obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                    GlassPanePopup.showPopup(obj);
                } else {
                    // Xử lý nếu không có bản ghi nào được chèn
                    // Có thể thêm thông báo lỗi hoặc xử lý khác nếu cần
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
    }

    private void clearForm() {
        // Xóa văn bản trong các trường nhập liệu
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtGia.setText("");
        // Đặt lại các hộp thoại lựa chọn (ComboBox)
        cboMau.setSelectedIndex(-1); // Bỏ chọn tất cả các mục
        // Đặt lại các nút radio (RadioButton)
        rdoLon.setSelected(false);
        rdoMini.setSelected(false);
        rdoNho.setSelected(false);
        rdoVua.setSelected(false);
    }

    public void eventOK(ActionListener event) {
        btnOK.addActionListener(event);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(graphics);
    }

    private void populateComboBox() {
        cboMau.addItem("Xám");
        cboMau.addItem("Xanh");
        cboMau.addItem("Cam");
        cboMau.addItem("Đỏ");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnOK;
    private com.inventory.swing.Button button2;
    private com.inventory.swing.ComboBoxSuggestion cboMau;
    private com.inventory.swing.ComboBoxSuggestion comboBoxProductID;
    private javax.swing.ButtonGroup grpLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton rdoLon;
    private javax.swing.JRadioButton rdoMini;
    private javax.swing.JRadioButton rdoNho;
    private javax.swing.JRadioButton rdoVua;
    private com.inventory.swing.TextField txtGia;
    private com.inventory.swing.TextField txtMaSanPham;
    private com.inventory.swing.TextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}
