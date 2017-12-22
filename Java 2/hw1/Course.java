/**
 * Java. Level 2. Lesson 1. Example of homework
 * Class Course:
 *   contains array of obstacles
 */
package hw1;
import hw1.obstacles.*;
import java.util.*;

public class Course {
    Doable[] obstacles;

    public Course(Doable[] obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {
        for (Doable obstacle : obstacles)
            team.doIt(obstacle);
    }

    @Override
    public String toString() {
        return Arrays.toString(obstacles);
    }
}