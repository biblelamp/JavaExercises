/**
 * Class Animal is parent for all animals
 *
 * @author  Sergey Iryupin
 * @version 0.3 dated Mar 24, 2017
 */
abstract class Animal {
    protected String name;
    protected int run_limit;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    abstract String voice();

    boolean run(int length) {
        return run_limit > length;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + name;
    }
}