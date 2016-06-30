package animal;

/**
 * Class Dog based on Animal
 */
public class Dog extends Animal {
	public Dog() {
		this.type = "Dog";
		this.speed = 10;
		this.height = 0.72f;
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
}