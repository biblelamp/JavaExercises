/**
 * Java. Level 2. Lesson 1. Example of homework
 * Class Course:
 *   contains array of obstacles
 */
package hw1;
import hw1.obstacles.*;
import java.util.*;

public class Course {
    Doable[] course;

    public Course(Doable[] objects) {
        course = new Doable[objects.length];
        course = objects;
    }

    public void doIt(Team team) {
        for (Doable obstacle : course)
            team.doIt(obstacle);
    }

    @Override
    public String toString() {
        return Arrays.toString(course);
    }
}