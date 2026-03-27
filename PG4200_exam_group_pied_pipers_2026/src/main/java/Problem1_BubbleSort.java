package main.java;

import java.util.ArrayList;

public class Problem1_BubbleSort {

    public static int bubbleSortNonOptimised(ArrayList<Wine> list) {
        int n = list.size();
        int swaps = 0;
        // Grabs an element in the list
        for (int i = 0; i < n - 1; i++) {

            // Compares that element to the adjacent element, swap places if it's bigger than the adjacent one
            // Repeat for-loop until it reaches end of list, even though no swaps occurred
            for (int j = 0; j < n - 1; j++) {
                if (list.get(j).alcohol() > list.get(j + 1).alcohol()) {
                    Wine temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                }
            }
        }
        return swaps;
    }

    // Optimised: exits early if no swaps happen in the outer pass, and reduces range for each inner pass
    public static int bubbleSortOptimised(ArrayList<Wine> list) {
        int n = list.size();
        int swaps = 0; // Counts number of swaps done
        // Grabs an element in the list
        for (int i = 0; i < n - 1; i++) {

            // Flag to track if the current element swapped places or not
            boolean swapped = false;

            // Compares that element to the adjacent element, swap places if it's bigger than the adjacent one
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).alcohol() > list.get(j + 1).alcohol()) {
                    Wine temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    // Tell algorithm that the element was swapped, make the loop run again
                    swapped = true;
                }
            }
            // Abort if no swaps occurred, check next element in outer loop
            // If list is ordered, the inner loop will never run
            if (!swapped) break;
        }
        return swaps;
    }
}
