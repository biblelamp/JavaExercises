/**
 * Class Hen
 * 
 * @author  Sergey Iryupin
 * @version 0.3 dated Mar 24, 2017
 */
class Hen extends Animal implements Jumpable {
    private float jump_limit;

    Hen(String name) {
        this.name = name;
        super.run_limit = 100;
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