package animals;
/**
 * Abstract class Animal - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Animal {
    protected String name;
    protected int run_limit;

    public abstract String voice();

    public boolean run(int length) {
        return run_limit >= length;
    }
    
    @Override
    public String toString() {
        return this.getClass().getName() + " " + name;
    }    
}