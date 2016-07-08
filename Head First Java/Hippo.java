public class Hippo extends Animal {

    public Hippo() {
        System.out.println("Hippo created.");
    }

	@Override
	public void makeNoise() {
        System.out.println("Hippo: gru-gru");
    }

	@Override
	public void eat() {
        System.out.println("Hippo eat grass.");
    }
}