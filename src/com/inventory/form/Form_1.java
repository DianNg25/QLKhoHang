/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.inventory.form;

import com.inventory.main.Login;
import com.inventory.message.LogoutPanel;
import com.inventory.swing.ScrollBar;
import com.inventory.swing.TableActionCellEditor;
import com.inventory.swing.TableActionCellRender;
import com.inventory.swing.TableActionEvent;
import com.inventory.swing.TableHeader;
import com.inventory.utils.XJdbc;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Form_1 extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    public Form_1() {
        initComponents();
          customizeTable();
             loadData();
             
             
             
        //thông tin chỉnh sửa của table
//        spTable.setVerticalScrollBar(new ScrollBar());
//        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
//        spTable.getViewport().setBackground(Color.WHITE);
//        JPanel p = new JPanel();
//        p.setBackground(Color.WHITE);
//        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
//
//        TableActionEvent event = new TableActionEvent() {
//
//            @Override
//            public void onEdit(int row) {
//                System.out.println("Edit row : " + row);
//            }
//
//            @Override
//            public void onDelete(int row) {
//                if (table.isEditing()) {
//                    table.getCellEditor().stopCellEditing();
//                }
//                DefaultTableModel model = (DefaultTableModel) table.getModel();
//                model.removeRow(row);
//            }
//
//        };
//
//        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Loại", "Màu", "Số lượng", "Giá", "Trạng thái", "Thao tác"};
//        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
//        table.setModel(model);
//
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer());
//        }
//
//        table.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
//        table.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event));
//
//        Object[][] rowData = {
//            {"SP001", "Điện thoại Samsung", "mini", "Đỏ", "15", "120000", "Bình thường", ""},
//            {"SP002", "Điện thoại Nokia", "classic", "Xanh", "10", "90000", "Tốt", ""},
//            {"SP003", "Điện thoại iPhone", "pro", "Đen", "20", "200000", "Mới", ""},
//            {"SP004", "Máy tính bảng Samsung", "tab", "Trắng", "8", "300000", "Bình thường", ""},
//            {"SP005", "Máy tính bảng Apple", "pro", "Bạc", "5", "500000", "Mới", ""},
//            {"SP006", "Tai nghe Sony", "earbud", "Đen", "25", "50000", "Tốt", ""},
//            {"SP007", "Tai nghe Bose", "over-ear", "Trắng", "12", "150000", "Bình thường", ""},
//            {"SP008", "Laptop Dell", "ultrabook", "Xám", "7", "700000", "Mới", ""},
//            {"SP009", "Laptop HP", "notebook", "Bạc", "9", "600000", "Tốt", ""},
//            {"SP010", "Smartwatch Garmin", "sport", "Xanh lá", "30", "250000", "Bình thường", ""}
//        };
//
//        for (Object[] row : rowData) {
//            model.addRow(row);
//        }

