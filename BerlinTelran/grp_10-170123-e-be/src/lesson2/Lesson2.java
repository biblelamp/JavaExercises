package lesson2;

/**
 * Java Proffessional
 * @date 5.5.2023
 */

public class Lesson2 {
    public static void main(String[] args) {
        Cat cat = new Cat("Barsik", "black", 3);
        System.out.println(cat.voice());
        System.out.println(cat);
        cat.setName("Murzik");
        System.out.println("Name: " + cat.getName());
        System.out.println(cat);

        Cat catNull = new Cat();
    }
}
