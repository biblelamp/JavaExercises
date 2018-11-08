import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Java. Lunar lander simple simulator
 *
 * @author Sergey Iryupin
 * @version 0.3.3 dated Nov 08, 2018
 */

public class LunarLander extends JFrame {

    // service constants
    final String TITLE_OF_PROGRAM = "Lunar lander";
    final int WIN_WIDTH = 480;      // 480x800 (GT-I8160), 320x480 (GT-S5830)
    final int WIN_HEIGHT = 680;
    final int KEY_LEFT = 37;
    final int KEY_UP = 38;
    final int KEY_RIGHT = 39;
    final int KEY_DOWN = 40;
    final int KEY_CTRL = 17;
    final int KEY_ENTER = 10;
    final int FUEL_UNIT = 2;
    final float TIME_UNIT = 0.2f;
    final int DIRECT_THRUST = 1;
    final int REVERSE_THRUST = -1;

    int thrustDirection = DIRECT_THRUST;
    boolean isRealTime = true;

    // for rendering
    Canvas canvas;

    // mathematical model
    private LunarLanderModel model;

    public static void main(String[] args) {
        new LunarLander();
    }

    public LunarLander() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new Canvas();
        canvas.setBackground(Color.black);
        canvas.setPreferredSize(new Dimension(WIN_WIDTH, WIN_HEIGHT));

        model = new LunarLanderModel();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                processingKeyPressing(e.getKeyCode());
            }
        });

        add(canvas);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void processingKeyPressing(int keyCode) {
        switch (keyCode) {
            case KEY_UP:
                model.addFuel(FUEL_UNIT);
                break;
            case KEY_DOWN:
                model.addFuel(-FUEL_UNIT);
                break;
            case KEY_LEFT:
                model.addDuration(-TIME_UNIT);
                break;
            case KEY_RIGHT:
                model.addDuration(TIME_UNIT);
                break;
            case KEY_CTRL:
                thrustDirection = reverseThrust(thrustDirection);
                break;
            case KEY_ENTER:
                float duration = model.getDuration();
                if (!isRealTime) {
                    model.simulate(thrustDirection);
                } else {
                    float partFuel = model.getFuel()/model.getDuration() * TIME_UNIT;
                    float timeCount = 0;
                    do {
                        timeCount += TIME_UNIT;
                        model.setFuel(partFuel);
                        model.setDuration(TIME_UNIT);
                        model.simulate(thrustDirection);
                        canvas.repaint();
                        sleep((long)(TIME_UNIT * 1000));
                    } while (timeCount < duration);
                }
                model.setDuration(duration);
                break;
            default:
                System.out.println(keyCode);
        }
        canvas.repaint();
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int reverseThrust(int direction) {
        return (direction > 0)? REVERSE_THRUST: DIRECT_THRUST;
    }

    private void drawStringCenter(Graphics g, String text, int x, int y, int dx) {
        int width = g.getFontMetrics().stringWidth(text);
        g.drawString(text, x + (dx - width)/2, y);
    }

    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            g.setColor(new Color(78, 154, 1)); //115, 210, 22));
            g.setFont(new Font("Arial Black", 0, 26));
            drawStringCenter(g, "FUEL", 0, 45, 120);
            drawStringCenter(g, "TOTAL", 0, 105, 120);
            drawStringCenter(g, "TIME", 0, 165, 120);

            drawStringCenter(g, "SPEED", 360, 45, 120);
            drawStringCenter(g, "A", 360, 105, 120);
            drawStringCenter(g, "ALT", 360, 165, 120);

            drawStringCenter(g, "FUEL", 0, WIN_HEIGHT - 165, 180);
            drawStringCenter(g, "TIME", 300, WIN_HEIGHT - 165, 180);

            g.setColor(Color.green);
            g.setFont(new Font("Arial", 0, 20));

            drawStringCenter(g, String.format("%3.1f", model.getFuelWeight()), 0, 70, 120);
            drawStringCenter(g, String.format("%3.1f", model.getTotalWeight()), 0, 130, 120);
            drawStringCenter(g, String.format("%3.1f", model.getFlightTime()), 0, 190, 120);

            drawStringCenter(g, String.format("%3.2f", model.getSpeed()), 360, 70, 120);
            drawStringCenter(g, String.format("%3.2f", model.getAcceleration()), 360, 130, 120);
            drawStringCenter(g, String.format("%3.2f", model.getHeight()), 360, 190, 120);

            g.setFont(new Font("Arial", 0, 70));
            drawStringCenter(g, Integer.toString(model.getIntFuel()), 0, WIN_HEIGHT - 50, 180);
            drawStringCenter(g, String.format("%3.1f", model.getDuration()), 300, WIN_HEIGHT - 50, 180);

            g.setFont(new Font("Arial", 0, 28));
            drawStringCenter(g, Integer.toString(model.getIntFuel() + 2), 0, WIN_HEIGHT - 120, 180);
            drawStringCenter(g, (model.getIntFuel() == 0)? "" : Integer.toString(model.getIntFuel() - 2), 0, WIN_HEIGHT - 10, 180);

            drawStringCenter(g, String.format("%3.1f", model.getDuration() + 0.2f), 300, WIN_HEIGHT - 120, 180);
            drawStringCenter(g, (model.getDuration() == 0)? "" : String.format("%3.1f", model.getDuration() - 0.2f), 300, WIN_HEIGHT - 10, 180);

            g.drawOval(WIN_WIDTH/2 - 50, WIN_HEIGHT - 125, 100, 100);
            drawStringCenter(g, (thrustDirection > 0)? "^" : "v", WIN_WIDTH/2 - 50, WIN_HEIGHT - 60, 100);
        }
    }
}
