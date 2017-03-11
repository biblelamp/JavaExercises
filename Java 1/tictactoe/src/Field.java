/*
 * Java 1. Lesson 8. Game Tic Tac Toe
 * Class: Field
 *
 * @author Sergey Iryupin
 * @version 0.1 dated March 11, 2017
 */
import java.awt.*;
import java.awt.geom.*; // for Graphics2D

class Field {
    private final int FIELD_SIZE;
    private final int WINDOW_SIZE;
    private final int CELL_SIZE;
    private final char HUMAN_DOT;
    private final char AI_DOT;
    private final char EMPTY_DOT = '.';
    private final String MSG_DRAW = "Draw, sorry...";
    private final String MSG_HUMAN_WON = "YOU WON!";
    private final String MSG_AI_WON = "AI WON!";
    private char[][] field;
    private boolean gameOver;

    Field(int field_size, int window_size, char human_dot, char ai_dot) {
        FIELD_SIZE = field_size;
        WINDOW_SIZE = window_size;
        CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
        field = new char[FIELD_SIZE][FIELD_SIZE];
        HUMAN_DOT = human_dot;
        AI_DOT = ai_dot;
        init();
    }

    void init() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                field[i][j] = EMPTY_DOT;
        gameOver = false;
    }

    int getSize() { return FIELD_SIZE; }

    boolean isGameOver() { return gameOver; }

    void setDot(int x, int y, char ch) { // set dot and check fill and win
        field[x][y] = ch;
        if (isFull()) {
            System.out.println(MSG_DRAW);
            gameOver = true;
        }
        if (isWin(HUMAN_DOT)) {
            System.out.println(MSG_HUMAN_WON);
            gameOver = true;
        }
        if (isWin(AI_DOT)) {
            System.out.println(MSG_AI_WON);
            gameOver = true;
        }
    }

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

    public void paint(Graphics g) {
        g.setColor(Color.lightGray);
        for (int i = 1; i < FIELD_SIZE; i++) {
            g.drawLine(0, i*CELL_SIZE, WINDOW_SIZE, i*CELL_SIZE);
            g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, WINDOW_SIZE);
        }
        Graphics2D g2 = (Graphics2D) g; // use Graphics2D
        g2.setStroke(new BasicStroke(5));
        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                if (field[x][y] == HUMAN_DOT) {
                    g.setColor(Color.blue);
                    g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4));
                    g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4));
                }
                if (field[x][y] == AI_DOT) {
                    g.setColor(Color.red);
                    g2.draw(new Ellipse2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, CELL_SIZE/2, CELL_SIZE/2));
                }
            }
        }
    }
}