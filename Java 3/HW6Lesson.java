/**
 * Java. Level 3. Lesson 6. Homework
 * 1. Write a method which returns part of integers array after the last == 4.
 *    If there are no numbers in the array 4, - throw RuntimeException
 * 2. Write an array validation method.
 *
 * @author Sergey Iryupin
 * @version Mar 06, 2018
 * @link https://github.com/biblelamp
 */
import java.util.Arrays;

public class HW6Lesson {

    public static void main(String[] args) throws RuntimeException {
        HW6Lesson hw = new HW6Lesson();
        System.out.println(Arrays.toString(hw.getNumberAfrerLastFour(
                new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7})));
        System.out.println(Arrays.toString(hw.getNumberAfrerLastFour(
                new int[]{1, 2, 4, 4, 2, 3, 1, 7})));
        System.out.println(
            hw.validateArrayByOneAndFour(new int[]{1, 4, 4, 4, 1, 4}));
        System.out.println(
            hw.validateArrayByOneAndFour(new int[]{1, 4, 4, 5, 1, 4}));
    }

    int[] getNumberAfrerLastFour(int[] array) throws RuntimeException {
        int idx = -1;
        for (int i = 0; i < array.length; i++)
            if (array[i] == 4)
                idx = i + 1;
        if (idx == -1)
            throw new RuntimeException("There are no numbers 4");
        int[] result = new int[array.length - idx];
        System.arraycopy(array, idx, result, 0, result.length);
        return result;
    }

    boolean validateArrayByOneAndFour(int[] array) {
        for (int i : array)
            if (i != 1 && i != 4)
                return false;
        return true;
    }
}