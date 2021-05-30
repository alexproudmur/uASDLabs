package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String file = "Lab7/../text.txt";
        String findingText = "sd";
        int quantityOfTimes = 100000;
        long time;

        try {
            ITextProcessor textProcessor = new TextProcessor(file);

            for (int i = 0; i < quantityOfTimes; i++) {
                textProcessor.findFirst(file, findingText);
            }

            System.out.println("Find All");

            time = System.nanoTime();
            for (int i = 0; i < quantityOfTimes; i++) {
                textProcessor.findAll(file, findingText);
            }

            time = System.nanoTime() - time;
            System.out.printf("Time for %d: " + time / 1000000 + " ms\n\n", quantityOfTimes);

            System.out.println("Find First");

            time = System.nanoTime();
            for (int i = 0; i < quantityOfTimes; i++) {
                textProcessor.findFirst(file, findingText);
            }
            time = System.nanoTime() - time;
            System.out.printf("Time for %d: " + time / 1000000 + " ms\n\n", quantityOfTimes);

            System.out.println("Change All");

            time = System.nanoTime();
            for (int i = 0; i < quantityOfTimes; i++) {
                textProcessor.changeAll(file, findingText, findingText);
            }
            time = System.nanoTime() - time;
            System.out.printf("Time for %d: " + time / 1000000 + " ms\n\n", quantityOfTimes);

            System.out.println("Change First");

            time = System.nanoTime();
            for (int i = 0; i < quantityOfTimes; i++) {
                textProcessor.changeFirst(file, findingText, findingText);
            }
            time = System.nanoTime() - time;
            System.out.printf("Time for %d: " + time / 1000000 + " ms", quantityOfTimes);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
