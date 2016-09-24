/**
 * Java. Classic Game Xonix
 *
 * @author Sergey Iryupin
 * @version 0.2 dated September 24, 2016
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
    final int COLOR_TEMP = 1; // for temporary filling
    final int COLOR_WATER = 0;
    final int COLOR_LAND = 0x00a8a8;
    final int COLOR_TRACK = 0x901290;
    Canvas canvas = new Canvas();
    Field field = new Field();
    Xonix xonix = new Xonix();
    Ball ball = new Ball();

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
        setVisible(true);
    }

    void go() { // main loop of game
        while (true) {
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (Exception e) { e.printStackTrace(); }
            xonix.move();
            ball.move();
            if (xonix.isSelfCrosed() || ball.isHitTrackOrXonix()) {
                field.clearTrack();
                xonix = new Xonix();
            }
            canvas.repaint();
        }
    }

    class Field {
        int[][] field = new int[FIELD_WIDTH][FIELD_HEIGHT];

        Field() {
            for (int y = 0; y < FIELD_HEIGHT; y++)
                for (int x = 0; x < FIELD_WIDTH; x++)
                    field[x][y] = (x < 2 || x > FIELD_WIDTH - 3 || y < 2 || y > FIELD_HEIGHT - 3)? COLOR_LAND : COLOR_WATER;
        }

        int getColor(int x, int y) { return field[x][y]; }
        void setColor(int x, int y, int color) { field[x][y] = color; }

        void clearTrack() { // clear track of Xonix
            for (int y = 0; y < FIELD_HEIGHT; y++)
                for (int x = 0; x < FIELD_WIDTH; x++)
                    if (field[x][y] == COLOR_TRACK) field[x][y] = COLOR_WATER;
        }

        void fillTemporary(int x, int y) {
            if (field[x][y] > COLOR_WATER) return;
            field[x][y] = COLOR_TEMP; // filling temporary color
            for (int dx = -1; dx < 2; dx++)
                for (int dy = -1; dy < 2; dy++) fillTemporary(x + dx, y + dy);
        }

        void tryToFill() {
            fillTemporary(ball.getX(), ball.getY());
            for (int y = 0; y < FIELD_HEIGHT; y++)
                for (int x = 0; x < FIELD_WIDTH; x++) {
                    if (field[x][y] == COLOR_TRACK || field[x][y] == COLOR_WATER) field[x][y] = COLOR_LAND;
                    if (field[x][y] == COLOR_TEMP) field[x][y] = COLOR_WATER;
                }
        }

        void paint(Graphics g) {
            for (int y = 0; y < FIELD_HEIGHT; y++)
                for (int x = 0; x < FIELD_WIDTH; x++) {
                    g.setColor(new Color(field[x][y]));
                    g.fillRect(x*POINT_SIZE, y*POINT_SIZE, POINT_SIZE, POINT_SIZE);
                }
        }
    }

    class Xonix {
        int x, y, direction;
        boolean isWater, isSelfCross;

        Xonix() {
            y = 0;
            x = FIELD_WIDTH / 2;
            direction = 0;
            isWater = false;
        }

        int getX() { return x; }
        int getY() { return y; }

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
            isSelfCross = field.getColor(x, y) == COLOR_TRACK;
            if (field.getColor(x, y) != COLOR_WATER && isWater) {
                direction = 0;
                isWater = false;
                field.tryToFill();
            }
            if (field.getColor(x, y) == COLOR_WATER) {
                isWater = true;
                field.setColor(x, y, COLOR_TRACK);
            }
        }

        boolean isSelfCrosed() { return isSelfCross; }

        void paint(Graphics g) {
            g.setColor((field.getColor(x, y) == COLOR_LAND) ? new Color(COLOR_TRACK) : Color.white);
            g.fillRect(x*POINT_SIZE, y*POINT_SIZE, POINT_SIZE, POINT_SIZE);
            g.setColor((field.getColor(x, y) == COLOR_LAND) ? Color.white : new Color(COLOR_TRACK));
            g.fillRect(x*POINT_SIZE + 3, y*POINT_SIZE + 3, POINT_SIZE - 6, POINT_SIZE - 6);
        }
    }

    class Ball {
        int x, y, dx, dy;

        Ball() {
            x = 5;
            y = 5;
            dx = 1;
            dy = 1;
        }

        void move() {
            if (field.getColor(x + dx, y) == COLOR_LAND) dx = -dx;
            if (field.getColor(x, y + dy) == COLOR_LAND) dy = -dy;
            x += dx;
            y += dy;
        }

        int getX() { return x; }
        int getY() { return y; }

        boolean isHitTrackOrXonix() {
            if (field.getColor(x + dx, y + dy) == COLOR_TRACK) return true;
            if (xonix.getX() == x + dx && xonix.getY() == x + dy) return true;
            return false;
        }

        void paint(Graphics g) {
            g.setColor(Color.white);
            g.fillOval(x*POINT_SIZE, y*POINT_SIZE, POINT_SIZE, POINT_SIZE);
            g.setColor(new Color(COLOR_LAND));
            g.fillOval(x*POINT_SIZE + 2, y*POINT_SIZE + 2, POINT_SIZE - 4, POINT_SIZE - 4);
        }
    }

    class Canvas extends JPanel { // my canvas for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            field.paint(g);
            xonix.paint(g);
            ball.paint(g);
        }
    }
}