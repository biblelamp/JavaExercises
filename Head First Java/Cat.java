public class Cat extends Feline implements Pet {
    private String name;

    public Cat() {
        System.out.println("Cat created.");
    }

    public Cat(String name) {
        this.name = name;
        System.out.println("Cat '" + name + "' created.");
    }

	@Override
	public void makeNoise() {
        System.out.println("Cat: meow-meow");
    }

	@Override
	public void eat() {
        System.out.println("Cat eats home food.");
    }

	@Override
	public void beFriendly() {
        System.out.println("Cat is friendly.");
    }

	@Override
	public void play() {
        System.out.println("Cat plays with a ball of yarn.");
    }
}