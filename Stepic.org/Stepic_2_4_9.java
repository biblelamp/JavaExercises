import java.util.*;

class Stepic_2_4_9 {

    public static void main(String[] args) {
        int[] a1 = {0, 2};
        int[] a2 = {1, 3};
        int[] a3 = mergeArrays(a1, a2);
        System.out.println(Arrays.toString(a3));
    }

    /**
     * Merges two given sorted arrays into one
     *
     * @param a1 first sorted array
     * @param a2 second sorted array
     * @return new array containing all elements from a1 and a2, sorted
     */
    public static int[] mergeArrays(int[] a1, int[] a2) {
        int[] a3 = new int[a1.length + a2.length];
        int i = 0;
        int j = 0;
        while (i + j < a3.length) {
            if (i < a1.length && j < a2.length) {
                if (a1[i] < a2[j]) {
                    a3[i + j] = a1[i];
                    i++;
                } else {
                    a3[i + j] = a2[j];
                    j++;
                }
            } else {
                if (i < a1.length) {
                    a3[i + j] = a1[i];
                    i++;
                }
                if (j < a2.length) {
                    a3[i + j] = a2[j];
                    j++;
                }
            }
        }
        return a3;
    }
}