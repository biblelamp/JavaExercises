package util;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static boolean isGroupLineNumber(float numLine) {
        return numLine == Math.ceil(numLine);
    }

    public static boolean isValidLineNumber(String numLine) {
        return numLine.matches("\\d\\d?\\.\\d\\d?");
    }

    public static boolean isValidVariableName(String name) {
        return "01234567890Ff".indexOf(name.charAt(0)) < 0;
    }

    public static void printErrorMsgAddition(Float numLine) {
        System.out.println(numLine == null? "" : " in line " + String.format("%05.2f", numLine));
    }

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
            if (c == delimiter && !isString) {
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
}
