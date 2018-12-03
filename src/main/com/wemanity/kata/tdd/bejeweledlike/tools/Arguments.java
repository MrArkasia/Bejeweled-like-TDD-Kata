package com.wemanity.kata.tdd.bejeweledlike.tools;

public class Arguments {

    public static int checkSize(String[] args) {
        int size = -1;
        if (args.length > 0) {
            final String arg = args[0];
            try {
                size = Integer.parseInt(arg);
            } catch (Exception ex) {
                System.out.println("Error : Size argument this not a number");
            }
        }
        if(size < 10){
            size = -1;
        }
        return size;
    }
}
