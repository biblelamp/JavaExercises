package lesson22;

public class TriangleRectangle extends Shape2D {

    @Override
    public double perimeter() {
        return a + b + Math.sqrt(a*a + b*b);
    }

    @Override
    public double area() {
        return a * b / 2.;
    }
}
