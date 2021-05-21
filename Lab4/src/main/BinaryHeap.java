package main;

import java.util.NoSuchElementException;

public interface BinaryHeap<T> {
    void insert(T t);

    int findIndexByValue(T item);

    int findPosition(T item);

    void delete(T item) throws NoSuchElementException;

    void sortPrint();

    T getMax();

    boolean isEmpty();

    int size();

    void outHeap();

    void print();
}
