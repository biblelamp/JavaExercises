/**
 * Book: Data Structures and Algorithms in Java, by Robert LaFore
 * Chapter 6:
 *  triangle.java
 *  evaluates triangular numbers
 *  to compile this code: javac triangle.java
 *  to run this program:  java TriangleApp
 */
import java.io.*;

class TriangleApp {
    static int theNumber;

    public static void main(String[] args) throws IOException {
        System.out.print("Enter a number: ");
        theNumber = getInt();
        int theAnswer = triangle(theNumber);
        // we can check result using formula:
        //  result = (n^2 + n)/2
        System.out.println("Triangle = " + theAnswer);
    }

    public static int triangle(int n) {
        System.out.println("Entering: n = " + n);
        if (n==1) {
            System.out.println("Returning 1");
            return 1;
        } else {
            int temp = n + triangle(n-1);
            System.out.println("Returning " + temp);
            return temp;
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}