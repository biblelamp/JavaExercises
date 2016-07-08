/**
 * Java. Level 2. Lesson 1. Homework
 *  1. To understand the code that was written in class;
 *  2. Add a class Team, which contains: the name of the team,
 *  the array members, a method for displaying information about
 *  the members who passed distance and methods for displaying all members.
 *  3. Add class ОbstacleСourse, which contains: a list of the obstacles
 *  and the method that will ask the team pass the obstacle course
 *  and test - able whether each team member to pass all obstacles.
 *
 * @author Sergey Iryupin
 * @version 08 July 2016
 */
import lesson1.*;
 
public class Lesson1 {

    public static void main(String[] args) {
        new Lesson1().solution();
    }

    public void solution() {

        Animal[] animals = new Animal[]{
                new Cat("Barsik"), new Dog("Sharik"), new Horse("Spirit"),
                new Duck("Donald"), new Human("Bob")
        };

        Team tm = new Team("Soccer", animals);
        tm.showTeam();

        Obstacle[] obstacles = new Obstacle[]{
                new OWall(0.9f), new OCross(100),
                new OWater(10)
        };
        
        ObstacleCourse oc = new ObstacleCourse(obstacles);
        oc.showObstacles();

        System.out.println("------- Competition begins -----");
        oc.passObstacleCourse(tm.getMembers());

        /*for (Animal animal : animals) {
            for (Obstacle ob : obstacles) {
                ob.doIt(animal);
                if (!animal.isOnDistance())
                    break;
            }
        }*/
        
        System.out.println("------------ Winners ------------");
        tm.showMembersWhoPassedDistance();
        /*for (Animal animal : animals) {
            if (animal.isOnDistance())
                System.out.println(animal.getType() + " " + animal.getName() + " done it");
        }*/
    }
}