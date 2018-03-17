package core;

/**
 * core.Interpreter - executing of the programLines
 *
 * @author Sergey Iryupin
 * @version 0.2.10 dated Mar 17, 2018
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static tools.IConstants.*;
import tools.Calculate;
import tools.Tools;
import model.ProgramLines;
import model.Variables;
import model.Data;

public class Interpreter {
    ProgramLines programLines;
    Variables variables;
    Data data;

    public Interpreter(ProgramLines programLines, Variables variables, Data data) {
        this.programLines = programLines;
        this.variables = variables;
        this.data = data;
    }

    public void run() {
        data.init(programLines);
        List<Integer> lines = new ArrayList<>(programLines.keySet());
        int idx = 0;
        int result = 0;
        while (idx < lines.size() && result != -1) {
            result = execute(programLines.get(lines.get(idx)));
            if (result == 0)
                idx++;
            else if (result > 0)
                idx = lines.indexOf(result);
        }
    }

    public int execute(String str) {
        switch (Tools.getPartOfString(str)) {
            case OPER_DATA:
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
                                new Calculate(variables)
                                    .calculateBoolean(part)
                            );
                        else
                            System.out.print(
                                new Calculate(variables)
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
				new Calculate(variables)
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
            boolean result = new Calculate(variables).calculateBoolean(expression);
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
					new Calculate(variables)
						.calculatePostfix(
							Calculate.convertInfixToPostfix(expression)));
			else
				System.out.println(ERR_ILLEGAL_VARIABLE);
		} catch (NullPointerException ex) {
			System.out.println(ERR_INVALID_EXPRESSION);
		}

    }
}
