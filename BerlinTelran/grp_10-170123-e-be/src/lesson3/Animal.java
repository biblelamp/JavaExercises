package lesson3;

public abstract class Animal implements IAnimal {
    protected String name;
    protected String color;
    protected int age;
    private String className;

    public Animal(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.className = this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return className + "{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }
}
