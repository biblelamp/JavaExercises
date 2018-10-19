import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 * Java. Katamari Damacy Game
 *  Class Circles: enemies
 *
 * @author Sergey Iryupin
 * @version 0.5.2 dated Oct 19, 2018
 */

public class Circles {
    private LinkedList<Circle> circles;
    private Random random;

    public Circles(int number, Hero hero) {
        random = new Random();
        circles = new LinkedList<>();
        add(number, hero);
    }

    public void add(int number, Hero hero) {
        for (int i = 0; i < number; i++) {
            Circle circle;
            do {
                int size = random.nextInt(20) + 20;
                int x = random.nextInt(KatamariDamacy.WIDTH - size);
                int y = random.nextInt(KatamariDamacy.HEIGHT - size);
                int direction = 37 + random.nextInt(3);
                circle = new Circle(x, y, size, direction, Color.black);
            } while (getCrossing(circle) != null ||
                    getCrossing(hero) != null);
            circles.add(circle);
        }
    }

    public void remove(Circle circle) {
        if (circle != null)
            circles.remove(circle);
    }

    public Circle getCrossing(Circle object) {
        for (Circle circle : circles)
            if (circle.isCrossing(object) && !object.equals(circle))
                return circle;
        return null;
    }

    public void move() {
        for (Circle circle : circles)
            circle.move();
    }

    public void collisions() {
        for (Circle circle : circles) {
            Circle object = getCrossing(circle);
            if (object != null) {
                if (circle.getArea() < object.getArea()) {
                    object.setArea(object.getArea() + circle.getArea());
                    circles.remove(circle);
                } else {
                    circle.setArea(object.getArea() + circle.getArea());
                    circles.remove(object);
                }
                break;
            }
        }
    }

    public void clear() {
        circles.clear();
    }

    public int size() {
        return circles.size();
    }

    public void paint(Graphics g) {
        for (Circle circle : circles)
            circle.paint(g);
    }
}
