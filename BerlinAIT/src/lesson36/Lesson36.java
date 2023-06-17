package lesson36;

import java.util.Arrays;

/**
 * Lesson 36 19/06/2023
 * @author Sergey Iryupin
 *
 * Словарик:
 *  interface - международная морда
 *  extends - расширяет
 *  implements - реализует
 */

public class Lesson36 {
    public static void main(String[] args) {
        Cat cat = new Cat("Barsik", "white", 3);
        System.out.println(cat);

        Bird bird = new Bird("Raven", "black", 1);
        System.out.println(bird);
        System.out.println(bird.hashCode());

        String str = "Java";
        System.out.println(str);
        str += ", hello!";
        System.out.println(str);

        int[] intArray = {4, 7, 12, 55, 4, 1};
        System.out.println(Arrays.toString(intArray));
    }
}
