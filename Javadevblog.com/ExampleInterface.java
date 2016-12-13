class ExampleInterface {

    public static void main(String[] args) {
        Shape shape = new Circle(10);
        System.out.println(shape.draw());
        System.out.println("Area = " + shape.getArea());

        shape = new Rectangle(10, 10);
        System.out.println(shape.draw());
        System.out.println("Area = " + shape.getArea());
    }
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String draw() {
        return "Draw a circle";
    }

    @Override
    public double getArea(){
        return Math.PI * radius*radius;
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String draw() {
        return "Draw a rectangle";
    }

    @Override
    public double getArea() {
        return height * width;
    }
}

interface Shape { // define interface
    String draw();
    double getArea();
}