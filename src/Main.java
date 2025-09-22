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
            //quicksort testing
            int n = 20; // размер массива
            Random rnd = new Random();
            int[] arr = rnd.ints(n, -50, 50).toArray();

            System.out.println("Исходный массив: " + Arrays.toString(arr));

            SortMetrics m = new SortMetrics();
            QuickSort.sort(arr, m);

            System.out.println("Отсортированный массив: " + Arrays.toString(arr));
            System.out.println("Сравнений: " + m.getComparisons());
            System.out.println("Перестановок: " + m.getSwaps());
            System.out.println("Макс. глубина рекурсии: " + m.getMaxDepth());

        // Small test for correctness closest pair of points
        int smallN = 1000;
        Point[] smallPoints = generateRandomPoints(smallN, 10000);

        double fastDist = ClosestPairOfPoints.closestPair(smallPoints);
        double bruteDist = ClosestPairOfPoints.bruteForce(smallPoints);

        System.out.println("Small test:");
        System.out.println("Fast algorithm result: " + fastDist);
        System.out.println("Brute force result: " + bruteDist);
        System.out.println("Match? " + (Math.abs(fastDist - bruteDist) < 1e-9));

        // Large test for performance closest pair of points
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

