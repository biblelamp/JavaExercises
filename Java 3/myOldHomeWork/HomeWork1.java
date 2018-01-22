/**
 * Java. Level 3. Lesson 1. Homework
 * 1. Write a method that changes the places of the two elements of the array.
 * (Array can be of any reference type)
 * 2. Write method which converts an array in ArrayList
 * 3. "Big task" about fruit (apple, orange) and baskets (box)
 *
 * @author Sergey Iryupin
 * @version 12 Sep 2016
 */
import java.util.*;

public class HomeWork1 {

    public static void main(String[] args) {
        HomeWork1 hw = new HomeWork1();

        // stage 1

        String[] array = {"First", "Second", "Third", "Fourth"};
        hw.swapTwoElements(0, 2, array);
        for (String str : array) System.out.println(str);

        // stage 2

        Integer[] pi = {3, 1, 4, 1, 5, 9};
        ArrayList<Integer> al = new ArrayList<Integer>();
        hw.convertArrayToArrayList(pi, al);
        for (Integer i : al) System.out.println(i);

        // stage 3

        hw.boxes();
    }

    <T> void swapTwoElements(int p1, int p2, T[] array) {
        T tmp = array[p1];
        array[p1] = array[p2];
        array[p2] = tmp;
    }

    <T> void convertArrayToArrayList(T[] array, ArrayList<T> al) {
        for (T i : array) al.add(i);
    }

    void boxes() {
        Apple apple = new Apple();
        Orange orange = new Orange();
        Box<Apple> abox = new Box<Apple>(apple);
        Box<Orange> obox = new Box<Orange>(orange);
        Box<Orange> obox1 = new Box<Orange>(orange);
        abox.add(30);
        obox.add(20);
        obox1.add(10);
        System.out.println(abox.getWeight());
        System.out.println(obox.getWeight());
        System.out.println(obox1.getWeight());
        System.out.println(obox.compare(abox)); // compare
        System.out.println(obox.emptyIn(abox)); // empty in fail
        System.out.println(obox.emptyIn(obox1)); // empty in success
        System.out.println(obox.getWeight());
        System.out.println(obox1.getWeight());
    }

    class Box<T extends Fruit> {
        T obj;
        int total = 0;

        public Box(T obj) {
            this.obj = obj;
        }

        public T getObj() {
            return obj;
        }

        float getWeight() {
            return total*obj.getWeight();
        }

        boolean compare(Box box) {
            return getWeight() == box.getWeight();
        }

        boolean emptyIn(Box box) {
            if (getObj() == box.getObj()) {
                box.add(total);
                total = 0;
                return true;
            } else
                return false;
        }

        void add(int number) {
            total += number;
        }
    }

    class Apple extends Fruit {
        float getWeight() { return 1.0f; }
    }

    class Orange extends Fruit {
        float getWeight() { return 1.5f; }
    }

    abstract class Fruit {
        abstract float getWeight();
    }
}