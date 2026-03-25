package main.java;

import java.util.ArrayList;
import java.util.List;

public class Problem3_MergeSort {
    public static int mergeSort(ArrayList<Wine> list, int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        int count = mergeSort(list, left, mid)
                + mergeSort(list, mid + 1, right);
        count += merge(list, left, mid, right);
        return count;
    }

    private static int merge(ArrayList<Wine> list, int left, int mid, int right) {
        List<Wine> temp = new ArrayList<>();
        int i = left, j = mid + 1, count = 0;
        while (i <= mid && j <= right) {
            if (list.get(i).alcohol() <= list.get(j).alcohol()) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
                count++;
            }
        }
        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));
        for (int k = 0; k < temp.size(); k++)
            list.set(left + k, temp.get(k));
        return count;
    }
}