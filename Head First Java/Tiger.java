public class Tiger extends Feline {

    public Tiger() {
        System.out.println("Tiger created.");
    }

	@Override
	public void makeNoise() {
        System.out.println("Tiger: groowl-growl");
    }

	@Override
	public void eat() {
        System.out.println("Tiger eats raw meat.");
    }
}