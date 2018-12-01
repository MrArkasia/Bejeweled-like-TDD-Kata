package com.wemanity.kata.tdd.bejeweledlike.models;

import java.awt.*;
import java.util.Random;

public class DiamondColor {

    private static final Random rand = new Random();

    private static final java.awt.Color colors[] = {
            Color.WHITE,
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.GRAY,
            Color.PINK,
            Color.MAGENTA
    };

    public static java.awt.Color getColor(int i) {
        return colors[i];
    }

    public static int getRandomColor() {
        return 1 + rand.nextInt(colors.length - 1);
    }

}
