/**
 * Java. Classic Game Snake
 *
 * @author Sergey Iryupin
 * @version 0.7 dated Aug 18, 2018
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameSnake extends JFrame {

    final String TITLE_OF_PROGRAM = "Classic Game Snake";
    final String GAME_OVER_MSG = "GAME OVER";
    static final int POINT_SIZE = 20;          // size of cell in pix
    final int CANVAS_WIDTH = 30;               // width in point
    final int CANVAS_HEIGHT = 20;              // height in point
    final int START_SNAKE_SIZE = 5;            // initialization data
    final int START_SNAKE_X = CANVAS_WIDTH/2;  //   for
    final int START_SNAKE_Y = CANVAS_HEIGHT/2; //   snake
    final Color SNAKE_COLOR = Color.darkGray;
    final Color FOOD_COLOR = Color.green;
    final Color POISON_COLOR = Color.red;
    static final int KEY_LEFT = 37;             // codes
    static final int KEY_UP = 38;               //   of
    static final int KEY_RIGHT = 39;            //   cursor
    static final int KEY_DOWN = 40;             //   keys
    final int SNAKE_DELAY = 150;                // snake delay in milliseconds
    boolean gameOver = false;                   // a sign game is over or not

    Canvas canvas;                   // canvas object for rendering (drawing)
    Snake snake;                     // declare a snake object
    Food food;                       // declare a food object
    Poison poison;                   // declare a poison object

    public static void main(String[] args) {    // starting method
        new GameSnake().game();                 // create an object and call game()
    }

    public GameSnake() {
        setTitle(TITLE_OF_PROGRAM + " : " + START_SNAKE_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(
                POINT_SIZE * CANVAS_WIDTH - 10,
                POINT_SIZE * CANVAS_HEIGHT - 10));
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
    }

    private void game() {            // method containing game cycle
        snake = new Snake(           // creating snake object
                START_SNAKE_X,
                START_SNAKE_Y,
                START_SNAKE_SIZE,
                KEY_RIGHT, this);
        food = new Food(this);       // food object
        poison = new Poison(this);   // poison object

        while (!gameOver) {          // game cycle while NOT gameOver
            snake.move();            // snake move
            if (food.isEaten()) {    // if the snake ate the food
                food.show();         //   show food in new place
                poison.add();        //   add new poison point
            }
            canvas.repaint();        // repaint panel/window
            sleep(SNAKE_DELAY);      // to make delay in milliseconds
        }
        JOptionPane.showMessageDialog(GameSnake.this, GAME_OVER_MSG);
    }

    private void sleep(long ms) {    // method for suspending
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Canvas extends JPanel {    // class for rendering (drawing)
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            snake.paint(g);
            food.paint(g);
            poison.paint(g);
        }
    }
}