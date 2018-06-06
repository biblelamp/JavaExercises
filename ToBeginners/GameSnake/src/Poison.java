import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Poison {
    private List<Point> poison = new ArrayList<>();
    private Random random = new Random();

    public void add() {
        int x, y;
        do {
            x = random.nextInt(GameSnake.WIDTH);
            y = random.nextInt(GameSnake.HEIGHT);
        } while (false);
        poison.add(new Point(x, y, GameSnake.POISON_COLOR));
    }

    public void paint(Graphics g) {
        for (Point point : poison)
            point.paint(g);
    }
}