package com.inventory.swing;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TableCellRender implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        DefaultTableModel model = (DefaultTableModel) o;
        JScrollPane js = new JScrollPane();
        JTable tbl = new JTable(model);
        js.setViewportView(tbl);
        return js;
    }
}
