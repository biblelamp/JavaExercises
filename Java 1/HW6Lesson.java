/**
 * Java. Level 1. Lesson 6. Example of homework
 *
 * @author Sergey Iryupin
 * @version dated 10 Mar 2017
 */
public class HW6Lesson {

    public static void main(String[] args) {
        Animal cat = new Cat(200, 2, 0);
        System.out.println("Cat:");
        System.out.println("run: " + cat.run(200));
        System.out.println("jump: " + cat.jump(3));
        System.out.println("swim: " + cat.swim(0));
        Animal dog = new Dog(500, 0.5f, 10);
        System.out.println("Dog:");
        System.out.println("run: " + dog.run(200));
        System.out.println("jump: " + dog.jump(3));
        System.out.println("swim: " + dog.swim(5));
    }
}

class Cat extends Animal {
    Cat(int speed_limit, float jump_limit, int swim_limit) {
        super(speed_limit, jump_limit, swim_limit);
    }

    @Override
    protected boolean swim(int distance) {
        return false; // cats cannot swim (by the condition of task)
    }
}

class Dog extends Animal {
    Dog(int speed_limit, float jump_limit, int swim_limit) {
        super(speed_limit, jump_limit, swim_limit);
    }

    @Override
    protected boolean swim(int distance) {
        return distance <= swim_limit;
    }
}

abstract class Animal {
    private int speed_limit;
    private float jump_limit;
    protected int swim_limit; // for direct access from heirs

    Animal(int speed_limit, float jump_limit, int swim_limit) {
        this.speed_limit = speed_limit;
        this.jump_limit = jump_limit;
        this.swim_limit = swim_limit;
    }

    protected boolean run(int speed) {
        return speed <= speed_limit;
    }
    protected boolean jump(float height)  {
        return height <= jump_limit;
    }
    abstract boolean swim(int distance);
}