/**
 * Java. Head First Design Patterns
 *  Chapter 1: Strategy, init version
 *
 */
class DucksEmulation {
    public static void main(String[] args) {
    }
}

abstract class Duck {
    void quack() { }
    void swim() { }
    void display() { }
}

class MallardDuck extends Duck {
    @Override
    void display() { }
}

class RedheadDuck extends Duck {
    @Override
    void display() { }
}

class RubberDuck extends Duck {
    @Override
    void quack() { } // rubber ducks don't quack
    @Override
    void display() { }
}