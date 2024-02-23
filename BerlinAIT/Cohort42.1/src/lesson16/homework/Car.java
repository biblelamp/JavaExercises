package lesson16.homework;

public class Car {

    double maxFuel;

    double fuel;

    public Car(double maxFuel) {
        this.maxFuel = maxFuel;
    }

    public void refuel(PetrolStation station) {
        double toGet = station.decreaseFuel(maxFuel - fuel);
        fuel += toGet;
    }
}
