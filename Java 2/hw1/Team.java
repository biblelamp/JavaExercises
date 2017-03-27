/**
 * Java. Level 2. Lesson 1. Example of homework
 * Class Team:
 *   contains array of animals
 */
package hw1;
import animals.*;
import obstances.*;
import java.util.*;

public class Team {
    Animal[] animals;
    String result;

    public Team(Animal[] animals) {
        this.animals = new Animal[animals.length];
        this.animals = animals;
        result = null;
    }

    public void doIt(Doable obstacle) {
        result += obstacle.toString() + "\n";
        for (Animal animal : animals)
            result += "> " + animal + " " + obstacle.doIt(animal) + "\n";
    }

    public void showResult() {
        System.out.println(result);
    }

    @Override
    public String toString() {
        return Arrays.toString(animals);
    }
}