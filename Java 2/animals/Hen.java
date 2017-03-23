/**
 * Class Hen
 * 
 * @author  Sergey Iryupin
 * @version 0.2 dated Mar 23, 2017
 */
public class Hen extends Animal implements Jumpable {

    public Hen(String name) {
        this.name = name;
    }

    @Override
    public String voice() {
        return "ko-ko-ko";
    }

    @Override
    public void jump(float height) {
    }
}