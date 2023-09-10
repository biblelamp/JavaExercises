package homework6;

import java.util.Arrays;
import java.util.List;

/**
 * Java professional. Homework #6
 *
 * @author Sergey
 * @version 17.11-20.11
 */

public class FruitBoxDemo {
    public static void main(String[] args) {

        // tasks 1, 2
        String[] strings = {"First", "Second", "Third", "Fourth", "Five"};
        System.out.println(convertArrayToList(strings)); // task 2
        swapElements(0, 2, strings);
        System.out.println(Arrays.toString(strings));

        Integer[] numbers = {1, 2, 3, 4, 5, 6};
        System.out.println(convertArrayToList(numbers)); // task 2
        swapElements(0, 2, numbers);
        System.out.println(Arrays.toString(numbers));

        // task 3

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        for (int i = 0; i < 5; i++) {
            appleBox.add(new Apple());
            orangeBox.add(new Orange());
        }
        System.out.println(appleBox.getFruits());
        System.out.println(orangeBox.getFruits());

        Box<Apple> appleBoxTwo = new Box<>();
        appleBoxTwo.addAll(Arrays.asList(new Apple[]{new Apple(), new Apple()}));
        System.out.println(appleBoxTwo.getFruits());

        appleBox.moveAllToNextBox(appleBoxTwo);
        System.out.println(appleBox.getFruits());
        System.out.println(appleBoxTwo.getFruits());
    }

    private static <T> void swapElements(int idx1, int idx2, T[] array) {
        T tmp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = tmp;
    }

    private static <T> List<T> convertArrayToList(T[] array) {
        return Arrays.asList(array);
    }
}
