/**
 * Class Animal is parent for all animals
 *
 * @author  Sergey Iryupin
 * @version 0.1 dated Mar 18, 2017
 */
public abstract class Animal {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + name +
            ((this instanceof Swimable)?" can swim":" can not swim");
    }
}