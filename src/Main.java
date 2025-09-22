import java.util.*;

public class Main {

    // Generate random points
    private static Point[] generateRandomPoints(int n, int maxCoord) {
        Random rand = new Random();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(rand.nextInt(maxCoord), rand.nextInt(maxCoord));
        }
        return points;
    }

    public static void main(String[] args) {
        //1. QuickSort
        int n = 20; // размер массива
        Random rnd = new Random();
        int[] arr = rnd.ints(n, -50, 50).toArray();

        System.out.println("QuickSort testing");
        System.out.println("Исходный массив: " + Arrays.toString(arr));

        SortMetrics m = new SortMetrics();
        QuickSort.sort(arr, m);

        System.out.println("Отсортированный массив: " + Arrays.toString(arr));
        System.out.println("Сравнений: " + m.getComparisons());
        System.out.println("Перестановок: " + m.getSwaps());
        System.out.println("Макс. глубина рекурсии: " + m.getMaxDepth());
        System.out.println();

        //2. MergeSort
        Random rand = new Random();
        for (int s : new int[]{10, 100, 1000, 10000}) {
            int[] array = new int[s];
            for (int i = 0; i < s ; i++) array[i] = rand.nextInt(100000);

            MergeSort ms = new MergeSort(s);
            ms.sort(array);

            System.out.println("MergeSort testing");
            System.out.println("Random s=" + s +
                    " sorted=" + MergeSort.isSorted(array) +
                    " maxDepth=" + ms.getMaxDepth());
        }

        int s = 1000;
        int[] adversarial = new int[s];
        for (int i = 0; i < s; i++) adversarial[i] = s - i;

        MergeSort ms2 = new MergeSort(s);
        ms2.sort(adversarial);

        System.out.println("Adversarial s=" + s +
                " sorted=" + MergeSort.isSorted(adversarial) +
                " maxDepth=" + ms2.getMaxDepth());

        int[] sorted = new int[s];
        for (int i = 0; i < s; i++) sorted[i] = i;

        MergeSort ms3 = new MergeSort(s);
        ms3.sort(sorted);

        System.out.println("Sorted input s=" + s +
                " sorted=" + MergeSort.isSorted(sorted) +
                " maxDepth=" + ms3.getMaxDepth());

        //3. Deterministic Select (Median-of-Medians) ---
        System.out.println("\nDeterministic Select testing");

        int[] arr3 = rnd.ints(25, -100, 100).toArray();
        System.out.println("Original array: " + Arrays.toString(arr3));

        SelectMetrics sm = new SelectMetrics();

        int k = arr3.length / 2;

        int median = DeterministicSelect.select(Arrays.copyOf(arr3, arr3.length), k, sm);
        int[] copy = Arrays.copyOf(arr3, arr3.length);
        Arrays.sort(copy);
        int expected = copy[k];

        System.out.println("Expected (via sort): " + expected);
        System.out.println("Found (DeterministicSelect): " + median);
        System.out.println("Match? " + (expected == median));

        System.out.println("Comparisons: " + sm.getComparisons());
        System.out.println("Swaps: " + sm.getSwaps());
        System.out.println("Max recursion depth: " + sm.getMaxDepth());


        // 4. Closest pair of points
        int smallN = 1000;
        Point[] smallPoints = generateRandomPoints(smallN, 10000);

        double fastDist = ClosestPairOfPoints.closestPair(smallPoints);
        double bruteDist = ClosestPairOfPoints.bruteForce(smallPoints);

        System.out.println();
        System.out.println("Closest pair of points testing");
        System.out.println("Small test:");
        System.out.println("Fast algorithm result: " + fastDist);
        System.out.println("Brute force result: " + bruteDist);
        System.out.println("Match? " + (Math.abs(fastDist - bruteDist) < 1e-9));

        int bigN = 100000;
        Point[] bigPoints = generateRandomPoints(bigN, 1000000);

        long start = System.currentTimeMillis();
        double distLarge = ClosestPairOfPoints.closestPair(bigPoints);
        long end = System.currentTimeMillis();

        System.out.println("\nLarge test:");
        System.out.println("Fast algorithm result: " + distLarge);
        System.out.println("Time taken: " + (end - start) + " ms");

    }
}

