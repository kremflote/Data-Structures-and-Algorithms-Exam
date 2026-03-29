package main.java;

import main.java.records.BenchmarkResult;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrintHandler {

    private PrintHandler() {}

    public static void printResults(ArrayList<BenchmarkResult> results, boolean exportToFile) {
        String output = formatResults(results);
        System.out.println(output);
        if (exportToFile) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("benchmark_results.txt"))) {
                writer.println(output);
                System.out.println("exportToFile-flag set to TRUE.");
                System.out.println("Results saved in project root. ");
            } catch (IOException e) {
                System.err.println("Error writing results to file.");
            }
        }
        else {System.out.println("exportToFile-flag set to FALSE. Results are not saved. Set to TRUE in main if need to save results locally.");}
    }

    private static String formatResults(ArrayList<BenchmarkResult> results) {
        int width = 92;
        String border = "=".repeat(width);
        String divider = "-".repeat(width);
        String title = "Sorting algorithm performance comparison";
        int padding = (width - 2 - title.length()) / 2;
        String centeredTitle = "|" + " ".repeat(padding) + title + " ".repeat(width - 2 - padding - title.length()) + "|";

        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(border).append("\n");
        sb.append(centeredTitle).append("\n");
        sb.append(border).append("\n");
        sb.append(String.format("%-30s %-10s %-15s %-25s%n", "Algorithm", "Input", "Avg Time", "Avg Operations"));
        sb.append(divider).append("\n");

        String lastName = "";
        for (BenchmarkResult r : results) {
            if (!r.name().equals(lastName)) {
                if (!lastName.isEmpty()) sb.append("\n");
                lastName = r.name();
            }
            sb.append(String.format("| %-28s %-10s %-15s %-33s|%n",
                    r.name(),
                    r.inputType().getLabel(),
                    formatTime(r.avgMicros()),
                    r.avgOperations() + " " + r.operationLabel().getLabel()));
        }
        sb.append(border).append("\n");
        return sb.toString();
    }

    private static String formatTime(double micros) {
        if (micros >= 1000) return String.format("%.2f ms", micros / 1000);
        if (micros >= 1)    return String.format("%.0f µs", micros);
        return String.format("%.2f µs", micros);
    }
}