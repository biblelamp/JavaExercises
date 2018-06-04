import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    List<Point> snake = new ArrayList<>();
    int direction;

    public Snake(int x, int y, int length, int direction) {
        for (int i = 0; i < length; i++) {
            Point point = new Point(x - i, y);
            snake.add(point);
        }
        this.direction = direction;
    }

    public void setDirection(int direction) {
        if ((direction >= GameSnake.LEFT) && (direction <= GameSnake.DOWN)) {
            if (Math.abs(this.direction - direction) != 2) {
                this.direction = direction;
            }
        }
    }

    public void move() {
        int x = snake.get(0).getX();
        int y = snake.get(0).getY();
        if (direction == GameSnake.LEFT)
            x--;
        if (direction == GameSnake.RIGHT)
            x++;
        if (direction == GameSnake.UP)
            y--;
        if (direction == GameSnake.DOWN)
            y++;
        if (x < 0)
            x = GameSnake.WIDTH - 1;
        if (x == GameSnake.WIDTH)
            x = 0;
        if (y < 0)
            y = GameSnake.HEIGHT - 1;
        if (y == GameSnake.HEIGHT)
            y = 0;
        snake.add(0, new Point(x, y));
        snake.remove(snake.size() - 1);
    }

    public void paint(Graphics g) {
        for (Point point : snake)
            point.paint(g);
    }
}