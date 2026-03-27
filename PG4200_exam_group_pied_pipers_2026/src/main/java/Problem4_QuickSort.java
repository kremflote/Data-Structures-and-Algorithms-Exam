package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Problem4_QuickSort {

    // Wrapper functions to call them in main without using lambda expressions
    public static void quickSortFirst(ArrayList<Wine> list) {
        quickSort(list, 0, list.size() - 1, "first");
    }

    public static void quickSortLast(ArrayList<Wine> list) {
        quickSort(list, 0, list.size() - 1, "last");
    }

    public static void quickSortRandom(ArrayList<Wine> list) {
        quickSort(list, 0, list.size() - 1, "random");
    }

    public static void quickSortMedian(ArrayList<Wine> list) {
        quickSort(list, 0, list.size() - 1, "median");
    }

    private static void quickSort(ArrayList<Wine> list, int low, int high, String pivotType) {
        if (low >= high) return; // Base case: only 1 element, already sorted
        int pivotIndex = partition(list, low, high, pivotType); // Partition and get pivot position
        quickSort(list, low, pivotIndex - 1, pivotType);  // Sort left of pivot
        quickSort(list, pivotIndex + 1, high, pivotType); // Sort right of pivot
    }

    private static int partition(ArrayList<Wine> list, int low, int high, String pivotType) {

        // Move the chosen pivot to the end before partitioning
        if (pivotType.equals("first")) {
            // First element as pivot - bad on sorted data, always worst case split
            Collections.swap(list, low, high);

        } else if (pivotType.equals("random")) {
            // Random element as pivot - best general choice, unpredictable worst case
            int randomIndex = low + new Random().nextInt(high - low + 1);
            Collections.swap(list, randomIndex, high);

        } else if (pivotType.equals("median")) {
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
            // If last is already median, no swap needed - it's already at high

        }
        // Default (pivotType "last"): last element is pivot, already in position

        // Everything below is the same regardless of pivot strategy
        // since we always move the pivot to the end before this point
        double pivot = list.get(high).alcohol();
        int i = low - 1;

        // Walk through the section, moving elements smaller than pivot to the left
        for (int j = low; j < high; j++) {
            if (list.get(j).alcohol() < pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }

        // Place pivot in its final sorted position
        Collections.swap(list, i + 1, high);
        return i + 1; // Return the pivot's final index
    }
}