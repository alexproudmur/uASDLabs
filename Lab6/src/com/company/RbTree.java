package com.company;

import java.util.NoSuchElementException;

@SuppressWarnings("all")
public class RbTree {

    private Node root;
    private final Node NIL;

    public RbTree() {
        NIL = new Node();
        NIL.color = 0;
        NIL.left = null;
        NIL.right = null;
        root = NIL;
    }

    public void insert(int key) {
        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = NIL;
        node.right = NIL;
        node.color = 1; // red

        Node y = null;
        Node x = this.root;

        while (x != NIL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = 0;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        balanceIns(node);
    }

    private void balanceIns(Node k) {
        Node u;
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left; // uncle
                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right; // uncle
                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = 0;
    }

    public void delete(int data) {
        deleteNodeHelper(this.root, data);
    }


    private void deleteNodeHelper(Node node, int key) {
        Node z = NIL;
        Node x, y;
        while (node != NIL) {
            if (node.data == key) {
                z = node;
            }

            if (node.data <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        if (z == NIL) {
            throw new NoSuchElementException();
        }
        y = z;
        int yOriginalColor = y.color;
        if (z.left == NIL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == NIL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == 0) {
            balanceDel(x);
        }
    }

    private void balanceDel(Node x) {
        Node s;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.right.color == 0) {
                        s.left.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = x.parent.right;
                    }
                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.left.color == 0) {
                        s.right.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = x.parent.left;
                    }
                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }

    private void rbTransplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    public Node searchTree(int k) {
        return searchTreeHelper(this.root, k);
    }

    private Node searchTreeHelper(Node node, int key) {
        if (node == NIL || key == node.data) {
            return node;
        }
        if (key < node.data) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    // ##### Печать + обход дерева #####

    public void print() {
        inOrder(root);
        System.out.println(" ");
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        //System.out.printf("%s ", node.data);
        inOrder(node.right);
    }

    public void getSum() {
        System.out.println("Сума: " + sumCounter(root));
    }

    private long sumCounter(Node node) {
        long counter;
        if (node == null) return 0;
        counter = sumCounter(node.left) + node.data + sumCounter(node.right);
        return counter;
    }


    // ##### Доп. операции + операции поворота #####

    public Node minimum(Node node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }

    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NIL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public Node getRoot() {
        return this.root;
    }

    public static void main(String[] args) {
        System.out.println("RB Tree Test");
        long start, end;

        for (int i = 100000; i <= 1000000; i += 100000) {
            RbTree rbTree = new RbTree();
            int[] arr = new int[i];
            Util.fillTestArray(arr);

            start = System.nanoTime();
            for (int j : arr) {
                rbTree.insert(j);
            }

            end = System.nanoTime();
            System.out.printf("Insert time for RB Tree, %d elements- " + (end - start) / arr.length + " ns\n", i);

            start = System.nanoTime();
            for (int j : arr) {
                rbTree.searchTree(j);
            }
            end = System.nanoTime();
            System.out.printf("Search time for RB Tree, %d elements- " + (end - start) / arr.length + " ns\n", i);


//            start = System.nanoTime();
//            for (int j : arr) {
//                rbTree.delete(j);
//            }
//            end = System.nanoTime();
//            System.out.printf("Delete time for RB Tree, %d elements- " + (end - start) / arr.length + " ns\n", i);

            start = System.nanoTime();
            rbTree.inOrder(rbTree.root);
            end = System.nanoTime();
            System.out.printf("Sort print time for RB Tree, %d elements- " + (end - start)/arr.length + " ns\n\n", i);

            rbTree = null;
        }

        for (int i = 2000000; i <= 10000000; i += 1000000) {
            RbTree rbTree = new RbTree();
            int[] arr = new int[i];
            Util.fillTestArray(arr);

            start = System.nanoTime();
            for (int j : arr) {
                rbTree.insert(j);
            }

            end = System.nanoTime();
            System.out.printf("Insert time for RB Tree, %d elements- " + (end - start) / arr.length + " ns\n", i);

            start = System.nanoTime();
            for (int j : arr) {
                rbTree.searchTree(j);
            }
            end = System.nanoTime();
            System.out.printf("Search time for RB Tree, %d elements- " + (end - start) / arr.length + " ns\n", i);


//            start = System.nanoTime();
//            for (int j : arr) {
//                rbTree.delete(j);
//            }
//            end = System.nanoTime();
//            System.out.printf("Delete time for RB Tree, %d elements- " + (end - start) / arr.length + " ns\n", i);

            start = System.nanoTime();
            rbTree.inOrder(rbTree.root);
            end = System.nanoTime();
            System.out.printf("Sort print time for RB Tree, %d elements- " + (end - start)/arr.length + " ns\n\n", i);

            rbTree = null;
        }


    }

}

class Node {

    int data; // holds the key
    Node parent; // pointer to the parent
    Node left; // pointer to left child
    Node right; // pointer to right child
    int color; // 1 . Red, 0 . Black
}
