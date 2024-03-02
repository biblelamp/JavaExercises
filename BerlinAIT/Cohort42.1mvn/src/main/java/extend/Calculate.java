package extend;

import java.util.Arrays;

public class Calculate {
    public static String[] expressionToTokens(String exp) {
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

    public static Integer calculate(String[] tokens) {
        String[] newTokens = new String[tokens.length];
        newTokens[0] = tokens[0];
        int length = 1;
        // calculation of higher priority operations '*','/'
        int result = Integer.valueOf(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            switch (tokens[i]) {
                case "*":
                    result *= Integer.valueOf(tokens[i + 1]);
                    newTokens[length - 1] = String.valueOf(result);
                    break;
                case "/":
                    result /= Integer.valueOf(tokens[i + 1]);
                    newTokens[length - 1] = String.valueOf(result);
                    break;
                default:
                    newTokens[length] = tokens[i];
                    newTokens[length + 1] = tokens[i + 1];
                    result = Integer.valueOf(tokens[i + 1]);
                    length += 2;
            }
        }
        // calculation of low priority operations '+', '-'
        result = Integer.valueOf(newTokens[0]);
        for (int i = 1; i < length; i += 2) {
            switch (newTokens[i]) {
                case "+":
                    result += Integer.valueOf(newTokens[i + 1]);
                    break;
                case "-":
                    result -= Integer.valueOf(newTokens[i + 1]);
                    break;
                default:
                    System.out.println("Undefined operation: " + newTokens[i]);
                    return null;
            }
        }
        return result;
    }
}
