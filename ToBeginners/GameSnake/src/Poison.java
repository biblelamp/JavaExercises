import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Poison {
    private List<Cell> poison = new ArrayList<>();
    private Random random = new Random();
    private GameSnake game;

    public Poison(GameSnake game) {
        this.game = game;
    }

    public boolean isPoison(int x, int y) {
        for (Cell cell : poison)
            if ((cell.getX() == x) && (cell.getY() == y))
                return true;
        return false;
    }

    public void add() {
        int x, y;
        do {
            x = random.nextInt(game.CANVAS_WIDTH);
            y = random.nextInt(game.CANVAS_HEIGHT);
        } while (isPoison(x, y) ||
                game.snake.isInsideSnake(x, y) ||
                game.food.isFood(x, y));
        poison.add(new Cell(x, y, game.POISON_COLOR));
    }

    public void paint(Graphics g) {
        for (Cell cell : poison)
            cell.paint(g);
    }
}