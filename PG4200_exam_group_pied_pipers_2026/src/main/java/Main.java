package main.java;

import main.java.records.BenchmarkResult;
import main.java.enums.InputType;
import main.java.enums.OperationLabel;
import main.java.records.Wine;
import java.util.Collections;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String whitePath = "src/main/resources/winequality-white.csv";
        String redPath = "src/main/resources/winequality-red.csv";

        int warmupRounds = 10;
        int testRounds = 100;
        boolean exportToFile = true;

        // Preparing data for benchmarking
        ArrayList<Wine> uniqueWines = WineLoader.loadUniqueWines(redPath, whitePath);
        System.out.println("Total unique wines loaded: " + uniqueWines.size());
        ArrayList<Wine> rawWines = new ArrayList<>(uniqueWines);                    // unprocessed data from source
        ArrayList<Wine> shuffledWines = new ArrayList<>(uniqueWines);               // shuffled data for problems 1b and 2b
        ArrayList<Wine> sortedWines = new ArrayList<>(uniqueWines);                 // sorted data for omega benchmarking
        ArrayList<Wine> allWines    = WineLoader.loadAllWines(redPath, whitePath);  // a larger dataset to benchmark input size impact
        System.out.println("Total wines with duplicates loaded: " + allWines.size());

        Collections.shuffle(shuffledWines);
        Problem1_BubbleSort.bubbleSortOptimised(sortedWines);   // utilizing the appropriate algorithm given the data size and input order to sort the data
        ArrayList<BenchmarkResult> results = new ArrayList<>();

        // Problem 1 - Bubble Sort
        System.out.println("--- | Warming up Bubble Sort");
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem1_BubbleSort::bubbleSortNonOptimised);
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem1_BubbleSort::bubbleSortOptimised);
        System.out.println("--- | Executing Bubble Sort benchmarks...");
        results.add(BenchmarkHandler.benchmark("Bubble Sort Non-optimized", testRounds, InputType.RAW,      rawWines,      Problem1_BubbleSort::bubbleSortNonOptimised, OperationLabel.SWAPS));
        results.add(BenchmarkHandler.benchmark("Bubble Sort Non-optimized", testRounds, InputType.SHUFFLED, shuffledWines, Problem1_BubbleSort::bubbleSortNonOptimised, OperationLabel.SWAPS));
        results.add(BenchmarkHandler.benchmark("Bubble Sort Non-optimized", testRounds, InputType.SORTED,   sortedWines,   Problem1_BubbleSort::bubbleSortNonOptimised, OperationLabel.SWAPS));
        results.add(BenchmarkHandler.benchmark("Bubble Sort Optimized",     testRounds, InputType.RAW,      rawWines,      Problem1_BubbleSort::bubbleSortOptimised,    OperationLabel.SWAPS));
        results.add(BenchmarkHandler.benchmark("Bubble Sort Optimized",     testRounds, InputType.SHUFFLED, shuffledWines, Problem1_BubbleSort::bubbleSortOptimised,    OperationLabel.SWAPS));
        results.add(BenchmarkHandler.benchmark("Bubble Sort Optimized",     testRounds, InputType.SORTED,   sortedWines,   Problem1_BubbleSort::bubbleSortOptimised,    OperationLabel.SWAPS));
        System.out.println("--- | Bubble Sort benchmarking complete.");

        // Problem 2 - Insertion Sort
        System.out.println("--- | Warming up Insertion Sort");
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem2_InsertionSort::insertionSort);
        System.out.println("--- | Executing Insertion Sort benchmarks...");
        results.add(BenchmarkHandler.benchmark("Insertion Sort", testRounds, InputType.RAW,      rawWines,      Problem2_InsertionSort::insertionSort, OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Insertion Sort", testRounds, InputType.SHUFFLED, shuffledWines, Problem2_InsertionSort::insertionSort, OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Insertion Sort", testRounds, InputType.SORTED,   sortedWines,   Problem2_InsertionSort::insertionSort, OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Insertion Sort", testRounds, InputType.ALL,       allWines,      Problem2_InsertionSort::insertionSort, OperationLabel.COMPARISONS));
        System.out.println("--- | Insertion Sort benchmarking complete.");

        // Problem 3 - Merge Sort
        System.out.println("--- | Warming up Merge Sort");
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem3_MergeSort::mergeSort);
        System.out.println("--- | Executing Merge Sort benchmarks...");
        results.add(BenchmarkHandler.benchmark("Merge Sort", testRounds, InputType.RAW,      rawWines,      Problem3_MergeSort::mergeSort, OperationLabel.MERGE_OPERATIONS));
        results.add(BenchmarkHandler.benchmark("Merge Sort", testRounds, InputType.SHUFFLED, shuffledWines, Problem3_MergeSort::mergeSort, OperationLabel.MERGE_OPERATIONS));
        results.add(BenchmarkHandler.benchmark("Merge Sort", testRounds, InputType.SORTED,   sortedWines,   Problem3_MergeSort::mergeSort, OperationLabel.MERGE_OPERATIONS));
        results.add(BenchmarkHandler.benchmark("Merge Sort", testRounds, InputType.ALL,       allWines,      Problem3_MergeSort::mergeSort, OperationLabel.MERGE_OPERATIONS));
        System.out.println("--- | Insertion Merge benchmarking complete.");

        // Problem 4 - Quick Sort
        System.out.println("--- | Warming up Quick Sort | ---");
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem4_QuickSort::quickSortFirst);
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem4_QuickSort::quickSortLast);
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem4_QuickSort::quickSortRandom);
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem4_QuickSort::quickSortMedian);
        System.out.println("--- | Executing Quick Sort benchmarks...");
        results.add(BenchmarkHandler.benchmark("Quick Sort - First pivot",  testRounds, InputType.RAW,      rawWines,      Problem4_QuickSort::quickSortFirst,  OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Quick Sort - First pivot",  testRounds, InputType.SHUFFLED, shuffledWines, Problem4_QuickSort::quickSortFirst,  OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Quick Sort - First pivot",  testRounds, InputType.SORTED,   sortedWines,   Problem4_QuickSort::quickSortFirst,  OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Quick Sort - Last pivot",   testRounds, InputType.RAW,      rawWines,      Problem4_QuickSort::quickSortLast,   OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Quick Sort - Last pivot",   testRounds, InputType.SHUFFLED, shuffledWines, Problem4_QuickSort::quickSortLast,   OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Quick Sort - Last pivot",   testRounds, InputType.SORTED,   sortedWines,   Problem4_QuickSort::quickSortLast,   OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Quick Sort - Random pivot", testRounds, InputType.RAW,      rawWines,      Problem4_QuickSort::quickSortRandom, OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Quick Sort - Random pivot", testRounds, InputType.SHUFFLED, shuffledWines, Problem4_QuickSort::quickSortRandom, OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Quick Sort - Random pivot", testRounds, InputType.SORTED,   sortedWines,   Problem4_QuickSort::quickSortRandom, OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Quick Sort - Median pivot", testRounds, InputType.RAW,      rawWines,      Problem4_QuickSort::quickSortMedian, OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Quick Sort - Median pivot", testRounds, InputType.SHUFFLED, shuffledWines, Problem4_QuickSort::quickSortMedian, OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Quick Sort - Median pivot", testRounds, InputType.SORTED,   sortedWines,   Problem4_QuickSort::quickSortMedian, OperationLabel.COMPARISONS));
        System.out.println("--- | Insertion Quick benchmarking complete.");

        PrintHandler.printResults(results, exportToFile);
    }
}