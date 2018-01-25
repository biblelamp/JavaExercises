import java.util.ArrayList;
import java.util.List;

/**
 * Interpreter - executing of the program
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jan 25, 2018
 */
class Interpreter {
    final String OPER_INPUT = "input";
    final String OPER_PRINT = "print";
    final String OPER_IF = "if";
    final String OPER_GOTO = "goto";

    ProgramLines programLines;
    Variables variables;

    public Interpreter(ProgramLines programLines, Variables variables) {
        this.programLines = programLines;
        this.variables = variables;
    }

    void run() {
        List<Integer> lines = new ArrayList(programLines.keySet());
        for (int i = 0; i < lines.size(); i++)
            execute(programLines.get(lines.get(i)));
    }

    int execute(String str) {
        switch (Tools.getPieceOfString(str)) {
            case OPER_PRINT:
                print(str);
                break;
            case OPER_INPUT:
                input(str);
                break;
            case OPER_IF:
                ifThen(str);
                break;
            case OPER_GOTO:
                goTo(str);
                break;
            default:
                let(str);
        }
        return 0;
    }

    void print(String str) {
        for (int i = str.indexOf(" ") + 1; i < str.length(); i++)
            switch (str.charAt(i)) {
                case ';':
                case ',':
                case '"':
                    break;
                default:
                    System.out.print(str.charAt(i));
            }
        System.out.println();
    }

    void input(String str) {
    }

    void ifThen(String str) {
    }

    void goTo(String str) {
    }

    void let(String str) {
    }
}
