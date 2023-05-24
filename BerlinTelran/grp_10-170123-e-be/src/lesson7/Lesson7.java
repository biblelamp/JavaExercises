package lesson7;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class Lesson7 {
    public static void main(String[] args) {
        SimpleBox<Integer> box1 = new SimpleBox<>(5);
        SimpleBox<Integer> box2 = new SimpleBox<>(8);
        SimpleBox<String> box3 = new SimpleBox<>("Box");

        int sum = box1.getValue() + box2.getValue();
        System.out.println(sum);

        // talking about homework
        Integer[] integers = {4, 3, 2, 1, 6, 7};
        swapElements(2, 4, integers);

        String[] strings = {"qwe", "dfa", "sdf", "fff"};
        swapElements(1, 3, strings);

        // using RubberArray
        RubberArray<Integer> ints = new RubberArray<>();
        ints.add(15);
        ints.add(9);
        System.out.println(ints);

        RubberArray<String> strs = new RubberArray<>();
        strs.add("Hello");
        strs.add("Java");
        System.out.println(strs);
    }

    static <T> void swapElements(int idxSrc, int idxTrg, T[] array) {
        System.out.println(array[idxSrc]);
        System.out.println(array[idxTrg]);
    }

    static <T> List<T> toList(T[] array) {
        return null;
    }
}
