/**
 * Tools - service methods
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jan 25, 2018
 */
class Tools {

    static int getLineNumber(String str) {
        try {
            return Integer.parseInt(getPieceOfString(str));
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    static String getPieceOfString(String str, int idx, String delimiter) {
        String[] pieces = str.split(delimiter);
        try {
            return pieces[idx];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    static String getPieceOfString(String str, int idx) {
        return getPieceOfString(str, idx, " ");
    }

    static String getPieceOfString(String str) {
        return getPieceOfString(str, 0, " ");
    }
}