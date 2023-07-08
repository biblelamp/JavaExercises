package homework3;

/**
 * Algorithms. Homework #3
 *
 * @author Sergey Iryupin
 * @version 07 Jul 2023
 */

import java.util.Arrays;

public class Homework3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(fibonacciRecursion(10)));
        //System.out.println(fibonachi(4));
    }

    static int fibonachi(int n){
        // base case
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
//        if (n < 3) {
//            return n - 1;
//        }
        return fibonachi(n - 1) + fibonachi(n - 2); // recursive case
    }

    private static int[] fibonacciRecursion(int n) {
        if (n == 2) { // base case
            return new int[]{0, 1};
        }
        if (n == 1) {
            return new int[]{0};
        }
        int[] result = fibonacciRecursion(n - 1);      // recursive case
        System.out.println(Arrays.toString(result));
        result = Arrays.copyOf(result, n);             // increase the array size
        result[n - 1] = result[n - 2] + result[n - 3]; // sum of 2 previous elements
        return result;
    }
}
