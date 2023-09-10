package lesson7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Lesson7 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(4, 6, 2, 9, 1));
        for (Integer item : numbers) {
            System.out.print(item + " ");
        }
        System.out.println();

        int[] array = {5, 7, 3, 2, 6};
        for (int item : array) {
            System.out.print(item + " ");
        }
        int i1 = 3;
        int i2 = 1;
        System.out.println();
        System.out.println(array[i1 = i2]);
        System.out.println(i1 + ", " + i2);

        RubberArray<Integer> ra = new RubberArray<>();
        ra.addAll(9, 3, 5, 7, 0);
        Iterator<Integer> iterator = ra.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
