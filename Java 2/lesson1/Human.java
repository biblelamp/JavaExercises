package lesson1;

/**
 * class Human inherits from the class Animal
 */
public class Human extends Animal implements Swimable, Jumpable {
    private float endurance;

    public Human(String name) {
        this.name = name;
        animType = "Human";
        onDistance = true;
        maxRunDistance = 5000;
        endurance = 5000;
    }

    public void swim(float dist) {
        if (dist < 5000) {
            endurance -= dist * 10.0f;
            if (endurance < 0)
                getOutOfDistance("endurance(swim)");
            else
                System.out.println(animType + " water ok");
        } else {
            getOutOfDistance("swim");
        }

    }

    @Override
    public void jump(float height) {
        if (height < 1.0f) {
            endurance -= height * 100;
            if (endurance < 0)
                getOutOfDistance("endurance(jump)");
            else
                System.out.println(animType + " jump ok");
        } else {
            getOutOfDistance("jump");
        }
    }

    @Override
    public void cross(float dist) {
        if (dist < maxRunDistance) {
            endurance -= dist * 5;
            if (endurance < 0)
                getOutOfDistance("endurance(cross)");
            else
                System.out.println(animType + " cross ok");
        } else {
            getOutOfDistance("cross");
        }
    }
}