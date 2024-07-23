package com.inventory.main;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import com.inventory.main.Login;
import javax.swing.SwingUtilities;

public class LoadingScreen extends javax.swing.JFrame {

    private final Main mainFrame;
    private boolean loginPageOpened = false;

    public LoadingScreen(Main mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        getContentPane().setBackground(new Color(221, 221, 221));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        curvesPanel1 = new com.inventory.swing.CurvesPanel();
        jLabel1 = new javax.swing.JLabel();
        pro = new com.inventory.swing.ProgressBarCustom();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventory/icon/logo_sg_1 (1).png"))); // NOI18N

        lblStatus.setForeground(new java.awt.Color(200, 200, 200));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Status");

        javax.swing.GroupLayout curvesPanel1Layout = new javax.swing.GroupLayout(curvesPanel1);
        curvesPanel1.setLayout(curvesPanel1Layout);
        curvesPanel1Layout.setHorizontalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addContainerGap(247, Short.MAX_VALUE)
                .addGroup(curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(247, Short.MAX_VALUE))
        );
        curvesPanel1Layout.setVerticalGroup(
            curvesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvesPanel1Layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pro, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(curvesPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(curvesPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doTask("Đang kết nối SQL Server.", 10);
                    doTask("Đang kết nối SQL Server..", 12);
                    doTask("Đang kết nối SQL Server...", 14);
                    doTask("Đang kết nối SQL Server....", 18);

                    doTask("Đang kiểm tra tồn kho.", 20);
                    doTask("Đang kiểm tra tồn kho..", 22);
                    doTask("Đang kiểm tra tồn kho...", 24);
                    doTask("Đang kiểm tra tồn kho....", 28);

                    doTask("Đang nhận đơn hàng mới.", 30);
                    doTask("Đang nhận đơn hàng mới..", 32);
                    doTask("Đang nhận đơn hàng mới...", 34);
                    doTask("Đang nhận đơn hàng mới....", 38);

                    doTask("Đang cập nhật trạng thái đơn hàng.", 40);
                    doTask("Đang cập nhật trạng thái đơn hàng..", 42);
                    doTask("Đang cập nhật trạng thái đơn hàng...", 44);
                    doTask("Đang cập nhật trạng thái đơn hàng....", 48);

                    doTask("Đang kiểm tra mức độ tồn kho.", 50);
                    doTask("Đang kiểm tra mức độ tồn kho..", 52);
                    doTask("Đang kiểm tra mức độ tồn kho...", 54);
                    doTask("Đang kiểm tra mức độ tồn kho....", 58);

                    doTask("Đang tạo báo cáo tồn kho.", 60);
                    doTask("Đang tạo báo cáo tồn kho..", 62);
                    doTask("Đang tạo báo cáo tồn kho...", 64);
                    doTask("Đang tạo báo cáo tồn kho....", 68);

                    doTask("Đang kiểm tra lịch sử giao dịch.", 70);
                    doTask("Đang kiểm tra lịch sử giao dịch..", 72);
                    doTask("Đang kiểm tra lịch sử giao dịch...", 74);
                    doTask("Đang kiểm tra lịch sử giao dịch....", 78);

                    doTask("Đang tối ưu hóa kho hàng.", 80);
                    doTask("Đang tối ưu hóa kho hàng..", 82);
                    doTask("Đang tối ưu hóa kho hàng...", 84);
                    doTask("Đang tối ưu hóa kho hàng....", 88);

                    doTask("Đang kiểm tra và bảo trì hệ thống.", 90);
                    doTask("Đang kiểm tra và bảo trì hệ thống..", 92);
                    doTask("Đang kiểm tra và bảo trì hệ thống...", 94);
                    doTask("Đang kiểm tra và bảo trì hệ thống....", 98);

                    doTask("Hoàn tất công việc...", 99);
                    doTask("Mở ứng dụng", 100);

                    dispose();
                    curvesPanel1.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }//GEN-LAST:event_formWindowOpened

    private void doTask(String taskName, int progress) throws Exception {
        lblStatus.setText(taskName);
        Thread.sleep(100);
        pro.setValue(progress);

        if (progress == 100 && !loginPageOpened) {
            openLoginPage();
            loginPageOpened = true;
        }
    }

    private void openLoginPage() {
        try {
            Login login = new Login(mainFrame);
            login.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể mở trang chính: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LoadingScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.inventory.swing.CurvesPanel curvesPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblStatus;
    private com.inventory.swing.ProgressBarCustom pro;
    // End of variables declaration//GEN-END:variables
}
