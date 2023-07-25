package lesson36;

public class Bird extends Animal implements ICommon, IFly {

    public Bird(String name, String color, int age) {
        super(name, color, age);
    }

    @Override
    public String voice() {
        return null;
    }

    @Override
    public void eat() {

    }

    @Override
    public void fly() {

    }
}
