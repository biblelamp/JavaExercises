package lesson22.homework;

public class Animal {
    protected int age;

    public Animal(int age) {
        this.age = age;
    }

    public String voice() {
        return "unknown";
    }

    @Override
    public String toString() {
        return "Animal{" +
                "age=" + age +
                '}';
    }
}
