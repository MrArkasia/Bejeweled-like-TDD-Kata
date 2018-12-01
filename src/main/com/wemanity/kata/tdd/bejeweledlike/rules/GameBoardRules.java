package com.wemanity.kata.tdd.bejeweledlike.rules;

import com.wemanity.kata.tdd.bejeweledlike.models.Coordinates;
import com.wemanity.kata.tdd.bejeweledlike.models.DiamondColor;
import com.wemanity.kata.tdd.bejeweledlike.models.GameBoard;

public class GameBoardRules {

    private final GameBoard gameBoard;

    public GameBoardRules(int size) {
        this.gameBoard = new GameBoard(size);
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
}
