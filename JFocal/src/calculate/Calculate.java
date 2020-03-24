package calculate;

import interpreter.Interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Calculate {

    public static float calculate(String expression) {
        return calculatePostfix(infixToPostfix(expression));
    }

    private static float calculatePostfix(List<String> list) {
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
                        System.out.printf(Interpreter.ERROR_NUMBER_FORMAT, str);
                    }
            }
        }
        return stack.pop();
    }

    private static List<String> infixToPostfix(String expression) {
        List<String> result = new ArrayList<>();
        LinkedList<Character> stackOper = new LinkedList<>();
        String numberOrVariable = "";
        for (int i = 0; i <expression.length(); i++) {
            char c = expression.charAt(i);

            // added in list number or variable
            if (precedence(c) > -1 && !numberOrVariable.isEmpty()) {
                result.add(numberOrVariable);
                numberOrVariable = "";
            }

            // check if char is operator +,-,*,/,^
            if (precedence(c) > 0) {
                while(!stackOper.isEmpty() && precedence(stackOper.peek()) >= precedence(c)) {
                    result.add(stackOper.pop().toString());
                }
                stackOper.push(c);
            } else if (c == ')') {
                char x = stackOper.pop();
                while(x != '('){
                    result.add(Character.toString(x));
                    x = stackOper.pop();
                }
            } else if (c == '(') {
                stackOper.push(c);
            } else {
                // character is neither operator nor (,)
                numberOrVariable += Character.toString(c);
            }
        }
        if (!numberOrVariable.isEmpty()) {
            result.add(numberOrVariable);
        }
        for (int i = 0; i < stackOper.size() + 1; i++) {
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

}
