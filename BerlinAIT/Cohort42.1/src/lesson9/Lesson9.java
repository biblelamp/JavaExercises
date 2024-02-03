package lesson9;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #9
 *
 * @version 2-Feb-24
 */
public class Lesson9 {
    public static void main(String[] args) {
        int[] arr = {3, 5, 8, 2, 1};
        printArray(arr);

        int[] arr2 = {-5, -7, -1, -6, -5};
        printArray(arr2);

        int aa = 2;
        int bb = 2;
        int r1 = add(aa, bb);
        int r2 = add(-4, 12);
        System.out.println(r1 + ", " + r2);
        System.out.println(add(5,6,-1));
        task1(3);
    }

    static int add(int a, int b) {
        int c = a + b;
        return c;
    }

    static int add(int... a) {
        int s = 0;
        for (int i : a) {
            s += i;
        }
        return s;
    }

    static void task1(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println("Task" + i);
        }
    }

    static void printArray(int[] a) {
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
