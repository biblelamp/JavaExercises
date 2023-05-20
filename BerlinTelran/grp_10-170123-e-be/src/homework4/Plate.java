package homework4;

public class Plate {
    protected int maxVolume;
    protected int food;

    public Plate(int maxVolume, int food) {
        this.maxVolume = maxVolume;
        this.food = food;
    }

    public boolean decreaseFood(int portion) {
        if (food < portion) {
            return false;
        }
        food -= portion;
        return true;
    }

    public void add(int food) {
        if (this.food + food <= maxVolume) {
            this.food += food;
        }
    }

    @Override
    public String toString() {
        return "plate: " + food;
    }
}