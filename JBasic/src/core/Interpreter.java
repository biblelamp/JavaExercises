package core;

/**
 * core.Interpreter - executing of the programLines
 *
 * @author Sergey Iryupin
 * @version 0.2.3 dated Feb 08, 2018
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static tools.IConstants.*;

import tools.Calculate;
import tools.Tools;
import model.ProgramLines;
import model.Variables;

public class Interpreter {
    ProgramLines programLines;
    Variables variables;

    public Interpreter(ProgramLines programLines, Variables variables) {
        this.programLines = programLines;
        this.variables = variables;
    }

    public void run() {
        List<Integer> lines = new ArrayList<>(programLines.keySet());
        int idx = 0;
        int resut = 0;
        while (idx < lines.size() && resut != -1) {
            int result = execute(programLines.get(lines.get(idx)));
            if (result == 0)
                idx++;
            else if (result > 0)
                idx = lines.indexOf(result);
        }
    }

    public int execute(String str) {
        switch (Tools.getPartOfString(str)) {
            case OPER_PRINT:
                print(str);
                break;
            case OPER_INPUT:
                input(Tools.getPartOfString(str, 1));
                break;
            case OPER_GOTO:
                return goTo(Tools.getPartOfString(str, 1));
            case OPER_IF:
                ifThen(str);
                break;
            default:
                let(str);
        }
        return 0;
    }

    public void print(String str) {
        String part = "";
        boolean isString = false;
        str += " ";
        for (int i = str.indexOf(" ") + 1; i < str.length(); i++)
            switch (str.charAt(i)) {
                case ' ':
                case ',':
                    if (isString)
                        part += str.charAt(i);
                    else if (!part.isEmpty()) {
                        Calculate calculate = new Calculate(variables);
                        System.out.print(
                             calculate.calculatePostfix(
                                  calculate.convertInfixToPostfix(part)));
                    }
                    break;
                case '"':
                    if (isString) {
                        System.out.print(part);
                        part = "";
                        isString = false;
                    } else
                        isString = true;
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
            String value = scr.nextLine();
            Calculate calculate = new Calculate(variables);
            variables.put(name,
				calculate.calculatePostfix(
                     calculate.convertInfixToPostfix(value)));
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
        return 0;
    }

    public void let(String str) {
        String name = Tools.getPartOfString(str, 0, "=").trim();
        String expression = Tools.getPartOfString(str, 1, "=").trim();
        /*if (variables.isNameValid(name))
            variables.put(name, calculateNumericExpression(expression));
        else
            System.out.println(ERR_ILLEGAL_VARIABLE);
        */
    }
}
