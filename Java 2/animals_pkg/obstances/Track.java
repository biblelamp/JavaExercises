package obstances;
import animals.Animal;
/**
 * Class Track
 * 
 * @author  Sergey Iryupin
 * @version 0.2 dated Mar 26, 2017
 */
public class Track {
    private int length;

    public Track(int length) {
        this.length = length;
    }

    public boolean doIt(Animal animal) {
        return animal.run(length);
    }
}
