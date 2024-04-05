package lesson32.homework;

import java.util.ArrayList;
import java.util.List;

public class Box<F extends Fruit> {
    private List<F> fruits = new ArrayList<>();

    public void add(F fruit) {
        fruits.add(fruit);
    }

    public void addAll(List<F> fruits) {
        this.fruits.addAll(fruits);
    }

    public float getWeight() {
        float weight = 0;
        for (F fruit : fruits) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public int compareTo(Box<?> box) {
        return (int) Math.signum(getWeight() - box.getWeight());
    }

    public void moveTo(Box<F> box) {
        box.addAll(fruits);
        fruits.clear();
    }

    @Override
    public String toString() {
        return fruits.toString();
    }
}
