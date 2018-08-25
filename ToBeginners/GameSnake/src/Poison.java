/**
 * Java. Classic Game Snake
 *  Class Poison: it's deadly for a snake
 *
 * @author Sergey Iryupin
 * @version dated Aug 23, 2018
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Poison {
    private List<Cell> poison;
    private Random random;
    private GameSnake game;

    public Poison(GameSnake game) {
        poison = new ArrayList<>();
        random = new Random();
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
                game.snake.isInSnake(x, y) ||
                game.food.isFood(x, y));
        poison.add(new Cell(x, y, GameSnake.CELL_SIZE, game.POISON_COLOR));
    }

    public void paint(Graphics g) {
        for (Cell cell : poison)
            cell.paint(g);
    }
}