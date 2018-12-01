package com.wemanity.kata.tdd.bejeweledlike.rules;

import com.wemanity.kata.tdd.bejeweledlike.models.Coordinates;
import com.wemanity.kata.tdd.bejeweledlike.models.GameBoard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GameBoardRulesTest {

    public GameBoardRules gameBoardRules;

    @Before
    public void setUp() throws Exception {
        gameBoardRules = new GameBoardRules(4);
        gameBoardRules.fill();
    }

    @Test
    public void should_return_swap_values() {
        //given
        Coordinates box1 = new Coordinates(0, 0);
        Coordinates box2 = new Coordinates(0, 1);
        int oldValueBox1 = gameBoardRules.getGameBoard().getValue(box1);
        int oldValueBox2 = gameBoardRules.getGameBoard().getValue(box2);

        //when
        gameBoardRules.swap(box1, box2);

        //then
        assertEquals(gameBoardRules.getGameBoard().getValue(box1), oldValueBox2);
        assertEquals(gameBoardRules.getGameBoard().getValue(box2), oldValueBox1);
    }

    @Test
    public void should_return_modified_grid_when_fill() {
        //given

        //when
        boolean modified = gameBoardRules.fill();

        //then
        assertTrue(modified);
    }

    @Test
    public void should_return_true_when_grid_contain_horizontal_Alignement() {
        //given
        int grid[][] = {
                {1, 2, 3, 4},
                {1, 2, 3, 4},
                {1, 2, 3, 4},
                {1, 2, 3, 4}};
        gameBoardRules.getGameBoard().setGrid(grid);

        //when
        boolean isAligned = gameBoardRules.horizontalAligned(0, 0);

        //then
        assertTrue(isAligned);
    }

    @Test
    public void should_return_true_when_grid_does_not_contain_horizontal_Alignement() {
        //given
        int grid[][] = {
                {1, 2, 3, 4},
                {4, 3, 2, 1},
                {1, 2, 3, 4},
                {1, 2, 3, 4}};
        gameBoardRules.getGameBoard().setGrid(grid);

        //when
        boolean isAligned = gameBoardRules.horizontalAligned(0, 0);

        //then
        assertFalse(isAligned);
    }

    @Test
    public void should_return_true_when_grid_contain_vertical_Alignement() {
        //given
        int grid[][] = {
                {1, 1, 1, 1},
                {2, 2, 2, 2},
                {3, 3, 3, 3},
                {4, 4, 4, 4}};
        gameBoardRules.getGameBoard().setGrid(grid);

        //when
        boolean isAligned = gameBoardRules.verticalAligned(0, 0);

        //then
        assertTrue(isAligned);
    }

    @Test
    public void should_return_true_when_grid_does_not_contain_vertical_Alignement() {
        //given
        int grid[][] = {
                {1, 4, 1, 1},
                {2, 3, 2, 2},
                {3, 2, 3, 3},
                {4, 1, 4, 4}};
        gameBoardRules.getGameBoard().setGrid(grid);

        //when
        boolean isAligned = gameBoardRules.verticalAligned(0, 0);

        //then
        assertFalse(isAligned);
    }
}