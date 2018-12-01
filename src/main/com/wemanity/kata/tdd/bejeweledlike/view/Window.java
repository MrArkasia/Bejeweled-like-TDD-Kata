package com.wemanity.kata.tdd.bejeweledlike.view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public void create() {
        JFrame window = new JFrame();
        window.setTitle("Diamond Mine");
        window.setSize(800, 600);
        window.setResizable(false);
        window.setBackground(Color.WHITE);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().validate();
        window.setVisible(true);
    }

}
