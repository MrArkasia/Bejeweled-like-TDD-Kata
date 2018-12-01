package com.wemanity.kata.tdd.bejeweledlike.models;

public class DiamondColor {

    private static final java.awt.Color colors[] = {
            java.awt.Color.WHITE,
            java.awt.Color.RED,
            java.awt.Color.GREEN,
            java.awt.Color.BLUE,
            java.awt.Color.GRAY,
            java.awt.Color.PINK,
            java.awt.Color.CYAN
    };

    public static java.awt.Color getColor(int i) {
        return colors[i];
    }

}
