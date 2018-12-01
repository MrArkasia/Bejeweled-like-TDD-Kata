package com.wemanity.kata.tdd.bejeweledlike.models;

public class GameBoard {

    private int grid[][];

    public GameBoard(int size) {
        this.grid = new int[size][size];
    }

    public int[][] getGrid() {
        return grid;
    }
}
