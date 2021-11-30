/**
 * Java 1. Homework 1
 * 
 * @author Sergey
 * @version 6.9.2021
 */
class Lesson1HomeWork {

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
    }

    static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    static void checkSumSign() {
        int a = 5;
        int b = 3;
        System.out.println("The sum is " + (a + b >= 0? "positive" : "negative"));
    }

    static void printColor() {
        int value = 101;
        if (value <= 0) {
            System.out.println("Red");
        } else if (value <= 100) {
            System.out.println("Yellow");
        } else {
            System.out.println("Green");
        }
    }

    static void compareNumbers() {
        int a = 10;
        int b = 10;
        System.out.println(a >= b? "a >= b" : "a < b");
    }
}