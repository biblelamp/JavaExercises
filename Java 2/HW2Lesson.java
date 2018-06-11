/**
 * Java. Level 2. Lesson 2. Example of homework
 *
 * @author Sergey Iryupin
 * @version 0.3.2 dated Jun 11, 2018
 */
import java.io.*;
import java.util.Arrays;

class HW2Lesson {
    static final int SIZE = 4;
    static final String[] DATA = {
        "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0",
        "1 3 1 2\n2 a 2 2\n5 6 7 1\n3 3 1 0",
        "1 3 1 2\n2 3 2 2\n5 6 7 1",
        "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1"};
    static final String FILE_NAME = "lesson2test.txt";

    public static void main(String[] args) {

        // reading from file to buffer
        StringBuffer buffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(FILE_NAME))) {

            while (reader.ready())
                buffer.append(reader.readLine() + "\n");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // processing the data from buffer
        if (buffer.length() > 0)
            try {
                System.out.println(calcMatrix(strToMatrix(buffer.toString())));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }

        // processing strings from DATA
        for (String str : DATA)
            try {
                System.out.println(calcMatrix(strToMatrix(str)));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }
    }

    static String[][] strToMatrix(String str) { // convert String into Matrix
        String[] lines = str.split("\n");
        String[][] matrix = new String[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            matrix[i] = lines[i].split("\\s+");
            System.out.println(Arrays.toString(matrix[i]));
        }
        return matrix;
    }

    static int calcMatrix(String[][] matrix)
        throws NumberFormatException, ArrayIndexOutOfBoundsException {

        int result = 0;

        // checking the number of rows
        if (matrix == null || matrix.length != SIZE)
            throw new ArrayIndexOutOfBoundsException(
                "The number of rows doesn't match");

        // processing the matrix
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null ||
                    matrix[i].length != SIZE) // checking the number of columns
                throw new ArrayIndexOutOfBoundsException(
                    "The number of colums in row " + (i+1) + " doesn't match");
            for (int j = 0; j < matrix[i].length; j++)
                try {
                    result += Integer.parseInt(matrix[i][j]);
                } catch (NumberFormatException ex) {
                    throw new NumberFormatException(
                        "Bad symbol(s) in row " + (i+1) + " column " + (j+1));
                }
        }
        return result;
    }
}