/**
 * Java. Level 1. Lesson 5. Homework
 * + Create several classes: Dog, Horse, Cat - inheritance from a parent class Animal
 * + All classes must be able to perform actions: run, swim, jump
 *
 * @author Alexander Krayko, modified by Sergey Iryupin
 * @version November 22, 2016
 */

public class GeekBrains5 {

    public static void main(String[] args) {
        Animal[] animArray = { new Dog(29,3,1), new Cat(33,2,2), new Horse(60,3,3) };
        for (Animal animal : animArray) {
            System.out.println(animal.getClass().getSimpleName());
            System.out.println(animal.run());
            System.out.println(animal.swim());
            System.out.println(animal.jump());
        }
    }
}

interface Animal {
    String run();
    String swim();
    String jump();
}

abstract class AbstractAnimal implements Animal {
    private int vrun;
    private int vswim;
    private int hjump;

    public AbstractAnimal(int vrun, int vswim, int hjump) {
        this.vrun = vrun;
        this.vswim = vswim;
        this.hjump = hjump;
    }

    public int getVrun() {
        return vrun;
    }

    public int getVswim() {
        return vswim;
    }

    public int getHjump() {
        return hjump;
    }

}
class Dog extends AbstractAnimal {

    private static int MAX_RUN_SPEED = 30;
    private static int MAX_SWIM_SPEED = 4;
    private static int MAX_JUMP_HEIGHT = 2;

    public Dog(int vrun, int vswim, int hjump) {
        super(vrun, vswim, hjump);
    }

    @Override
    public String jump() {
        if (getHjump()<= MAX_JUMP_HEIGHT) {
            return "I have jumped successfully on height "+ getHjump();
        }
        return "I can't jump this " + getHjump();
    }

    @Override
    public String run() {
        if (getVrun()<= MAX_RUN_SPEED) {
            return "I can run on this speed "+ getVrun();
        }
        return "I can't run " + getVrun();
    }

    @Override
    public String swim() {
        if (getVswim()<= MAX_SWIM_SPEED) {
            return "I can swim on this speed "+ getVswim();
        }
        return "I can't swim " + getVswim();
    }
}

class Cat extends AbstractAnimal {

    private static int MAX_RUN_SPEED = 25;
    private static int MAX_SWIM_SPEED = 0;
    private static int MAX_JUMP_HEIGHT = 3;

    public Cat(int vrun, int vswim, int hjump) {
        super(vrun, vswim, hjump);
    }

    @Override
    public String jump() {
        if (getHjump()<= MAX_JUMP_HEIGHT) {
            return "I have jumped successfully on height "+ getHjump();
        }
        return "I can't jump this " + getHjump();
    }

    @Override
    public String run() {
        if (getVrun()<= MAX_RUN_SPEED) {
            return "I can run on this speed "+ getVrun();
        }
        return "I can't run " + getVrun();
    }

    @Override
    public String swim() {
        if (getVswim()<= MAX_SWIM_SPEED) {
            return "I can swim on this speed "+ getVswim();
        }
        return "I can't swim " + getVswim();
    }
}

class Horse extends AbstractAnimal {

    private static int MAX_RUN_SPEED = 70;
    private static int MAX_SWIM_SPEED = 6;
    private static int MAX_JUMP_HEIGHT = 3;

    public Horse(int vrun, int vswim, int hjump) {
        super(vrun, vswim, hjump);
    }

    @Override
    public String jump() {
        if (getHjump()<= MAX_JUMP_HEIGHT) {
            return "I have jumped successfully on height "+ getHjump();
        }
        return "I can't jump this " + getHjump();
    }

    @Override
    public String run() {
        if (getVrun()<= MAX_RUN_SPEED) {
            return "I can run on this speed "+ getVrun();
        }
        return "I can't run " + getVrun();
    }

    @Override
    public String swim() {
        if (getVswim()<= MAX_SWIM_SPEED) {
            return "I can swim on this speed "+ getVswim();
        }
        return "I can't swim " + getVswim();
    }
}