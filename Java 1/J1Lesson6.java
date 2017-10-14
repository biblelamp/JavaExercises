class J1Lesson6 {

    public static void main(String[] args) {
        Animal[] animals = {new Cat("Murzik", "white", 5), new Dog("Tuzik", "black", 8)};
        for (Animal animal : animals)
            System.out.println(animal + " say " + animal.voice());
        //Animal animal = new Animal(null, null, -1);
        //System.out.println(animal + " say " + animal.voice());
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 + "\n" + s2);
    }
}

class Singleton {
    private static Singleton singleton;
    // more fields...

    private Singleton() { }

    static Singleton getInstance() {
        if (singleton == null)
            singleton = new Singleton();
        return singleton;
    }
    // more methods...
}

class Cat extends Animal {
    Cat(String name, String color, int age) {
        super(name, color, age);
    }
    @Override
    public String voice() {
        return "meow";
    }
}

class Dog extends Animal {
    Dog(String name, String color, int age) {
        super(name, color, age);
    }
    @Override
    public String voice() {
        return "gaf-gaf";
    }
}

abstract class Animal {
    String name;
    String color;
    int age;

    Animal(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    String voice() {
        return "gaf-gaf";
    }

    @Override
    public String toString() {
        return "{" + name + ", " + color + ", " + age + "}";
    }
}