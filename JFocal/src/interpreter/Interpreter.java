package interpreter;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Interpreter {

    private final static String WELCOME = "JFocal, version 0.03";
    private final static String PROMT = "* ";

    private final static String Q = "Q";
    private final static String QUIT = "QUIT";

    private final static String W = "W";
    private final static String WRITE = "WRITE";

    private final static String COMMAND_NOT_RECOGNIZED = "Error - command '%s' not recognized\n";

    private Scanner scanner;
    private Map<Float, String> program;
    private boolean quit;

    public Interpreter() {
        scanner = new Scanner(System.in);
        program = new TreeMap<>();
        quit = false;
    }

    public void run() {
        System.out.println(WELCOME);
        while (!quit) {
            System.out.print(PROMT);
            String line = scanner.nextLine();
            if (line.length() > 0) {
                processLine(line);
            }
        }
    }

    private void addLineToProgram(String numLine, String line) {
        program.put(Float.valueOf(numLine), line.substring(line.indexOf(' ') + 1));
    }

    private void writeLineProgram() {
        for (Float key : program.keySet()) {
            System.out.printf("%02d.%02d %s\n", key.intValue(), (int)(key * 100 % 100), program.get(key));
        }
    }

    private boolean isValidLineNumber(String numLine) {
        return numLine.matches("\\d\\d?\\.\\d\\d?");
    }

    private void processLine(String line) {
        String[] tokens = line.split(" ");
        if (isValidLineNumber(tokens[0])) {
            addLineToProgram(tokens[0], line);
        } else {
            String cmd = tokens[0].toUpperCase();
            switch (cmd) {
                case Q:
                case QUIT:
                    quit = true;
                    break;
                case W:
                case WRITE:
                    writeLineProgram();
                    break;
                default:
                    System.out.printf(COMMAND_NOT_RECOGNIZED, tokens[0]);
            }
        }
    }
}
