package main.java;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String whitePath = "src/main/resources/winequality-white.csv";
        String redPath = "src/main/resources/winequality-red.csv";
        ArrayList<Wine> wines = DataHandler.loadAllWines(redPath, whitePath);
        System.out.println("Total wines loaded: " + wines.size());

        int warmupRounds = 10;
        int testRounds = 100;

        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem1_BubbleSort::bubbleSortNonOptimised);
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem1_BubbleSort::bubbleSortOptimised);

        System.out.println("\n=======================================================================");
        System.out.println("| Sorting wines in ascending order based on  unique alchohol contents |");
        System.out.println("=======================================================================");

        // Problem 1 - Bubble Sort
        // Shuffled - shows average case
        System.out.println("\n--- Problem 1: Bubble Sort Sort ---");
        BenchmarkHandler.benchmark("Non-optimised", testRounds, true, wines, Problem1_BubbleSort::bubbleSortNonOptimised);
        BenchmarkHandler.benchmark("Optimised", testRounds, true, wines, Problem1_BubbleSort::bubbleSortOptimised);

        // Pre-sort the list once for the sorted benchmarks
        ArrayList<Wine> sortedWines = new ArrayList<>(wines);
        Problem1_BubbleSort.bubbleSortOptimised(sortedWines);

        // Sorted - shows best case advantage of optimised
        BenchmarkHandler.benchmark("Non-optimised", testRounds, false, sortedWines, Problem1_BubbleSort::bubbleSortNonOptimised);
        BenchmarkHandler.benchmark("Optimised", testRounds, false, sortedWines, Problem1_BubbleSort::bubbleSortOptimised);

        // Problem 2 - Insertion Sort
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem2_InsertionSort::insertionSort);

        System.out.println("\n--- Problem 2: Insertion Sort ---");
        BenchmarkHandler.benchmark("Insertion Sort", testRounds, true, wines, Problem2_InsertionSort::insertionSort);
        BenchmarkHandler.benchmark("Insertion Sort", testRounds, false, sortedWines, Problem2_InsertionSort::insertionSort);

        // Problem 3 - Merge Sort
        System.out.println("\n--- Problem 3: Merge Sort ---");
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem3_MergeSort::mergeSort);

        BenchmarkHandler.benchmark("Merge Sort", testRounds, true, wines, Problem3_MergeSort::mergeSort);
        BenchmarkHandler.benchmark("Merge Sort", testRounds, false, sortedWines, Problem3_MergeSort::mergeSort);

        // Problem 4 - Quick Sort
        System.out.println("\n--- Problem 4: Quick Sort ---");
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem4_QuickSort::quickSortFirst);
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem4_QuickSort::quickSortLast);
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem4_QuickSort::quickSortRandom);
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem4_QuickSort::quickSortMedian);

        BenchmarkHandler.benchmark("Quick Sort - First pivot",  testRounds, true,  wines,       Problem4_QuickSort::quickSortFirst);
        BenchmarkHandler.benchmark("Quick Sort - First pivot",  testRounds, false, sortedWines, Problem4_QuickSort::quickSortFirst);

        BenchmarkHandler.benchmark("Quick Sort - Last pivot",   testRounds, true,  wines,       Problem4_QuickSort::quickSortLast);
        BenchmarkHandler.benchmark("Quick Sort - Last pivot",   testRounds, false, sortedWines, Problem4_QuickSort::quickSortLast);

        BenchmarkHandler.benchmark("Quick Sort - Random pivot", testRounds, true,  wines,       Problem4_QuickSort::quickSortRandom);
        BenchmarkHandler.benchmark("Quick Sort - Random pivot", testRounds, false, sortedWines, Problem4_QuickSort::quickSortRandom);

        BenchmarkHandler.benchmark("Quick Sort - Median pivot", testRounds, true,  wines,       Problem4_QuickSort::quickSortMedian);
        BenchmarkHandler.benchmark("Quick Sort - Median pivot", testRounds, false, sortedWines, Problem4_QuickSort::quickSortMedian);
    }
}