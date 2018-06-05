import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> snake = new ArrayList<>();
    private int direction;
    private GameSnake gameSnake;

    public Snake(int x, int y, int length, int direction, GameSnake gameSnake) {
        for (int i = 0; i < length; i++)
            snake.add(new Point(x - i, y));
        this.direction = direction;
        this.gameSnake = gameSnake;
    }

    public void setDirection(int direction) {
        if ((direction >= GameSnake.LEFT) && (direction <= GameSnake.DOWN))
            if (Math.abs(this.direction - direction) != 2)
                this.direction = direction;
    }

    private boolean meetFood(Food food) {
        return (snake.get(0).getX() == food.getX()) &&
               (snake.get(0).getY() == food.getY());
    }

    public void move() {
        int x = snake.get(0).getX();
        int y = snake.get(0).getY();
        switch (direction) {
            case GameSnake.LEFT: x--;
                if (x < 0)
                    x = GameSnake.WIDTH - 1;
                break;
            case GameSnake.RIGHT: x++;
                if (x == GameSnake.WIDTH)
                    x = 0;
                break;
            case GameSnake.UP: y--;
                if (y < 0)
                    y = GameSnake.HEIGHT - 1;
                break;
            case GameSnake.DOWN: y++;
                if (y == GameSnake.HEIGHT)
                    y = 0;
                break;
        }
        snake.add(0, new Point(x, y));
        if (meetFood(gameSnake.food)) {
            gameSnake.food.eat();
            gameSnake.setTitle(gameSnake.TITLE_OF_PROGRAM + " : " + snake.size());
        } else
            snake.remove(snake.size() - 1);
    }

    public void paint(Graphics g) {
        for (Point point : snake)
            point.paint(g);
    }
}