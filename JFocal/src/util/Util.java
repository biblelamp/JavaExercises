package util;

public class Util {

    public static boolean isValidLineNumber(String numLine) {
        return numLine.matches("\\d\\d?\\.\\d\\d?");
    }

}
