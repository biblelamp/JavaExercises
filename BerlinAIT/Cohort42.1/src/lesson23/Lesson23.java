package lesson23;

import lesson22.TriangleRectangle;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #23
 *
 * @version 6-Mar-24
 */
public class Lesson23 {
    public static void main(String[] args) {
        Lesson23 lesson23 = new Lesson23();
        // toString()
        System.out.println(lesson23);
        // hashCode()
        System.out.println(Integer.toHexString(lesson23.hashCode()));
        // getClass
        System.out.println(lesson23.getClass().getSimpleName());
        // equals
        lesson23.equals(lesson23);
        String s1 = "qwerty";
        String s2 = "qwerty";
        String s3 = "qwert";
        s2 = s3 + "y";
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s3);

        TriangleRectangle tr1 = new TriangleRectangle(4, 6);
        TriangleRectangle tr2 = new TriangleRectangle(4, 6);
        System.out.println("triangles: " + tr1.equals(tr2));

        // notify, notifyAll, wait - multithreading
    }

    @Override
    public String toString() {
        return "Lesson23{}";
    }
}
