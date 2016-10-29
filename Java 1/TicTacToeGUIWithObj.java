/*
 * Java 1. Game Tic Tac Toe GUI with Objects
 *
 * @author Sergey Iryupin
 * @version 0.1 dated October 29, 2016
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*; // for Graphics2D
import javax.swing.*;
import java.util.*;

class TicTacToeGUIWithObj extends JFrame {

    final String TITLE_OF_PROGRAM = "Tic Tac Toe";
    final int START_POSITION = 300;
    final int WINDOW_SIZE = 300;
    final int WINDOW_DX = 17;
    final int WINDOW_DY = 65;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
    final String BTN_INIT = "New game";
    final String BTN_EXIT = "Exit";
    final char HUMAN_DOT = 'x';
    final char AI_DOT = 'o';
    Random rand = new Random();
    Canvas canvas = new Canvas();
    Field field = new Field();
    Human human = new Human(HUMAN_DOT);
    AI ai = new AI(AI_DOT);

    public static void main(String args[]) {
        new TicTacToeGUIWithObj();
    }
    
    TicTacToeGUIWithObj() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_POSITION, START_POSITION, WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);

        JButton init = new JButton(BTN_INIT);
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                field = new Field();
                canvas.repaint();
            }
        });
        JButton exit = new JButton(BTN_EXIT);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                //dispose();
            }
        });
        JPanel bp = new JPanel();
        canvas.setBackground(Color.white);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX()/CELL_SIZE;
                int y = e.getY()/CELL_SIZE;
                human.turn(x, y);
                canvas.repaint();
            }
        });

        bp.setLayout(new GridLayout());
        bp.add(init);
        bp.add(exit);

        setLayout(new BorderLayout());
        add(bp, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);
        setVisible(true);
    }

    class Field {
        final char EMPTY_DOT = '.';
        char[][] field = new char[FIELD_SIZE][FIELD_SIZE];
        boolean gameOver;

        Field() {
            for (int i = 0; i < FIELD_SIZE; i++)
                for (int j = 0; j < FIELD_SIZE; j++)
                    field[i][j] = EMPTY_DOT;
            gameOver = false;
        }

        boolean isGameOver() { return gameOver; }

        void setDot(int x, int y, char ch) { // set dot and check fill and win
            field[x][y] = ch;
            if (isFull()) {
                System.out.println("Draw...");
                gameOver = true;
            }
            if (isWin(human.getDot())) {
                System.out.println("YOU WON!");
                gameOver = true;
            }
            if (isWin(ai.getDot())) {
                System.out.println("AI WON!");
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

        void paint(Graphics g) {
            g.setColor(Color.lightGray);
            for (int i = 1; i < FIELD_SIZE; i++) {
                g.drawLine(0, i*CELL_SIZE, WINDOW_SIZE, i*CELL_SIZE);
                g.drawLine(i*CELL_SIZE, 0, i*CELL_SIZE, WINDOW_SIZE);
            }
            Graphics2D g2 = (Graphics2D) g; // use Graphics2D
            g2.setStroke(new BasicStroke(5));
            for (int y = 0; y < FIELD_SIZE; y++) {
                for (int x = 0; x < FIELD_SIZE; x++) {
                    if (field[x][y] == human.getDot()) {
                        g.setColor(Color.blue);
                        g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4));
                        g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4));
                    }
                    if (field[x][y] == ai.getDot()) {
                        g.setColor(Color.red);
                        g2.draw(new Ellipse2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, CELL_SIZE/2, CELL_SIZE/2));
                    }
                }
            }
        }
    }

    abstract class Player {
        char DOT;
        void turn(int x, int y) {}
        void turn() {}
        char getDot() { return DOT; }
    }


    class Human extends Player {

        Human(char ch) { DOT = ch; }

        @Override
        void turn(int x, int y) {
            if (field.isCellEmpty(x, y)) {
                if (!field.isGameOver()) field.setDot(x, y, DOT);
                if (!field.isGameOver()) ai.turn();
            }
        }
    }

    class AI extends Player {

        AI(char ch) { DOT = ch; }

        @Override
        void turn() {
            int x, y;
            do {
                x = rand.nextInt(FIELD_SIZE);
                y = rand.nextInt(FIELD_SIZE);
            } while (!field.isCellEmpty(x, y));
            field.setDot(x, y, DOT);
        }
    }

    class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            field.paint(g);
        }
    }
}