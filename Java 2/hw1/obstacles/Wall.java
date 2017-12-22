package hw1.obstacles;
import hw1.animals.*;
/**
 * Class Wall
 * 
 * @author  Sergey Iryupin
 * @version 0.4 dated Mar 28, 2017
 */
public class Wall implements Doable {
    private float height;

    public Wall(float height) {
        this.height = height;
    }

    public boolean doIt(Animal animal) {
        if(animal instanceof Jumpable)
            return ((Jumpable) animal).jump(height);
        else
            return false;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + height + "m";
    }
}