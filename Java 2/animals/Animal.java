/**
 * Class Animal is parent for all animals
 *
 * @author  Sergey Iryupin
 * @version 0.2 dated Mar 23, 2017
 */
public abstract class Animal {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract String voice();

    @Override
    public String toString() {
        return this.getClass().getName() + " " + name;
    }
}