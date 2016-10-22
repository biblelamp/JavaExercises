/**
 * Java. Game TicTacToe with objects
 *
 * @author Sergey Iryupin
 * @version 0.3 dated October 22, 2016
 */
import java.util.*;

class TicTacToeWithObjects {

    final String DRAW_MSG = "Sorry draw...";
    Field field = new Field();
    Player[] players = {new Human(), new AI()};
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] args) {
        new TicTacToeWithObjects().go();
    }

    void go() {
        field.print();
        while (!field.isOver()) {
            for (Player player : players) {
                player.turn();
                field.print();
                if (field.isWin(player.getDot())) {
                    System.out.println(player.getClass().getSimpleName() + " WON");
                    field.setOver();
                }
                if (field.isFull()) {
                    System.out.println(DRAW_MSG);
                    field.setOver();
                }
            }
        }
    }

    class Field {
        final int FIELD_SIZE = 3;
        final char EMPTY_DOT = '.';
        char[][] field = new char[FIELD_SIZE][FIELD_SIZE];
        boolean gameOver = false;

        Field() {
            for (int i = 0; i < FIELD_SIZE; i++)
                for (int j = 0; j < FIELD_SIZE; j++)
                    field[i][j] = EMPTY_DOT;
        }

        void setOver() { gameOver = true; }

        boolean isOver() { return gameOver; }

        int getSize() { return FIELD_SIZE; }

        void setDot(int x, int y, char ch) { field[x][y] = ch; }

        boolean isCellEmpty(int x, int y) {
            if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1) return false;
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
            for (int i = 0; i < FIELD_SIZE; i++) {
                if (field[i][0] == ch && field[i][1] == ch && field[i][2] == ch) return true;
                if (field[0][i] == ch && field[1][i] == ch && field[2][i] == ch) return true;
            }
            // checking diagonals
            if(field[0][0] == ch && field[1][1] == ch && field[2][2] == ch) return true;
            if(field[2][0] == ch && field[1][1] == ch && field[0][2] == ch) return true;
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

    abstract class Player {
        char DOT;
        abstract void turn();
        char getDot() { return DOT; }
    }

    class Human extends Player {

        Human() { DOT = 'x'; }

        @Override
        void turn() {
            int x, y;
            do {
                System.out.println("Enter coordinates X Y (1-"+field.getSize()+"):");
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } while (!field.isCellEmpty(x, y));
            field.setDot(x, y, DOT);
        }
    }

    class AI extends Player {

        AI() { DOT = 'o'; }

        @Override
        void turn() {
            System.out.println("AI made its turn");
            int x, y;
            do {
                x = rand.nextInt(field.getSize());
                y = rand.nextInt(field.getSize());
            } while (!field.isCellEmpty(x, y));
            field.setDot(x, y, DOT);
        }
    }
}