package homework4;

public class Cat {
    private String name;
    protected int appetite; // ability to eat for 1 time
    protected boolean fullness; // satiety status

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        fullness = false;
    }

    public void setFullness(boolean status) {
        fullness = status;
    }

    public void eat(Plate plate) {
        if (!fullness) {
            fullness = plate.decreaseFood(appetite);
        }
    }

    @Override
    public String toString() {
        return "{name=" + name + ", appetite=" +
                appetite + ", fullness=" + fullness + "}";
    }
}