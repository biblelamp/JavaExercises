package lesson27;

public class Rectangle implements Figure {
    private int a, b;

    public Rectangle(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double calcArea() {
        return a * b;
    }

    @Override
    public double calcPerimeter() {
        return (a + b) * 2;
    }
}
