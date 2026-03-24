package main.java;

import java.util.ArrayList;

public class Problem1_BubbleSort {

    // Non-optimised bubblesort
    public static void bubbleSortNonOptimised(ArrayList<Wine> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (list.get(j).alcohol() > list.get(j + 1).alcohol()) {
                    Wine temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    // Optimised: exits early if no swaps happen in a pass, and reduces range for each pass
    public static void bubbleSortOptimised(ArrayList<Wine> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).alcohol() > list.get(j + 1).alcohol()) {
                    Wine temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}