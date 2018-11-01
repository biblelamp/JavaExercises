import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Java. Lunar lander simple simulator
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Nov 01, 2018
 */

public class LunarLander extends JFrame {

    // service constants
    final String TITLE_OF_PROGRAM = "Lunar lander";
    final int WIDTH = 480;
    final int HEIGHT = 800;
    final int KEY_LEFT = 37;
    final int KEY_UP = 38;
    final int KEY_RIGHT = 39;
    final int KEY_DOWN = 40;

    // flight constants
    float accelOfGravity = 1.62f;       // m/s^2, at Moon surface
    int dryWeight = 2000 + 150;         // kg, lunarfly and pilot
    int exhaustSpeed = 3660;            // m/s, from the engine
    float accelLimit = 3 * 9.81f;       // 3G, G is earth acceleration of gravity
    float speedLimit = 5;               // m/s, landing speed limit

    // flight variables
    float fuel;                         // kg, fuel consumption
    float duration;                     // sec, maneuver time
    float speed;                        // m/s^2, current speed
    float height;                       // m, current height
    float acceleration;                 // m/c^2
    float fuelWeight = 400;             // kg, total fuel weight
    float flightTime;                   // sec, total flight time
    boolean isLanding;                  // sign of landing

    public static void main(String[] args) {
        new LunarLander();
    }

    public LunarLander() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.black);
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KEY_UP:
                        fuel += 2;
                        break;
                    case KEY_DOWN:
                        fuel -= 2;
                        if (fuel < 0)
                            fuel = 0;
                        break;
                    case KEY_LEFT:
                        duration -= 0.2;
                        if (duration < 0.001)
                            duration = 0;
                        break;
                    case KEY_RIGHT:
                        duration += 0.2;
                        break;
                }
                canvas.repaint();
            }
        });

        add(canvas);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void drawStringCenter(Graphics g, String text, int x, int y, int dx) {
        int width = g.getFontMetrics().stringWidth(text);
        g.drawString(text, x + (dx - width)/2, y);
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(new Color(78, 154, 6)); //115, 210, 22));
            g.setFont(new Font("Arial Black", 0, 26));
            drawStringCenter(g, "FUEL", 0, 60, 120);
            drawStringCenter(g, "TOTAL", 0, 120, 120);
            drawStringCenter(g, "TIME", 0, 180, 120);

            drawStringCenter(g, "SPEED", 360, 60, 120);
            drawStringCenter(g, "A", 360, 120, 120);
            drawStringCenter(g, "ALT", 360, 180, 120);

            drawStringCenter(g, "FUEL", 0, 610, 180);
            drawStringCenter(g, "TIME", 300, 610, 180);

            g.setColor(Color.green);
            g.setFont(new Font("Arial", 0, 20));

            drawStringCenter(g, Float.toString(fuelWeight), 0, 85, 120);
            drawStringCenter(g, Float.toString(dryWeight + fuelWeight), 0, 145, 120);
            drawStringCenter(g, Float.toString(flightTime), 0, 205, 120);

            drawStringCenter(g, Float.toString(speed), 360, 85, 120);
            drawStringCenter(g, Float.toString(acceleration), 360, 145, 120);
            drawStringCenter(g, Float.toString(height), 360, 205, 120);

            g.setFont(new Font("Arial", 0, 70));
            drawStringCenter(g, Integer.toString((int)fuel), 0, 725, 180);
            drawStringCenter(g, String.format("%3.1f", duration), 300, 725, 180);

            g.setFont(new Font("Arial", 0, 28));
            drawStringCenter(g, Integer.toString((int)fuel + 2), 0, 655, 180);
            drawStringCenter(g, (fuel == 0)? "" : Integer.toString((int)fuel - 2), 0, 765, 180);

            drawStringCenter(g, String.format("%3.1f", duration + 0.2f), 300, 655, 180);
            drawStringCenter(g, (duration == 0)? "" : String.format("%3.1f", duration - 0.2f), 300, 765, 180);

            g.drawOval(240 - 50, 650, 100, 100);
        }
    }
}
