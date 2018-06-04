/**
 * Java. Classic Game Snake
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jun 04, 2018
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameSnake extends JFrame {

    static final String TITLE_OF_PROGRAM = "Classic Game Snake";
    static final String GAME_OVER_MSG = "GAME OVER";
    static final int POINT_SIZE = 20; // in pix
    static final int WIDTH = 30;      // width in point
    static final int HEIGHT = 20;
    static final int START_SNAKE_SIZE = 5;
    static final int START_SNAKE_X = 10;
    static final int START_SNAKE_Y = 10;
    static final int SHOW_DELAY = 150;
    static final int LEFT = 37;
    static final int UP = 38;
    static final int RIGHT = 39;
    static final int DOWN = 40;

    Canvas canvas; // for drawing
    Snake snake;
    Food food;

    public static void main(String[] args) {
        new GameSnake();
    }

    GameSnake() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(POINT_SIZE * WIDTH - 10,
                POINT_SIZE * HEIGHT - 10));
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                snake.setDirection(e.getKeyCode());
            }
        });
        add(canvas);
        pack();
        setLocationRelativeTo(null); // to the center
        setResizable(false);
        setVisible(true);
        //System.out.println(canvas.getWidth() + ":" + canvas.getHeight());

        snake = new Snake(START_SNAKE_X, START_SNAKE_Y, START_SNAKE_SIZE, RIGHT);

        while (true) {
            snake.move();
            canvas.repaint();
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            snake.paint(g);
            //food.paint(g);
        }
    }
}