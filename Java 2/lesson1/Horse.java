package lesson1;

/**
 * class Horse inherits from the class Animal
 */
public class Horse extends Animal implements Swimable, Jumpable {

    public Horse(String name) {
        super(name);
        animType = "Horse";
        onDistance = true;
        maxRunDistance = 10000;
    }

    public void swim(float dist) {
        if(dist < 10) {
            System.out.println(animType + " water ok");
        } else {
            getOutOfDistance("swim");
        }
    }

    @Override
    public void jump(float height) {
        if(height < 1.0f) {
            System.out.println(animType + " jump ok");
        } else {
            getOutOfDistance("jump");
        }
    }
}