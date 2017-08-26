/**
 * Java. ShootingBall
 *  Class Pill
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Aug 26 2017
 */
import java.awt.Graphics;

class Pill {
    private int x, y, angle;

    Pill() {
        x = y = -1;
    }

    Pill(int x, int y, int angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    void move(int distance) {
        if (x > -1 || y > -1) {
            double dx = distance * Math.cos(Math.toRadians(angle));
            double dy = distance * Math.sin(Math.toRadians(angle));
            x += (int) dx;
            y += (int) dy;
        }
    }

    void paint(Graphics g) {
        if (x > -1 || y > -1) 
            g.drawOval(x - 2, y - 2, 4, 4);
    }
}