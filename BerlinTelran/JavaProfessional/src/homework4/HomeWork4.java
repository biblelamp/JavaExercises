package homework4;

/**
 * Java professional. Homework #3
 *
 * @author Sergey
 * @version 10.11-14.11
 */

public class HomeWork4 {
    public static void main(String[] args) {
        Cat cat = new Cat(200, 3);
        Human human = new Human(100, 1);
        Robot robot = new Robot(500, 2);
        Action[] participants = {cat, human, robot};

        Track track = new Track(150);
        Wall wall = new Wall(1);
        Obstacle[] obstacles = {track, wall};

        for (Action participant : participants) {
            for (Obstacle obstacle : obstacles) {
                participant.tryOvercome(obstacle);
            }
        }
    }
}
