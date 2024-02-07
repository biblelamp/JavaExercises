package lesson11;

import java.util.Random;
import java.util.Scanner;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #11
 *
 * @version 7-Feb-24
 */
public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        char[][] table = new char[3][3];
        // init table (.)
        initTable();
        // main game loop
        while (true) {
            // human turn (x)
            turnHuman();
            // is human win? yes - game over
            if (isWin('x')) {
                System.out.println("YOU WON!");
                break;
            }
            // is table fill? yes - game over
            if (isTableFill()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            // AI turn (o)
            turnAI();
            // is AI win? yes - game over
            if (isWin('o')) {
                System.out.println("AI WON!");
                break;
            }
            // is table fill? yes - game over
            if (isTableFill()) {
                System.out.println("Sorry, DRAW");
                break;
            }
            // print table
            printTable();
        }
        // print table
        printTable();
    }

    static void initTable() {
    }

    static void printTable() {
    }

    static void turnHuman() {
    }

    static void turnAI() {
    }

    static boolean isWin(char chr) {
        return false;
    }

    static boolean isTableFill() {
        return false;
    }
}
