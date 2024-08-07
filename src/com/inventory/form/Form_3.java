/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.entity.ExportFormsTable;
import com.inventory.swing.ScrollBar;
import com.inventory.swing.TableHeader;
import com.inventory.utils.XJdbc;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Form_3 extends javax.swing.JPanel {
    
    private ExportFormsTable exportFormsTable;
    
    public Form_3() {
        initComponents();
        loadExportFormsData();
        customizeTable();
    }
    
     private void customizeTable() {
        spTable.setVerticalScrollBar(new JScrollBar()); // Use JScrollBar instead of ScrollBar
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        tblXuatKho.setShowHorizontalLines(true);
        tblXuatKho.setGridColor(new Color(230, 230, 230));
        tblXuatKho.setRowHeight(40);

        // Renderer for column headers
        // Renderer for column headers
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblXuatKho.getTableHeader().setDefaultRenderer(headerRenderer);
        tblXuatKho.getTableHeader().setReorderingAllowed(false);
        tblXuatKho.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value.toString());
                header.setHorizontalAlignment(JLabel.CENTER); // Center-align the header text
                return header;
            }
        });

        // Default renderer for table cells
        // Default renderer for table cells
        tblXuatKho.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
                if (column == 4) { // Status column
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

    
    
    
    
    private void loadExportFormsData() {
    String sql = "SELECT ExportFormID, CustomerID, ExportDate, TotalAmount, Status FROM ExportForms";

    try {
        List<ExportFormsTable> exportFormsList = selectExportFormsBySql(sql);

        DefaultTableModel tableModel = (DefaultTableModel) tblXuatKho.getModel(); // tblExportForms là JTable hiển thị dữ liệu
        tableModel.setRowCount(0); // Xóa tất cả các hàng hiện tại

        for (ExportFormsTable exportForm : exportFormsList) {
            Object[] row = new Object[]{
                exportForm.getExportFormID(),
                exportForm.getCustomerID(),
                exportForm.getExportDate(),
                exportForm.getTotalAmount(),
                exportForm.getStatus()
            };
            tableModel.addRow(row);
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading data from database.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    protected List<ExportFormsTable> selectExportFormsBySql(String sql, Object... args) {
    List<ExportFormsTable> list = new ArrayList<>();
    try {
        java.sql.ResultSet rs = null;
        try {
            rs = XJdbc.query(sql, args);
            while (rs.next()) {
                ExportFormsTable entity = new ExportFormsTable();
                entity.setExportFormID(rs.getString("ExportFormID"));
                entity.setCustomerID(rs.getString("CustomerID"));
                entity.setExportDate(rs.getDate("ExportDate"));
                entity.setTotalAmount(rs.getDouble("TotalAmount"));
                entity.setStatus(rs.getString("Status"));
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
        jPanel6 = new javax.swing.JPanel();
        button3 = new com.inventory.swing.Button();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        textField3 = new com.inventory.swing.TextField();
        jLabel4 = new javax.swing.JLabel();
        textField1 = new com.inventory.swing.TextField();
        button1 = new com.inventory.swing.Button();
        jPanel2 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        tblXuatKho = new com.inventory.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(905, 649));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 200));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        button3.setBackground(new java.awt.Color(102, 102, 255));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Tạo phiếu xuất");
        button3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel1.add(jPanel6, java.awt.BorderLayout.PAGE_END);

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
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        spTable.setBorder(null);

        tblXuatKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã PX", "Mã KH", "Ngày xuất", "Tổng tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tblXuatKho);

        jPanel2.add(spTable, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
       JDialog add = new JDialog();
        Model_Add_ProductDelivery model = new  Model_Add_ProductDelivery();
        add.setUndecorated(true);
        add.getContentPane().add(model);
        add.pack();
        add.setLocationRelativeTo(this);
        add.setVisible(true);
    }//GEN-LAST:event_button3ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button button1;
    private com.inventory.swing.Button button3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane spTable;
    private com.inventory.swing.Table tblXuatKho;
    private com.inventory.swing.TextField textField1;
    private com.inventory.swing.TextField textField3;
    // End of variables declaration//GEN-END:variables
}
