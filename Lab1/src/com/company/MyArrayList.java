package com.company;

public class MyArrayList implements List{
    private final int DEFAULT_CAPACITY = 10;
    private Integer[] array = new Integer[DEFAULT_CAPACITY];
    private int counter = 0;

    public void push(int el) {
        if (DEFAULT_CAPACITY-1 == counter)
        {
            recreate(array.length *2);
        }
        Integer[] newArray = new Integer[counter + 1];
        newArray[0] = el;
        System.arraycopy(array, 0, newArray, 1, counter);
        array = newArray;
        counter++;
    }

    public void append(int el) {
        if (DEFAULT_CAPACITY-1 == counter)
        {
            recreate(array.length *2);
        }
        Integer[] newArray = new Integer[counter + 1];
        newArray[newArray.length - 1] = el;
        System.arraycopy(array, 0, newArray, 0, counter);
        array = newArray;
        counter++;
    }

    public void addByIndex(int el, int index) {

        if (index > counter) {
            return;
        }
        if (DEFAULT_CAPACITY-1 == counter)
        {
            recreate(array.length *2);
        }

        Integer[] newArray = new Integer[counter + 1];
        newArray[index] = el;
        System.arraycopy(array, 0, newArray, 0, index);

        System.arraycopy(array, index, newArray, index+1, counter-index);
        array = newArray;
        counter++;


    }
    public void replaceBeginning(int el)
    {
        array[0] = el;
    }
    public void replaceEnd(int el)
    {
        array[counter - 1] = el;
    }
    public void replaceByIndex(int el, int index)
    {
        if (counter == 0 || index > counter ) {
            return;
        }
        array[index] = el;

    }
    public void removeBeginning()
    {
        if (counter == 0) return;
        Integer[] newArray = new Integer[counter-1];
        System.arraycopy(array, 1, newArray, 0, counter-1);
        array = newArray;
        counter--;

    }
    public void removeEnd()
    {
        if (counter == 0) return;
        Integer[] newArray = new Integer[counter-1];
        System.arraycopy(array, 0, newArray, 0, counter-1);
        array = newArray;
        counter--;
    }

    public void removeByIndex(int index)
    {
        if (counter == 0 || index > counter) return;
        Integer[] newArray = new Integer[counter-1];
        System.arraycopy(array, 0, newArray, 0, index);

        System.arraycopy(array, index+1, newArray, index, counter-index-1);
        array = newArray;
        counter--;
    }


    public int get(int index) {
        return array[index];
    }

    public int calcSum()
    {
        int result = 0;
        for (int i =0; i < counter; i ++)
        {
            result += array[i];
        }
        return result;

    }

    public int searchIndex(int el)
    {
        int result = -1;
        for (int i =0; i < counter; i ++)
        {
            if (array[i] == el)
            {
                result = i;
                break;
            }
        }
        return result;
    }
    private void recreate(int capacity)
    {
        Integer[] newArray = new Integer[capacity];
        System.arraycopy(array, 0, newArray, 0, counter);
        array = newArray;
    }


}



