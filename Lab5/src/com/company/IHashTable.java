package com.company;

public interface IHashTable<K, V> {
    void put(K key, V value);

    V remove(K key);

    V get(K key);

}
