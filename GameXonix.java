/**
 * Java. Classic Game Xonix
 *
 * @author Sergey Iryupin
 * @version 0.1 dated September 23, 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class GameXonix extends JFrame {

    final String TITLE_OF_PROGRAM = "Xonix";
    final int POINT_SIZE = 10;
    final int FIELD_WIDTH = 640 / POINT_SIZE;
    final int FIELD_HEIGHT = 460 / POINT_SIZE;
    final int FIELD_DX = 6;
    final int FIELD_DY = 28;
    final int START_LOCATION = 200;
    final int LEFT = 37; // key codes
    final int UP = 38;
    final int RIGHT = 39;
    final int DOWN = 40;
    final int SHOW_DELAY = 60; // delay for animation
    final int COLOR_LAND = 0x00a8a8;
    final int COLOR_TRACK = 0x901290;
    int[][] field = new int[FIELD_WIDTH][FIELD_HEIGHT];
    Canvas canvas = new Canvas();
    Xonix xonix = new Xonix();

    public static void main(String[] args) {
        new GameXonix().go();
    }

    GameXonix() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, FIELD_WIDTH*POINT_SIZE + FIELD_DX, FIELD_HEIGHT*POINT_SIZE + FIELD_DY);
        setResizable(false);
        add(BorderLayout.CENTER, canvas);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() >= LEFT && e.getKeyCode() <= DOWN)
                    xonix.setDirection(e.getKeyCode());
            }
        });
        initField();
        setVisible(true);
    }

    void go() { // main loop of game
        while (true) {
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (Exception e) { e.printStackTrace(); }
            xonix.move();
            if (xonix.isSelfCrosed()) {
                clearTrack();
                xonix = new Xonix();
            }
            canvas.repaint();
        }
    }

    void initField() {
        for (int y = 0; y < FIELD_HEIGHT; y++)
            for (int x = 0; x < FIELD_WIDTH; x++)
                if (x < 2 || x > FIELD_WIDTH - 3 || y < 2 || y > FIELD_HEIGHT - 3) field[x][y] = COLOR_LAND;
    }

    void clearTrack() {
        for (int y = 0; y < FIELD_HEIGHT; y++)
            for (int x = 0; x < FIELD_WIDTH; x++)
                if (field[x][y] == COLOR_TRACK) field[x][y] = 0;
    }

    class Xonix {
        int x, y, direction;
        boolean isWater, isCross;

        Xonix() {
            y = 0;
            x = FIELD_WIDTH / 2;
            direction = 0;
            isWater = false;
        }

        void setDirection(int direction) { this.direction = direction; }

        void move() {
            if (direction == LEFT) x--;
            if (direction == RIGHT) x++;
            if (direction == UP) y--;
            if (direction == DOWN) y++;
            if (x < 0) x = 0;
            if (y < 0) y = 0;
            if (y > FIELD_HEIGHT - 1) y = FIELD_HEIGHT - 1;
            if (x > FIELD_WIDTH - 1) x = FIELD_WIDTH - 1;
            isCross = field[x][y] == COLOR_TRACK;
            if (field[x][y] != 0 && isWater) {
                direction = 0;
                isWater = false;
            }
            if (field[x][y] == 0) {
                isWater = true;
                field[x][y] = COLOR_TRACK;
            }
        }

        boolean isSelfCrosed() { return isCross; }

        void paint(Graphics g) {
            g.setColor((field[x][y] == COLOR_LAND) ? new Color(COLOR_TRACK) : Color.white);
            g.fillRect(x*POINT_SIZE, y*POINT_SIZE, POINT_SIZE, POINT_SIZE);
            g.setColor((field[x][y] == COLOR_LAND) ? Color.white : new Color(COLOR_TRACK));
            g.fillRect(x*POINT_SIZE + 3, y*POINT_SIZE + 3, 4, 4);
        }
    }

    class Canvas extends JPanel { // my canvas for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int y = 0; y < FIELD_HEIGHT; y++)
                for (int x = 0; x < FIELD_WIDTH; x++) {
                    g.setColor(new Color(field[x][y]));
                    g.fillRect(x*POINT_SIZE, y*POINT_SIZE, POINT_SIZE, POINT_SIZE);
                }
            xonix.paint(g);
        }
    }
}