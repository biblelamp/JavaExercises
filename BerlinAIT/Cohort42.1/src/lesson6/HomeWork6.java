package lesson6;

import java.util.Scanner;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #6
 *
 * @author Sergey Iryupin
 * @version 29-Jan-24
 */
public class HomeWork6 {
    public static void main(String[] args) {
        // task 1
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1, 2 or 3:");
        int n = scanner.nextInt();
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

        // task 2
        int day = 5;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            // 3,4,5 - Wednesday, Thursday, Friday
            case 6,7:
                System.out.println("Weekend");
                break;
            default:
                System.out.println("Invalid day of week number!");
        }

        // task 3
        int i = 1;
        while (i < 11) {
            System.out.println("Task" + i);
            i++;
        }

        // task 4
        int count = 0;
        do {
            count++;
            if (count % 5 == 0) {
                System.out.println(count);
            }
        } while (count < 100);

        // task 5
        scanner = new Scanner(System.in);
        count = 0;
        String word = "";
        do {
            count++;
            System.out.println("Say 'hello' and come in:");
            word = scanner.nextLine();
        } while (!word.equalsIgnoreCase("hello"));
        System.out.println("Hello, friend! count=" + count);

        // task 6
        String expression = "34 + 8";
        String n1 = "";
        String n2 = "";
        String s = "";
        boolean error = false;
        int p = 0;
        while (p < expression.length()) {
            char ch = expression.charAt(p);
            switch (ch) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                    if (s.isEmpty()) {
                        n1 += ch;
                    } else {
                        n2 += ch;
                    }
                    break;
                case '+', '-', '*', '/':
                    s = String.valueOf(ch);
                    break;
                case ' ':
                    break;
                default:
                    error = true;
            }
            p++;
        }
        if (error) {
            System.out.println("Error in expression!");
        } else {
            int n1value = Integer.valueOf(n1);
            int n2value = Integer.valueOf(n2);
            switch (s) {
                case "+":
                    System.out.println(n1 + s + n2 + " = " + (n1value + n2value));
                    break;
                case "-":
                    System.out.println(n1 + s + n2 + " = " + (n1value - n2value));
                    break;
                case "*":
                    System.out.println(n1 + s + n2 + " = " + (n1value * n2value));
                    break;
                case "/":
                    System.out.println(n1 + s + n2 + " = " + (n1value / n2value));
                    break;
            }
        }
    }
}
