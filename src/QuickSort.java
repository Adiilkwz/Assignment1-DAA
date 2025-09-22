import java.util.Random;

public class QuickSort {
    private static final Random rnd = new Random();

    public static void sort(int[] a, SortMetrics m) {
        if (a == null || a.length <= 1) return;
        quicksort(a, 0, a.length - 1, m);
    }

    private static void quicksort(int[] a, int lo, int hi, SortMetrics m) {
        while (lo < hi) {
            m.enterRecursion();
            int p = partition(a, lo, hi, m);
            m.exitRecursion();

            int leftSize = p - lo;
            int rightSize = hi - p;

            if (leftSize < rightSize) {
                quicksort(a, lo, p - 1, m);
                lo = p + 1;
            } else {
                quicksort(a, p + 1, hi, m);
                hi = p - 1;
            }
        }
    }

    private static int partition(int[] a, int lo, int hi, SortMetrics m) {
        int pivotIndex = lo + rnd.nextInt(hi - lo + 1);
        int pivot = a[pivotIndex];
        swap(a, pivotIndex, hi, m);

        int store = lo;
        for (int i = lo; i < hi; i++) {
            m.incComparisons();
            if (a[i] < pivot) {
                swap(a, i, store, m);
                store++;
            }
        }
        swap(a, store, hi, m);
        return store;
    }

    private static void swap(int[] a, int i, int j, SortMetrics m) {
        if (i == j) return;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        m.incSwaps();
    }
}
