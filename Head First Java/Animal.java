abstract public class Animal {

    String picture;
    String food;
    int hunger;
    int[] boundaries = new int[2];
    int[] location = new int[2];

    abstract public void makeNoise();
    abstract public void eat();
    public void sleep() {};
    public void roam() {};

}