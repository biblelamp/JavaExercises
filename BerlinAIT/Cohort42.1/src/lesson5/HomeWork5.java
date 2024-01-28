package lesson5;

import java.util.Random;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #5
 *
 * @author Sergey Iryupin
 * @version 26-Jan-24
 */
public class HomeWork5 {
    public static void main(String[] args) {
        // task 1
        int n = 345;
        boolean a = n % 2 == 0;
        boolean b = n % 3 == 0;
        boolean c = a && b;
        System.out.println("Number " + n + " div by 2:" + a + ", div by 3:" + b + ", dib by 2 and div by 3:" + c);

        // task2
        n = 3;
        if (n == 1) {
            System.out.println("One");
        } else if (n == 2) {
            System.out.println("Two");
        } else if (n == 3) {
            System.out.println("Three");
        } else {
            System.out.println("Wrong number");
        }

        // task3
        Random random = new Random();
        int n1 = random.nextInt(101);
        int n2 = random.nextInt(101);
        int n3 = random.nextInt(101);
        int n4 = random.nextInt(101);
        int n12 = n1 < n2? n2 : n1;
        int n34 = n3 < n4? n4 : n3;
        int max = n12 < n34? n34 : n12;
        System.out.println(n1 + ", " + n2 + ", " + n3 + ", " + n4 + ", max: " + max);

        // task4
        String str = "4381";
        if (str.length() == 4) {
            n1 = str.charAt(0) - '0';
            n2 = str.charAt(1) - '0';
            n3 = str.charAt(2) - '0';
            n4 = str.charAt(3) - '0';
            System.out.println(n1 + n2 == n3 + n4? "lucky number" : "unlucky number");
        } else {
            System.out.println("Wrong input: " + str);
        }
    }
}
