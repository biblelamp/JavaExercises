package homework4;

public interface Action {
    void tryOvercome(Obstacle obstacle);
    int getRunLimit();
    int getJumpLimit();
    String getType();
}
