package calculations;

import interpreter.Interpreter;
import util.Util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Calculate {

    private final static String OPEN_BRACKETS = "(<[";
    private final static String CLOSE_BRACKETS = ")>]";

    /**
     * Returns the result of an expression or null on error
     * @param expression in string
     * @param variables map of variables
     * @return float number or null
     */
    public static Float calculate(String expression, Map<String, Float> variables) {
        return calculatePostfix(infixToPostfix(expression), variables);
    }

    /**
     * Calculation of the result using a data stack and variables
     * @param list data stack
     * @param variables current variables
     * @return calculation result as a float number
     */
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
                        System.out.printf(Interpreter.DIVISION_BY_ZERO);
                        return null;
                    }
                    stack.push(stack.pop() / second);
                    break;
                default:
                    // calculation of built-in functions
                    if (Function.isFunction(str)) {
                        stack.push(Function.calculate(str, stack.pop()));
                        // getting an array element
                    } else if (str.indexOf('(') > -1) {
                        Float index = stack.pop();
                        if (index != index.intValue()) {
                            System.out.printf(Interpreter.INDEX_ISNT_INTEGER, String.valueOf(index));
                            return null;
                        }
                        Float number = variables.getOrDefault(str + String.valueOf(index.intValue()), 0f);
                        stack.push(number);
                    } else {
                        try {
                            stack.push(Float.parseFloat(str));
                        } catch (NumberFormatException ex) {
                            if (Util.isValidVariableName(str)) {
                                stack.push(variables.getOrDefault(Util.shortenVariableName(str.toUpperCase()), 0f));
                            } else {
                                stack.push(Util.convertLettersToNumber(str));
                            }
                        }
                    }
            }
        }
        return stack.pop();
    }

    /**
     * Convert expression to data stack
     * @param expression like string
     * @return data stack with numbers, variables, functions and operations
     */
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
                if (OPEN_BRACKETS.indexOf(c) > -1) {
                    if (Function.isFunction(numberOrVariable)) {
                        stackFunc.push(numberOrVariable.toUpperCase());
                    } else {
                        stackFunc.push(Util.shortenVariableName(numberOrVariable.toUpperCase()) + "(");
                    }
                } else {
                    result.add(numberOrVariable);
                }
                numberOrVariable = "";
            }

            // check if char is operator +,-,*,/,^
            if (precedence(c) > 0) {
                if ("+-*/^".indexOf(previous) > -1) {
                    System.out.printf(Interpreter.ERROR_IN_EXPRESSION, expression);
                    return null;
                }
                // for expressions like: -3; 2*(-2-3)
                if (c == '-' && (result.isEmpty() || OPEN_BRACKETS.contains(String.valueOf(expression.charAt(i-1))))) {
                    result.add("0");
                }
                while (!stackOper.isEmpty() && precedence(stackOper.peek()) >= precedence(c)) {
                    result.add(stackOper.pop().toString());
                }
                stackOper.push(c);
            } else if (CLOSE_BRACKETS.indexOf(c) > -1) {
                char x;
                try {
                    x = stackOper.pop();
                    while (x != OPEN_BRACKETS.charAt(CLOSE_BRACKETS.indexOf(c))) {
                        result.add(Character.toString(x));
                        x = stackOper.pop();
                    }
                } catch (NoSuchElementException ex) {
                    System.out.printf(Interpreter.UNPAIRED_PARENTHESES, expression);
                    return null;
                }
                if (!stackFunc.isEmpty()) {
                    stackFunc.pop();
                    if (!OPEN_BRACKETS.contains(stackFunc.peek())) {
                        if (stackFunc.peek().equals("FRAN") && x == '(') {
                            result.add("0"); // dummy parameter
                        }
                        result.add(stackFunc.pop());
                    }
                }
            } else if (OPEN_BRACKETS.indexOf(c) > -1) {
                stackOper.push(c);
                if (!stackFunc.isEmpty()) {
                    stackFunc.push(String.valueOf(c));
                }
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
            System.out.printf(Interpreter.UNPAIRED_PARENTHESES, expression);
            return null;
        }
        while (stackOper.size() > 0) {
            result.add(stackOper.pop().toString());
        }
        return result;
    }

    /**
     * Returns operation priority
     * @param c sign of arithmetic operation
     * @return number as operation priority
     */
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
            case '<':
            case '>':
            case '[':
            case ']':
                return 0;
        }
        return -1;
    }

    public static void main(String[] args) {
        //System.out.println(infixToPostfix("FITR(C*(20*A+S)/P/100+1)"));
        //System.out.println(infixToPostfix("10+A(1+2)"));
        System.out.println(infixToPostfix("2*(-2-3)"));
        System.out.println(infixToPostfix("-3"));
        System.out.println(infixToPostfix("5280*(A-FITR(A))"));
    }

}
