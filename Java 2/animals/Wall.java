/**
 * Class Wall
 * 
 * @author  Sergey Iryupin
 * @version 0.1 dated Mar 24, 2017
 */
class Wall {
    private float height;

    Wall(float height) {
        this.height = height;
    }

    boolean doIt(Animal animal) {
        if(animal instanceof Jumpable)
            return ((Jumpable) animal).jump(height);
        else
            return false;
    }
}