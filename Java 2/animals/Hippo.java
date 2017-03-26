/**
 * Class Hippo
 * 
 * @author  Sergey Iryupin
 * @version 0.2 dated Mar 24, 2017
 */
class Hippo extends Animal implements Swimable {
    private int swim_limit;
   
    Hippo(String name) {
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