package com.inventory.swing.glasspanepopup;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class ModalErrorPopup extends JComponent {

    public static ModalErrorGlassPanePopup instance;
    private final JDialog modalDialog;
    private final Component component;
    private final Option option;
    private Animator animator;
    private MigLayout layout;
    private float animate;
    private boolean show;
    private boolean mouseHover;

    public ModalErrorPopup(JDialog modalDialog, Component component, Option option) {
        this.modalDialog = modalDialog;
        this.component = component;
        this.option = option;
        init();
        initAnimator();
    }

    private void init() {
        layout = new MigLayout();
        initOption();
        setLayout(layout);
        add(component, option.getLayout(modalDialog, 0));
    }

    private void initOption() {
        if (option.closeWhenPressedEsc()) {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    mouseHover = true;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    mouseHover = false;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (!new Rectangle(0, 0, getWidth(), getHeight()).contains(e.getPoint())) {
                        setShowPopup(false);
                    }
                }
            });
        } else if (option.blockBackground()) {
            addMouseListener(new MouseAdapter() {
            });
        }

        if (option.closeWhenPressedEsc()) {
            modalDialog.getRootPane().registerKeyboardAction(e -> setShowPopup(false),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                    JComponent.WHEN_IN_FOCUSED_WINDOW);
        }
    }

    private void initAnimator() {
        animator = new Animator(option.duration(), new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (show) {
                    animate = fraction;
                } else {
                    animate = 1f - fraction;
                }
                float f = format(animate);
                option.setAnimate(f);
                // Sửa lỗi
                JDialog parentDialog = (JDialog) SwingUtilities.getWindowAncestor(component);
                String lc = option.getLayout(parentDialog.getLayeredPane(), f);

                if (lc != null) {
                    layout.setComponentConstraints(component, lc);
                }
                repaint();
                revalidate();
            }

            @Override
            public void begin() {
            }

            @Override
            public void end() {
                if (!show) {
                    JDialog parentDialog = (JDialog) SwingUtilities.getWindowAncestor(component);
                    if (parentDialog != null) { // Kiểm tra parentDialog trước khi removePopup
                        ModalErrorGlassPanePopup.instance.removePopup(ModalErrorPopup.this, parentDialog);
                    }
                }
            }
        }
        );
        animator.setAcceleration(
                .5f);
        animator.setDeceleration(
                .5f);
        animator.setResolution(
                5);
    }

    public void setShowPopup(boolean show) {
        if (this.show != show) {
            if (animator.isRunning()) {
                float f = animator.getTimingFraction();
                animator.stop();
                animator.setStartFraction(1f - f);
            } else {
                animator.setStartFraction(0);
            }
            this.show = show;
            animator.start();
            if (!show) {
                uninstallOption();
            }
        }
    }

    private void uninstallOption() {
        if (getMouseListeners().length != 0) {
            removeMouseListener(getMouseListeners()[0]);
        }
        if (option.closeWhenPressedEsc()) {
            unregisterKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.SrcOver.derive(animate * option.opacity()));
        g2.setColor(option.background());
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        g2.setComposite(AlphaComposite.SrcOver.derive(animate));
        super.paintComponent(g);
    }

    private float format(float v) {
        int a = Math.round(v * 100);
        return a / 100f;
    }
}
