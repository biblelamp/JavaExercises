package lesson36;

public class Cat extends Animal implements ICommon {

    public Cat(String name, String color, int age) {
        super(name, color, age);
    }

    @Override
    public String voice() {
        return "meow";
    }

    @Override
    public void eat() {

    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }
}
