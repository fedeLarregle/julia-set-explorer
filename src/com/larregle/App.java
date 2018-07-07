package com.larregle;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.EventQueue;

public class App {
    public static void main(String... args) {
        EventQueue.invokeLater(()-> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setDefaultLookAndFeelDecorated(true);
            f.setResizable(false);
            JuliaExplorer je = new JuliaExplorer();
            f.add(je, BorderLayout.CENTER);
            f.pack();
            f.setVisible(true);
        });
    }
}
