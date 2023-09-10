package homework3;

abstract public class Animal implements IAnimal {
    protected int runLimit;
    protected int swimLimit;
    protected String type;
    private static int count;

    public Animal(int runLimit, int swimLimit) {
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
        count++;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public void run(int d) {
        if (d <= runLimit) {
            System.out.println(type + " can run " + d + "m.");
        } else {
            System.out.println(type + " cannot run " + d + "m.");
        }
    }

    @Override
    public void swim(int d) {
        if (d <= swimLimit) {
            System.out.println(type + " can swim " + d + "m.");
        } else {
            System.out.println(type + " cannot swim " + d + "m.");
        }
    }
}
