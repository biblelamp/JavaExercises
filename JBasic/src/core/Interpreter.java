package core;

/**
 * core.Interpreter - executing of the programLines
 *
 * @author Sergey Iryupin
 * @version 0.2.14 dated Mar 26, 2018
 */
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import static tools.IConstants.*;

import tools.Calculate;
import tools.Tools;
import model.ProgramLines;
import model.Variables;
import model.Data;
import model.Def;

public class Interpreter {
    LinkedList<Integer> gosub;
    List<Integer> lines;
    ProgramLines programLines;
    Variables variables;
    Data data;
    Def def;

    public Interpreter(ProgramLines programLines, Variables variables, Data data, Def def) {
        this.programLines = programLines;
        this.variables = variables;
        this.data = data;
        this.def = def;
    }

    public void run() {
        def.init(programLines);
        data.init(programLines);
        lines = new ArrayList<>(programLines.keySet());
        gosub = new LinkedList<>();
        int idx = 0;
        int result = 0;
        while (idx < lines.size() && result != -1) {
            result = execute(programLines.get(lines.get(idx)), idx);
            if (result == 0)
                idx++;
            else if (result > 0) {
                idx = lines.indexOf(result);
                if (idx < 0) {
                    System.out.println(ERR_UNDEFINED_LINE_NUMBER);
                    return;
                }
            } else
                break;
        }
    }

    public int execute(String str, int idx) {
        switch (Tools.getPartOfString(str)) {
            case OPER_DATA:
            case OPER_DEF:
                break;
            case OPER_READ:
                return data.read(str.substring(OPER_READ.length()));
            case OPER_PRINT:
                print(str);
                break;
            case OPER_INPUT:
                input(Tools.getPartOfString(str, 1));
                break;
            case OPER_GOTO:
                return goTo(Tools.getPartOfString(str, 1));
            case OPER_IF:
                return ifThen(str);
            case OPER_GOSUB:
                gosub.push(idx + 1);
                return goTo(Tools.getPartOfString(str, 1));
            case OPER_RETURN:
                if (gosub.size() > 0)
                    return lines.get(gosub.pop());
                else {
                    System.out.println(ERR_ILLEGAL_RETURN);
                    return -1;
                }
            case OPER_FOR:

                // define name of counter

                // looking for tne next

                // define init, finish and step values

                // save values in for-stack

                break; // temporary stub
            case OPER_NEXT:

                // check size for-stack

                // get values and check condition of finish

                break; // temporary stub
            case OPER_END:
                return -1;
            default:
                let(str);
        }
        return 0;
    }

    public void print(String str) {
        String part = "";
        boolean isString = false;
        str += "\n";
        for (int i = str.indexOf(" ") + 1; i < str.length(); i++)
            switch (str.charAt(i)) {
                case '"':
                case ' ':
                case ',':
                case '\n':
                    if (str.charAt(i) == '"')
                        if (isString) {
                            System.out.print(part);
                            part = "";
                            isString = false;
                            break;
                        } else
                            isString = true;
                    if (str.charAt(i) == ' ') {
                        if (isString)
                            part += str.charAt(i);
                        break;
                    }
                    if (!part.isEmpty()) {
                        if (Calculate.isComparison(part))
                            System.out.print(
                                new Calculate(variables, def)
                                    .calculateBoolean(part)
                            );
                        else
                            System.out.print(
                                new Calculate(variables, def)
                                    .calculatePostfix(
                                        Calculate.convertInfixToPostfix(part))
                            );
                        System.out.print((str.charAt(i) == ',')? "\t" : " ");
                        part = "";
                    } else
                        System.out.print((str.charAt(i) == ',')? "\t" : "");
                    break;
                default:
                    part += str.charAt(i);
            }
        System.out.println();
    }

    public void input(String name) {
        if (variables.isNameValid(name)) {
            Scanner scr = new Scanner(System.in);
            System.out.print("? ");
            String expression = scr.nextLine();
            variables.put(name,
				new Calculate(variables, def)
					.calculatePostfix(
						Calculate.convertInfixToPostfix(expression)));
        } else
            System.out.println(ERR_ILLEGAL_VARIABLE);
    }

    public int goTo(String str) {
        try {
            int line = Integer.parseInt(str);
            programLines.get(line);
            return line;
        } catch (NumberFormatException | NullPointerException ex) {
            return -1;
        }
    }

    public int ifThen(String str) {
        str = str.substring(2); // skip if
        try {
            String lineStr = Tools.getPartOfString(str, 1, OPER_THEN).trim();
            String expression = Tools.getPartOfString(str, 0, OPER_THEN).trim();
            int line = Integer.parseInt(lineStr);
            boolean result = new Calculate(variables, def)
                    .calculateBoolean(expression);
            if (result) {
                programLines.get(line);
                return line;
            } else
                return 0;
        } catch (NumberFormatException | NullPointerException ex) {
            return -1;
        }
    }

    public void let(String str) {
		String expression = null;
        String name = Tools.getPartOfString(str, 0, "=").trim();
        if (name.startsWith(OPER_LET))
			name = name.substring(OPER_LET.length()).trim();
		try {
			expression = Tools.getPartOfString(str, 1, "=").trim();
			if (variables.isNameValid(name))
				variables.put(name,
					new Calculate(variables, def)
						.calculatePostfix(
							Calculate.convertInfixToPostfix(expression)));
			else
				System.out.println(ERR_ILLEGAL_VARIABLE);
		} catch (NullPointerException ex) {
			System.out.println(ERR_INVALID_EXPRESSION);
		}

    }
}