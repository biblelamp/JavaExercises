/**
 * Java. Game Battle Ship
 * Class: Ships
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Aug 22, 2017
 */
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

class Ships {
    private final int CELL_SIZE;
    private ArrayList<Ship> ships = new ArrayList<Ship>(); // array for ship
    private final int[] PATTERN = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; // pattern for ships
    private Random random;
    private boolean hide;

    Ships(int fieldSize, int cellSize, boolean hide) {
        random = new Random();
        for (int i = 0; i < PATTERN.length; i++) {
            Ship ship;
            do {
                int x = random.nextInt(fieldSize);
                int y = random.nextInt(fieldSize);
                int position = random.nextInt(2);
                ship = new Ship(x, y, PATTERN[i], position);
            } while (ship.isOutOfField(0, fieldSize - 1) || isOverlayOrTouch(ship));
            ships.add(ship);
        }
        CELL_SIZE = cellSize;
        this.hide = hide;
    }

    boolean isOverlayOrTouch(Ship ctrlShip) {
        for (Ship ship : ships) if (ship.isOverlayOrTouch(ctrlShip)) return true;
        return false;
    }

    boolean checkHit(int x, int y) {
        for (Ship ship : ships) if (ship.checkHit(x, y)) return true;
        return false;
    }

    boolean checkSurvivors() {
        for (Ship ship : ships) if (ship.isAlive()) return true;
        return false;
    }

    void paint(Graphics g) {
        for (Ship ship : ships) ship.paint(g, CELL_SIZE, hide);
    }
}