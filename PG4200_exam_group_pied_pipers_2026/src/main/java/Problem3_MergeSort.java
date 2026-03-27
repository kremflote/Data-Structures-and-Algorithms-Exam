package main.java;

import java.util.ArrayList;
import java.util.List;

public class Problem3_MergeSort {

    // Wrapper method so our benchmark tool can call the recursive method
    public static int mergeSort(ArrayList<Wine> list) {
        return mergeSort(list, 0, list.size() - 1);
    }

    // Method only containing splitting logic
    private static int mergeSort(ArrayList<Wine> list, int left, int right) {
        if (left >= right) return 0;                        // If there is only 1 element, already sorted. Return.

        int mid = left + (right - left) / 2;                // Find the middle

        int mergeCounts = mergeSort(list, left, mid)              // Sort everything left of mid until it hits single elements
                + mergeSort(list, mid + 1, right);      // Sort everything right of mid the same way

        mergeCounts += merge(list, left, mid, right);             // Merge sorted halves, count the operation
        return mergeCounts;
    }

    // Merges two sorted halves into a single sorted list
    private static int merge(ArrayList<Wine> list, int left, int mid, int right) {
        List<Wine> temp = new ArrayList<>();                // Temporary list for the sorted result before writing back

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {                    // Compares current element of each half, adds the smaller one to temp
            if (list.get(i).alcohol() <= list.get(j).alcohol()) {
                temp.add(list.get(i++));                    // Take the smallest value from left half
            } else {
                temp.add(list.get(j++));                    // Take the smallest value from the right half
            }
        }
        while (i <= mid) temp.add(list.get(i++));           // Checks for leftover elements in left half, append to temp
        while (j <= right) temp.add(list.get(j++));         // Checks for leftover elements in right half, append to temp

        for (int k = 0; k < temp.size(); k++) {             // Write the sorted temp list back into the original list
            list.set(left + k, temp.get(k));
        }
        return 1;                                           // Count this merge operation
    }
}