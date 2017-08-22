/**
 * Java. Game Battle Ship
 * Class: Shots
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Aug 22, 2017
 */
import java.awt.Graphics;
import java.util.ArrayList;

class Shots {
    private final int CELL_SIZE;
    private ArrayList<Shot> shots;

    Shots(int cellSize) {
        CELL_SIZE = cellSize;
        shots = new ArrayList<Shot>();
    }

    void add(int x, int y, boolean shot) {
        shots.add(new Shot(x, y, shot));
    }

    boolean hitSamePlace(int x, int y) {
        for (Shot shot : shots)
            if (shot.getX() == x && shot.getY() == y && shot.isShot())
                return true;
        return false;
    }

    Shot getLabel(int x, int y) {
        for (Shot label : shots)
            if (label.getX() == x && label.getY() == y && (!label.isShot()))
                return label;
        return null;
    }

    void removeLabel(Shot label) {
        shots.remove(label);
    }

    void paint(Graphics g) {
        for (Shot shot : shots) shot.paint(g, CELL_SIZE);
    }
}