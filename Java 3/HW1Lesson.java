/**
 * Java. Level 3. Lesson 1. Homework
 *
 * 1. Write a method which changes the places of the two elements of the array.
 *    (array can be of any reference type)
 * 2. Write method which converts an array in ArrayList
 * 3. "Big task" about fruits (apples, oranges) and baskets (box)
 *
 * @author Sergey Iryupin
 * @version Feb 03, 2018
 */
import java.util.ArrayList;
import java.util.Arrays;

public class HW1Lesson {

    public static void main(String[] args) {
        HW1Lesson hw = new HW1Lesson();

        // tasks 1, 2

        String[] strings = {"First", "Second", "Third", "Fourth", "Five"};
        System.out.println(hw.convertArrayToList(strings)); // task 2
        hw.swapElements(0, 2, strings);
        System.out.println(Arrays.toString(strings));
        
        Integer[] numbers = {1, 2, 3, 4, 5, 6};
        System.out.println(hw.convertArrayToList(numbers)); // task 2
        hw.swapElements(0, 2, numbers);
        System.out.println(Arrays.toString(numbers));

        // task 3

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        for (int i = 0; i < 5; i++) {
            appleBox.add(new Apple());
            orangeBox.add(new Orange());
        }
        System.out.println(appleBox.getFruits());
        System.out.println(orangeBox.getFruits());

        Box<Apple> appleBox1 = new Box<>();
        appleBox1.add(new Apple());
        appleBox.moveAllToNextBox(appleBox1);
        System.out.println(appleBox.getFruits());
        System.out.println(appleBox1.getFruits());
    }

    private <T> void swapElements(int idx1, int idx2, T[] array) {
        T tmp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = tmp;
    }

    private <T> ArrayList<T> convertArrayToList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}

class Box<T extends Fruit> {
    private ArrayList<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void addFruits(ArrayList<T> fruits) {
        this.fruits.addAll(fruits);
    }

    public ArrayList<T> getFruits() {
        return fruits;
    }

    public float getWeight() {
        float totalWeight = 0.0f;
        for (T fruit : fruits)
            totalWeight += fruit.getWeight();
        return (float)Math.ceil(totalWeight * 100)/100;
    }

    public boolean compare(Box<?> box) {
        return Float.compare(getWeight(), box.getWeight()) == 0;
    }

    public void moveAllToNextBox(Box<T> box) {
        box.addFruits(getFruits());
        fruits.clear();
    }
}

class Apple extends Fruit {
    public Apple() {
        super(0.7f + (float)Math.random()/3);
    }
}

class Orange extends Fruit {
    public Orange() {
        super(1.2f + (float)Math.random()/3);
    }
}

abstract class Fruit {
    protected float weight;

    public Fruit(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "=" + Math.ceil(weight * 100)/100;
    }
}