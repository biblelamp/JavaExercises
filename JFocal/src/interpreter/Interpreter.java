package interpreter;

import calculate.Calculate;
import program.ProgramLines;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interpreter {

    private final static String WELCOME = "JFocal, version 0.07";
    private final static String PROMT = "* ";

    private final static String S = "S";
    private final static String SET = "SET";
    private final static String T = "T";
    private final static String TYPE = "TYPE";
    private final static String Q = "Q";
    private final static String QUIT = "QUIT";

    private final static String E = "E";
    private final static String ERASE = "ERASE";
    private final static String O = "O";
    private final static String OPEN = "OPEN";
    private final static String W = "W";
    private final static String WRITE = "WRITE";

    private final static String COMMAND_NOT_RECOGNIZED = "Error: Command '%s' not recognized\n";
    private final static String NOT_ENOUGH_PARAMETERS = "Error: Not enough parameters command '%s'\n";
    private final static String OPERATION_NOT_RECOGNIZED = "Error: Operation '%s' not recognized\n";
    private final static String UNPAIRED_QUOTES = "Error: Unpaired quotes %s\n";
    public final static String ERROR_NUMBER_FORMAT = "Error number format %s\n";

    private Scanner scanner;
    private ProgramLines program;
    private Map<String, Float> variables;
    private boolean quit;

    public Interpreter() {
        scanner = new Scanner(System.in);
        program = new ProgramLines();
        variables = new HashMap<>();
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

    private void commandSet(String line) {
        String[] parts = line.substring(line.indexOf(' ') + 1).split("=");
        if (parts.length < 2) {
            System.out.printf(NOT_ENOUGH_PARAMETERS, "SET");
            return;
        }
        float result = Calculate.calculate(parts[1].trim(), variables);
        variables.put(parts[0].trim().toUpperCase(), result);
    }

    private void commandType(String line) {
        String[] parameters = line.substring(line.indexOf(' ') + 1).split(",");
        for (String parameter : parameters) {
            String item = parameter.trim();
            if (item.startsWith("\"")) {
                if (item.endsWith("\"")) {
                    System.out.print(item.substring(1, item.length() - 1));
                } else {
                    System.out.printf(UNPAIRED_QUOTES, item);
                }
            } else if (item.startsWith("%")) {
                // TODO getting number output format
            } else {
                // TODO number, variable or expression result
                System.out.println(Calculate.calculate(parameter, variables));
            }
        }
    }

    private void readWriteProgram(String[] tokens) {
        if (tokens.length < 3) {
            System.out.printf(NOT_ENOUGH_PARAMETERS, tokens[0]);
            return;
        }
        String operation = tokens[1].toUpperCase();
        switch (operation) {
            case "I":
            case "INPUT":
                program.input(tokens[2]);
                break;
            case "O":
            case "OUTPUT":
                program.output(tokens[2]);
                break;
            default:
                System.out.printf(OPERATION_NOT_RECOGNIZED, tokens[1]);
        }
    }

    private void processLine(String line) {
        String[] tokens = line.split(" ");
        if (program.isValidLineNumber(tokens[0])) {
            program.add(tokens[0], line);
        } else {
            String cmd = tokens[0].toUpperCase();
            switch (cmd) {
                case S:
                case SET:
                    commandSet(line);
                    break;
                case T:
                case TYPE:
                    commandType(line);
                    break;
                case Q:
                case QUIT:
                    quit = true;
                    break;
                case E:
                case ERASE:
                    program.erase();
                    break;
                case O:
                case OPEN:
                    readWriteProgram(tokens);
                    break;
                case W:
                case WRITE:
                    program.write();
                    break;
                default:
                    System.out.printf(COMMAND_NOT_RECOGNIZED, tokens[0]);
            }
        }
    }
}
