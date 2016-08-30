/**
 * Java. Game Space Invaders
 *
 * @author Sergey Iryupin
 * @version 0.3 dated 30 Aug 2016
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
	final int STEP_X = 5; // wave step left-right
	final int STEP_Y = 15; // wave step down
	final int GROUND_Y = FIELD_HEIGHT - 30;
    final int LEFT = 37; // key codes
    final int RIGHT = 39;
    final int DOWN = 40;
    final int FIRE = 32;
    final int SHOW_DELAY = 25;
	final int MAX_ALIEN_RAYS = 2;
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
	final int[] ARRAY_SCORES = {10, 20, 40};
	Cannon cannon = new Cannon();
    Ray ray = new Ray();
	Wave wave = new Wave();
	AlienRays rays = new AlienRays();
    JFrame frame;
    Canvas canvasPanel;
    Random random = new Random();
	int countScore = 0, countLives = 3;
    boolean gameOver = false;

    public static void main(String[] args) {
        new GameSpaceInvaders().go();
    }

    void go() {
        frame = new JFrame(TITLE_OF_PROGRAM + " // Score " + countScore + " // Live " + countLives);
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
			rays.fly();
			rays.checkGround();
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

        boolean isEnable() {
            return exists;
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
	
	class AlienRay { // from one alien
		int x, y;
		final int width = 6;
		final int height = 14;
		final int dy = 8;

		AlienRay(int x, int y) {
			this.x = x;
			this.y = y;
		}

		void fly() {
			y += dy;
		}
		
		boolean hitGround() {
			return y + height > GROUND_Y;
		}

		void paint(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(x + 2, y, 2, height);
			g.fillRect(x, y + 10, width, 2);
		}
	}

	class AlienRays { // a few rays from alien
		ArrayList<AlienRay> rays = new ArrayList<AlienRay>();
		final int maxRays = 5;

		void add(int x, int y) {
			rays.add(new AlienRay(x, y));
		}

		void fly() {
			for (AlienRay ray : rays) {
                ray.fly();
			}
		}

		void checkGround() {
			for (AlienRay ray : rays) {
				if (ray.hitGround()) {
					rays.remove(ray);
					break;
				}
			}
		}

		int getSize() {
			return rays.size();
		}

		void paint(Graphics g) {
			for (AlienRay ray : rays) {
                ray.paint(g);
            }
		}
	}

    class Alien { // for attacking wave
        int x, y;
        int view;
		int type;
		int width, height;

        Alien(int x, int y, int type) {
            this.x = x;
            this.y = y;
			this.type = type;
			view = 0;
			width = PATTERN_OF_ALIENS[type][view][8][0];
			height = PATTERN_OF_ALIENS[type][view][8][1];
        }
		
		int getType() {
			return type;
		}

        boolean isTouchRay() {
            if (ray.isEnable()) {
                if ((ray.getX() >= x) && (ray.getX() <= x + width*POINT_SCALE)) {
                    if (ray.getY() < y + height*POINT_SCALE) {
                        ray.disable();
                        return true;
                    }
                }
            }
            return false;
        }

        void nextStep(int direction) {
                view = 1 - view;
				if (direction == RIGHT) {
					x += STEP_X;
				} else if (direction == LEFT) {
                    x -= STEP_X;
                } else if (direction == DOWN) {
                    y += STEP_Y;
                }
        }

		void shot() {
			rays.add(x + width/2, y + height);
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
		int startX = 40;
		int startY = 60;
		final int[][] PATTERN = {
			{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
		ArrayList<Alien> wave = new ArrayList<Alien>();
        final int numFrames = 20; // sets the speed of the wave
        int countFrames = 0;
		int direction = RIGHT;
        boolean stepDown = false;

		Wave() {
			for (int y = 0; y < 5; y++) {
				for (int x = 0; x < 11; x++) {
					wave.add(new Alien(startX + x*POINT_SCALE*16, startY + y*POINT_SCALE*16, PATTERN[y][x]));
				}
			}
		}

		void nextStep() {
            if (countFrames == numFrames) {
                if ((startX == 10) || (startX == 10 + 14*STEP_X)) { // time to change direction
                    if (!stepDown) {
                        direction = DOWN;
                    } else {
                        direction = (startX == 10)? RIGHT : LEFT;
                        stepDown = false;
                    }
                }
                for (Alien alien : wave) { // wave moves and shots
                    alien.nextStep(direction);
					if (random.nextInt(10) == 9) {
						if (rays.getSize() < MAX_ALIEN_RAYS) {
							alien.shot();
						}
					}
                }
                if (direction == DOWN) {
                    startY += STEP_Y;
                    stepDown = true;
                } else {
                    startX += (direction == RIGHT)? STEP_X : -STEP_X;
                }
                countFrames = 0;
            } else {
                countFrames++;
            }
		}

		void checkHit() {
			for (Alien alien : wave) {
                if (alien.isTouchRay()) {
					countScore += ARRAY_SCORES[alien.getType()];
					frame.setTitle(TITLE_OF_PROGRAM + " // Score " + countScore + " // Live " + countLives);
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
            g.fillRect(10, GROUND_Y, FIELD_WIDTH - 20, 2);
            cannon.paint(g);
            ray.paint(g);
            wave.paint(g);
			rays.paint(g);
        }
    }
}