/**
 * Class Cat
 * 
 * @author  Sergey Iryupin
 * @version 0.1 dated Mar 18, 2017
 */
public class Cat extends Animal implements Swimable {

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void swim() {
    }
}