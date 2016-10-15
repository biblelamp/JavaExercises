/**
 * Java. Game TicTacToe with objects
 *
 * @author Sergey Iryupin
 * @version 0.1 dated October 15, 2016
 */
import java.util.*;

class TicTacToeWithObjects {

    Field field = new Field();
    Human human = new Human();
    AI ai = new AI();
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] args) {
        new TicTacToeWithObjects().go();
    }

    void go() {
        field.print();
        while (true) {
            human.turn();
            field.print();
            if (field.isWin(human.getDot())) {
                System.out.println("You won!");
                break;
            }
            if (field.isFull()) {
                System.out.println("Sorry draw...");
                break;
            }
            ai.turn();
            System.out.println("AI made its turn");
            field.print();
            if (field.isWin(ai.getDot())) {
                System.out.println("AI won!");
                break;
            }
            if (field.isFull()) {
                System.out.println("Sorry draw...");
                break;
            }
        }
    }

    class Field {
        final int FIELD_SIZE = 3;
        final char EMPTY_DOT = '.';
        char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

        Field() {
            for (int i = 0; i < FIELD_SIZE; i++)
                for (int j = 0; j < FIELD_SIZE; j++)
                    field[i][j] = EMPTY_DOT;
        }

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

    class Human {
        final char DOT = 'x';

        char getDot() { return DOT; }

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

    class AI {
        final char DOT = 'o';

        char getDot() { return DOT; }

        void turn() {
            int x, y;
            do {
                x = rand.nextInt(field.getSize());
                y = rand.nextInt(field.getSize());
            } while (!field.isCellEmpty(x, y));
            field.setDot(x, y, DOT);
        }
    }
}