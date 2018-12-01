package com.wemanity.kata.tdd.bejeweledlike.rules;

import com.wemanity.kata.tdd.bejeweledlike.models.Coordinates;
import com.wemanity.kata.tdd.bejeweledlike.models.DiamondColor;
import com.wemanity.kata.tdd.bejeweledlike.models.GameBoard;

public class GameBoardRules {

    private final GameBoard gameBoard;
    private final boolean marked[][];

    public GameBoardRules(int size) {
        this.gameBoard = new GameBoard(size);
        marked = new boolean[size][size];
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Exchange the contents of two boxes
     */
    public void swap(Coordinates box1, Coordinates box2) {
        int oldValue = gameBoard.getValue(box1);
        gameBoard.setValue(box1, gameBoard.getValue(box2));
        gameBoard.setValue(box2, oldValue);
    }

    /**
     * Fill the empty boxes by gravity, and randomly generate boxes from the top
     */
    public boolean fill() {
        boolean modified = false;
        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = gameBoard.getSize() - 1; j >= 0; j--) {
                Coordinates actualCoordinates = new Coordinates(i, j);
                if (gameBoard.getValue(actualCoordinates) == 0) {
                    if (j == 0) {
                        gameBoard.setValue(actualCoordinates, DiamondColor.getRandomColor());
                    } else {
                        Coordinates topCoordinates = new Coordinates(i, j - 1);
                        gameBoard.setValue(actualCoordinates, gameBoard.getValue(topCoordinates));
                        gameBoard.setValue(topCoordinates, 0);
                    }
                    modified = true;
                }
            }
        }
        return modified;
    }

    /**
     * Do we have three squares of the same color to the right from (i, j)
     */
    boolean horizontalAligned(int i, int j) {
        if (i < 0 || j < 0 || i >= gameBoard.getSize() - 2 || j >= gameBoard.getSize()) return false;
        return gameBoard.getValue(new Coordinates(i, j)) == gameBoard.getValue(new Coordinates(i + 1, j))
                && gameBoard.getValue(new Coordinates(i, j)) == gameBoard.getValue(new Coordinates(i + 2, j));
    }

    /**
     * Do we have three boxes of the same color down from (i, j)
     */
    boolean verticalAligned(int i, int j) {
        if (i < 0 || j < 0 || i >= gameBoard.getSize() || j >= gameBoard.getSize() - 2) return false;
        return gameBoard.getValue(new Coordinates(i, j)) == gameBoard.getValue(new Coordinates(i, j + 1))
                && gameBoard.getValue(new Coordinates(i, j)) == gameBoard.getValue(new Coordinates(i, j + 2));
    }

    /**
     * Remove alignments
     */
    public boolean removeAlignments() {
        // pass 1: mark all alignments
        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = 0; j < gameBoard.getSize(); j++) {
                if (gameBoard.getValue(new Coordinates(i, j)) != 0 && horizontalAligned(i, j)) {
                    marked[i][j] = marked[i + 1][j] = marked[i + 2][j] = true;
                }
                if (gameBoard.getValue(new Coordinates(i, j)) != 0 && verticalAligned(i, j)) {
                    marked[i][j] = marked[i][j + 1] = marked[i][j + 2] = true;
                }
            }
        }
        // pass 2: delete marked boxes
        boolean modified = false;
        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = 0; j < gameBoard.getSize(); j++) {
                if (marked[i][j]) {
                    gameBoard.setValue(new Coordinates(i, j), 0);
                    marked[i][j] = false;
                    modified = true;
                }
            }
        }
        return modified;
    }

    /**
     * Determines whether the exchange between two boxes is valid
     */
    public boolean isValidSwap(Coordinates box1, Coordinates box2) {

        if (box1.x == -1 || box2.x == -1 || box1.y == -1 || box2.y == -1) return false;
        if (Math.abs(box2.x - box1.x) + Math.abs(box2.y - box1.y) != 1) return false;
        if (gameBoard.getValue(box1) == gameBoard.getValue(box2)) return false;

        swap(box1, box2);

        // we check that it creates a new alignment
        boolean newAlignment = false;
        for (int i = 0; i < 3; i++) {
            newAlignment |= horizontalAligned(box1.x - i, box1.y);
            newAlignment |= horizontalAligned(box2.x - i, box2.y);
            newAlignment |= verticalAligned(box1.x, box1.y - i);
            newAlignment |= verticalAligned(box2.x, box2.y - i);
        }

        // then we cancel the exchange
        swap(box1, box2);

        return newAlignment;
    }

}
