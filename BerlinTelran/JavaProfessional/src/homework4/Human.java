package homework4;

public class Human implements Action {
    private int runLimit;
    private int jumpLimit;

    public Human(int runLimit, int jumpLimit) {
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    @Override
    public void tryOvercome(Obstacle obstacle) {
        obstacle.isOvercome(this);
    }

    @Override
    public int getRunLimit() {
        return runLimit;
    }

    @Override
    public int getJumpLimit() {
        return jumpLimit;
    }

    @Override
    public String getType() {
        return "Human";
    }
}
