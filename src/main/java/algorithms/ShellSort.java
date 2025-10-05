package algorithms;

import metrics.PerformanceTracker;

public class ShellSort {
    public enum GapSequence {
        SHELL, KNUTH, SEDGEWICK
    }

    private PerformanceTracker tracker;

    public ShellSort() {
        this.tracker = new PerformanceTracker();
    }

    public PerformanceTracker getTracker() {
        return tracker;
    }

    public void sort(int[] array, GapSequence sequence) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (array.length <= 1) {
            return;
        }

        tracker.reset();
        tracker.startTimer();

        int n = array.length;
        int[] gaps = generateGapSequence(n, sequence);

        // Perform Shell sort with the selected gap sequence
        for (int gap : gaps) {
            if (gap == 0) continue;

            for (int i = gap; i < n; i++) {
                int temp = array[i];
                tracker.incrementArrayAccesses(1);

                int j;
                for (j = i; j >= gap; j -= gap) {
                    tracker.incrementComparisons();
                    tracker.incrementArrayAccesses(1);
                    if (array[j - gap] <= temp) {
                        break;
                    }

                    array[j] = array[j - gap];
                    tracker.incrementArrayAccesses(2);
                    tracker.incrementSwaps();
                }

                array[j] = temp;
                tracker.incrementArrayAccesses(1);
            }
        }

        tracker.stopTimer();
    }

    public void sortOptimized(int[] array, GapSequence sequence) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (array.length <= 1) {
            return;
        }

        tracker.reset();
        tracker.startTimer();

        int n = array.length;
        int[] gaps = generateGapSequence(n, sequence);

        for (int gap : gaps) {
            if (gap == 0) continue;

            boolean swapped;
            do {
                swapped = false;
                for (int i = gap; i < n; i++) {
                    int temp = array[i];
                    tracker.incrementArrayAccesses(1);

                    int j = i;
                    while (j >= gap) {
                        tracker.incrementComparisons();
                        tracker.incrementArrayAccesses(1);
                        if (array[j - gap] > temp) {
                            array[j] = array[j - gap];
                            tracker.incrementArrayAccesses(2);
                            tracker.incrementSwaps();
                            swapped = true;
                            j -= gap;
                        } else {
                            break;
                        }
                    }
                    array[j] = temp;
                    tracker.incrementArrayAccesses(1);
                }
            } while (swapped && gap > 1); // Early termination if no swaps
        }

        tracker.stopTimer();
    }

    private int[] generateGapSequence(int n, GapSequence sequence) {
        switch (sequence) {
            case SHELL:
                return generateShellSequence(n);
            case KNUTH:
                return generateKnuthSequence(n);
            case SEDGEWICK:
                return generateSedgewickSequence(n);
            default:
                return generateShellSequence(n);
        }
    }

    private int[] generateShellSequence(int n) {
        // Shell's original sequence
        int gapCount = (int) (Math.log(n) / Math.log(2));
        int[] gaps = new int[gapCount];
        int gap = n / 2;

        for (int i = 0; i < gapCount; i++) {
            gaps[i] = gap;
            gap /= 2;
        }
        return gaps;
    }

    private int[] generateKnuthSequence(int n) {
        // Knuth's sequence
        java.util.ArrayList<Integer> gapList = new java.util.ArrayList<>();
        int k = 1;
        int gap;

        do {
            gap = (int) ((Math.pow(3, k) - 1) / 2);
            if (gap <= n / 3) {
                gapList.add(gap);
            }
            k++;
        } while (gap <= n / 3);

        // Reverse to start with largest gap
        int[] gaps = new int[gapList.size()];
        for (int i = 0; i < gapList.size(); i++) {
            gaps[gapList.size() - 1 - i] = gapList.get(i);
        }
        return gaps;
    }

    private int[] generateSedgewickSequence(int n) {
        // Sedgewick's sequence
        java.util.ArrayList<Integer> gapList = new java.util.ArrayList<>();
        int k = 0;

        while (true) {
            int gap;
            if (k % 2 == 0) {
                gap = (int) (9 * (Math.pow(2, k) - Math.pow(2, k/2)) + 1);
            } else {
                gap = (int) (8 * Math.pow(2, k) - 6 * Math.pow(2, (k+1)/2) + 1);
            }

            if (gap > n) break;
            gapList.add(gap);
            k++;
        }

        // Reverse to start with largest gap
        int[] gaps = new int[gapList.size()];
        for (int i = 0; i < gapList.size(); i++) {
            gaps[gapList.size() - 1 - i] = gapList.get(i);
        }
        return gaps;
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}