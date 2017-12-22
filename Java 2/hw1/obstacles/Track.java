package hw1.obstacles;
import hw1.animals.*;
/**
 * Class Track
 * 
 * @author  Sergey Iryupin
 * @version 0.4 dated Mar 28, 2017
 */
public class Track implements Doable {
    private int length;

    public Track(int length) {
        this.length = length;
    }

    public boolean doIt(Animal animal) {
        return animal.run(length);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + length + "m";
    }
}