/**
 * Java. Classic Game Snake
 *
 * @author Sergey Iryupin
 * @version 0.5 dated Jul 18, 2018
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameSnake extends JFrame {

    final String TITLE_OF_PROGRAM = "Classic Game Snake";
    final String GAME_OVER_MSG = "GAME OVER";
    static final int POINT_SIZE = 20;        // size of cell in pix
    final int WIDTH = 30;                    // width in point
    final int HEIGHT = 20;                   // height in point
    final int START_SNAKE_SIZE = 5;          // initialization data
    final int START_SNAKE_X = WIDTH/2;       //   for
    final int START_SNAKE_Y = HEIGHT/2;      //   snake
    final Color SNAKE_COLOR = Color.darkGray;
    final Color FOOD_COLOR = Color.green;
    final Color POISON_COLOR = Color.red;
    static final int KEY_LEFT = 37;          // codes
    static final int KEY_UP = 38;            //   of
    static final int KEY_RIGHT = 39;         //   cursor
    static final int KEY_DOWN = 40;          //   keys
    final int SHOW_DELAY = 150;              // delay in milliseconds
    boolean gameOver = false;                // a sign that the game is over or not

    Canvas canvas;                           // panel for rendering (drawing)
    Snake snake;
    Food food;
    Poison poison;

    public static void main(String[] args) {
        new GameSnake();
    }

    GameSnake() {
        setTitle(TITLE_OF_PROGRAM + " : " + START_SNAKE_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(POINT_SIZE * WIDTH - 10,
                POINT_SIZE * HEIGHT - 10));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.setDirection(e.getKeyCode());
            }
        });
        add(canvas);                 // add a panel for rendering
        pack();                      // bringing the window to the required size
        setLocationRelativeTo(null); // the window will be in the center
        setResizable(false);         // prohibit window resizing
        setVisible(true);            // make the window visible

        // creation of game objects
        snake = new Snake(START_SNAKE_X, START_SNAKE_Y, START_SNAKE_SIZE, KEY_RIGHT, this);
        food = new Food(this);
        poison = new Poison(this);

        while (!gameOver) {          // game cycle
            snake.move();            // snake move
            if (food.isEaten()) {    // if the snake ate the food
                food.show();         //   show food in new place
                poison.add();        //   add new poison point
            }
            canvas.repaint();        // repaint panel/window
            try {                    // to make delay in ms
                Thread.sleep(SHOW_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(GameSnake.this, GAME_OVER_MSG);
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            snake.paint(g);
            food.paint(g);
            poison.paint(g);
        }
    }
}