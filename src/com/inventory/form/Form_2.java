/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.entity.ImportForm;
import com.inventory.swing.ScrollBar;
import com.inventory.swing.TableActionCellEditor;
import com.inventory.swing.TableActionCellRender;
import com.inventory.swing.TableActionEvent;
import com.inventory.swing.TableHeader;
import com.inventory.utils.XJdbc;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Form_2 extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    
    public Form_2() {
        initComponents();
        loadData();
        customizeTable();
    }
    
    
    
     private void customizeTable() {

        tblNhapHang.setShowHorizontalLines(true);
        tblNhapHang.setGridColor(new Color(230, 230, 230));
        tblNhapHang.setRowHeight(40);

        // Renderer for column headers
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblNhapHang.getTableHeader().setDefaultRenderer(headerRenderer);
        tblNhapHang.getTableHeader().setReorderingAllowed(false);
        tblNhapHang.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value.toString());
                header.setHorizontalAlignment(JLabel.CENTER); // Center-align the header text
                return header;
            }
        });

        // Default renderer for table cells
        tblNhapHang.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
    String sql = "SELECT "
               + "ifm.ImportFormID, "
               + "s.SupplierName, "
               + "ifm.ImportDate, "
               + "COALESCE(SUM(p.Quantity), 0) AS TotalQuantityProducts, "
               + "COALESCE(SUM(ifd.Quantity), 0) AS TotalQuantityImports "
               + "FROM ImportForms ifm "
               + "JOIN Suppliers s ON ifm.SupplierID = s.SupplierID "
               + "LEFT JOIN ImportFormDetails ifd ON ifm.ImportFormID = ifd.ImportFormID "
               + "LEFT JOIN Products p ON s.SupplierID = p.SupplierID "
               + "GROUP BY ifm.ImportFormID, s.SupplierName, ifm.ImportDate";

    try (java.sql.Connection conn = XJdbc.getConnection();
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

        try (java.sql.ResultSet rs = pstmt.executeQuery()) {
            DefaultTableModel tableModel = (DefaultTableModel) tblNhapHang.getModel();
            tableModel.setRowCount(0); // Xóa tất cả các hàng hiện tại

            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("ImportFormID"),          // Mã phiếu nhập
                    rs.getString("SupplierName"),          // Tên nhà cung cấp
                    rs.getDate("ImportDate"),              // Ngày nhập
                    rs.getInt("TotalQuantityProducts"),    // Tổng số lượng sản phẩm từ bảng Products
                    rs.getInt("TotalQuantityImports")      // Tổng số lượng sản phẩm nhập từ bảng ImportFormDetails
                };
                tableModel.addRow(row);
            }

            tblNhapHang.setModel(tableModel);

        }

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    
    
    protected List<ImportForm> selectBySql(String sql, Object... args) {
    List<ImportForm> list = new ArrayList<>();
    try {
        java.sql.ResultSet rs = null;
        try {
            rs = XJdbc.query(sql, args);
            while (rs.next()) {
                ImportForm entity = new ImportForm();
                entity.setImportFormID(rs.getString("ImportFormID"));
                entity.setSupplierName(rs.getString("SupplierName"));
                entity.setImportDate(rs.getDate("ImportDate"));
                entity.setTotalAmount(rs.getBigDecimal("TotalAmount"));
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
        jPanel2 = new javax.swing.JPanel();
        button3 = new com.inventory.swing.Button();
        btnAddExcel = new com.inventory.swing.Button();
        jPanel3 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tblNhapHang = new com.inventory.swing.Table();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        textField3 = new com.inventory.swing.TextField();
        jLabel4 = new javax.swing.JLabel();
        textField1 = new com.inventory.swing.TextField();
        button1 = new com.inventory.swing.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(878, 633));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        button3.setBackground(new java.awt.Color(102, 102, 255));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Tạo phiếu nhập");
        button3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        btnAddExcel.setBackground(new java.awt.Color(102, 102, 255));
        btnAddExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnAddExcel.setText("Excel");
        btnAddExcel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnAddExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(526, Short.MAX_VALUE)
                .addComponent(btnAddExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setLayout(new java.awt.BorderLayout());

        spTable.setBackground(new java.awt.Color(255, 255, 255));
        spTable.setBorder(null);

        tblNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã nhập hàng", "Nhà cung cấp", "Ngày Nhập", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tblNhapHang);

        jPanel3.add(spTable, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setText("Từ ngày");

        textField3.setBackground(new java.awt.Color(72, 142, 174));
        textField3.setForeground(new java.awt.Color(255, 255, 255));
        textField3.setCaretColor(new java.awt.Color(255, 255, 255));
        textField3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setText("Đến ngày");

        textField1.setBackground(new java.awt.Color(72, 142, 174));
        textField1.setForeground(new java.awt.Color(255, 255, 255));
        textField1.setCaretColor(new java.awt.Color(255, 255, 255));
        textField1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        button1.setBackground(new java.awt.Color(102, 102, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Tìm Kiếm");
        button1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(34, 34, 34)
                .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addGap(34, 34, 34)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        JDialog add = new JDialog();
        Model_Add_EnterCoupon model = new Model_Add_EnterCoupon();
        add.setUndecorated(true);
        add.getContentPane().add(model);
        add.pack();
        add.setLocationRelativeTo(this);
        add.setVisible(true);
    }//GEN-LAST:event_button3ActionPerformed

    private void btnAddExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddExcelActionPerformed
        JDialog add = new JDialog();
        ImportExcel model = new ImportExcel();
        add.setUndecorated(true);
        add.getContentPane().add(model);
        add.pack();
        add.setLocationRelativeTo(this);
        add.setVisible(true);
    }//GEN-LAST:event_btnAddExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button btnAddExcel;
    private com.inventory.swing.Button button1;
    private com.inventory.swing.Button button3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane spTable;
    private com.inventory.swing.Table tblNhapHang;
    private com.inventory.swing.TextField textField1;
    private com.inventory.swing.TextField textField3;
    // End of variables declaration//GEN-END:variables
}
