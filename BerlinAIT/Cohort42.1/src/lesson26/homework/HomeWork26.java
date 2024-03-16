package lesson26.homework;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #26
 *
 * @author Sergey Iryupin
 * @version 15-Mar-24
 */
public class HomeWork26 {
    public static void main(String[] args) {
        double sumArea = 0;
        double sumPerimeter = 0;

        Figure[] figures = {new Circle(5), new Triangle(5, 5, 5), new Rectangle(4, 5)};

        for (Figure figure : figures) {
            sumArea += figure.calcArea();
            sumPerimeter += figure.calcPerimeter();
        }

        System.out.println("allArea: " + sumArea + ", allPerimeter: " + sumPerimeter);
    }
}
