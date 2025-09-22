import java.util.*;

public class ClosestPairOfPoints {

    // Entry point: O(n log n) closest pair
    public static double closestPair(Point[] points) {
        // Copy points and sort by x
        Point[] pointsSortedByX = points.clone();
        Arrays.sort(pointsSortedByX, Comparator.comparingInt(p -> p.x));

        // Auxiliary array for merges
        Point[] aux = new Point[points.length];

        return closestPairRec(pointsSortedByX, aux, 0, points.length - 1);
    }

    // Recursive divide and conquer
    private static double closestPairRec(Point[] points, Point[] aux, int left, int right) {
        // Base case: brute force if small set
        if (right - left <= 3) {
            return bruteForce(points, left, right);
        }

        int mid = (left + right) / 2;
        int midX = points[mid].x;

        double dLeft = closestPairRec(points, aux, left, mid);
        double dRight = closestPairRec(points, aux, mid + 1, right);
        double d = Math.min(dLeft, dRight);

        // Merge step: sort by y
        mergeByY(points, aux, left, mid, right);

        // Build strip of points within distance d of midX
        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midX) < d) {
                strip.add(points[i]);
            }
        }

        // Check strip neighbors (classic 7–8 check)
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                d = Math.min(d, distance(strip.get(i), strip.get(j)));
            }
        }

        return d;
    }

    // Merge two halves sorted by y
    private static void mergeByY(Point[] points, Point[] aux, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (points[i].y <= points[j].y) {
                aux[k++] = points[i++];
            } else {
                aux[k++] = points[j++];
            }
        }
        while (i <= mid) aux[k++] = points[i++];
        while (j <= right) aux[k++] = points[j++];
        for (i = left; i <= right; i++) {
            points[i] = aux[i];
        }
    }

    // Brute force O(n^2), used for small n
    public static double bruteForce(Point[] points, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                min = Math.min(min, distance(points[i], points[j]));
            }
        }
        return min;
    }

    // Overloaded brute force for full array
    public static double bruteForce(Point[] points) {
        return bruteForce(points, 0, points.length - 1);
    }

    // Euclidean distance
    private static double distance(Point p1, Point p2) {
        long dx = (long)p1.x - p2.x;
        long dy = (long)p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
