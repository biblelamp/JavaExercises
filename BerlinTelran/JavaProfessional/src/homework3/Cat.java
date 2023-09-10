package homework3;

public class Cat extends Animal {
    public Cat(int runLimit) {
        super(runLimit, -1);
        type = "Cat";
    }

    @Override
    public void swim(int d) {
        System.out.println("Cat cannot swim.");
    }
}
