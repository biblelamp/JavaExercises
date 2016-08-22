/**
 * Java. Classic Game Tetris
 *
 * @author Sergey Iryupin
 * @version 0.1 dated 22 Aug 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameTetris {

    final String TITLE_OF_PROGRAM = "Game Tetris";
    final String GAME_OVER_MSG = "GAME OVER";
    final int BLOCK_SIZE = 25; // size of one block
    final int ARC_RADIUS = 6;
    final int FIELD_WIDTH = 10; // size game field in block
    final int FIELD_HEIGHT = 18;
    final int START_LOCATION = 200;
    final int FIELD_DX = 6; // determined experimentally
    final int FIELD_DY = 28;
    final int LEFT = 37; // key codes
    final int UP = 38;
    final int RIGHT = 39;
    final int DOWN = 40;
    final int SHOW_DELAY = 200; // delay for animation
    final int[][][] shapes = {
        {{1,1,1,1}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0}}, // I
        {{1,1,1,0}, {0,1,0,0}, {0,0,0,0}, {0,0,0,0}}, // T
        {{0,1,1,0}, {0,1,1,0}, {0,0,0,0}, {0,0,0,0}}, // O
        {{1,1,1,0}, {1,0,0,0}, {0,0,0,0}, {0,0,0,0}}, // L
        {{1,1,1,0}, {0,0,1,0}, {0,0,0,0}, {0,0,0,0}}, // J
        {{0,1,1,0}, {1,1,0,0}, {0,0,0,0}, {0,0,0,0}}, // S
        {{1,1,0,0}, {0,1,1,0}, {0,0,0,0}, {0,0,0,0}}, // Z
    };
    boolean[][] mine = new boolean[FIELD_HEIGHT + 1][FIELD_WIDTH];
    JFrame frame;
    Canvas canvasPanel;
    Figure figure;
    Random random = new Random();
    boolean gameOver = false;

    public static void main(String[] args) {
        new GameTetris().go();
    }

    void go() {
        frame = new JFrame(TITLE_OF_PROGRAM);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_WIDTH * BLOCK_SIZE + FIELD_DX, FIELD_HEIGHT * BLOCK_SIZE + FIELD_DY);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);

        canvasPanel = new Canvas();
        canvasPanel.setBackground(Color.white);
        
        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == UP) {
                    figure.rotate();
                }
                if ((e.getKeyCode() == LEFT) || (e.getKeyCode() == RIGHT)) {
                    figure.move(e.getKeyCode());
                }
                canvasPanel.repaint();
            }
        });

        frame.setVisible(true);

        // create a bottom for mines
        Arrays.fill(mine[FIELD_HEIGHT], true);

        figure = new Figure();

        // main loop of game
        while (!gameOver) {
            figure.fallDown();
            if (figure.isTouchGround()) {
                figure.leaveOnTheGround();
                figure = new Figure();
                gameOver = figure.isTouchGround();
            }
            canvasPanel.repaint();
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }

        JOptionPane.showMessageDialog(frame, GAME_OVER_MSG, TITLE_OF_PROGRAM, JOptionPane.ERROR_MESSAGE); // game over
    }

    class Figure {
        ArrayList<Block> figure = new ArrayList<Block>();
        int xLeftCorner = 3;
        int yLeftCorner = 1;

        Figure() {
            int type = random.nextInt(shapes.length);
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    if (shapes[type][y][x] == 1) {
                        figure.add(new Block(x + xLeftCorner, y + yLeftCorner));
                    }
                }
            }
        }

        boolean isTouchGround() {
            for (Block block : figure) {
                if (mine[block.getY() + 1][block.getX()]) {
                    return true;
                }
            }
            return false;
        }

        void leaveOnTheGround() {
            for (Block block : figure) {
                mine[block.getY()][block.getX()] = true;
            }
        }

        boolean isTouchWall(int direction) {
            for (Block block : figure) {
                if (((block.getX() == 0) && (direction == LEFT)) || ((block.getX() == FIELD_WIDTH - 1) && (direction == RIGHT))) {
                    return true;
                }
            }
            return false;
        }

        void move(int direction) {
            if (!isTouchWall(direction)) {
                for (int i = 0; i < figure.size(); i++) {
                    Block block = figure.get(i);
                    int dx = (direction == LEFT)? -1 : (direction == RIGHT)? 1 : 0;
                    block.setX(block.getX() + dx);
                    figure.set(i, block);
                }
            }
        }

        void fallDown() {
            for (int i = 0; i < figure.size(); i++) {
                Block block = figure.get(i);
                block.setY(block.getY() + 1);
                figure.set(i, block);
            }
        }

        void rotate() {
            //
        }

        void paint(Graphics g) {
            for (Block block : figure) {
                block.paint(g);
            }
        }
    }

    class Block {
        int x, y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() { return x; }
        int getY() { return y; }

        void setX(int x) { this.x = x; }
        void setY(int y) { this.y = y; }

        void paint(Graphics g) {
            g.setColor(Color.black);
            g.drawRoundRect(x*BLOCK_SIZE+1, y*BLOCK_SIZE+1, BLOCK_SIZE-2, BLOCK_SIZE-2, ARC_RADIUS, ARC_RADIUS);
        }
    }

    public class Canvas extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int x = 0; x < FIELD_WIDTH; x++) {
                for (int y = 0; y < FIELD_HEIGHT; y++) {
                    g.setColor(Color.lightGray);
                    g.drawLine((x+1)*BLOCK_SIZE-2, (y+1)*BLOCK_SIZE, (x+1)*BLOCK_SIZE+2, (y+1)*BLOCK_SIZE);
                    g.drawLine((x+1)*BLOCK_SIZE, (y+1)*BLOCK_SIZE-2, (x+1)*BLOCK_SIZE, (y+1)*BLOCK_SIZE+2);
                    if (mine[y][x]) {
                        g.setColor(Color.gray);
                        g.fillOval(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    }
                }
            }
            figure.paint(g);
        }
    }
}