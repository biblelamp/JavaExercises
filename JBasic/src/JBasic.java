/**
 * JBasic - the simplest BASIC on Java
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Jan 25, 2018
 */
import java.util.Scanner;

class JBasic {
    final String CMD_NEW = "new";
    final String CMD_LIST = "list";
    final String CMD_DELETE = "delete";
    final String CMD_SAVE = "save";
    final String CMD_LOAD = "load";
    final String CMD_RUN = "run";
    final String CMD_EXIT = "exit";

    ProgramLines program;
    Variables variables;
    Interpreter interpreter;

    public static void main(String[] args) {
        new JBasic();
    }

    JBasic() {
        variables = new Variables();
        program = new ProgramLines();
        interpreter = new Interpreter(program, variables);
        Scanner scr = new Scanner(System.in);
        String str;
        do {
            System.out.print("> ");
            str = scr.nextLine();
            switch (Tools.getPieceOfString(str)) {
                case CMD_NEW:
                    program.deleteAll();
                    break;
                case CMD_LIST:
                    program.list();
                    break;
                case CMD_DELETE:
                    program.delete(str);
                    break;
                case CMD_SAVE:
                    program.save(str);
                    break;
                case CMD_LOAD:
                    program.load(str);
                    break;
                case CMD_RUN:
                    interpreter.run();
                    break;
                case CMD_EXIT:
                    break;
                default:
                    if (Tools.getLineNumber(str) > 0)
                        program.add(str);
            }
        } while (!str.equals(CMD_EXIT));
    }
}