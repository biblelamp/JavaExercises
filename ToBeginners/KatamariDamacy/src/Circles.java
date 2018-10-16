import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 * Java. Katamari Damacy Game
 *  Class Circles: enemies
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Oct 16, 2018
 */

public class Circles {
    private LinkedList<Circle> circles;
    private Random random;

    public Circles(KatamariDamacy katamariDamacy) {
        random = new Random();
        circles = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            Circle circle;
            do {
                int size = random.nextInt(30) + 20;
                int x = random.nextInt(KatamariDamacy.WIDTH - size);
                int y = random.nextInt(KatamariDamacy.HEIGHT - size);
                circle = new Circle(x, y, size, KatamariDamacy.KEY_SPACE, Color.black);
            } while (isCrossing(circle) || isCrossing(katamariDamacy.hero));
            circles.add(circle);
        }
    }

    public boolean isCrossing(Circle circle) {
        for (Circle item : circles) {
            double distance = Math.sqrt(Math.pow(item.getX() - circle.getX(), 2) +
                    Math.pow(item.getY() - circle.getY(), 2));
            if (item.getRadius() + circle.getRadius() >= distance)
                return true;
        }
        return false;
    }

    public void paint(Graphics g) {
        for (Circle circle : circles)
            circle.paint(g);
    }
}
