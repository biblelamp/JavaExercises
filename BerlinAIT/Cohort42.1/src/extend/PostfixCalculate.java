package extend;

import java.util.Stack;

/**
 * AIT-TR, cohort 42.1, Java Basic, consultation #7
 *
 * @author Sergey Iryupin
 * @version 14-Mar-24
 */
public class PostfixCalculate {
    public static void main(String[] args) {
        String[] postfix = {"10", "5", "+", "3", "*"}; //{"10", "5", "+"};
        Stack<String> stack = new Stack<>();
        int a,b,c;

        for (String str : postfix) {
            switch (str) {
                case "+":
                    a = Integer.valueOf(stack.pop());
                    b = Integer.valueOf(stack.pop());
                    c = a + b;
                    stack.push(String.valueOf(c));
                    break;
                case "-":
                    // TODO -
                case "*":
                    a = Integer.valueOf(stack.pop());
                    b = Integer.valueOf(stack.pop());
                    c = a * b;
                    stack.push(String.valueOf(c));
                    break;
                case "/":
                    // TODO /
                default:
                    stack.push(str);
            }
        }
        System.out.println(stack.pop());
    }
}
