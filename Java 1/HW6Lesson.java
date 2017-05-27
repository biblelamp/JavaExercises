/**
 * Java. Level 1. Lesson 6. Example of homework
 *
 * @author Sergey Iryupin
 * @version dated May 26, 2017
 */
class HW6Lesson {

    public static void main(String[] args) {
        Animal[] animal = {new Cat(200, 2, 0), new Dog(500, 0.5f, 10)};
        for (Animal an : animal)
            System.out.println(
                an.getClass().getName() +
                "\nrun: " + an.run(200) +
                "\njump: " + an.jump(1.5f) +
                "\nswim: " + an.swim(5));
    }
}

class Cat extends Animal {
    Cat(int run_limit, float jump_limit, int swim_limit) {
        super(run_limit, jump_limit, swim_limit);
    }

    @Override
    protected boolean swim(int distance) {
        return false; // cats cannot swim (by the condition of task)
    }
}

class Dog extends Animal {
    Dog(int run_limit, float jump_limit, int swim_limit) {
        super(run_limit, jump_limit, swim_limit);
    }
}

abstract class Animal {
    private int run_limit;
    private float jump_limit;
    private int swim_limit;

    Animal(int run_limit, float jump_limit, int swim_limit) {
        this.run_limit = run_limit;
        this.jump_limit = jump_limit;
        this.swim_limit = swim_limit;
    }

    protected boolean run(int distance) {
        return distance <= run_limit;
    }
    protected boolean jump(float height)  {
        return height <= jump_limit;
    }
    protected boolean swim(int distance) {
        return distance <= swim_limit;
    }
}