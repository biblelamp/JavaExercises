package lesson27;

public class Duck implements Runable, Flyable, Swimable {
    @Override
    public void fly() {
        System.out.println("Duck fly.");
    }

    @Override
    public void run() {
        System.out.println("Duck run.");
    }

    @Override
    public void swim() {
        System.out.println("Duck swim.");
    }
}
