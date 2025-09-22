import java.util.*;

public class DeterministicSelect {

    public static int select(int[] arr, int k, SelectMetrics m) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k is out of range");
        }
        return selectRec(arr, 0, arr.length - 1, k, m);
    }

    private static int selectRec(int[] arr, int left, int right, int k, SelectMetrics m) {
        while (true) {
            if (left == right) return arr[left];

            m.enterRecursion();

            int n = right - left + 1;
            int numGroups = (n + 4) / 5;
            int[] medians = new int[numGroups];

            for (int i = 0; i < numGroups; i++) {
                int l = left + i * 5;
                int r = Math.min(l + 4, right);
                insertionSort(arr, l, r, m);
                int medianIdx = l + (r - l) / 2;
                medians[i] = arr[medianIdx];
            }

            int pivot;
            if (numGroups == 1) {
                pivot = medians[0];
            } else {
                pivot = select(medians, numGroups / 2, m);
            }

            int pivotIndex = partition(arr, left, right, pivot, m);

            m.exitRecursion();

            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    // Partition (in-place)
    private static int partition(int[] arr, int left, int right, int pivotVal, SelectMetrics m) {
        int pivotIndex = -1;
        for (int i = left; i <= right; i++) {
            m.incComparisons();
            if (arr[i] == pivotVal) {
                pivotIndex = i;
                break;
            }
        }
        if (pivotIndex == -1) pivotIndex = right;
        swap(arr, pivotIndex, right, m);

        int store = left;
        for (int i = left; i < right; i++) {
            m.incComparisons();
            if (arr[i] < pivotVal) {
                swap(arr, i, store, m);
                store++;
            }
        }
        swap(arr, store, right, m);
        return store;
    }

    private static void insertionSort(int[] arr, int l, int r, SelectMetrics m) {
        for (int i = l + 1; i <= r; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= l) {
                m.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                    m.incSwaps();
                } else break;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(int[] arr, int i, int j, SelectMetrics m) {
        if (i == j) return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        m.incSwaps();
    }
}
