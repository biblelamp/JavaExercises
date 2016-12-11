class ExampleInheritance {

    public static void main(String[] args) {
        Cat cat = new Cat(false, "meat and milk", 4, "black");

        System.out.println("Cat is vegetaring? " + cat.isVegetarian());
        System.out.println("Cat eats " + cat.getFood());
        System.out.println("Cat has " + cat.getNumberOfLegs() + " legs");
        System.out.println("Cat color is " + cat.getColor());
        
        Cat unknown = new Cat();
        System.out.println("Unknown cat is vegetaring? " + unknown.isVegetarian());
        System.out.println("Unknown cat eats " + unknown.getFood());
        System.out.println("Unknown cat has " + unknown.getNumberOfLegs() + " legs");
        System.out.println("Unknown cat color is " + unknown.getColor());
    }
}

class Cat extends Animal { // new class is based on class Animal and it extends it
    private String color; // new property

    public Cat(boolean vegetarian, String food, int numberOfLegs, String color) {
        super(vegetarian, food, numberOfLegs);
        this.color = color;
    }

    public Cat() { // overloaded constructor
        super();
        color = "unknown";
    }

    public String getColor() { // new method
        return color;
    }

    public void setColor(String color) { // new method
        this.color = color;
    }
}

class Animal {
    private boolean vegetarian;
    private String food;
    private int numberOfLegs;

    public Animal(boolean vegetarian, String food, int numberOfLegs) {
        this.vegetarian = vegetarian;
        this.food = food;
        this.numberOfLegs = numberOfLegs;
    }

    public Animal() { // overloaded constructor
        food = "unknown";
        numberOfLegs = -1;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }
}