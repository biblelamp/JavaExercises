/**
 * Java. Classic Game Tetris
 *
 * @author Sergey Iryupin
 * @version 0.3.7 dated September 20, 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class GameTetris extends JFrame {

    final String TITLE_OF_PROGRAM = "Tetris";
    final int BLOCK_SIZE = 25; // size of one block
    final int ARC_RADIUS = 6;
    final int FIELD_WIDTH = 10; // size game field in block
    final int FIELD_HEIGHT = 18;
    final int START_LOCATION = 180;
    final int FIELD_DX = 7; // determined experimentally
    final int FIELD_DY = 26;
    final int LEFT = 37; // key codes
    final int UP = 38;
    final int RIGHT = 39;
    final int DOWN = 40;
    final int SHOW_DELAY = 400; // delay for animation
    final int[][][] SHAPES = {
        {{0,0,0,0}, {1,1,1,1}, {0,0,0,0}, {0,0,0,0}, {4, 0x00f0f0}}, // I
        {{0,0,0,0}, {0,1,1,0}, {0,1,1,0}, {0,0,0,0}, {4, 0xf0f000}}, // O
        {{1,0,0,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0}, {3, 0x0000f0}}, // J
        {{0,0,1,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0}, {3, 0xf0a000}}, // L
        {{0,1,1,0}, {1,1,0,0}, {0,0,0,0}, {0,0,0,0}, {3, 0x00f000}}, // S
        {{1,1,1,0}, {0,1,0,0}, {0,0,0,0}, {0,0,0,0}, {3, 0xa000f0}}, // T
        {{1,1,0,0}, {0,1,1,0}, {0,0,0,0}, {0,0,0,0}, {3, 0xf00000}}  // Z
    };
    final int[] SCORES = {100, 300, 700, 1500};
    int gameScore = 0;
    int[][] mine = new int[FIELD_HEIGHT + 1][FIELD_WIDTH]; // mine/glass
    JFrame frame;
    Canvas canvas = new Canvas();
    Random random = new Random();
    Figure figure = new Figure();
    boolean gameOver = false;
    final int[][] GAME_OVER_MSG = {
        {0,1,1,0,0,0,1,1,0,0,0,1,0,1,0,0,0,1,1,0},
        {1,0,0,0,0,1,0,0,1,0,1,0,1,0,1,0,1,0,0,1},
        {1,0,1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1},
        {1,0,0,1,0,1,0,0,1,0,1,0,1,0,1,0,1,0,0,0},
        {0,1,1,0,0,1,0,0,1,0,1,0,1,0,1,0,0,1,1,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,1,0,0,1,0,0,1,0,0,1,1,0,0,1,1,1,0,0},
        {1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,0},
        {1,0,0,1,0,1,0,1,0,0,1,1,1,1,0,1,1,1,0,0},
        {1,0,0,1,0,1,1,0,0,0,1,0,0,0,0,1,0,0,1,0},
        {0,1,1,0,0,1,0,0,0,0,0,1,1,0,0,1,0,0,1,0}};

    public static void main(String[] args) {
        new GameTetris().go();
    }

    GameTetris() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, FIELD_WIDTH * BLOCK_SIZE + FIELD_DX, FIELD_HEIGHT * BLOCK_SIZE + FIELD_DY);
        setResizable(false);
        canvas.setBackground(Color.black); // define the background color
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!gameOver) {
                    if (e.getKeyCode() == DOWN) figure.drop();
                    if (e.getKeyCode() == UP) figure.rotate();
                    if (e.getKeyCode() == LEFT || e.getKeyCode() == RIGHT) figure.move(e.getKeyCode());
                }
                canvas.repaint();
            }
        });
        add(BorderLayout.CENTER, canvas);
        setVisible(true);
        Arrays.fill(mine[FIELD_HEIGHT], 1); // create a ground for mines
    }

    void go() { // main loop of game
        while (!gameOver) {
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (Exception e) { e.printStackTrace(); }
            canvas.repaint();
            checkFilling();
            if (figure.isTouchGround()) {
                figure.leaveOnTheGround();
                figure = new Figure();
                gameOver = figure.isCrossGround(); // Is there space for a new figure?
            } else
                figure.stepDown();
        }
    }

    void checkFilling() { // check filling rows
        int row = FIELD_HEIGHT - 1;
        int countFillRows = 0;
        while (row > 0) {
            int filled = 1;
            for (int col = 0; col < FIELD_WIDTH; col++)
                filled *= Integer.signum(mine[row][col]);
            if (filled > 0) {
                countFillRows++;
                for (int i = row; i > 0; i--) System.arraycopy(mine[i-1], 0, mine[i], 0, FIELD_WIDTH);
            } else
                row--;
        }
        if (countFillRows > 0) {
            gameScore += SCORES[countFillRows - 1];
            setTitle(TITLE_OF_PROGRAM + " : " + gameScore);
        }
    }

    class Figure {
        private ArrayList<Block> figure = new ArrayList<Block>();
        private int[][] shape = new int[4][4];
        private int type, size, color;
        private int x = 3, y = 0; // starting left up corner

        Figure() {
            type = random.nextInt(SHAPES.length);
            size = SHAPES[type][4][0];
            color = SHAPES[type][4][1];
            if (size == 4) y = -1;
            for (int i = 0; i < size; i++)
                System.arraycopy(SHAPES[type][i], 0, shape[i], 0, SHAPES[type][i].length);
            createFromShape();
        }

        void createFromShape() {
            for (int x = 0; x < size; x++)
                for (int y = 0; y < size; y++)
                    if (shape[y][x] == 1) figure.add(new Block(x + this.x, y + this.y));
        }

        boolean isTouchGround() {
            for (Block block : figure) if (mine[block.getY() + 1][block.getX()] > 0) return true;
            return false;
        }

        boolean isCrossGround() {
            for (Block block : figure) if (mine[block.getY()][block.getX()] > 0) return true;
            return false;
        }

        void leaveOnTheGround() {
            for (Block block : figure) mine[block.getY()][block.getX()] = color;
        }

        boolean isTouchWall(int direction) {
            for (Block block : figure) {
                if (direction == LEFT && (block.getX() == 0 || mine[block.getY()][block.getX() - 1] > 0)) return true;
                if (direction == RIGHT && (block.getX() == FIELD_WIDTH - 1 || mine[block.getY()][block.getX() + 1] > 0)) return true;
            }
            return false;
        }

        void move(int direction) {
            if (!isTouchWall(direction)) {
                int dx = direction - 38; // LEFT = -1, RIGHT = 1
                for (Block block : figure) block.setX(block.getX() + dx);
                x += dx;
            }
        }

        void stepDown() {
            for (Block block : figure) block.setY(block.getY() + 1);
            y++;
        }

        void drop() { while (!isTouchGround()) stepDown(); }

        boolean isWrongPosition() {
            for (int x = 0; x < size; x++)
                for (int y = 0; y < size; y++)
                    if (shape[y][x] == 1) {
                        if (y + this.y < 0) return true;
                        if (x + this.x < 0 || x + this.x > FIELD_WIDTH - 1) return true;
                        if (mine[y + this.y][x + this.x] > 0) return true;
                    }
            return false;
        }

        void rotateShape(int direction) {
            for (int i = 0; i < size/2; i++)
                for (int j = i; j < size-1-i; j++)
                    if (direction == RIGHT) { // clockwise
                        int tmp = shape[size-1-j][i];
                        shape[size-1-j][i] = shape[size-1-i][size-1-j];
                        shape[size-1-i][size-1-j] = shape[j][size-1-i];
                        shape[j][size-1-i] = shape[i][j];
                        shape[i][j] = tmp;
                    } else { // counterclockwise
                        int tmp = shape[i][j];
                        shape[i][j] = shape[j][size-1-i];
                        shape[j][size-1-i] = shape[size-1-i][size-1-j];
                        shape[size-1-i][size-1-j] = shape[size-1-j][i];
                        shape[size-1-j][i] = tmp;
                }
        }

        void rotate() {
            rotateShape(RIGHT);
            if (!isWrongPosition()) {
                figure.clear();
                createFromShape();
            } else
                rotateShape(LEFT);
        }

        void paint(Graphics g) {
            for (Block block : figure) block.paint(g, color);
        }
    }

    class Block { // building element for Figure
        private int x, y;

        public Block(int x, int y) {
            setX(x);
            setY(y);
        }

        void setX(int x) { this.x = x; }
        void setY(int y) { this.y = y; }

        int getX() { return x; }
        int getY() { return y; }

        void paint(Graphics g, int color) {
            g.setColor(new Color(color));
            g.drawRoundRect(x*BLOCK_SIZE+1, y*BLOCK_SIZE+1, BLOCK_SIZE-2, BLOCK_SIZE-2, ARC_RADIUS, ARC_RADIUS);
        }
    }

    class Canvas extends JPanel { // my canvas for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int x = 0; x < FIELD_WIDTH; x++)
                for (int y = 0; y < FIELD_HEIGHT; y++) {
                    if (x < FIELD_WIDTH - 1 && y < FIELD_HEIGHT - 1) {
                        g.setColor(Color.lightGray);
                        g.drawLine((x+1)*BLOCK_SIZE-2, (y+1)*BLOCK_SIZE, (x+1)*BLOCK_SIZE+2, (y+1)*BLOCK_SIZE);
                        g.drawLine((x+1)*BLOCK_SIZE, (y+1)*BLOCK_SIZE-2, (x+1)*BLOCK_SIZE, (y+1)*BLOCK_SIZE+2);
                    }
                    if (mine[y][x] > 0) {
                        g.setColor(new Color(mine[y][x]));
                        g.fill3DRect(x*BLOCK_SIZE+1, y*BLOCK_SIZE+1, BLOCK_SIZE-1, BLOCK_SIZE-1, true);
                    }
                }
            if (gameOver) {
                g.setColor(Color.white);
                for (int y = 0; y < GAME_OVER_MSG.length; y++)
                    for (int x = 0; x < GAME_OVER_MSG[y].length; x++)
                        if (GAME_OVER_MSG[y][x] == 1) g.fill3DRect(x*11+18, y*11+160, 10, 10, true);
            } else
                figure.paint(g);
        }
    }
}