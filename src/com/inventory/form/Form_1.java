/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.dao.ProductsDAO;
import com.inventory.entity.Products;
import com.inventory.entity.ProductsTable;
import com.inventory.swing.ScrollBar;
import com.inventory.swing.TableHeader;
import com.inventory.utils.XJdbc;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.inventory.message.*;
import com.inventory.swing.glasspanepopup.GlassPanePopup;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet; // Chỉ sử dụng import này// Import lớp Sheet từ gói đúng
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class Form_1 extends javax.swing.JPanel {

    private ProductsTable productsTable;

    /**
     * Creates new form Form_1
     */
    public Form_1() {
        initComponents();
        customizeTable();
        loadData();

    }

    private void loadData() {
        // Tạo đối tượng DAO
        String sql = "SELECT * FROM Products";

        try {
            // Gọi phương thức selectBySql để lấy danh sách sản phẩm
            List<Products> productList = selectBySql(sql);

            productList.sort((p1, p2) -> {
                // Trạng thái 'Đã xóa' sẽ được đưa xuống dưới cùng
                return p1.getStatus().equals("Đã xóa") ? 1 : p2.getStatus().equals("Đã xóa") ? -1 : 0;
            });

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0); // Xóa tất cả các hàng hiện tại

            // Thêm dữ liệu vào mô hình dữ liệu
            for (Products product : productList) {
                Object[] row = new Object[]{
                    product.getProductID(),
                    product.getProductName(),
                    product.getPrice(),
                    product.getColor(),
                    product.getWeight(),
                    product.getStatus(),
                    product.getSupplierID()
                };
                tableModel.addRow(row);
            }

            // Gán mô hình dữ liệu cho JTable
            table.setModel(tableModel); // Đổi yourTable thành tên biến của JTable bạn đang sử dụng

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected List<Products> selectBySql(String sql, Object... args) {
        List<Products> list = new ArrayList<>();
        try {
            java.sql.ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    Products entity = new Products();
                    entity.setProductID(rs.getString("ProductID"));
                    entity.setProductName(rs.getString("ProductName"));

                    entity.setColor(rs.getString("Color"));
                    entity.setWeight(rs.getString("Weight"));
                    entity.setQuantity(rs.getInt("Quantity"));
                    entity.setPrice(rs.getDouble("price"));
                    entity.setStatus(rs.getString("Status"));
                    entity.setSupplierID(rs.getString("SupplierID"));
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

    private void customizeTable() {
        spTable.setVerticalScrollBar(new JScrollBar()); // Use JScrollBar instead of ScrollBar
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setRowHeight(40);

        // Renderer for column headers
        // Renderer for column headers
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value.toString());
                header.setHorizontalAlignment(JLabel.CENTER); // Center-align the header text
                return header;
            }
        });

        // Default renderer for table cells
        // Default renderer for table cells
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
                if (column == 5) { // Status column
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

        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }

    private void timKiemSanPham() {
        String ProductName = txtTimKiem.getText();
        if (ProductName.isEmpty()) {

            SearchProducts_Null obj = new SearchProducts_Null();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=QuanLyKhoHang;user=sa;password=123");
            CallableStatement stmt = con.prepareCall("{call sp_TimKiemSanPham(?)}");
            stmt.setString(1, ProductName);
            java.sql.ResultSet rs = stmt.executeQuery();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;

                String productID = rs.getString("ProductID");
                String productName = rs.getString("ProductName");
                double price = rs.getDouble("Price");
                String color = rs.getString("Color");
                String weight = rs.getString("Weight");
                String status = rs.getString("Status");

                model.addRow(new Object[]{productID, productName, price, color, weight, status});
            }

            if (!hasResults) {

                Products_NullName obj = new Products_NullName();
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
        btnDel = new com.inventory.swing.Button();
        button1 = new com.inventory.swing.Button();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new com.inventory.swing.TextField();
        btnTimKiemSanPham = new com.inventory.swing.Button();
        jPanel2 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        table = new com.inventory.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(847, 40));
        jPanel1.setPreferredSize(new java.awt.Dimension(905, 200));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        button3.setBackground(new java.awt.Color(102, 102, 255));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Thêm ");
        button3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button3.setMaximumSize(new java.awt.Dimension(94, 38));
        button3.setMinimumSize(new java.awt.Dimension(94, 38));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(102, 102, 255));
        btnDel.setForeground(new java.awt.Color(255, 255, 255));
        btnDel.setText("Xóa");
        btnDel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        button1.setBackground(new java.awt.Color(102, 102, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Sửa");
        button1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(534, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 17, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setText("Tên sản phẩm");

        txtTimKiem.setBackground(new java.awt.Color(72, 142, 174));

        btnTimKiemSanPham.setBackground(new java.awt.Color(102, 102, 255));
        btnTimKiemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiemSanPham.setText("Tìm Kiếm");
        btnTimKiemSanPham.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnTimKiemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(btnTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên sản phẩm", "Giá", "Màu", "Loại", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        spTable.setViewportView(table);

        jPanel2.add(spTable, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        JDialog add = new JDialog();
        Model_Add_Product model = new Model_Add_Product();
        add.setUndecorated(true);
        add.getContentPane().add(model);
        add.pack();
        add.setLocationRelativeTo(this);
        add.setVisible(true);
    }//GEN-LAST:event_button3ActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String productID = table.getValueAt(selectedRow, 0).toString();

            ProductsDAO dao = new ProductsDAO();
            String currentStatus = dao.getStatus(productID);

            if ("Đã xóa".equals(currentStatus)) {

                DeleteProducts_DaDuocXoa obj = new DeleteProducts_DaDuocXoa();
                obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
                GlassPanePopup.showPopup(obj);

            } else {
                DeleteEmployees obj = new DeleteEmployees();
                obj.setMessage("Bạn có chắc chắn xóa sản phẩm này không?");
                obj.eventOK((ae) -> {
                    if (obj.getOption() == DeleteEmployees.OPTION_YES) {
                        // Cập nhật trạng thái của sản phẩm
                        dao.updateStatus(productID, "Đã xóa");

                        // Tải lại dữ liệu để cập nhật bảng
                        loadData();
                    }
                });
                GlassPanePopup.showPopup(obj);
            }
        } else {
            DeleteProducts_Null obj = new DeleteProducts_Null();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        if (productsTable != null) {

            // Mở cửa sổ chỉnh sửa với dữ liệu của nhân viên đã chọn
            JDialog add = new JDialog();
            Model_Edit_Product1 model = new Model_Edit_Product1();
            // Truyền đối tượng EmployeesTable vào form Model_update_Employees1
            model.setProductData(productsTable);

            // Thiết lập JDialog
            add.setUndecorated(true);
            add.getContentPane().add(model);
            add.pack();
            add.setLocationRelativeTo(this); // this có thể là JFrame hoặc JDialog hiện tại
            add.setVisible(true);
        } else {
            // Xử lý khi không có nhân viên nào được chọn
            EditProduct obj = new EditProduct();
            obj.eventOK((ae) -> GlassPanePopup.closePopupLast());
            GlassPanePopup.showPopup(obj);
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) { // Kiểm tra nếu có hàng được chọn
            try {
                // Tạo một đối tượng ProductsTable và lấy dữ liệu từ bảng
                ProductsTable product = new ProductsTable();

                // Lấy giá trị từ bảng và gán vào đối tượng ProductsTable
                product.setProductID((String) table.getValueAt(selectedRow, 0));
                product.setProductName((String) table.getValueAt(selectedRow, 1));

                // Chuyển đổi giá từ Number thành Double
                Object priceObject = table.getValueAt(selectedRow, 2);
                if (priceObject instanceof Number) {
                    product.setPrice(((Number) priceObject).doubleValue());
                } else {
                    product.setPrice(null); // Hoặc gán giá trị mặc định nếu không thể chuyển đổi
                }

                product.setColor((String) table.getValueAt(selectedRow, 3));
                product.setWeight((String) table.getValueAt(selectedRow, 4));

                // Chuyển đổi số lượng từ Number thành int
                product.setStatus((String) table.getValueAt(selectedRow, 5));

                // Đặt đối tượng Products vào biến toàn cục
                this.productsTable = product;

                // Hiển thị thông báo để kiểm tra dữ liệu đã được lưu vào đối tượng
//                JOptionPane.showMessageDialog(this, "Sản phẩm được chọn: " + product.getProductName() + "\nTrạng thái: " + product.getStatus());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi lấy dữ liệu sản phẩm từ bảng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_tableMouseClicked

    private void btnTimKiemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSanPhamActionPerformed
        timKiemSanPham();
    }//GEN-LAST:event_btnTimKiemSanPhamActionPerformed

// Hàm chọn tệp Excel
    private File chooseExcelFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx", "xls"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public boolean importProductsFromExcel(File excelFile) {
        ProductsDAO dao = new ProductsDAO(); // Khởi tạo đối tượng ProductsDAO
        boolean success = true;

        try (FileInputStream file = new FileInputStream(excelFile); XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0); // Xóa tất cả các hàng hiện tại trong bảng

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Bỏ qua hàng tiêu đề
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                try {
                    String productID = getCellValue(row.getCell(0));
                    String productName = getCellValue(row.getCell(1));
                    String weight = getCellValue(row.getCell(2));
                    String color = getCellValue(row.getCell(3));

                    double price = parseDouble(getCellValue(row.getCell(4)));
                    String status = getCellValue(row.getCell(5));

                    // Nếu giá trị trạng thái trống hoặc không hợp lệ, gán giá trị mặc định
                    if (status == null || status.trim().isEmpty()) {
                        status = "Hoạt động"; // Gán giá trị mặc định
                    }

                    if (productID.isEmpty() || productName.isEmpty()) {
                        System.err.println("ProductID or ProductName is empty at row: " + row.getRowNum());
                        success = false; // Set success to false if any critical field is empty
                        continue;
                    }

                    Products pr = new Products();
                    pr.setProductID(productID);
                    pr.setProductName(productName);
                    pr.setWeight(weight);
                    pr.setColor(color);

                    pr.setPrice(price);
                    pr.setStatus(status); // Thiết lập trạng thái

                    // Thực hiện thêm đối tượng pr vào cơ sở dữ liệu
                    dao.insert(pr); // Chèn sản phẩm vào cơ sở dữ liệu

                    // Thêm dữ liệu vào bảng
                    Object[] rowData = new Object[]{productID, productName, weight, color, price, status};
                    tableModel.addRow(rowData);
                } catch (Exception e) {
                    // Xử lý lỗi với từng hàng, ví dụ như định dạng sai
                    System.err.println("Lỗi khi đọc dữ liệu hàng: " + row.getRowNum() + " - " + e.getMessage());
                    success = false; // Đánh dấu lỗi nếu có lỗi xảy ra
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi đọc file Excel!");
            success = false; // Đánh dấu lỗi nếu có lỗi khi đọc tệp
        }

        return success;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula(); // Thay đổi nếu cần thiết
            default:
                return "";
        }
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0; // Hoặc ném ngoại lệ tùy theo yêu cầu
        }
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return 0.0; // Hoặc ném ngoại lệ tùy theo yêu cầu
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnDel;
    private com.inventory.swing.Button btnTimKiemSanPham;
    private com.inventory.swing.Button button1;
    private com.inventory.swing.Button button3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane spTable;
    private com.inventory.swing.Table table;
    private com.inventory.swing.TextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
