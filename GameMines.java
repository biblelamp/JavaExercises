/**
 * Java. Classic Game Minesweeper
 *
 * @author Sergey Iryupin
 * @version 0.1.1 dated 09 Sep 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameMines {

    final String TITLE_OF_PROGRAM = "Mines";
    final int BLOCK_SIZE = 30; // size of one block
    final int FIELD_SIZE = 9; // in blocks
    final int FIELD_DX = 6; // determined experimentally
    final int FIELD_DY = 28;
    final int MOUSE_BUTTON_LEFT = 1; // for mouse listener
    final int MOUSE_BUTTON_RIGHT = 3;
    final int START_LOCATION = 200;
    final int NUMBER_OF_MINES = 10;
    final int[] COLOR_OF_NUMBERS = {0x0000FF, 0x008000, 0xFF0000, 0x800000};
    Cell[][] field = new Cell[FIELD_SIZE][FIELD_SIZE];
    JFrame frame;
    Canvas canvasPanel = new Canvas();
    Random random = new Random();
    int countOpenedCells = 0;
    boolean youWon = false;
    boolean bangMine = false;
    int bangX, bangY;

    public static void main(String[] args) {
        new GameMines().go();
    }

    void go() {
        frame = new JFrame(TITLE_OF_PROGRAM);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_SIZE * BLOCK_SIZE + FIELD_DX, FIELD_SIZE * BLOCK_SIZE + FIELD_DY);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);
        canvasPanel.setBackground(Color.white);
        canvasPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int x = e.getX()/BLOCK_SIZE;
                int y = e.getY()/BLOCK_SIZE;
                if (e.getButton() == MOUSE_BUTTON_LEFT && !bangMine && !youWon) // left button mouse
                    if (field[y][x].isNotOpen()) {
                        field[y][x].open();
                        youWon = countOpenedCells == FIELD_SIZE*FIELD_SIZE - NUMBER_OF_MINES; // winning check
                        if (bangMine) {
                            bangX = x;
                            bangY = y;
                        }
                }
                if (e.getButton() == MOUSE_BUTTON_RIGHT) field[y][x].inverseFlag(); // right button mouse
                canvasPanel.repaint();
            }
        });
        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.setVisible(true);
        initField();
    }

    void initField() {
        int countMines = 0;
        int x, y;
        // create cells for the field
        for (x = 0; x < FIELD_SIZE; x++)
            for (y = 0; y < FIELD_SIZE; y++)
                field[y][x] = new Cell();
        // to mine field
        while (countMines < NUMBER_OF_MINES) {
            do {
                x = random.nextInt(FIELD_SIZE);
                y = random.nextInt(FIELD_SIZE);
            } while (field[y][x].isMined());
            field[y][x].mine();
            countMines++;
        }
        // to count dangerous neighbors
        for (x = 0; x < FIELD_SIZE; x++)
            for (y = 0; y < FIELD_SIZE; y++)
                if (!field[y][x].isMined()) {
                    int count = 0;
                    for (int dx = -1; dx < 2; dx++)
                        for (int dy = -1; dy < 2; dy++) {                    
                            int nX = x + dx;
                            int nY = y + dy;
                            if (nX < 0 || nY < 0 || nX > FIELD_SIZE - 1 || nY > FIELD_SIZE - 1) {
                                nX = x;
                                nY = y;
                            }
                            count += (field[nY][nX].isMined()) ? 1 : 0;
                        }
                    field[y][x].setCountBomb(count);
                }
    }

    class Cell {
        private boolean isOpen, isMine, isFlag;
        private int countBombNear;

        Cell() {
            isOpen = isMine = isFlag = false;
            countBombNear = 0;
        }

        void open() {
            isOpen = true;
            bangMine = isMine;
            if (!isMine) countOpenedCells++;
        }

        void mine() {
            isMine = true;
        }

        void setCountBomb(int count) {
            countBombNear = count;
        }

        boolean isNotOpen() {
            return !isOpen;
        }

        boolean isMined() {
            return isMine;
        }

        void inverseFlag() {
            isFlag = !isFlag;
        }

        void paint(Graphics g, int x, int y) {
            if (!isOpen) {
                if ((bangMine || youWon) && isMine) {
                    g.setColor(Color.black);
                    g.drawOval(x*BLOCK_SIZE + 5, y*BLOCK_SIZE + 5, BLOCK_SIZE - 10, BLOCK_SIZE - 10);
                    g.fillOval(x*BLOCK_SIZE + 8, y*BLOCK_SIZE + 8, BLOCK_SIZE - 16, BLOCK_SIZE - 16);
                } else {
                    g.setColor(Color.lightGray);
                    g.fill3DRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE - 1, BLOCK_SIZE - 1, true);
                    if (isFlag) {
                        g.setColor(Color.gray);
                        g.setFont(new Font("", Font.BOLD, BLOCK_SIZE - 4));
                        g.drawString("?", x*BLOCK_SIZE + 7, y*BLOCK_SIZE + 24);
                    }
                }
            } else {
                g.setColor(Color.lightGray);
                g.drawRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                if (isMine) {
                    g.setColor(bangMine? Color.red : Color.black);
                    g.drawOval(x*BLOCK_SIZE + 5, y*BLOCK_SIZE + 5, BLOCK_SIZE - 10, BLOCK_SIZE - 10);
                    g.fillOval(x*BLOCK_SIZE + 8, y*BLOCK_SIZE + 8, BLOCK_SIZE - 16, BLOCK_SIZE - 16);
                } else {
                    if (countBombNear > 0) {
                        g.setColor(new Color(COLOR_OF_NUMBERS[countBombNear - 1]));
                        g.setFont(new Font("", Font.BOLD, BLOCK_SIZE));
                        g.drawString(Integer.toString(countBombNear), x*BLOCK_SIZE + 8, y*BLOCK_SIZE + 26);
                    }
                }
            }
        }
    }

    public class Canvas extends JPanel { // my canvas for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int x = 0; x < FIELD_SIZE; x++)
                for (int y = 0; y < FIELD_SIZE; y++)
                    field[y][x].paint(g, x, y);
        }
    }
}