/**
 * App - example of using command-parser
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Apr 12, 2018
 */
import cz.parser.CLExecutor;

public class App {

    public static void main(String[] args) {
        CLExecutor cle = new CLExecutor(args);
        cle.execute();
    }
}