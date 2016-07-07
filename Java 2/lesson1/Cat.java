package lesson1;
import java.util.Random;

/**
 * class Cat inherits from the class Animal
 */
public class Cat extends Animal implements Jumpable {

    public Cat(String name) {
        super(name);
        onDistance = true;
        animType = "Cat";
        maxRunDistance = 500;
    }

    @Override
    public void jump(float height) {
        if (height < 1.5f){
            System.out.println(animType + " jump ok");
        } else {
            Random random = new Random();
            float doubleJump = random.nextFloat();
            if (height < 1.5f + doubleJump){
                System.out.println(animType + " cool jump ok");
            } else
                getOutOfDistance("jump");
        }
    }
}