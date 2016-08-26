/**
 * Java. Game Space Invaders
 *
 * @author Sergey Iryupin
 * @version 0.1 dated 26 Aug 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameSpaceInvaders {

    final String TITLE_OF_PROGRAM = "Space Invaders";
    final String GAME_OVER_MSG = "GAME OVER";
    final int POINT_SCALE = 2;
    final int FIELD_WIDTH = 436;
    final int FIELD_HEIGHT = 436;
    final int START_LOCATION = 150;
    final int FIELD_DX = 6; // determined experimentally
    final int FIELD_DY = 28;
    final int LEFT = 37; // key codes
    final int RIGHT = 39;
    final int FIRE = 32;
    final int SHOW_DELAY = 25;
    Cannon cannon;
    Ray ray;
    Alien alien;
    JFrame frame;
    Canvas canvasPanel;
    Random random = new Random();
    boolean gameOver = false;

    public static void main(String[] args) {
        new GameSpaceInvaders().go();
    }

    void go() {
        frame = new JFrame(TITLE_OF_PROGRAM);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_WIDTH + FIELD_DX, FIELD_HEIGHT + FIELD_DY);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);

        canvasPanel = new Canvas();
        canvasPanel.setBackground(Color.black);

        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == LEFT) || (e.getKeyCode() == RIGHT)) {
                    cannon.move(e.getKeyCode());
                    canvasPanel.repaint();
                }
                if (e.getKeyCode() == FIRE) {
                    ray.start(cannon.getX() + 12, cannon.getY() - 8);
                    canvasPanel.repaint();
                }
            }
        });
        frame.setVisible(true);

        cannon = new Cannon();
        ray = new Ray();
        alien = new Alien(random.nextInt(FIELD_WIDTH - 12*2), random.nextInt(FIELD_HEIGHT - 100));

        // main loop of game
        while (!gameOver) {
            ray.fly();
            alien.nextView();
            if (alien.isTouchRay()) {
                alien = new Alien(random.nextInt(FIELD_WIDTH - 12*2), random.nextInt(FIELD_HEIGHT - 100));
            }
            canvasPanel.repaint();
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    class Ray { // from laser cannon
        int x, y;
        final int width = 2;
        final int height = 8;
        final int dy = 8;

        public Ray() {
            x = -1;
            y = -1;
        }

        void start(int x, int y) {
            if (this.y < 0) {
                this.x = x;
                this.y = y;
            }
        }

        void fly() {
            if (y + height > -1) {
                y -= dy;
            }
        }

        int getX() { return x; }
        int getY() { return y; }
        
        void paint(Graphics g) {
            if (x > 0) {
                g.fillRect(x, y, width, height);
            }
        }
    }

    class Cannon { // laser cannon
        final int width = 26;
        final int height = 16;
        final int dx = 10;
        int x, y;

        public Cannon() {
            x = 10;
            y = FIELD_HEIGHT - height - 60;
        }

        void move(int direction) {
            if (direction == LEFT) {
                if (x > 10) {
                    x -= dx;
                }
            } else {
                if (x < FIELD_WIDTH - width - 10) {
                    x += dx;
                }
            }
        }

        int getX() { return x; }
        int getY() { return y; }

        void paint(Graphics g) {
            g.fillRect(x, y + height/2, width, height/2);
            g.fillRect(x + 2, y + height/2 - 2, width - 4, height/2);
            g.fillRect(x + 10, y + 2, width - 20, height/2);
            g.fillRect(x + 12, y, 2, 2);
        }
    }

    class Alien { // experimental model
        int x, y;
        int view = 0;
        int countFrame = 0;
        final int numFrames = 10;
        final int[][][] shape = {
           {{0,0,0,0,1,1,1,1,0,0,0,0},
            {0,1,1,1,1,1,1,1,1,1,1,0},
            {1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,0,1,1,0,0,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,1,1,1,0,0,1,1,1,0,0},
            {0,1,1,0,0,1,1,0,0,1,1,0},
            {0,0,1,1,0,0,0,0,1,1,0,0}},
           {{0,0,0,0,1,1,1,1,0,0,0,0},
            {0,1,1,1,1,1,1,1,1,1,1,0},
            {1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,0,0,1,1,0,0,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,1,1,0,0,1,1,0,0,0},
            {0,0,1,1,0,1,1,0,1,1,0,0},
            {1,1,0,0,0,0,0,0,0,0,1,1}}
        };

        Alien(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean isTouchRay() {
            if ((ray.getX() >= x) && (ray.getX() <= x + 12*POINT_SCALE)) {
                if (ray.getY() < y + 8*POINT_SCALE) {
                    return true;
                }
            }
            return false;
        }
        
        void nextView() {
            if (countFrame == numFrames) {
                view = 1 - view;
                countFrame = 0;
            } else { countFrame++; }
        }

        void move() {
            
        }

        void paint(Graphics g) {
            g.setColor(Color.white);
            for (int col = 0; col <12; col++) {
                for (int row = 0; row < 8; row++) {
                    if (shape[view][row][col] == 1) {
                        g.fillRect(col*POINT_SCALE + x, row*POINT_SCALE + y, POINT_SCALE, POINT_SCALE);
                    }
                }
            }
        }
    }

    public class Canvas extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.green);
            g.fillRect(10, FIELD_HEIGHT - 30, FIELD_WIDTH - 20, 2);
            cannon.paint(g);
            ray.paint(g);
            alien.paint(g);
        }
    }
}