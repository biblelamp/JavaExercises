package lesson32.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AIT-TR, cohort 42.1, Java Basic, Homework #32
 *
 * @author Sergey Iryupin
 * @version 3-Apr-24
 */
public class HomeWork32 {
    public static void main(String[] args) {
        // task #1
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7};
        String[] strArray = {"one", "two", "three", "four", "five", "six", "seven"};
        swapElements(1, 5, intArray);
        System.out.println(Arrays.toString(intArray));
        swapElements(1, 5, strArray);
        System.out.println(Arrays.toString(strArray));

        // task #2
        System.out.println(arrayToList(intArray));
        System.out.println(arrayToList(strArray));
    }

    static <T> void swapElements(int idxOne, int idxTwo, T[] array) {
        T tmp = array[idxOne];
        array[idxOne] = array[idxTwo];
        array[idxTwo] = tmp;
    }

    static <T> List<T> arrayToList(T[] array) {
        List<T> result = new ArrayList<>();
        for (T item : array) {
            result.add(item);
        }
        return result;
    }
}
