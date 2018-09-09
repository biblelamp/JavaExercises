/**
 * Java. Classic Game Snake
 *  Class Food: snake food
 *
 * @author Sergey Iryupin
 * @version dated Sep 09, 2018
 */
import java.util.Random;

public class Food extends Cell {
    //protected int x, y;                   // fields that are inherited
    //protected Color color;                //   from the Cell class
    private GameSnake gameSnake;
    private Random random;

    //public void set(int x, int y)         // methods that are inherited
    //public int getX()                     //   from the Cell class
    //public int getY()
    //public void paint(Graphics g)

    public Food(GameSnake gameSnake) {      // constructor
        super(-1, -1, gameSnake.CELL_SIZE, gameSnake.FOOD_COLOR);
        this.gameSnake = gameSnake;
        random = new Random();
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

    public void setNewPlace() {
        int x, y;
        do {
            x = random.nextInt(gameSnake.CANVAS_WIDTH);
            y = random.nextInt(gameSnake.CANVAS_HEIGHT);
        } while (gameSnake.snake.isInSnake(x, y) ||
                gameSnake.poison.isPoison(x, y));
        set(x, y);
    }
}