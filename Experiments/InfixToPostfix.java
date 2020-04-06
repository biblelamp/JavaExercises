import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

class InfixToPostfix {

    static List<String> infixToPostfix(String expression) {
        List<String> result = new ArrayList<>();
        LinkedList<Character> stackOper = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (precedence(c) > 0) {
                while (!stackOper.isEmpty() && precedence(stackOper.peek()) >= precedence(c)) {
                    result.add(stackOper.pop().toString());
                }
                stackOper.push(c);
            } else if (c == ')') {
                char x = stackOper.pop();
                while (x != '('){
                    result.add(Character.toString(x));
                    x = stackOper.pop();
                }
            } else if (c == '(') {
                stackOper.push(c);
            } else {
                // character is neither operator nor parentheses
                result.add(Character.toString(c));
            }
            System.out.print(result);
            System.out.println(stackOper);
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
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(infixToPostfix("2*((1+1)"));
    }
}