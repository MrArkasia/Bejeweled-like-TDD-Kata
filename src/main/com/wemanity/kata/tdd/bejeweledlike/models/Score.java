package com.wemanity.kata.tdd.bejeweledlike.models;

public class Score {

    private int value;

    public Score() {
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
