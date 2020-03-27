package util;

public class Util {

    public static boolean isValidLineNumber(String numLine) {
        return numLine.matches("\\d\\d?\\.\\d\\d?");
    }

    public static boolean isValidVariableName(String name) {
        return "01234567890Ff".indexOf(name.charAt(0)) < 0;
    }

    public static String numLineToString(Float numLine) {
        return String.format("%02d.%02d %s\n", numLine.intValue(), (int)(numLine * 100 % 100));
    }

    public static void printErrorMsgAddition(Float numLine) {
        System.out.println(numLine == null? "" : " in line " + numLineToString(numLine));
    }

}
