package interpreter;

import calculations.Calculate;
import program.ProgramLines;
import util.Iterator;
import util.Util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Interpreter {

    private final static String WELCOME = "JFocal, version 0.52, 21 May 2020";
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
    private final static String L = "L";
    private final static String LIBRARY = "LIBRARY";
    private final static String W = "W";
    private final static String WRITE = "WRITE";

    private final static String COMMAND_NOT_RECOGNIZED = "Error: Command '%s' not recognized";
    private final static String NOT_ENOUGH_PARAMETERS = "Error: Not enough parameters command '%s'";
    private final static String OPERATION_NOT_RECOGNIZED = "Error: Operation '%s' not recognized";
    private final static String NO_LINE_WITH_NUMBER = "Error: No line with number %s";
    public final static String INVALID_NUMBER_FORMAT = "Error: Invalid number format '%s'";
    public final static String RETURN_WITHOUT_DO = "Error: RETURN without %s";
    public final static String INDEX_ISNT_INTEGER = "Error: Index isn't an integer (%s)";

    public final static String ERROR_WRITING_FILE = "Error writing file '%s'\n";
    public final static String ERROR_READING_FILE = "Error reading file '%s'\n";
    public final static String INVALID_LINE_NUMBER = "Error: Invalid line number '%s'";
    public final static String NO_LINE_IN_GROUP = "Error: No line in group number '%s'";

    public final static String ERROR_IN_EXPRESSION = "Error in expression '%s'";
    public final static String UNPAIRED_PARENTHESES = "Error: Unpaired parentheses '%s'";
    public final static String DIVISION_BY_ZERO = "Error: Division by zero";

    private final static String DEFAULT_FORMAT_NUMBER = "%10.4f";

    private Scanner scanner;
    private ProgramLines program;
    private Map<String, Float> variables;
    private Iterator<Float> iterator;
    private String formatNumber;

    public Interpreter() {
        scanner = new Scanner(System.in);
        program = new ProgramLines();
        variables = new HashMap<>();
        iterator = null;
        formatNumber = DEFAULT_FORMAT_NUMBER;
    }

    /**
     * Run of dialogue with the interpreter
     */
    public void run() {
        System.out.println(WELCOME);
        while (iterator == null) {
            System.out.print(PROMT);
            String line = scanner.nextLine();
            if (line.length() > 0) {
                if (line.toUpperCase().startsWith(GO)) {
                    if (program.size() > 0) {
                        goProgram(null);
                    }
                } else {
                    processLine(line);
                    iterator = null;
                }
            }
        }
    }

    /**
     * Program execution
     * @param toLine start line number
     */
    private void goProgram(Float toLine) {
        iterator = new Iterator<Float>(program.keySet());
        if (toLine == null) {
            iterator.next();
        } else {
            iterator.set(toLine);
        }
        while (iterator != null) {
            float number = processLine(program.get(iterator.get()));
            if (number == 0) {
                iterator.next();
            } else if (number == 100) {
                Util.printErrorMsg(RETURN_WITHOUT_DO, "DO", iterator);
                iterator = null;
            } else if (number > 0) {
                iterator.set(number);
            } else {
                iterator = null;
            }
        }
    }

    /**
     * Command ASK [A] getting values for variables from the user
     * @param line
     * @return
     */
    private float commandAsk(String line) {
        String[] parameters = Util.splitString(line.substring(line.indexOf(' ') + 1));
        for (String item : parameters) {
            if (item.startsWith("\"")) {
                System.out.print(item.substring(1, item.length() - (item.endsWith("\"")? 1 : 0)));
            } else if (item.equals("!")) {
                System.out.println();
            } else {
                System.out.print(":");
                String stringNumber = scanner.nextLine();
                try {
                    float number = Float.parseFloat(stringNumber);
                    variables.put(Util.shortenVariableName(item.trim().toUpperCase()), number);
                } catch (NumberFormatException e) {
                    variables.put(Util.shortenVariableName(item.trim().toUpperCase()), Util.convertLettersToNumber(stringNumber));
                }
            }
        }
        return 0;
    }

    /**
     * Command DO [D] subroutine execution
     * @param doLine
     * @return
     */
    private float commandDo(String doLine) {
        if (doLine == null) {
            Util.printErrorMsg(NOT_ENOUGH_PARAMETERS, "D/DO", iterator);
             return -1;
        } if (Util.isValidLineNumber(doLine)) {
            return processLine(program.get(Float.parseFloat(doLine)));
        } else if (Util.isValidGroupNumber(doLine)) {
            Float numGroup = Float.parseFloat(doLine);
            Float returnToLine = 0f;
            if (iterator != null) {
                returnToLine = iterator.get(); // save line for return
            } else {
                iterator = new Iterator<>(program.keySet()); // start program mode
            }
            Float numLine = iterator.firstInGroup(numGroup);
            if (numLine == null) {
                Util.printErrorMsg(NO_LINE_IN_GROUP, doLine, iterator);
                return -1;
            }
            iterator.set(numLine);
            float number;
            do {
                number = processLine(program.get(iterator.get()));
                if (number == 0) {
                    iterator.next();
                } else if (number > 0) {
                    iterator.set(number);
                } else {
                    iterator = null;
                    return -1;
                }
            } while (iterator.get() != null && number != 100 && numGroup == iterator.get().intValue());
            iterator.set(returnToLine);
            return 0;
        } else {
            Util.printErrorMsg(INVALID_LINE_NUMBER, doLine, iterator);
            return -1;
        }
    }

    /**
     * Command FOR [F] loop implementation
     * @param parts
     * @param idx
     * @return
     */
    private float commandFor(String[] parts, int idx) {
        String[] first = parts[idx].substring(parts[idx].indexOf(' ') + 1).split("=");
        String countName = first[0].trim().toUpperCase();
        String[] paramStr = first[1].trim().split(",");
        int init = Integer.parseInt(paramStr[0]);
        int stop = Integer.parseInt(paramStr.length < 3? paramStr[1] : paramStr[2]);
        int step = paramStr.length < 3? 1 : Integer.parseInt(paramStr[1]);
        for (int i = init; i <= stop; i += step) {
            variables.put(Util.shortenVariableName(countName), (float)i);
            for (int j = idx + 1; j < parts.length; j++) {
                float result = processLine(parts[j].trim());
                if (result == -1) {
                    return -1;
                }
            }
        }
        return 0;
    }

    /**
     * Command GOTO [G] go to a new line by number
     * @param toLine
     * @return
     */
    private float commandGoto(String toLine) {
        if (toLine == null) {
            Util.printErrorMsg(NOT_ENOUGH_PARAMETERS, "G/GOTO", iterator);
            return -1;
        }
        Float number;
        if (Util.isValidLineNumber(toLine)) {
            number = Float.parseFloat(toLine);
        } else {
            Util.printErrorMsg(INVALID_LINE_NUMBER, toLine, iterator);
            return -1;
        }
        String line = program.get(number);
        if (line == null) {
            Util.printErrorMsg(NO_LINE_WITH_NUMBER, toLine, iterator);
            return -1;
        }
        if (iterator == null) { // command mode
            goProgram(number);
        }
        return number;
    }

    /**
     * Command IF [I] condition check and goto
     * @param line
     * @return
     */
    public float commandIf(String line) {
        String[] parts = Util.splitIf(line);
        Float condition = Calculate.calculate(parts[1].trim(), variables);
        if (condition == null) {
            return -1;
        }
        condition = Math.signum(condition);
        String[] lineNums = parts[2].trim().split(",");
        for (int i = 0; i < 3; i++) {
            if (condition == (i - 1)) { // condition -> [-1,0,1]
                if (lineNums.length < i + 1) {
                    return 0;
                }
                String toLine = lineNums[i].trim();
                if (toLine.length() == 0) {
                    return 0;
                }
                if (Util.isValidLineNumber(toLine)) {
                    return Float.parseFloat(toLine);
                } else {
                    Util.printErrorMsg(INVALID_LINE_NUMBER, toLine, iterator);
                    return -1;
                }
            }
        }
        return 0;
    }

    /**
     * Command SET [S] assigning value(s) to variable(s)
     * @param line
     * @return
     */
    private float commandSet(String line) {
        String[] parts = line.substring(line.indexOf(' ') + 1).split("=");
        if (parts.length < 2) {
            Util.printErrorMsg(NOT_ENOUGH_PARAMETERS, "SET", iterator);
            return -1;
        }
        String varName = Util.shortenVariableName(parts[0].trim().toUpperCase());
        String[] var = parts[0].trim().split("[()]");
        if (var.length > 1) {
            Float index = Calculate.calculate(var[1].trim(), variables);
            if (index != null) {
                if (index == index.intValue()) {
                    varName += "(" + String.valueOf(index.intValue());
                } else {
                    Util.printErrorMsg(INDEX_ISNT_INTEGER, var[1].trim(), iterator);
                    return -1;
                }
            } else {
                Util.printErrorMsg(null, null, iterator);
                return -1;
            }
        }
        Float result = Calculate.calculate(parts[1].trim(), variables);
        if (result != null) {
            variables.put(varName, result);
        } else {
            Util.printErrorMsg(null, null, iterator);
            return -1;
        }
        return 0;
    }

    /**
     * Command TYPE [T] type string and numbers to the console
     * @param line
     * @return
     */
    private float commandType(String line) {
        String[] parameters = Util.splitString(line.substring(line.indexOf(' ') + 1));
        for (String item : parameters) {
            if (item.startsWith("\"")) {
                System.out.print(item.substring(1, item.length() - (item.endsWith("\"")? 1 : 0)));
            } else if (item.startsWith("%")) {
                if (item.equals("%")) {
                    formatNumber = "%e";
                } else if (Util.isValidFormatNumber(item)) {
                    formatNumber = Util.convertFormatNumber(item);
                } else {
                    Util.printErrorMsg(INVALID_NUMBER_FORMAT, item, iterator);
                    return -1;
                }
            } else if ("!#:".contains(item)) {
                switch (item) {
                    case "!":
                        System.out.println();
                        break;
                    case "#":
                        System.out.print("\r");
                        break;
                    case ":":
                        System.out.print("\t");
                        break;
                }
            } else if (item.equals("$")) {
                Util.printVariables(formatNumber, variables);
            } else {
                Float result = Calculate.calculate(item, variables);
                if (result != null) {
                    System.out.printf(Locale.ROOT, formatNumber, result);
                } else {
                    Util.printErrorMsg(null, null, iterator);
                    return -1; // error in expression
                }
            }
        }
        return 0;
    }

    /**
     * Command ERASE [E] erasing program line(s)
     * @param parameter
     */
    private void commandErase(String parameter) {
        if (parameter == null) {
            formatNumber = DEFAULT_FORMAT_NUMBER;
            variables.clear();
        } else if (parameter.toUpperCase().equals("ALL")) {
            program.erase();
        } else {
            program.erase(parameter);
        }
    }

    /**
     * Command LIBRARY [L] call/save program file
     * @param tokens
     * @return
     */
    private float commandLibrary(String[] tokens) {
        if (tokens.length < 3) {
            Util.printErrorMsg(NOT_ENOUGH_PARAMETERS, tokens[0], iterator);
            return -1;
        }
        String operation = tokens[1].toUpperCase();
        switch (operation) {
            case "C":
            case "CALL":
                program.call(tokens[2]); // read program from file
                break;
            case "BC":
            case "BCALL":
                // TODO read program from bin file
                break;
            case "G":
            case "GO":
                program.call(tokens[2]); // read from file
                goProgram(null);         // and go program
                break;
            case "S":
            case "SAVE":
                program.save(tokens[2]); // save program to file
                break;
            case "BS":
            case "BSAVE":
                // TODO save program to bin file
                break;
            default:
                Util.printErrorMsg(OPERATION_NOT_RECOGNIZED, tokens[1], iterator);
                return -1;
        }
        return 0;
    }

    /**
     * Command WRITE [W] write program line(s)
     * @param parameter
     */
    private void commandWrite(String parameter) {
        if (parameter == null) {
            program.write();
        } else if (parameter.toUpperCase().equals("ALL")) {
            program.write();
        } else {
            program.write(parameter);
        }
    }

    /**
     * Command line execution
     * @param line command line
     * @return -1 if error, 0 continue, >0 to goto
     */
    private float processLine(String line) {
        if (line == null) {
            return -1;
        }
        String[] tokens = line.split(" ");
        if ("0123456789".contains(tokens[0].substring(0, 1))) {
            if (Util.isValidLineNumber(tokens[0])) {
                if (tokens.length > 1) {
                    program.add(tokens[0], line);
                } else {
                    program.erase(tokens[0]);
                }
            } else {
                Util.printErrorMsg(INVALID_LINE_NUMBER, tokens[0], iterator);
                return -1;
            }
        } else {
            String[] parts = Util.splitString(line, ';');
            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                tokens = part.split(" ");
                String cmd = tokens[0].toUpperCase();
                switch (cmd) {
                    case A:
                    case ASK:
                        if (commandAsk(part) < 0) {
                            return -1;
                        }
                        break;
                    case C:
                    case COMMENT:
                        break;
                    case D:
                    case DO:
                        if (commandDo(tokens.length < 2? null: tokens[1]) < 0) {
                            return -1;
                        }
                        break;
                    case F:
                    case FOR:
                        if (commandFor(parts, i) < 0) {
                            return -1;
                        }
                        break;
                    case G:
                    case GOTO:
                        return commandGoto(tokens.length < 2? null: tokens[1]);
                    case I:
                    case IF:
                        float result = commandIf(part);
                        if (result > 0) {
                            return result;
                        }
                        if (result < 0) {
                            return -1;
                        }
                        break;
                    case R:
                    case RETURN:
                        return 100;
                    case S:
                    case SET:
                        if (commandSet(part) < 0) {
                            return -1;
                        }
                        break;
                    case T:
                    case TYPE:
                        if (commandType(part) < 0) {
                            return -1;
                        }
                        break;
                    case Q:
                    case QUIT:
                        return -1;
                    case E:
                    case ERASE:
                        commandErase(tokens.length < 2? null : tokens[1]);
                        break;
                    case L:
                    case LIBRARY:
                        return commandLibrary(tokens);
                    case W:
                    case WRITE:
                        commandWrite(tokens.length < 2? null : tokens[1]);
                        break;
                    default:
                        Util.printErrorMsg(COMMAND_NOT_RECOGNIZED, tokens[0], iterator);
                        return -1;
                }
            }
        }
        return 0;
    }
}
