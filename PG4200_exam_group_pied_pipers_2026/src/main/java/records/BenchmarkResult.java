package main.java.records;

import main.java.enums.InputType;
import main.java.enums.OperationLabel;

public record BenchmarkResult(
        String name,
        InputType inputType,
        double avgMicros,
        long avgOperations,
        OperationLabel operationLabel
) {}