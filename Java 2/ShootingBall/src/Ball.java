/**
 * Java. ShootingBall
 *  Class Ball
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Aug 26, 2017
 */
import java.awt.Graphics;

class Ball {
    private int x, y, radius, angle;

    Ball(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        angle = 270;
    }

    void forward(int points) {
        double dx = points * Math.cos(Math.toRadians(angle));
        double dy = points * Math.sin(Math.toRadians(angle));
        x += (int) dx;
        y += (int) dy;
    }

    void backward(int points) {
        double dx = points * Math.cos(Math.toRadians(angle));
        double dy = points * Math.sin(Math.toRadians(angle));
        x -= (int) dx;
        y -= (int) dy;
    }

    void turnLeft(int angle) {
        this.angle -= angle;
    }

    void turnRight(int angle) {
        this.angle += angle;
    }

    int getXPill() {
        return (int)(x + (radius + 4) * Math.cos(Math.toRadians(angle)));
    }

    int getYPill() {
        return (int)(y + (radius + 4) * Math.sin(Math.toRadians(angle)));
    }

    int getAngle() {
        return angle;
    }

    void paint(Graphics g) {
        double x1 = (radius - 4) * Math.cos(Math.toRadians(angle));
        double y1 = (radius - 4) * Math.sin(Math.toRadians(angle));
        double dx = (radius + 4) * Math.cos(Math.toRadians(angle));
        double dy = (radius + 4) * Math.sin(Math.toRadians(angle));
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
        g.drawLine(x + (int)x1, y + (int)y1, x + (int)dx, y + (int)dy);
    }
}