package lesson27;

public class Triangle implements Figure {
    private int a, b, c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // it is a right triangle
    public Triangle(int a, int b) {
        this.a = a;
        this.b = b;
        this.c = -1;
    }

    @Override
    public double calcArea() {
        if (c > 0) {
            double p = calcPerimeter() / 2;
            return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        } else {
            return a * b / 2;
        }
    }

    @Override
    public double calcPerimeter() {
        if (c > 0) {
            return a + b + c;
        } else {
            return a + b + Math.sqrt(a*a + b*b);
        }
    }
}
