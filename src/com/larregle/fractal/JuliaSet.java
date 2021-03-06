package com.larregle.fractal;

import com.larregle.utils.Complex;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class JuliaSet {

    private static final JuliaSet instance;
    public static final int MAX_ITERATIONS = 2000;
    public static final int[] colors;
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    private int zoom;
    private double moveX;
    private double moveY;
    private Complex complex;
    public static final Complex[] COMPLEX_SETS;
    private int currentSet;

    static {
        COMPLEX_SETS = new Complex[]{
                new Complex(-0.4,0.6),
                new Complex(0.285, 0),
                new Complex(0.285, 0.01),
                new Complex(0.45,0.1428),
                new Complex(-0.7, -0.27015),
                new Complex(-0.835,-0.2321),
                new Complex(-0.8,0.156),
                new Complex(-0.7269, 0.1889)
        };

        colors = new int[MAX_ITERATIONS];
        for (int i = 0; i<MAX_ITERATIONS; i++) {
            colors[i] = Color.HSBtoRGB(i%256, -0.1F, i/(i+640F));
        }
        instance = new JuliaSet();
    }

    private JuliaSet() {
        zoom = 1;
        moveX = 0.1F;
        moveY = 0;
        complex = COMPLEX_SETS[0];
        currentSet = 0;
    }

    public static JuliaSet getInstance() { return instance; }

    public BufferedImage generateFractalImage() throws Exception {
        final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Color color = find(mapToReal(col), mapToImaginary(row));
                image.setRGB(col, row, color.getRGB());
            }
        }

        return image;
    }

    /**
     * Finds a color for a given complex number using the 'Escape time algorithm'
     * @return the corresponding {@link Color} for a given {@link Complex} number.
     */
    private Color find(double c_r, double c_i) {
        int i = 0;
        while ( ((c_r * c_r) + (c_i * c_i)) < 4.0 && i < MAX_ITERATIONS ) {
            double new_x = c_r * c_r - c_i * c_i + complex.getX();
            c_i = 2.0 * c_r * c_i + complex.getY();
            c_r = new_x;
            i++;
        }

        return new Color( i < MAX_ITERATIONS ? colors[i] : 0x000000);
    }

    private double mapToReal(int x) {
        return 1.5 * (x - WIDTH / 2) / (0.5 * zoom * WIDTH) + moveX;
    }

    private double mapToImaginary(int y) {
        return (y - HEIGHT / 2) / (0.5 * zoom * HEIGHT) + moveY;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public double getMoveX() {
        return moveX;
    }

    public void setMoveX(double moveX) {
        this.moveX = moveX;
    }

    public double getMoveY() {
        return moveY;
    }

    public void setMoveY(double moveY) {
        this.moveY = moveY;
    }

    public Complex nextComplex() {
        if (currentSet == COMPLEX_SETS.length - 1) {
            currentSet = 0;
        } else {
            currentSet++;
        }
        return COMPLEX_SETS[currentSet]; }

    public void setComplex(Complex complex) {
        zoom = 1;
        moveX = 0.1F;
        moveY = 0;
        this.complex = complex;
    }
}
