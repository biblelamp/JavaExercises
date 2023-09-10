package homework4;

public class Track implements Obstacle {
    private int length;

    public Track(int length) {
        this.length = length;
    }

    @Override
    public void isOvercome(Action participant) {
        if (length > participant.getRunLimit()) {
            System.out.println(participant.getType() + " cannot run " + length + "m.");
        } else {
            System.out.println(participant.getType() + " run " + length + "m.");
        }
    }
}
