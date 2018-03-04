/**
 * Java. Level 3. Lesson 5. Homework
 * We organize races:
 * - All racers must start simultaneously
 * - At the same time more than half of the riders can not enter the tunnel
 *   (this is the condition)
 * - Determine the winner of the race
 * - Only after the finish of the last racer you can report: race is over
 * - Try to synchronize all this
 *
 * @author Sergey Iryupin
 * @version Mar 04, 2018
 * @link https://github.com/biblelamp
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch; // for start/finish
import java.util.concurrent.Semaphore;      // for tunnel

public class HW5Lesson {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch START = new CountDownLatch(CARS_COUNT + 1);
    public static final Semaphore TUNNEL = new Semaphore(CARS_COUNT / 2, false);
    public static final CountDownLatch FINISH = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Attention >>> Preparation...");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int)(Math.random() * 10));
            new Thread(cars[i]).start();
        }

        while (START.getCount() > 1)   // are the racers ready for the start?
            Thread.sleep(100);         // wait 100 ms

        System.out.println("Attention >>> The race began!");
        START.countDown();             // start all the racers 

        while (FINISH.getCount() > 0); // this race is over?
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

            HW5Lesson.START.countDown();    // we expect the readiness
            HW5Lesson.START.await();        // of all racers

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (HW5Lesson.FINISH.getCount() ==  // determine the winner
                HW5Lesson.CARS_COUNT)       // of the race
            System.out.println(name + " - WIN");

        try {
            HW5Lesson.FINISH.countDown();   // we expect the finish
            HW5Lesson.FINISH.await();       // of all racers
        } catch (InterruptedException e) {
            e.printStackTrace();
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

                HW5Lesson.TUNNEL.acquire(); // block the tunnel

                System.out.println(car.getName() +
                    " started the stage: " + description);
                Thread.sleep(length / car.getSpeed() * 1000);

                HW5Lesson.TUNNEL.release(); // release the tunnel

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