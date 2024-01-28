package lesson3;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #3
 *
 * @version 19-Jan-24
 */
public class Lesson3 {
    public static void main(String[] args) {
        int b = 10;
        float c = 6;
        double a = b / (double) c;
        char f = 103;
        //b = f;
        long t = Math.round(a);
        System.out.println(b + " / " + c + " = " + a);
        System.out.printf("a = %f", a);
    }
}
