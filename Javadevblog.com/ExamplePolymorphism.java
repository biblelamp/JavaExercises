class ExamplePolymorphism {

    public static void main(String[] args) {
        Figure[] figures = {new Circle(), new Square(), new Triangle()};
        for (Figure figure : figures)
            figure.draw();
    }
}

class Triangle extends Figure {
    @Override
    void draw() {
        System.out.println("triangle");
    }
}

class Square extends Figure {
    @Override
    void draw() {
        System.out.println("square");
    }
}

class Circle extends Figure {
    @Override
    void draw() {
        System.out.println("circle");
    }
}

class Figure {
    void draw() {
        System.out.println("figure");
    }
}