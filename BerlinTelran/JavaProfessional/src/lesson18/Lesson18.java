package lesson18;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Lesson18 {
    public static void main(String[] args) {
        int a = 10;
        int b = 2;
        int c;
        if (b == 0) {
            throw new DivideByZeroException();
        }
        System.out.println(div(a, b));
        System.out.println("Application done.");
    }

    static int sumMetrix(String[][] matrix) {
        // проверка размеров массива
        // если размеры не соответствуют, выброс MyArraySizeException

        int sum = 0;

        // преобразование всех элементов matrix в int
        // и суммирование их
        // преобразование строки в целое число: Integer.parseInt(str)
        // если преобразование не удаётся (исключение), выброс MyArrayDataException(row, col)

        return sum;
    }

    static int div(int a, int b) {
        return a / b;
    }

    static void fileWrite() throws IOException {
        FileWriter file = new FileWriter("text.txt");
        file.write("Hello\nLesson18");
        file.close();
    }

    static void fileWriteRead() {

        // writing to file
        try (FileWriter file = new FileWriter("text.txt")) {
            file.write("Hello\nJava");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // reading from file
        try (FileReader file = new FileReader("text.txt")) {
            int b;
            while ((b = file.read()) != -1)
                System.out.print((char)b);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
