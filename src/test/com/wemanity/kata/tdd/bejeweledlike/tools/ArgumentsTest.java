package com.wemanity.kata.tdd.bejeweledlike.tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArgumentsTest {

    @Test
    public void should_return_arg_value() {
        //given
        String args[] = {"15"};

        //when
        int size = Arguments.checkSize(args);

        //then
        assertEquals(size, 15);
    }

    @Test
    public void should_return_minus_one_when_size_arg_is_under_ten() {
        //given
        String args[] = {"8"};

        //when
        int size = Arguments.checkSize(args);

        //then
        assertEquals(size, -1);
    }

    @Test
    public void should_return_minus_one_when_arg_is_not_a_number() {
        //given
        String args[] = {"a"};

        //when
        int size = Arguments.checkSize(args);

        //then
        assertEquals(size, -1);
    }

}