/**
 * Class Cat
 * 
 * @author  Sergey Iryupin
 * @version 0.2 dated Mar 19, 2017
 */
public class Cat extends Animal implements Swimable, Jumpable {

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void swim(int distance) {
    }

    @Override
    public void jump(float height) {
    }
}