package lesson6;

import java.util.ArrayList;
import java.util.List;

/*
 * Домашнее задание: реализовать:
 * addAll(int... values)
 * add(int index, int value)
 * contains(int value)
 * indexOf(int value)
 * isEmpty()
 * clear()
 * size()
 */

public class Lesson6 {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        System.out.println(intList);
        intList.add(8);
        intList.add(17);
        intList.add(5);
        System.out.println(intList);
        System.out.println(intList.get(1));
        intList.remove(3);
        System.out.println(intList);
        System.out.println("----- RubberArray");

        RubberArray rb = new RubberArray();
        System.out.println(rb);
        rb.add(8);
        rb.add(17);
        rb.add(5);
        rb.add(23);
        System.out.println(rb);
        System.out.println(rb.get(1));
        rb.remove(1);
        System.out.println(rb);
        rb.remove(0);
        System.out.println(rb);
        rb.remove(1);
        System.out.println(rb);
    }
}
