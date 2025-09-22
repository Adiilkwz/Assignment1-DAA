import java.util.Arrays;

public class MergeSort {
    private static final int CUTOFF = 16;
    private final int[] buffer;
    private int maxDepth;

    public MergeSort(int n) {
        this.buffer = new int[n];
    }

    public void sort(int[] arr) {
        maxDepth = 0;
        sort(arr, 0, arr.length - 1, 1);
    }

    private void sort(int[] arr, int left, int right, int depth) {
        maxDepth = Math.max(maxDepth, depth);

        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right);
            return;
        }

        int mid = (left + right) >>> 1;
        sort(arr, left, mid, depth + 1);
        sort(arr, mid + 1, right, depth + 1);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        System.arraycopy(arr, left, buffer, left, mid - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (buffer[i] <= arr[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = buffer[i++];
        }
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    // Проверка корректности сортировки
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }
}