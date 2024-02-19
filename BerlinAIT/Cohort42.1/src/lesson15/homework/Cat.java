package lesson15.homework;

public class Cat {

    int energy;

    public void walk() {
        if (energy < 3) {
            voice();
            return;
        }
        energy -= 3;
    }

    public void run() {
        if (energy < 5) {
            voice();
            return;
        }
        energy -= 5;
    }

    public void eat(int food) {
        energy += food;
    }

    public void voice() {
        System.out.println("Meow!");
    }

    public int status() {
        return energy;
    }
}
