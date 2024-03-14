package lesson25.homework;

public class Human {
    protected int speed;
    protected int rest;

    public Human(int speed, int rest) {
        this.speed = speed;
        this.rest = rest;
    }

    public String run() {
        return "Run with speed: " + speed + " km/h\n" + rest();
    }

    public String rest() {
        return "Rest: " + rest + " min.";
    }
}
