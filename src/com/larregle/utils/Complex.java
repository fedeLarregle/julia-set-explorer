package com.larregle.utils;

public class Complex {

    private double x;
    private double y;

    public Complex() {
        this(0, 0);
    }

    public Complex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
