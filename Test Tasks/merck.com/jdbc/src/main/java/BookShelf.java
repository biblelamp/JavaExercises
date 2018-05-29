/**
 * Test task Bookshelf from merck.com
 *
 * @author Sergey Iryupin
 * @version dated May 28-29, 2018
 */

import tools.Service;

public class BookShelf {

    public static void main(String[] args) {
        if (args.length > 1) {
            if (args[0].equalsIgnoreCase("--init"))
                new Service().load(args[1]);
        } else {
            System.out.println(new Service().getAuthors());
            System.out.println(new Service().getBornByYears());
            System.out.println(Service.sortByValues(new Service()
                    .getMostPopularAuthors()));
        }
    }
}