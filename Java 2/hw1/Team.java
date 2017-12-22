/**
 * Java. Level 2. Lesson 1. Example of homework
 * Class Team:
 *   contains array of animals
 */
package hw1;
import hw1.animals.*;
import hw1.obstacles.*;
import java.util.*;

public class Team {
    private String name;
    private Animal[] animals;
    private String result;

    public Team(String name, Animal[] animals) {
        this.name = name;
        this.animals = animals;
        result = "";
    }

    public void doIt(Doable obstacle) {
        result += obstacle.toString() + "\n";
        for (Animal animal : animals)
            result += "> " + animal + " " + obstacle.doIt(animal) + "\n";
    }

    public void showResults() {
        System.out.println(result);
    }

    @Override
    public String toString() {
        return "Team: " + name + " " + Arrays.toString(animals);
    }
}