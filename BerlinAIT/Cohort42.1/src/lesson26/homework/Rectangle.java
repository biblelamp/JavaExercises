package lesson26.homework;

public class Rectangle extends Figure {
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
