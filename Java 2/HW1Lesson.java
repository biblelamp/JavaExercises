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
 *    Compile using this way:
 *    > javac -cp lib/animals.jar;lib/obstances.jar HW1Lesson.java
 *    Run compiled program this way:
 *    > java -cp lib/animals.jar;lib/obstances.jar;. HW1Lesson
 * 4. Or put both packages to [JDK]/jre/lib/ext
 *    and compile and run this way:
 *    > javac HW1Lesson.java
 *    > java HW1Lesson
 *
 * @author Sergey Iryupin
 * @version 0.2 dated 27 Mar 2017
 */
import animals.*;
import obstances.*;
import hw1.*;

public class HW1Lesson {

    public static void main(String[] args) {
        Course course = new Course(new Doable[] {
            new Track(50), new Water(50), new Wall(2)});
        Team team = new Team(new Animal[] {
            new Cat("Murzik"), new Hen("Izzy"), new Hippo("Hippopo")});

        System.out.println(team);
        course.doIt(team);
        team.showResult();
    }
}