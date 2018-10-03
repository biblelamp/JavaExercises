/**
 * Java. ShootingBall
 *  Preparing for a some network game
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Aug 26, 2017
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ShootingBall extends JFrame implements KeyListener {

    final String TITLE_OF_PROGRAM = "Shooting Ball";
    final int WINDOW_WIDTH = 500;
    final int WINDOW_HEIGHT = 380;
    final int LEFT = 37; // key codes
    final int UP = 38;
    final int RIGHT = 39;
    final int DOWN = 40;
    final int FIRE = 32; // space key
    final int SHOW_DELAY = 20; // delay for animation

    Canvas canvas;
    Ball ball;
    Pill pill;

    public static void main(String args[]) {
        new ShootingBall();
    }

    ShootingBall() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        add(canvas);

        pack();
        setLocationRelativeTo(null); // to the center
        addKeyListener(this);
        setVisible(true);
        goGame();
    }

    void goGame() {
        ball = new Ball(100, 100, 10, 0, WINDOW_WIDTH, WINDOW_HEIGHT); // x, y, raduis
        pill = new Pill();
        while (true) {
            try {
                Thread.sleep(SHOW_DELAY);
            } catch (Exception e) { }
            pill.move(5);
            canvas.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent event) { }

    @Override
    public void keyTyped(KeyEvent event) { }

    @Override
    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case LEFT: ball.turn(-15);
                break;
            case RIGHT: ball.turn(15);
                break;
            case UP: ball.move(10);
                break;
            case DOWN: ball.move(-10);
                break;
            case FIRE: pill = new Pill(
                    ball.getXPill(), ball.getYPill(), ball.getAngle(), Color.black);
                break;
        }
    }

    class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            ball.paint(g);
            pill.paint(g);
        }
    }
}