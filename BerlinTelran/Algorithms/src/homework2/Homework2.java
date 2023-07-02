package homework2;

/**
 * Algorithms. Homework #2
 *
 * @author Sergey Iryupin
 * @version 25 Jun 2023
 * @see Оценка сложности алгоритмов https://habr.com/ru/articles/104219/
 */

/**
 * 2. Task #1
 * START
 * READ number n
 * IF n == 1 THEN return
 * FOR i = 1, i <= n, i++
 * 	 FOR j = 1; j <= n, j++
 *     print "*"
 * 	   BREAK
 * END
 */

/**
 * 2. Task #2
 * START
 * READ number n
 * numbers i = 0, j = 0, a = 0
 * FOR i = n/2, i <= n; i++
 * 	 FOR j = 2, j <= n, j * 2
 *     a = a + n / 2
 * END
 */

/**
 * 2. Task #3
 * START
 * READ number n
 * number a = 0
 * FOR i = 0, i < n, i++
 * 	 FOR j = n, j > i, j--
 *     a = a + i + j
 * END
 */

/**
 * 2. Task #4
 * START
 * READ number n
 * numbers a = 0, i = n
 * WHILE i > 0
 *   a = a + i
 *   i = i / 2
 * END
 */

public class Homework2 {
    public static void main(String[] args) {
        // Task #1: O(n)
        // Task #2: O(n log n)
        // Task #3: O(n^2)
        // Task #4: O(log n)
    }
    // best O(1) worst O(n)+1 = O(n)
    static void task1(int n) {
        if (n == 1) {
            return;
        }
        for (int i = 1; i <= n; i++) {     // times = n
            for (int j = 1; j <= n; j++) { // times = 1
                System.out.println("*");
                break;
            }
        }
    }
    // n/2 * log(n) = O(n log n)
    static void task2(int n) {
        int a = 0;
        for (int i = n/2; i <= n; i++) {      // times = n/2
            for (int j = 2; j <= n; j *= 2) { // times = log(n)
                a = a + n / 2;
            }
        }
    }
    // n * n = O(n^2)
    static void task3(int n) {
        int a = 0;
        for (int i = 0; i < n; i++) {     // times = n
            for (int j = n; j > i; j--) { // times = n
                a = a + i + j;
            }
        }
    }
    // n/2^n = O(log n)
    static void task4(int n) {
        int a = 0, i = n;
        while (i > 0) { // times = n/2^n
            a += i;
            i /= 2;
        }
    }
}
