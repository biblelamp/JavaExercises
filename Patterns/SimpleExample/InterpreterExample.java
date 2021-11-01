import java.util.ArrayDeque;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface Expr {
    int interpret(Map<String, Integer> context);

    static Expr plus(Expr left, Expr right) {
        return context -> left.interpret(context) + right.interpret(context);
    }

    static Expr minus(Expr left, Expr right) {
        return context -> left.interpret(context) - right.interpret(context);
    }

    static Expr variable(String name) {
        return context -> context.getOrDefault(name, 0);
    }

    static Expr parseToken(String token, ArrayDeque<Expr> stack) {
        Expr left, right;
        switch(token) {
            case "+":
                right = stack.pop();
                left = stack.pop();
                return Expr.plus(left, right);
            case "-":
                right = stack.pop();
                left = stack.pop();
                return Expr.minus(left, right);
            default:
                return Expr.variable(token);
        }
    }

    public static Expr parse(String expression) {
        ArrayDeque<Expr> stack = new ArrayDeque<>();
        for (String token : expression.split(" ")) {
            stack.push(parseToken(token, stack));
        }
        return stack.pop();
    }
}

public class InterpreterExample {
    public static void main(String[] args) {
        Expr expr = Expr.parse("w x z - +"); // x - z + w
        Map<String, Integer> context = Stream.of(new Object[][] {
                {"w", 8},
                {"x", 12},
                {"z", 5},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
        int result = expr.interpret(context);
        System.out.println(result); // 12 - 5 + 8 = 15
    }
}