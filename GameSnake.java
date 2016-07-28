/**
 * Java. Classic Game Snake
 *
 * @author Sergey Iryupin
 * @version 0.1 dated 28 July 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameSnake {

    final String nameOfGame = "Classic Game Snake";
    final String gameOverMsg = "GAME OVER";
    final int POINT_RADIUS = 20; // size of one point
    final int FIELD_WIDTH = 30; // in point
    final int FIELD_HEIGHT = 20;
    final int FIELD_DX = 6; // determined experimentally
    final int FIELD_DY = 28;
    final int START_LOCATION = 200;
    final int START_SNAKE_SIZE = 6;
    final int START_SNAKE_X = 10;
    final int START_SNAKE_Y = 10;
    int showDelay = 150;
    final int LEFT = 37; // key codes
    final int UP = 38;
    final int RIGHT = 39;
    final int DOWN = 40;
    final int START_DIRECTION = RIGHT;
    final Color FOOD_COLOR = Color.green;
    Snake snake;
    Point food;
    int x, y;
    Random random = new Random();
    JFrame frame;
    Canvas canvasPanel;
    boolean gameOver = false;

    public static void main(String[] args) {
        new GameSnake().go();
    }

    void go() {
        frame = new JFrame(nameOfGame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_WIDTH * POINT_RADIUS + FIELD_DX, FIELD_HEIGHT * POINT_RADIUS + FIELD_DY);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false);

        canvasPanel = new Canvas();
        canvasPanel.setBackground(Color.white);

        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                snake.setDirection(e.getKeyCode());
            }
        });

        frame.setVisible(true);

        snake = new Snake(START_SNAKE_X, START_SNAKE_Y, START_SNAKE_SIZE, START_DIRECTION);
        food = new Point(-1, -1, FOOD_COLOR);

        // main loop of game
        while (!gameOver) {
            snake.move();
            if (food.getX() < 0) {
                do {
                    x = random.nextInt(FIELD_WIDTH);
                    y = random.nextInt(FIELD_HEIGHT);
                } while (snake.isInsideSnake(x, y));
                food.setXY(x, y);
            }
            canvasPanel.repaint();
            try {
                Thread.sleep(showDelay);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    class Snake {
        ArrayList<Point> snake = new ArrayList<Point>();
        int direction;

        public Snake(int x, int y, int length, int direction) {
            for (int i = 0; i < length; i++) {
                Point point = new Point(x - i, y);
                snake.add(point);
            }
            this.direction = direction;
        }

        boolean isInsideSnake(int x, int y) {
            for (Point point : snake) {
                if ((point.getX() == x) && (point.getY() == y)) {
                    return true;
                }
            }
            return false;
        }

        boolean isFood(Point food) {
            return ((snake.get(0).getX() == food.getX()) && (snake.get(0).getY() == food.getY()));
        }

        void move() {
            int x = snake.get(0).getX();
            int y = snake.get(0).getY();
            if (direction == LEFT) { x--; }
            if (direction == RIGHT) { x++; }
            if (direction == UP) { y--; }
            if (direction == DOWN) { y++; }
            if (x > FIELD_WIDTH) { x = 0; }
            if (x < 0) { x = FIELD_WIDTH; }
            if (y > FIELD_HEIGHT) { y = 0; }
            if (y < 0) { y = FIELD_HEIGHT; }
            gameOver = isInsideSnake(x, y); // check game over
            snake.add(0, new Point(x, y));
            if (isFood(food)) { // check meeting food
                food.setXY(-1, -1);
                frame.setTitle(nameOfGame + " : " + snake.size());
            } else {
                snake.remove(snake.size() - 1);
            }
        }

        void setDirection(int direction) {
            if ((direction >= LEFT) && (direction <= DOWN)) { // block bad codes
                if (Math.abs(this.direction - direction) != 2) { // block back moving
                    this.direction = direction;
                }
            }
        }

        void paint(Graphics g) {
            for (Point point : snake) {
                point.paint(g);
            }
        }
    }

    class Point {
        int x;
        int y;
        Color color;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.color = Color.black;
        }

        public Point(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        void paint(Graphics g) {
            g.setColor(color);
            g.fillOval(getX()*POINT_RADIUS, getY()*POINT_RADIUS, POINT_RADIUS, POINT_RADIUS);
        }

        int getX() { return x; }
        int getY() { return y; }
        void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class Canvas extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            snake.paint(g);
            food.paint(g);
            if (gameOver) {
                g.setFont(new Font("Arial", Font.BOLD, 40));
                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(gameOverMsg);
                g.setColor(Color.red);
                g.drawString(gameOverMsg, (FIELD_WIDTH * POINT_RADIUS + FIELD_DX -textWidth)/2, (FIELD_HEIGHT * POINT_RADIUS + FIELD_DY)/2);
            }
        }
    }
}