package interpreter;

import calculations.Calculate;
import program.ProgramLines;
import util.Iterator;
import util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Interpreter {

    private final static String WELCOME = "JFocal, version 0.10";
    private final static String PROMT = "* ";

    private final static String A = "A";
    private final static String ASK = "ASK";
    private final static String C = "C";
    private final static String COMMENT = "COMMENT";
    private final static String G = "G";
    private final static String GOTO = "GOTO";
    private final static String S = "S";
    private final static String SET = "SET";
    private final static String T = "T";
    private final static String TYPE = "TYPE";
    private final static String Q = "Q";
    private final static String QUIT = "QUIT";

    private final static String GO = "GO";
    private final static String E = "E";
    private final static String ERASE = "ERASE";
    private final static String O = "O";
    private final static String OPEN = "OPEN";
    private final static String W = "W";
    private final static String WRITE = "WRITE";

    private final static String COMMAND_NOT_RECOGNIZED = "Error: Command '%s' not recognized\n";
    private final static String NOT_ENOUGH_PARAMETERS = "Error: Not enough parameters command '%s'\n";
    private final static String OPERATION_NOT_RECOGNIZED = "Error: Operation '%s' not recognized\n";
    private final static String UNPAIRED_QUOTES = "Error: Unpaired quotes '%s'\n";
    public final static String ERROR_NUMBER_FORMAT = "Error number format '%s'\n";
    public final static String DIVISION_BY_ZERO = "Error: division by zero\n";

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
                if (line.toUpperCase().startsWith(GO)) {
                    if (program.size() > 0) {
                        goProgram();
                    }
                } else {
                    processLine(line);
                }
            }
        }
    }

    private void goProgram() {
        Set<Float> numLines = program.keySet();
        Iterator<Float> iterator = new Iterator<>(numLines);
        Float numLine = iterator.next();
        do {
            String line = program.get(numLine);
            if (line != null) {
                float result = processLine(line);
                if (result == 0) {
                    if (iterator.hasNext()) {
                        numLine = iterator.next();
                    } else {
                        numLine = -1f;
                    }
                } else {
                    numLine = result;
                    iterator.set(result);
                }
            } else {
                numLine = -1f;
            }
        } while (numLine > -1);
        quit = false;
    }

    private void commandAsk(String line) {
        String[] parameters = line.substring(line.indexOf(' ') + 1).split(",");
        for (String parameter : parameters) {
            if (parameter.startsWith("\"")) {
                if (parameter.endsWith("\"")) {
                    System.out.print(parameter.substring(1, parameter.length() - 1));
                } else {
                    System.out.printf(UNPAIRED_QUOTES, parameter);
                }
            } else {
                System.out.print("?");
                float number = Float.parseFloat(scanner.nextLine());
                variables.put(parameter.trim().toUpperCase(), number);
            }
        }
    }

    private float commandGoto(String numLine) {
        float number = Float.parseFloat(numLine);
        return number;
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
            } else if (item.startsWith("!") || item.startsWith("#") || item.startsWith(":")) {
                if (item.equals("!")) {
                    System.out.println();
                }
                // TODO implement control characters: !(CR/LF) #(CR) :(TAB)
            } else {
                // TODO number, variable or expression result
                System.out.print(Calculate.calculate(parameter, variables));
            }
        }
    }

    private void commandOpen(String[] tokens) {
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

    private float processLine(String line) {
        String[] tokens = line.split(" ");
        if (Util.isValidLineNumber(tokens[0])) {
            program.add(tokens[0], line);
        } else {
            String cmd = tokens[0].toUpperCase();
            switch (cmd) {
                case A:
                case ASK:
                    commandAsk(line);
                    break;
                case C:
                case COMMENT:
                    break;
                case G:
                case GOTO:
                    return commandGoto(tokens[1]);
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
                    return -1;
                case E:
                case ERASE:
                    program.erase();
                    break;
                case O:
                case OPEN:
                    commandOpen(tokens);
                    break;
                case W:
                case WRITE:
                    program.write();
                    break;
                default:
                    System.out.printf(COMMAND_NOT_RECOGNIZED, tokens[0]);
                    return -1;
            }
        }
        return 0;
    }
}
