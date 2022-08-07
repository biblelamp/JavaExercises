package starbattle;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Asteroid {

    public Bullet(int x, int y, int d) {
        super(x, y, d);
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.drawOval(getX(), getY(), getD(), getD());
    }
}
