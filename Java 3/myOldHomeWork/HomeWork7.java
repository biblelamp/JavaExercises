/**
 * Java. Level 3. Lesson 7. Homework
 * 1. Write method, which return int array with number from input array after last number 4
 * 2. Write method, which check that int array contains only numbers 1 and 4
 * 3. 
 */
import java.util.*;

class HomeWork7 {

    public static void main(String[] args) {
        HomeWork7 hw = new HomeWork7();
        int[] array = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(hw.extractNumbers(array)));
        int[] a = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        System.out.println(hw.checkArray(new int[]{1, 4, 4}));
        System.out.println(hw.checkArray(new int[]{2, 1, 4}));
    }

    int[] extractNumbers(int[] array) { // stage 1
        int idx = -1;
        for (int i = array.length - 1; i >= 0; i--)
            if (array[i] == 4) {
                idx = i + 1;
                break;
            }
        int[] result = new int[array.length - idx];
        System.arraycopy(array, idx, result, 0, array.length - idx);
        return result;
    }

    boolean checkArray(int[] array) { // stage 2
        for (int i : array)
            if (i != 1 && i != 4) return false;
        return true;
    }
}