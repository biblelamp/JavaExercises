/**
 * Java. Level 1. Lesson 7. Example of homework
 *
 * @author Sergey Iryupin
 * @version dated Oct 20, 2017
 */
class HW7Lesson {

    public static void main(String[] args) {
        Cat[] cats = {
            new Cat("Barsik", 15), new Cat("Murzik", 10), new Cat("Vasily", 20)
        };
        Plate plate = new Plate(50, 30);
        System.out.println(plate);

        // feeding cats first time
        for (Cat cat : cats) {
            cat.eat(plate);
            System.out.println(cat);
        }

        // adding food in the plate
        System.out.println(plate);
        plate.add(40);
        System.out.println(plate);

        // feeding cats second time
        for (Cat cat : cats) {
            cat.setFullness(false);
            cat.eat(plate);
            System.out.println(cat);
        }
        System.out.println(plate);
    }
}

class Cat {
    protected String name;
    protected int appetite; // ability to eat for 1 time
    protected boolean fullness; // satiety status

    Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        fullness = false;
    }

    void setFullness(boolean status) {
        fullness = status;
    }

    void eat(Plate plate) {
        if (!fullness)
            fullness = plate.decreaseFood(appetite);
    }

    @Override
    public String toString() {
        return "{name=" + name + ", appetite=" +
            appetite + ", fullness=" + fullness + "}";
    }
}

class Plate {
    protected int volume;
    protected int food;

    Plate(int volume, int food) {
        this.volume = volume;
        this.food = food;
    }

    boolean decreaseFood(int portion) {
        if (food < portion) return false;
        food -= portion;
        return true;
    }

    void add(int food) {
        if (this.food + food <= volume)
            this.food += food;
    }

    @Override
    public String toString() {
        return "plate: " + food;
    }
}