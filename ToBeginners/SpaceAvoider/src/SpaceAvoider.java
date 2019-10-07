import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SpaceAvoider extends JFrame {

    final String TITLE_OF_PROGRAM = "Space Avoider";
    static final int WINDOW_WIDTH = 400;
    static final int WINDOW_HEIGHT = 600;
    static final int KEY_LEFT = 37;
    static final int KEY_UP = 38;
    static final int KEY_RIGHT = 39;
    static final int KEY_DOWN = 40;

    Starship starship;

    public static void main(String[] args) {
        new SpaceAvoider().game();
    }

    public SpaceAvoider() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                starship.control(e.getKeyCode());
                canvas.repaint();
            }
        });
        add(canvas);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void game() {
        starship = new Starship(WINDOW_WIDTH / 2, 450);
        while (true) {
            // life of game objects
        }
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            starship.paint(g);
        }
    }
}