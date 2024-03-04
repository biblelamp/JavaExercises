package lesson22;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #22
 *
 * @version 4-Mar-24
 */
public class Lesson22 {
    public static void main(String[] args) {
        Horse horse = new Horse("Tunder");
        System.out.println(horse);
        horse.run();
        horse.walk();

        Pegas pegas = new Pegas("Pegas");
        System.out.println(pegas);
        pegas.run();
        pegas.walk();
        pegas.fly();
    }
}
