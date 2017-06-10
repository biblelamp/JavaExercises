package hw1.animals;
/**
 * Class Hen
 * 
 * @author  Sergey Iryupin
 * @version 0.4 dated Mar 26, 2017
 */
public class Hen extends Animal implements Jumpable {
    private float jump_limit;

    public Hen(String name) {
        this.name = name;
        this.run_limit = 100;
        jump_limit = 3.2f;
    }

    @Override
    public String voice() {
        return "ko-ko-ko";
    }

    @Override
    public boolean jump(float height) {
        return jump_limit > height;
    }
}