package lesson10;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #10
 *
 * @version 5-Feb-24
 */
public class Lesson10 {
    public static void main(String[] args) {
        // java compiler and JVM

        // variables simple types == 8
        int a = 25;
        int b = 12 * 3 + a;

        // logics
        boolean f = true; // false;
        boolean l = f && true; // && (and) || (or) !(not)

        // if-else
        if (a == 3) {
            System.out.println("a = 3");
        } else {
            System.out.println("a != 3");
        }
        String s = a == 3? "a = 3" : "a != 3"; // ternary operator

        // switch
        switch (a) {
            case 1, 2, 3:
                System.out.println("a == 1 or 2 or 3");
                break;
            // case N:
            default:
                System.out.println("Alternative reality");
        }

        // loops while, do-while
        while (a < 10) {
            // TODO write loop body
            a++;
        }

        // loops for with 2 counters
        for (int i = 0, j = 4; i < 5; i++, j--) {
            System.out.println("i = " + i + ", j = " + j);
        }

        // array and for
        int[] arr = {4, 8, 1, 12, -3};
        arr[0] = -1;
        for (int item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();

        // using break && continue
        for (int i = 0; i < arr.length; i++) {
            if (i == 2) {
                //break;
                continue;
            }
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // method(s)
        int sum = add(5, 8);
        System.out.println(sum);
    }

    static int add(int a, int b) {
        return a + b;
    }
}
