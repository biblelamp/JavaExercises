package lesson4;

public class Cat {
    private final int appetite;

    public Cat(int appetite) {
        this.appetite = appetite;
    }

    public int getAppetite() {
        return appetite;
    }

    public void eat(Plate plate) {
        plate.decreaseFood(appetite);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "appetite=" + appetite +
                '}';
    }
}
