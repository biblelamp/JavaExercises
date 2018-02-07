package tools;

/**
 * tools.Calculate - convert infix to postfix
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Feb 08, 2018
 */

import java.util.LinkedList;
import java.util.List;

import model.Variables;

public class Calculate {
    private List<String> list;
    private LinkedList<Character> stackOper;
    private Variables variables;
    
    public Calculate(Variables variables) {
		this.variables = variables;
	}

    public float calculatePostfix(List<String> list) {
        LinkedList<Float> stack = new LinkedList<>();
        float second;
        //System.out.println(list);
        for (String str : list) {
            switch (str) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
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
						stack.push(variables.get(str));
					}
            }
        }
        return stack.pop();
    }

    public List<String> convertInfixToPostfix(String input) {
        list = new LinkedList<>();
        stackOper = new LinkedList<>();
        String part = "";
        for (int i = 0; i < input.length(); i++)
            switch (input.charAt(i)) {
                case '+':
                case '-':
                    if (!part.isEmpty()) {
                        list.add(part);
                        part = "";
                    }
                    getOperator(input.charAt(i), 1);
                    break;
                case '*':
                case '/':
                    if (!part.isEmpty()) {
                        list.add(part);
                        part = "";
                    }
                    getOperator(input.charAt(i), 2);
                    break;
                case '(':
                    if (!part.isEmpty()) {
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

    private void getOperator(char operator, int precedence) {
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

    private void getRightParenthesis() {
        while (stackOper.size() > 0) {
            Character chx = stackOper.pop();
            if (chx == '(')
                break;
            else {
                list.add(chx.toString());
            }
        }
    }
}
