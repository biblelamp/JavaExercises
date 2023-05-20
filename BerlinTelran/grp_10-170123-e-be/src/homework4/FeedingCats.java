package homework4;

/**
 * Java. Homework #4
 * @author Sergey
 * @version 16 May 2023
 */
public class FeedingCats {
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
            cat.setFullness(false); // make the cat hungry
            cat.eat(plate);
            System.out.println(cat);
        }
        System.out.println(plate);
    }
}