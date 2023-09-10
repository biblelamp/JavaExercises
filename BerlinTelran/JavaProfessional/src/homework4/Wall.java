package homework4;

public class Wall implements Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void isOvercome(Action participant) {
        if (height > participant.getJumpLimit()) {
            System.out.println(participant.getType() + " cannot jump " + height + "m.");
        } else {
            System.out.println(participant.getType() + " jump " + height + "m.");
        }
    }
}
