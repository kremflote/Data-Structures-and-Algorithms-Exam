package main.java;

import main.java.records.BenchmarkResult;
import main.java.enums.InputType;
import main.java.enums.OperationLabel;
import main.java.records.Wine;

import java.util.ArrayList;
import java.util.function.Function;

public class BenchmarkHandler {

    // The JVM's JIT compiler will optimize the code after running a couple of times. This affects the time measurements.
    // jvmWarmup eliminates the issue by triggering optimizations before the benchmarks.
    public static void jvmWarmup(int warmupRounds, ArrayList<Wine> raw,
                                 ArrayList<Wine> shuffled, ArrayList<Wine> sorted,
                                 Function<ArrayList<Wine>, Integer> algorithm) {
        for (int i = 0; i < warmupRounds; i++) {
            algorithm.apply(new ArrayList<>(raw));
            algorithm.apply(new ArrayList<>(shuffled));
            algorithm.apply(new ArrayList<>(sorted));
        }
    }

    public static BenchmarkResult benchmark(String name, int testRounds, InputType inputType,
                                            ArrayList<Wine> wines, Function<ArrayList<Wine>, Integer> algorithm,
                                            OperationLabel operationLabel) {
        Timer timer = new Timer();
        int totalCount = 0;
        for (int i = 0; i < testRounds; i++) {
            ArrayList<Wine> list = new ArrayList<>(wines);
            timer.start();
            totalCount += algorithm.apply(list);
            timer.stop();
        }
        return new BenchmarkResult(name, inputType, timer.averageMicros(), totalCount / testRounds, operationLabel);
    }
}