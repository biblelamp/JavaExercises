/**
 * Java. Game TicTacToe with GUI
 *
 * @author Sergey Iryupin
 * @version 0.2 dated September 17, 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*; // for Graphics2D

class GameTicTacToe extends JFrame {

    final String TITLE_OF_PROGRAM = "Tic Tac Toe";
    final int START_LOCATION = 200;
    final int WINDOW_SIZE = 300;
    final int WINDOW_DX = 7;
    final int WINDOW_DY = 55;
    final int FIELD_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;
    char[][] field = new char[FIELD_SIZE][FIELD_SIZE];
    final char PLAYER_DOT = 'x';
    final char AI_DOT = 'o';
    final char EMPTY_DOT = '.';
    Canvas canvas;
    Random rand = new Random();
    boolean gameOver;

    public static void main(String[] args) {
        new GameTicTacToe();
    }

    GameTicTacToe() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);
        setResizable(false);
        // panel for painting
        canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX() / CELL_SIZE;
                int y = e.getY() / CELL_SIZE;
                if (isCellEmpty(x, y) && !gameOver) {
                    field[x][y] = PLAYER_DOT;
                    canvas.repaint();
                    if (checkWin(PLAYER_DOT)) {
                        System.out.println("YOU WON!");
                        gameOver = true;
                    } else {
                        if (isFieldFull()) {
                            System.out.println("Sorry, draft...");
                            gameOver = true;
                        } else {
                            aiTurn();
                            canvas.repaint();
                            if (checkWin(AI_DOT)) {
                                System.out.println("COMPUTER WON!");
                                gameOver = true;
                            } else {
                                if (isFieldFull()) {
                                    System.out.println("Sorry, draft...");
                                    gameOver = true;
                                }
                            }
                        }
                    }
                }
            }
        });
        // panel for buttons
        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout());
        JButton start = new JButton("New game");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initField();
                canvas.repaint();
            }
        });
        JButton exit = new JButton("Exit game");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bp.add(start);
        bp.add(exit);
        // add both panels
        add(BorderLayout.CENTER, canvas);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
        initField();
    }

    void initField() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                field[i][j] = EMPTY_DOT;
        gameOver = false;
    }

    boolean isCellEmpty(int x, int y) {
        if (field[x][y] == EMPTY_DOT) return true;
        return false;
    }

    void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(FIELD_SIZE);
            y = rand.nextInt(FIELD_SIZE);
        } while (!isCellEmpty(x, y));
        field[x][y] = AI_DOT;
    }

    boolean isFieldFull() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }


    boolean checkWin(char dot) {
        // check horizontals
        if (field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
        if (field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;
        // check verticals
        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
        if (field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
        if (field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;
        // check diagonals
        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
        if (field[2][0] == dot && field[1][1] == dot && field[0][2] == dot) return true;
        return false;
    }

    class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < FIELD_SIZE - 1; i++) {
                g.drawLine(0, (i + 1)*CELL_SIZE, WINDOW_SIZE, (i + 1)*CELL_SIZE);
                g.drawLine((i + 1)*CELL_SIZE, 0, (i + 1)*CELL_SIZE, WINDOW_SIZE);
            }
            Graphics2D g2 = (Graphics2D) g; // use Graphics2D
            g2.setStroke(new BasicStroke(5)); // 
            for (int y = 0; y < FIELD_SIZE; y++)
                for (int x = 0; x < FIELD_SIZE; x++) {
                    if (field[x][y] == PLAYER_DOT) {
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