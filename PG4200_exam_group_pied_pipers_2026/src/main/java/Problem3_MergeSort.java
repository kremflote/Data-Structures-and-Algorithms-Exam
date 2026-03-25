package main.java;

import java.util.ArrayList;
import java.util.List;

public class Problem3_MergeSort {

    // Wrapper method so our benchmark tool can call the recursive method
    public static void mergeSort(ArrayList<Wine> list) {
        mergeSort(list, 0, list.size() - 1);
    }

    // Merge Sort Implementation. Time complexity: O(n log n) in all cases.
    // Splits the list in half until each sublist contains one element
    // Then it merges the sublists back together in sorted order
    private static void mergeSort(ArrayList<Wine> list, int left, int right) {
        if (left >= right) return;              // If there is only 1 element, already sorted. Return.

        int mid = left + (right - left) / 2;    // Find the middle

        mergeSort(list, left, mid);             // Sort left half
        mergeSort(list, mid + 1, right);    // sort right half

        merge(list, left, mid, right);          // Merge sorted halves
    }

    // Merges two already sorted halves into a single sorted section
    private static void merge(ArrayList<Wine> list, int left, int mid, int right) {
        List<Wine> temp = new ArrayList<>();

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (list.get(i).alcohol() <= list.get(j).alcohol()) {
                temp.add(list.get(i++));        // Take the smallest value from left half
            } else {
                temp.add(list.get(j++));        // Take the smallest value from the right half
            }
        }
        while (i <= mid) temp.add(list.get(i++));   // Checks for leftover elements in left half
        while (j <= right) temp.add(list.get(j++)); // Checks for leftover elements in right half

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }
}