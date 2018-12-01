package com.wemanity.kata.tdd.bejeweledlike.models;

import java.util.Random;

public class DiamondColor {

    private static final Random rand = new Random();

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

    public static int getRandomColor(){
        return 1 + rand.nextInt(colors.length - 1);
    }

}
