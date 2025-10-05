package cli;

import algorithms.ShellSort;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        System.out.println("Shell Sort Benchmark Runner");
        System.out.println("===========================");

        // Test different input sizes
        int[] sizes = {100, 1000, 10000, 100000};

        // Prepare CSV output
        try (FileWriter writer = new FileWriter("benchmark_results.csv")) {
            writer.write("size,sequence,comparisons,swaps,array_accesses,time_nano\n");

            for (int size : sizes) {
                System.out.println("\nTesting size: " + size);

                for (ShellSort.GapSequence sequence : ShellSort.GapSequence.values()) {
                    System.out.println("  Sequence: " + sequence);

                    // Generate test data
                    int[] array = generateRandomArray(size);

                    // Run benchmark
                    ShellSort sorter = new ShellSort();
                    sorter.sort(array, sequence);

                    // Get results
                    PerformanceTracker tracker = sorter.getTracker();

                    // Write to CSV
                    String line = String.format("%d,%s,%s\n",
                            size, sequence, tracker.toCSV());
                    writer.write(line);
                    writer.flush();

                    // Print summary
                    System.out.printf("    Comparisons: %,d\n", tracker.getComparisons());
                    System.out.printf("    Swaps: %,d\n", tracker.getSwaps());
                    System.out.printf("    Time: %,d ns\n", tracker.getTimeNano());

                    // Verify sorting is correct
                    if (!ShellSort.isSorted(array)) {
                        System.err.println("    ERROR: Array not sorted correctly!");
                    }
                }
            }

            System.out.println("\nBenchmark completed. Results saved to benchmark_results.csv");

        } catch (IOException e) {
            System.err.println("Error writing benchmark results: " + e.getMessage());
        }
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random(42); // Fixed seed for reproducible results
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 10);
        }
        return array;
    }
}