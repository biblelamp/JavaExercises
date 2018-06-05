import java.awt.*;

public class Point {
    protected int x, y;
    protected Color color;

    public Point(int x, int y) {
        set(x, y);
        color = GameSnake.SNAKE_COLOR;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x * GameSnake.POINT_SIZE, y * GameSnake.POINT_SIZE,
                GameSnake.POINT_SIZE, GameSnake.POINT_SIZE);
    }
}