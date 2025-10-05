package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
}