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
        totalTime += durationMillis();
        laps++;
    }

    public long durationMillis() {
        return (endTime - startTime) / 1_000_000;
    }

    public long totalMillis() {
        return totalTime;
    }

    public long averageMillis() {
        return laps == 0 ? 0 : totalTime / laps;
    }

    public void reset() {
        startTime = 0;
        endTime = 0;
        totalTime = 0;
        laps = 0;
    }
}