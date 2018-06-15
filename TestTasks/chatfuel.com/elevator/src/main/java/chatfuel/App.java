package chatfuel;

/**
 * Test task from chatfuel.com
 * Lift simulator
 * Main class
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Dec 05, 2017
 */

class App {

    public static void main(String[] args) {
        //Elevator elevator = new Lift(9, 4, 1, 10);
        if (args.length < 4) {
            System.out.println("You can use 4 arguments in command line.");
            return;
        }
        Elevator elevator = new Elevator(
                Integer.parseInt(args[0]),  // number of the floors
                Integer.parseInt(args[1]),  // height of one floor, m
                Float.parseFloat(args[2]),  // speed of elevator, m/s
                Integer.parseInt(args[3])); // time closing the door, sec
        User user = new User(elevator);
        while (!user.isExit()) {}
    }
}