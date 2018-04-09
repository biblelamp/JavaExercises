package core;

/**
 * core.Interpreter - executing of the programLines
 *
 * @author Sergey Iryupin
 * @version 0.2.19 dated Apr 09, 2018
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
import model.Dim;

public class Interpreter {
    LinkedList<String> forNext;
    LinkedList<Integer> gosub;
    List<Integer> lines;
    ProgramLines programLines;
    Variables variables;
    Data data;
    Def def;
    Dim dim;

    public Interpreter(ProgramLines programLines, Variables variables,
                       Data data, Def def, Dim dim) {
        this.programLines = programLines;
        this.variables = variables;
        this.data = data;
        this.def = def;
        this.dim = dim;
    }

    public void run() {
        if (!data.init(programLines))
            return;
        def.init(programLines);
        dim.init(programLines);
        lines = new ArrayList<>(programLines.keySet());
        gosub = new LinkedList<>();
        forNext = new LinkedList<>();
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
            case OPER_DIM:
            case OPER_REM:
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

                // define name and init value of counter
                String[] dividedByTo = str.substring(3).split(OPER_TO);
                String[] counter = dividedByTo[0].split("=");
                String counterName = counter[0].trim();
                float counterInit = new Calculate(variables, def)
                        .calculatePostfix(
                                Calculate.convertInfixToPostfix(counter[1]));

                // searching and checking the next
                int next = -1;
                for (int i = idx + 1; i < lines.size(); i++)
                    if (programLines.get(lines.get(i)).startsWith(OPER_NEXT))
                        if (programLines.get(lines.get(i))
                                .substring(4)
                                .trim()
                                .equals(counterName))
                            next = i + 1;
                if (next < 0) {
                    System.out.println(ERR_FOR_WITHOUT_NEXT);
                    return -1;
                }

                // define finish and step values
                String[] afterTo = dividedByTo[1].split(OPER_STEP);
                float counterFinish = new Calculate(variables, def)
                        .calculatePostfix(
                                Calculate.convertInfixToPostfix(afterTo[0]));
                float step = 1;
                if (afterTo.length > 1)
                    step = new Calculate(variables, def)
                            .calculatePostfix(
                                    Calculate.convertInfixToPostfix(afterTo[1]));

                // init counter
                variables.put(counterName, counterInit);

                // checking the possibility to enter to the loop
                if (counterInit > counterFinish)
                    return lines.get(next);

                // save values in for-stack
                forNext.push(counterName + " " + counterFinish + " " + step + " " +
                        lines.get(idx + 1));

                break;
            case OPER_NEXT:

                // check size for-stack
                if (forNext.size() < 1) {
                    System.out.println(ERR_NOT_MATCH_WITH_FOR);
                    return -1;
                }

                // get name of counter
                String nextName = Tools.getPartOfString(str, 1);

                // get values from stack
                String[] forStr = forNext.pop().split(" ");
                float counterVal = variables.get(forStr[0]);
                float finishVal = Float.parseFloat(forStr[1]);
                float stepVal = Float.parseFloat(forStr[2]);
                int backLine = Integer.parseInt(forStr[3]);

                // checking the name of the counter
                if (!nextName.equals(forStr[0])) {
                    System.out.println(ERR_NOT_MATCH_WITH_FOR);
                    return -1;
                }

                // check condition of the end of the cycle
                if (counterVal + stepVal > finishVal)
                    return 0;

                // changing the value of the counter
                variables.put(forStr[0], counterVal + stepVal);

                // save values in for-stack
                forNext.push(forStr[0] + " " + finishVal + " " + stepVal + " " + backLine);

                // back to the beginning of the cycle
                return backLine;
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
                            System.out.print(Tools.floatToString(
                                new Calculate(variables, def)
                                    .calculatePostfix(
                                        Calculate.convertInfixToPostfix(part)))
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