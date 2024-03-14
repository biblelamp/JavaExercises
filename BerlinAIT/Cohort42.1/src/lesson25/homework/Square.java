package lesson25.homework;

public class Square extends Figure {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public double calcArea() {
        return side * side;
    }
}
