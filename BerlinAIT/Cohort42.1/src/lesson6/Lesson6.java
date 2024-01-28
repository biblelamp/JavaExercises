package lesson6;

import java.util.Scanner;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #6
 *
 * @version 26-Jan-24
 */
public class Lesson6 {
    public static void main(String[] args) {
        // switch
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1, 2 or 3:");
        int n = 1; //scanner.nextInt();
        switch (n) {
            case 1:
                System.out.println("One");
                break;
            case 2:
                System.out.println("Two");
                break;
            case 3:
                System.out.println("Three");
                break;
            default:
                System.out.println("Wrong number!");
        }
        // loop while
        System.out.println("while i--");
        int i = 5;
        while (i > 0) {
            System.out.println(i);
            i--; // i = i - 1;
        }
        System.out.println("while i++");
        i = 1;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }
        // while with String
        String str = "4317";
        System.out.println(str);
        int p = 0;
        do {
            System.out.println(str.charAt(p));
            p++; // p = p + 1;
        } while (p < str.length());
    }
}
