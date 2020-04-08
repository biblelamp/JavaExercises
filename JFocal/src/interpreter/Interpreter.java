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

    private final static String WELCOME = "JFocal, version 0.22, 8 Apr 2020";
    private final static String PROMT = "*";

    private final static String A = "A";
    private final static String ASK = "ASK";
    private final static String C = "C";
    private final static String COMMENT = "COMMENT";
    private final static String D = "D";
    private final static String DO = "DO";
    private final static String F = "F";
    private final static String FOR = "FOR";
    private final static String G = "G";
    private final static String GOTO = "GOTO";
    private final static String I = "I";
    private final static String IF = "IF";
    private final static String R = "R";
    private final static String RETURN = "RETURN";
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

    private final static String COMMAND_NOT_RECOGNIZED = "Error: Command '%s' not recognized";
    private final static String NOT_ENOUGH_PARAMETERS = "Error: Not enough parameters command '%s'";
    private final static String OPERATION_NOT_RECOGNIZED = "Error: Operation '%s' not recognized";
    private final static String UNPAIRED_QUOTES = "Error: Unpaired quotes '%s'";
    private final static String NO_LINE_WITH_NUMBER = "Error: No line with number %s";
    private final static String UNKNOWN_CTRL_CHARACTER = "Error: Unknown control character '%s'";
    private final static String INVALID_NUMBER_FORMAT = "Error: Invalid number format '%s'";

    private static String formatNumber = "%8.4f";

    private Scanner scanner;
    private ProgramLines program;
    private Map<String, Float> variables;
    private Iterator<Float> iterator;
    private Float numLine;
    private Float returnToLine;
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
        iterator = new Iterator<>(numLines);
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
                    Util.printErrorMsg(UNPAIRED_QUOTES, parameter, numLine);
                    return -1;
                }
            } else {
                System.out.print("?");
                String floatNumber = scanner.nextLine();
                try {
                    float number = Float.parseFloat(floatNumber);
                    variables.put(Util.shortenVariableName(parameter.trim().toUpperCase()), number);
                } catch (NumberFormatException e) {
                    Util.printErrorMsg(Calculate.INVALID_NUMBER_FORMAT, floatNumber, numLine);
                    return -1;
                }
            }
        }
        return 0;
    }

    private float commandDo(String toLine) {
        float number = Float.parseFloat(toLine);
        if (!Util.isGroupLineNumber(number)) {
            number = processLine(program.get(number));
        } else {
            if (numLine != null) {
                returnToLine = iterator.next();
            }
            number = iterator.firstInGroup(number);
            return number;
        }
        return 0;
    }

    private float commandFor(String[] parts) {
        String[] first = parts[0].substring(parts[0].indexOf(' ') + 1).split("=");
        String countName = first[0].trim().toUpperCase();
        String[] paramStr = first[1].trim().split(",");
        int init = Integer.parseInt(paramStr[0]);
        int stop = Integer.parseInt(paramStr.length < 3? paramStr[1] : paramStr[2]);
        int step = paramStr.length < 3? 1 : Integer.parseInt(paramStr[1]);
        for (int i = init; i <= stop; i += step) {
            variables.put(Util.shortenVariableName(countName), (float)i);
            for (int j = 1; j < parts.length; j++) {
                processLine(parts[j].trim());
            }
        }
        return 0;
    }

    private float commandGoto(String toLine) {
        float number;
        try {
            number = Float.parseFloat(toLine);
        } catch (NumberFormatException e) {
            Util.printErrorMsg(ProgramLines.BAD_LINE_NUMBER, toLine, numLine);
            return -1;
        }
        String line = program.get(number);
        if (line == null) {
            Util.printErrorMsg(NO_LINE_WITH_NUMBER, toLine, numLine);
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

    private float commandReturn() {
        return returnToLine;
    }

    private float commandSet(String line) {
        String[] parts = line.substring(line.indexOf(' ') + 1).split("=");
        if (parts.length < 2) {
            Util.printErrorMsg(NOT_ENOUGH_PARAMETERS, "SET", numLine);
            return -1;
        }
        Float result = Calculate.calculate(parts[1].trim(), variables);
        if (result != null) {
            variables.put(Util.shortenVariableName(parts[0].trim().toUpperCase()), result);
        } else {
            Util.printErrorMsg(null, null, numLine);
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
                    Util.printErrorMsg(UNPAIRED_QUOTES, item, numLine);
                    return -1;
                }
            } else if (item.startsWith("%")) {
                if (item.equals("%")) {
                    formatNumber = "%e";
                } else if (!Util.isValidFormatNumber(item)) {
                    Util.printErrorMsg(INVALID_NUMBER_FORMAT, item, numLine);
                    return -1;
                } else {
                    formatNumber = item + 'f';
                }
            } else if ("!#:".indexOf(item.substring(0, 1)) > -1) {
                for (int i = 0; i < item.length(); i++) {
                    char c = item.charAt(i);
                    switch (c) {
                        case '!':
                            System.out.println();
                            break;
                        case '#':
                            System.out.print("\r");
                            break;
                        case ':':
                            System.out.print("\t");
                            break;
                        default:
                            Util.printErrorMsg(UNKNOWN_CTRL_CHARACTER, Character.toString(c), numLine);
                            return -1;
                    }
                }
            } else if (item.equals("$")) {
                for (String name : variables.keySet()) {
                    System.out.printf("%s=" + formatNumber + "\n", name, variables.get(name));
                }
            } else {
                Float result = Calculate.calculate(parameter, variables);
                if (result != null) {
                    System.out.printf(formatNumber, result);
                } else {
                    Util.printErrorMsg(null, null, numLine);
                    return -1; // error in expression
                }
            }
        }
        return 0;
    }

    private float commandOpen(String[] tokens) {
        if (tokens.length < 3) {
            Util.printErrorMsg(NOT_ENOUGH_PARAMETERS, tokens[0], numLine);
            return -1;
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
                Util.printErrorMsg(OPERATION_NOT_RECOGNIZED, tokens[1], numLine);
                return -1;
        }
        return 0;
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
                    case D:
                    case DO:
                        return commandDo(tokens[1]);
                    case F:
                    case FOR:
                        return commandFor(parts);
                    case G:
                    case GOTO:
                        return commandGoto(tokens[1]);
                    case I:
                    case IF:
                        return commandIf(part);
                    case R:
                    case RETURN:
                        return commandReturn();
                    case S:
                    case SET:
                        if (commandSet(part) < 0) {
                            return -1;
                        }
                        break;
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
                        return commandOpen(tokens);
                    case W:
                    case WRITE:
                        program.write();
                        break;
                    default:
                        Util.printErrorMsg(COMMAND_NOT_RECOGNIZED, tokens[0], numLine);
                        return -1;
                }
            }
        }
        return 0;
    }
}
