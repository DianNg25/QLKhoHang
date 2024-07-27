package com.inventory.swing.glasspanepopup;

import java.awt.CardLayout;
import java.awt.Component;
import java.lang.System.Logger;
import javax.swing.*;
//import org.slf4j.LoggerFactory;

public class ModalErrorGlassPanePopup {

//    private static final Logger logger = LoggerFactory.getLogger(ModalErrorGlassPanePopup.class);
    public static ModalErrorGlassPanePopup instance;
    private JLayeredPane layerPane;
    private JDialog currentDialog;

    private ModalErrorGlassPanePopup() {
        layerPane = new JLayeredPane();
        layerPane.setLayout(new CardLayout());
    }

    public static void showPopup(JDialog dialog, Component component, Option option) {
        if (instance == null || instance.currentDialog != dialog) { // Check if dialog has changed
            install(dialog);
        }
        ModalErrorPopup popup = new ModalErrorPopup(dialog, component, option);
        instance.layerPane.add(popup, 0);
        popup.setVisible(true);
        popup.setShowPopup(true);
        instance.layerPane.setVisible(true); // Đảm bảo layerPane hiển thị
    }

    public static void closePopup(String name) {
        for (Component com : instance.layerPane.getComponents()) {
            if (com.getName() != null && com.getName().equals(name)) {
                if (com instanceof Popup) {
                    Popup popup = (Popup) com;
                    popup.setShowPopup(false);
                }
            }
        }
    }

    public static void install(JDialog dialog) {
        instance = new ModalErrorGlassPanePopup();
        instance.currentDialog = dialog; // Store the current dialog
        dialog.setGlassPane(instance.layerPane);
    }

    public static void closePopupAll() {
        for (Component com : instance.layerPane.getComponents()) {
            if (com instanceof Popup) {
                Popup popup = (Popup) com;
                popup.setShowPopup(false);
            }
        }
    }

    public static void closePopupLast() {
        if (instance != null && instance.layerPane != null) { // Check if instance and layerPane exist
            int count = instance.layerPane.getComponentCount();
            if (count > 0) {
                Component com = instance.layerPane.getComponent(count - 1); // Get the last popup
                if (com instanceof ModalErrorPopup || com instanceof JDialog) {
                    // Cast to the appropriate type
                    if (com instanceof ModalErrorPopup) {
                        ((ModalErrorPopup) com).setShowPopup(false);
                    } else {
                        ((JDialog) com).dispose();
                    }
                }
            }
        }
    }

    public static int getPopupCount() {
        return instance.layerPane.getComponentCount();
    }

    protected synchronized void removePopup(Component popup, JDialog parentDialog) {
        if (layerPane != null && parentDialog != null && parentDialog.getGlassPane() == layerPane) { // Kiểm tra tất cả các điều kiện
            layerPane.remove(popup);
            if (layerPane.getComponentCount() == 0) {
                layerPane.setVisible(false);
                if (parentDialog.getGlassPane() == layerPane) { // Kiểm tra lại glassPane trước khi set null
                    parentDialog.setGlassPane(null);
                } else {
                    System.out.println("Không thể đặt glassPane thành null vì nó đã bị thay đổi.");
                }
            }
        } else {
            System.out.println("Không thể xóa popup vì layerPane hoặc parentDialog là null, hoặc glassPane không khớp.");
        }
    }
}
