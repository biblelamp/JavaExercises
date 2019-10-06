import java.awt.Graphics;

public class Starship {
    private int x, y, width, height;

    public Starship(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 60;
    }

    public void paint(Graphics g) {
        g.drawLine(x, y, x - width / 2, y + height);
        g.drawLine(x - width / 2, y + height, x + width / 2, y + height);
        g.drawLine(x + width / 2, y + height, x, y);
    }
}