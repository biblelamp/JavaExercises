/**
 * Java. Test tasks from http://webjer.com/testjava1.html
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Mar 5, 2017
 */
import java.util.*;
 
public class TestJava {

    public static void main(String[] args) {

        // test for QUESTION #1
        System.out.println("QUESTION #1\n");
        System.out.println(allStringSetsIdentical(null));
        System.out.println(allStringSetsIdentical(new String[][]{{}}));
        System.out.println(allStringSetsIdentical(new String[][]{{"a", "b"}}));
        System.out.println(allStringSetsIdentical(
            new String[][]{{"a", "b"}, {"b", "b", "a"}, {"b", "a"}}));
        System.out.println(allStringSetsIdentical(
            new String[][]{{"a","b"},{"a"},{"b"}}));

        // test for QUESTION #2:
        System.out.println("\nQUESTION #2\n");
        System.out.println(StateUtils.createStateSelectList());
        System.out.println(StateUtils.parseSelectedState("Arizona"));
        System.out.println(StateUtils.parseSelectedState("Kansas"));
        System.out.println(StateUtils.displayStateFullName("CA"));
        System.out.println(StateUtils.displayStateFullName("KA"));

        // test for QUESTION #3:
        System.out.println("\nQUESTION #3\n");
        String text =
            "The  String\n\n argument\n may\n contain\n lines\n\n longer than"+
            " maxCharsPerLine.\nNewlines should be added so that each line in" +
            " the return  String  has at most maxCharsPerLine characters.\nTo" +
            " determine where newlines should be added, \n try to fit as many" +
            " words as possible on a line\n(while keeping line length at most" +
            " maxCharsPerLine and satisfying  the other requirements)\nbefore" +
            " starting a new line.";
        System.out.println(wrapText(text, 50));
    }

    /**
     * QUESTION #1:
     *
     * Write a Java method that takes an array of "sets" of String objects,
     * and determines whether _all_ sets in the array are equivalent.
     *
     * Each "set" in the input array is represented as an array of String
     * objects, in no particular order, and possibly containing duplicates.
     * Nevertheless, when determining whether two of these "sets" are
     * equivalent, you should disregard order and duplicates. For example,
     * the sets represented by these arrays are all equivalent:
     * {"a", "b"}
     * {"b", "a"}
     * {"a", "b", "a"}
     *
     * The signature for your method should be:
     * public static boolean allStringSetsIdentical(String[ ][ ] sets)
     *
     * Examples of the method in operation:
     * allStringSetsIdentical(
     *    new String[][]{{"a","b"},{"b","b","a"},{"b","a"}}) returns true
     * allStringSetsIdentical(
     *    new String[][]{{"a","b"},{"a"},{"b"}}) returns false
     */
    public static boolean allStringSetsIdentical(String[][] sets) {
        if (sets == null || sets.length < 2)
            return false;
        String[] sample = new HashSet<String>(
            Arrays.asList(sets[0])).toArray(new String[0]);
        for (int i = 1; i < sets.length; i++)
            if (!Arrays.equals(sample, new HashSet<String>(
                Arrays.asList(sets[i])).toArray(new String[0])))
                return false;
        return true;
    }

    /**
     * QUESTION #2:
     *
     * The following Java code is responsible for creating an HTML "SELECT"
     * list of U.S. states, allowing a user to specify his or her state. This
     * might be used, for instance, on a credit card transaction screen.
     *
     * Please rewrite this code to be "better". Submit your replacement code,
     * and please also submit a few brief comments explaining why you think your
     * code is better than the sample.
     *
     * Seggey Iryupin:
     *    I think my variant is better because it divides data and code which
     *    processes them. Also my variant is more compact.
     *
     * (For brevity, this sample works for only 5 states. The real version would
     * need to work for all 50 states. But it is fine if your rewrite shows only
     * the 5 states here.)
     */
    public static class StateUtils {
        static String[][] states = {
            {"Alabama", "Alaska", "Arizona", "Arkansas", "California"},
            {"AL", "AK", "AZ", "AR", "CA"}
        };

        /**
         * Generates an HTML select list that can be used to select a specific
         * U.S. state.
         */
        public static String createStateSelectList() {
            StringBuffer str = new StringBuffer("<select name=\"state\">\n");
            for (int i = 0; i < states[0].length; i++)
                str.append("<option value=\"" + states[0][i] + "\">" +
                states[0][i] + "</option>\n");
            str.append("</select>\n");
            return str.toString();
        }

        /**
         * Parses the state from an HTML form submission, converting it to
         * the two-letter abbreviation.
         */
        public static String parseSelectedState(String s) {
            int index = Arrays.binarySearch(states[0], s);
            return (index < 0)? null : states[1][index];
        }

        /**
         * Displays the full name of the state specified by the two-letter code.
         */
        public static String displayStateFullName(String abbr) {
            int index = Arrays.binarySearch(states[1], abbr);
            return (index < 0)? null : states[0][index];
        }
    }

    /**
     * QUESTION #3:
     *
     * Write a Java method with the following method signature that takes
     * a String and returns a String formatted so that it satisfies the
     * requirements below. It may need to insert newlines and/or delete spaces.
     *
     * Method Signature:
     * public static String wrapText(String text, int maxCharsPerLine)
     * Definitions and Assumptions:
     *
     * A word is a nonempty sequence of characters that contains no spaces and
     * no newlines. Lines in the return String are separated by the newline
     * character, '\n'. Words on each line are separated by spaces. Assume that
     * the String argument does not contain any whitespace characters other than
     * spaces and newlines.
     *
     * Requirements:
     * 1. Newlines in the String argument are preserved.
     * 2. Words in the return String are separated by either a single space or
     * by one or more newlines.
     * 3. Lines in the return String do not start or end with any spaces.
     * 4. When constructing the return String from the String argument, each
     * word in the String argument with at most maxCharsPerLine characters
     * should not be broken up. Each word in the String argument with more than
     * maxCharsPerLine characters should be broken up so that all of the other
     * requirements are satisfied.
     * 5. The String argument may contain lines longer than maxCharsPerLine.
     * Newlines should be added so that each line in the return String has at
     * most maxCharsPerLine characters. To determine where newlines should be
     * added, try to fit as many words as possible on a line (while keeping line
     * length at most maxCharsPerLine and satisfying the other requirements)
     * before starting a new line.
     */
    public static String wrapText(String text, int maxCharsPerLine) {
        StringBuffer result = new StringBuffer();
        String[] words = text.split("(\\s)+");
        int length = 0;
        for (String str : words) {
            if ((length + str.length() + 1) < maxCharsPerLine) {
                result.append(" " + str);
                length += str.length() + 1;
            } else {
                result.append("\n" + str);
                length = str.length() + 1;
            }
        }
        return result.delete(0, 1).toString();
    }
}