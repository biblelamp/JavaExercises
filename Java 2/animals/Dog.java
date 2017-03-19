/**
 * Class Dog
 * 
 * @author  Sergey Iryupin
 * @version 0.2 dated Mar 19, 2017
 */
public class Dog extends Animal implements Swimable, Jumpable {

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void swim(int distance) {
    }
    
    @Override
    public void jump(float height) {
    }
}