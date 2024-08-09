/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.dao.CustomersDAO;
import com.inventory.entity.Customers;
import com.inventory.entity.CustomersTable;
import com.inventory.swing.ScrollBar;
import com.inventory.swing.TableHeader;
import com.inventory.utils.XJdbc;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.inventory.message.*;
import com.inventory.swing.glasspanepopup.GlassPanePopup;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class Form_8 extends javax.swing.JPanel {

    private CustomersTable customersTable;

    /**
     * Creates new form Form_8
     */
    public Form_8() {
        initComponents();
        loadData();
        customizeTable();
    }

    private void timKiemKhachHang() {
        String customerName = txtTenKH.getText();
        if (customerName.isEmpty()) {
            SearchCustomers_Null obj = new SearchCustomers_Null();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
        model.setRowCount(0);

        try {
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=QuanLyKhoHang;user=sa;password=123");
            CallableStatement stmt = con.prepareCall("{call sp_TimKiemKhachHang(?)}");
            stmt.setString(1, customerName);
            java.sql.ResultSet rs = stmt.executeQuery();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                String id = rs.getString("CustomerID");
                String name = rs.getString("CustomerName");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                model.addRow(new Object[]{id, name, address, phone});
            }

            if (!hasResults) {
                Customers_NullName obj = new Customers_NullName();
                obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                GlassPanePopup.showPopup(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    private void customizeTable() {

        tblTable.setShowHorizontalLines(true);
        tblTable.setGridColor(new Color(230, 230, 230));
        tblTable.setRowHeight(40);

        // Renderer for column headers
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblTable.getTableHeader().setDefaultRenderer(headerRenderer);
        tblTable.getTableHeader().setReorderingAllowed(false);
        tblTable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value.toString());
                header.setHorizontalAlignment(JLabel.CENTER); // Center-align the header text
                return header;
            }
        });

        // Default renderer for table cells
        tblTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

    private void loadData() {
        String sql = "SELECT * FROM Customers";

        try {
            List<Customers> customerList = selectBySql(sql);

            DefaultTableModel tableModel = (DefaultTableModel) tblTable.getModel();
            tableModel.setRowCount(0); // Xóa tất cả các hàng hiện tại

            for (Customers customer : customerList) {
                Object[] row = new Object[]{
                    customer.getCustomerID(),
                    customer.getCustomerName(),
                    customer.getAddress(),
                    customer.getPhone()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected List<Customers> selectBySql(String sql, Object... args) {
        List<Customers> list = new ArrayList<>();
        try {
            java.sql.ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    Customers entity = new Customers();
                    entity.setCustomerID(rs.getString("CustomerID"));
                    entity.setCustomerName(rs.getString("CustomerName"));
                    entity.setAddress(rs.getString("Address"));
                    entity.setPhone(rs.getString("Phone")); // Sửa từ rs.getInt thành rs.getString

                    list.add(entity);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTenKH = new com.inventory.swing.TextField();
        btnTimKiemKhachHang = new com.inventory.swing.Button();
        btnXoa = new com.inventory.swing.Button();
        btnSua = new com.inventory.swing.Button();
        button1 = new com.inventory.swing.Button();
        jPanel2 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tblTable = new com.inventory.swing.Table();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(766, 120));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setText("Tên khách hàng");

        txtTenKH.setBackground(new java.awt.Color(72, 142, 174));

        btnTimKiemKhachHang.setBackground(new java.awt.Color(102, 102, 255));
        btnTimKiemKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemKhachHang.setText("Tìm kiếm");
        btnTimKiemKhachHang.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        btnTimKiemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemKhachHangActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(102, 102, 255));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("xóa");
        btnXoa.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(102, 102, 255));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        button1.setBackground(new java.awt.Color(102, 102, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Thêm");
        button1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "Địa Chỉ", "Số điện thoại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTableMouseClicked(evt);
            }
        });
        spTable.setViewportView(tblTable);

        jPanel2.add(spTable, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int selectedRow = tblTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy ID khách hàng từ hàng đã chọn
            String customerId = tblTable.getValueAt(selectedRow, 0).toString();

            // Xác nhận hành động xóa
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khách hàng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Xóa khách hàng bằng cách gọi phương thức deleteCustomer
                CustomersDAO dao = new CustomersDAO();
                boolean success = dao.deleteCustomer(customerId);

                // Hiển thị thông báo thành công hoặc thất bại
                if (success) {
                    JOptionPane.showMessageDialog(this, "Khách hàng đã được xóa thành công!");
                    // Tải lại dữ liệu để cập nhật bảng
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa khách hàng thất bại.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để xóa.");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // Mở cửa sổ chỉnh sửa với dữ liệu của nhân viên đã chọn
        JDialog add = new JDialog();
        Model_Edit_Customers model = new Model_Edit_Customers();
        // Truyền đối tượng EmployeesTable vào form Model_update_Employees1
        model.setCustomersData(customersTable);

        // Thiết lập JDialog
        add.setUndecorated(true);
        add.getContentPane().add(model);
        add.pack();
        add.setLocationRelativeTo(this); // this có thể là JFrame hoặc JDialog hiện tại
        add.setVisible(true);

    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseClicked
        int selectedRow = tblTable.getSelectedRow();

        if (selectedRow != -1) { // Có một hàng được chọn
            CustomersTable customer = new CustomersTable();

            // Lấy dữ liệu từ bảng và lưu vào đối tượng CustomersTable
            customer.setCustomerID((String) tblTable.getValueAt(selectedRow, 0));
            customer.setCustomerName((String) tblTable.getValueAt(selectedRow, 1));
            customer.setAddress((String) tblTable.getValueAt(selectedRow, 2));
            customer.setPhone((String) tblTable.getValueAt(selectedRow, 3)); // Sử dụng getString cho cột Phone

            // Đặt đối tượng CustomersTable vào biến toàn cục
            this.customersTable = customer;

//            // Optional: Hiển thị thông báo để kiểm tra dữ liệu đã được lưu vào đối tượng
//            JOptionPane.showMessageDialog(this, "Selected Customer: " + customer.getCustomerName() + "\nPhone: " + customer.getPhone());
        }
    }//GEN-LAST:event_tblTableMouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        JDialog add = new JDialog();
        Model_Add_Customers model = new Model_Add_Customers();
        add.setUndecorated(true);
        add.getContentPane().add(model);
        add.pack();
        add.setLocationRelativeTo(this);
        add.setVisible(true);
    }//GEN-LAST:event_button1ActionPerformed

    private void btnTimKiemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemKhachHangActionPerformed
        timKiemKhachHang();
    }//GEN-LAST:event_btnTimKiemKhachHangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnSua;
    private com.inventory.swing.Button btnTimKiemKhachHang;
    private com.inventory.swing.Button btnXoa;
    private com.inventory.swing.Button button1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane spTable;
    private com.inventory.swing.Table tblTable;
    private com.inventory.swing.TextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
