package com.wemanity.kata.tdd.bejeweledlike.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {

    public Score score;

    @Before
    public void setUp() {
        score = new Score();
    }

    @Test
    public void should_return_increase_score_when_add_value() {
        //given

        //when
        score.increase(10);

        //then
        assertEquals(score.getValue(), 10);
    }

    @Test
    public void should_return_increase_score_when_multi_add_value() {
        //given

        //when
        score.increase(10);
        score.increase(30);

        //then
        assertEquals(score.getValue(), 40);
    }

    @Test
    public void should_return_old_score_when_add_zero() {
        //given

        //when
        score.increase(0);

        //then
        assertEquals(score.getValue(), 0);
    }

    @Test
    public void should_return_old_score_when_add_negative_value() {
        //given

        //when
        score.increase(12);
        score.increase(-2);

        //then
        assertEquals(score.getValue(), 12);
    }

}