package homework1;

/**
 * Java Prof. Homework #1
 *
 * @author Sergey
 * @version 31.10 - 03.11
 */
public class HelloCat {
    public static void main(String[] args) {
        Cat cat = new Cat("Barsik", "red", 4);
        cat.voice();
        System.out.println(cat);

        cat.setName(null);
        System.out.println(cat.getName());
        System.out.println(cat);

        int x = 5;
        System.out.println(x);
        //System.out.println(cat);
    }
}