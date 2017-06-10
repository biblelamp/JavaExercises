package hw1.animals;
/**
 * Class Hippo
 * 
 * @author  Sergey Iryupin
 * @version 0.3 dated Mar 26, 2017
 */
public class Hippo extends Animal implements Swimable {
    private int swim_limit;
   
    public Hippo(String name) {
        this.name = name;
        this.run_limit = 100;
        swim_limit = 100;
    }

    @Override
    public String voice() {
        return "wuf-wuf";
    }

    @Override
    public boolean swim(int length) {
        return swim_limit > length;
    }
}