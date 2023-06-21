package homework36;

import java.util.Arrays;

/**
 * Homework #36
 *
 * @author Sergey Iryupin
 * @version 21 Jun 2023
 */

public class Homework36 {
    public static void main(String[] args) {
        // task #1
        int[] arr = {43, 13, 22, 76, 1, 8};
        System.out.println(Arrays.toString(arr));
        System.out.println(toString(arr));
        int[] empty = {};
        System.out.println(toString(empty));

        // task #2
        Point2D p1 = new Point2D(5, 5);
        Point2D p2 = new Point2D(5, 5);

        System.out.println(p1.equals(p2));

        ColorPoint2D cp = new ColorPoint2D(5, 5);

        System.out.println(p1.equals(cp));
    }

    static String toString(int[] intArray) {
        StringBuilder sb = new StringBuilder("[");
//        sb.append(intArray[0]);
//        for (int i = 1; i < intArray.length; i++) {
//            sb.append(", " + intArray[i]);
//        }
        for (int i = 0; i < intArray.length; i++) {
            sb.append(intArray[i] + (i != intArray.length - 1 ? ", " : ""));
//            if (i != intArray.length - 1) {
//                sb.append(", ");
//            }
        }
        return sb.append("]").toString();
    }
}
