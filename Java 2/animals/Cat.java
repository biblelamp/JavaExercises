/**
 * Class Cat
 * 
 * @author  Sergey Iryupin
 * @version 0.3 dated Mar 23, 2017
 */
public class Cat extends Animal implements Swimable, Jumpable {

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public String voice() {
        return "meow";
    }

    @Override
    public void swim(int distance) {
    }

    @Override
    public void jump(float height) {
    }
}