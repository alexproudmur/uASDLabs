package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class HashTable<K, V> implements IHashTable<K, V> {
    private ArrayList<Node<K, V>> buckets;
    private int numBuckets;
    private int currentSize;
    private final double loadFactor;

    /**
     * Конструктор за умовчанням
     */

    public HashTable() {
        this.numBuckets = 12;
        this.currentSize = 0;
        this.buckets = new ArrayList<>();
        this.loadFactor = 0.75;

        for (int i = 0; i < this.numBuckets; i++) {
            this.buckets.add(null);
        }
    }

    /**
     * Конструктор з заданим
     * load factor
     * @param loadFactor - заданий load factor
     */

    public HashTable(double loadFactor) {
        this.numBuckets = 12;
        this.currentSize = 0;
        this.buckets = new ArrayList<>();
        this.loadFactor = loadFactor;

        for (int i = 0; i < this.numBuckets; i++) {
            this.buckets.add(null);
        }
    }

    /**
     * Хеш-функція
     * @param key - ключ ноди
     * @return - повертає ціле число
     */

    public int hashCode(K key) {
        return Objects.hash(key);
    }

    private int indexForBucket(int hash) {
        return hash % numBuckets;
    }

    /**
     * метод отримання відсортаванних
     * нодів, що зберігаються у нашій
     * хеш-таблиці
     * @return - повертаєтсья список
     */

    public ArrayList<Node<K, V>> getSorted() {
        ArrayList<Node<K, V>> sorted = new ArrayList<>();
        Node<K, V> node;
        for (Node<K, V> elem : this.buckets) {
            if (elem != null) {
                node = elem;
                while (node != null) {
                    sorted.add(node);
                    node = node.getNext();
                }
            }
        }
        sorted.sort(Comparator.comparingInt(o -> this.hashCode(o.getKey())));
        return sorted;
    }

    /**
     * Отримання значення за ключем
     * @param key - ключ на вході
     * @return - повертається значення
     */

    public V get(K key) {
        int hashCode = this.hashCode(key);
        int index = indexForBucket(hashCode);
        Node<K, V> node = buckets.get(index);
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * видалення ноди за ключем
     * @param key - ключ на вході
     * @return - повертаємо значення видаленої ноди
     */

    public V remove(K key) {
        int hashCode = this.hashCode(key);
        int index = indexForBucket(hashCode);
        Node<K, V> node = buckets.get(index);
        Node<K, V> prev = null;
        while (node != null) {
            if (node.getKey().equals(key)) {
                break;
            }

            prev = node;
            node = node.getNext();
        }

        if (node == null) {
            return null;
        }

        this.currentSize--;

        if (prev != null) {
            prev.setNext(node.getNext());
        } else {
            this.buckets.set(index, node.getNext());
        }

        return node.getValue();
    }

    /**
     * Додати до колеції з вказанням ключа
     * @param key - ключ
     * @param value - значення
     */

    public void put(K key, V value) {
        int hashCode = this.hashCode(key);
        int index = indexForBucket(hashCode);
        Node<K, V> node = new Node<>(key, value);
        Node<K, V> find = this.buckets.get(index);
        if (find == null) {
            this.buckets.set(index, node);
        } else {
            while (find != null) {
                if (hashCode(find.getKey()) == hashCode(node.getKey()) && find.equals(node)) {
                    find.setValue(node.getValue());
                    return;
                }
                if (find.getNext() == null) {
                    find.setNext(node);
                    break;
                }
                find = find.getNext();
            }
        }

        this.currentSize++;

        if ((1.0 * this.currentSize) / this.numBuckets >= this.loadFactor) {
            ArrayList<Node<K, V>> temp = this.buckets;
            this.numBuckets *= 2;
            this.buckets = new ArrayList<>();
            this.currentSize = 0;

            for (int i = 0; i < this.numBuckets; i++) {
                this.buckets.add(null);
            }

            for (Node<K, V> elem : temp) {
                while (elem != null) {
                    this.put(elem.getKey(), elem.getValue());
                    elem = elem.getNext();
                }
            }
        }
    }

    /**
     * Вкладений статичний
     * клас ноди
     * @param <K> - ключ
     * @param <V> - значення
     */

    public static class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V value) {
            this.value = value;
            return this.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getKey());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return getKey().equals(node.getKey());
        }

        public Node<K, V> getNext() {
            return this.next;
        }

        public void setNext(Node<K, V> node) {
            this.next = node;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
