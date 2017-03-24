/**
 * Class Water
 * 
 * @author  Sergey Iryupin
 * @version 0.1 dated Mar 24, 2017
 */
class Water {
    private int length;

    Water(int length) {
        this.length = length;
    }

    boolean doIt(Animal animal) {
        if(animal instanceof Swimable)
            return ((Swimable) animal).swim(length);
        else
            return false;
    }
}
