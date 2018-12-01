package com.wemanity.kata.tdd.bejeweledlike.models;

public class DiamondColor {

    private java.awt.Color colors[] = {
            java.awt.Color.WHITE,
            java.awt.Color.RED,
            java.awt.Color.GREEN,
            java.awt.Color.BLUE,
            java.awt.Color.GRAY,
            java.awt.Color.PINK,
            java.awt.Color.CYAN
    };

    public java.awt.Color getColor(int i) {
        return colors[i];
    }

}
