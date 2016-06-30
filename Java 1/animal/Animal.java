package animal;

/**
 * Base class - Animal
 */
public abstract class Animal {
	protected String type;
	protected int speed; // maximum running speed km/h
	protected float height; // maximum jump height in meters
	public void run(int speed) {
		System.out.println(this.type + " runs with a speed " + speed + " kph");
	}
	public void jump(float height) {
		System.out.println(this.type + " jumps to a height " + height + "m");
	}
	public void swim() {
		System.out.println(this.type + " swins");
	}
}