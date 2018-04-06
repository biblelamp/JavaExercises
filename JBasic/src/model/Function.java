package model;

/**
 * model.Function - working with with built-in functions
 *
 * @author Sergey Iryupin
 * @version 0.1.2 dated Apr 06, 2018
 */
import java.util.LinkedList;

import static tools.IConstants.*;

public class Function {

    public static boolean isFunction(String str) {
        return str.equals(FN_SQR) ||
                str.equals(FN_INT) ||
                str.equals(FN_ABS) ||
                str.equals(FN_SIN) ||
                str.equals(FN_COS) ||
                str.startsWith(FUNC_FN);
    }

    public static void calculate(String str, LinkedList<Float> stack, Variables variables) {
        switch (str) {
            case FN_SQR:
                stack.push((float)Math.sqrt(stack.pop()));
                break;
            case FN_INT:
                stack.push((float)Math.floor(stack.pop()));
                break;
            case FN_ABS:
                stack.push(Math.abs(stack.pop()));
                break;
            case FN_SIN:
                stack.push((float)Math.sin(stack.pop()));
                break;
            case FN_COS:
                stack.push((float)Math.cos(stack.pop()));
                break;
            default:
                stack.push(variables.get(str));
        }
    }
}