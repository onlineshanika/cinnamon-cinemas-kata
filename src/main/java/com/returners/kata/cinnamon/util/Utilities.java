package com.returners.kata.cinnamon.util;

import java.util.Random;

public class Utilities {

    public static String randomIdGenerator(){
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        int i = generator.nextInt(1000000) % 1000000;

        java.text.DecimalFormat f = new java.text.DecimalFormat("000000");

        return f.format(i) ;
    }

}
