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
        ArrayList<Wine> wines = DataParser.loadAllWines(redPath, whitePath);
        System.out.println("Total wines loaded: " + wines.size());

        int warmupRounds = 10;
        int testRounds = 100;

        // preparing data for benchmarking
        ArrayList<Wine> rawWines = new ArrayList<>(wines);      // unprocessed data from source
        ArrayList<Wine> shuffledWines = new ArrayList<>(wines); // shuffled data for problems 1b and 2b
        ArrayList<Wine> sortedWines = new ArrayList<>(wines);   // sorted data for omega benchmarking

        Collections.shuffle(shuffledWines);
        Problem1_BubbleSort.bubbleSortOptimised(sortedWines);   // utilizing the appropriate algorithm given the data size and input order to sort the data
        ArrayList<BenchmarkResult> results = new ArrayList<>();

        // JVM warmup of every algorithm
        System.out.println("--- | Warming up JVM | ---");
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem1_BubbleSort::bubbleSortNonOptimised);
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem1_BubbleSort::bubbleSortOptimised);
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem2_InsertionSort::insertionSort);
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem3_MergeSort::mergeSort);
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem4_QuickSort::quickSortFirst);
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem4_QuickSort::quickSortLast);
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem4_QuickSort::quickSortRandom);
        BenchmarkHandler.jvmWarmup(warmupRounds, rawWines, shuffledWines, sortedWines, Problem4_QuickSort::quickSortMedian);
        System.out.println("--- | Warmup done | ---");

        // Problem 1 - Bubble Sort
        results.add(BenchmarkHandler.benchmark("Bubble Sort Non-optimised", testRounds, InputType.RAW,      rawWines,      Problem1_BubbleSort::bubbleSortNonOptimised, OperationLabel.SWAPS));
        results.add(BenchmarkHandler.benchmark("Bubble Sort Non-optimised", testRounds, InputType.SHUFFLED, shuffledWines, Problem1_BubbleSort::bubbleSortNonOptimised, OperationLabel.SWAPS));
        results.add(BenchmarkHandler.benchmark("Bubble Sort Non-optimised", testRounds, InputType.SORTED,   sortedWines,   Problem1_BubbleSort::bubbleSortNonOptimised, OperationLabel.SWAPS));
        results.add(BenchmarkHandler.benchmark("Bubble Sort Optimised",     testRounds, InputType.RAW,      rawWines,      Problem1_BubbleSort::bubbleSortOptimised,    OperationLabel.SWAPS));
        results.add(BenchmarkHandler.benchmark("Bubble Sort Optimised",     testRounds, InputType.SHUFFLED, shuffledWines, Problem1_BubbleSort::bubbleSortOptimised,    OperationLabel.SWAPS));
        results.add(BenchmarkHandler.benchmark("Bubble Sort Optimised",     testRounds, InputType.SORTED,   sortedWines,   Problem1_BubbleSort::bubbleSortOptimised,    OperationLabel.SWAPS));

        // Problem 2 - Insertion Sort
        results.add(BenchmarkHandler.benchmark("Insertion Sort", testRounds, InputType.RAW,      rawWines,      Problem2_InsertionSort::insertionSort, OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Insertion Sort", testRounds, InputType.SHUFFLED, shuffledWines, Problem2_InsertionSort::insertionSort, OperationLabel.COMPARISONS));
        results.add(BenchmarkHandler.benchmark("Insertion Sort", testRounds, InputType.SORTED,   sortedWines,   Problem2_InsertionSort::insertionSort, OperationLabel.COMPARISONS));

        // Problem 3 - Merge Sort
        results.add(BenchmarkHandler.benchmark("Merge Sort", testRounds, InputType.RAW,      rawWines,      Problem3_MergeSort::mergeSort, OperationLabel.MERGE_OPERATIONS));
        results.add(BenchmarkHandler.benchmark("Merge Sort", testRounds, InputType.SHUFFLED, shuffledWines, Problem3_MergeSort::mergeSort, OperationLabel.MERGE_OPERATIONS));
        results.add(BenchmarkHandler.benchmark("Merge Sort", testRounds, InputType.SORTED,   sortedWines,   Problem3_MergeSort::mergeSort, OperationLabel.MERGE_OPERATIONS));

        // Problem 4 - Quick Sort
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

        printResults(results);
    }

    // ----- Helper methods to print results -----
    private static void printResults(ArrayList<BenchmarkResult> results) {
        System.out.println("\n=======================================================================");
        System.out.println("| Sorting wines in ascending order based on unique alcohol contents   |");
        System.out.println("=======================================================================");
        System.out.printf("%-30s %-10s %-15s %-25s%n", "Algorithm", "Input", "Avg Time", "Avg Operations");
        System.out.println("-".repeat(80));

        String lastName = "";
        for (BenchmarkResult r : results) {
            if (!r.name().equals(lastName)) {
                if (!lastName.isEmpty()) System.out.println();
                lastName = r.name();
            }
            System.out.printf("%-30s %-10s %-15s %d %s%n",
                    r.name(), r.inputType(), formatTime(r.avgMicros()),
                    r.avgOperations(), r.operationLabel().getLabel());
        }
    }

    private static String formatTime(double micros) {
        if (micros >= 1000) return String.format("%.2f ms", micros / 1000);
        if (micros >= 1)    return String.format("%.0f µs", micros);
        return String.format("%.2f µs", micros);
    }
}