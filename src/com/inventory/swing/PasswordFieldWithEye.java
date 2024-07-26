/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.swing;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author ADMIN
 */
public class PasswordFieldWithEye extends JPasswordField{
   
      private final JButton toggleButton;
    private boolean isPasswordVisible;

    public PasswordFieldWithEye() {
        super();
        toggleButton = new JButton();
        isPasswordVisible = false;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setEyeIcon("/com/inventory/icon/eye.png"); // Icon for showing password
        toggleButton.setBorderPainted(false);
        toggleButton.setContentAreaFilled(false);
        toggleButton.setFocusPainted(false);

        add(toggleButton, BorderLayout.EAST);

        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility();
            }
        });

        // Hide button if password is empty
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateVisibility();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateVisibility();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateVisibility();
            }
        });
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            setEchoChar('*');
            setEyeIcon("/com/inventory/icon/eye.png");
        } else {
            setEchoChar((char) 0);
            setEyeIcon("/com/inventory/icon/eye_slash.png");
        }
        isPasswordVisible = !isPasswordVisible;
    }

    private void updateVisibility() {
        toggleButton.setVisible(getPassword().length > 0);
    }

    private void setEyeIcon(String iconPath) {
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(iconPath)));
            if (icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
                throw new IOException("Icon could not be loaded from path: " + iconPath);
            }
            toggleButton.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
            // Optional: Add a default icon or an error message
        }
    }
}
