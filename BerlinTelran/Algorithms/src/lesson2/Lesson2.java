package lesson2;

import java.util.Arrays;

public class Lesson2 {
    public static void main(String[] args) {
        int[] array = {1, 4, 5, 6, 9};

        // сложность алгоритма O(1)
        int a = array[1];

        // сложность алгоритма O(N)
        int sum = 0;
        for (int item : array) {
            sum += item;
        }
        System.out.println(Arrays.toString(array));
        System.out.println(sum);

        // сложность алгоритма O(log N)
        int idx = Arrays.binarySearch(array, 4);

        // сложность алгоритма O(N log N)
        // сортировка слиянием и быстрая сортировка

        // сложность алгоритма O(N ^ 2)
        // пузырьковая сортировка

        // сложность алгоритма O(N!)
        // задача коммивояжёра
    }
}
