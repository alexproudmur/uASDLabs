package com.company;

@SuppressWarnings("all")
public class LinkedList {
    Node head;

    static class Node {
        int data;
        Node next;
        Node(int d) {
            data = d;
            next = null;
        }
    }


    public void printList() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println();
    }

    public int getCount() {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public void push(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    public void addByIndex(int new_data_2, int index) {
        int i = 0;
        Node index_node = head;
        Node prev_node = head;
        Node node = new Node(new_data_2);

        while (i != index) {
            index_node = index_node.next;
            i++;
        }
        i = 0;
        while (i != index - 1) {
            prev_node = prev_node.next;
            i++;
        }

        Node node_temp = index_node;
        prev_node.next = node;
        node.next = node_temp;

    }

    public void append(int new_data_3) {
        Node new_node = new Node(new_data_3);
        if (head == null) {
            head = new Node(new_data_3);
            return;
        }
        new_node.next = null;

        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = new_node;
    }

    public void removeBeginning() {
        head = head.next;
    }

    public void removeByIndex(int position) {
        if (head == null)
            return;

        Node temp = head;

        if (position == 0) {
            head = temp.next;
            return;
        }

        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null || temp.next == null) return;

        temp.next = temp.next.next;
    }

    public void removeEnd() {
        Node node = head;

        while (node.next.next != null) {
            node = node.next;
        }

        node.next = null;
    }


    public int calcSum() {
        int sum = 0, len = 0;
        Node temp = head;

        while (temp != null) {
            len++;
            temp = temp.next;
        }

        int c = len - 6;  // change 6 if needed
        temp = head;

        while (temp != null && c-- > 0) {
            temp = temp.next;
        }

        while (temp != null) {
            sum += temp.data;
            temp = temp.next;
        }
        return sum;
    }

    public int searchIndex(int n) {
        int index = 0;
        Node current = head;
        while (current != null) {
            if (current.data == n) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public void replaceByIndex(int data, int index) {
        int i = 0;
        Node index_node = head;
        Node next_node = head;
        Node node = null;

        while (i != index) {
            index_node = index_node.next;
            i++;
        }

        index_node.data = data;
    }

    public void replaceBeginning(int data) {
        head.data = data;
    }

    public void replaceEnd(int data) {
        Node node = head;

        while (node.next != null) {
            node = node.next;
        }
        node.data = data;
    }

    public static void main(String[] args) {
        System.out.println("LinkedList Test");
        long start, end;

        for (int i = 10000; i <= 100000; i += 10000) {
            LinkedList linkedList = new LinkedList();
            int[] arr = new int[i];
            Util.fillTestArray(arr);

            start = System.nanoTime();
            for (int j : arr) {
                linkedList.push(j);
            }

            end = System.nanoTime();
            System.out.printf("Insert time for linked list, %d elements- " + (end - start)/arr.length + " ns\n", i);

            start = System.nanoTime();
            for (int j : arr) {
                linkedList.searchIndex(j);
            }
            end = System.nanoTime();
            System.out.printf("Search time for linked list, %d elements- " + (end - start)/arr.length + " ns\n", i);


            start = System.nanoTime();
            for (int j = 0; j < arr.length; j++) {
                linkedList.removeBeginning();
            }
            end = System.nanoTime();
            System.out.printf("Delete time for linked list, %d elements- " + (end - start)/arr.length + " ns\n\n", i);

            linkedList = null;
        }

        for (int i = 200000; i <= 500000; i += 100000) {
            LinkedList linkedList = new LinkedList();
            int[] arr = new int[i];
            Util.fillTestArray(arr);

            start = System.nanoTime();
            for (int j : arr) {
                linkedList.push(j);
            }

            end = System.nanoTime();
            System.out.printf("Insert time for linked list, %d elements- " + (end - start)/arr.length + " ns\n", i);

            start = System.nanoTime();
            for (int j : arr) {
                linkedList.searchIndex(j);
            }
            end = System.nanoTime();
            System.out.printf("Search time for linked list, %d elements- " + (end - start)/arr.length + " ns\n", i);


            start = System.nanoTime();
            for (int j = 0; j < arr.length; j++) {
                linkedList.removeBeginning();
            }
            end = System.nanoTime();
            System.out.printf("Delete time for linked list, %d elements- " + (end - start)/arr.length + " ns\n\n", i);
        }

    }

}
