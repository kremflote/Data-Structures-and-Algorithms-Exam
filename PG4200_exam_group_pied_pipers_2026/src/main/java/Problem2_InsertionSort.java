package main.java;

import java.util.ArrayList;

public class Problem2_InsertionSort {

    public static int insertionSort(ArrayList<Wine> list) {
        int count = 0;

        // Start on the second index as the first is assumed to be sorted
        for (int i = 1; i < list.size(); i++) {

            // Store a copy of the current wine
            Wine current_wine = list.get(i);
            double keyAlcohol = current_wine.alcohol();

            // Compare the current wine with each element to its left.
            // Shift elements with higher alcohol content one position to the right
            // until the correct insertion slot is found.
            int j = i - 1;
            while (j >= 0 && list.get(j).alcohol() > keyAlcohol) {
                list.set(j + 1, list.get(j));
                j--;
                count++;
            }

            // set the current wine-element in the correct place in the sorted list
            list.set(j + 1, current_wine);
        }
        return count;
    }
}
