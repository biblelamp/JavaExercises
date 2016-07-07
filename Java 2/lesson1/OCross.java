package lesson1;

/**
 * class OCross inherits from the class Obstacle
 */
public class OCross extends Obstacle {

    public OCross(float size) {
        super(size);
    }

    @Override
    public void doIt(Animal a) {
        a.cross(size);
    }
}