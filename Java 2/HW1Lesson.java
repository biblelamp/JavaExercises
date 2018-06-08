/**
 * Java. Level 2. Lesson 1. Example of homework
 * Preparatory actions:
 * 1. Making zoo.jar based on code in lesson 1:
 *    - compile sourse files
 *      > javac hw1/animals/*.java
        > javac hw1/obstacles/*.java
 *    - make package using command:
 *      > jar -cvf zoo.jar hw1/animals/*.class hw1/obstacles/*.class
 * 2. Move package to lib/ directory
 *    > move animals.jar lib
 *    > move obstacles.jar lib
 * 3. Compile using this command:
 *    > javac -cp lib/zoo.jar;. HW1Lesson.java
 *    Run compiled program this way:
 *    > java -cp lib/zoo.jar;. HW1Lesson
 * 4. Or put package to [JDK]/jre/lib/ext
 *    and compile and run this way:
 *    > javac HW1Lesson.java
 *    > java HW1Lesson
 *
 * @author Sergey Iryupin
 * @version 0.3.5 dated Jun 08, 2018
 * @link https://github.com/biblelamp
 */
import hw1.animals.*;
import hw1.obstacles.*;
import hw1.*;

class HW1Lesson {

    public static void main(String[] args) {
        Course cource = new Course(new Doable[] { // creating an obstacle course
            new Track(50),
            new Water(50),
            new Wall(2)});
        Team team = new Team("Zoo", new Animal[] { // creating a team
            new Cat("Murzik"),
            new Cat("Barsik"),
            new Hen("Izzy"),
            new Hippo("Hippopo")});

        System.out.println(team); // to show team list
        cource.doIt(team);        // ask the team to pass the obstacle course
        team.showResults();       // to show results
    }
}