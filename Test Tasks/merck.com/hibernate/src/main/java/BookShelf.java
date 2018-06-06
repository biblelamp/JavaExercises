/**
 * Test task Bookshelf from merck.com
 *
 * @author Sergey Iryupin
 * @version dated Jun 06, 2018
 */

import controller.SQLite;
import tools.Service;

public class BookShelf {

    public static void main(String[] args) {
        if (args.length > 1) {
            if (args[0].equalsIgnoreCase("--init"))
                new Service().load(args[1]);
        } else {
            Service service = new Service();
            System.out.println(service.getAuthors());
            System.out.println(service.getBornByYears());
            System.out.println(service.sortByValues(new Service()
                    .getMostPopularAuthors()));
        }
        SQLite.close();
        System.exit(0);
    }
}