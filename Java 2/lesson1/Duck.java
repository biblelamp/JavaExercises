package lesson1;

/**
 * class Duck inherits from the class Animal
 */
public class Duck extends Animal implements Swimable {

    public Duck(String name) {
        super(name);
        animType = "Duck";
        onDistance = true;
        maxRunDistance = 50;
    }

    public void swim(float dist) {
        if(dist < 15000) {
            System.out.println(animType + " water ok");
        } else {
            getOutOfDistance("swim");
        }
    }
}