package com.company;

public class Main {

    public static void main(String[] args) {

    	long startTime = System.nanoTime();
	    List dll = new DLL();
		long finalTime = System.nanoTime();
		printTime(finalTime - startTime);

		startTime = System.nanoTime();
	    dll.push(7);
	    finalTime = System.nanoTime();
		printTime(finalTime - startTime);


		startTime = System.nanoTime();
	    dll.append(10);
	    finalTime = System.nanoTime();
		printTime(finalTime - startTime);

		startTime = System.nanoTime();
		dll.addByIndex(4, 1);
		finalTime = System.nanoTime();
		printTime(finalTime - startTime);


		dll.append(7);
		dll.append(8);
		dll.append(85);
		dll.append(63);
		dll.append(87);

		startTime = System.nanoTime();
		dll.removeBeginning();
		finalTime = System.nanoTime();
		printTime(finalTime - startTime);

		startTime = System.nanoTime();
		dll.removeEnd();
		finalTime = System.nanoTime();
		printTime(finalTime - startTime);

		startTime = System.nanoTime();
		dll.removeByIndex(1);
		finalTime = System.nanoTime();
		printTime(finalTime - startTime);


		startTime = System.nanoTime();
		dll.replaceBeginning(12);
		finalTime = System.nanoTime();
		printTime(finalTime - startTime);

		startTime = System.nanoTime();
		dll.replaceEnd(5);
		finalTime = System.nanoTime();
		printTime(finalTime - startTime);

		startTime = System.nanoTime();
		dll.replaceByIndex(4, 2);
		finalTime = System.nanoTime();
		printTime(finalTime - startTime);

		startTime = System.nanoTime();
		dll.calcSum();
		finalTime = System.nanoTime();
		printTime(finalTime - startTime);

		startTime = System.nanoTime();
		dll.searchIndex(8);
		finalTime = System.nanoTime();
		printTime(finalTime - startTime);


        /*
        long start = System.nanoTime();
		LL linkedList = new LL();
        long finish = System.nanoTime();
        System.out.println(finish - start);

        start = System.nanoTime();
		linkedList.append(5);
		finish = System.nanoTime();
		System.out.println(finish - start);

        start = System.nanoTime();
		linkedList.append(87);
		finish = System.nanoTime();
		System.out.println(finish - start);

		linkedList.printList();
		linkedList.removeBeginning();
		linkedList.printList();




        long start = System.nanoTime();
        List list = new MyArrayList();
        long finish = System.nanoTime();
        System.out.println(finish - start);

        start = System.nanoTime();
        list.push(4);
        finish = System.nanoTime();
        System.out.println(finish - start);

        start = System.nanoTime();
        list.append(87);
        finish = System.nanoTime();
        System.out.println(finish - start);

        start = System.nanoTime();
        list.push(87);
        finish = System.nanoTime();
        System.out.println(finish - start);

        list.append(4);
        list.append(87);

        start = System.nanoTime();
        list.addByIndex(87, 2);
        finish = System.nanoTime();
        System.out.println(finish - start);

        start = System.nanoTime();
        list.searchIndex(1);
        finish = System.nanoTime();
        System.out.println(finish - start);

         */
    }

    static void printTime(long a) {
    	System.out.println("Time : " + a);
	}
}
