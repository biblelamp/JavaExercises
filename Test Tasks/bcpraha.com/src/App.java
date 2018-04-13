/**
 * App - example of using command-parser
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Apr 13, 2018
 */
import cz.parser.CLExecutor;
import cz.parser.Item;

public class App {

    public static void main(String[] args) {
        CLExecutor cle = new CLExecutor();

        // fill objects with a parser
        Item<Integer> item = new Item("L", true, "-l <Integer>",
                new String[]{"-l", "--left"});
        cle.add(item);

        String cmd = "-l 5 -r 6 -o PLUS -v";
        cle.execute(cmd.split(" "));
    }
}