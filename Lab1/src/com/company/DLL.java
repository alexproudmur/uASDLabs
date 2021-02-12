package com.company;

public class DLL implements List{
    Node head;
    int listSize = 0;

    public void push(int data) {
        Node node = new Node(data);

        node.next = head;
        node.prev = null;

        if (head != null) {
            head.prev = node;
        }
        head = node;
        listSize++;
    }

    public void append(int data) {
        Node node = new Node(data);
        Node last = head;

        node.next = null;

        if (head == null) {
            node.prev = null;
            head = node;
            return;
        }

        while (last.next != null) {
            last = last.next;
        }

        last.next = node;
        node.prev = last;

        listSize++;
    }

    public void addByIndex(int data, int index) {
        if (index <= 0 || index >= listSize - 1) {
            return;
        }

        Node node = new Node(data);

        int i = 0;
        Node indexNode = head;

        while (i != index) {
            i++;
            indexNode = indexNode.next;
            if (indexNode == null) {
                return;
            }
        }

        Node temp = indexNode;

        indexNode = node;
        node.prev = temp.prev;
        node.next = temp;

        temp.prev = node;
        indexNode.prev.next = node;

        listSize++;
    }

    public void removeBeginning() {
        head = head.next;
        listSize--;
    }

    public void removeEnd() {
        Node last = head;

        while (last.next != null) {
            last = last.next;
        }

        last.prev.next = null;
        last.next = null;
        listSize--;
    }

    public void removeByIndex(int index) {
        if (index <= 0 || index >= listSize - 1) {
            return;
        }
        int i = 0;
        Node indexNode = head;

        while (i != index) {
            i++;
            indexNode = indexNode.next;
            if (indexNode == null) {
                return;
            }
        }

        Node temp = indexNode;
        indexNode.prev.next = temp.next;
        indexNode.next.prev = temp.prev;
        listSize--;
    }

    public void replaceBeginning(int data) {
        head.data = data;
    }

    public void replaceEnd(int data) {
        Node last = head;

        while (last.next != null) {
            last = last.next;
        }

        last.data = data;
    }

    public void replaceByIndex(int data, int index) {
        if (index <= 0 || index >= listSize - 1) {
            return;
        }
        int i = 0;
        Node indexNode = head;

        while (i != index) {
            i++;
            indexNode = indexNode.next;
        }

        indexNode.data = data;
    }

    public int calcSum() {
        int sum = 0;
        Node node = head;

        while (node != null) {
            sum += node.data;
            node = node.next;
        }

        return sum;
    }

    public int searchIndex(int data) {
        Node node = head;
        int i = 0;
        while (node != null) {
            if (node.data == data) {
                return i;
            }
            i++;
            node = node.next;
        }
        return -1;
    }

    public void printDLL() {
        Node node = head;

        while (node.next != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }

        System.out.println(node);
    }
}


class Node {
    int data;
    Node prev;
    Node next;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
    }
}