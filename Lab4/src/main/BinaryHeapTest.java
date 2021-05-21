package main;


import org.omg.CORBA.TIMEOUT;
import sun.reflect.generics.tree.Tree;

import java.util.TreeSet;

public class BinaryHeapTest {
    public static void main(String[] args) {
        randomizingTest();
        sortingTest();
    }

    /**
     * Тут тестується поведінка
     * структури даних при загальних операціях
     * вставки, видалення і тд.
     * при випадково заданих даних
     */

    static void randomizingTest() {
        int testQuantity = 200_000;
        System.out.printf("Random Test - %d elements\n\n", testQuantity);
        MyBinaryHeap<Integer> test = new MyBinaryHeap<>();

        /*
          Для тесту найгіршого випадку
          та взагалі правильності написання
          нашої структури
          будемо використовувати вбудовані
          в бібліотеку Java засоби,
          оскільки тест повинен бути незалежним
          та на базі верифікованого коду
         */

        TreeSet<Long> timeSet = new TreeSet<>();
        long operationStart;
        long operationEnd;

        long start = System.nanoTime();
        for (int i = 1; i <= testQuantity; i++) {
            operationStart = System.nanoTime();
            test.insert((int) (Math.random() * 1000));
            operationEnd = System.nanoTime();
            timeSet.add(operationEnd - operationStart);
        }

        long end = System.nanoTime();

        System.out.println("InsertTest - " + (end - start) / 1000000 + " мс");
        System.out.println("Найкращий випадок вставки - " + timeSet.first() / 1000000 + " мс");
        System.out.println("Найгірший випадок вставки - " + timeSet.last() / 1000000 + " мс");
        System.out.println();

        timeSet.clear();

        start = System.nanoTime();
        for (int i = 0; i < testQuantity; i += 2) {
            operationStart = System.nanoTime();
            test.findIndexByValue((int) (Math.random() * 1000));
            operationEnd = System.nanoTime();
            timeSet.add(operationEnd - operationStart);
        }

        end = System.nanoTime();
        System.out.println("FindTest - " + (end - start) / 1000000 + " мс");
        System.out.println("Найкращий випадок пошуку - " + timeSet.first() / 1000000 + " мс");
        System.out.println("Найгірший випадок пошуку - " + timeSet.last() / 1000000 + " мс");
        System.out.println();

        timeSet.clear();

        start = System.nanoTime();
        for (int i = 0; i < testQuantity; i++) {
            operationStart = System.nanoTime();
            test.delete((int) (Math.random() * 1000));
            operationEnd = System.nanoTime();
            timeSet.add(operationEnd - operationStart);
        }

        end = System.nanoTime();
        System.out.println("DeleteTest - " + (end - start) / 1000000 + " мс");
        System.out.println("Найкращий випадок видалення - " + timeSet.first() / 1000000 + " мс");
        System.out.println("Найгірший випадок видалення - " + timeSet.last() / 1000000 + " мс");
        System.out.println();

        timeSet.clear();
    }

    /**
     * Тест сортованого виведення
     * даних, що зберігаються у
     * структурі
     */

    static void sortingTest() {
        System.out.println("\nHeap Test");
        MyBinaryHeap<Integer> test = new MyBinaryHeap<>();

        for (int i = 0; i < 15; i++) {
            test.insert((int) (Math.random() * 10000));
        }

        System.out.println("\nPrint Array of Heap");
        test.print();
        System.out.println("\nPrint Heap");
        test.outHeap();
        System.out.println("\nSort Test");
        test.sortPrint();
    }
}

