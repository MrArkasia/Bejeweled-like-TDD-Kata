package com.wemanity.kata.tdd.bejeweledlike;

import com.wemanity.kata.tdd.bejeweledlike.tools.Arguments;
import com.wemanity.kata.tdd.bejeweledlike.view.Window;

public class Main {

    public static void main(String[] args) {
        int size = Arguments.checkSize(args);
        if (size < 0) {
            size = 10;
        }
        new Window().create(size);
    }
}
