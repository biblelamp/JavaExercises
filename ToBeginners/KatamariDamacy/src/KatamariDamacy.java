import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Java. Katamari Damacy Game
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Oct 12, 2018
 */

public class KatamariDamacy extends JFrame {

    final String TITLE_OF_PROGRAM = "Katamari Damacy";
    final String GAME_OVER_MSG = "GAME OVER";
    final static int WIDTH = 640;
    final static int HEIGHT = 480;
    final static int KEY_LEFT = 37;             // codes
    final static int KEY_UP = 38;               //   of
    final static int KEY_RIGHT = 39;            //   cursor
    final static int KEY_DOWN = 40;             //   keys
    final int HERO_START_SIZE = 40;
    final int SHOW_DELAY = 5;                   // delay show in milliseconds
    boolean gameOver = false;                   // a sign game is over or not

    Canvas canvas;                              // canvas object for rendering
    Hero hero;
    //Circles circles;

    public static void main(String[] args) {    // starting method
        new KatamariDamacy().game();            // create an object and call game()
    }

    public KatamariDamacy() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                hero.setDirection(e.getKeyCode());
            }
        });
        add(canvas);                 // add a panel for rendering
        pack();                      // bringing the window to the required size
        setLocationRelativeTo(null); // the window will be in the center
        setResizable(false);         // prohibit window resizing
        setVisible(true);            // make the window visible
    }

    private void game() {            // method containing game cycle
        hero = new Hero(
                (WIDTH - HERO_START_SIZE)/2,
                (HEIGHT - HERO_START_SIZE)/2,
                HERO_START_SIZE,
                KEY_RIGHT,
                Color.black);
        //circles = new Circles();

        while (!gameOver) {          // game cycle while NOT gameOver
            hero.move();
            canvas.repaint();
            sleep(SHOW_DELAY);
        }
        JOptionPane.showMessageDialog(KatamariDamacy.this, GAME_OVER_MSG);
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
            hero.paint(g);
            //circles.paint(g);
        }
    }
}
