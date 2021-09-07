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
        if (a + b >= 0) {
            System.out.println("The sum is positive");
        } else {
            System.out.println("The sum is negative");
        }
    }

    static void printColor() {
        int value = 101;
        if (value <= 0) {
            System.out.println("Red");
        } else if (value <= 100) {
            System.out.println("Yellow");
        } else if (value > 100) {
            System.out.println("Green");
        }
    }

    static void compareNumbers() {
        int c = 10;
        int d = 10;
        if (c >= d) {
            System.out.println(c + ">=" + d);
        } else if (c < d) {
            System.out.println(c + "<" + d);
        }
    }
}