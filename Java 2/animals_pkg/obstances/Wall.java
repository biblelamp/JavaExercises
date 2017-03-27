package obstances;
import animals.*;
/**
 * Class Wall
 * 
 * @author  Sergey Iryupin
 * @version 0.3 dated Mar 27, 2017
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
}