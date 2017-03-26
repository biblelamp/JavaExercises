package obstances;
import animals.*;
/**
 * Class Wall
 * 
 * @author  Sergey Iryupin
 * @version 0.2 dated Mar 26, 2017
 */
public class Wall {
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
}