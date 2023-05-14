package lesson3;

public class Lesson3 {
    public static void main(String[] args) {
        IAnimal[] animals = {new Cat("Barsik", "black", 3), new Dog("Tuzik", "brown", 5)};

        for (IAnimal animal : animals) {
            System.out.println(animal.voice());
            System.out.println(animal);
        }
    }
}
