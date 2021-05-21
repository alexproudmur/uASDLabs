package com.company;

@SuppressWarnings("all")
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
        } else if (difference1 <= -2) {
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
        System.out.println("AVL Tree Test");
        long start, end;

        for (int i = 100000; i <= 1000000; i += 100000) {
            AvlTree avlTree = new AvlTree();
            int[] arr = new int[i];
            Util.fillTestArray(arr);

            start = System.nanoTime();
            for (int j : arr) {
                avlTree.add(j);
            }

            end = System.nanoTime();
            System.out.printf("Insert time for AVL Tree, %d elements- " + (end - start) / arr.length + " ns\n", i);

            start = System.nanoTime();
            for (int j : arr) {
                avlTree.contains(j);
            }
            end = System.nanoTime();
            System.out.printf("Search time for AVL Tree, %d elements- " + (end - start) / arr.length + " ns\n", i);


            start = System.nanoTime();
            for (int j : arr) {
                avlTree.remove(j);
            }
            end = System.nanoTime();
            System.out.printf("Delete time for AVL Tree, %d elements- " + (end - start) / arr.length + " ns\n\n", i);

            avlTree = null;
        }

        for (int i = 2000000; i <= 10000000; i += 1000000) {
            AvlTree avlTree = new AvlTree();
            int[] arr = new int[i];
            Util.fillTestArray(arr);

            start = System.nanoTime();
            for (int j : arr) {
                avlTree.add(j);
            }

            end = System.nanoTime();
            System.out.printf("Insert time for AVL Tree, %d elements- " + (end - start) / arr.length + " ns\n", i);

            start = System.nanoTime();
            for (int j : arr) {
                avlTree.contains(j);
            }
            end = System.nanoTime();
            System.out.printf("Search time for AVL Tree, %d elements- " + (end - start) / arr.length + " ns\n", i);


            start = System.nanoTime();
            for (int j : arr) {
                avlTree.remove(j);
            }
            end = System.nanoTime();
            System.out.printf("Delete time for AVL Tree, %d elements- " + (end - start) / arr.length + " ns\n\n", i);

            avlTree = null;
        }
    }
}

