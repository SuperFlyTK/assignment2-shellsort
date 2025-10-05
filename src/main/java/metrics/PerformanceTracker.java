package metrics;

public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long arrayAccesses;
    private long startTime;
    private long endTime;

    public PerformanceTracker() {
        reset();
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        startTime = 0;
        endTime = 0;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementSwaps() {
        swaps++;
    }

    public void incrementArrayAccesses(int count) {
        arrayAccesses += count;
    }

    // Getters
    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getTimeNano() { return endTime - startTime; }

    public String toCSV() {
        return String.format("%d,%d,%d,%d",
                getComparisons(), getSwaps(), getArrayAccesses(), getTimeNano());
    }

    public static String getCSVHeader() {
        return "comparisons,swaps,array_accesses,time_nano";
    }
}