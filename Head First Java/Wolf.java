public class Wolf extends Canine {

    public Wolf() {
        System.out.println("Wolf created.");
    }

	@Override
	public void makeNoise() {
        System.out.println("Wolf: wuuu-wuuu");
    }

	@Override
	public void eat() {
        System.out.println("Wolf eats raw meat.");
    }
}