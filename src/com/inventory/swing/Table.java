package com.inventory.swing;

import com.inventory.form.StatusType;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);

        // Renderer cho tiêu đề cột
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        getTableHeader().setDefaultRenderer(headerRenderer);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(value.toString());
                header.setHorizontalAlignment(JLabel.CENTER); // Căn giữa tiêu đề cột
                return header;
            }
        });

        // Renderer mặc định cho các ô trong bảng
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                com.setBackground(Color.WHITE);
                setBorder(noFocusBorder);
                com.setFont(new Font("sansserif", Font.PLAIN, 13)); // Thay đổi kích thước chữ thành 14
                com.setForeground(isSelected ? new Color(36, 183, 194)  : new  Color(102, 102, 102) );
                                    com.setFont(com.getFont().deriveFont(Font.BOLD));
                setHorizontalAlignment(JLabel.CENTER); // Căn giữa nội dung trong các ô
                if (column == 6) { // Cột trạng thái
                    StatusType type = (StatusType) value;
                    JLabel label = new JLabel(type.getText());
                    label.setFont(label.getFont().deriveFont(Font.BOLD));
                      label.setFont(new Font("sansserif", Font.BOLD, 13)); // Thay đổi kích thước chữ thành 14 và đậm cho cột trạng thái
                    label.setHorizontalAlignment(JLabel.CENTER); // Căn giữa nội dung trong cột trạng thái
                    if (type == StatusType.DA_XOA) {
                        label.setForeground(Color.RED); // Màu chữ đỏ cho "Đã xóa"
                    } else if (type == StatusType.BINH_THUONG) {
                        label.setForeground(Color.GREEN); // Màu chữ xanh lá cho "Bình thường"
                    }
                    return label;
                }
                return com;
            }
        });

    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
}
