/**
 * Java. ShootingBall
 *  Class Pill
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Aug 30, 2017
 */
import java.awt.Graphics;
import java.awt.Color;

class Pill {
    private int x, y, angle;
    private Color color;

    Pill() {
    }

    Pill(int x, int y, int angle, Color color) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.color = color;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void move(int distance) {
        double dx = distance * Math.cos(Math.toRadians(angle));
        double dy = distance * Math.sin(Math.toRadians(angle));
        x += (int) dx;
        y += (int) dy;
    }

    void paint(Graphics g) {
        g.setColor(color);
        g.drawOval(x - 2, y - 2, 4, 4);
    }
}