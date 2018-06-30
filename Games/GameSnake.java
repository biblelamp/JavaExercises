/**
 * Java. Classic Game Snake
 *
 * @author Sergey Iryupin
 * @version 0.3.4 dated October 03, 2016
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

class GameSnake extends JFrame {

    final String TITLE_OF_PROGRAM = "Classic Game Snake";
    final String GAME_OVER_MSG = "GAME OVER";
    final int POINT_RADIUS = 25; // size of one point
    final int FIELD_WIDTH = 30; // in point
    final int FIELD_HEIGHT = 20;
    final int FIELD_DX = 6; // determined experimentally
    final int FIELD_DY = 28;
    final int START_LOCATION = 200;
    final int START_SNAKE_SIZE = 6;
    final int START_SNAKE_X = 10;
    final int START_SNAKE_Y = 10;
    final int SHOW_DELAY = 150; // delay for animation
    final int LEFT = 37; // key codes
    final int UP = 38;
    final int RIGHT = 39;
    final int DOWN = 40;
    final int START_DIRECTION = RIGHT;
    final Color DEFAULT_COLOR = Color.gray;
    //final Color FOOD_COLOR = Color.green;
    //final Color POISON_COLOR = Color.red;
    Image apple, amanita; // images for food and poison
    Random random = new Random();
    Canvas canvas = new Canvas();
    Snake snake = new Snake(START_SNAKE_X, START_SNAKE_Y, START_SNAKE_SIZE, START_DIRECTION);
    Food food = new Food();
    Poison poison = new Poison();
    boolean gameOver = false;

    public static void main(String[] args) {
        new GameSnake().go();
    }

    GameSnake() {
        setTitle(TITLE_OF_PROGRAM + " : " + START_SNAKE_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, FIELD_WIDTH * POINT_RADIUS + FIELD_DX, FIELD_HEIGHT * POINT_RADIUS + FIELD_DY);
        setResizable(false);
        canvas.setBackground(Color.white);
        add(BorderLayout.CENTER, canvas);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                snake.setDirection(e.getKeyCode());
            }
        });
        try {
            apple = ImageIO.read(new File("img/apple.png"));
            amanita = ImageIO.read(new File("img/amanita.png"));
        } catch(IOException e) { e.printStackTrace(); }
        setVisible(true);
    }
    
    void go() { // main loop of game
        while (!gameOver) {
            snake.move();
            if (food.isEaten()) {
                food.next();
                poison.add();
            }
            canvas.repaint();
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    class Snake {
        private ArrayList<Point> snake = new ArrayList<Point>();
        private int direction;

        public Snake(int x, int y, int length, int direction) {
            for (int i = 0; i < length; i++) snake.add(new Point(x - i, y));
            this.direction = direction;
        }

        boolean isInsideSnake(int x, int y) {
            for (Point point : snake)
                if ((point.getX() == x) && (point.getY() == y))
                    if (!((snake.get(snake.size() - 1).getX() == x) && (snake.get(snake.size() - 1).getY() == y))) // to exclude snake's tail
                        return true;
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
            if (x > FIELD_WIDTH - 1) { x = 0; }
            if (x < 0) { x = FIELD_WIDTH - 1; }
            if (y > FIELD_HEIGHT - 1) { y = 0; }
            if (y < 0) { y = FIELD_HEIGHT - 1; }
            gameOver = isInsideSnake(x, y) || poison.isPoison(x, y); // check game over
            snake.add(0, new Point(x, y)); // new position for head
            if (isFood(food)) { // check meeting food
                food.eat();
                setTitle(TITLE_OF_PROGRAM + " : " + snake.size());
            } else {
                snake.remove(snake.size() - 1);
            }
        }

        void setDirection(int direction) {
            if ((direction >= LEFT) && (direction <= DOWN)) // block wrong codes
                if (Math.abs(this.direction - direction) != 2) // block moving back
                    this.direction = direction;
        }

        void paint(Graphics g) {
            for (Point point : snake) point.paint(g);
        }
    }

    class Food extends Point {

        public Food() {
            super(-1, -1);
            //this.color = FOOD_COLOR;
        }

        void eat() { this.setXY(-1, -1); }

        boolean isEaten(){ return this.getX() == -1; }

        boolean isFood(int x, int y) { return (this.x == x) && (this.y == y); }

        void next() {
            int x, y;
            do {
                x = random.nextInt(FIELD_WIDTH);
                y = random.nextInt(FIELD_HEIGHT);
            } while (snake.isInsideSnake(x, y) || poison.isPoison(x, y));
            this.setXY(x, y);
        }

        void paint(Graphics g) {
            g.drawImage(apple, x*POINT_RADIUS, y*POINT_RADIUS, null);
        }
    }

    class Poison {
        private ArrayList<Point> poison = new ArrayList<Point>();

        boolean isPoison(int x, int y) {
            for (Point point : poison) if ((point.getX() == x) && (point.getY() == y)) return true;
            return false;
        }

        void add() {
            int x, y;
            do {
                x = random.nextInt(FIELD_WIDTH);
                y = random.nextInt(FIELD_HEIGHT);
            } while (isPoison(x, y) || snake.isInsideSnake(x, y) || food.isFood(x, y));
            poison.add(new Point(x, y)); //, POISON_COLOR));
        }

        void paint(Graphics g) {
            for (Point point : poison) g.drawImage(amanita, point.getX()*POINT_RADIUS, point.getY()*POINT_RADIUS, null);
        }
    }

    class Point {
        protected int x, y;
        protected Color color = DEFAULT_COLOR;

        public Point(int x, int y) {
            setXY(x, y);
        }

        public Point(int x, int y, Color color) {
            setXY(x, y);
            this.color = color;
        }

        int getX() { return x; }
        int getY() { return y; }

        void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void paint(Graphics g) {
            g.setColor(color);
            g.fill3DRect(getX()*POINT_RADIUS + 1, getY()*POINT_RADIUS + 1, POINT_RADIUS - 2, POINT_RADIUS - 2, true); // fillOval()
        }
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            snake.paint(g);
            food.paint(g);
            poison.paint(g);
            if (gameOver) {
                g.setColor(Color.red);
                g.setFont(new Font("Arial", Font.BOLD, 60));
                FontMetrics fm = g.getFontMetrics();
                g.drawString(GAME_OVER_MSG, (FIELD_WIDTH * POINT_RADIUS + FIELD_DX - fm.stringWidth(GAME_OVER_MSG))/2, (FIELD_HEIGHT * POINT_RADIUS + FIELD_DY)/2);
            }
        }
    }
}