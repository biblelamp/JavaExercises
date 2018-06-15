package chatfuel;

/**
 * Test task from chatfuel.com
 * Elevator class
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Dec 05, 2017
 */
import java.util.Timer;
import java.util.TimerTask;

class Elevator {
    private int numFloors;      // number of the floors
    private int heightFloor;    // height of one floor, m
    private float speed;        // speed of elevator, m/s
    private int timeOpenDoor;   // time closing the door, sec
    private int floor;          // current floor
    private int targerFloor;    // target floor
    private float position;     // position of elevator, m
    private int timeDoor;       // remaining open door time, sec
    private TimerTask timerTask;

    Elevator(int numFloors, int heightFloor, float speed, int timeOpenDoor) {
        this.numFloors = numFloors;
        this.heightFloor = heightFloor;
        this.speed = speed;
        this.timeOpenDoor = timeOpenDoor;
        // init default
        floor = 1;
        targerFloor = 1;
        timeDoor = 0; // door is closed
        position = 0;
        // start timer
        TimerTask timerTask = new OneTic();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1000); // 1 sec
    }

    private void setFloor(int floor) {
        this.floor = floor;
        position = 0;
        System.out.println("Elevator on the " + floor + " floor.");
        if (floor == targerFloor) {
            System.out.println("Elevator arrived.");
            openDoor(timeOpenDoor);
        }
    }

    private void openDoor(int timeDoor) {
        this.timeDoor = timeDoor;
        System.out.println("The door is opened.");
    }

    private void closeDoor() {
        timeDoor--;
        if (timeDoor == 0)
            System.out.println("The door is closed.");
    }

    public boolean isDoorOpen() {
        return timeDoor > 0;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public int getFloor() {
        return floor;
    }

    public void setTargetFloor(int targerFloor) {
        if (targerFloor > 0 && targerFloor <= numFloors) {
            if (targerFloor == floor)
                openDoor(timeOpenDoor);
            else 
                this.targerFloor = targerFloor;
        }
    }

    // the elevator in real time
    private class OneTic extends TimerTask {

        @Override
        public void run() {
            if (timeDoor > 0)
                closeDoor();
            else if (floor != targerFloor)
                if (targerFloor > floor) {
                    if (position > heightFloor)
                        setFloor(++floor);
                    else
                        position += speed;
                }
                if (targerFloor < floor) {
                    if (position < heightFloor)
                        setFloor(--floor);
                    else
                        position -= speed;
                }
        }
    }
}