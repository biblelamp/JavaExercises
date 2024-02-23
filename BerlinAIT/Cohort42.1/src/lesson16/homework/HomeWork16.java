package lesson16.homework;

import lesson16.Cat;
import lesson16.Plate;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #16
 *
 * @author Sergey Iryupin
 * @version 21-Feb-24
 */
public class HomeWork16 {
    public static void main(String[] args) {
        // task #1
        Cat[] cats = {new Cat(12), new Cat(18), new Cat(20), new Cat(8)};
        Plate plate = new Plate(50);
        plate.add(40);

        //for (int i = 0; i < cats.length; i++) {
        //    Cat cat = cats[i];
        for (Cat cat : cats) {
            cat.eat(plate);
            System.out.println("Cat: " + ((double)cat.realVolume/cat.maxVolume)*100 + "%");
        }

        // task #2
        Car car = new Car(40);
        PetrolStation station = new PetrolStation(1000);
        System.out.println("Car: " + car.fuel);
        System.out.println("Station: " + station.fuel);

        car.refuel(station);
        System.out.println("Car: " + car.fuel);
        System.out.println("Station: " + station.fuel);
    }
}
