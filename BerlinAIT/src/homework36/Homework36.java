package homework36;

/**
 * Homework #36
 *
 * @author Sergey Iryupin
 * @version 21 Jun 2023
 */

public class Homework36 {
    public static void main(String[] args) {
        Point2D p = new Point2D(12, 20);
        ColorPoint2D cp = new ColorPoint2D(12, 20);
        System.out.println(p.equals(cp));
        System.out.println(cp.equals(p));
    }
}
