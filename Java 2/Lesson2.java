/**
 * Java. Level 2. Lesson 2. Homework
 *  1. To understand the code that was written in class;
 *  2. If one of the cells isn't a number (letter/word), then
 *  the calculation will not work, so we throw an exception about
 *  the wrong data in the file;
 *  3. If the matrix file not found/doesn't exist - to inform the user;
 *  4. If the size of the matrix in the file is less than 5x5 -
 *  throw an exception on the wrong size of the matrix.
 *
 * @author Sergey Iryupin
 * @version 11 July 2016
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lesson2 {

    public static void main(String[] args) {
        doIt();
    }

    public static void doIt() {
        Scanner sc = new Scanner(System.in);
        String strIn, strOut;
        System.out.println("Enter file name with matrix");
        strIn = sc.next();
        try {
            InputData id = new InputData(strIn);
            System.out.println("Enter file name for save result");
            strOut = sc.next();
            calculateAndSave(id, strOut);
        } catch (IllegalArgumentException | FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public static void calculateAndSave(InputData id, String filename) {
        try {
            PrintWriter pw = new PrintWriter(filename);
            pw.write(id.calculateAndGenerate());
            pw.close();
            System.out.println("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}