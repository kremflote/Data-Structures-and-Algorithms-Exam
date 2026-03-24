package main.java;

import java.util.ArrayList;

public class Problem2_InsertionSort {
    public static void insertionSort(ArrayList<Wine> list) {
        for (int i = 1; i < list.size(); i++) {
            Wine key = list.get(i);
            double keyAlcohol = key.alcohol();
            int j = i - 1;
            while (j >= 0 && list.get(j).alcohol() > keyAlcohol) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}
