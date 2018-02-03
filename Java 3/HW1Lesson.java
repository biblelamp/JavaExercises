/**
 * Java. Level 3. Lesson 1. Homework
 *
 * 1. Write a method which changes the places of the two elements of the array.
 *    (array can be of any reference type)
 * 2. Write method which converts an array in ArrayList
 * 3. "Big task" about fruit (apple, orange) and baskets (box)
 *
 * @author Sergey Iryupin
 * @version Feb 03, 2018
 */
import java.util.ArrayList;
import java.util.Arrays;

public class HW1Lesson {

    public static void main(String[] args) {
        HW1Lesson hw = new HW1Lesson();

        // stage 1, 2

        String[] strings = {"First", "Second", "Third", "Fourth", "Five"};
        System.out.println(hw.convertArrayToList(strings)); // stage 2
        hw.swapElements(0, 2, strings);
        System.out.println(Arrays.toString(strings));
        
        Integer[] numbers = {1, 2, 3, 4, 5, 6};
        System.out.println(hw.convertArrayToList(numbers)); // stage 2
        hw.swapElements(0, 2, numbers);
        System.out.println(Arrays.toString(numbers));

        // stage 3

        //hw.boxes();
    }

    private <T> void swapElements(int idx1, int idx2, T[] array) {
        T tmp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = tmp;
    }

    private <T> ArrayList<T> convertArrayToList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}