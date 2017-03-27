package obstances;
import animals.Animal;
/**
 * Class Track
 * 
 * @author  Sergey Iryupin
 * @version 0.3 dated Mar 27, 2017
 */
public class Track implements Doable {
    private int length;

    public Track(int length) {
        this.length = length;
    }

    public boolean doIt(Animal animal) {
        return animal.run(length);
    }
}
