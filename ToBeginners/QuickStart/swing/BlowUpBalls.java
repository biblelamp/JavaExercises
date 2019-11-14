import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

class BlowUpBalls extends JFrame {

    final String TITLE_OF_PROGRAM = "Blow Up Balls";
    final int WIN_WIDTH = 550;
    final int WIN_HEIGHT = 550;
    final int NUMBER_OF_BALLS = 30;
    final Color[] COLORS = {
        Color.gray, Color.blue, Color.green, Color.red, Color.yellow, Color.pink, Color.magenta, Color.orange
    };
    int showDelay = 1000;
    int counter = 0;

    Random random;
    List<Ball> balls;
    Canvas canvas;

    public static void main(String[] args) {
        new BlowUpBalls().game();
    }

    public BlowUpBalls() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        random = new Random();
        balls = new ArrayList<>();

        canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(WIN_WIDTH, WIN_HEIGHT));
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                deleteBall(e.getX(), e.getY());
                canvas.repaint();
            }
        });
        add(canvas);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    void game() {
        while (true) {
            counter++;
            addBall();
            sleep(showDelay);
            canvas.repaint();
        }
    }

    void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void initBalls(int count) {
        for (int i = 0; i < count; i++) {
            addBall();
        }
    }

    void addBall() {
        int d = random.nextInt(30) + 50;
        int x = random.nextInt(WIN_WIDTH - d);
        int y = random.nextInt(WIN_HEIGHT - d);
        Color color = COLORS[random.nextInt(COLORS.length)];
        balls.add(new Ball(x, y, d, color));
    }

    void deleteBall(int x, int y) {
        for (Ball ball : balls) {
            double d = Math.sqrt(Math.pow(ball.x + ball.d/2 - x, 2) +
                Math.pow(ball.y  + ball.d/2 - y, 2));
            if (d < ball.d/2) {
                balls.remove(ball);
                break;
            }
        }
    }

    void paintBalls(Graphics g) {
        for (Ball ball : balls) {
            ball.paint(g);
        }
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            paintBalls(g);
        }
    }

    class Ball {
        int x, y, d;
        Color color;

        Ball(int x, int y, int d, Color color) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.color = color;
        }

        void paint(Graphics g) {
            g.setColor(color);
            g.fillOval(x, y, d, d);
            g.setColor(Color.black);
            g.drawOval(x, y, d, d);
        }
    }
}