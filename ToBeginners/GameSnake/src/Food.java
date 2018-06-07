import java.util.Random;

public class Food extends Point {
    private Random random;
    private GameSnake game;

    public Food(GameSnake game) {
        super(-1, -1);
        color = GameSnake.FOOD_COLOR;
        random = new Random();
        this.game = game;
    }

    public boolean isFood(int x, int y) {
        return (this.x == x) && (this.y == y);
    }

    public boolean isEaten() {
        return getX() == -1;
    }

    public void eat() {
        set(-1, -1);
    }

    public void show() {
        int x, y;
        do {
            x = random.nextInt(GameSnake.WIDTH);
            y = random.nextInt(GameSnake.HEIGHT);
        } while (game.snake.isInsideSnake(x, y) ||
                game.poison.isPoison(x, y));
        set(x, y);
    }
}