package lesson11;

import java.util.Random;
import java.util.Scanner;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #11
 *
 * @version 7-Feb-24
 */
public class TicTacToe {

    static char[][] table = new char[3][3];
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static final char CHAR_EMPTY = '-';
    static final char CHAR_X = 'x';
    static final char CHAR_O = 'o';

    public static void main(String[] args) {
        // init table (.)
        initTable();
        // main game loop
        while (true) {
            // human turn (x)
            turnHuman();
            // is human win? yes - game over
            if (isWin(CHAR_X)) {
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
            if (isWin(CHAR_O)) {
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
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                table[y][x] = CHAR_EMPTY;
            }
        }
    }

    static void printTable() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                System.out.print(table[y][x] + " ");
            }
            System.out.println();
        }
    }

    static void turnHuman() {
        int x, y;
        do {
            System.out.println("Enter x & y [0..2]:");
            x = scanner.nextInt();
            y = scanner.nextInt();
        } while(!isCellValid(x, y));
        table[y][x] = CHAR_X;
    }

    static void turnAI() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while(!isCellValid(x, y));
        table[y][x] = CHAR_O;
    }

    static boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x > 2 || y > 2) {
            return false;
        }
        return table[y][x] == CHAR_EMPTY;
    }

    static boolean isWin(char chr) {
        // by x
        if (table[0][0] == chr && table[0][1] == chr && table[0][2] == chr) return true;
        if (table[1][0] == chr && table[1][1] == chr && table[1][2] == chr) return true;
        if (table[2][0] == chr && table[2][1] == chr && table[2][2] == chr) return true;
        // by y
        if (table[0][0] == chr && table[1][0] == chr && table[2][0] == chr) return true;
        if (table[0][1] == chr && table[1][1] == chr && table[2][1] == chr) return true;
        if (table[0][2] == chr && table[1][2] == chr && table[2][2] == chr) return true;
        // diagonals
        if (table[0][0] == chr && table[1][1] == chr && table[2][2] == chr) return true;
        if (table[0][2] == chr && table[1][1] == chr && table[2][0] == chr) return true;
        return false;
    }

    static boolean isTableFill() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (table[y][x] == CHAR_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
