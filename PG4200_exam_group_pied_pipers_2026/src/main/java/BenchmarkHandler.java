package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class BenchmarkHandler {

    // The JVM's JIT compiler will optimize the code after running a couple of times.
    // This affects the time measurements.
    // This method eliminates this issue by triggering optimization before doing the benchmarks.
    public static void jvmWarmup(int warmupRounds, ArrayList<Wine> wines,
                                 Consumer<ArrayList<Wine>> algorithm) {
        System.out.println("--- | Warming up JVM | ---");
        for (int i = 0; i < warmupRounds; i++) {
            ArrayList<Wine> warmup = new ArrayList<>(wines);
            Collections.shuffle(warmup);
            algorithm.accept(warmup);
        }
        System.out.println("Warmup done.");
    }

    public static void benchmark(String name, int testRounds, boolean shuffle,
                                 ArrayList<Wine> wines, Consumer<ArrayList<Wine>> algorithm) {
        Timer timer = new Timer();
        for (int i = 0; i < testRounds; i++) {
            ArrayList<Wine> list = new ArrayList<>(wines);
            if (shuffle) Collections.shuffle(list);
            timer.start();
            algorithm.accept(list);
            timer.stop();
        }
        String shuffleLabel = shuffle ? "shuffled" : "sorted";
        System.out.println("\n--- | " + name + " - " + shuffleLabel + " (" + testRounds + " runs) | ---");
        if (timer.totalMillis() < 1.0) {
            System.out.printf("Total:   %.0f µs%n", timer.totalMicros());
        } else {
            System.out.printf("Total:   %.0f ms%n", timer.totalMillis());
        }


        if (timer.averageMillis() < 0.001) {
            System.out.printf("Average: %.2f µs%n", timer.averageMicros());
        } else if (timer.averageMillis() < 1.0) {
            System.out.printf("Average: %.0f µs%n", timer.averageMicros());
        } else {
            System.out.printf("Average: %.2f ms%n", timer.averageMillis());
        }
    }
}