package com.company;

import java.util.TreeSet;

public class AvlTree {
    Node root = null;

    /**
     * Нижче подана реалізація АВЛ-дерева
     * Спочатку йде сам код структури
     * А потім точка входу в програму (метод main)
     */

    private void resetHeight(Node n) {
        n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
    }

    private int getHeight(Node n) {
        return n == null ? -1 : n.height;
    }


    public boolean contains(Integer k) {
        return contains(root, k);
    }

    private boolean contains(Node n, Integer k) {
        if (n == null)
            return false;
        int cmp = k.compareTo(n.key);
        if (cmp < 0)
            return contains(n.left, k);
        else if (cmp > 0)
            return contains(n.right, k);
        return true;
    }

    public void remove(int k) {
        root = remove(root, k);
    }

    public void getSum() {
        System.out.println("Сума: " + sumCounter(root));
    }

    private long sumCounter(Node node) {
        long counter;
        if (node == null) return 0;
        counter = sumCounter(node.left) + node.value + sumCounter(node.right);
        return counter;
    }

    private Node remove(Node n, Integer k) {
        if (n == null)
            return null;
        int cmp = k.compareTo(n.key);
        if (cmp < 0)
            n.left = remove(n.left, k);
        else if (cmp > 0)
            n.right = remove(n.right, k);
        else {
            if (n.left == null) {
                n = n.right;
                return n;
            } else if (n.right == null) {
                n = n.left;
                return n;
            } else {
                Node replace = minV(n.right);
                n.key = replace.key;
                n.value = replace.value;
                n.right = remove(n.right, n.key);
                return n;
            }
        }
        return balance(n);
    }

    private Node minV(Node n) {
        while (n.left != null)
            n = n.left;
        return n;
    }

    public void add(int k) {
        root = add(root, k, k);
    }

    private Node add(Node n, Integer k, Integer v) {
        if (n == null)
            return new Node(k, v);
        int cmp = k.compareTo(n.key);
        if (cmp < 0)
            n.left = add(n.left, k, v);
        else if (cmp > 0)
            n.right = add(n.right, k, v);
        else
            n.value = v;
        return balance(n);
    }

    private Node balance(Node n) {
        resetHeight(n);
        int difference1 = getHeight(n.left) - getHeight(n.right);
        if (difference1 >= 2) {
            int difference2 = getHeight(n.left.right) - getHeight(n.left.left);
            if (difference2 > 0)
                n.left = rotateLeft(n.left);
            n = rotateRight(n);
        }

        else if (difference1 <= -2) {
            int diff2 = getHeight(n.right.left) - getHeight(n.right.right);
            if (diff2 > 0)
                n.right = rotateRight(n.right);
            n = rotateLeft(n);
        }
        return n;
    }

    private Node rotateLeft(Node n) {
        Node x = n.right;
        n.right = x.left;
        x.left = n;
        resetHeight(n);
        resetHeight(x);
        return x;
    }


    private Node rotateRight(Node n) {
        Node x = n.left;
        n.left = x.right;
        x.right = n;
        resetHeight(n);
        resetHeight(x);
        return x;
    }

    static class Node {
        int key, value, height;
        Node left, right;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.height = 0;
        }
    }

    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        TreeSet<Long> timeSet = new TreeSet<>();
        long startTime, finishTime;

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            startTime = System.currentTimeMillis();
            tree.add(i);
            finishTime = System.currentTimeMillis();
            timeSet.add(finishTime - startTime);
        }

        long end = System.currentTimeMillis();
        System.out.println("Додаємо мільйон елементів: " + (end - start) + " ms\n");
        System.out.println("Найгірший випадок додавання елемента: " + timeSet.last());
        System.out.println("Найкращий випадок додавання елемента: " + timeSet.first() + "\n");

        timeSet.clear();

        start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            startTime = System.currentTimeMillis();
            tree.remove(i);
            finishTime = System.currentTimeMillis();
            timeSet.add(finishTime - startTime);
        }
        end = System.currentTimeMillis();
        System.out.println("Видаляємо мільйон елементіа: " + (end - start) + " ms\n");
        System.out.println("Найгірший випадок видалення елемента: " + timeSet.last());
        System.out.println("Найкращий випадок видалення елемента: " + timeSet.first() + "\n");
        timeSet.clear();

        for (int i = 0; i < 1_000_000; i++) {
            tree.add(i);
        }

        start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            startTime = System.currentTimeMillis();
            tree.contains(i);
            finishTime = System.currentTimeMillis();
            timeSet.add(finishTime - startTime);
        }
        end = System.currentTimeMillis();
        System.out.println("Перевіряємо наявність елементу: " + (end - start) + " ms\n");
        System.out.println("Найгірший випадок пошуку наявного елемента: " + timeSet.last());
        System.out.println("Найкращий випадок пошуку наявного елемента: " + timeSet.first() + "\n");


        timeSet.clear();

        start = System.currentTimeMillis();
        for (int i = 1_000_000; i < 2_000_000; i++) {
            startTime = System.currentTimeMillis();
            tree.contains(i);
            finishTime = System.currentTimeMillis();
            timeSet.add(finishTime - startTime);
        }
        end = System.currentTimeMillis();
        System.out.println("Перевіряємо навність елементу, якого не існує: " + (end - start) + " ms\n");
        System.out.println("Найгірший випадок пошуку ненаявного елемента: " + timeSet.last());
        System.out.println("Найкращий випадок пошуку ненаявного елемента: " + timeSet.first() + "\n");

        timeSet.clear();

        for (int i = 0; i < 1_000_000; i++) {
            tree.remove(i);
        }

        for (int i = 0; i < 1_000_000; i++) {
            tree.add(i);
        }

        start = System.currentTimeMillis();
        tree.getSum();
        end = System.currentTimeMillis();
        System.out.println("Обчислюємо суму елементів: " + (end - start) + " ms");
    }
}
