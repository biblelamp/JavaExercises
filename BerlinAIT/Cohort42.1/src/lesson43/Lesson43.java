package lesson43;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #43
 *
 * @version 26-Apr-24
 */
public class Lesson43 {
    public static void main(String[] args) {
        int a = 1;
        int b = 0;
        String str = null;
        int[] ints = {1, 2, 3};
        //System.out.println("a = " + a + ", b = " + b);
        //System.out.println(ints[3]);
        //System.out.println(str.charAt(0));
        //System.out.println(getADivB(a, b));

//        int idx = 1;
//        try {
//            ints[idx] = 5;
//            System.out.println(ints[idx]);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("Finally block.");
//        }

        //printTextFile("textfile.txt");

        if (str == null) {
            throw new MyException("String `str` is null.");
        }

        System.out.println("Everything OK");
    }

    static void printTextFile(String fileName) throws IOException {
        Stream<String> text = Files.lines(Path.of(fileName));
        text.forEach(System.out::println);
    }

    static Integer add(Integer a, Integer b) {
        return a + b;
    }

    static int getADivB(int a, int b) {
        return div(a, b);
    }

    static int div(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            // TODO
        }
        return 0;
    }
}
