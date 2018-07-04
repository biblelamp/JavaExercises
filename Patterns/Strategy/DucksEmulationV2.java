/**
 * Java. Head First Design Patterns
 *  Chapter 1: Strategy, version with interfaces
 *
 */
class DucksEmulationV2 {
    public static void main(String[] args) {
    }
}

abstract class Duck {
    void swim() { }
    void display() { }
}

interface Flyable {
    void fly();
}

interface Quackable {
    void quack();
}

class MallardDuck extends Duck implements Quackable, Flyable {
    @Override
    void display() { }
    public void fly() { }
    public void quack() { }
}

class RedheadDuck extends Duck implements Quackable, Flyable {
    @Override
    void display() { }
    public void fly() { }
    public void quack() { }
}

class RubberDuck extends Duck implements Quackable {
    @Override
    void display() { }
    public void quack() { }
}

class DecoyDuck extends Duck {
    @Override
    void display() { }
}