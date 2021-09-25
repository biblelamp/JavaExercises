/**
 * Java 1. Homework 3
 * 
 * @author Sergey Iryupin
 * @version 16.9.2021
 */
import java.util.Arrays;
import java.util.Random;

class Lesson3HomeWork {

    static Random random = new Random();

    public static void main(String[] args) {

        // 1. Invert array values
        int[] arrOne = createAndFllArray(10, true, 2);
        System.out.println(Arrays.toString(arrOne));
        for (int i = 0; i < arrOne.length; i++) {
            arrOne[i] = 1 - arrOne[i];
        }
        System.out.println(Arrays.toString(arrOne));

        // 2. Fill the array with values from 1 to N
        int[] arrTwo = createAndFllArray(100, false, 1);
        System.out.println(Arrays.toString(arrTwo));

        // 3. Loop over array elements with condition
        int[] arrThree = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arrThree.length; i++) {
            if (arrThree[i] < 6) {
                arrThree[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(arrThree));

        // 4. Fill in the diagonals of the matrix
        int[][] matrix = new int[5][5];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][i] = 1;
            matrix[i][matrix.length - i - 1] = 1;
        }
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }

        // 5. Create and fill an array with the same values
        int[] arrFive = createAndFllArray(15, 5);
        System.out.println(Arrays.toString(arrFive));

        // 6. Find the min and max value in an array
        int[] arrSix = createAndFllArray(25, true, 25);
        System.out.println(Arrays.toString(arrSix));
        int[] minMax = findMinMax(arrSix);
        System.out.println(Arrays.toString(minMax));

        // 7. Finding balance in an array
        int[][] test = {{1, 1, 1, 2, 1}, {2, 1, 1, 2, 1}, {10, 10}};
        for (int i = 0; i < test.length; i++) {
            System.out.println(
                Arrays.toString(test[i]) + " " + checkBalance(test[i]));
        }

        // 8. Cyclic shift of an array
        System.out.println(Arrays.toString(shiftArray(new int[]{1, 2, 3, 4, 5}, 2)));
        System.out.println(Arrays.toString(shiftArray(new int[]{1, 2, 3, 4, 5}, -3)));
    }

    static int[] createAndFllArray(int len, int initialValue) {
        int[] arr = new int[len];
        Arrays.fill(arr, initialValue);
        return arr;
    }

    static int[] createAndFllArray(int length, boolean rnd, int value) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            if (rnd) {
                arr[i] = random.nextInt(value);
            } else {
                arr[i] = i + value;
            }
        }
        return arr;
    }

    static int[] findMinMax(int[] array) {
        int[] minMax = new int[2];
        minMax[0] = minMax[1] = array[0];
        for (int i = 1; i < array.length; i++) {
            if (minMax[0] > array[i]) {
                minMax[0] = array[i];
            }
            if (minMax[1] < array[i]) {
                minMax[1] = array[i];
            }
        }
        return minMax;
    }

    static boolean checkBalance(int[] array) {
        int sumArray = 0, sumLeft = 0;
        for (int i : array) {
            sumArray += i;
        }
        for (int i = 0; i < array.length - 1; i++) {
            sumLeft += array[i];
            if (sumLeft*2 == sumArray) {
                return true;
            }
        }
        return false;
    }

    static int[] shiftArray(int[] array, int shift) {
        shift = shift % array.length; // optimization of the number of shifts
        if (shift < 0) {
            shift += array.length; // by Gusar Mikhail
        }
        for (int cnt = 0; cnt < shift; cnt++) {
            int tmp = array[array.length - 1];
            for (int i = array.length - 1; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = tmp;
        }
        return array;
    }

}
