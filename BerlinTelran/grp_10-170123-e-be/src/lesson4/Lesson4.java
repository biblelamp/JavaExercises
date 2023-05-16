package lesson4;

public class Lesson4 {
    public static void main(String[] args) {
        Cat cat = new Cat(15);
        Plate plate = new Plate(10);
        System.out.println(plate);

        // feed cat
        //plate.setFood(plate.getFood() - cat.getAppetite());
        //plate.decreaseFood(cat.getAppetite());
        cat.eat(plate);
        System.out.println(plate);
    }
}
