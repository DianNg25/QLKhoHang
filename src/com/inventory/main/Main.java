package com.inventory.main;

import com.inventory.form.*;
import com.inventory.form.Form_Home;
import com.inventory.message.LogoutPanel;
import com.inventory.swing.glasspanepopup.GlassPanePopup;
import com.inventory.utils.Auth;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class Main extends javax.swing.JFrame {

    public Main() {
        setUndecorated(true);
        showLoadAndLogin();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.inventory.swing.PanelBorder();
        menu = new com.inventory.component.Menu();
        mainPanel = new javax.swing.JPanel();
        header1 = new com.inventory.component.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> new Main());

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.component.Header header1;
    private javax.swing.JPanel mainPanel;
    private com.inventory.component.Menu menu;
    private com.inventory.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables

    private void showLoadAndLogin() {
        LoadingScreen loading = new LoadingScreen(this);
        loading.setVisible(true);
    }

    public void showAfterSuccessfulLogin() {
        initComponents();
        init();
        this.setVisible(true);
        menu.setEnabledAt(7, Auth.isAdmin()); // Check and set visibility of menu item 7
        this.setVisible(true);
    }

    private void init() {
        setBackground(new Color(0, 0, 0, 0));
        menu.initMoving(Main.this);
        GlassPanePopup.install(this);
        menu.addEventMenuSelected((int index) -> {
            switch (index) {
                case 0 ->
                    setForm(new Form_Home());
                case 1 ->
                    setForm(new Form_1());
                case 2 ->
                    setForm(new Form_2());
                case 3 ->
                    setForm(new Form_3());
                case 4 ->
                    setForm(new Form_4());
                case 5 ->
                    setForm(new Form_5());
                case 6 ->
                    setForm(new Form_6());
                case 7 ->
                    setForm(new Form_7());
                default ->
                    logoutForm();
            }
        });
        setForm(new Form_Home());
    }

    private void logoutForm() {
        LogoutPanel logoutPanel = new LogoutPanel();
        logoutPanel.eventOK(evt -> {
            GlassPanePopup.closePopupLast();
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                Login login = new Login(this);
                login.setVisible(true);
                // Reset the selected menu index in the new Main instance
                login.getMainFrame().menu.resetSelectedIndex();
            });
        });
        GlassPanePopup.showPopup(logoutPanel);
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

}
