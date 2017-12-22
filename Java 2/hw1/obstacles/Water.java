package hw1.obstacles;
import hw1.animals.*;
/**
 * Class Water
 * 
 * @author  Sergey Iryupin
 * @version 0.4 dated Mar 28, 2017
 */
public class Water  implements Doable {
    private int length;

    public Water(int length) {
        this.length = length;
    }

    public boolean doIt(Animal animal) {
        if(animal instanceof Swimable)
            return ((Swimable) animal).swim(length);
        else
            return false;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + length + "m";
    }
}