package lesson16;

public class Cat {
    int maxVolume;

    int realVolume;

    public Cat(int maxVolume) {
        this.maxVolume = maxVolume;
        this.realVolume = 0;
    }

    public void eat(Plate plate) {
        // a cat can't eat more than it can eat (maxVolume)
        int food = plate.amountFood();
        if (food > maxVolume - realVolume) {
            food = maxVolume - realVolume;
        }
        realVolume += food;
        plate.decreaseFood(food);
    }
}
