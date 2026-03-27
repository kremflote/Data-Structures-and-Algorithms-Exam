package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Problem4_QuickSort {

    // Wrapper functions to call them in main without using lambda expressions
    public static int quickSortFirst(ArrayList<Wine> list) {
        return quickSort(list, 0, list.size() - 1, "first");
    }

    public static int quickSortLast(ArrayList<Wine> list) {
        return quickSort(list, 0, list.size() - 1, "last");
    }

    public static int quickSortRandom(ArrayList<Wine> list) {
        return quickSort(list, 0, list.size() - 1, "random");
    }

    public static int quickSortMedian(ArrayList<Wine> list) {
        return quickSort(list, 0, list.size() - 1, "median");
    }

    private static int quickSort(ArrayList<Wine> list, int low, int high, String pivotType) {
        if (low >= high) return 0; // Base case: only 1 element, already sorted
        int[] result = partition(list, low, high, pivotType); // [pivotIndex, count]
        int pivotIndex = result[0];
        int count = result[1];
        count += quickSort(list, low, pivotIndex - 1, pivotType);  // Sort left of pivot
        count += quickSort(list, pivotIndex + 1, high, pivotType); // Sort right of pivot
        return count;
    }

    // Returns int[] where [0] = pivot's final index, [1] = comparison count
    private static int[] partition(ArrayList<Wine> list, int low, int high, String pivotType) {

        // Move the chosen pivot to the end before partitioning
        switch (pivotType) {
            case "first" ->
                // First element as pivot - bad on sorted data, always worst case split
                    Collections.swap(list, low, high);
            case "random" -> {
                // Random element as pivot - best general choice, unpredictable worst case
                int randomIndex = low + new Random().nextInt(high - low + 1);
                Collections.swap(list, randomIndex, high);
            }
            case "median" -> {
                // Median of first, middle and last element as pivot
                // Good balance between predictability and performance
                int mid = low + (high - low) / 2;
                double first = list.get(low).alcohol();
                double middle = list.get(mid).alcohol();
                double last = list.get(high).alcohol();

                // Find which of the three values is the median and swap it to the end
                if ((first <= middle && middle <= last) || (last <= middle && middle <= first)) {
                    Collections.swap(list, mid, high);
                } else if ((middle <= first && first <= last) || (last <= first && first <= middle)) {
                    Collections.swap(list, low, high);
                }
            }
            case "last" -> {
                // Do nothing - last element is already in position
            }
        }

        // Everything below is the same regardless of pivot strategy
        // since we always move the pivot to the end before this point
        double pivot = list.get(high).alcohol();
        int i = low - 1, comparisons = 0;

        // Walk through the section, moving elements smaller than pivot to the left
        for (int j = low; j < high; j++) {
            comparisons++; // Count every comparison
            if (list.get(j).alcohol() < pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }

        // Place pivot in its final sorted position
        Collections.swap(list, i + 1, high);
        return new int[]{i + 1, comparisons}; // Return pivot index and comparison count
    }
}