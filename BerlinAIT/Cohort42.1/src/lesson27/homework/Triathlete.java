package lesson27.homework;

public class Triathlete implements Swimmer, Runner {
    @Override
    public void run() {
        System.out.println("run...");
    }

    @Override
    public void swim() {
        System.out.println("swim...");
    }
}
