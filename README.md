# Shell Sort Implementation

An optimized and well-documented implementation of the **Shell Sort** algorithm — an improved version of Insertion Sort that sorts elements with decreasing gap intervals, resulting in significantly better performance on large datasets.

---

## Algorithm Overview

**Shell Sort** improves upon **Insertion Sort** by allowing exchanges of far-apart elements.
It starts with a large gap between elements, sorting sublists of elements spaced by that gap.
As the algorithm progresses, the gap is reduced until it becomes 1 — effectively performing a final Insertion Sort pass on a nearly sorted array.

This reduces the total number of shifts required and increases sorting efficiency.

---

## Complexity Analysis

| Case                 | Time Complexity         | Description                     |
| -------------------- | ----------------------- | ------------------------------- |
| **Best Case**        | O(n log n)              | When the array is nearly sorted |
| **Average Case**     | O(n^(3/2)) – O(n^(4/3)) | Depends on the gap sequence     |
| **Worst Case**       | O(n²)                   | For poorly chosen gap sequences |
| **Space Complexity** | O(1)                    | In-place sorting algorithm      |

### Gap Sequences Comparison

| Sequence Type            | Formula / Pattern   | Worst-Case Complexity |
| ------------------------ | ------------------- | --------------------- |
| **Shell's Original**     | n/2, n/4, n/8, ...  | O(n²)                 |
| **Knuth's Sequence**     | (3^k - 1) / 2       | O(n^(3/2))            |
| **Sedgewick's Sequence** | 4^k + 3·2^(k−1) + 1 | O(n^(4/3))            |

---

## Features Implemented

### Core Algorithm

* Three **gap sequences** supported: Shell, Knuth, and Sedgewick
* **Performance tracking** for comparisons, swaps, array accesses, and time
* **Early termination** optimization when no swaps occur during a pass

### Testing & Validation

* **Comprehensive JUnit 5 test suite**
* Coverage for:

  * Empty arrays
  * Single-element arrays
  * Sorted arrays
  * Reverse-sorted arrays
* Includes **performance validation tests**

### Benchmarking Tools

* Command-line **Benchmark Runner**
* Configurable input sizes and distributions (random, sorted, reverse-sorted)
* **CSV output** for data analysis and graph plotting

---

## Project Structure

```
assignment2-shellsort/
├── src/
│   ├── main/java/
│   │   ├── algorithms/ShellSort.java        # Core sorting logic
│   │   ├── metrics/PerformanceTracker.java  # Metrics collection
│   │   └── cli/BenchmarkRunner.java         # CLI benchmark executor
│   └── test/java/
│       └── algorithms/ShellSortTest.java        # Unit tests
├── docs/performance-plots/                          # Benchmark visualizations
├── pom.xml                                          # Maven configuration
└── README.md                                        # Project documentation
```

---

## Usage

### Running Tests

```bash
mvn test
```

### Running Benchmarks

```bash
mvn compile exec:java -Dexec.mainClass="cli.BenchmarkRunner"
```

---

Example Code

```
ShellSort sorter = new ShellSort();
int[] array = {64, 34, 25, 12, 22, 11, 90};
sorter.sort(array, ShellSort.GapSequence.SEDGEWICK);

// Retrieve performance metrics
PerformanceTracker tracker = sorter.getTracker();
System.out.println("Comparisons: " + tracker.getComparisons());
System.out.println("Execution Time: " + tracker.getTimeNano() + " ns");
```

---

## Benchmark Output

After running the benchmark, a CSV file is generated with the following columns:

| Column           | Description                                 |
| ---------------- | ------------------------------------------- |
| `size`           | Input array size                            |
| `sequence`       | Gap sequence type (SHELL, KNUTH, SEDGEWICK) |
| `comparisons`    | Total comparisons                           |
| `swaps`          | Total swaps                                 |
| `array_accesses` | Array read/write operations                 |
| `time_nano`      | Execution time in nanoseconds               |

---

## Performance Metrics Tracked

* **Comparisons** – Number of element comparisons
* **Swaps** – Number of assignments or exchanges
* **Array Accesses** – Total reads/writes to array elements
* **Execution Time** – Nanosecond-precision timing

---

## Optimization Highlights

* Early termination when no swaps occur in a pass
* Precomputed gap sequences for better efficiency
* Comparative evaluation across multiple gap strategies

---

## Dependencies

| Dependency                | Purpose                          |
| ------------------------- | -------------------------------- |
| **JUnit 5**               | Unit testing                     |
| **Maven Surefire Plugin** | Test execution                   |
| **Java 23**               | Compiler and runtime environment |

---

##  Author (Akhanov Yerdaulet)

Developed as part of **DAA (Design and Analysis of Algorithms)** – *Assignment 2.*

---
