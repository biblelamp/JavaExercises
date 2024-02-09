package extend;

import java.util.Arrays;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #9 ext
 *
 * @author Sergey Iryupin
 * @version 09-Feb-24
 */
public class HomeWork9Ext {
    public static void main(String[] args) {
        String[] tokens = expressionToTokens("16-23-1+18");
        System.out.println(Arrays.toString(tokens));
        System.out.println("= " + calculate(tokens));
    }

    static String[] expressionToTokens(String exp) {
        String[] tokens = new String[exp.length()];
        int idx = 0;
        String number = "";
        for (int i = 0; i < exp.length(); i++) {
            char chr = exp.charAt(i);
            switch (chr) {
                case '+', '-', '*', '/':
                    tokens[idx] = number;
                    tokens[idx + 1] = String.valueOf(chr);
                    idx += 2;
                    number = "";
                    break;
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                    number += chr;
                    break;
            }
        }
        if (!number.isEmpty()) {
            tokens[idx] = number;
            idx++;
        }
        return Arrays.copyOf(tokens, idx);
    }

    static Integer calculate(String[] tokens) {
        int result = Integer.valueOf(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            switch (tokens[i]) {
                case "+":
                    result += Integer.valueOf(tokens[i + 1]);
                    break;
                case "-":
                    result -= Integer.valueOf(tokens[i + 1]);
                    break;
                default:
                    System.out.println("Undefined operation: " + tokens[i]);
                    return null;
            }
        }
        return result;
    }
}
