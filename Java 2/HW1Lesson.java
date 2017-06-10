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
 * @version 0.3.1 dated Jun 10, 2017
 */
import hw1.animals.*;
import hw1.obstances.*;
import hw1.*;

class HW1Lesson {

    public static void main(String[] args) {
        Course c = new Course(new Doable[] { // creating an obstacle course
            new Track(50),
            new Water(50),
            new Wall(2)});
        Team t1 = new Team("Animals", new Animal[] { // creating a team
            new Cat("Murzik"),
            new Cat("Barsik"),
            new Hen("Izzy"),
            new Hippo("Hippopo")});

        System.out.println(t1); // to show team list
        c.doIt(t1); // ask the team to pass the obstacle course
        t1.showResults(); // to show results
    }
}