package main.java;

import java.util.ArrayList;
import java.util.List;

public class Problem3_MergeSort {

    // Wrapper method so our benchmark tool can call the recursive method
    public static void mergeSort(ArrayList<Wine> list) {
        mergeSort(list, 0, list.size() - 1);
    }
    private static void mergeSort(ArrayList<Wine> list, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(list, left, mid);
        mergeSort(list, mid + 1, right);

        merge(list, left, mid, right);
    }

    private static void merge(ArrayList<Wine> list, int left, int mid, int right) {
        List<Wine> temp = new ArrayList<>();

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (list.get(i).alcohol() <= list.get(j).alcohol()) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
            }
        }

        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }
}