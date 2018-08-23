/**
 * Java. Classic Game Snake
 *  Class Cell: minimal building element
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Aug 23, 2018
 */
import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    protected int x, y;                       // object coordinates
    protected int size;                       // object size in px
    protected Color color;                    // object color

    public Cell(int x, int y, int size, Color color) {
        set(x, y);                            // init coordinates
        this.size = size;                     // init size
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
        g.fillOval(x * size, y * size,        // upper left corner
                size, size);                  // width and height
    }
}