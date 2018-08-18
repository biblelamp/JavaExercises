/**
 * Java. Classic Game Snake
 *  A class that implements a snake
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Aug 02, 2018
 */
import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Cell> snake;
    private int direction;
    private GameSnake gameSnake;

    public Snake(int x, int y, int length, int direction, GameSnake gameSnake) {
        snake = new LinkedList<>();
        for (int i = 0; i < length; i++)
            snake.add(new Cell(x - i, y, gameSnake.SNAKE_COLOR));
        this.direction = direction;
        this.gameSnake = gameSnake;
    }

    public void setDirection(int direction) {
        if ((direction >= gameSnake.KEY_LEFT) && (direction <= gameSnake.KEY_DOWN))
            if (Math.abs(this.direction - direction) != 2)
                this.direction = direction;
    }

    public boolean isInsideSnake(int x, int y) {
        for (Cell cell : snake)
            if ((cell.getX() == x) && (cell.getY() == y))
                return true;
        return false;
    }

    public void move() {
        int x = snake.getFirst().getX();
        int y = snake.getFirst().getY();
        switch (direction) {
            case GameSnake.KEY_LEFT: x--;
                if (x < 0)
                    x = gameSnake.CANVAS_WIDTH - 1;
                break;
            case GameSnake.KEY_RIGHT: x++;
                if (x == gameSnake.CANVAS_WIDTH)
                    x = 0;
                break;
            case GameSnake.KEY_UP: y--;
                if (y < 0)
                    y = gameSnake.CANVAS_HEIGHT - 1;
                break;
            case GameSnake.KEY_DOWN: y++;
                if (y == gameSnake.CANVAS_HEIGHT)
                    y = 0;
                break;
        }
        if (isInsideSnake(x, y) ||                 // snake shouldn't cross itself
                gameSnake.poison.isPoison(x, y)) { // and eat poison
            gameSnake.gameOver = true;
            return;
        }
        snake.addFirst(new Cell(x, y, gameSnake.SNAKE_COLOR)); // new head of snake
        if (gameSnake.food.isFood(x, y)) {
            gameSnake.food.eat();
            gameSnake.setTitle(gameSnake.TITLE_OF_PROGRAM + " : " + snake.size());
        } else
            snake.removeLast();
    }

    public void paint(Graphics g) {
        for (Cell cell : snake)
            cell.paint(g);
    }
}