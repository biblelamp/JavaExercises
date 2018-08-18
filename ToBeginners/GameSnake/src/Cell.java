/**
 * Java. Classic Game Snake
 *  Class Cell: minimal building element
 *
 * @author Sergey Iryupin
 * @version dated Jul 18, 2018
 */
import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    protected int x, y;                       // object coordinates
    protected Color color;                    // object color

    public Cell(int x, int y, Color color) {  // constructor
        set(x, y);                            // init coordinates
        this.color = color;                   // init color
    }

    public void set(int x, int y) {           // set coordinates
        this.x = x;
        this.y = y;
    }

    public int getX() {                       // get the X coordinate
        return x;
    }

    public int getY() {                       // get the Y coordinate
        return y;
    }

    public void paint(Graphics g) {           // object rendering
        g.setColor(color);
        g.fillOval(x * GameSnake.CELL_SIZE,  // coordinates of the
                y * GameSnake.CELL_SIZE,     //    upper left corner
                GameSnake.CELL_SIZE,         // width
                GameSnake.CELL_SIZE);        // height
    }
}