import animals.*;
import obstances.*;
/**
 * Java. Level 2. Lesson 1. Example of homework
 * Preparatory actions:
 * 1. Making animals.jar based on code in lesson 1:
 *    - makind animals dir
 *    - put in the dir classes: animal, cat, hen, hippo, jumpable, swimable
 *    - using command (from the dir level above):
 *      > jar -cf animals.jar animals
 * 2. Making obstances.jar from classes track, wall, water in the same way
 * 3. Put both packages to lib/ directory
 * 4. Compile using this way:
 *    > javac -cp lib/animals.jar;lib/obstances.jar HW1Lesson.java
 * 5. Run compiled program this way:
 *    > java -cp lib/animals.jar;lib/obstances.jar;. HW1Lesson
 *
 * @author Sergey Iryupin
 * @version 0.1 dated 26 Mar 2017
 */
public class HW1Lesson {

    public static void main(String[] args) {
        Animal[] zoo = {new Cat("Murzik"), new Hen("Izzy"), new Hippo("Hippopo")};
        Track track = new Track(50);
        Water water = new Water(50);
        Wall wall = new Wall(2);

        for (Animal animal : zoo) {
            System.out.println(animal);
            System.out.println(" voice: " + animal.voice());
            System.out.println(" run: " + track.doIt(animal));
            System.out.println(" swim: " + water.doIt(animal));
            System.out.println(" jump: " + wall.doIt(animal));
        }
    }
}