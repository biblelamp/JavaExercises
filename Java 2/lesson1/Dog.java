package lesson1;

/**
 * class Dog inherits from the class Animal
 */
public class Dog extends Animal implements Jumpable, Swimable{

    public Dog(String name) {
        super(name);
        onDistance = true;
        animType = "Dog";
        maxRunDistance = 1000;
    }

    @Override
    public void jump(float height) {
        if (height < 0.5f){
            System.out.println(animType + " jump ok");
        } else {
            getOutOfDistance("jump");
        }

    }

    @Override
    public void swim(float dist) {
        if (dist < 300){
            System.out.println(animType + " water ok");
        } else {
            getOutOfDistance("swim");
        }
    }
}