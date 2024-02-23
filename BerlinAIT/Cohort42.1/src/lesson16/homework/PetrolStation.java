package lesson16.homework;

public class PetrolStation {

    double fuel;

    public PetrolStation(double fuel) {
        this.fuel = fuel;
    }

    public double decreaseFuel(double fuel) {
        if (this.fuel - fuel < 0) {
            return 0;
        }
        this.fuel -= fuel;
        return fuel;
    }
}
