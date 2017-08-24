/**
 * Java. ShootingBall
 *  Class Ball
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Aug 24, 2017
 */
import java.awt.Graphics;

class Ball {
    private int x, y, radius;
    private double angle;

    Ball(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius / 2;
        angle = 270;
    }

    void forward(int points) {
        double dx = (points) * Math.cos(angle * Math.PI/180);
        double dy = (points) * Math.sin(angle * Math.PI/180);
        x += (int) dx;
        y += (int) dy;
    }

    void backward(int points) {
        double dx = (points) * Math.cos(angle * Math.PI/180);
        double dy = (points) * Math.sin(angle * Math.PI/180);
        x -= (int) dx;
        y -= (int) dy;
    }

    void turnLeft(int angle) {
        this.angle -= angle;
    }

    void turnRight(int angle) {
        this.angle += angle;
    }

    void paint(Graphics g) {
        double dx = (radius + 5) * Math.cos(angle * Math.PI/180);
        double dy = (radius + 5) * Math.sin(angle * Math.PI/180);
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
        g.drawLine(x, y, x + (int)dx, y + (int)dy);
    }
}