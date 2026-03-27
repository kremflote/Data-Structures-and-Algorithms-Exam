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

        // Pre-sort the list once for the sorted benchmarks
        ArrayList<Wine> sortedWines = new ArrayList<>(wines);
        Problem1_BubbleSort.bubbleSortOptimised(sortedWines);

        // jvm warmup of every algorithm
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem1_BubbleSort::bubbleSortNonOptimised);
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem1_BubbleSort::bubbleSortOptimised);
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem2_InsertionSort::insertionSort);
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem3_MergeSort::mergeSort);
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem4_QuickSort::quickSortFirst);
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem4_QuickSort::quickSortLast);
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem4_QuickSort::quickSortRandom);
        BenchmarkHandler.jvmWarmup(warmupRounds, wines, Problem4_QuickSort::quickSortMedian);

        System.out.println("\n=======================================================================");
        System.out.println("| Sorting wines in ascending order based on  unique alchohol contents |");
        System.out.println("=======================================================================");

        // Problem 1 - Bubble Sort
        System.out.println("\n======== Problem 1: Bubble Sort ========");
        BenchmarkHandler.benchmark("Non-optimised", testRounds, true,  wines,       Problem1_BubbleSort::bubbleSortNonOptimised, "swaps");
        BenchmarkHandler.benchmark("Optimised",     testRounds, true,  wines,       Problem1_BubbleSort::bubbleSortOptimised,    "swaps");
        BenchmarkHandler.benchmark("Non-optimised", testRounds, false, sortedWines, Problem1_BubbleSort::bubbleSortNonOptimised, "swaps");
        BenchmarkHandler.benchmark("Optimised",     testRounds, false, sortedWines, Problem1_BubbleSort::bubbleSortOptimised,    "swaps");
        System.out.println("\n=======================================");

        // Problem 2 - Insertion Sort
        System.out.println("\n======== Problem 2: Insertion Sort ========");
        BenchmarkHandler.benchmark("Insertion Sort", testRounds, true,  wines,       Problem2_InsertionSort::insertionSort, "comparisons");
        BenchmarkHandler.benchmark("Insertion Sort", testRounds, false, sortedWines, Problem2_InsertionSort::insertionSort, "comparisons");
        System.out.println("\n=======================================");

        // Problem 3 - Merge Sort
        System.out.println("\n======== Problem 3: Merge Sort ========");
        BenchmarkHandler.benchmark("Merge Sort", testRounds, true,  wines,       Problem3_MergeSort::mergeSort, "merge operations");
        BenchmarkHandler.benchmark("Merge Sort", testRounds, false, sortedWines, Problem3_MergeSort::mergeSort, "merge operations");
        System.out.println("\n=======================================");

        // Problem 4 - Quick Sort
        System.out.println("\n======== Problem 4: Quick Sort ========");
        BenchmarkHandler.benchmark("Quick Sort - First pivot",  testRounds, true,  wines,       Problem4_QuickSort::quickSortFirst,  "comparisons");
        BenchmarkHandler.benchmark("Quick Sort - First pivot",  testRounds, false, sortedWines, Problem4_QuickSort::quickSortFirst,  "comparisons");
        BenchmarkHandler.benchmark("Quick Sort - Last pivot",   testRounds, true,  wines,       Problem4_QuickSort::quickSortLast,   "comparisons");
        BenchmarkHandler.benchmark("Quick Sort - Last pivot",   testRounds, false, sortedWines, Problem4_QuickSort::quickSortLast,   "comparisons");
        BenchmarkHandler.benchmark("Quick Sort - Random pivot", testRounds, true,  wines,       Problem4_QuickSort::quickSortRandom, "comparisons");
        BenchmarkHandler.benchmark("Quick Sort - Random pivot", testRounds, false, sortedWines, Problem4_QuickSort::quickSortRandom, "comparisons");
        BenchmarkHandler.benchmark("Quick Sort - Median pivot", testRounds, true,  wines,       Problem4_QuickSort::quickSortMedian, "comparisons");
        BenchmarkHandler.benchmark("Quick Sort - Median pivot", testRounds, false, sortedWines, Problem4_QuickSort::quickSortMedian, "comparisons");
        System.out.println("\n=======================================");
    }
}