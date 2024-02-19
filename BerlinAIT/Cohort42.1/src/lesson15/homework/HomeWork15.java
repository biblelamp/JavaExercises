package lesson15.homework;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #15
 *
 * @author Sergey Iryupin
 * @version 19-Feb-24
 */
public class HomeWork15 {
    public static void main(String[] args) {
        // task #1
        Cat cat = new Cat();
        System.out.println(cat.status());
        cat.eat(10);
        cat.walk();
        cat.run();
        System.out.println(cat.status());
        cat.walk();
        System.out.println(cat.status());
        Cat newCat = new Cat();
        System.out.println(newCat.status());

        // task #2
        Car car = new Car(5, 30);
        if (car.starEngine()) {
            System.out.println("Drive: " + car.drive(50));
            System.out.println("Fuel: " + car.fuel);
        }
    }
}
