package com.wemanity.kata.tdd.bejeweledlike.rules;

import com.wemanity.kata.tdd.bejeweledlike.models.Coordinates;
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
}