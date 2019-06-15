/**
 * Java. Level 1. Lesson 4. Example of homework
 *  Tic-tac-toe in console with simple AI
 *
 * @author Sergey Iryupin
 * @version dated Jun 15, 2019
 */
import java.util.Random;
import java.util.Scanner;

class HW4Lesson {

    final int SIZE = 3;         // size of the game map
    final int WIN_SIZE = 3;     // size of win-line
    final char DOT_X = 'x';     // sign of human
    final char DOT_O = 'o';     // sign of AI
    final char DOT_EMPTY = '.'; // sign of empty cell
    final String MSG_FOR_HUMAN = "Enter X and Y (1..3):";
    final String MSG_YOU_WON = "YOU WON!";
    final String MSG_AI_WON = "AI WON!";
    final String MSG_DRAW = "Sorry, DRAW!";
    final String MSG_GAME_OVER = "GAME OVER.";
    char[][] map;
    Scanner sc;
    Random rand;

    public static void main(String[] args) {
        new HW4Lesson().game();
    }

    HW4Lesson() {
        map = new char[SIZE][SIZE];
        sc = new Scanner(System.in);
        rand = new Random();
    }

    void game() {
        initMap();
        while (true) {
            printMap();
            turnHuman(DOT_X);
            if (checkWin(DOT_X)) {
                System.out.println(MSG_YOU_WON);
                break;
            }
            if (isMapFull()) {
                System.out.println(MSG_DRAW);
                break;
            }
            turnAI(DOT_O, DOT_X);
            //printMap();
            if (checkWin(DOT_O)) {
                System.out.println(MSG_AI_WON);
                break;
            }
            if (isMapFull()) {                      // this code doesn't matter
                System.out.println(MSG_DRAW);       // because a human always
                break;                              //  makes a move last in 3x3
            }
        }
        System.out.println(MSG_GAME_OVER);
        printMap();
    }

    void initMap() {                                // init game's field
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = DOT_EMPTY;
    }

    void printMap() {                               // output game's field
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    void turnHuman(char dot) {                      // human action
        int x, y;
        do {
            System.out.println(MSG_FOR_HUMAN);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = dot;
    }

    void turnAI(char dot, char enemyDot) {          // AI action
        int x, y;
        for (x = 0; x < SIZE; x++)                  // simple blocking
            for (y = 0; y < SIZE; y++)
                if (isCellValid(x, y)) {            // if cell empty
                    map[y][x] = enemyDot;           // try to be like enemy
                    if (checkWin(enemyDot)) {       // if win
                        map[y][x] = dot;            // block
                        return;                     // and exit
                    }
                    map[y][x] = DOT_EMPTY;          // restore cell
                }
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = dot;
    }

    boolean checkWin(char dot) {                    // check win condition
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                for (int dy = -1; dy < 2; dy++)
                    for (int dx = -1; dx < 2; dx++)
                        if (checkLine(x, y, dx, dy, dot) == WIN_SIZE)
                            return true;
        /* simple checking of win in 3x3 map
        // check horizontals and verticals
        for (int i = 0; i < SIZE; i++)
            if ((map[i][0] == dot && map[i][1] == dot && map[i][2] == dot) ||
                (map[0][i] == dot && map[1][i] == dot && map[2][i] == dot))
                return true;
        // check diagonals
        if ((map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) ||
            (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot))
            return true;
        */    
        return false;
    }

    int checkLine(int x, int y, int dx, int dy, char dot) {
        int count = 0;                              // check line for win
        if (dx == 0 && dy == 0)
            return 0;
        for (int i = 0, xi = x, yi = y;
                i < WIN_SIZE; i++, xi += dx, yi += dy)
            if (xi >= 0 && yi >= 0 && xi < SIZE &&
                    yi < SIZE && map[yi][xi] == dot)
                count++;
        return count;
    }

    boolean isMapFull() {                           // check field filling
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    boolean isCellValid(int x, int y) {             // check cell
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE)
            return false;
        return map[y][x] == DOT_EMPTY; // by DSerov
    }
}
