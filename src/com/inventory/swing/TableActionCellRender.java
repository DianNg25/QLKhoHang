/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author WINDOWS
 */
public class TableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column) {
        PanelAction action = new PanelAction();
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);
        if (isSeleted == false && row % 2 == 0) {
            action.setBackground(Color.WHITE);
        } else {
              action.setBackground(new Color(250, 250, 250));
        }

        return action;
    }
}
