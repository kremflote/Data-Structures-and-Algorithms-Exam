package main.java;

import java.util.ArrayList;
import java.util.List;

public class Problem3_MergeSort {
    public static int mergeSort(ArrayList<Wine> list, int left, int right) {
        if (left >= right) return 0; // If there is only 1 element, already sorted. Return.
        int mid = left + (right - left) / 2; // Find the middle
        int count = mergeSort(list, left, mid) // Sort the left half
                + mergeSort(list, mid + 1, right); // sort the right half
        count += merge(list, left, mid, right); // Merge them together
        return count;
    }

    private static int merge(ArrayList<Wine> list, int left, int mid, int right) {
        List<Wine> temp = new ArrayList<>();
        int i = left, j = mid + 1, count = 0;
        while (i <= mid && j <= right) {
            if (list.get(i).alcohol() <= list.get(j).alcohol()) {
                temp.add(list.get(i++)); // Take the smallest value from left half
            } else {
                temp.add(list.get(j++)); // Take the smallest value from the right half
                count++;
            }
        }
        while (i <= mid) temp.add(list.get(i++)); // Checks for leftover elements in left half
        while (j <= right) temp.add(list.get(j++)); // Checks for leftover elements in right half
        for (int k = 0; k < temp.size(); k++)
            list.set(left + k, temp.get(k));
        return count;
    }
}