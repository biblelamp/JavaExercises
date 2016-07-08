public class Lion extends Feline {

    public Lion() {
        System.out.println("Lion created.");
    }

	@Override
	public void makeNoise() {
        System.out.println("Lion: roar-roar");
    }

	@Override
	public void eat() {
        System.out.println("Lion eats raw meat.");
    }
}