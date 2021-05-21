package com.company;

import java.util.ArrayList;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        HashTable<Integer, String> table = new HashTable<>();

        // для заміру часу на окрему операцію
        // ми використовуємо верифіковані
        // засоби

        long operationStart, operationEnd;
        TreeSet<Long> timeSet = new TreeSet<>();

        // початок дослідження
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            operationStart = System.currentTimeMillis();
            table.put(i, "Alex" + Math.random());
            operationEnd = System.currentTimeMillis();
            timeSet.add(operationEnd - operationStart);
        }
        time = System.currentTimeMillis() - time;

        System.out.println("Adding 100k elem in " + time);
        System.out.println("Best case - " + timeSet.first());
        System.out.println("Worse case - " + timeSet.last() + "\n");
        timeSet.clear();

        time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            operationStart = System.currentTimeMillis();
            table.get(i);
            operationEnd = System.currentTimeMillis();
            timeSet.add(operationEnd - operationStart);
        }
        time = System.currentTimeMillis() - time;

        System.out.println("Getting 100k elem in " + time);
        System.out.println("Best case - " + timeSet.first());
        System.out.println("Worse case - " + timeSet.last() + "\n");
        timeSet.clear();

        time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            operationStart = System.currentTimeMillis();
            table.remove(i);
            operationEnd = System.currentTimeMillis();
            timeSet.add(operationEnd - operationStart);
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Deleting 100k elem in " + time);
        System.out.println("Best case - " + timeSet.first());
        System.out.println("Worse case - " + timeSet.last() + "\n");
        timeSet.clear();

        table.put(1, "fhgh");
        table.put(6, "jfdjf");
        table.put(4, "hjjdfj");
        table.put(2, "gjfkgkf");
        table.put(7, "hfjgjf");

        ArrayList<HashTable.Node<Integer, String>> sortedElements = table.getSorted();
        sortedElements.forEach(System.out::println);
    }
}
