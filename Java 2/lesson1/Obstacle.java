package lesson1;

/**
 * abstract class Obstacle
 */
public abstract class Obstacle {

    public Obstacle() {
    }

    public Obstacle(float size) {
        this.size = size;
    }

    protected float size;
    
    public abstract String getType();

    public abstract void doIt(Animal a);
}