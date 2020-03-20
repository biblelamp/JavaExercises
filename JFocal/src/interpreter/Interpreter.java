package interpreter;

import java.util.Scanner;

public class Interpreter {

    private static String WELCOME = "JFocal, version 0.02";
    private static String PROMT = "* ";

    private static String COMMAND_NOT_RECOGNIZED = "Error - command '%s' not recognized\n";

    private Scanner scanner;
    private boolean quit;

    public Interpreter() {
        scanner = new Scanner(System.in);
        quit = false;
    }

    public void run() {
        System.out.println(WELCOME);
        while (!quit) {
            System.out.print(PROMT);
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
                System.out.printf(COMMAND_NOT_RECOGNIZED, tokens[0]);
        }
    }
}
