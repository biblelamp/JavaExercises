/**
 * Java. Level 1. Lesson 2. Homework
 *	+	Create array consisting of elements of 0 and 1, for example, int [] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
 *	+	The array of the preceding paragraph, using a loop to replace the 0 to 1, 1 to 0;
 *	+	Create an array of 8 integers. With the help of the cycle to fill it with a value of 1, 4, 7, 10, 13, 16,
19, 22;
 *	+	Set array int [] mas = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1}; cycle to pass through it,
 *		and the number of that is less 6 multiply by 2
 *	+	* Set one-dimensional array and find the minimum and maximum elements;
 *	+	** Write a simple console calculator. The user enters two numbers and an operation
 *		which wants to execute, the program calculates and displays the result in the console; 
 *
 * @author Sergey Iryupin
 * @version 10 June 2016
 */
import java.util.Scanner;

public class Lesson2 {

    public static void main(String[] args) {

        // creating an array and output
        int[] array1 = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 };
        for (int i=0; i<array1.length; i++) {
            System.out.print( array1[ i ] );
        }
        System.out.println();

        // inverting elements and output
        for (int i=0; i<array1.length; i++) {
            array1[ i ] = 1 - array1[ i ];
            System.out.print( array1[ i ] );
        }
        System.out.println();

        // creating an array of 8 integers and fill it
        int[] nums = { 1, 4, 7, 10, 13, 16, 19, 22 };
        int[] array2 = new int[8];
        int j = 0;
        for (int item : nums) {
            array2[ j ] = item;
            j++;
        }

        // creating an array of at least 6 multiply by 2
        int[] mas = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i=0; i<mas.length; i++) {
            if (mas[ i ] < 6) {
                mas[ i ] *= 2;
            }
            System.out.print( mas[ i ] + " ");
        }
        System.out.println();

        // finding the maximum and minimum values in an array mas[]
        int max = -2147483648;
        int min = 2147483647;
        for (int item : mas) {
            if (max < item) {
                max = item;
            }
            if (min > item) {
                min = item;
            }
        }
        System.out.println("Max = " + max + " Min = " + min);

		// a simple console calculator
		calcConsole();
    }

    /** a simple console calculator
    */
    static void calcConsole() {

       Scanner read = new Scanner(System.in);

       System.out.println("Enter separated by space: Integer [+|-|*|/] Integer");

       int first = read.nextInt();
       String operator = read.next();
       int second = read.nextInt();

       switch(operator) {
           case "+":
               System.out.println("= " + (first + second));
               break;
           case "-":
               System.out.println("= " + (first - second));
               break;
           case "*":
               System.out.println("= " + (first * second));
               break;
           case "/":
               if (second != 0) {
                   System.out.println("= " + (first / second));
               } else {
                   System.out.println("Error: Division by zero");
               }
               break;
          default:
               System.out.println("Error: Undefined operation");
       }
   }
}