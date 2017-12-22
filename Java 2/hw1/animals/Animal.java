package hw1.animals;
/**
 * Class Animal is parent for all animals
 *
 * @author  Sergey Iryupin
 * @version 0.3.1 dated Dec 21, 2017
 */
public abstract class Animal implements Runable {
    protected String name;
    protected int run_limit;

    public abstract String voice();

    public boolean run(int length) {
        return run_limit > length;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + name;
    }
}