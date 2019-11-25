import java.awt.Color;
import java.awt.Graphics;

class Ball {
    int x, y, d;
    Color color;

    Ball(int x, int y, int d, Color color) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.color = color;
    }

    void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    boolean isPointInside(int x, int y) {
        double dx = this.x + this.d/2 - x;
        double dy = this.y  + this.d/2 - y;
        double d = Math.sqrt(dx * dx + dy * dy);
        return d < this.d/2;
    }

    void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, d, d);
        g.setColor(Color.black);
        g.drawOval(x, y, d, d);
    }
}