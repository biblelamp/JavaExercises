import java.util.Random;

public class Food extends Point {
    private Random random;
    private Snake snake;

    public Food(Snake snake) {
        super(-1, -1);
        color = GameSnake.FOOD_COLOR;
        random = new Random();
        this.snake = snake;
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
        } while (snake.isCrossItself(x, y));
        set(x, y);
    }
}