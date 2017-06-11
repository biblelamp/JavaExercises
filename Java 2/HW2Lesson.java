/**
 * Java. Level 2. Lesson 2. Example of homework
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Jun 11, 2017
 */
import java.util.*;
import java.io.*;

public class HW2Lesson {
    static final int SIZE = 4;
    static final String[] data = {
        "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0",
        "1 3 1 2\n2 a 2 2\n5 6 7 1\n3 3 1 0",
        "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3\n0 6",
        "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0 6"};
    static final String FILE_NAME = "lesson2test.txt";

    public static void main(String[] args) {
        // try ro read file
        String line;
        String file = "";
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(FILE_NAME)))){
            while ((line = reader.readLine()) != null)
                file += line + "\n";
        } catch (Exception e) {
            System.out.println(e);
        }
        if (file.length() > 0)
            try {
                System.out.println(calcMatrix(file));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println(e);
            }
        // processing strings
        for (String str : data)
            try {
                System.out.println(calcMatrix(str));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println(e);
            }
    }

    static float calcMatrix(String str) 
        throws NumberFormatException, ArrayIndexOutOfBoundsException {

        String[][] matrix = new String[SIZE][SIZE];
        float result = 0;
        // converting a string to an array
        String[] lines = str.split("\n");
        if (lines.length != SIZE)
            throw new ArrayIndexOutOfBoundsException(
                "The number of rows doesn't match");
        for (int i = 0; i < lines.length; i++) {
            matrix[i] = lines[i].split(" ");
            System.out.println(Arrays.toString(matrix[i]));
            if (matrix[i].length != SIZE)
                throw new ArrayIndexOutOfBoundsException(
                    "The number of colums in row " + (i+1) + " doesn't match");
        }
        // counting the result (sum of all elements divided of 2)
        for (String[] line : matrix)
            for (String item : line)
                result += Integer.parseInt(item);
        return result/2;
    }
}