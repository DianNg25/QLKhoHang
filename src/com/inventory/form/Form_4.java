/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.dao.SuppliersDAO;
import com.inventory.entity.Suppliers;
import com.inventory.swing.ScrollBar;
import com.inventory.swing.TableHeader;
import com.inventory.utils.XJdbc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import com.inventory.message.*;
import com.inventory.swing.glasspanepopup.GlassPanePopup;
import java.awt.Toolkit;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Form_4 extends javax.swing.JPanel {

    private JTextField tfProductCode;
    private JTextField tfProductName;
    private JTextField tfSupplier;
    private JCheckBox cbMini, cbSmall, cbMedium, cbLarge;
    private JTextField tfPrice;
    private JCheckBox cbGray, cbRed, cbBlue, cbOrange;
    private JTable productTable;
    private DefaultTableModel tableModel;

    public Form_4() {
        initComponents();
        customizeTable();
        loadSupplierData();

    }

    private void customizeTable() {

        tblNCC.setShowHorizontalLines(true);
        tblNCC.setGridColor(new Color(230, 230, 230));
        tblNCC.setRowHeight(40);

        // Renderer for column headers
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblNCC.getTableHeader().setDefaultRenderer(headerRenderer);
        tblNCC.getTableHeader().setReorderingAllowed(false);
        tblNCC.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value.toString());
                header.setHorizontalAlignment(JLabel.CENTER); // Center-align the header text
                return header;
            }
        });

        // Default renderer for table cells
        tblNCC.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                com.setBackground(Color.WHITE);
                setBorder(noFocusBorder);
                com.setFont(new Font("sansserif", Font.PLAIN, 13)); // Set font size to 13
                com.setForeground(isSelected ? new Color(36, 183, 194) : new Color(102, 102, 102));
                com.setFont(com.getFont().deriveFont(Font.BOLD));
                setHorizontalAlignment(JLabel.CENTER); // Center-align the cell text

                if (column == 6) { // Status column
                    StatusType type = (StatusType) value;
                    JLabel label = new JLabel(type.getText());
                    label.setFont(label.getFont().deriveFont(Font.BOLD));
                    label.setFont(new Font("sansserif", Font.BOLD, 13)); // Set font size to 13 and bold for status column
                    label.setHorizontalAlignment(JLabel.CENTER); // Center-align the status column text
                    if (type == StatusType.DA_XOA) {
                        label.setForeground(Color.RED); // Red text for "Đã xóa"
                    } else if (type == StatusType.BINH_THUONG) {
                        label.setForeground(Color.GREEN); // Green text for "Bình thường"
                    }
                    return label;
                }
                return com;
            }
        });

        // Additional customization for JScrollPane
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }

    private void loadSupplierData() {
        String sql = "SELECT SupplierID, SupplierName, Address, Phone, Email FROM Suppliers";
        try {
            List<Suppliers> suppliers = selectBySql(sql); // Lấy danh sách nhà cung cấp từ database

            DefaultTableModel tableModel = (DefaultTableModel) tblNCC.getModel();
            tableModel.setRowCount(0); // Xóa tất cả các hàng hiện tại

            for (Suppliers supplier : suppliers) {
                Object[] row = {
                    supplier.getSupplierID(),
                    supplier.getSupplierName(),
                    supplier.getAddress(),
                    supplier.getPhone(),
                    supplier.getEmail()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading supplier data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    protected List<Suppliers> selectBySql(String sql, Object... args) {
        List<Suppliers> list = new ArrayList<>();
        try {
            java.sql.ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    Suppliers entity = new Suppliers();
                    entity.setSupplierID(rs.getString("SupplierID"));
                    entity.setSupplierName(rs.getString("SupplierName"));
                    entity.setAddress(rs.getString("Address"));
                    entity.setPhone(rs.getString("Phone"));
                    entity.setEmail(rs.getString("Email"));

                    list.add(entity);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    private void timKiemNCC() {
        String supplierName = txtNameNCC.getText();
        if (supplierName.isEmpty()) {

            SearchSuppliers_Null obj = new SearchSuppliers_Null();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblNCC.getModel();
        model.setRowCount(0);

        try {
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=QuanLyKhoHang;user=sa;password=123");
            CallableStatement stmt = con.prepareCall("{call sp_TimKiemNhaCungCap(?)}");
            stmt.setString(1, supplierName);
            java.sql.ResultSet rs = stmt.executeQuery();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                String id = rs.getString("SupplierID");
                String name = rs.getString("SupplierName");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                String email = rs.getString("Email");
                model.addRow(new Object[]{id, name, address, phone, email});
            }

            if (!hasResults) {

                Suppliers_NullName obj = new Suppliers_NullName();
                obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                GlassPanePopup.showPopup(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        button3 = new com.inventory.swing.Button();
        button1 = new com.inventory.swing.Button();
        btnDelete_Suppliers = new com.inventory.swing.Button();
        jPanel4 = new javax.swing.JPanel();
        btnTimKiemNCC = new com.inventory.swing.Button();
        txtNameNCC = new com.inventory.swing.TextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tblNCC = new com.inventory.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        button3.setBackground(new java.awt.Color(102, 102, 255));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Thêm");
        button3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button1.setBackground(new java.awt.Color(102, 102, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Sửa");
        button1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N

        btnDelete_Suppliers.setBackground(new java.awt.Color(102, 102, 255));
        btnDelete_Suppliers.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete_Suppliers.setText("Xóa");
        btnDelete_Suppliers.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnDelete_Suppliers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete_SuppliersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(499, Short.MAX_VALUE)
                .addComponent(btnDelete_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete_Suppliers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        btnTimKiemNCC.setBackground(new java.awt.Color(102, 102, 255));
        btnTimKiemNCC.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemNCC.setText("Tìm Kiếm");
        btnTimKiemNCC.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnTimKiemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNCCActionPerformed(evt);
            }
        });

        txtNameNCC.setBackground(new java.awt.Color(72, 142, 174));
        txtNameNCC.setForeground(new java.awt.Color(255, 255, 255));
        txtNameNCC.setCaretColor(new java.awt.Color(255, 255, 255));
        txtNameNCC.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setText("Tên nhà cung cấp");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(40, 40, 40)
                .addComponent(txtNameNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(btnTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNameNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(15, 15, 15))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        spTable.setBorder(null);

        tblNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung câp", "Địa chỉ", "Số điện thoại", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tblNCC);

        jPanel2.add(spTable, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        SwingUtilities.invokeLater(() -> {
            JDialog add = new JDialog();
            add.setModal(true);
            Model_Add_Suppliers model = new Model_Add_Suppliers();
            add.setUndecorated(true);
            add.getContentPane().add(model);
            add.pack();

            // Centering Logic
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - add.getWidth()) / 2;
            int y = (screenSize.height - add.getHeight()) / 2;
            add.setLocation(x, y);

            add.setVisible(true);
        });
    }//GEN-LAST:event_button3ActionPerformed

    private void btnTimKiemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNCCActionPerformed
        timKiemNCC();
    }//GEN-LAST:event_btnTimKiemNCCActionPerformed

    private void btnDelete_SuppliersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete_SuppliersActionPerformed

        int selectedRow = tblNCC.getSelectedRow();
        if (selectedRow >= 0) {
            String EmployeeID = tblNCC.getValueAt(selectedRow, 0).toString();

            // Hiển thị hộp thoại xác nhận
            int confirmation = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa nhà cung cấp này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                SuppliersDAO dao = new SuppliersDAO();
                // Sử dụng hàm delete để xóa Nhà cung cấp
                dao.delete(EmployeeID);

                DeleteSuppliers obj = new DeleteSuppliers();
                obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                GlassPanePopup.showPopup(obj);
            } else {
                DeleteSuppliers1 obj = new DeleteSuppliers1();
                obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                GlassPanePopup.showPopup(obj);
            }
        } else {
            DeleteSuppliers2 obj = new DeleteSuppliers2();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
        }


    }//GEN-LAST:event_btnDelete_SuppliersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnDelete_Suppliers;
    private com.inventory.swing.Button btnTimKiemNCC;
    private com.inventory.swing.Button button1;
    private com.inventory.swing.Button button3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane spTable;
    private com.inventory.swing.Table tblNCC;
    private com.inventory.swing.TextField txtNameNCC;
    // End of variables declaration//GEN-END:variables
}
