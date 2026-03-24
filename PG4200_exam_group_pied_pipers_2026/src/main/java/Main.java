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

        // Shuffled - shows average case
        BenchmarkHandler.benchmark("Non-optimised Bubble Sort", testRounds, true, wines, Problem1_BubbleSort::bubbleSortNonOptimised);
        BenchmarkHandler.benchmark("Optimised Bubble Sort", testRounds, true, wines, Problem1_BubbleSort::bubbleSortOptimised);

        // Pre-sort the list once for the sorted benchmarks
        ArrayList<Wine> sortedWines = new ArrayList<>(wines);
        Problem1_BubbleSort.bubbleSortOptimised(sortedWines);

        // Sorted - shows best case advantage of optimised
        BenchmarkHandler.benchmark("Non-optimised Bubble Sort", testRounds, false, sortedWines, Problem1_BubbleSort::bubbleSortNonOptimised);
        BenchmarkHandler.benchmark("Optimised Bubble Sort", testRounds, false, sortedWines, Problem1_BubbleSort::bubbleSortOptimised);
    }
}