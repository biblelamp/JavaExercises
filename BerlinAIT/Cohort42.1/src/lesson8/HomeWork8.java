package lesson8;

import java.util.Random;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #8
 *
 * @author Sergey Iryupin
 * @version 02-Feb-24
 */
public class HomeWork8 {
    public static void main(String[] args) {
        // task #1
        for (int i = 1; i < 11; i++) {
            System.out.println("Task" + i);
        }

        // task #2
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        for (int i : a) {
            if (i % 5 == 0) {
                System.out.println(i);
            }
        }

        // task #3
        String[] s = {"One", "Two", "Three", "Four"};
        String strMaxLength = "";
        for (String str : s) {
            if (str.length() > strMaxLength.length()) {
                strMaxLength = str;
            }
        }
        for (char chr : strMaxLength.toCharArray()) {
            System.out.println(chr);
        }

        // task #4
        Random random = new Random();
        int[] n = new int[100];
        int sum = 0;
        // fill array with random int numbers
        for (int i = 0; i < n.length; i++) {
            n[i] = random.nextInt(100);
        }
        int max = n[0];
        int min = n[0];
        // execute array
        for (int i : n) {
            sum += i;
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        System.out.println("Max = " + max + ", min = " + min + ", average = " + (sum / (double) n.length) + " " + (sum / n.length));
    }
}
