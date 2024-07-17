package com.inventory.swing;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ComboSuggestionUI extends BasicComboBoxUI {

    private static final int ROUND = 10;
    private static final Insets SHADOW_SIZE = new Insets(2, 5, 4, 5);
    private BufferedImage imageShadow;
    private Color shadowColor = new Color(170, 170, 170);

    @Override
    public void installUI(JComponent jc) {
        super.installUI(jc);
        Border border = new Border();
        JTextField txt = (JTextField) comboBox.getEditor().getEditorComponent();
        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                border.setColor(new Color(128, 189, 255));
            }

            @Override
            public void focusLost(FocusEvent fe) {
                border.setColor(new Color(206, 212, 218));
            }
        });
        comboBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
                arrowButton.setBackground(new Color(180, 180, 180));
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
                arrowButton.setBackground(new Color(150, 150, 150));
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent pme) {
                arrowButton.setBackground(new Color(150, 150, 150));
            }
        });
        AutoCompleteDecorator.decorate(comboBox);
        txt.setSelectionColor(new Color(54, 189, 248));
        txt.setBorder(new EmptyBorder(0, 4, 0, 4));
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(border);
        comboBox.setOpaque(false);
    }

    @Override
    protected JButton createArrowButton() {
        return new ArrowButton();
    }

    @Override
    protected ComboPopup createPopup() {
        return new ComboSuggestionPopup(comboBox);
    }

    @Override
    protected ListCellRenderer<Object> createRenderer() {
        return new ListCellRenderer<>() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                String text = value == null ? "" : value.toString();
                JLabel label = new JLabel(text);
                label.setFont(comboBox.getFont());
                if (index >= 0) {
                    label.setBorder(new EmptyBorder(5, 8, 5, 8));
                } else {
                    label.setBorder(null);
                }
                if (isSelected) {
                    label.setOpaque(true);
                    label.setBackground(new Color(240, 240, 240));
                    label.setForeground(new Color(17, 155, 215));
                }
                return label;
            }
        };
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = c.getWidth() - (SHADOW_SIZE.left + SHADOW_SIZE.right);
        int height = c.getHeight() - (SHADOW_SIZE.top + SHADOW_SIZE.bottom);
        int x = SHADOW_SIZE.left;
        int y = SHADOW_SIZE.top;

        if (imageShadow == null || width != imageShadow.getWidth() || height != imageShadow.getHeight()) {
            createImageShadow(width, height);
        }

        g2.drawImage(imageShadow, 0, 0, null);

        g2.setColor(c.getBackground());
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, ROUND, ROUND));
        g2.fill(area);

        super.paint(g, c);
    }

    private void createImageShadow(int width, int height) {
        imageShadow = new BufferedImage(width + SHADOW_SIZE.left + SHADOW_SIZE.right,
                height + SHADOW_SIZE.top + SHADOW_SIZE.bottom, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imageShadow.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BufferedImage shadow = createShadowImage(width, height);
        g2.drawImage(shadow, SHADOW_SIZE.left, SHADOW_SIZE.top, null);

        g2.dispose();
    }

    private BufferedImage createShadowImage(int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, ROUND, ROUND));
        g2.dispose();
        return new ShadowRenderer(5, 0.3f, shadowColor).createShadow(img);
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        // Do nothing
    }

    private class ComboSuggestionPopup extends BasicComboPopup {

        public ComboSuggestionPopup(JComboBox<?> combo) {
            super((JComboBox) combo);
            setBorder(new Border(1));
        }

        @Override
        protected JScrollPane createScroller() {
            JScrollPane scroll = super.createScroller();
            list.setBackground(Color.WHITE);
            ScrollBarCustom sb = new ScrollBarCustom();
            sb.setPreferredSize(new Dimension(12, 70));
            scroll.setVerticalScrollBar(sb);
            ScrollBarCustom sbH = new ScrollBarCustom();
            sbH.setOrientation(JScrollBar.HORIZONTAL);
            scroll.setHorizontalScrollBar(sbH);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            return scroll;
        }
    }

    private class ArrowButton extends JButton {

        public ArrowButton() {
            setContentAreaFilled(false);
            setBorder(new EmptyBorder(5, 5, 5, 5));
            setBackground(new Color(150, 150, 150));
        }

        @Override
        public void paint(Graphics grphcs) {
            super.paint(grphcs);
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();
            int sizeX = 12;
            int sizeY = 8;
            int x = (width - sizeX) / 2;
            int y = (height - sizeY) / 2;
            int[] px = {x, x + sizeX, x + sizeX / 2};
            int[] py = {y, y, y + sizeY};
            g2.setColor(getBackground());
            g2.fillPolygon(px, py, px.length);
            g2.dispose();
        }
    }

    private class Border extends EmptyBorder {

        private Color focusColor = new Color(128, 189, 255);
        private Color color = new Color(206, 212, 218);

        public Color getFocusColor() {
            return focusColor;
        }

        public void setFocusColor(Color focusColor) {
            this.focusColor = focusColor;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Border(int border) {
            super(border, border, border, border);
        }

        public Border() {
            this(5);
        }

        @Override
        public void paintBorder(Component cmpnt, Graphics grphcs, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (cmpnt.isFocusOwner()) {
                g2.setColor(focusColor);
            } else {
                g2.setColor(color);
            }
            int rectWidth = width - SHADOW_SIZE.left - SHADOW_SIZE.right - 1;
            int rectHeight = height - SHADOW_SIZE.top - SHADOW_SIZE.bottom - 1;
            g2.drawRoundRect(x + SHADOW_SIZE.left, y + SHADOW_SIZE.top, rectWidth, rectHeight, ROUND, ROUND);
            g2.dispose();
        }
    }
}
