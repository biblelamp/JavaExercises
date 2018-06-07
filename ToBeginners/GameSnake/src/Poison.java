import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Poison {
    private List<Point> poison = new ArrayList<>();
    private Random random = new Random();
    private GameSnake game;

    public Poison(GameSnake game) {
        this.game = game;
    }

    public boolean isPoison(int x, int y) {
        for (Point point : poison)
            if ((point.getX() == x) && (point.getY() == y))
                return true;
        return false;
    }

    public void add() {
        int x, y;
        do {
            x = random.nextInt(GameSnake.WIDTH);
            y = random.nextInt(GameSnake.HEIGHT);
        } while (isPoison(x, y) ||
                game.snake.isInsideSnake(x, y) ||
                game.food.isFood(x, y));
        poison.add(new Point(x, y, GameSnake.POISON_COLOR));
    }

    public void paint(Graphics g) {
        for (Point point : poison)
            point.paint(g);
    }
}