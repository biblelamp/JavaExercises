/**
 * App - example of using command-parser
 *
 * @author Sergey Iryupin
 * @version 0.2.2 dated Apr 15, 2018
 */
import cz.parser.Action;
import cz.parser.CLExecutor;
import cz.parser.Item;

public class App {

    public static void main(String[] args) {
        CLExecutor cle = new CLExecutor();

        // fill objects with a parser
        cle.add(new Item<Integer>("L", true, "-l/--left <Integer>",
                new String[]{"-l", "--left"}, Action.GET));
        cle.add(new Item<Integer>("R", true, "-r/--right <Integer>",
                new String[]{"-r", "--right"}, Action.GET));
        cle.add(new Item<Integer>("O", true, "-o/--operation <Action>",
                new String[]{"-o", "--operation"}, Action.CALCULATE));
        cle.add(new Item<Boolean>("S", false, "-v/--verbose",
                new String[]{"-v", "--verbose"}, Action.PRINT));

        String cmd = "-l 5 -r 6 -o PLUS -v";
        cle.execute(cmd.split(" "));
    }
}