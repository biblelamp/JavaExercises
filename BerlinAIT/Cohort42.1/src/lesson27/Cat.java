package lesson27;

public class Cat implements CatAction {
    @Override
    public void jump() {
        System.out.println("Cat jump.");
    }

    @Override
    public void run() {
        System.out.println("Cat run.");
    }

    @Override
    public void swim() {
        System.out.println("Cat swim");
    }
}
