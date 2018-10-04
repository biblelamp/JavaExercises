import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Java. Katamari Damacy Game
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 04, 2018
 */

public class KatamariDamacy extends JFrame {

    final String TITLE_OF_PROGRAM = "Katamari Damacy";
    final String GAME_OVER_MSG = "GAME OVER";
    final int WIDTH = 640;
    final int HEIGHT = 480;
    static final int KEY_LEFT = 37;             // codes
    static final int KEY_UP = 38;               //   of
    static final int KEY_RIGHT = 39;            //   cursor
    static final int KEY_DOWN = 40;             //   keys
    boolean gameOver = false;                   // a sign game is over or not

    Canvas canvas;                              // canvas object for rendering
    //

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
                //
            }
        });
        add(canvas);                 // add a panel for rendering
        pack();                      // bringing the window to the required size
        setLocationRelativeTo(null); // the window will be in the center
        setResizable(false);         // prohibit window resizing
        setVisible(true);            // make the window visible
    }

    private void game() {            // method containing game cycle
        //

        while (!gameOver) {          // game cycle while NOT gameOver
            //
        }
        JOptionPane.showMessageDialog(KatamariDamacy.this, GAME_OVER_MSG);
    }

    class Canvas extends JPanel {    // class for rendering (drawing)
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //
        }
    }
}
