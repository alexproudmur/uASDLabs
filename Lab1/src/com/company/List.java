package com.company;

public interface List {
    void push(int data);
    void append(int data);
    void addByIndex(int data, int index);
    void removeBeginning();
    void removeEnd();
    void removeByIndex(int index);
    void replaceBeginning(int data);
    void replaceEnd(int data);
    void replaceByIndex(int data, int index);
    int calcSum();
    int searchIndex(int data);
}
