/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.dao.ImportFormDetailDAO;
import com.inventory.dao.ImportFormsDAO;
import com.inventory.dao.ProductsDAO;
import com.inventory.entity.ImportForm;
import com.inventory.entity.ImportFormDetail;
import com.inventory.entity.Products;
import com.inventory.swing.ScrollBar;
import com.inventory.swing.TableHeader;
import com.inventory.utils.XJdbc;
import com.sun.jdi.connect.spi.Connection;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author WINDOWS
 */
public class ImportExcel extends javax.swing.JPanel {

    /**
     * Creates new form Model_Add_EnterCoupon
     */
    public ImportExcel() {
        initComponents();
        customizeTable();
    }

    private void customizeTable() {

        tblExcel.setShowHorizontalLines(true);
        tblExcel.setGridColor(new Color(230, 230, 230));
        tblExcel.setRowHeight(40);

        // Renderer for column headers
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblExcel.getTableHeader().setDefaultRenderer(headerRenderer);
        tblExcel.getTableHeader().setReorderingAllowed(false);
        tblExcel.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value.toString());
                header.setHorizontalAlignment(JLabel.CENTER); // Center-align the header text
                return header;
            }
        });

        // Default renderer for table cells
        tblExcel.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                com.setBackground(Color.WHITE);
                setBorder(noFocusBorder);
                com.setFont(new Font("sansserif", Font.PLAIN, 13)); // Set font size to 13
                com.setForeground(isSelected ? new Color(36, 183, 194) : new Color(102, 102, 102));
                com.setFont(com.getFont().deriveFont(Font.BOLD));
                setHorizontalAlignment(JLabel.CENTER); // Center-align the cell text

                if (column == 7) { // Status column
                    JLabel label = new JLabel(value.toString());
                    label.setFont(new Font("sansserif", Font.BOLD, 13));
                    if ("Đã xóa".equals(value)) {
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
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
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
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSupplierID = new com.inventory.swing.TextField();
        txtQuantity = new com.inventory.swing.TextField();
        jPanel2 = new javax.swing.JPanel();
        button1 = new com.inventory.swing.Button();
        btnOK = new com.inventory.swing.Button();
        btnDeleExcel = new com.inventory.swing.Button();
        btnExcel = new com.inventory.swing.Button();
        spTable = new javax.swing.JScrollPane();
        tblExcel = new com.inventory.swing.Table();
        txtSupplierName = new com.inventory.swing.TextField();

        setPreferredSize(new java.awt.Dimension(785, 545));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(32, 137, 173));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        jPanel1.setPreferredSize(new java.awt.Dimension(680, 50));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("THÔNG TIN SẢN PHẨM");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 10, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 52, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 53, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 1, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGap(0, 1, Short.MAX_VALUE)))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(32, 137, 173));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setPreferredSize(new java.awt.Dimension(785, 545));
        jPanel3.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã nhà cung cấp");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nhà cung cấp");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Số lượng");

        txtSupplierID.setFocusable(false);

        txtQuantity.setFocusable(false);

        jPanel2.setBackground(new java.awt.Color(32, 137, 173));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 45));

        button1.setBackground(new java.awt.Color(255, 153, 0));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Hủy");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        btnOK.setBackground(new java.awt.Color(27, 66, 139));
        btnOK.setForeground(new java.awt.Color(255, 255, 255));
        btnOK.setText("Lưu");
        btnOK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnDeleExcel.setBackground(new java.awt.Color(255, 0, 0));
        btnDeleExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleExcel.setText("Xóa");
        btnDeleExcel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnDeleExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleExcelActionPerformed(evt);
            }
        });

        btnExcel.setBackground(new java.awt.Color(0, 153, 0));
        btnExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnExcel.setText("Flie");
        btnExcel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(351, Short.MAX_VALUE)
                .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tblExcel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại", "Số lượng", "Màu", "Nhà cung cấp", "Giá", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tblExcel);

        txtSupplierName.setFocusable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSupplierID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        SwingUtilities.getWindowAncestor(this).dispose();
    }//GEN-LAST:event_button1ActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        File file = chooseExcelFile(); // Implement this method to open a file chooser and return the selected file
        if (file == null) {
            JOptionPane.showMessageDialog(this, "Lỗi đọc tệp tin Excel!");
        } else {
            // Nhập sản phẩm từ tệp Excel
            if (importProductsFromExcel(file)) {
                JOptionPane.showMessageDialog(this, "Import danh sách sản phẩm thành công!");
                // Cập nhật bảng sản phẩm nếu cần
//                updateProductTable(); // Ensure this method updates the table correctly
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi nhập danh sách sản phẩm!");
            }
        }
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnDeleExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleExcelActionPerformed
        // TODO add your handling code here:  
        DefaultTableModel tableModel = (DefaultTableModel) tblExcel.getModel();

        // Lấy chỉ số hàng được chọn
        int selectedRow = tblExcel.getSelectedRow();

        // Kiểm tra nếu có hàng nào được chọn
        if (selectedRow != -1) {
            // Hiển thị hộp thoại xác nhận trước khi xóa
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa hàng này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            // Nếu người dùng chọn "Yes"
            if (confirm == JOptionPane.YES_OPTION) {
                // Xóa hàng được chọn khỏi bảng dữ liệu
                tableModel.removeRow(selectedRow);

                // Cập nhật tổng số lượng sau khi xóa hàng
                updateTotalQuantity();
            }
        } else {
            // Thông báo người dùng nếu không có hàng nào được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.");
        }
    }//GEN-LAST:event_btnDeleExcelActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        try {
            boolean success = saveProductsToDatabase();
            if (success) {
                // Thực hiện hành động nếu lưu dữ liệu thành công
            } else {
                // Xử lý nếu lưu dữ liệu không thành công
            }
        } catch (Exception e) {
            // Xử lý lỗi I/O
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi lưu dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0; // Hoặc giá trị mặc định khác
        }
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0; // Hoặc giá trị mặc định khác
        }
    }

    private String generateNewImportFormID() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

    private String generateNewImportFormDetailID() {
        return UUID.randomUUID().toString().substring(0, 10);
    }

    private File chooseExcelFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx", "xls"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    private boolean saveProductsToDatabase() {
        DefaultTableModel tableModel = (DefaultTableModel) tblExcel.getModel();
        String supplierName = txtSupplierName.getText().trim();
        String supplierID = getSupplierIDByName(supplierName);

        if (supplierID == null) {
            JOptionPane.showMessageDialog(this, "Nhà cung cấp không tồn tại trong cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try (java.sql.Connection conn = XJdbc.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            ProductsDAO productDAO = new ProductsDAO();

            try {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String productID = tableModel.getValueAt(i, 0).toString().trim();
                    String productName = tableModel.getValueAt(i, 1).toString().trim();
                    String weight = tableModel.getValueAt(i, 2).toString().trim();
                    int quantity = parseInteger(tableModel.getValueAt(i, 3).toString());
                    String color = tableModel.getValueAt(i, 4).toString().trim();
                    String supplierNameFromTable = tableModel.getValueAt(i, 5).toString().trim();
                    String supplierIDFromTable = getSupplierIDByName(supplierNameFromTable);
                    double price = parseDouble(tableModel.getValueAt(i, 6).toString());
                    String status = tableModel.getValueAt(i, 7).toString().trim();

                    if (status.isEmpty()) {
                        status = "Hoạt động";
                    }

                    if (supplierIDFromTable == null) {
                        System.err.println("Nhà cung cấp không tồn tại: " + supplierNameFromTable);
                        continue; // Bỏ qua hàng này
                    }

                    Products product = new Products(productID, productName, color, weight, quantity, price, status, supplierIDFromTable);

                    productDAO.insertOrUpdateProduct(conn, product);
                }

                conn.commit(); // Commit giao dịch
                JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu vào cơ sở dữ liệu.", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                return true;

            } catch (SQLException ex) {
                conn.rollback(); // Rollback giao dịch nếu có lỗi
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi lưu dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi kết nối cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private Set<String> supplierNamesInFile = new HashSet<>(); // Định nghĩa biến để lưu tên nhà cung cấp

    // Phương thức để cập nhật tổng số lượng sau khi xóa hàng
    private void updateTotalQuantity() {
        DefaultTableModel tableModel = (DefaultTableModel) tblExcel.getModel();
        int rowCount = tableModel.getRowCount();
        int totalQuantity = 0;

        for (int i = 0; i < rowCount; i++) {
            // Lấy giá trị của cột số lượng từ mỗi hàng
            Object quantityObj = tableModel.getValueAt(i, 3); // Cột số lượng là cột thứ 4 (chỉ số 3)
            if (quantityObj != null) {
                try {
                    int quantity = Integer.parseInt(quantityObj.toString());
                    totalQuantity += quantity;
                } catch (NumberFormatException e) {
                    System.err.println("Lỗi định dạng số lượng tại hàng: " + i + " - " + e.getMessage());
                }
            }
        }

        // Cập nhật trường txtQuantity
        txtQuantity.setText(String.valueOf(totalQuantity));
    }

// Phương thức để xóa hàng đã chọn và cập nhật tổng số lượng
    private void deleteSelectedRows() {
        DefaultTableModel tableModel = (DefaultTableModel) tblExcel.getModel();
        int[] selectedRows = tblExcel.getSelectedRows();

        // Xóa hàng từ dưới lên để không làm thay đổi chỉ số hàng còn lại
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            tableModel.removeRow(selectedRows[i]);
        }

        // Cập nhật tổng số lượng sau khi xóa hàng
        updateTotalQuantity();
    }

// Phương thức để nhập sản phẩm từ Excel
    private boolean importProductsFromExcel(File excelFile) {
        DefaultTableModel tableModel = (DefaultTableModel) tblExcel.getModel();
        tableModel.setRowCount(0); // Xóa các hàng hiện tại
        int totalQuantity = 0; // Biến để lưu tổng số lượng

        try (FileInputStream file = new FileInputStream(excelFile); XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (!rowIterator.hasNext()) {
                JOptionPane.showMessageDialog(this, "File Excel trống.");
                return false;
            }

            rowIterator.next(); // Bỏ qua hàng tiêu đề

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                try {
                    String productID = getCellValue(row.getCell(0)).trim();
                    String productName = getCellValue(row.getCell(1)).trim();
                    String weightStr = getCellValue(row.getCell(2)).trim();
                    int quantity = parseInteger(getCellValue(row.getCell(3)).trim());
                    if (quantity <= 0) {
                        quantity = 1; // Đặt số lượng mặc định là 1 nếu bị 0 hoặc lỗi
                    }

                    String color = getCellValue(row.getCell(4)).trim();
                    String supplierName = getCellValue(row.getCell(5)).trim();
                    double price = parseDouble(getCellValue(row.getCell(6)).trim());
                    String status = getCellValue(row.getCell(7)).trim();
                    if (status.isEmpty()) {
                        status = "Hoạt động";
                    }

                    // Đảm bảo tên nhà cung cấp không bị null hoặc trống
                    if (supplierName == null || supplierName.trim().isEmpty()) {
                        System.err.println("Nhà cung cấp không hợp lệ: " + supplierName);
                        continue; // Bỏ qua hàng này nếu tên nhà cung cấp không hợp lệ
                    }

                    supplierNamesInFile.add(supplierName); // Cập nhật tên nhà cung cấp

                    String supplierID = getSupplierIDByName(supplierName);
                    if (supplierID == null) {
                        // Cảnh báo nếu nhà cung cấp không tồn tại
                        System.err.println("Nhà cung cấp không tồn tại: " + supplierName);
                        continue; // Bỏ qua hàng này
                    }

                    Object[] rowData = new Object[]{productID, productName, weightStr, quantity, color, supplierName, price, status};
                    tableModel.addRow(rowData);

                    totalQuantity += quantity; // Cộng dồn số lượng

                } catch (NumberFormatException e) {
                    System.err.println("Lỗi định dạng số tại hàng: " + row.getRowNum() + " - " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Lỗi khi đọc hàng: " + row.getRowNum() + " - " + e.getMessage());
                }
            }

            // Cập nhật các trường txt với tổng số lượng
            txtQuantity.setText(String.valueOf(totalQuantity));

            // Cập nhật txtSupplierName và txtSupplierID cho nhà cung cấp đầu tiên trong danh sách
            if (!supplierNamesInFile.isEmpty()) {
                String firstSupplierName = supplierNamesInFile.iterator().next();
                String supplierID = getSupplierIDByName(firstSupplierName);
                if (supplierID != null) {
                    txtSupplierName.setText(firstSupplierName);
                    txtSupplierID.setText(supplierID);
                }
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

//  private boolean importProductsFromExcel(File excelFile) {
//    DefaultTableModel tableModel = (DefaultTableModel) tblExcel.getModel();
//    tableModel.setRowCount(0); // Xóa các hàng hiện tại
//    int totalQuantity = 0; // Biến để lưu tổng số lượng
//
//    try (FileInputStream file = new FileInputStream(excelFile); XSSFWorkbook workbook = new XSSFWorkbook(file)) {
//        Sheet sheet = workbook.getSheetAt(0);
//        Iterator<Row> rowIterator = sheet.iterator();
//
//        if (!rowIterator.hasNext()) {
//            JOptionPane.showMessageDialog(this, "File Excel trống.");
//            return false;
//        }
//
//        rowIterator.next(); // Bỏ qua hàng tiêu đề
//
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            try {
//                String productID = getCellValue(row.getCell(0)).trim();
//                String productName = getCellValue(row.getCell(1)).trim();
//                String weightStr = getCellValue(row.getCell(2)).trim();
//                int quantity = parseInteger(getCellValue(row.getCell(3)).trim());
//                if (quantity <= 0) {
//                    quantity = 1; // Đặt số lượng mặc định là 1 nếu bị 0 hoặc lỗi
//                }
//
//                String color = getCellValue(row.getCell(4)).trim();
//                String supplierName = getCellValue(row.getCell(5)).trim();
//                double price = parseDouble(getCellValue(row.getCell(6)).trim());
//                String status = getCellValue(row.getCell(7)).trim();
//                if (status.isEmpty()) {
//                    status = "Hoạt động";
//                }
//
//                // Đảm bảo tên nhà cung cấp không bị null hoặc trống
//                if (supplierName == null || supplierName.trim().isEmpty()) {
//                    System.err.println("Nhà cung cấp không hợp lệ: " + supplierName);
//                    continue; // Bỏ qua hàng này nếu tên nhà cung cấp không hợp lệ
//                }
//
//                supplierNamesInFile.add(supplierName); // Cập nhật tên nhà cung cấp
//
//                String supplierID = getSupplierIDByName(supplierName);
//                if (supplierID == null) {
//                    // Cảnh báo nếu nhà cung cấp không tồn tại
//                    System.err.println("Nhà cung cấp không tồn tại: " + supplierName);
//                    continue; // Bỏ qua hàng này
//                }
//
//                Object[] rowData = new Object[]{productID, productName, weightStr, quantity,color,supplierName, price, status };
//                tableModel.addRow(rowData);
//
//                totalQuantity += quantity; // Cộng dồn số lượng
//
//            } catch (NumberFormatException e) {
//                System.err.println("Lỗi định dạng số tại hàng: " + row.getRowNum() + " - " + e.getMessage());
//            } catch (Exception e) {
//                System.err.println("Lỗi khi đọc hàng: " + row.getRowNum() + " - " + e.getMessage());
//            }
//        }
//
//        // Cập nhật các trường txt với tổng số lượng
//        txtQuantity.setText(String.valueOf(totalQuantity));
//        // Cập nhật txtSupplierName và txtSupplierID cho nhà cung cấp đầu tiên trong danh sách
//        if (!supplierNamesInFile.isEmpty()) {
//            String firstSupplierName = supplierNamesInFile.iterator().next();
//            String supplierID = getSupplierIDByName(firstSupplierName);
//            if (supplierID != null) {
//                txtSupplierName.setText(firstSupplierName);
//                txtSupplierID.setText(supplierID);
//            }
//        }
//        return true;
//
//    } catch (IOException e) {
//        e.printStackTrace();
//        return false;
//    }
//}
    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim(); // Loại bỏ khoảng trắng
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }

//
//    private int parseInteger(String value) {
//        try {
//            return Integer.parseInt(value);
//        } catch (NumberFormatException e) {
//            System.err.println("Invalid integer format: " + value);
//            return 0; // Giá trị mặc định
//        }
//    }
//
//    private double parseDouble(String value) {
//        try {
//            return Double.parseDouble(value);
//        } catch (NumberFormatException e) {
//            System.err.println("Invalid double format: " + value);
//            return 0.0; // Giá trị mặc định
//        }
//    }
    private String getSupplierIDByName(String supplierName) {
        String sql = "SELECT SupplierID FROM Suppliers WHERE SupplierName = ?";
        try (ResultSet rs = XJdbc.query(sql, supplierName)) {
            if (rs.next()) {
                return rs.getString("SupplierID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateSupplierDetails() {
        // Method to update supplier details if needed
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnDeleExcel;
    private com.inventory.swing.Button btnExcel;
    private com.inventory.swing.Button btnOK;
    private com.inventory.swing.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane spTable;
    private com.inventory.swing.Table tblExcel;
    private com.inventory.swing.TextField txtQuantity;
    private com.inventory.swing.TextField txtSupplierID;
    private com.inventory.swing.TextField txtSupplierName;
    // End of variables declaration//GEN-END:variables
}
