package obstances;
import animals.*;
/**
 * Class Water
 * 
 * @author  Sergey Iryupin
 * @version 0.3 dated Mar 27, 2017
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
}
