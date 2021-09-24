/**
 * Java 1. Homework 2
 * 
 * @author Sergey Iryupin
 * @version 13.9.2021
 */
class Lesson2HomeWork {

    public static void main(String[] args) {
        System.out.println(isBetween10And20(10, -2));
        System.out.println(isBetween10And20(12, 5));
        System.out.println(isBetween10And20(20, 1));

        isPositiveOrNegative(-1);
        isPositiveOrNegative(0);
        isPositiveOrNegative(1);

        System.out.println(isNegativeOrPositive(-1));
        System.out.println(isNegativeOrPositive(0));
        System.out.println(isNegativeOrPositive(1));

        repeatString("Hello, Java!", 3);

        System.out.println(isYearLeap(1904));
        System.out.println(isYearLeap(1900));
        System.out.println(isYearLeap(2000));
        System.out.println(isYearLeap(2005));
    }

    static boolean isBetween10And20(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    static void isPositiveOrNegative(int a) {
        System.out.println(a < 0? "Negative" : "Positive");
    }

    static boolean isNegativeOrPositive(int a) {
        return a >= 0;
    }

    static void repeatString(String s, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(s);
        }
    }

    static boolean isYearLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}