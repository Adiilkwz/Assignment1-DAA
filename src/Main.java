

//quicksort
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
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
    }
}
