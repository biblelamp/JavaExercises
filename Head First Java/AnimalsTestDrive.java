public class AnimalsTestDrive {

    public static void main(String[] args) {

        Animal[] animals = new Animal[]{
            new Hippo(),
            new Lion(), new Tiger(), new Cat("Tom"),
            new Wolf(), new Dog("Volt")
        };
        
        for (Animal animal : animals) {
            animal.makeNoise();
            animal.eat();
            if (animal instanceof Cat) {
                Cat oCat = (Cat) animal;
                oCat.beFriendly();
                oCat.play();
            }
            if (animal instanceof Dog) {
                Dog oDog = (Dog) animal;
                oDog.beFriendly();
                oDog.play();
            }
        }
    }
}