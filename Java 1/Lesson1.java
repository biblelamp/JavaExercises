/**
 * Java. Level 1. Lesson 1. Homework
 *	+	Create variables passed all types of data, and initialize their values;
 *	+	Write method of calculating the expression a * (b + (c / d)),
 *		and returns the result, and, b, c, d - input parameters of this method.
 *	+	Write a method that takes the input of the two numbers,
 *		and checking sum is in the range 10 to 20, and if so - to return true, otherwise - false.
 *	+	* Write a method that determines whether a leap year.
 *		Every 4th year is leap year but every 100th, each 400th - leap.
 *
 * @author Sergey Iryupin
 * @version от 09 June 2016
 */
public class Lesson1 {

    public static void main(String[] args) {
        byte b = 64;
        short s = 1024;
        int i = 200000;
        long l = 15000L;
        float f = 120.0f;
        double d = 15.72d;
        boolean flag = true;
        char c = 'a';

        System.out.println("" + b + " " + s + " " + i + " " + l + " " + f + " " + d + " " + flag + " " + c);

        System.out.println("Calculating a * (b + (c / d))");
        System.out.println(calcExpr(2, 10, 6, 2));
        System.out.println(calcExpr(1, 8, 5, 0));

        System.out.println("Cheking the summ (a + b) is in the range [10..20]");
        System.out.println(checkSumm(1, 6));
        System.out.println(checkSumm(10, 2));
        System.out.println(checkSumm(10, 11));

        System.out.println("Cheking - is the year leap?");
        System.out.println(isYearLeap(1600));
        System.out.println(isYearLeap(1604));
        System.out.println(isYearLeap(1800));
    }

    /** calculating a * (b + (c / d))
    */
    static int calcExpr(int a, int b, int c, int d) {
        if (d == 0) {
            System.out.println("Error: Division by zero");
            return -1;
        } else {
            return a * (b + (c / d));
        }
    }

    /** cheking the summ (a + b) is in the range [10..20]
    */
    static boolean checkSumm(int a, int b) {
        if ((a + b >= 10) && (a + b <= 20)) {
            return true;
        } else {
            return false;
        }
    }

    /** cheking - is the year leap?
    */
    static boolean isYearLeap(int y) {
        if (((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }
}