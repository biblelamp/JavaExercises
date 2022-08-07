package starbattle;

import java.awt.Color;
import java.awt.Graphics;

public class Star {
    private int x;
    private int y;
    private int d;

    public Star(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, d, d);
    }
}
