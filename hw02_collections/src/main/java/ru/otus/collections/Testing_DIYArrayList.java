package ru.otus.collections;

import java.util.Collections;
import java.util.List;

public class Testing_DIYArrayList {
    private static void printElements(DIYArrayList arrayList) {
        for(int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i));
        }
        System.out.println("\n---------------");
    }

    public static void main(String[] args) {
        //List<String> arrlist13 = new DIYArrayList<>();
        //Collections.addAll(arrlist13, "BB", "AA", "CC", "DD", "BB", "AA", "CC", "DD", "BB", "AA");

        //List<String> arrlist12 = new DIYArrayList<>();
        //for (int i = 0; i < 21; i++) {
        //    arrlist12.add("element" + i);
        //}
        //Collections.copy(arrlist12, arrlist13);
        List<String> arrlist = new DIYArrayList<>();
        arrlist.add("B");
        arrlist.add("A");
        arrlist.add("D");
        arrlist.add("C");
        printElements((DIYArrayList) arrlist);

        Collections.addAll(arrlist, "BB","AA", "CC", "DD");
        printElements((DIYArrayList) arrlist);

        List<String> arrlist2 = new DIYArrayList<>();
        for(int i = 0; i<21; i++) {
            arrlist2.add("element" + i);
        }
        printElements((DIYArrayList) arrlist2);

        Collections.copy(arrlist2, arrlist);
        printElements((DIYArrayList) arrlist2);

        Collections.sort(arrlist2, Collections.reverseOrder());
        printElements((DIYArrayList) arrlist2);
        List<Integer> list = new DIYArrayList<>();
        for(int i = 1; i <= 200; i++){
            list.add(i);
        }
        printElements((DIYArrayList) list);
    }


}
