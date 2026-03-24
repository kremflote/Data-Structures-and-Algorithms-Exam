package main.java;

public class Timer {
    private long startTime;
    private long endTime;
    private long totalTime;
    private int laps;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
        totalTime += (endTime - startTime); // store raw nanoseconds
        laps++;
    }

    public double totalMillis() {
        return totalTime / 1_000_000.0; // convert only for display
    }

    public double averageMillis() {
        return laps == 0 ? 0 : ((double) totalTime / laps) / 1_000_000.0;
    }

    public double totalMicros() {
        return totalTime / 1_000.0;
    }

    public double averageMicros() {
        return laps == 0 ? 0 : ((double) totalTime / laps) / 1_000.0;
    }

    public void reset() {
        startTime = 0;
        endTime = 0;
        totalTime = 0;
        laps = 0;
    }
}