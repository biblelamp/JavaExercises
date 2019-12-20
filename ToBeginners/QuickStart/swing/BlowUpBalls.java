import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
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
            addBall();
            if (balls.size() >= 5) {
                System.out.println("Game Over");
                break;
            }
            canvas.repaint();
            counter++;
            if (counter % 10 == 0 && showDelay > 100) {
                showDelay -= 100;
            }
            try {
                Thread.sleep(showDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        for (int i = balls.size() - 1; i > -1; i--) {
            double dx = balls.get(i).x + balls.get(i).d/2 - x;
            double dy = balls.get(i).y  + balls.get(i).d/2 - y;
            double d = Math.sqrt(dx * dx + dy * dy);
            if (d < balls.get(i).d/2) {
                balls.remove(i);
                break;
            }
        }
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (Ball ball : balls) {
                ball.paint(g);
            }
        }
    }
}