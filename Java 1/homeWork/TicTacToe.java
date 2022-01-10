/**
 * Java. Level 1. Lesson 4. Example of homework
 *  Tic-tac-toe in console with simple AI
 *
 * @author Sergey Iryupin
 * @version dated 20 Sep, 2021
 */
import java.util.Random;
import java.util.Scanner;

class TicTacToe {

    final int SIZE = 3;          // size of the game table
    final int WIN_SIZE = 3;      // size of win-line
    final char SIGN_X = 'x';     // sign of human
    final char SIGN_O = 'o';     // sign of AI
    final char SIGN_EMPTY = '.'; // sign of empty cell
    final String MSG_FOR_HUMAN = "Enter X and Y (1..3):";
    final String MSG_YOU_WON = "YOU WON!";
    final String MSG_AI_WON = "AI WON!";
    final String MSG_DRAW = "Sorry, DRAW!";
    final String MSG_GAME_OVER = "GAME OVER.";
    char[][] table;
    Scanner sc;
    Random random;

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    TicTacToe() {
        table = new char[SIZE][SIZE];
        sc = new Scanner(System.in);
        random = new Random();
    }

    void game() {
        initTable();
        while (true) {
            printTable();
            turnHuman(SIGN_X);
            if (checkWin(SIGN_X)) {
                System.out.println(MSG_YOU_WON);
                break;
            }
            if (isTableFull()) {
                System.out.println(MSG_DRAW);
                break;
            }
            turnAI(SIGN_O, SIGN_X);
            //printTable();
            if (checkWin(SIGN_O)) {
                System.out.println(MSG_AI_WON);
                break;
            }
            if (isTableFull()) {                    // this code doesn't matter
                System.out.println(MSG_DRAW);       // because a human always
                break;                              //  makes a move last in 3x3
            }
        }
        System.out.println(MSG_GAME_OVER);
        printTable();
    }

    void initTable() {                              // init game's field
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                table[i][j] = SIGN_EMPTY;
            }
    }

    void printTable() {                             // output game's field
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void turnHuman(char ch) {                      // human action
        int x, y;
        do {
            System.out.println(MSG_FOR_HUMAN);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = ch;
    }

    void turnAI(char ch, char enemyDot) {          // AI action
        int x, y;
        for (x = 0; x < SIZE; x++) {               // simple blocking
            for (y = 0; y < SIZE; y++) {
                if (isCellValid(x, y)) {           // if cell empty
                    table[y][x] = enemyDot;        // try to be like enemy
                    if (checkWin(enemyDot)) {      // if win
                        table[y][x] = ch;          // block
                        return;                    // and exit
                    }
                    table[y][x] = SIGN_EMPTY;      // restore cell
                }
            }
        }
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        table[y][x] = ch;
    }

    boolean checkWin(char ch) {                    // check win condition
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                for (int dy = -1; dy < 2; dy++) {
                    for (int dx = -1; dx < 2; dx++) {
                        if (checkLine(x, y, dx, dy, ch) == WIN_SIZE) {
                            return true;
                        }
                    }
                }
            }
        }
        /*
        //simple checking of win in 3x3 table
        //
        // check horizontals and verticals
        for (int i = 0; i < SIZE; i++) {
            if ((table[i][0] == ch && table[i][1] == ch && table[i][2] == ch) ||
                (table[0][i] == ch && table[1][i] == ch && table[2][i] == ch))
                return true;
        }
        // check diagonals
        if ((table[0][0] == ch && table[1][1] == ch && table[2][2] == ch) ||
            (table[2][0] == ch && table[1][1] == ch && table[0][2] == ch)) {
            return true;
        }
        */
        return false;
    }

    int checkLine(int x, int y, int dx, int dy, char ch) {
        int count = 0;                              // check line for win
        if (dx == 0 && dy == 0) {
            return 0;
        }
        for (int i = 0, xi = x, yi = y;
                i < WIN_SIZE; i++, xi += dx, yi += dy) {
            if (xi >= 0 && yi >= 0 && xi < SIZE &&
                    yi < SIZE && table[yi][xi] == ch) {
                count++;
            }
        }
        return count;
    }

    boolean isTableFull() {                        // check field filling
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (table[i][j] == SIGN_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isCellValid(int x, int y) {             // check cell
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return table[y][x] == SIGN_EMPTY;
    }
}
