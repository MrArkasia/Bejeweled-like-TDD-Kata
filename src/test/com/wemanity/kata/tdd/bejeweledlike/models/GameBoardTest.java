package com.wemanity.kata.tdd.bejeweledlike.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTest {

    public GameBoard gameBoard;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void should_return_grid_with_size_8_when_create_game_board_with_size_8() {
        //given
        gameBoard = new GameBoard(8);

        //when
        int size = gameBoard.getGrid().length;

        //then
        assertEquals(size, 8);
    }

    @Test
    public void should_return_grid_with_size_0_when_create_game_board_with_size_0() {
        //given
        gameBoard = new GameBoard(0);

        //when

        //then
        assertEquals(gameBoard.getGrid().length, 0);
    }

    @Test(expected = NegativeArraySizeException.class)
    public void should_return_exception_when_create_game_board_with_negative_size() {
        //given
        gameBoard = new GameBoard(-1);

        //when

        //then
    }

    @Test
    public void should_modif_of_box_when_set_value() {
        //given
        Coordinates coordinates = new Coordinates(0, 0);
        gameBoard = new GameBoard(1);

        //when
        gameBoard.setValue(coordinates, 2);

        //then
        assertEquals(gameBoard.getValue(coordinates), 2);
    }

    @Test
    public void should_return_size() {
        //given
        gameBoard = new GameBoard(4);

        //when
        int size = gameBoard.getSize();

        //then
        assertEquals(size, 4);
    }

    @Test
    public void should_return_true_when_grid_contain_the_value_zero() {
        //given
        int grid[][] = {{1, 0}, {1, 1}};
        gameBoard = new GameBoard(2);
        gameBoard.setGrid(grid);

        //when
        boolean containZero = gameBoard.containWhiteColor();

        //then
        assertTrue(containZero);
    }

    @Test
    public void should_return_grid_with_all_boxes_at_zero_when_init_grid() {
        //given
        gameBoard = new GameBoard(10);

        //when

        //then
        assertTrue(gameBoard.containWhiteColor());
    }

}