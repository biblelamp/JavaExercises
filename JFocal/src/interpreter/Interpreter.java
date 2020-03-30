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

    private final static String WELCOME = "JFocal, version 0.13, 30 Mar 2020";
    private final static String PROMT = "* ";

    private final static String A = "A";
    private final static String ASK = "ASK";
    private final static String C = "C";
    private final static String COMMENT = "COMMENT";
    private final static String G = "G";
    private final static String GOTO = "GOTO";
    private final static String I = "I";
    private final static String IF = "IF";
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
    private final static String NO_LINE_WITH_NUMBER = "Error: No line with number %s";

    private Scanner scanner;
    private ProgramLines program;
    private Map<String, Float> variables;
    private Float numLine;
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
            numLine = null;
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
        numLine = iterator.next();
        do {
            String line = program.get(numLine);
            if (line != null) {
                float result = processLine(line);
                if (result == 0) {
                    if (iterator.hasNext()) {
                        numLine = iterator.next();
                    } else {
                        numLine = null;
                    }
                } else if (result < 0) {
                    numLine = null;
                } else {
                    numLine = result;
                    iterator.set(result);
                }
            } else {
                numLine = null;
            }
        } while (numLine != null);
        quit = false;
    }

    private float commandAsk(String line) {
        String[] parameters = line.substring(line.indexOf(' ') + 1).split(",");
        for (String parameter : parameters) {
            if (parameter.startsWith("\"")) {
                if (parameter.endsWith("\"")) {
                    System.out.print(parameter.substring(1, parameter.length() - 1));
                } else {
                    System.out.printf(UNPAIRED_QUOTES, parameter);
                    return -1;
                }
            } else {
                System.out.print("?");
                String floatNumber = scanner.nextLine();
                try {
                    float number = Float.parseFloat(floatNumber);
                    variables.put(parameter.trim().toUpperCase(), number);
                } catch (NumberFormatException e) {
                    System.out.printf(Calculate.INVALID_NUMBER_FORMAT, floatNumber);
                    Util.printErrorMsgAddition(numLine);
                    return -1;
                }
            }
        }
        return 0;
    }

    private float commandGoto(String toLine) {
        float number;
        try {
            number = Float.parseFloat(toLine);
        } catch (NumberFormatException e) {
            System.out.printf(ProgramLines.BAD_LINE_NUMBER, toLine);
            Util.printErrorMsgAddition(numLine);
            return -1;
        }
        String line = program.get(number);
        if (line == null) {
            System.out.printf(NO_LINE_WITH_NUMBER, toLine);
            Util.printErrorMsgAddition(numLine);
            return -1;
        }
        return number;
    }

    public float commandIf(String line) {
        String[] parts = line.split("[()]");
        Float condition = Calculate.calculate(parts[1].trim(), variables);
        if (condition == null) {
            return -1;
        }
        String[] lineNums = parts[2].trim().split(",");
        if (condition < 0) {
            try {
                return Float.parseFloat(lineNums[0].trim());
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        if (condition == 0) {
            try {
                return Float.parseFloat(lineNums[1].trim());
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        if (condition > 0) {
            try {
                return Float.parseFloat(lineNums[2].trim());
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return 0;
    }

    private float commandSet(String line) {
        String[] parts = line.substring(line.indexOf(' ') + 1).split("=");
        if (parts.length < 2) {
            System.out.printf(NOT_ENOUGH_PARAMETERS, "SET");
            return -1;
        }
        Float result = Calculate.calculate(parts[1].trim(), variables);
        if (result != null) {
            variables.put(parts[0].trim().toUpperCase(), result);
        } else {
            Util.printErrorMsgAddition(numLine);
            return -1;
        }
        return 0;
    }

    private float commandType(String line) {
        String[] parameters = Util.splitString(line.substring(line.indexOf(' ') + 1), ',');
        for (String parameter : parameters) {
            String item = parameter.trim();
            if (item.startsWith("\"")) {
                if (item.endsWith("\"")) {
                    System.out.print(item.substring(1, item.length() - 1));
                } else {
                    System.out.printf(UNPAIRED_QUOTES, item);
                    return -1;
                }
            } else if (item.startsWith("%")) {
                // TODO getting number output format
            } else if (item.startsWith("!") || item.startsWith("#") || item.startsWith(":")) {
                if (item.equals("!")) {
                    System.out.println();
                }
                // TODO implement control characters: !(CR/LF) #(CR) :(TAB)
            } else {
                Float result = Calculate.calculate(parameter, variables);
                if (result != null) {
                    System.out.print(result);
                } else {
                    Util.printErrorMsgAddition(numLine);
                    return -1; // error in expression
                }
            }
        }
        return 0;
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
            String[] parts = Util.splitString(line, ';');
            for (String part : parts) {
                tokens = part.split(" ");
                String cmd = tokens[0].toUpperCase();
                switch (cmd) {
                    case A:
                    case ASK:
                        return commandAsk(part);
                    case C:
                    case COMMENT:
                        break;
                    case G:
                    case GOTO:
                        return commandGoto(tokens[1]);
                    case I:
                    case IF:
                        return commandIf(part);
                    case S:
                    case SET:
                        return commandSet(part);
                    case T:
                    case TYPE:
                        return commandType(part);
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
        }
        return 0;
    }
}
