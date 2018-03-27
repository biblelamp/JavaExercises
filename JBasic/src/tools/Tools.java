package tools;

/**
 * tools.Tools - service methods
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Mar 27, 2018
 */
public class Tools {

    public static int getLineNumber(String str) {
        try {
            return Integer.parseInt(getPartOfString(str));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static String getPartOfString(String str, int idx, String delimiter) {
        String[] pieces = str.split(delimiter);
        try {
            return pieces[idx];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    public static String getPartOfString(String str, int idx) {
        return getPartOfString(str, idx, " ");
    }

    public static String getPartOfString(String str) {
        return getPartOfString(str, 0);
    }

    public static String floatToString(float number) {
        if (Math.floor(number) == number)
            return Integer.toString(Math.round(number));
        else
            return Float.toString(number);
    }
}