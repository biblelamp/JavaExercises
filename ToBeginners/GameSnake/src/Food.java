import java.util.Random;

/**
 * Java. Classic Game Snake
 *  Class Food: snake food
 *
 * @author Sergey Iryupin
 * @version dated Mar 09, 2019
 */

public class Food extends Cell {
    //protected int x, y;                   // fields that are inherited
    //protected int size;                   //   from the Cell class
    //protected Color color;
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

    public void appear() {
        int x, y;
        do {
            x = random.nextInt(gameSnake.CANVAS_WIDTH);
            y = random.nextInt(gameSnake.CANVAS_HEIGHT);
        } while (gameSnake.isCoordinatesBusy(x, y));
        set(x, y);
    }
}