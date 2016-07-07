/**
 * Java. Level 2. Lesson 1. Homework
 *  1. To understand the code that was written in class;
 *
 * @author Sergey Iryupin
 * @version 07 July 2016
 */
import lesson1.*;
 
public class Lesson1 {

    public static void main(String[] args) {
        new Lesson1().solution();
    }

    public void solution() {
        Animal[] animals = new Animal[]{
                new Cat("Barsik"), new Dog("Barsik"), new Horse("Spirit"),
                new Duck("Donald"), new Human("Bob")
        };

        Obstacle[] obstacles = new Obstacle[]{
                new OWall(0.9f), new OCross(100),
                new OWater(10)
        };

        for (Animal animal : animals) {
            for (Obstacle ob : obstacles) {
                ob.doIt(animal);
                if (!animal.isOnDistance())
                    break;
            }
        }
        System.out.println("------------ Winners ------------");
        for (Animal animal : animals) {
            if (animal.isOnDistance())
                System.out.println(animal.getType() + " " + animal.getName() + " done it");
        }
    }

}