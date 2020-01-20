package com.polytech.sma.model;

/**
 *
 * @author Stefano
 */
public class Slot {
    private int value;
    private int x,y;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Slot(int x, int y) {
        this.value = 0;
        this.x = x;
        this.y = y;
    }
}