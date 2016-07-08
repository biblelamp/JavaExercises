package lesson1;

/**
 * class OWater inherits from the class Obstacle
 */
public class OWater extends Obstacle {

    public OWater(float size) {
        this.size = size;
    }

    @Override
    public String getType() {
        return "Water";
    }

    @Override
    public void doIt(Animal a) {
        if(a instanceof Swimable) {
            ((Swimable) a).swim(size);
        } else {
            a.getOutOfDistance("water");
        }
    }
}