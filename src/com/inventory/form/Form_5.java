package com.inventory.form;

import com.inventory.message.ErrorAll;
import com.inventory.swing.ScrollBar;
import com.inventory.swing.TableHeader;
import com.inventory.swing.glasspanepopup.GlassPanePopup;
import com.inventory.utils.XDate;

import com.inventory.utils.XJdbc;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.CallableStatement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Form_5 extends javax.swing.JPanel {

    private DefaultTableModel tableModel;

    public Form_5() {
        initComponents();
        tableModel = new DefaultTableModel();
        table1.setModel(tableModel);
        customizeTable();

    }

    private void customizeTable() {

        table1.setShowHorizontalLines(true);
        table1.setGridColor(new Color(230, 230, 230));
        table1.setRowHeight(40);

        // Renderer for column headers
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table1.getTableHeader().setDefaultRenderer(headerRenderer);
        table1.getTableHeader().setReorderingAllowed(false);
        table1.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value.toString());
                header.setHorizontalAlignment(JLabel.CENTER); // Center-align the header text
                return header;
            }
        });

        // Default renderer for table cells
        table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtStartDate = new com.inventory.swing.TextField();
        jLabel4 = new javax.swing.JLabel();
        txtEndDate = new com.inventory.swing.TextField();
        jPanel2 = new javax.swing.JPanel();
        btnDoanhThu = new com.inventory.swing.Button();
        btnXuatKho = new com.inventory.swing.Button();
        btnNhapKho = new com.inventory.swing.Button();
        jPanel3 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        table1 = new com.inventory.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(910, 607));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setText("Từ ngày");

        txtStartDate.setBackground(new java.awt.Color(72, 142, 174));
        txtStartDate.setForeground(new java.awt.Color(255, 255, 255));
        txtStartDate.setCaretColor(new java.awt.Color(255, 255, 255));
        txtStartDate.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setText("Đến ngày");

        txtEndDate.setBackground(new java.awt.Color(72, 142, 174));
        txtEndDate.setForeground(new java.awt.Color(255, 255, 255));
        txtEndDate.setCaretColor(new java.awt.Color(255, 255, 255));
        txtEndDate.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtEndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEndDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnDoanhThu.setBackground(new java.awt.Color(102, 102, 255));
        btnDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        btnDoanhThu.setText("Doanh thu");
        btnDoanhThu.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoanhThuActionPerformed(evt);
            }
        });

        btnXuatKho.setBackground(new java.awt.Color(102, 102, 255));
        btnXuatKho.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatKho.setText("Xuất kho");
        btnXuatKho.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnXuatKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatKhoActionPerformed(evt);
            }
        });

        btnNhapKho.setBackground(new java.awt.Color(102, 102, 255));
        btnNhapKho.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapKho.setText("Nhập kho");
        btnNhapKho.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapKhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(btnDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXuatKho, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatKho, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapKho, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(860, 383));
        jPanel3.setLayout(new java.awt.BorderLayout());

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table1);

        jPanel3.add(spTable, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateTable(String type) {
        // Xóa dữ liệu cũ và cấu hình cột mới cho bảng
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        // Đọc ngày bắt đầu và ngày kết thúc từ các trường văn bản
        String startDateStr = txtStartDate.getText();
        String endDateStr = txtEndDate.getText();

        // Định dạng ngày
        String dateFormat = "yyyy-MM-dd";
        java.sql.Date startDate = null;
        java.sql.Date endDate = null;

        try {
            // Sử dụng XDate để chuyển đổi và định dạng ngày
            java.util.Date startUtilDate = XDate.toDate(startDateStr, dateFormat);
            java.util.Date endUtilDate = XDate.toDate(endDateStr, dateFormat);

            // Kiểm tra ngày bắt đầu không lớn hơn ngày kết thúc
            if (startUtilDate.after(endUtilDate)) {
                ErrorAll obj = new ErrorAll();
                obj.setMessage("Ngày bắt đầu không được lớn hơn ngày kết thúc. Vui lòng kiểm tra lại.");
                GlassPanePopup.showPopup(obj);
//            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày kết thúc. Vui lòng kiểm tra lại.");
                return;
            }

            startDate = new java.sql.Date(startUtilDate.getTime());
            endDate = new java.sql.Date(endUtilDate.getTime());
        } catch (RuntimeException e) {
            e.printStackTrace();
            ErrorAll obj = new ErrorAll();
            obj.setMessage("Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
            GlassPanePopup.showPopup(obj);
//        JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
            return;
        }

        // Xác định stored procedure và cấu hình cột bảng
        String storedProcedure = "";
        if (type.equals("Doanh Thu")) {
            tableModel.setColumnIdentifiers(new Object[]{"Ngày", "Tổng Doanh Thu"});
            storedProcedure = "GetRevenue";
        } else if (type.equals("Xuất Kho")) {
            tableModel.setColumnIdentifiers(new Object[]{"Phiếu Xuất", "Nhà Cung Cấp", "Số Lượng", "Ngày Xuất"});
            storedProcedure = "GetExportDetails";
        } else if (type.equals("Nhập Kho")) {
            tableModel.setColumnIdentifiers(new Object[]{"Phiếu Nhập", "Nhà Cung Cấp", "Số Lượng", "Ngày Nhập"});
            storedProcedure = "GetImportDetails";
        } else {
            JOptionPane.showMessageDialog(this, "Loại báo cáo không hợp lệ.");
            return;
        }

        // Thực hiện stored procedure và cập nhật bảng
        try (Connection conn = XJdbc.getConnection(); CallableStatement stmt = conn.prepareCall("{call " + storedProcedure + "(?, ?)}")) {

            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row;
                    if (storedProcedure.equals("GetRevenue")) {
                        java.sql.Date exportDate = rs.getDate("ExportDate");
                        double totalRevenue = rs.getDouble("TotalRevenue");
                        String formattedDate = XDate.toString(exportDate, "dd/MM/yyyy");
                        row = new Object[]{formattedDate, totalRevenue};
                    } else if (storedProcedure.equals("GetExportDetails")) {
                        String exportFormID = rs.getString("ExportFormID");
                        String supplierName = rs.getString("SupplierName");
                        int quantity = rs.getInt("Quantity");
                        java.sql.Date exportDate = rs.getDate("ExportDate");
                        String formattedDate = XDate.toString(exportDate, "dd/MM/yyyy");
                        row = new Object[]{exportFormID, supplierName, quantity, formattedDate};
                    } else if (storedProcedure.equals("GetImportDetails")) {
                        String importFormID = rs.getString("ImportFormID");
                        String supplierName = rs.getString("SupplierName");
                        int quantity = rs.getInt("Quantity");
                        java.sql.Date importDate = rs.getDate("ImportDate");
                        String formattedDate = XDate.toString(importDate, "dd/MM/yyyy");
                        row = new Object[]{importFormID, supplierName, quantity, formattedDate};
                    } else {
                        continue; // Bỏ qua nếu loại không hợp lệ
                    }
                    tableModel.addRow(row);
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
             ErrorAll obj = new ErrorAll();
            obj.setMessage("Lỗi khi truy xuất dữ liệu từ cơ sở dữ liệu. Vui lòng kiểm tra kết nối và cấu hình cơ sở dữ liệu.");
            GlassPanePopup.showPopup(obj);
//            JOptionPane.showMessageDialog(this, "Lỗi khi truy xuất dữ liệu từ cơ sở dữ liệu. Vui lòng kiểm tra kết nối và cấu hình cơ sở dữ liệu.");
        } catch (Exception e) {
            e.printStackTrace();
            ErrorAll obj = new ErrorAll();
            obj.setMessage("Đã xảy ra lỗi không xác định. Vui lòng thử lại.");
            GlassPanePopup.showPopup(obj);

        }
    }

//    private void updateTable(String type) {
//    // Xóa dữ liệu cũ và cấu hình cột mới cho bảng
//    tableModel.setRowCount(0);
//    tableModel.setColumnCount(0);
//
//    // Đọc ngày bắt đầu và ngày kết thúc từ các trường văn bản
//    String startDateStr = txtStartDate.getText();
//    String endDateStr = txtEndDate.getText();
//
//    // Kiểm tra và định dạng ngày
//    java.sql.Date startDate = null;
//    java.sql.Date endDate = null;
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày
//
//    try {
//        startDate = new java.sql.Date(sdf.parse(startDateStr).getTime());
//        endDate = new java.sql.Date(sdf.parse(endDateStr).getTime());
//    } catch (ParseException e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
//        return;
//    }
//
//    // Xác định stored procedure và cấu hình cột bảng
//    String storedProcedure = "";
//    if (type.equals("Doanh Thu")) {
//        tableModel.setColumnIdentifiers(new Object[]{"Ngày", "Tổng Doanh Thu"});
//        storedProcedure = "GetRevenue";
//    } else if (type.equals("Xuất Kho")) {
//        tableModel.setColumnIdentifiers(new Object[]{"Phiếu Xuất", "Nhà Cung Cấp", "Số Lượng", "Ngày Xuất"});
//        storedProcedure = "GetExportDetails";
//    } else if (type.equals("Nhập Kho")) {
//        tableModel.setColumnIdentifiers(new Object[]{"Phiếu Nhập", "Nhà Cung Cấp", "Số Lượng", "Ngày Nhập"});
//        storedProcedure = "GetImportDetails";
//    } else {
//        JOptionPane.showMessageDialog(this, "Loại báo cáo không hợp lệ.");
//        return;
//    }
//
//    // Thực hiện stored procedure và cập nhật bảng
//    try (Connection conn = XJdbc.getConnection(); 
//         CallableStatement stmt = conn.prepareCall("{call " + storedProcedure + "(?, ?)}")) {
//
//        stmt.setDate(1, startDate);
//        stmt.setDate(2, endDate);
//
//        try (ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                Object[] row;
//                if (storedProcedure.equals("GetRevenue")) {
//                    java.sql.Date exportDate = rs.getDate("ExportDate");
//                    double totalRevenue = rs.getDouble("TotalRevenue");
//                    String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(exportDate);
//                    row = new Object[]{formattedDate, totalRevenue};
//                } else if (storedProcedure.equals("GetExportDetails")) {
//                    String exportFormID = rs.getString("ExportFormID");
//                    String supplierName = rs.getString("SupplierName");
//                    int quantity = rs.getInt("Quantity");
//                    java.sql.Date exportDate = rs.getDate("ExportDate");
//                    String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(exportDate);
//                    row = new Object[]{exportFormID, supplierName, quantity, formattedDate};
//                } else if (storedProcedure.equals("GetImportDetails")) {
//                    String importFormID = rs.getString("ImportFormID");
//                    String supplierName = rs.getString("SupplierName");
//                    int quantity = rs.getInt("Quantity");
//                    java.sql.Date importDate = rs.getDate("ImportDate");
//                    String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(importDate);
//                    row = new Object[]{importFormID, supplierName, quantity, formattedDate};
//                } else {
//                    continue; // Bỏ qua nếu loại không hợp lệ
//                }
//                tableModel.addRow(row);
//            }
//        }
//    } catch (java.sql.SQLException e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi khi truy xuất dữ liệu từ cơ sở dữ liệu. Vui lòng kiểm tra kết nối và cấu hình cơ sở dữ liệu.");
//    } catch (Exception e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi không xác định. Vui lòng thử lại.");
//    }
//}

    private void btnDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoanhThuActionPerformed
        // TODO add your handling code here
        updateTable("Doanh Thu");
    }//GEN-LAST:event_btnDoanhThuActionPerformed

    private void btnNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapKhoActionPerformed
        // TODO add your handling code here:
        updateTable("Nhập Kho");
    }//GEN-LAST:event_btnNhapKhoActionPerformed

    private void btnXuatKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatKhoActionPerformed
        // TODO add your handling code here:
        updateTable("Xuất Kho");
    }//GEN-LAST:event_btnXuatKhoActionPerformed

    private void txtEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEndDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEndDateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnDoanhThu;
    private com.inventory.swing.Button btnNhapKho;
    private com.inventory.swing.Button btnXuatKho;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane spTable;
    private com.inventory.swing.Table table1;
    private com.inventory.swing.TextField txtEndDate;
    private com.inventory.swing.TextField txtStartDate;
    // End of variables declaration//GEN-END:variables
}
