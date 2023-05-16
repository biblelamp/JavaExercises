package homework3;

public class Cat extends Animal {

    public Cat(int runLimit) {
        super(runLimit, -1);
    }

    @Override
    public String swim(int distance) {
        return getClassName() + " can't swim";
    }
}