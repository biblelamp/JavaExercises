package homework6;

abstract class Fruit {
    protected float weight;

    public Fruit(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%s = %.2f", this.getClass().getSimpleName(), weight);
    }
}