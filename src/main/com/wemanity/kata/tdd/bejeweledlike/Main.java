package com.wemanity.kata.tdd.bejeweledlike;

import com.wemanity.kata.tdd.bejeweledlike.view.Window;

public class Main {

    private static int size = 10;

    public static void main(String[] args) {
        if (args.length > 0) {
            final String arg = args[0];
            try {
                size = Integer.parseInt(arg);
            } catch (Exception ex) {
                System.out.println("Error : Unable to read size");
            }
        }
        new Window().create(size);
    }
}
