import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 * Java. Katamari Damacy Game
 *  Class Circles: enemies
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 14, 2018
 */

public class Circles {
    private LinkedList<Circle> circles;
    private Random random;

    public Circles() {
    }

    public void paint(Graphics g) {
        for (Circle circle : circles)
            circle.paint(g);
    }
}
