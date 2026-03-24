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
        totalTime += durationMicros();
        laps++;
    }

    public long durationMicros() {
        return (endTime - startTime) / 1000;
    }

    public long totalMicros() {
        return totalTime;
    }

    public long averageMicros() {
        return laps == 0 ? 0 : totalTime / laps;
    }

    public void reset() {
        startTime = 0;
        endTime = 0;
        totalTime = 0;
        laps = 0;
    }
}