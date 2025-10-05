package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class ShellSortTest {

    @Test
    void testEmptyArray() {
        ShellSort sorter = new ShellSort();
        int[] array = {};
        sorter.sort(array, ShellSort.GapSequence.SHELL);
        assertTrue(ShellSort.isSorted(array));
    }

    @Test
    void testSingleElement() {
        ShellSort sorter = new ShellSort();
        int[] array = {5};
        sorter.sort(array, ShellSort.GapSequence.SHELL);
        assertTrue(ShellSort.isSorted(array));
        assertEquals(5, array[0]);
    }

    @Test
    void testAlreadySorted() {
        ShellSort sorter = new ShellSort();
        int[] array = {1, 2, 3, 4, 5};
        sorter.sort(array, ShellSort.GapSequence.SHELL);
        assertTrue(ShellSort.isSorted(array));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testReverseSorted() {
        ShellSort sorter = new ShellSort();
        int[] array = {5, 4, 3, 2, 1};
        sorter.sort(array, ShellSort.GapSequence.SHELL);
        assertTrue(ShellSort.isSorted(array));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testArrayWithDuplicates() {
        ShellSort sorter = new ShellSort();
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5};
        sorter.sort(array, ShellSort.GapSequence.SHELL);
        assertTrue(ShellSort.isSorted(array));
    }

    @Test
    void testRandomArray() {
        ShellSort sorter = new ShellSort();
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        sorter.sort(array, ShellSort.GapSequence.SHELL);
        assertTrue(ShellSort.isSorted(array));
    }

    @Test
    void testNullArray() {
        ShellSort sorter = new ShellSort();
        assertThrows(IllegalArgumentException.class,
                () -> sorter.sort(null, ShellSort.GapSequence.SHELL));
    }

    @Test
    void testAllGapSequences() {
        int[] array = {64, 34, 25, 12, 22, 11, 90, 1, 77, 45};

        for (ShellSort.GapSequence seq : ShellSort.GapSequence.values()) {
            ShellSort sorter = new ShellSort();
            int[] copy = array.clone();
            sorter.sort(copy, seq);
            assertTrue(ShellSort.isSorted(copy),
                    "Array should be sorted with " + seq + " sequence");
        }
    }

    @Test
    void testPerformanceTracking() {
        ShellSort sorter = new ShellSort();
        int[] array = {5, 2, 8, 1, 9};
        sorter.sort(array, ShellSort.GapSequence.SHELL);

        var tracker = sorter.getTracker();
        assertTrue(tracker.getComparisons() > 0);
        assertTrue(tracker.getSwaps() > 0);
        assertTrue(tracker.getArrayAccesses() > 0);
        assertTrue(tracker.getTimeNano() > 0);
    }

    @Test
    void testOptimizedSort() {
        ShellSort sorter = new ShellSort();
        int[] array = {1, 2, 3, 5, 4, 6, 7, 9, 8}; // Nearly sorted

        // Test optimized version
        sorter.sortOptimized(array, ShellSort.GapSequence.SHELL);
        assertTrue(ShellSort.isSorted(array));

        var tracker = sorter.getTracker();
        assertTrue(tracker.getComparisons() > 0);
        assertTrue(tracker.getSwaps() > 0);
    }

    @Test
    void testOptimizedSortPerformance() {
        ShellSort sorter = new ShellSort();
        int[] array1 = generateLargeArray(1000);
        int[] array2 = array1.clone();

        sorter.sort(array1, ShellSort.GapSequence.SHELL);
        long standardTime = sorter.getTracker().getTimeNano();


        sorter.sortOptimized(array2, ShellSort.GapSequence.SHELL);
        long optimizedTime = sorter.getTracker().getTimeNano();

        assertTrue(ShellSort.isSorted(array1));
        assertTrue(ShellSort.isSorted(array2));

        System.out.printf("Standard: %d ns, Optimized: %d ns%n", standardTime, optimizedTime);
    }

    @Test
    void testNearlySortedArray() {
        ShellSort sorter = new ShellSort();
        int[] array = {1, 2, 3, 5, 4, 6, 7, 9, 8}; // Nearly sorted
        sorter.sort(array, ShellSort.GapSequence.SHELL);
        assertTrue(ShellSort.isSorted(array));
    }

    private int[] generateLargeArray(int size) {
        Random random = new Random(42);
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 10);
        }
        return array;
    }
}