/**
 * Java. ShootingBall
 *  Class BallBot
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Aug 29, 2017
 */
import java.awt.Graphics;
import java.util.Random;

class BallBot {
    private Ball ball;
    private Pill pill;
    private int typeOfAction, steps;
    private Random random;

    BallBot(int width, int height) {
        ball = new Ball(100, 100, 10, 0, width, height);
        random = new Random();
        typeOfAction = -1;
        steps = 0;
    }

    void action() {
        if (steps == 0) {
            typeOfAction = random.nextInt(5);
            if (typeOfAction == 4)
                pill = new Pill(
                    ball.getXPill(), ball.getYPill(), ball.getAngle());
            else
                steps = random.nextInt(10) + 1;
        }
        switch (typeOfAction) {
            case 0: ball.turnLeft(steps--);
                break;
            case 1: ball.turnRight(steps--);
                break;
            case 2: ball.forward(steps--);
                break;
            case 3: ball.backward(steps--);
                break;
        }
        if (pill != null)
            pill.move(5);
    }

    void paint(Graphics g) {
        ball.paint(g);
        if (pill != null)
            pill.paint(g);
    }
}