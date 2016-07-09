/**
 * Java. Level 2. Lesson 2. Homework
 *  1. To understand the code that was written in class;
 *
 * @author Sergey Iryupin
 * @version 09 July 2016
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
        InputData id = new InputData(strIn);
        System.out.println("Enter file name for save result");
        strOut = sc.next();
        calculateAndSave(id, strOut);
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