import java.awt.Color;
import java.awt.Graphics;

/**
 * Java. Katamari Damacy Game
 *  Class Circle: base class
 *
 * @author Sergey Iryupin
 * @version 0.2.1 dated Oct 15, 2018
 */

public class Circle {
    protected int x, y;
    protected int size;
    protected int direction;
    protected Color color;

    public Circle(int x, int y, int size, int direction, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.direction = direction;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.drawOval(x, y, size, size);
    }
}