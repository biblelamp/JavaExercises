/**
 * Tools - service methods
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jan 25, 2018
 */
class Tools {

    static int getLineNumber(String str) {
        try {
            return Integer.parseInt(getPartOfString(str));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    static String getPartOfString(String str, int idx, String delimiter) {
        String[] pieces = str.split(delimiter);
        try {
            return pieces[idx];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    static String getPartOfString(String str, int idx) {
        return getPartOfString(str, idx, " ");
    }

    static String getPartOfString(String str) {
        return getPartOfString(str, 0);
    }
}