package chatfuel;

/**
 * Test task from chatfuel.com
 * User class
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Dec 05, 2017
 */
import java.util.Scanner;
 
class User {
    private Elevator elevator;
    private Scanner reader;
    private boolean isOutside;
    private boolean isOver;
    private int floor;

    User(Elevator elevator) {
        this.elevator = elevator;
        reader = new Scanner(System.in);
        isOutside = true;
        isOver = false;
        floor = 1;
        console();
    }

    public boolean isExit() {
        return isOver;
    }

    private void call() {
        System.out.println("Elevator on the " + elevator.getFloor() + " floor.");
        elevator.setTargetFloor(floor);
    }

    private void go(String str) {
        if (str.equals("in")) {
            if (isOutside && elevator.isDoorOpen()) {
                isOutside = false;
                System.out.println("You entered the elevator.");
            } else
                System.out.println("The attempt to enter failed.");
            return;
        }
        if (str.equals("out")) {
            if (!isOutside && elevator.isDoorOpen()) {
                isOutside = true;
                floor = elevator.getFloor();
                System.out.println("You left the elevator.");
            } else
                System.out.println("The attempt to left failed.");
            return;
        }
        try {
            int goal = Integer.parseInt(str);
            if (goal < 1 || goal > elevator.getNumFloors())
                throw new NumberFormatException();
            if (isOutside) {
                floor = goal;
                System.out.println("You are on the " + goal + " floor.");
            } else {
                elevator.setTargetFloor(goal);
                System.out.println("You are going to the " + goal + " floor.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Bad command argument.");
        }
    }

    private void console() {
        System.out.println("You can control elevator and user uging commands:\n" +
            "  call   - call elevator on the floor or open the door\n" +
            "  go in  - enter the elevator\n" +
            "  go out - left the elevator\n" +
            "  go <N> - go to the N floor by foot or by elevator\n" +
            "  exit   - end the program\n" +
            "Enjoy!");
        while (true) {
            String[] cmd = reader.nextLine().split(" ");
            switch (cmd[0]) {
                case "call": call();        // call the elevator
                    break;
                case "go":   go(cmd[1]);    // go on the floor
                    break;
                case "exit": isOver = true; // end the program
                    return;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}