/**
 * Java. Level 1. Lesson 7. Example of homework
 *
 * @author Sergey Iryupin
 * @version dated 11 Mar 2017
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
    }
}

class Cat {
    private String name;
    private int volume; // ability to eat for 1 time
    private boolean satiety; // satiety status

    Cat(String name, int volume) {
        this.name = name;
        this.volume = volume;
        satiety = false;
    }

    void eat(Plate plate) {
        //satiety = plate.decreaseFood(volume);
        if (plate.getQuantity() >= volume)
            satiety = plate.decreaseFood(volume);
    }

    @Override
    public String toString() {
        return name + " {volume: " + volume + " satiety: " + satiety + "}";
    }
}

class Plate {
    private int food;

    Plate(int food) {
        this.food = food;
    }

    int getQuantity() {
        return food;
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