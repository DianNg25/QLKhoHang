/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.swing;

import com.inventory.form.Model_Menu;
import java.awt.Component;
import java.awt.MenuItem;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author ADMIN
 */
public class ListMenu<E extends Object> extends JList<E> {

    private final DefaultListModel model;
    public ListMenu() {
        model = new DefaultListModel();
        setModel(model);
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            Model_Menu data;

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Model_Menu data;
                if (value instanceof Model_Menu) {
                    data = (Model_Menu) value;
                } else {
                    data = new Model_Menu("", value + "", Model_Menu.MenuType.EMPTY);
                }
                MenuItems item = new MenuItems(data);
                return item;
            }

        };
    }
    public void addItem(Model_Menu data){
        model.addElement(data);
    }
}
