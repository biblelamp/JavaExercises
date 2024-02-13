package lesson13;

import lesson12.HomeWork12;
import lesson9.HomeWork9;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #13
 *
 * @version 12-Feb-24
 */
public class Lesson13 {
    public static void main(String[] args) {
        int[] array = HomeWork9.createRandomArray(1_000_000, 100);

        long startTime = System.currentTimeMillis();
        System.out.println(HomeWork12.linearSearch(array, 45));
        System.out.println((System.currentTimeMillis() - startTime) + " ms");

        HomeWork12.selectionSort(array);

        startTime = System.currentTimeMillis();
        System.out.println(HomeWork12.binarySearch(array, 45));
        System.out.println((System.currentTimeMillis() - startTime) + " ms");
    }
}
