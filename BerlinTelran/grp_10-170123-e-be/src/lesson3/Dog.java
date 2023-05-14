package lesson3;

public class Dog extends Animal {
    public Dog(String name, String color, int age) {
        super(name, color, age);
    }

    @Override
    public String voice() {
        return "gaf-gaf";
    }
}
