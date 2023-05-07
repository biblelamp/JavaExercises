package cz.javageek.games;
/**
 * Game TicTacToe
 * @version 0.1 from 7.5.2023
 */
import java.util.Random;

public class TicTacToe {

    private final String YOU_WON = "You won!";
    private final String AI_WON = "AI won!";
    private final String SORRY_DRAW = "Sorry, draw!";
    private final char CHAR_O = 'o';
    private final char CHAR_X = 'x';
    private final int SIZE = 3;

    private char[][] table;
    private Random random;
    private String gameStatus;

    public TicTacToe() {
        table = new char[SIZE][SIZE];
        random = new Random();
    }

    public void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                table[i][j] = 0;
            }
        }
        gameStatus = null;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public boolean isGameOver() {
        return gameStatus != null;
    }

    public boolean turn(int x, int y) {
        if (isCellValid(x, y)) {
            table[y][x] = CHAR_X;
            if (isWin(CHAR_X)) {
                gameStatus = YOU_WON;
            } else if (isTableFull()) {
                gameStatus = SORRY_DRAW;
            }
            if (gameStatus == null) {
                turnAI();
                if (isWin(CHAR_O)) {
                    gameStatus = AI_WON;
                } else if (isTableFull()) {
                    gameStatus = SORRY_DRAW;
                }
            }
            return true;
        }
        return false;
    }

    private void turnAI() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        table[y][x] = CHAR_O;
    }

    public boolean isTableFull() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (table[y][x] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWin(char ch) {
        for (int i = 0; i < SIZE; i++) {
            if (table[i][0] == ch && table[i][1] == ch && table[i][2] == ch) return true;
            if (table[0][i] == ch && table[1][i] == ch && table[2][i] == ch) return true;
        }

        if (table[0][0] == ch && table[1][1] == ch && table[2][2] == ch) return true;
        if (table[2][0] == ch && table[1][1] == ch && table[0][2] == ch) return true;
        return false;
    }

    private boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x > 2 || y > 2) {
            return false;
        }
        return table[y][x] == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                sb.append(table[y][x] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}