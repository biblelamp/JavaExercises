/**
 * Java. ShootingBall
 *  Class BallBot
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Aug 31, 2017
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class BallBot {
    private Ball ball;
    private Pill pill;
    private int typeOfAction, steps;
    private Random random;
    private final int WIDTH, HEIGHT;

    BallBot(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        ball = new Ball(WIDTH / 2, HEIGHT / 2, 10, 0, WIDTH, HEIGHT);
        random = new Random();
        steps = 0;
    }

    void action() {
        if (steps == 0) {
            typeOfAction = random.nextInt(5);
            if (typeOfAction == 0) {
                if (pill == null) {
                    pill = new Pill(
                        ball.getXPill(), ball.getYPill(), 
                        ball.getAngle(), Color.red);
                    typeOfAction = 4;
                    steps = 6;
                }
            } else
                steps = random.nextInt(10) + 1;
        }
        switch (typeOfAction) {
            case 1: ball.turn(-(steps*5));
                steps--;
                break;
            case 2: ball.turn(steps*5);
                steps--;
                break;
            case 3: ball.move(steps--);
                break;
            case 4: ball.move(-(steps--));
                break;
        }
        if (pill != null)
            if (pill.getX() < 0 || pill.getY() < 0 ||
                pill.getX() > WIDTH || pill.getY() > HEIGHT)
                pill = null;
            else pill.move(5);
    }

    void paint(Graphics g) {
        ball.paint(g);
        if (pill != null) pill.paint(g);
    }
}