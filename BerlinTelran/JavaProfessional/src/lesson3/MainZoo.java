package lesson3;

public class MainZoo {
    public static void main(String[] args) {
        Cat cat = new Cat("Barsik", "red", 3);
        Dog dog = new Dog("Sharik", "black", 5);

        final int a = 1;

        int[] ints = {1, 5, 7, 9};
        IAnimal[] animals = {cat, dog};
        for (IAnimal animal : animals) {
            animal.voice();
        }
    }
}
