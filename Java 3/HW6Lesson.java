/**
 * Java. Level 3. Lesson 6. Homework
 * 1. Write a method which returns part of integers array after the last == 4.
 *    If there are no numbers in the array 4, - throw RuntimeException
 * 2. Write an array validation method. If the array consists only of numbers 1 and 4
 *    then the method returns true if not - false
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
                new int[]{1, 2, 4, 4})));
        System.out.println(
            hw.validateArrayByOneAndFour(new int[]{1, 4, 4, 4, 1, 4}));
        System.out.println(
            hw.validateArrayByOneAndFour(new int[]{1, 4, 5}));
    }

    int[] getNumberAfrerLastFour(int[] array) throws RuntimeException {
        for (int i = array.length - 1; i >= 0; i--)
            if (array[i] == 4) {
                int idx = i + 1;
                int[] result = new int[array.length - idx];
                System.arraycopy(array, idx, result, 0, result.length);
                return result;
            }
        throw new RuntimeException("There are no numbers 4");
    }

    boolean validateArrayByOneAndFour(int[] array) {
        boolean containsOne = false;
        boolean containsFour = false;
        for (int i : array)
            if (i == 1)
                containsOne = true;
            else if (i == 4)
                containsFour = true;
            else
                return false;
        return containsOne && containsFour;
    }
}