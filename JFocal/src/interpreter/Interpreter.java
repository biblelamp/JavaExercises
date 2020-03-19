package interpreter;

import java.util.Scanner;

public class Interpreter {

    private Scanner scanner;
    private boolean quit;

    public Interpreter() {
        scanner = new Scanner(System.in);
        quit = false;
    }

    public void run() {
        System.out.println("JFocal, version 0.01");
        while (!quit) {
            System.out.print("# ");
            String line = scanner.nextLine();
            processLine(line);
        }
    }

    private void processLine(String line) {
        String[] tokens = line.split(" ");
        switch (tokens[0]) {
            case "q":
            case "quit":
                quit = true;
                break;
            default:
                System.out.printf("Error - command '%s' not recognized\n", tokens[0]);
        }
    }
}
