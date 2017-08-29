/**
 * Java. ShootingBall
 *  Class Ball
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Aug 29, 2017
 */
import java.awt.Graphics;

class Ball {
    private int x, y, radius, angle;
    private int width, heigth;

    Ball(int x, int y, int radius, int angle, int width, int heigth) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angle = angle;
        this.width = width;
        this.heigth = heigth;
    }

    private double getDX(int points) {
        return points * Math.cos(Math.toRadians(angle));
    }

    private double getDY(int points) {
        return points * Math.sin(Math.toRadians(angle));
    }

    void forward(int points) {
        double dx, dy;
        points++;
        do {
            points--;
            dx = getDX(points);
            dy = getDY(points);
        } while (isAbroad(x + (int)dx, y + (int)dy));
        x += (int) dx;
        y += (int) dy;
    }

    void backward(int points) {
        double dx, dy;
        points++;
        do {
            points--;
            dx = getDX(points);
            dy = getDY(points);
        } while (isAbroad(x - (int)dx, y - (int)dy));
        x -= (int) dx;
        y -= (int) dy;
    }

    boolean isAbroad(int x, int y) {
        return (x - radius < 0) || (y - radius < 0)
            || (x + radius > width) || (y + radius > heigth);
    }

    void turnLeft(int angle) {
        this.angle -= angle;
    }

    void turnRight(int angle) {
        this.angle += angle;
    }

    int getXPill() {
        return (int)(x + getDX(radius + 4));
    }

    int getYPill() {
        return (int)(y + getDY(radius + 4));
    }

    int getAngle() {
        return angle;
    }

    void paint(Graphics g) {
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
        g.drawLine(x + (int)getDX(radius - 4), y + (int)getDY(radius - 4),
            x + (int)getDX(radius + 4), y + (int)getDY(radius + 4));
    }
}