package com.inventory.swing.glasspanepopup;

import java.awt.Color;
import java.awt.Component;

public class ModalErrorOption implements Option {

    private float animate;
    @Override
    public String getLayout(Component parent, float animate) {
        this.animate = animate;
        return "pos 0.5al 0.5al, w 80%";
    }

    @Override
    public boolean useSnapshot() {
        return false;
    }
    
    @Override
    public boolean closeWhenPressedEsc() {
        return true;
    }

    @Override
    public boolean closeWhenClickOutside() {
        return false;
    }

    @Override
    public boolean blockBackground() {
        return true;
    }

    @Override
    public Color background() {
        return new Color(100, 100, 100);
    }

    @Override
    public float opacity() {
        return 0.5f;
    }

    @Override
    public int duration() {
        return 300;
    }

    @Override
    public float getAnimate() {
        return animate;
    }

    @Override
    public void setAnimate(float animate) {
        this.animate = animate;
    }
}
