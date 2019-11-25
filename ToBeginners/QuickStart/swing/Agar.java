import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Agar extends JFrame {

    final String TITLE_OF_PROGRAM = "Agar game";
    final int WINDOW_WIDTH = 450;
    final int WINDOW_HEIGHT = 650;

    public static void main(String[] args) {
        new Agar().game();
    }

    public Agar() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                // key handling
            }
        });
        add(canvas);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void game() {
        // create game object
        while (true) {
            // life of game objects
        }
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
        }
    }
}