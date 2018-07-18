import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> snake = new ArrayList<>();
    private int direction;
    private GameSnake game;

    public Snake(int x, int y, int length, int direction, GameSnake gameSnake) {
        for (int i = 0; i < length; i++)
            snake.add(new Point(x - i, y, gameSnake.SNAKE_COLOR));
        this.direction = direction;
        this.game = gameSnake;
    }

    public void setDirection(int direction) {
        if ((direction >= game.KEY_LEFT) && (direction <= game.KEY_DOWN))
            if (Math.abs(this.direction - direction) != 2)
                this.direction = direction;
    }

    public boolean isInsideSnake(int x, int y) {
        for (Point point : snake)
            if ((point.getX() == x) && (point.getY() == y))
                return true;
        return false;
    }

    public void move() {
        int x = snake.get(0).getX();
        int y = snake.get(0).getY();
        switch (direction) {
            case GameSnake.KEY_LEFT: x--;
                if (x < 0)
                    x = game.WIDTH - 1;
                break;
            case GameSnake.KEY_RIGHT: x++;
                if (x == game.WIDTH)
                    x = 0;
                break;
            case GameSnake.KEY_UP: y--;
                if (y < 0)
                    y = game.HEIGHT - 1;
                break;
            case GameSnake.KEY_DOWN: y++;
                if (y == game.HEIGHT)
                    y = 0;
                break;
        }
        if (isInsideSnake(x, y) ||            // snake shouldn't cross itself
                game.poison.isPoison(x, y)) { // and eat poison
            game.gameOver = true;
            return;
        }
        snake.add(0, new Point(x, y, game.SNAKE_COLOR));
        if (game.food.isFood(x, y)) {
            game.food.eat();
            game.setTitle(game.TITLE_OF_PROGRAM + " : " + snake.size());
        } else
            snake.remove(snake.size() - 1);
    }

    public void paint(Graphics g) {
        for (Point point : snake)
            point.paint(g);
    }
}