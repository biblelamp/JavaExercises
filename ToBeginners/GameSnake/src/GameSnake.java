/**
 * Java. Classic Game Snake
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jun 03, 2018
 */
import javax.swing.*;
import java.awt.*;

public class GameSnake extends JFrame {

    final String TITLE_OF_PROGRAM = "Classic Game Snake";
    final String GAME_OVER_MSG = "GAME OVER";
    final int POINT_SIZE = 20;      // in pix
    final int WIDTH_IN_POINT = 30;  // width in point
    final int HEIGHT_IN_POINT = 20;
    final int START_SNAKE_SIZE = 4;
    final int START_SNAKE_X = 10;
    final int START_SNAKE_Y = 10;
    final int SHOW_DELAY = 150;
    final int LEFT = 37;
    final int UP = 38;
    final int RIGHT = 39;
    final int DOWN = 40;

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
        canvas.setPreferredSize(new Dimension(WIDTH_IN_POINT * POINT_SIZE,
                HEIGHT_IN_POINT * POINT_SIZE));
        canvas.setBackground(Color.white);

        add(canvas);
        pack();
        setLocationRelativeTo(null); // to the center
        setResizable(false);
        setVisible(true);
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //snake.paint(g);
            //food.paint(g);
        }
    }
}