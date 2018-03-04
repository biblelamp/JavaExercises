import java.util.Arrays;
import java.util.ArrayList;

public class CarRacing {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("Attention >>> Preparation...");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
            new Thread(cars[i]).start();
        }
        System.out.println("Attention >>> The race began!");
        System.out.println("Attention >>> The race is over!");
    }
}

class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        this.name = "Racer #" + (++CARS_COUNT);
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is getting ready");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(name + " is ready (" + speed + ")");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }
}

abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car car);
}

class Road extends Stage {

    public Road(int length) {
        this.length = length;
        this.description = "Road " + length + " meters";
    }

    @Override
    public void go(Car car) {
        try {
            System.out.println(
                car.getName() + " started the stage: " + description);
            Thread.sleep(length / car.getSpeed() * 1000);
            System.out.println(
                car.getName() + " finished the stage: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {

    public Tunnel() {
        this.length = 80;
        this.description = "Tunnel " + length + " meters";
    }

    @Override
    public void go(Car car) {
        try {
            try {
                System.out.println(car.getName() +
                    " prepares for the stage (waits): " + description);
                System.out.println(car.getName() +
                    " started the stage: " + description);
                Thread.sleep(length / car.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(car.getName() +
                    " finished the stage: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Race {
    private ArrayList<Stage> stages;

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }
}