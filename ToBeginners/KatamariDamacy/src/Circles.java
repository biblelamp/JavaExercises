import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 * Java. Katamari Damacy Game
 *  Class Circles: enemies
 *
 * @author Sergey Iryupin
 * @version 0.4 dated Oct 18, 2018
 */

public class Circles {
    private LinkedList<Circle> circles;
    private Random random;

    public Circles(KatamariDamacy katamariDamacy) {
        random = new Random();
        circles = new LinkedList<>();
        for (int i = 0; i < 40; i++) {
            Circle circle;
            do {
                int size = random.nextInt(20) + 20;
                int x = random.nextInt(KatamariDamacy.WIDTH - size);
                int y = random.nextInt(KatamariDamacy.HEIGHT - size);
                int direction = 37 + random.nextInt(3);
                circle = new Circle(x, y, size, direction, Color.black);
            } while (isCrossing(circle) || isCrossing(katamariDamacy.hero));
            circles.add(circle);
        }
    }

    public void remove(Circle circle) {
        if (circle != null)
            circles.remove(circle);
    }

    public boolean isCrossing(Circle object) {
        for (Circle circle : circles)
            if (circle.isCrossing(object))
                return true;
        return false;
    }

    public Circle getCrossing(Circle object) {
        for (Circle circle : circles)
            if (circle.isCrossing(object))
                return circle;
        return null;
    }

    public void move() {
        for (Circle circle : circles)
            circle.move();
    }

    public void paint(Graphics g) {
        for (Circle circle : circles)
            circle.paint(g);
    }
}
