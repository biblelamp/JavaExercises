import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

public class BlowUpBalls extends JFrame {

    final String TITLE_OF_PROGRAM = "Blow Up Balls";
    final int WIN_WIDTH = 550;
    final int WIN_HEIGHT = 550;
    final Color[] COLORS = {
        Color.gray, Color.blue, Color.green, Color.red, Color.yellow, Color.pink, Color.magenta, Color.orange
    };

    Random random;

    public static void main(String[] args) {
        new BlowUpBalls();
    }

    public BlowUpBalls() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        random = new Random();

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(WIN_WIDTH, WIN_HEIGHT));

        add(canvas);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    void paintBalls(int count, Graphics g) {
        for (int i = 0; i < count; i++) {
            int r = random.nextInt(30) + 50;
            int x = random.nextInt(WIN_WIDTH);
            int y = random.nextInt(WIN_HEIGHT);
            g.setColor(COLORS[random.nextInt(COLORS.length)]);
            g.fillOval(x, y, r, r);
            g.setColor(Color.black);
            g.drawOval(x, y, r, r);
        }
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            paintBalls(80, g);
        }
    }
}