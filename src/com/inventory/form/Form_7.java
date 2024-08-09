/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.dao.EmployeesDAO;
import com.inventory.dao.ProductsDAO;
import com.inventory.entity.Employees;
import com.inventory.entity.EmployeesTable;
import com.inventory.utils.XJdbc;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import com.inventory.message.*;
import com.inventory.swing.ScrollBar;
import com.inventory.swing.TableHeader;
import com.inventory.swing.glasspanepopup.GlassPanePopup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class Form_7 extends javax.swing.JPanel {

    private EmployeesTable selectedEmployee;

    public Form_7() {
        initComponents();
        customizeTable();
        loadData();
    }

    private void customizeTable() {
        spTable.setVerticalScrollBar(new JScrollBar()); // Use JScrollBar instead of ScrollBar
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        tblTable.setShowHorizontalLines(true);
        tblTable.setGridColor(new Color(230, 230, 230));
        tblTable.setRowHeight(40);

        // Renderer for column headers
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

                // Custom renderer for specific columns (example: status column)
                if (column == 6) { // Status column
                    JLabel label = new JLabel(value.toString());
                    label.setFont(new Font("sansserif", Font.BOLD, 13));
                    if ("Nghỉ làm".equals(value)) {
                        label.setForeground(Color.RED); // Màu đỏ cho trạng thái đã xóa
                    } else {
                        label.setForeground(Color.GREEN); // Màu khác cho trạng thái khác
                    }
                    label.setHorizontalAlignment(JLabel.CENTER); // Center align status column
                    return label;
                }
                return com;
            }
        });
        // Additional customization for JScrollPane
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);

        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }

    private void loadData() {
        EmployeesDAO dao = new EmployeesDAO();
        List<Employees> employees = dao.selectAll(); // Lấy tất cả nhân viên

        // Sắp xếp dữ liệu sao cho trạng thái "Nghỉ làm" nằm ở cuối bảng
        employees.sort((e1, e2) -> {
            if ("Nghỉ làm".equals(e1.getStatus()) && !"Nghỉ làm".equals(e2.getStatus())) {
                return 1; // e1 nên xuất hiện sau e2
            } else if (!"Nghỉ làm".equals(e1.getStatus()) && "Nghỉ làm".equals(e2.getStatus())) {
                return -1; // e1 nên xuất hiện trước e2
            }
            return 0; // Giữ nguyên vị trí nếu cả hai có cùng trạng thái
        });

        // Cập nhật dữ liệu vào bảng
        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
        model.setRowCount(0); // Xóa các hàng cũ

        for (Employees employee : employees) {
            // Chuyển đổi giá trị từ 1 và 0 thành "Quản lý" và "Nhân viên"
            String position = employee.getPosition() == 1 ? "Quản lý" : "Nhân viên";

            model.addRow(new Object[]{
                employee.getEmployeeID(),
                employee.getUsername(),
                employee.getFullName(),
                employee.getPhone(),
                employee.getEmail(),
                position, // Hiển thị "Quản lý" hoặc "Nhân viên"
                employee.getStatus()
            });
        }
    }

    protected List<EmployeesTable> selectBySql(String sql, Object... args) {
        List<EmployeesTable> list = new ArrayList<>();
        try {
            java.sql.ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    EmployeesTable entity = new EmployeesTable();
                    entity.setEmployeeID(rs.getString("EmployeeID"));
                    entity.setUsername(rs.getString("Username"));
                    entity.setFullName(rs.getString("FullName"));

                    // Đọc số điện thoại dưới dạng String
                    entity.setPhone(rs.getInt("Phone"));

                    entity.setEmail(rs.getString("Email"));
                    entity.setPosition(rs.getByte("Position"));
                    entity.setImage(rs.getString("Image"));
                    entity.setPassword(rs.getString("Password"));
                    entity.setStatus(rs.getString("Status"));
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

    private void timKiemNhanVienTheoTen() {
        String employeesName = txtTimKiem.getText();
        if (employeesName.isEmpty()) {
            SearchEmployees_Null obj = new SearchEmployees_Null();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
        model.setRowCount(0);

        try {
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=QuanLyKhoHang;user=sa;password=123");
            CallableStatement stmt = con.prepareCall("{call sp_TimKiemNhanVien(?)}");
            stmt.setString(1, employeesName);
            java.sql.ResultSet rs = stmt.executeQuery();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                String id = rs.getString("EmployeeID");
                String username = rs.getString("Username");
                String fullname = rs.getString("FullName");
                 int phone = rs.getInt("Phone");
                String email = rs.getString("Email");
                String password = rs.getString("Password");

                
                byte position = rs.getByte("Position");
                String image = rs.getString("Image");
                String status = rs.getString("Status");

                // Thêm dữ liệu vào model (hoặc bất kỳ đối tượng nào bạn đang sử dụng)
                model.addRow(new Object[]{id, username, fullname, phone, email, password, position, image, status});
            }

            if (!hasResults) {
                Employees_NullName obj = new Employees_NullName();
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
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new com.inventory.swing.TextField();
        button1 = new com.inventory.swing.Button();
        btnTimKiemNhanVien = new com.inventory.swing.Button();
        btnXoa = new com.inventory.swing.Button();
        btnSua = new com.inventory.swing.Button();
        jPanel2 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tblTable = new com.inventory.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(905, 600));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(766, 120));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setText("Tên nhân viên");

        txtTimKiem.setBackground(new java.awt.Color(72, 142, 174));

        button1.setBackground(new java.awt.Color(102, 102, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Thêm");
        button1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        btnTimKiemNhanVien.setBackground(new java.awt.Color(102, 102, 255));
        btnTimKiemNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemNhanVien.setText("Tìm kiếm");
        btnTimKiemNhanVien.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        btnTimKiemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNhanVienActionPerformed(evt);
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
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTimKiemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tài Khoản", "Họ và tên", "Số điện thoại", "Email", "Chức vụ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        JDialog add = new JDialog();
        Model_Add_Employees model = new Model_Add_Employees();
        add.setUndecorated(true);
        add.getContentPane().add(model);
        add.pack();
        add.setLocationRelativeTo(this);
        add.setVisible(true);
    }//GEN-LAST:event_button1ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // Lấy hàng đã chọn trong bảng
        int selectedRow = tblTable.getSelectedRow();

        // Kiểm tra xem có hàng nào được chọn không
        if (selectedRow >= 0) {
            // Lấy giá trị từ hàng đã chọn
            String em = tblTable.getValueAt(selectedRow, 0).toString();

            // Tạo đối tượng EmployeesDAO để truy xuất thông tin trạng thái
            EmployeesDAO dao = new EmployeesDAO();
            String currentStatus = dao.getStatus(em); // Giả sử có phương thức getStatus để lấy trạng thái hiện tại của nhân viên

            // Kiểm tra trạng thái hiện tại
            if ("Nghỉ làm".equals(currentStatus)) {
                // Nếu trạng thái đã là "Nghỉ làm", hiển thị thông báo rằng nhân viên đã bị xóa
                DeleteEmployees1 obj = new DeleteEmployees1();

                obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                GlassPanePopup.showPopup(obj);
            } else {
                // Hiển thị hộp thoại xác nhận
                DeleteEmployees obj = new DeleteEmployees();
                obj.setMessage("Bạn có chắc chắn xóa nhân viên này không?");
                obj.eventOK((ae) -> {
                    if (obj.getOption() == DeleteEmployees.OPTION_YES) {
                        // Cập nhật trạng thái của nhân viên
                        dao.updateStatus(em, "Nghỉ làm");

                        // Tải lại dữ liệu để cập nhật bảng
                        loadData();
                    }
                });
                GlassPanePopup.showPopup(obj);
            }
        } else {
            // Thông báo nếu không có hàng nào được chọn
            DeleteEmployees obj = new DeleteEmployees();
            obj.setMessage("Vui lòng chọn nhân viên để xóa.");
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        if (selectedEmployee != null) {
            System.out.println("EmployeeID: " + selectedEmployee.getEmployeeID());
            System.out.println("Username: " + selectedEmployee.getUsername());
            System.out.println("FullName: " + selectedEmployee.getFullName());
            System.out.println("Phone: " + selectedEmployee.getPhone());
            System.out.println("Email: " + selectedEmployee.getEmail());
            System.out.println("Position: " + (selectedEmployee.getPosition() == 1 ? "Admin" : "User"));
            System.out.println("Image: " + selectedEmployee.getImage());
            System.out.println("Password: " + selectedEmployee.getPassword());
            System.out.println("Status: " + selectedEmployee.getStatus());

            // Mở cửa sổ chỉnh sửa với dữ liệu của nhân viên đã chọn
            JDialog add = new JDialog();
            Model_update_Employees1 model = new Model_update_Employees1();
            // Truyền đối tượng EmployeesTable vào form Model_update_Employees1
            model.setEmployeeData(selectedEmployee);

            // Thiết lập JDialog
            add.setUndecorated(true);
            add.getContentPane().add(model);
            add.pack();
            add.setLocationRelativeTo(this); // this có thể là JFrame hoặc JDialog hiện tại
            add.setVisible(true);
        } else {
            // Xử lý khi không có nhân viên nào được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để chỉnh sửa.");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseClicked
        int selectedRow = tblTable.getSelectedRow();

        if (selectedRow != -1) { // Có một hàng được chọn
            EmployeesTable employee = new EmployeesTable();

            // Lấy dữ liệu từ bảng và lưu vào đối tượng EmployeesTable
            employee.setEmployeeID((String) tblTable.getValueAt(selectedRow, 0));
            employee.setUsername((String) tblTable.getValueAt(selectedRow, 1));
            employee.setFullName((String) tblTable.getValueAt(selectedRow, 2));
            employee.setPhone(((Number) tblTable.getValueAt(selectedRow, 3)).intValue());
            employee.setEmail((String) tblTable.getValueAt(selectedRow, 4));

            Object positionObj = tblTable.getValueAt(selectedRow, 5);
            if (positionObj instanceof String) {
                String positionStr = (String) positionObj;
                employee.setPosition(positionStr.equalsIgnoreCase("Admin") ? (byte) 1 : (byte) 0);
            } else if (positionObj instanceof Number) {
                employee.setPosition(((Number) positionObj).byteValue());
            } else {
                employee.setPosition((byte) 0); // Giá trị mặc định
            }

            // Kiểm tra số cột hiện có trong bảng
            int columnCount = tblTable.getColumnCount();
            System.out.println("Column count: " + columnCount);

            // Điều chỉnh chỉ số cột trạng thái cho đúng
            int statusColumnIndex = 6; // Giả sử cột trạng thái là cột thứ 7 (index 6)
            if (statusColumnIndex < columnCount) {
                Object statusObj = tblTable.getValueAt(selectedRow, statusColumnIndex);
                if (statusObj instanceof String) {
                    employee.setStatus((String) statusObj);
                } else {
                    employee.setStatus("Unknown"); // Giá trị mặc định nếu không phải String
                }
            } else {
                employee.setStatus("Unknown"); // Giá trị mặc định nếu cột trạng thái không tồn tại
            }

            // Đặt đối tượng EmployeesTable vào biến toàn cục
            this.selectedEmployee = employee;

            // Optional: Hiển thị thông báo để kiểm tra dữ liệu đã được lưu vào đối tượng
//            JOptionPane.showMessageDialog(this, "Selected Employee: " + employee.getFullName() + "\nStatus: " + employee.getStatus());
        }
    }//GEN-LAST:event_tblTableMouseClicked

    private void btnTimKiemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNhanVienActionPerformed
        timKiemNhanVienTheoTen();
    }//GEN-LAST:event_btnTimKiemNhanVienActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnSua;
    private com.inventory.swing.Button btnTimKiemNhanVien;
    private com.inventory.swing.Button btnXoa;
    private com.inventory.swing.Button button1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane spTable;
    private com.inventory.swing.Table tblTable;
    private com.inventory.swing.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
