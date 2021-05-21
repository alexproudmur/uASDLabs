package com.company;

// Java program to demonstrate
// insert operation in binary
// search tree
@SuppressWarnings({"unused", "all"})
class BinarySearchTree {

    /* Class containing left
       and right child of current node
     * and key value*/
    static class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // Root of BST
    Node root;

    // Constructor
    BinarySearchTree() {
        root = null;
    }

    // This method mainly calls insertRec()
    void insert(int key) {
        root = insertRec(root, key);
    }

    /* A recursive function to
       insert a new key in BST */
    Node insertRec(Node root, int key) {

        /* If the tree is empty,
           return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        /* return the (unchanged) node pointer */
        return root;
    }

    // This method mainly calls InorderRec()
    void inorder() {
        inorderRec(root);
    }

    // A utility function to
    // do inorder traversal of BST
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            //System.out.println(root.key);
            inorderRec(root.right);
        }
    }

    public Node search(Node root, int key)
    {
        // Base Cases: root is null or key is present at root
        if (root==null || root.key==key)
            return root;

        // Key is greater than root's key
        if (root.key < key)
            return search(root.right, key);

        // Key is smaller than root's key
        return search(root.left, key);
    }

    void deleteKey(int key) { root = deleteRec(root, key); }

    /* A recursive function to
      delete an existing key in BST
     */
    Node deleteRec(Node root, int key)
    {
        /* Base Case: If the tree is empty */
        if (root == null)
            return null;

        /* Otherwise, recur down the tree */
        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);

            // if key is same as root's
            // key, then This is the
            // node to be deleted
        else {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root)
    {
        int minv = root.key;
        while (root.left != null)
        {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }
    // Driver Code
    public static void main(String[] args) {
        System.out.println("Binary Search Tree Test");
        long start, end;

        for (int i = 100000; i <= 1000000; i += 100000) {
            BinarySearchTree binarySearchTree = new BinarySearchTree();
            int[] arr = new int[i];
            Util.fillTestArray(arr);

            start = System.nanoTime();
            for (int j : arr) {
                binarySearchTree.insert(j);
            }

            end = System.nanoTime();
            System.out.printf("Insert time for BST, %d elements- " + (end - start)/arr.length + " ns\n", i);

            start = System.nanoTime();
            for (int j : arr) {
                binarySearchTree.search(binarySearchTree.root, j);
            }
            end = System.nanoTime();
            System.out.printf("Search time for BST, %d elements- " + (end - start)/arr.length + " ns\n", i);


            start = System.nanoTime();
            for (int j : arr) {
                binarySearchTree.deleteKey(j);
            }
            end = System.nanoTime();
            System.out.printf("Delete time for BST, %d elements- " + (end - start)/arr.length + " ns\n\n", i);

            start = System.nanoTime();
            binarySearchTree.inorder();
            end = System.nanoTime();
            System.out.printf("Sort print time for BST, %d elements- " + (end - start)/arr.length + " ns\n\n", i);

            binarySearchTree = null;
        }

        for (int i = 2000000; i <= 10000000; i += 1000000) {
            BinarySearchTree binarySearchTree = new BinarySearchTree();
            int[] arr = new int[i];
            Util.fillTestArray(arr);

            start = System.nanoTime();
            for (int j : arr) {
                binarySearchTree.insert(j);
            }

            end = System.nanoTime();
            System.out.printf("Insert time for BST, %d elements- " + (end - start)/arr.length + " ns\n", i);

            start = System.nanoTime();
            for (int j : arr) {
                binarySearchTree.search(binarySearchTree.root, j);
            }
            end = System.nanoTime();
            System.out.printf("Search time for BST, %d elements- " + (end - start)/arr.length + " ns\n", i);


            start = System.nanoTime();
            for (int j : arr) {
                binarySearchTree.deleteKey(j);
            }
            end = System.nanoTime();
            System.out.printf("Delete time for BST, %d elements- " + (end - start)/arr.length + " ns\n", i);

            start = System.nanoTime();
            binarySearchTree.inorder();
            end = System.nanoTime();
            System.out.printf("Sort print time for BST, %d elements- " + (end - start)/arr.length + " ns\n\n", i);

            binarySearchTree = null;
        }
    }
}
