package tools;

/**
 * tools.Calculate - convert infix to postfix
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jan 31, 2018
 */
import java.util.LinkedList;
import java.util.List;

public class Calculate {
    private List<String> list;
    private LinkedList<Character> stackOper;

    public List<String> convertInfixToPostfix(String input) {
        list = new LinkedList<>();
        stackOper = new LinkedList<>();
        String part = "";
        for (int i = 0; i < input.length(); i++)
            switch (input.charAt(i)) {
                case '+':
                case '-':
                    list.add(part);
                    part = "";
                    getOperator(input.charAt(i), 1);
                    break;
                case '*':
                case '/':
                    list.add(part);
                    part = "";
                    getOperator(input.charAt(i), 2);
                    break;
                case '(':
                    list.add(part);
                    part = "";
                    stackOper.push(input.charAt(i));
                    break;
                case ')':
                    list.add(part);
                    part = "";
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

    public float calculatePostfix(List<String> list) {
        System.out.print(list);
        float result = 0;
        return result;
    }
}