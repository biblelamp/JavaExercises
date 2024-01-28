package lesson5;

import java.util.Random;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #5
 *
 * @version 23-Jan-24
 */
public class Lesson5 {
    public static void main(String[] args) {
        int n = 8;
        boolean a = n % 2 == 0;
        boolean b = n % 3 == 0;
        boolean c = a && b; // boolean AND -> &&, boolean OR -> ||, boolean NOT -> !
        System.out.println(a + ", " + b + ", " + c);

        System.out.println(Math.random());

        Random random = new Random();
        System.out.println(random.nextInt(101));

        String str = "2356";
        System.out.println(str.length());
        System.out.println(str.charAt(0));

        int t = 4;
        System.out.println(t <= 0);
        if (t <= 0) {
            System.out.println("It's frost.");
        } else {
            System.out.println("It's NO frost.");
        }
        str = new String("Hello");
        if (str.equals("Hello")) {
            System.out.println("ok");
        } else {
            System.out.println("NOT ok");
        }
        {
            int v = 55;
            System.out.println(v * 2);
        }
        //int b = 3;
        //System.out.println(b);
    }
}
