package lesson15;

public class Cat {
    String name;
    String color;
    int age;
    int volume;
    int food;

    public Cat(String name, String color, int age, int volume) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.volume = volume;
    }

    public Cat() {
    }

    public void eat(int food) {
        this.food = food;
    }

    public double satiated() {
        return (double) food / volume * 100;
    }

    public void voice() {
        System.out.println("Meow!");
    }
}
