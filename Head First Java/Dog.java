public class Dog extends Canine implements Pet {
    private String name;

    public Dog() {
        System.out.println("Dog created.");
    }

    public Dog(String name) {
        this.name = name;
        System.out.println("Dog '" + name + "' created.");
    }

	@Override
	public void makeNoise() {
        System.out.println("Dog: woof-woof");
    }

	@Override
	public void eat() {
        System.out.println("Dog eats home food.");
    }

	@Override
	public void beFriendly() {
        System.out.println("Dog is friendly.");
    }

	@Override
	public void play() {
        System.out.println("Dog plays with a stick.");
    }
}