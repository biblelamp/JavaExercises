package lesson9;

import java.util.Arrays;
import java.util.Random;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #9
 *
 * @author Sergey Iryupin
 * @version 05-Feb-24
 */
public class HomeWork9 {
    public static void main(String[] args) {
        int[] array = createRandomArray(6, 100);//{1, 2, 3, 4, 5, 6};

        // task #1
        task1(5);

        // task #2
        printArray(array);

        // task #3
        printArray(array, false);
        printArray(array, true);

        // task #4
        System.out.println(Arrays.toString(calculateArray(array)));
    }

    static void task1(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println("Task" + i);
        }
    }

    public static int[] createRandomArray(int len, int bound) {
        int[] a = new int[len];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(bound);
        }
        return a;
    }

    static void printArray(int[] a) {
        printArray(a, true);
    }

    static void printArray(int[] a, boolean reverseDirection) {
        if (reverseDirection) {
            for (int i = a.length - 1; i > -1; i--) {
                System.out.print(a[i] + " ");
            }
        } else {
            for (int i : a) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    static double[] calculateArray(int[] a) {
        int max = a[0];
        int min = a[0];
        double sum = 0;
        for (int i : a) {
            sum += i;
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        double[] result = {min, max, sum / a.length};
        return result;
    }
}
