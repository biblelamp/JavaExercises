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
    final int MAP_SIZE = 3;
    final int CELL_SIZE = WINDOW_SIZE / MAP_SIZE;
    char[][] map = new char[MAP_SIZE][MAP_SIZE];
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
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
                    map[x][y] = DOT_X;
                    canvas.repaint();
                    if (checkWin(DOT_X)) {
                        System.out.println("YOU WON!");
                        gameOver = true;
                    } else {
                        if (isMapFull()) {
                            System.out.println("Sorry, draft...");
                            gameOver = true;
                        } else {
                            aiTurn();
                            canvas.repaint();
                            if (checkWin(DOT_O)) {
                                System.out.println("COMPUTER WON!");
                                gameOver = true;
                            } else {
                                if (isMapFull()) {
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
                initMap();
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
        initMap();
    }

    void initMap() {
        for (int i = 0; i < MAP_SIZE; i++)
            for (int j = 0; j < MAP_SIZE; j++)
                map[i][j] = DOT_EMPTY;
        gameOver = false;
    }

    boolean isCellEmpty(int x, int y) {
        return map[x][y] == DOT_EMPTY;
    }

    void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(MAP_SIZE);
            y = rand.nextInt(MAP_SIZE);
        } while (!isCellEmpty(x, y));
        map[x][y] = DOT_O;
    }

    boolean isMapFull() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    boolean checkWin(char dot) {
        // check horizontals and verticals
        for (int i = 0; i < MAP_SIZE; i++)
            if ((map[i][0] == dot && map[i][1] == dot && map[i][2] == dot) ||
                (map[0][i] == dot && map[1][i] == dot && map[2][i] == dot))
                return true;
        // check diagonals
        if ((map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) ||
            (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot))
            return true;
        return false;
    }

    class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.lightGray);
            for (int i = 0; i < MAP_SIZE - 1; i++) {
                g.drawLine(0, (i + 1)*CELL_SIZE, WINDOW_SIZE, (i + 1)*CELL_SIZE);
                g.drawLine((i + 1)*CELL_SIZE, 0, (i + 1)*CELL_SIZE, WINDOW_SIZE);
            }
            Graphics2D g2 = (Graphics2D) g;   // use Graphics2D
            g2.setStroke(new BasicStroke(5)); // set line width
            for (int y = 0; y < MAP_SIZE; y++)
                for (int x = 0; x < MAP_SIZE; x++) {
                    if (map[x][y] == DOT_X) {
                        g.setColor(Color.blue);
                        g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4));
                        g2.draw(new Line2D.Float(x*CELL_SIZE+CELL_SIZE/4, (y+1)*CELL_SIZE-CELL_SIZE/4, (x+1)*CELL_SIZE-CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4));
                    }
                    if (map[x][y] == DOT_O) {
                        g.setColor(Color.red);
                        g2.draw(new Ellipse2D.Float(x*CELL_SIZE+CELL_SIZE/4, y*CELL_SIZE+CELL_SIZE/4, CELL_SIZE/2, CELL_SIZE/2));
                    }
                }
        }
    }
}