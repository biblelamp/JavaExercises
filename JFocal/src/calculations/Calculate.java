package calculations;

import util.Util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Calculate {

    public final static String ERROR_IN_EXPRESSION = "Error in expression '%s'";
    private final static String UNPAIRED_PARENTHESES = "Error: Unpaired parentheses '%s'";
    public final static String INVALID_NUMBER_FORMAT = "Error: Invalid number format '%s'";
    private final static String DIVISION_BY_ZERO = "Error: Division by zero";

    /**
     * Returns the result of an expression or null on error
     * @param expression in string
     * @param variables map of variables
     * @return float number or null
     */
    public static Float calculate(String expression, Map<String, Float> variables) {
        return calculatePostfix(infixToPostfix(expression), variables);
    }

    private static Float calculatePostfix(List<String> list, Map<String, Float> variables) {
        if (list == null) {
            return null;
        }
        LinkedList<Float> stack = new LinkedList<>();
        float second;
        for (String str : list) {
            switch (str) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "^":
                    second = stack.pop();
                    stack.push((float) Math.pow(stack.pop(), second));
                    break;
                case "-":
                    second = stack.pop();
                    stack.push((stack.size() == 0? 0 : stack.pop()) - second);
                    break;
                case "/":
                    second = stack.pop();
                    if (second == 0) {
                        System.out.printf(DIVISION_BY_ZERO);
                        return null;
                    }
                    stack.push(stack.pop() / second);
                    break;
                default:
                    // calculation of built-in functions
                    if (Function.isFunction(str)) {
                        stack.push(Function.calculate(str, stack.pop()));
                    } else {
                        try {
                            stack.push(Float.parseFloat(str));
                        } catch (NumberFormatException ex) {
                            if (Util.isValidVariableName(str)) {
                                stack.push(variables.getOrDefault(Util.shortenVariableName(str.toUpperCase()), 0f));
                            } else {
                                System.out.printf(INVALID_NUMBER_FORMAT, str);
                                return null;
                            }
                        }
                    }
            }
        }
        return stack.pop();
    }

    private static List<String> infixToPostfix(String expression) {
        expression = expression.replaceAll(" ", ""); // remove all spaces
        List<String> result = new ArrayList<>();
        LinkedList<Character> stackOper = new LinkedList<>();
        LinkedList<String> stackFunc = new LinkedList<>();
        String numberOrVariable = "";
        char previous = ' ';
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // added in list number or variable
            if (precedence(c) > -1 && !numberOrVariable.isEmpty()) {
                if (c == '(' && Function.isFunction(numberOrVariable)) {
                    stackFunc.push(numberOrVariable.toUpperCase());
                } else {
                    result.add(numberOrVariable);
                }
                numberOrVariable = "";
            }

            // check if char is operator +,-,*,/,^
            if (precedence(c) > 0) {
                if ("+-*/^".indexOf(previous) > -1) {
                    System.out.printf(ERROR_IN_EXPRESSION, expression);
                    return null;
                }
                while (!stackOper.isEmpty() && precedence(stackOper.peek()) >= precedence(c)) {
                    result.add(stackOper.pop().toString());
                }
                stackOper.push(c);
            } else if (c == ')') {
                try {
                    char x = stackOper.pop();
                    while (x != '(') {
                        result.add(Character.toString(x));
                        x = stackOper.pop();
                    }
                } catch (NoSuchElementException ex) {
                    System.out.printf(UNPAIRED_PARENTHESES, expression);
                    return null;
                }
                if (stackFunc.size() > 0 && stackOper.size() == 0) {
                    result.add(stackFunc.pop());
                }
            } else if (c == '(') {
                stackOper.push(c);
            } else {
                // character is neither operator nor parentheses
                numberOrVariable += Character.toString(c);
            }
            previous = c;
        }
        if (!numberOrVariable.isEmpty()) {
            result.add(numberOrVariable);
        }
        if (stackOper.contains('(')) {
            System.out.printf(UNPAIRED_PARENTHESES, expression);
            return null;
        }
        while (stackOper.size() > 0) {
            result.add(stackOper.pop().toString());
        }
        return result;
    }

    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            case '(':
            case ')':
                return 0;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(infixToPostfix("FSIN(2*2)"));
    }

}
