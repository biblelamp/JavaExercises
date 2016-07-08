package lesson1;
import java.util.*;

/**
 * class ObstacleCourse
 */
public class ObstacleCourse {
    private ArrayList<Obstacle> obstacles = new ArrayList();

    public ObstacleCourse(Obstacle[] obstacles) {
        Collections.addAll(this.obstacles, obstacles);
    }

    public void showObstacles() {
        System.out.println("Obstacles:");
        for (Obstacle obstacle : obstacles) {
            System.out.println(obstacle.getType());
        }
    }
    
    public void passObstacleCourse(ArrayList<Animal> members) {
        for (Animal member : members) {
            for (Obstacle ob : obstacles) {
                ob.doIt(member);
                if (!member.isOnDistance())
                    break;
            }
        }
    }
}