import java.awt.Graphics;

public class Starship {
    private int x, y, width, height;

    public Starship(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 60;
    }

    public void control(int code) {
        switch (code) {
            case SpaceAvoider.KEY_LEFT:
                x -= 5;
                if (x < width / 2)
                    x = width / 2;
                break;
            case SpaceAvoider.KEY_RIGHT:
                x += 5;
                if (x > SpaceAvoider.WINDOW_WIDTH - width / 2)
                    x = SpaceAvoider.WINDOW_WIDTH - width / 2;
                break;
        }
    }

    public void paint(Graphics g) {
        g.drawLine(x, y, x - width / 2, y + height);
        g.drawLine(x - width / 2, y + height, x + width / 2, y + height);
        g.drawLine(x + width / 2, y + height, x, y);
    }
}