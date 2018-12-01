package com.wemanity.kata.tdd.bejeweledlike.view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static final int BOX_SIZE = 32;

    public void create(int size) {
        final GameView gameView = new GameView(size);
        final JFrame window = new JFrame();
        window.setTitle("Bejeweled Like");
        window.setSize(size * BOX_SIZE + 5, size * BOX_SIZE + 45);
        window.setResizable(false);
        window.setBackground(Color.WHITE);
        window.add(gameView, BorderLayout.CENTER);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().validate();
        window.setVisible(true);
        gameView.init();
    }

}
