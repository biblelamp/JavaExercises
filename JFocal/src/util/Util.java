package util;

public class Util {

    public static boolean isValidLineNumber(String numLine) {
        return numLine.matches("\\d\\d?\\.\\d\\d?");
    }

    public static boolean isValidVariableName(String name) {
        return "01234567890Ff".indexOf(name.charAt(0)) < 0;
    }

}
