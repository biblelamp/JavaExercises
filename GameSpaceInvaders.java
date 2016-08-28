/**
 * Java. Game Space Invaders
 *
 * @author Sergey Iryupin
 * @version 0.2 dated 28 Aug 2016
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
    final int FIELD_HEIGHT = 446;
    final int START_LOCATION = 150;
    final int FIELD_DX = 6; // determined experimentally
    final int FIELD_DY = 28;
	final int STEP_X = 5;
    final int LEFT = 37; // key codes
    final int RIGHT = 39;
    final int FIRE = 32;
    final int SHOW_DELAY = 25;
	final int[][][][] PATTERN_OF_ALIENS = {
	  {{{0,0,0,0,1,1,1,1,0,0,0,0}, // alien 1
		{0,1,1,1,1,1,1,1,1,1,1,0},
		{1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,0,0,1,1,0,0,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1},
		{0,0,1,1,1,0,0,1,1,1,0,0},
		{0,1,1,0,0,1,1,0,0,1,1,0},
		{0,0,1,1,0,0,0,0,1,1,0,0}, {12, 8}},
	   {{0,0,0,0,1,1,1,1,0,0,0,0},
		{0,1,1,1,1,1,1,1,1,1,1,0},
		{1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,0,0,1,1,0,0,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1},
		{0,0,0,1,1,0,0,1,1,0,0,0},
		{0,0,1,1,0,1,1,0,1,1,0,0},
		{1,1,0,0,0,0,0,0,0,0,1,1}}},
	  {{{0,0,1,0,0,0,0,0,1,0,0,0}, // alien 2
		{0,0,0,1,0,0,0,1,0,0,0,0},
		{0,0,1,1,1,1,1,1,1,0,0,0},
		{0,1,1,0,1,1,1,0,1,1,0,0},
		{1,1,1,1,1,1,1,1,1,1,1,0},
		{1,0,1,1,1,1,1,1,1,0,1,0},
		{1,0,1,0,0,0,0,0,1,0,1,0},
		{0,0,0,1,1,0,1,1,0,0,0,0}, {11, 8}},
	   {{0,0,1,0,0,0,0,0,1,0,0,0},
		{1,0,0,1,0,0,0,1,0,0,1,0},
		{1,0,1,1,1,1,1,1,1,0,1,0},
		{1,1,1,0,1,1,1,0,1,1,1,0},
		{1,1,1,1,1,1,1,1,1,1,1,0},
		{0,1,1,1,1,1,1,1,1,1,0,0},
		{0,0,1,0,0,0,0,0,1,0,0,0},
		{0,1,0,0,0,0,0,0,0,1,0,0}}},
	  {{{0,0,0,1,1,0,0,0,0,0,0,0}, // alien 3
		{0,0,1,1,1,1,0,0,0,0,0,0},
		{0,1,1,1,1,1,1,0,0,0,0,0},
		{1,1,0,1,1,0,1,1,0,0,0,0},
		{1,1,1,1,1,1,1,1,0,0,0,0},
		{0,0,1,0,0,1,0,0,0,0,0,0},
		{0,1,0,1,1,0,1,0,0,0,0,0},
		{1,0,1,0,0,1,0,1,0,0,0,0}, {8, 8}},
	   {{0,0,0,1,1,0,0,0,0,0,0,0},
		{0,0,1,1,1,1,0,0,0,0,0,0},
		{0,1,1,1,1,1,1,0,0,0,0,0},
		{1,1,0,1,1,0,1,1,0,0,0,0},
		{1,1,1,1,1,1,1,1,0,0,0,0},
		{0,1,0,1,1,0,1,0,0,0,0,0},
		{1,0,0,0,0,0,0,1,0,0,0,0},
		{0,1,0,0,0,0,1,0,0,0,0,0}}}};
	Cannon cannon = new Cannon();
    Ray ray = new Ray();
	Wave wave = new Wave();
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

        // main loop of game
        while (!gameOver) {
            ray.fly();
            wave.nextStep();
			wave.checkHit();
            canvasPanel.repaint();
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    class Ray { // from laser cannon
        int x, y;
		boolean exists;
        final int width = 2;
        final int height = 8;
        final int dy = 8;

        public Ray() {
            exists = false;
        }

        void start(int x, int y) {
            if (!exists) {
				exists = true;
                this.x = x;
                this.y = y;
            }
        }

        void fly() {
            if (exists) {
                y -= dy;
				exists = (y + dy) > 0;
            }
        }

		void disable() {
			exists = false;
		}

        int getX() { return x; }
        int getY() { return y; }

        void paint(Graphics g) {
            if (exists) {
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

    class Alien { // for attacking wave
        int x, y;
        int view;
		int type;
		int width, height;
        int countFrame = 0;
        final int numFrames = 20;

        Alien(int x, int y, int type) {
            this.x = x;
            this.y = y;
			this.type = type;
			view = 0;
			width = PATTERN_OF_ALIENS[type][view][8][0];
			height = PATTERN_OF_ALIENS[type][view][8][1];
        }

        boolean isTouchRay() {
            if ((ray.getX() >= x) && (ray.getX() <= x + width*POINT_SCALE)) {
                if (ray.getY() < y + height*POINT_SCALE) {
					ray.disable();
                    return true;
                }
            }
            return false;
        }
        
        void nextStep(int direction) {
            if (countFrame == numFrames) {
                view = 1 - view;
                countFrame = 0;
				if (direction == RIGHT) {
					x += STEP_X;
				}
            } else { countFrame++; }
        }

        void paint(Graphics g) {
            g.setColor(Color.white);
            for (int col = 0; col < width; col++) {
                for (int row = 0; row < height; row++) {
					if (PATTERN_OF_ALIENS[type][view][row][col] == 1) {
						g.fillRect(col*POINT_SCALE + x, row*POINT_SCALE + y, POINT_SCALE, POINT_SCALE);
                    }
                }
            }
        }
    }

	class Wave { // attacking wave of aliens
		final int startX = 10;
		final int startY = 20;
		final int[][] PATTERN = {
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
		ArrayList<Alien> wave = new ArrayList<Alien>();
		int direction = RIGHT;

		Wave() {
			for (int y = 0; y < 5; y++) {
				for (int x = 0; x < 11; x++) {
					wave.add(new Alien(startX + x*POINT_SCALE*16, startY + y*POINT_SCALE*16, PATTERN[y][x]));
				}
			}
		}

		void nextStep() {
			for (Alien alien : wave) {
                alien.nextStep(direction);
			}
		}

		void checkHit() {
			for (Alien alien : wave) {
                if (alien.isTouchRay()) {
					wave.remove(alien);
					break;
				}
            }
		}

		void paint(Graphics g) {
			for (Alien alien : wave) {
                alien.paint(g);
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
            wave.paint(g);
        }
    }
}