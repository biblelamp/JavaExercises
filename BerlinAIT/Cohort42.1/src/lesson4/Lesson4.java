package lesson4;

import java.util.Scanner;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #4
 *
 * @version 22-Jan-24
 */
public class Lesson4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter first number:");
//        int a = scanner.nextInt();
//        System.out.println("Enter second number:");
//        int b = scanner.nextInt();
//        System.out.println("Result:");
//        System.out.println(a + " + " + b + " = " + (a + b));

        // class String
        String str = "Hello, Java";
        String ch = String.valueOf('a');
        String[] parts = str.split(" ");
        String s = 10 + " sm";
        System.out.println(str.substring(7) + 256 + " is great!");
    }
}
