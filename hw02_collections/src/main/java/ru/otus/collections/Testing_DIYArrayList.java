package ru.otus.collections;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Testing_DIYArrayList {
    private static void printElements(DIYArrayList arrayList) {
        for(int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i));
        }
        System.out.println("\n---------------");
    }

    public static void main(String[] args) {


        List<String> arrlist = new DIYArrayList<>();

        Collections.addAll(arrlist, "BB", "AA", "CC", "DD", "BB", "AA", "CC", "DD", "BB", "AA", "ZZ");
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
        List<Integer> src = IntStream.range(0, 100).boxed().collect(Collectors.toList());
        List<Integer> dest = new DIYArrayList<>();
        for(int i = 0; i < src.size(); i++) {
            dest.add(i);
        }
        Collections.copy(dest, src);
        printElements((DIYArrayList) dest);
    }


}
