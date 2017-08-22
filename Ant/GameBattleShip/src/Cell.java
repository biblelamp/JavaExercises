/**
 * Java. Game Battle Ship
 * Class: Cell
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Aug 22, 2017
 */
import java.awt.Color;
import java.awt.Graphics;

class Cell {
    private final Color RED = Color.red;
    private int x, y;
    private Color color;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
        color = Color.gray; // default color;
    }

    int getX() { return x; }
    int getY() { return y; }

    boolean checkHit(int x, int y) {
        if (this.x == x && this.y == y) {
            color = RED; // change color if hit
            return true;
        }
        return false;
    }

    boolean isAlive() {
        return color != RED; // judged by color
    }

    void paint(Graphics g, int cellSize, boolean hide) {
        if (!hide || (hide && color == RED)) {
            g.setColor(color);
            g.fill3DRect(x*cellSize + 1, y*cellSize + 1, cellSize - 2, cellSize - 2, true);
        }
    }
}