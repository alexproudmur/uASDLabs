package main;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyBinaryHeap<T extends Comparable<T>> implements BinaryHeap<T>{

    private final ArrayList<T> items;
    private ArrayList<T> copy;
    private int size = 0;
    private int copySize = 0;


    public MyBinaryHeap() {
        items = new ArrayList<>();
    }

    /**
     * приватний метод для
     * перебудови дерева
     * купи у максимальне
     */

    private void shiftUp() {
        int k = items.size() - 1;
        while (k > 0) {
            int curr = (k - 1) / 2;
            T Item = items.get(k);
            T Parent = items.get(curr);
            if (Item.compareTo(Parent) > 0) {
                items.set(k, Parent);
                items.set(curr, Item);
                k = curr;
            } else break;
        }
    }

    /**
     * приватний метод для
     * перебудови дерева
     * купи у мінімальне
     */

    private void shiftDown() {
        int curr = 0;
        int leftChild = 1;
        while (leftChild < items.size()) {
            int max = leftChild;
            int rightChild = leftChild + 1;
            if (rightChild < items.size()) {
                if (items.get(rightChild).compareTo(items.get(1)) > 0) {
                    ++max;
                }
            }
            if (items.get(curr).compareTo(items.get(max)) < 0) {
                T tmp = items.get(curr);
                items.set(curr, items.get(max));
                items.set(max, tmp);
                curr = max;
                leftChild = 2 * curr + 1;
            } else break;
        }
    }

    /**
     * метод вставки
     * @param item - елемент, що передаємо
     *             до нашої купи
     *             на зберігання
     */

    @Override
    public void insert(T item) {
        items.add(item);
        size++;
        shiftUp();
    }

    /**
     * метод, що повертає індекс елементу у масиві
     * @param item - елемент
     * @return - індекс
     */

    @Override
    public int findIndexByValue(T item) {
        if (isEmpty()) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (item == items.get(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * знайти позицію елементу
     * @param item - елемент
     * @return - позиція (індекс у дереві)
     */

    @Override
    public int findPosition(T item) {
        int ind = findIndexByValue(item);
        if (ind == -1) {
            throw new NoSuchElementException("There is no such element in heap!");
        }
        return ind;
    }

    /**
     * метод видалення елементу
     * @param item - елемент
     */

    @Override
    public void delete(T item) {
        int ind = findIndexByValue(item);
        if (isEmpty()) {
            return;
        }
        if (ind != -1) {
            items.set(ind, items.get(size - 1));
            size--;
            items.remove(size);
            shiftDown();
        }
    }


    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        return items.toString();
    }

    /**
     * метод виводу змісту дерева
     * (не сортований)
     */

    @Override
    public void print() {
        for (T item : items) {
            System.out.print(item.toString() + " ");
        }
        System.out.println();
    }

    /**
     * виведення змісту купи
     * за індексами дерева
     */

    @Override
    public void outHeap() {
        int i = 0;
        int k = 1;
        while (i < size) {
            while ((i < k) && (i < size)) {
                System.out.print(items.get(i) + " ");
                i++;
            }
            System.out.println();
            k = k * 2 + 1;
        }
    }

    private void heapify(int i) {
        int left, right;
        T temp;
        left = 2 * i + 1;
        right = 2 * i + 2;
        if (left < copySize) {
            if (copy.get(i).compareTo(copy.get(left)) < 0) {
                temp = copy.get(i);
                copy.set(i, copy.get(left));
                copy.set(left, temp);
                heapify(left);
            }
        }
        if (right < copySize) {
            if (copy.get(i).compareTo(copy.get(right)) < 0) {
                temp = copy.get(i);
                copy.set(i, copy.get(right));
                copy.set(right, temp);
                heapify(right);
            }
        }
    }

    @Override
    public T getMax() {
        T x;
        x = copy.get(0);
        copy.set(0, copy.get(copySize - 1));
        copySize--;
        copy.remove(copySize);
        heapify(0);
        return (x);
    }

    /**
     * метод для сортованого виведення
     * елментів, щро зберігаються у купі
     */

    @Override
    public void sortPrint() {
        copy = (ArrayList<T>) items.clone();
        copySize = size;
        for (int i = 0; i < size; i++) {
            System.out.print(getMax() + " ");
        }
        System.out.println();
    }
}
