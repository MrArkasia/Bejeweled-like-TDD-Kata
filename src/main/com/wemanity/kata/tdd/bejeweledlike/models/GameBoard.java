package com.wemanity.kata.tdd.bejeweledlike.models;

public class GameBoard {

    private int grid[][];

    public GameBoard(int size) {
        this.grid = new int[size][size];
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public int getValue(Coordinates coordinates) {
        return grid[coordinates.x][coordinates.y];
    }

    public void setValue(Coordinates coordinates, int value) {
        grid[coordinates.x][coordinates.y] = value;
    }
}
