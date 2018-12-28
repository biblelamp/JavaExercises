import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Java. Classic Game Snake
 *  Class Poison: it's deadly for a snake
 *
 * @author Sergey Iryupin
 * @version dated Dec 28, 2018
 */

public class Poison {
    private List<Cell> poison;
    private Random random;
    private GameSnake gameSnake;

    public Poison(GameSnake gameSnake) {
        poison = new ArrayList<>();
        random = new Random();
        this.gameSnake = gameSnake;
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
            x = random.nextInt(gameSnake.CANVAS_WIDTH);
            y = random.nextInt(gameSnake.CANVAS_HEIGHT);
        } while (isPoison(x, y) ||
                gameSnake.snake.isInSnake(x, y) ||
                gameSnake.food.isFood(x, y));
        poison.add(new Cell(x, y, gameSnake.CELL_SIZE, gameSnake.POISON_COLOR));
    }

    public void paint(Graphics2D g) {
        for (Cell cell : poison)
            cell.paint(g);
    }
}