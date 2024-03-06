package lesson22;

public class TriangleRectangle extends Shape2D {

    public TriangleRectangle(int a, int b) {
        super(a, b);
    }

    @Override
    public double perimeter() {
        return a + b + Math.sqrt(a*a + b*b);
    }

    @Override
    public double area() {
        return a * b / 2.;
    }

    @Override
    public boolean equals(Object obj) {
        return a == ((TriangleRectangle) obj).a && b == ((TriangleRectangle) obj).b;
    }
}
