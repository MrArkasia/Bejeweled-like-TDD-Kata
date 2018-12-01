package com.wemanity.kata.tdd.bejeweledlike.rules;

import com.wemanity.kata.tdd.bejeweledlike.models.Coordinates;
import com.wemanity.kata.tdd.bejeweledlike.models.GameBoard;

public class GameBoardRules {

    private final GameBoard gameBoard;

    public GameBoardRules(int size) {
        this.gameBoard = new GameBoard(size);
    }

    /**
     * Exchange the contents of two boxes
     */
    public void swap(Coordinates box1, Coordinates box2) {
        int oldValue = gameBoard.getValue(box1);
        gameBoard.setValue(box1, gameBoard.getValue(box2));
        gameBoard.setValue(box2, oldValue);
    }
}
