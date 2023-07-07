package lesson4;

/**
 * Algorithms. Lesson #4. Merge sorting
 *
 * @author Sergey Iryupin
 * @version 07 Jul 2023
 */

import java.util.Arrays;

public class Lesson4 {
    public static void main(String[] args) {
        int[] array = {4, 1, 7, 5, 3, 2, 6};
        int[] sortArray = mergeSort(array);
        System.out.println(Arrays.toString(sortArray));
    }

    static int[] mergeSort(int[] src) {
        if (src.length <= 1) return src;
        int[] left = Arrays.copyOfRange(src, 0, src.length / 2);
        int[] right = Arrays.copyOfRange(src, left.length, src.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        int resIn = 0, leftIn = 0, rightIn = 0;

        while (leftIn < left.length && rightIn < right.length) {
            if (left[leftIn] < right[rightIn]) {
                result[resIn++] = left[leftIn++];
            } else {
                result[resIn++] = right[rightIn++];
            }
        }

        while (resIn < result.length) {
            if (leftIn != left.length) {
                result[resIn++] = left[leftIn++];
            } else {
                result[resIn++] = right[rightIn++];
            }
        }
        return result;
    }
}
