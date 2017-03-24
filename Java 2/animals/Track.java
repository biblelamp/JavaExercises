/**
 * Class Track
 * 
 * @author  Sergey Iryupin
 * @version 0.1 dated Mar 24, 2017
 */
class Track {
    private int length;

    Track(int length) {
        this.length = length;
    }

    boolean doIt(Animal animal) {
        return animal.run(length);
    }
}
