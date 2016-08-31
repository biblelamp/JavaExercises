/**
 * Java. Classic Game Tetris
 *
 * @author Sergey Iryupin
 * @version 0.2.3 dated 31 Aug 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameTetris {

    final String TITLE_OF_PROGRAM = "Tetris";
    final int BLOCK_SIZE = 25; // size of one block
    final int ARC_RADIUS = 6;
    final int FIELD_WIDTH = 10; // size game field in block
    final int FIELD_HEIGHT = 20;
    final int START_LOCATION = 200;
    final int FIELD_DX = 6; // determined experimentally
    final int FIELD_DY = 28;
    final int LEFT = 37; // key codes
    final int UP = 38;
    final int RIGHT = 39;
    final int DOWN = 40;
    final int SHOW_DELAY = 400; // delay for animation
    final int[] SCORES = {100, 300, 700, 1500};
    int gameScore = 0;
    int[][][] shapes = {
        {{0,0,0,0}, {1,1,1,1}, {0,0,0,0}, {0,0,0,0}, {4}}, // I
        {{0,0,0,0}, {0,1,1,0}, {0,1,1,0}, {0,0,0,0}, {4}}, // O
        {{1,0,0,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0}, {3}}, // J
        {{0,0,1,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0}, {3}}, // L
        {{0,1,1,0}, {1,1,0,0}, {0,0,0,0}, {0,0,0,0}, {3}}, // S
        {{1,1,1,0}, {0,1,0,0}, {0,0,0,0}, {0,0,0,0}, {3}}, // T
        {{1,1,0,0}, {0,1,1,0}, {0,0,0,0}, {0,0,0,0}, {3}}  // Z
    };
    boolean[][] mine = new boolean[FIELD_HEIGHT + 1][FIELD_WIDTH];
    JFrame frame;
    Canvas canvasPanel = new Canvas();
    Random random = new Random();
    Figure figure = new Figure();
    boolean gameOver = false;
    final int[][] GAME_OVER_MSG = {
		{0,1,1,0,0,0,1,1,0,0,0,1,0,1,0,0,0,1,1,0},
		{1,0,0,0,0,1,0,0,1,0,0,1,0,1,0,0,1,0,0,1},
		{1,0,1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1},
		{1,0,0,1,0,1,0,0,1,0,1,0,1,0,1,0,1,0,0,0},
		{0,1,1,0,0,1,0,0,1,0,1,0,1,0,1,0,0,1,1,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,1,1,0,0,1,0,0,1,0,0,1,1,0,0,1,1,1,0,0},
		{1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,0},
		{1,0,0,1,0,1,0,1,0,0,1,1,1,1,0,1,1,1,0,0},
		{1,0,0,1,0,1,1,0,0,0,1,0,0,0,0,1,0,0,1,0},
		{0,1,1,0,0,1,0,0,0,0,0,1,1,0,0,1,0,0,1,0}
	};

    public static void main(String[] args) {
        new GameTetris().go();
    }

    void go() {
        frame = new JFrame(TITLE_OF_PROGRAM);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_WIDTH * BLOCK_SIZE + FIELD_DX, FIELD_HEIGHT * BLOCK_SIZE + FIELD_DY);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);

        canvasPanel.setBackground(Color.white); // define the background color

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == DOWN) {
                    figure.drop();
                }
                if (e.getKeyCode() == UP) {
                    figure.rotate();
                }
                if ((e.getKeyCode() == LEFT) || (e.getKeyCode() == RIGHT)) {
                    figure.move(e.getKeyCode());
                }
                canvasPanel.repaint();
            }
        });
        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.setVisible(true);

        Arrays.fill(mine[FIELD_HEIGHT], true); // create a ground for mines

        // main loop of game
        while (!gameOver) {
            if (figure.isTouchGround()) {
                figure.leaveOnTheGround();
                figure = new Figure();
                gameOver = figure.isTouchGround();
            } else {
                figure.stepDown();
            }
            checkFilling();
            canvasPanel.repaint();
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    void checkFilling() { // check filling rows
        int row = FIELD_HEIGHT - 1;
        int countFillRows = 0;
        while (row > 0) {
            boolean filled = true;
            for (int col = 0; col < FIELD_WIDTH; col++) {
                filled &= mine[row][col];
            }
            if (filled) {
                countFillRows++;
                for (int i = row; i>0; i--) {
                    System.arraycopy(mine[i-1], 0, mine[i], 0, FIELD_WIDTH);
                }
            } else {
                row--;
            }
        }
        if (countFillRows > 0) {
            gameScore += SCORES[countFillRows - 1];
            frame.setTitle(TITLE_OF_PROGRAM + " : " + gameScore);
        }
    }

    class Figure {
        private ArrayList<Block> figure = new ArrayList<Block>();
        private int type, size;
        private int x = 3, y = 0; // starting left up corner

        Figure() {
            type = random.nextInt(shapes.length);
            size = shapes[type][4][0];
            createFromShapes();
        }

        void createFromShapes() {
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    if (shapes[type][y][x] == 1) {
                        figure.add(new Block(x + this.x, y + this.y));
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
                if ((direction == LEFT) && (((block.getX() == 0) || (mine[block.getY()][block.getX() - 1])))) {
                    return true;
                }
                if ((direction == RIGHT) && (((block.getX() == FIELD_WIDTH - 1) || (mine[block.getY()][block.getX() + 1])))) {
                    return true;
                }
            }
            return false;
        }

        void move(int direction) {
            if (!isTouchWall(direction)) {
                int dx = (direction == LEFT)? -1 : (direction == RIGHT)? 1 : 0;
                for (Block block : figure) {
                    block.setX(block.getX() + dx);
                }
                x += dx;
            }
        }

        void stepDown() {
            for (Block block : figure) {
                block.setY(block.getY() + 1);
            }
            y++;
        }

        void drop() {
            while (!isTouchGround()) {
                stepDown();
            }
        }

        void rotate() {
            for (int i = 0; i < size/2; i++) {
                for (int j = i; j < size-1-i; j++) {
                    int tmp = shapes[type][size-1-j][i];
                    shapes[type][size-1-j][i] = shapes[type][size-1-i][size-1-j];
                    shapes[type][size-1-i][size-1-j] = shapes[type][j][size-1-i];
                    shapes[type][j][size-1-i] = shapes[type][i][j];
                    shapes[type][i][j] = tmp;
                }
            }
            figure.clear();
            createFromShapes();
        }

        void paint(Graphics g) {
            for (Block block : figure) {
                block.paint(g);
            }
        }
    }

    class Block {
        private int x, y;

        public Block(int x, int y) {
            setX(x);
            setY(y);
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
                        g.fill3DRect(x*BLOCK_SIZE+1, y*BLOCK_SIZE+1, BLOCK_SIZE-1, BLOCK_SIZE-1, true);
                    }
                }
            }
            figure.paint(g);
            if (gameOver) {
				g.setColor(Color.magenta);
				for (int x = 0; x < 20; x++)
					for (int y = 0; y < 12; y++)
						if (GAME_OVER_MSG[y][x] == 1) g.fill3DRect(x*10+25, y*10+200, 9, 9, true);
            }
        }
    }
}