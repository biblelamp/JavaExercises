/**
 * Java. ShootingBall
 *  Preparing for a some network game
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Aug 24, 2017
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

    Canvas canvas;
    Ball ball;

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
        ball = new Ball(100, 100, 25);
    }

    @Override
    public void keyPressed(KeyEvent event) { }

    @Override
    public void keyTyped(KeyEvent event) { }

    @Override
    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case LEFT: ball.turnLeft(15);
                canvas.repaint();
                break;
            case RIGHT: ball.turnRight(15);
                canvas.repaint();
                break;
            case UP: ball.forward(10);
                canvas.repaint();
                break;
            case DOWN: ball.backward(10);
                canvas.repaint();
                break;
        }
    }

    class Canvas extends JPanel { // for painting
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            ball.paint(g);
        }
    }
}