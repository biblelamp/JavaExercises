/**
 * Java. Head First Design Patterns
 *  Chapter 1: Strategy, version with inheritance
 *
 */
class DucksEmulationV1 {
    public static void main(String[] args) {
    }
}

abstract class Duck {
    void quack() { }
    void swim() { }
    void display() { }
    void fly() { } // added a new feature
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
    @Override
    void fly() { } // rubber ducks don't fly
}

class DecoyDuck extends Duck { // added new inheritable class
    @Override
    void quack() { } // decoy ducks don't quack
    @Override
    void display() { }
    @Override
    void fly() { } // decoy ducks don't fly
}