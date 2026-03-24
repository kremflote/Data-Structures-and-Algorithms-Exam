import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        /* Import data and save as records in an ArrayList */
        String whitePath = "resources/winequality-white.csv";
        String redPath = "resources/winequality-red.csv";
        ArrayList<Wine> wines = DataHandler.loadAllWines(redPath, whitePath);
        System.out.println("Total wines loaded: " + wines.size());

        /* "Warm up" the JIT X times */
        int warmupRounds = 10;
        /* How many times we run each test, giving us an averaged result */
        int testRounds = 100;

        jvmWarmup(warmupRounds, wines, Problem1_BubbleSort::bubbleSortNonOptimised);
        jvmWarmup(warmupRounds, wines, Problem1_BubbleSort::bubbleSortOptimised);

        benchmark("Optimised Bubble Sort", testRounds, wines, Problem1_BubbleSort::bubbleSortOptimised);
        benchmark("Non-optimised Bubble Sort", testRounds, wines, Problem1_BubbleSort::bubbleSortNonOptimised);
    }

    /* Helper method to warm up any given algorithm to avoid artificial spiking in time measurements  */
    private static void jvmWarmup(int warmupRounds, ArrayList<Wine> wines,
                                  java.util.function.Consumer<ArrayList<Wine>> algorithm) {
        System.out.println("--- | Warming up JVM | ---");
        for (int i = 0; i < warmupRounds; i++) {
            ArrayList<Wine> warmup = new ArrayList<>(wines);
            Collections.shuffle(warmup);
            algorithm.accept(warmup);
        }
        System.out.println("Warmup done.");
    }

    /* Helper method to measure and print the results of a benchmark on any given algorithm */
    private static void benchmark(String name, int testRounds, ArrayList<Wine> wines,
                                  java.util.function.Consumer<ArrayList<Wine>> algorithm) {
        Timer timer = new Timer();
        for (int i = 0; i < testRounds; i++) {
            ArrayList<Wine> list = new ArrayList<>(wines);
            Collections.shuffle(list);
            timer.start();
            algorithm.accept(list);
            timer.stop();
        }
        System.out.println("\n--- | " + name + " (" + testRounds + " runs) | ---");
        System.out.println("Total:   " + timer.totalMicros() + " µs");
        System.out.println("Average: " + timer.averageMicros() + " µs");
    }
}