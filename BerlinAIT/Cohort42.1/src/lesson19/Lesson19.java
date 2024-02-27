package lesson19;

import java.util.ArrayList;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #19
 *
 * @version 26-Feb-24
 */
public class Lesson19 {
    public static void main(String[] args) {
        RubberArray ra = new RubberArray();
        ra.add(8);
        ra.add(12);
        ra.add(-1);
        ra.add(3);
        ra.add(25);
        System.out.println(ra);
        System.out.println(ra.get(2));
        ra.remove(2);
        System.out.println(ra);
    }
}
