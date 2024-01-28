package lesson3;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #3
 *
 * @author Sergey Iryupin
 * @version 22-Jan-24
 */
public class HomeWork3 {
    public static void main(String[] args) {
        // task #1
        int n1 = 0, n2 = 1, n3 = 2, n4 = 3, n5 = 4, n6 = 5, n7 = 6, n8 = 7, n9 = 8, n10 = 9;
        int sum = (n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + n10);
        int average = sum / 10;
        double averageDouble = sum / 10.;
        System.out.println("Average = " + averageDouble);

        // task #2
        int productA = 1000;
        int productB = 500;
        int total = productA + productB;
        double discount = total / 10.;
        System.out.println("Discount = " + discount + " total = " + (total - discount));

        // task #3
        averageDouble = (1 + 0 + -1 + -2 + -1 + -2 + 7) / 7d;
        System.out.println("Average t = " + averageDouble);

        // task #4
        int a = 9 % 2;
        int b = 10 % 2;
        int c = 11 % 2;
        System.out.println(a + ", " + b + ", " + c);
        System.out.println((9 % 3) + ", " + (10 % 3) + ", " + (11 % 3) + ", " + (12 % 3));

        // task #5
        int x = 3;
        x += x++;
        System.out.println(x);
    }
}
