package lesson1;

/**
 * class OWall inherits from the class Obstacle
 */
public class OWall extends Obstacle {

    public OWall(float size) {
        super(size);
    }

    @Override
    public String getType() {
        return "Wall";
    }

    @Override
    public void doIt(Animal a) {
        if (a instanceof Jumpable){
            ((Jumpable) a).jump(size);
        } else {
            a.getOutOfDistance("jump");
        }
    }
}