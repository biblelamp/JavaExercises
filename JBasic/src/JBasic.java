/**
 * JBasic - the simplest BASIC on Java
 *
 * @author Sergey Iryupin
 * @version 0.3.3 dated Mar 23, 2018
 */
import java.util.Scanner;

import static tools.IConstants.*;
import tools.Tools;
import core.Interpreter;
import model.ProgramLines;
import model.Variables;
import model.Data;
import model.Def;

public class JBasic {
    Interpreter interpreter;
    ProgramLines programLines;
    Variables variables;
    Data data;
    Def def;

    public static void main(String[] args) {
        new JBasic();
    }

    public JBasic() {
        programLines = new ProgramLines();
        variables = new Variables();
        data = new Data(variables);
        def = new Def();
        interpreter = new Interpreter(programLines, variables, data, def);
        Scanner scr = new Scanner(System.in);
        String str = "";
        System.out.println(MSG_START);
        do {
            if (Tools.getLineNumber(str) == 0)
                System.out.println(MSG_READY);
            str = scr.nextLine().trim();
            switch (Tools.getPartOfString(str)) {
                case CMD_NEW:
                    programLines.deleteAll();
                    break;
                case CMD_LIST:
                    programLines.list();
                    break;
                case CMD_SAVE:
                    programLines.save(str);
                    break;
                case CMD_LOAD:
                    programLines.load(str);
                    break;
                case CMD_RUN:
                    if (str.indexOf(" ") > -1) // load & run
                        programLines.load(str);
                    interpreter.run();
                    break;
                case CMD_EXIT:
                    break;
                case CMD_VALUES:
                    variables.print();
                    break;
                case OPER_PRINT:
                    interpreter.print(str);
                    break;
                case OPER_INPUT:
                    interpreter.input(Tools.getPartOfString(str, 1));
                    break;
                default:
                    if (Tools.getLineNumber(str) > 0) // add/delete program line
                        programLines.add(str);
                    else
                        System.out.println(ERR_ILLEGAL_COMMAND);
            }
        } while (!str.equals(CMD_EXIT));
    }
}