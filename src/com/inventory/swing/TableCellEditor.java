package com.inventory.swing;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableCellEditor extends DefaultCellEditor {

    private DefaultTableModel model;

    public TableCellEditor() {
        super(new JCheckBox());
    }

//    @Override
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        model = (DefaultTableModel) value;
//        JScrollPane js = new JScrollPane();
//        JTable jtable = new JTable();
//        js.setViewportView(jtable);
//        return js;
//    }

//    @Override
//    public Object getCellEditorValue() {
//        return model;
//    }
}
