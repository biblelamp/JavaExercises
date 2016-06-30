package animal;

/**
 * Class Cat based on Animal
 */
public class Cat extends Animal {
	public Cat() {
		this.type = "Cat";
		this.speed = 13;
		this.height = 1.80f;
	}
	@Override
	public void run(int speed) {
		if (speed <= this.speed) {
			System.out.println(this.type + " runs with a speed " + speed + " kph");
		} else {
			System.out.println(this.type + " can't run with a speed of " + speed + " kph");
		}
	}
	@Override
	public void jump(float height) {
		if (height <= this.height) {
			System.out.println(this.type + " jumps to a height " + height + "m");
		} else {
			System.out.println(this.type + " can't jump to a height " + height + "m");
		}
	}
	@Override
	public void swim() {
		System.out.println(this.type + " can't swim");
	}
}