package starbattle;

import java.awt.Graphics;
import java.awt.Image;

public class Asteroid {
    private int x;
    private int y;
    private int d;
    private Image image;

    public Asteroid(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    public Asteroid(int x, int y, int d, Image image) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getD() {
        return d;
    }

    public void paint(Graphics g) {
        //g.drawOval(x, y, d, d);
        g.drawImage(image, x, y, d, d, null);
    }
}
