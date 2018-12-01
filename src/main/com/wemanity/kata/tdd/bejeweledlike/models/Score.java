package com.wemanity.kata.tdd.bejeweledlike.models;

public class Score {

    private int value;

    private static Score instance = new Score();

    public static Score getInstance() {
        return instance;
    }

    private Score() {
        value = 0;
    }

    public void increase(int toAdd) {
        if (toAdd > 0) {
            value += toAdd;
        }
    }

    public int getValue() {
        return value;
    }
}
