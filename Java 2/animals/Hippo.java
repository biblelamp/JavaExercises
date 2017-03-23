/**
 * Class Hippo
 * 
 * @author  Sergey Iryupin
 * @version 0.1 dated Mar 23, 2017
 */
public class Hippo extends Animal implements Swimable {

    public Hippo(String name) {
        this.name = name;
    }

    @Override
    public String voice() {
        return "wuf-wuf";
    }

    @Override
    public void swim(int distance) {
    }
}