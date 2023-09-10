package homework6;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void addAll(List<T> fruits) {
        this.fruits.addAll(fruits);
    }

    public List<T> getFruits() {
        return fruits;
    }

    public float getWeight() {
        float total = 0;
        for (T fruit : fruits) {
            total += fruit.getWeight();
        }
        return total;
    }

    public boolean compare(Box<?> box) {
        return this.getWeight() == box.getWeight();
    }

    public void moveAllToNextBox(Box<T> box) {
        box.addAll(fruits);
        fruits.clear();
    }
}