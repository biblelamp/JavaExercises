import java.util.Random;

public class Food extends Point {
    Random random;

    public Food() {
        super(-1, -1);
        color = GameSnake.FOOD_COLOR;
        random = new Random();
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
        } while (false);
        set(x, y);
    }
}