/**
 * Java. Level 1. Lesson 7. Example of homework
 *
 * @author Sergey Iryupin
 * @version dated Aug 19, 2017
 */
public class HW7Lesson {

    public static void main(String[] args) {
        Cat[] cats = {
            new Cat("Barsik", 15), new Cat("Murzik", 10), new Cat("Vasily", 20)
        };
        Plate plate = new Plate(30);
        System.out.println(plate);
        for (Cat cat : cats) {
            cat.eat(plate);
            System.out.println(cat);
        }
        System.out.println(plate);
        plate.add(40);
        System.out.println(plate);
        for (Cat cat : cats) {
            cat.setFullness(false);
            cat.eat(plate);
            System.out.println(cat);
        }
        System.out.println(plate);
    }
}

class Cat {
    private String name;
    private int volume; // ability to eat for 1 time
    private boolean fullness; // satiety status

    Cat(String name, int volume) {
        this.name = name;
        this.volume = volume;
        fullness = false;
    }

    void setFullness(boolean status) {
        fullness = status;
    }

    void eat(Plate plate) {
        if (!fullness)
            fullness = plate.decreaseFood(volume);
    }

    @Override
    public String toString() {
        return name + " {volume: " + volume + " fullness: " + fullness + "}";
    }
}

class Plate {
    private int food;

    Plate(int food) {
        this.food = food;
    }

    boolean decreaseFood(int portion) {
        if (food < portion) return false;
        food -= portion;
        return true;
    }

    void add(int food) {
        this.food += food;
    }

    @Override
    public String toString() {
        return "plate: " + food;
    }
}