package com.wemanity.kata.tdd.bejeweledlike.models;

public class GameBoard {

    private int grid[][];

    public GameBoard(int size) {
        this.grid = new int[size][size];
        gridInit(size);
    }

    private void gridInit(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public int getSize() {
        return grid.length;
    }

    public int getValue(Coordinates coordinates) {
        return grid[coordinates.x][coordinates.y];
    }

    public void setValue(Coordinates coordinates, int value) {
        grid[coordinates.x][coordinates.y] = value;
    }

    public boolean containWhiteColor() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