//         loadDataFromDatabase(model);
    }

    
    private void customizeTable() {
    String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Loại", "Màu", "Số lượng", "Giá", "Trạng thái", "Thao tác"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    table.setModel(model);

    spTable.setVerticalScrollBar(new ScrollBar());
    spTable.getVerticalScrollBar().setBackground(Color.WHITE);
    spTable.getViewport().setBackground(Color.WHITE);
    JPanel p = new JPanel();
    p.setBackground(Color.WHITE);
    spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

    table.setShowHorizontalLines(true);
    table.setGridColor(new Color(230, 230, 230));
    table.setRowHeight(40);

    // Renderer for table header
    DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
    headerRenderer.setHorizontalAlignment(JLabel.CENTER);
    table.getTableHeader().setDefaultRenderer(headerRenderer);
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel headerLabel = new JLabel(value.toString());
            headerLabel.setHorizontalAlignment(JLabel.CENTER); // Center align column headers
            headerLabel.setFont(new Font("sansserif", Font.BOLD, 14)); // Set font for headers
            headerLabel.setBackground(Color.LIGHT_GRAY); // Background color for headers
            headerLabel.setOpaque(true);
            return headerLabel;
        }
    });

    // Default renderer for table cells
    table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            com.setBackground(Color.WHITE);
            com.setFont(new Font("sansserif", Font.PLAIN, 13));
            com.setForeground(isSelected ? new Color(36, 183, 194) : new Color(102, 102, 102));
            com.setFont(com.getFont().deriveFont(Font.BOLD));
            setHorizontalAlignment(JLabel.CENTER); // Center align cell content

            // Custom renderer for specific columns (example: status column)
            if (column == 6) { // Status column
                JLabel label = new JLabel(value.toString());
                label.setFont(new Font("sansserif", Font.BOLD, 13));
                label.setHorizontalAlignment(JLabel.CENTER); // Center align status column
                label.setForeground(value.toString().equals("Hoạt động") ? Color.GREEN : Color.RED); // Example color logic
                return label;
            }
            return com;
        }
    });

    // Set custom renderers for other specific columns if needed
    table.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
    table.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(new TableActionEvent() {
        @Override
        public void onEdit(int row) {
            System.out.println("Edit row : " + row);
        }

        @Override
        public void onDelete(int row) {
              if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

        // Hiển thị hộp thoại xác nhận
        int confirm = JOptionPane.showConfirmDialog(
            table,
            "Bạn có muốn xóa không?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Thay đổi trạng thái thành "Đã xóa"
            model.setValueAt("Đã xóa", row, 6);

            // Thiết lập lại màu sắc cho cột trạng thái
            table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JLabel label = new JLabel(value.toString());
                    label.setFont(new Font("sansserif", Font.BOLD, 13));
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setForeground(value.toString().equals("Đã xóa") ? Color.RED : Color.GREEN);
                    return label;
                }
            });

            table.repaint();
        }}
    }));
}

    
    private void loadData() {
        String[][] rowData = {
            {"SP001", "Điện thoại Samsung", "mini", "Đỏ", "15", "120000", "Bình thường", ""},
            {"SP002", "Điện thoại Nokia", "classic", "Xanh", "10", "90000", "Bình thường", ""},
            {"SP003", "Điện thoại iPhone", "pro", "Đen", "20", "200000", "Bình thường", ""},
            {"SP004", "Máy tính bảng Samsung", "tab", "Trắng", "8", "300000", "Bình thường", ""},
            {"SP005", "Máy tính bảng Apple", "pro", "Bạc", "5", "500000", "Bình thường", ""},
            {"SP006", "Tai nghe Sony", "earbud", "Đen", "25", "50000", "Bình thường", ""},
            {"SP007", "Tai nghe Bose", "over-ear", "Trắng", "12", "150000", "Bình thường", ""},
            {"SP008", "Laptop Dell", "ultrabook", "Xám", "7", "700000", "Bình thường", ""},
            {"SP009", "Laptop HP", "notebook", "Bạc", "9", "600000", "Bình thường", ""},
            {"SP010", "Smartwatch Garmin", "sport", "Xanh lá", "30", "250000", "Bình thường", ""}
        };

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (String[] row : rowData) {
            model.addRow(row);
        }
    }
    
    
    
    
    
    
    
    
    
//   private void loadDataFromDatabase(DefaultTableModel model) {
//        String query = "SELECT MaSanPham, TenSanPham, Loai, Mau, SoLuong, Gia, TrangThai FROM Products";
//        try {
//            ResultSet rs = XJdbc.executeQuery(query);
//            while (rs.next()) {
//                Object[] row = {
//                    rs.getString("MaSanPham"),
//                    rs.getString("TenSanPham"),
//                    rs.getString("Loai"),
//                    rs.getString("Mau"),
//                    rs.getInt("SoLuong"),
//                    rs.getDouble("Gia"),
//                    rs.getString("TrangThai"),
//                    ""
//                };
//                model.addRow(row);
//            }
//            rs.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelproduct2 = new com.inventory.swing.Panelproduct();
        jPanel5 = new javax.swing.JPanel();
        button3 = new com.inventory.swing.Button();
        jPanel2 = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        table = new com.inventory.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(847, 40));
        jPanel1.setPreferredSize(new java.awt.Dimension(905, 200));
        jPanel1.setLayout(new java.awt.BorderLayout());

        panelproduct2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        panelproduct2.setForeground(new java.awt.Color(204, 204, 204));
        panelproduct2.setPreferredSize(new java.awt.Dimension(905, 50));
        jPanel1.add(panelproduct2, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        button3.setBackground(new java.awt.Color(102, 102, 255));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Thêm sản phẩm");
        button3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button3.setMaximumSize(new java.awt.Dimension(94, 38));
        button3.setMinimumSize(new java.awt.Dimension(94, 38));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(629, Short.MAX_VALUE)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.Button button3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private com.inventory.swing.Panelproduct panelproduct2;
    private javax.swing.JScrollPane spTable;
    private com.inventory.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
