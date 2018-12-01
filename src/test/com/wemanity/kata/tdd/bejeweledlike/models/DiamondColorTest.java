package com.wemanity.kata.tdd.bejeweledlike.models;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DiamondColorTest {

    public DiamondColor diamondColor;

    @Before
    public void setUp() throws Exception {
        diamondColor = new DiamondColor();
    }

    @Test
    public void should_return_WHITE_color_when_give_0() {
        //given
        Color color = diamondColor.getColor(0);

        //when

        //then
        assertEquals(color, Color.WHITE);
    }

    @Test
    public void should_return_RED_color_when_give_1() {
        //given
        Color color = diamondColor.getColor(1);

        //when

        //then
        assertEquals(color, Color.RED);
    }

    @Test
    public void should_return_GREEN_color_when_give_2() {
        //given
        Color color = diamondColor.getColor(2);

        //when

        //then
        assertEquals(color, Color.GREEN);
    }

    @Test
    public void should_return_BLUE_color_when_give_3() {
        //given
        Color color = diamondColor.getColor(3);

        //when

        //then
        assertEquals(color, Color.BLUE);
    }

    @Test
    public void should_return_WHITE_color_when_give_4() {
        //given
        Color color = diamondColor.getColor(4);

        //when

        //then
        assertEquals(color, Color.GRAY);
    }


    @Test
    public void should_return_WHITE_color_when_give_5() {
        //given
        Color color = diamondColor.getColor(5);

        //when

        //then
        assertEquals(color, Color.PINK);
    }

    @Test
    public void should_return_WHITE_color_when_give_6() {
        //given
        Color color = diamondColor.getColor(6);

        //when

        //then
        assertEquals(color, Color.CYAN);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void should_return_exception_color_when_give_bad_integer() {
        //given
        Color color = diamondColor.getColor(7);

        //when

        //then
        assertEquals(color, Color.WHITE);
    }

    @Test
    public void should_return_random_color() {
        //given
        int color = diamondColor.getRandomColor();

        //when

        //then
        assertTrue(color > 0 && color <= 6);
    }

}