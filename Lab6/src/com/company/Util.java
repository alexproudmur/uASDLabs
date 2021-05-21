package com.company;

public class Util {
    static void fillTestArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10000);
        }
    }
}
