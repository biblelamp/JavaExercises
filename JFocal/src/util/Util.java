package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    /**
     * Check: if numGrp (String) match group number (NN?)
     * @param numGrp
     * @return true if numGrp format match group number
     */
    public static boolean isValidGroupNumber(String numGrp) {
        return numGrp.matches("[1-9]\\d?");
    }

    /**
     * Check: if parameter (String) match line number (NN?.NN?)
     * @param numLine
     * @return true if numLine format match line number
     */
    public static boolean isValidLineNumber(String numLine) {
        if (numLine.matches("\\d\\d?\\.\\d\\d?")) {
            Float value = Float.parseFloat(numLine);
            return value != value.intValue() && value.intValue() > 0;
        } else {
            return false;
        }
    }

    /**
     * Check: if format (String) if valid (%N.NN?)
     * @param format
     * @return true if format valid
     */
    public static boolean isValidFormatNumber(String format) {
        return format.matches("%[123456789]\\.?\\d?\\d?");
    }

    /**
     * Format string conversion %N.nn to %N+nn.nnf
     * @param format like %N.nn
     * @return  converted format string
     */
    public static String convertFormatNumber(String format) {
        Matcher m = Pattern.compile("\\d+").matcher(format);
        int n = 5, d = 0;
        if (m.find()) {
            n = Integer.parseInt(m.group());
        }
        if (m.find()) {
            d = Integer.parseInt(m.group());
        }
        return '%' + String.format("%d.%df", n + d + 1, d);
    }

    /**
     * Check: if name not starts with 0..9 and Ff
     * @param name
     * @return true if condition is met
     */
    public static boolean isValidVariableName(String name) {
        return "01234567890Ff".indexOf(name.charAt(0)) < 0;
    }

    /**
     * Shorten the variable name to 2 characters
     * @param name of variable
     * @return shortened variable name
     */
    public static String shortenVariableName(String name) {
        if (name.indexOf('(') > -1) {
            name = name.substring(0, name.indexOf('('));
        }
        return name.length() > 2 ? name.substring(0, 2) : name;
    }

    /**
     * Convert a string of letters to a number
     * @param str string containing letters for conversion
     * @return conversion result as number
     */
    public static float convertLettersToNumber(String str) {
        float result = 0;
        for (byte b : str.toUpperCase().getBytes()) {
            if (b > 64 && b < 91) { // only from 'A' to 'Z'
                result += b - 64;
            }
        }
        return result;
    }

    /**
     * Print all live variables, using TYPE $
     * @param format
     * @param variables
     */
    public static void printVariables(String format, Map<String, Float> variables) {
        for (String name : variables.keySet()) {
            System.out.printf(Locale.ROOT,
                    (name.indexOf('(') < 0 ? "%s()=" : "%s)=") + format + "\n",
                    name, variables.get(name));
        }
    }

    /**
     * Print error message with line number if the program is running
     * @param message error message
     * @param parameter to message
     * @param iterator
     */
    public static void printErrorMsg(String message, String parameter, Iterator iterator) {
        if (message != null && parameter != null) {
            System.out.printf(message, parameter);
        }
        System.out.println(iterator == null? "" : String.format(Locale.ROOT, " in line %05.2f", iterator.get()));
    }

    /**
     * Splitting a string into parts using a delimiter
     * @param line string for splitting
     * @param delimiter delimiter character
     * @return array of strings
     */
    public static String[] splitString(String line, char delimiter) {
        List<String> parts = new ArrayList<>();
        if (line.indexOf(delimiter) < 0) {
            return new String[]{line};
        }
        line += delimiter;
        String part = "";
        boolean isString = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                isString = !isString;
            }
            if (c == delimiter && (!isString || (i == line.length() - 1))) {
                if (!part.isEmpty()) {
                    parts.add(part.trim());
                    part = "";
                }
            } else {
                part += c;
            }
        }
        return parts.toArray(new String[parts.size()]);
    }

    /**
     * Splitting a string into parts using special rules
     * @param line string for splitting
     * @return array of strings
     */
    public static String[] splitString(String line) {
        List<String> parts = new ArrayList<>();
        String part = "";
        boolean isString = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                isString = !isString;
                if (isString) {
                    if (!part.isEmpty()) {
                        parts.add(part);
                        part = "";
                    }
                    part += c;
                } else {
                    parts.add(part + '"');
                    part = "";
                }
                continue;
            }
            if ("!#:".indexOf(c) > -1 && !isString) {
                if (!part.isEmpty()) {
                    parts.add(part.trim());
                }
                parts.add(String.valueOf(c));
                part = "";
                continue;
            }
            if ((c == ',') && !isString) {
                if (!part.isEmpty()) {
                    parts.add(part.trim());
                    part = "";
                }
            } else {
                part += c;
            }
        }
        if (!part.isEmpty()) {
            parts.add(part.trim());
        }
        return parts.toArray(new String[parts.size()]);
    }

    /**
     * Splitting a string command IF
     * @param line
     * @return array of strings
     */
    public static String[] splitIf(String line) {
        List<String> parts = new ArrayList<>();
        int x = line.indexOf('(');
        int y = line.lastIndexOf(')');
        parts.add(line.substring(0, x));
        parts.add(line.substring(x + 1, y));
        parts.add(line.substring(y + 1));
        return parts.toArray(new String[parts.size()]);
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.asList(splitString("\"    \",%3,L,\"       \",FITR(A),\"  \",%4,5280*(A-FITR(A))")));
    }
}
