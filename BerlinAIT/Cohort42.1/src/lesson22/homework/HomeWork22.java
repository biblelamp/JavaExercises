package lesson22.homework;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #22
 *
 * @author Sergey Iryupin
 * @version 6-Mar-24
 */
public class HomeWork22 {
    public static void main(String[] args) {
        Animal animal = new Animal(5);
        System.out.println(animal);
        System.out.println(animal.voice());

        Cat cat = new Cat(3, "white");
        System.out.println(cat);
        System.out.println(cat.voice());

        Tiger tiger = new Tiger(4, "red", "Sherchan");
        System.out.println(tiger);
        System.out.println(tiger.voice());
    }
}
