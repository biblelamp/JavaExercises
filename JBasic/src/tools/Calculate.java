package tools;

/**
 * tools.Calculate - convert infix to postfix
 *
 * @author Sergey Iryupin
 * @version 0.3.14 dated Mar 31, 2018
 */
import java.util.LinkedList;
import java.util.List;

import static tools.IConstants.*;

import model.Def;
import model.Function;
import model.Variables;

public class Calculate {
    private static List<String> list;
    private static LinkedList<String> stackFunc;
    private static LinkedList<Character> stackOper;
    private Variables variables;
    private Def def;
    
    public Calculate(Variables variables, Def def) {
		this.variables = variables;
		this.def = def;
	}

    public float calculatePostfix(List<String> list) {
        LinkedList<Float> stack = new LinkedList<>();
        float second;
        for (String str : list)
            switch (str) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "^":
                    second = stack.pop();
                    stack.push((float)Math. pow(stack.pop(), second));
                    break;
                case "-":
                    second = stack.pop();
                    stack.push(stack.pop() - second);
                    break;
                case "/":
                    second = stack.pop();
                    stack.push(stack.pop() / second);
                    break;
                default:
                    try {
                        stack.push(Float.parseFloat(str));
                    } catch (NumberFormatException ex) {
                        if (str.startsWith(FUNC_FN)) {
                            stack.push(def.calculate(str, stack.pop()));
                            break;
                        }
                        Function.calculate(str, stack, variables);
                    }
            }
        return stack.pop();
    }

    public static List<String> convertInfixToPostfix(String input) {
        list = new LinkedList<>();
        stackOper = new LinkedList<>();
        stackFunc = new LinkedList<>();
        String part = "";
        for (int i = 0; i < input.length(); i++)
            switch (input.charAt(i)) {
                case '+':
                case '-':
                    if (!part.isEmpty()) {
                        list.add(part);
                        part = "";
                    } else
                        list.add("0");
                    getOperator(input.charAt(i), 1);
                    break;
                case '*':
                case '/':
                case '^':
                    if (!part.isEmpty()) {
                        list.add(part);
                        part = "";
                    }
                    getOperator(input.charAt(i), 2);
                    break;
                case '(':
                    if (!part.isEmpty()) {
                        if (Function.isFunction(part))
                            stackFunc.push(part);
                        else
                            list.add(part);
                        part = "";
                    }
                    stackOper.push(input.charAt(i));
                    break;
                case ')':
                    if (!part.isEmpty()) {
                        list.add(part);
                        part = "";
                    }
                    getRightParenthesis();
                    break;
                case ' ':
                    break;
                default:
                    part += input.charAt(i);
            }
        if (!part.isEmpty())
            list.add(part);
        while (stackOper.size() > 0)
            list.add(stackOper.pop().toString());
        return list;
    }

    private static void getOperator(char operator, int precedence) {
        while (stackOper.size() > 0) {
            Character opTop = stackOper.pop();
            if (opTop == '(') {
                stackOper.push(opTop);
                break;
            } else {
                int prec = (opTop == '+' || opTop == '-') ? 1 : 2;
                if (prec < precedence) {
                    stackOper.push(opTop);
                    break;
                } else
                    list.add(opTop.toString());
            }
        }
        stackOper.push(operator);
    }

    private static void getRightParenthesis() {
        while (stackOper.size() > 0) {
            Character chx = stackOper.pop();
            if (chx == '(')
                break;
            else {
                list.add(chx.toString());
            }
        }
        if (stackFunc.size() > 0)
            list.add(stackFunc.pop());
    }

    public static boolean isComparison(String expression) {
        return expression.matches("(.*)(" + 
			SIGN_EQU + "|" +		// =
			SIGN_LSS + "|" +		// <
			SIGN_GRT + ")(.*)");	// >
    }

    public boolean calculateBoolean(String expression) {
        float left = 0;
        float right = 0;
        int pos = -1;
        int length = 1;
        int typeOfComparison = 0;
		int posEQU = expression.indexOf(SIGN_EQU);
        int posLSS = expression.indexOf(SIGN_LSS);
        int posGRT = expression.indexOf(SIGN_GRT);
        int posLQU = expression.indexOf(SIGN_LQU);
        int posGQU = expression.indexOf(SIGN_GQU);
        int posNQU = expression.indexOf(SIGN_NQU);
		if (posLQU > -1) {
            pos = posLQU;
            typeOfComparison = 4;
            length = 2;
        } else if (posGQU > -1) {
            pos = posGQU;
            typeOfComparison = 5;
            length = 2;
        } else if (posNQU > -1) {
            pos = posNQU;
            typeOfComparison = 6;
            length = 2;
        } else if (posEQU > -1) {
            pos = posEQU;
            typeOfComparison = 1;
        } else if (posLSS > -1) {
            pos = posLSS;
            typeOfComparison = 2;
        } else if (posGRT > -1) {
            pos = posGRT;
            typeOfComparison = 3;
        }
        if (pos > -1) {
			left =
				calculatePostfix(
					Calculate.convertInfixToPostfix(
						expression.substring(0, pos)));
			right =
				calculatePostfix(
					Calculate.convertInfixToPostfix(
						expression.substring(pos + length)));

		}
		switch (typeOfComparison) {
            case 1: return (Float.compare(left, right) == 0);
            case 2: return (Float.compare(left, right) < 0);
            case 3: return (Float.compare(left, right) > 0);
            case 4: return (Float.compare(left, right) <= 0);
            case 5: return (Float.compare(left, right) >= 0);
            case 6: return (Float.compare(left, right) != 0);
        }
		return false;
	}
}