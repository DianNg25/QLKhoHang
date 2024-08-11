package com.inventory.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class TableCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Gọi phương thức của lớp cha để lấy thành phần mặc định cho ô
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Tùy chỉnh giao diện của ô
        if (isSelected) {
            cellComponent.setBackground(table.getSelectionBackground());
            cellComponent.setForeground(table.getSelectionForeground());
        } else {
            cellComponent.setBackground(table.getBackground());
            cellComponent.setForeground(table.getForeground());

            // Ví dụ: Thay đổi màu nền dựa trên giá trị của ô
            if (value != null && value.toString().equals("Điều kiện nào đó")) {
                cellComponent.setBackground(Color.YELLOW);
            }
        }
        
        return cellComponent;
    }
}