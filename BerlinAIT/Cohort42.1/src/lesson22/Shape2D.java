package lesson22;

public class Shape2D {
    protected int a, b;

    public Shape2D(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public double perimeter() {
        return (a + b) * 2;
    }

    public double area() {
        return a * b;
    }
}
