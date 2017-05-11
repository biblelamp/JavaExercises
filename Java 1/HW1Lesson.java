/**
 * Java. Level 1. Lesson 1. Example of homework
 *
 * @author Sergey Iryupin
 * @version dated May 11, 2017
 */
class HW1Lesson {

    public static void main(String[] args) {
        createVariable();
        System.out.println(calculate(1, 2, 3, 4));
        System.out.println(checkSum(10, 5));
        checkPositiveOrNegative(10);
        checkPositiveOrNegative(-5);
        System.out.println(сheckNegative(-1));
        System.out.println(сheckNegative(5));
        welcomeName("World");
        checkLeapYear(1900); // div 4 but div 100
        checkLeapYear(1996); // div 4 and not div 100
        checkLeapYear(2000); // div 400
        checkLeapYear(2016); // div 4 and not div 100
    }

    /**
     * 2. Создать и проинициализировать переменные типов
     *    byte, short, int, long, float, double, char, boolean, string
     */
    static void createVariable() {
        byte b = 127;
        short s = 1024; // 2^8
        int i = 65536; // 2^16
        long l = 4294967296L; // 2^32
        float f = 123.45F;
        double d = 567.89;
        char c = 'a';
        boolean bo = true;
        String str = "Hello";
    }

    /**
     * 3. Написать метод вычисляющий выражение  a * (b + (c / d)) и возвращающий
     *    результат, где a, b, c, d – входные параметры этого метода
     */
    static int calculate(int a, int b, int c, int d) {
        if (d == 0) return 0;
        return a * (b + (c / d));
    }

    /**
     * 4. Написать метод, принимающий на вход два числа, и проверяющий что их
     *    сумма лежит в пределах от 10 до 20 (включительно), если да – вернуть
     *    true, в противном случае – false
     */
    static boolean checkSum(int a, int b) {
        return ((a + b >= 10) && (a + b <= 20));
    }

    /**
     * 5. Написать метод, которому в качестве параметра передается целое число,
     *    метод должен напечатать в консоль положительное ли число передали,
     *    или отрицательное. Замечание: ноль считаем положительным числом
     */
    static void checkPositiveOrNegative(int a) {
        if (a < 0) System.out.println(a + " is Negitive");
        else System.out.println(a + " is Positive");
    }

    /**
     * 6. Написать метод, которому в качестве параметра передается целое число,
     *    метод должен вернуть true, если число отрицательное
     */
    static boolean сheckNegative(int a) {
        return a < 0;
    }

    /**
     * 7. Написать метод, которому в качестве параметра передается строка,
     *    обозначающая имя, метод должен вывести в консоль сообщение
     *    «Привет, указанное_имя!»
     */
    static void welcomeName(String name) {
        System.out.println("Hello, " + name + "!");
    }

    /**
     * 8* Написать метод, который определяет является ли год високосным, и
     *    выводит сообщение в консоль. Каждый 4-й год является високосным,
     *    кроме каждого 100-го, при этом каждый 400-й – високосный.
     */
    static void checkLeapYear(int y) {
        if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0)
            System.out.println(y + " year is a leap");
        else
            System.out.println(y + " year isn't a leap");
    }
}