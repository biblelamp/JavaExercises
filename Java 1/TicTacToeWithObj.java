/**
 * Java. Game TicTacToe with objects
 *
 * @author Sergey Iryupin
 * @version May 29, 2017
 */
import java.util.*;

class TicTacToeWithObj {

    final String DRAW_MSG = "Sorry, DRAW...";
    Field field = new Field(3);
    Player[] players = {new Human(), new AI()};

    public static void main(String[] args) {
        new TicTacToeWithObj();
    }

    TicTacToeWithObj() {
        field.print();
        while (!field.isOver()) {
            for (Player player : players) {
                player.turn(field);
                field.print();
                if (field.isWin(player.getDot())) {
                    System.out.println(player.getClass().getSimpleName() + " WON");
                    field.setOver();
                    break;
                }
                if (field.isFull()) {
                    System.out.println(DRAW_MSG);
                    field.setOver();
                }
            }
        }
    }
}

class Field {
    final int FIELD_SIZE;
    char[][] field;
    final char EMPTY_DOT = '.';
    boolean gameOver = false;

    Field(int size) {
        FIELD_SIZE = size;
        field = new char[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                field[i][j] = EMPTY_DOT;
    }

    void setOver() { gameOver = true; }

    boolean isOver() { return gameOver; }

    int getSize() { return FIELD_SIZE; }

    void setDot(int x, int y, char dot) { field[x][y] = dot; }

    boolean isCellEmpty(int x, int y) {
        if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1)
            return false;
        if (field[x][y] == EMPTY_DOT) return true;
        return false;
    }

    boolean isFull() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                if (field[i][j] == EMPTY_DOT) return false;
        return true;
    }

    boolean isWin(char ch) {
        // checking horizontals / verticals
        for (int i = 0; i < FIELD_SIZE; i++)
            if ((field[i][0] == ch && field[i][1] == ch && field[i][2] == ch) ||
                (field[0][i] == ch && field[1][i] == ch && field[2][i] == ch))
                return true;
        // checking diagonals
        if ((field[0][0] == ch && field[1][1] == ch && field[2][2] == ch) ||
            (field[2][0] == ch && field[1][1] == ch && field[0][2] == ch))
            return true;
        return false;
    }

    void print() {
        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) 
                System.out.print(field[x][y]);
            System.out.println();
        }
    }
}

abstract class Player { // abstract class for inheritance
    char DOT;
    char getDot() { return DOT; }
    abstract void turn(Field field);
}

class Human extends Player { // class for human player
    Scanner sc;

    Human() {
        DOT = 'x';
        sc = new Scanner(System.in);
    }

    @Override
    void turn(Field field) {
        int x, y;
        do {
            System.out.println("Enter X Y (1-"+field.getSize()+"):");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!field.isCellEmpty(x, y));
        field.setDot(x, y, DOT);
    }
}

class AI extends Player { // class for AI player
    Random random;

    AI() { 
        DOT = 'o';
        random = new Random();
    }

    @Override
    void turn(Field field) {
        int x, y;
        System.out.println("AI made its turn");
        do {
            x = random.nextInt(field.getSize());
            y = random.nextInt(field.getSize());
        } while (!field.isCellEmpty(x, y));
        field.setDot(x, y, DOT);
    }
}