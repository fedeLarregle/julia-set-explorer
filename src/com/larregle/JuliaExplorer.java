package com.larregle;

import com.larregle.fractal.JuliaSet;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class JuliaExplorer extends JPanel implements KeyListener {
    private final Set<Character> keyPressed;
    private int zoomPower;
    private float xPower;
    private float yPower;

    public JuliaExplorer() {
        keyPressed = new HashSet<>();
        zoomPower = 5;
        xPower = 0.00001F;
        yPower = 0.00001F;
        setPreferredSize(new Dimension(JuliaSet.WIDTH, JuliaSet.HEIGHT));
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow();
    }


    @Override
    protected void paintComponent(Graphics g) {
        try {
            g.drawImage(JuliaSet.getInstance().generateFractalImage(), 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ADD) {
            if (keyPressed.contains((char) KeyEvent.VK_CONTROL)) {
                zoomPower *= 2;
            }
            xPower /= 0.1;
            yPower /= 0.1;
        } else if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            keyPressed.add((char) KeyEvent.VK_CONTROL);
        } else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
            if (keyPressed.contains((char) KeyEvent.VK_CONTROL)) {
                zoomPower /= 2;
            } else {
                xPower *= 0.1;
                yPower *= 0.1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            JuliaSet.getInstance().setZoom(JuliaSet.getInstance().getZoom() + zoomPower);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            JuliaSet.getInstance().setMoveX(JuliaSet.getInstance().getMoveX() - xPower);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            JuliaSet.getInstance().setMoveX(JuliaSet.getInstance().getMoveX() + xPower);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            JuliaSet.getInstance().setMoveY(JuliaSet.getInstance().getMoveY() - yPower);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            JuliaSet.getInstance().setMoveY(JuliaSet.getInstance().getMoveY() + yPower);
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            JuliaSet.getInstance().setZoom(JuliaSet.getInstance().getZoom() - zoomPower);
        } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
            JuliaSet.getInstance().setComplex(JuliaSet.getInstance().nextComplex());
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        paintComponent(getGraphics());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            keyPressed.remove((char)KeyEvent.VK_CONTROL);
        }
    }
}
